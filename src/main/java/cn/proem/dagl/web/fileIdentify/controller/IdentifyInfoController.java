package cn.proem.dagl.web.fileIdentify.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.bpm.context.TaskOperate;
import cn.proem.bpm.model.Executor;
import cn.proem.bpm.model.NextStep;
import cn.proem.bpm.model.StartParam;
import cn.proem.bpm.model.Task;
import cn.proem.bpm.service.BpmQueryService;
import cn.proem.bpm.service.ProcessService;
import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.core.util.BeanUtils;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileBase;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileFlow;
import cn.proem.dagl.web.fileIdentify.dto.DtoIdentifyContent;
import cn.proem.dagl.web.fileIdentify.entity.FileIdentify;
import cn.proem.dagl.web.fileIdentify.entity.IdentifyContent;
import cn.proem.dagl.web.fileIdentify.service.IdentifyInfoService;
import cn.proem.dagl.web.fileIdentify.service.IdentifyService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.HandParam;
import cn.proem.suw.web.common.util.StringUtil;


/**
 * 档案鉴定控制层
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/w/fileidentify/identifyInfo")
public class IdentifyInfoController extends BaseCtrlModel{
	@Resource
	public TaskService taskService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private IdentifyService identifyService;
	@Autowired
	private IdentifyInfoService identifyInfoService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private BpmQueryService bpmQueryService;
	@Autowired
	private DicManagerService dicManagerService;
	@Autowired
	private DicManagerService dicService;
	
	
	private String valueOf(Object obj) {
	    return (obj == null) ? "" : obj.toString();
	}
	
	
	private String getDicTitle(String dno, String val){
	    List<DictionaryValue> dictionaryValues = dicService
                .getDicValueList(dno);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(val)){
	            return dic.getDvalue();
	        }
	    }
	    return null;
	}
	
	/**
	 * @Description 初始化页面
	 * @MethodName initView
	 * @author bao
	 * @date 2017年5月19日
	 * @param request
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/initView")
	public ModelAndView initView(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/identifyInfo.vm");
		return modelAndView;
	}
	
	
	/**
	 * @Description 跳转修改期限页面
	 * @MethodName toModifyRetentionView
	 * @author bao
	 * @date 2017年5月22日
	 * @param request
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/toModifyRetentionView")
	public ModelAndView toModifyRetentionView(HttpServletRequest request,String tablename,String ids,String flag) throws ServiceException{
		ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/modifyRetention.vm");
		
		String condition = " name = '修改保管期限流程'";
        List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
        String processId = processes.get(0).get("ID");
        
        modelAndView.addObject("processId",processId);
		modelAndView.addObject("tablename", tablename);
		modelAndView.addObject("ids", ids);
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	
	/**
	 * @Description 获得已经鉴定的档案集合
	 * @MethodName getIdentifyFiles
	 * @author bao
	 * @date 2017年5月21日
	 * @param dtGridPager
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/getIdentifyFiles")
	@ResponseBody
	@LogService(description = "查询已经鉴定的档案集合")
	public String getIdentifyFiles(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		//获得表名
			String tablename = String.valueOf(query.getParameters().get("tablename"));
			if(StringUtil.isNotEmpty(tablename)){
			String condition1;
			if(query.getParameters().get("tm")!=null){
				String tm = String.valueOf(query.getParameters().get("tm"));
				//3 档案 6 审批中 7已拒绝 
				condition1 = "delflag = '0' AND isarchive IN (3,5,6,7) AND sfjd = 'sfjd_2' AND NVL(tm,0) like '%"+tm+"%'";
			}else{
				condition1 = "delflag = '0' AND isarchive IN (3,5,6,7) AND sfjd = 'sfjd_2'";
			}
			dataGrid.setRecordCount(customArchiveService.getEntitiesByConditions(tablename, condition1).size());
			List<BaseEntityInf> records = customArchiveService.getEntitiesByPaging(tablename, condition1, dataGrid.getStartRecord(),dataGrid.getPageSize());
			for(BaseEntityInf entity : records){
				DtoFileBase dtoFileBase = new DtoFileBase();
				dtoFileBase.setUuid(this.valueOf(entity.get("UUID")));
				dtoFileBase.setDh(this.valueOf(entity.get("DH")));
				dtoFileBase.setBgqx(this.getDicTitle("bgqx", this.valueOf(entity.get("BGQX"))));
				dtoFileBase.setWh(this.valueOf(entity.get("WH")));
				dtoFileBase.setZrz(this.valueOf(entity.get("ZRZ")));
				dtoFileBase.setTm(this.valueOf(entity.get("TM")));
				dtoFileBase.setCwrq(this.valueOf(entity.get("CWRQ")));
				dtoFileBase.setMj(this.getDicTitle("mj", this.valueOf(entity.get("MJ"))));
				dtoFileBase.setIsarchive(this.valueOf(entity.get("ISARCHIVE")));
				dtoFileBases.add(dtoFileBase);
			}
			
			dataGrid.setExhibitDatas(dtoFileBases);
		}else{
			dataGrid.setRecordCount(0);
			dataGrid.setExhibitDatas(dtoFileBases);
		}
		
		return JSON.toJSONStringWithDateFormat(dataGrid,
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	
	/**
	 * @Description发起流程 
	 * @MethodName startProcess
	 * @author bao
	 * @date 2017年5月15日
	 * @param request
	 * @param obj
	 * @return ResultModel<String>
	 */
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/startProcess")
	@ResponseBody
	@LogService(description = "发起流程")
	public ResultModel<String> startProcess(HttpServletRequest request, @RequestBody Map<String, Object> obj) {
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
			String bgqx = (String)obj.get("BGQX");
			List<String> businessIds =  (List<String>)obj.get("businessIds");
			for(String businessId : businessIds){
				//获取当前用户，即发起人
				User user = this.getCurrentUser(request);
				UserDepartment department = commonService.findUserDepartmentByUserId(user.getId());
				//启动参数的普通字段属性
				StartParam startParam = BeanUtils.cloneObject(StartParam.class, obj.get("startParam"));
				startParam.setBusinessId(businessId);
				String tablename = businessId.substring(0, businessId.indexOf(","));
				String tableid = businessId.substring(businessId.indexOf(",")+1);
				
				//发起流程的第一个节点直接为当前使用默认
				startParam.setNextHandlers(null);
				
				
				//配置流程变量
				Map<String,Object> others = new HashMap<String, Object>();
				others.put("BGQX", bgqx);
				startParam.setOthers(others);
				
				//发起流程的第一个节点直接为当前使用默认
				//传入参数， 启动流程
				processService.startProcess(startParam, department);
					
					
					//获取待办任务列表
					DataGrid<Task> tasks = bpmQueryService.queryTask(user.getId(), startParam.getProcessId(), 1, -1);
					Task task = null;
					//找到第一个任务节点
					if (tasks != null && tasks.getRecordCount() > 0) {
						for (Task t : tasks.getExhibitDatas()) {
							if (t.getBusinessId().equals(startParam.getBusinessId())) {
								task = t;
								break;
							}
						}
					}
					//找到下一步
					List<NextStep> nextSteps = bpmQueryService.getNextSteps(task.getId());
					NextStep nextStep = null;
					if (nextSteps != null && nextSteps.size() > 0) {
						nextStep = nextSteps.get(0);
					}
					//启动参数的下一步执行人的集合
					Set<UserDepartment> nextDepartments = null;
					if (obj.get("nextHandlers") != null) {
						nextDepartments = new HashSet<UserDepartment>();
						List<String> list = (List<String>)obj.get("nextHandlers");
						for (String nextId : list) {
						    User nextUser = commonService.findById(nextId, User.class);
						    UserDepartment nextDepartment = commonService.findUserDepartmentByUserId(nextUser.getId());
						    nextDepartments.add(nextDepartment);
						}
					}
					
					//执行下一步的任务节点
					Executor executor = new Executor();
					executor.setTaskId(task.getId());
					executor.setOutcome(nextStep.getKey());
					executor.setOperate(TaskOperate.APPROVE);
					executor.setNextHandlers(nextDepartments);
					processService.handTask(executor, department);
					
					//更改为审批中状态
					BaseEntityInf entity = customArchiveService.getEntity(tablename, tableid);
					entity.set("ISARCHIVE", 6);
					customArchiveService.update(entity);
			}
			
				
				
		}catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("已在流程中，请等待结果");
		} 
		return resultModel;
	}
	
	
	
	
	/**
	 * @Description 待办列表页面
	 * @MethodName toDoTaskView
	 * @author bao
	 * @date 2017年5月7日
	 * @param request
	 * @return
	 * @throws ServiceException ModelAndView
	 */
     @RequestMapping(value = "/modifyRetentionToDoTaskView")
     public ModelAndView modifyRetentionToDoTaskView(HttpServletRequest request) throws ServiceException{
    	 User user = this.getCurrentUser(request);
         ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/modifyRetentionToDo.vm");
         
         //获得流程定义id
         String condition = " name = '修改保管期限流程'";
         List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
         String processId = processes.get(0).get("ID");
         
         modelAndView.addObject("processId", processId);
         return modelAndView;
 	 }
     
     
 	/**
		 * @Description 获得待办列表
		 * @MethodName getToDoTasks
		 * @author bao
		 * @date 2017年5月7日
		 * @param request
		 * @param dtGridPager
		 * @param processId
		 * @return String
		 */
		@RequestMapping(value = "/getToDoTasks")
		@ResponseBody
		@LogService(description = "获取待办列表")
		public String getToDoTasks(HttpServletRequest request, String dtGridPager,String processId) {
			User user = this.getCurrentUser(request);
			DataGridQuery query = parseToQuery(dtGridPager);
			
			//申明dtgrid所需属性
			DataGrid<DtoFileFlow> dataGrid = new DataGrid<DtoFileFlow>();
			dataGrid.setNowPage(query.getNowPage());
			dataGrid.setPageSize(query.getPageSize());
			Integer recordCount = 0;
			try {
				//获取该流程的对应实体类全限定名
				List<DtoFileFlow> datas = new ArrayList<DtoFileFlow>();
				//待办任务列表
				DataGrid<Task> tasks = bpmQueryService.queryTask(user.getId(), processId, query.getNowPage(), query.getPageSize());
				if (tasks != null && tasks.getRecordCount() > 0) {
					int skipcnt = 0;
					for (Task task : tasks.getExhibitDatas()) {
						
						
						String businessId = task.getBusinessId();
						String tablename = businessId.substring(0, businessId.indexOf(","));
						String tableid = businessId.substring(businessId.indexOf(",")+1);
						
						if("undefined".equals(tablename) || "undefined".equals(tableid)) {
							skipcnt = skipcnt + 1;
							continue ;
						}
						
						BaseEntityInf entity = customArchiveService.getEntity(tablename, tableid);
						try{
							DtoFileFlow dtoFileFlow = new DtoFileFlow();
							dtoFileFlow.setBusinessId(businessId);
							dtoFileFlow.setNodeName(task.getName());
							dtoFileFlow.setUuid(this.valueOf(entity.get("UUID")));
							dtoFileFlow.setDh(this.valueOf(entity.get("DH")));
							dtoFileFlow.setBgqx(this.getDicTitle("bgqx", this.valueOf(entity.get("BGQX"))));
							dtoFileFlow.setWh(this.valueOf(entity.get("WH")));
							dtoFileFlow.setZrz(this.valueOf(entity.get("ZRZ")));
							dtoFileFlow.setTm(this.valueOf(entity.get("TM")));
							dicManagerService.getDictionaryByDno("MJ");
							dtoFileFlow.setCwrq(this.valueOf(entity.get("CWRQ")));
							dtoFileFlow.setMj(this.getDicTitle("mj", this.valueOf(entity.get("MJ"))));
							
							String processInstanceId = task.getProcessInstanceId();
							
							org.activiti.engine.task.Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
							Object text =  taskService.getVariables(task1.getId()).get("BGQX");
							
							dtoFileFlow.setNewbgqx(this.getDicTitle("bgqx",String.valueOf(text)));
							datas.add(dtoFileFlow);
							
						}catch(NullPointerException e){
							continue;
						}
					}
					recordCount = tasks.getRecordCount();
				}
				dataGrid.setExhibitDatas(datas);
				dataGrid.setRecordCount(recordCount);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			return JSON.toJSONStringWithDateFormat(
				  	dataGrid,
	                "yyyy-MM-dd",
	                SerializerFeature.WriteDateUseDateFormat,
	                SerializerFeature.DisableCircularReferenceDetect);
			
		}
		
		/**
		 * @Description 流程扭转
		 * @MethodName handProcess
		 * @author bao
		 * @date 2017年5月7日
		 * @param request
		 * @param obj
		 * @return ResultModel<String>
		 * @throws ServiceException 
		 */
		@RequestMapping(value = "/handProcess")
		@ResponseBody
		@LogService(description = "流程扭转")
		public ResultModel<String> handProcess(HttpServletRequest request, @RequestBody Map<String, Object> obj) throws ServiceException {
			ResultModel<String> resultModel = new ResultModel<String>();
			User user = this.getCurrentUser(request);
			UserDepartment department = commonService.findUserDepartmentByUserId(user.getId());
			try {
				//解析数据
				HandParam handParam = BeanUtils.cloneObject(HandParam.class, obj.get("handParam"));
				
				String businessId = handParam.getBusinessId();
				String tablename = businessId.substring(0, businessId.indexOf(","));
				String tableid = businessId.substring(businessId.indexOf(",")+1);
				
				
				
				//任务对象ID
				DataGrid<Task> tasks = bpmQueryService.queryTask(user.getId(), handParam.getProcessId(), 1, -1);
				Task task = null;
				//找到任务对象（所有节点使用同一个taskId）
				if (tasks != null && tasks.getRecordCount() > 0) {
					for (Task t : tasks.getExhibitDatas()) {
						if (t.getBusinessId().equals(businessId)) {
							task = t;
							break;
						}
					}
				}
				
				//更新档案表的是否鉴定字段
				String processInstanceId = task.getProcessInstanceId();
				
				org.activiti.engine.task.Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
				Object text =  taskService.getVariables(task1.getId()).get("BGQX");
				
				
				
				Executor executor = new Executor();
				//找到指定类型的下一步
				
				Boolean isAgree = false;
				
				NextStep nextStep = null;
				List<NextStep> nextSteps = bpmQueryService.getNextSteps(task.getId());
				if (nextSteps != null && nextSteps.size() > 0) {
					if (nextSteps.size() == 1) {
						nextStep = nextSteps.get(0);
					} else {
						if(StringUtil.isEmpty(handParam.getExpression())){
							for (NextStep step : nextSteps) {
								if(step.getOperate().equals(handParam.getOperate()) && StringUtils.isEmpty(step.getExpression())){
									if(step.getOperate().equals("APPROVE")){
										isAgree = true;
									}
									nextStep = step;
									break;
								}
							}
						}else{
							for (NextStep step : nextSteps) {
								if(StringUtils.isNotEmpty(step.getExpression())){
									if(step.getOperate().equals("APPROVE") && StringUtils.isEmpty(step.getExpression())){
										isAgree = true;
									}
									nextStep = step;
									break;
								}
							}
						}
					}
				}
				
				
				//设置下一次执行人
				Set<UserDepartment> nextHandlerUsers = new HashSet<UserDepartment>();
				if (obj.get("nextHandlers") != null) {
					List<String> nextHandlers = (List<String>)obj.get("nextHandlers");
					for (String uid : nextHandlers) {
						User us = commonService.findById(uid, User.class);
						UserDepartment userd = new UserDepartment();
						userd.setUser(us);
						nextHandlerUsers.add(userd);
					}
					executor.setNextHandlers(new HashSet<UserDepartment>(nextHandlerUsers));
				}
				
				//执行到下一步
				executor.setTaskId(task.getId());
				executor.setOutcome(nextStep.getKey());
				executor.setOperate(this.convertOperateType(handParam.getOperate()));
				processService.handTask(executor, department);
				
				
				
				if(isAgree){
					BaseEntityInf fileEntity = customArchiveService.getEntity(tablename, tableid);
					fileEntity.set("BGQX", String.valueOf(text));
					
					customArchiveService.update(fileEntity);
					
					//添加档案鉴定表
					FileIdentify fileIdentify = new FileIdentify();
					fileIdentify.setQzh(this.valueOf(fileEntity.get("QZH")));
					fileIdentify.setNdh(this.valueOf(fileEntity.get("NDH")));
					fileIdentify.setWh(this.valueOf(fileEntity.get("WH")));
					fileIdentify.setMlh(this.valueOf(fileEntity.get("MLH")));
					fileIdentify.setAjh(this.valueOf(fileEntity.get("AJH")));
					fileIdentify.setZtdw(this.valueOf(fileEntity.get("ZTDW")));
					fileIdentify.setZtlx(this.valueOf(fileEntity.get("ZTLX")));
					fileIdentify.setZtsl(this.valueOf(fileEntity.get("ZTSL")));
					
					fileIdentify.setDh(this.valueOf(fileEntity.get("DH")));
					fileIdentify.setTm(this.valueOf(fileEntity.get("TM")));
					fileIdentify.setCwrq(this.valueOf(fileEntity.get("CWRQ")));
					fileIdentify.setZrz(this.valueOf(fileEntity.get("ZRZ")));
					fileIdentify.setBgqx(this.valueOf(fileEntity.get("BGQX")));
					fileIdentify.setMj(this.valueOf(fileEntity.get("MJ")));
					fileIdentify.setBm(tablename);
					fileIdentify.setBz(this.valueOf(fileEntity.get("BZ")));
					fileIdentify.setDaid(this.valueOf(fileEntity.get("UUID")));
					fileIdentify.setJdsj(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					
					
					String bgqxvalue = this.getDicTitle("bgqx", this.valueOf(text));
					fileIdentify.setJdnr("修改为"+bgqxvalue);
					
					String fileIdentifyId = identifyService.saveFileIdentify(fileIdentify);
					
					//添加鉴定内容表
					IdentifyContent identifyContent = new IdentifyContent();
					identifyContent.setBm(tablename);
					identifyContent.setDaid(this.valueOf(fileEntity.get("UUID")));
					identifyContent.setJdid(fileIdentifyId);
					identifyContent.setJdsj(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					identifyContent.setJdnr("修改为"+bgqxvalue);
					identifyContent.setJdr(user.getId());
					identifyService.saveIdentifyContent(identifyContent);
					
					
					//更改为审批中状态
					BaseEntityInf entity = customArchiveService.getEntity(tablename, tableid);
					entity.set("ISARCHIVE", 3);
					customArchiveService.update(entity);
				}
				
				if("REJECT".equals(handParam.getOperate())){
					//更改为审批中状态
					BaseEntityInf entity = customArchiveService.getEntity(tablename, tableid);
					entity.set("ISARCHIVE", 7);
					customArchiveService.update(entity);
				}
				
			}catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			}catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("提交失败");
			}  
			
			return resultModel;
		}
		
		/**
		 * @MethodName getOperateType
		 * @Description 转换操作类型
		 * @author Pan Jilong
		 * @date 2017年3月1日
		 * @param operate
		 * @return
		 */
		private TaskOperate convertOperateType(String operate) {
			if (TaskOperate.APPROVE.toString().equals(operate)) {
				return TaskOperate.APPROVE;
			} else if(TaskOperate.REJECT.toString().equals(operate)) {
				return TaskOperate.REJECT;
			} else if (TaskOperate.FIRST.toString().equals(operate)) {
				return TaskOperate.FIRST;
			} else if (TaskOperate.CANCEL.toString().equals(operate)) {
				return TaskOperate.CANCEL;
			} else if (TaskOperate.BACK.toString().equals(operate)) {
				return TaskOperate.BACK;
			} else if (TaskOperate.STOP.toString().equals(operate)) {
				return TaskOperate.STOP;
			} else if (TaskOperate.JUMP.toString().equals(operate)) {
				return TaskOperate.JUMP;
			}
			return null;
		}
		
		
		/**
		 * @Description 获得鉴定内容中修改保管期限的内容
		 * @MethodName getIdentifyInfoTables
		 * @author bao
		 * @date 2017年5月22日
		 * @param dtGridPager
		 * @param request
		 * @return String
		 */
		@RequestMapping(value = "/getIdentifyInfoTables")
		@ResponseBody
		@LogService(description = "获取鉴定内容中修改保管期限的内容")
		public String getIdentifyInfoTables(String dtGridPager, HttpServletRequest request){
			DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
			QueryBuilder queryBuilder =  query.generateQueryBuilder();
			queryBuilder.addCondition(new QueryCondition("bm",null, ConditionType.NNULL, FieldType.STRING,null));
			
			
			return JSON.toJSONStringWithDateFormat(identifyInfoService.getIdentifyInfoTables(queryBuilder, query.getNowPage(), query.getPageSize()),
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect);
		}
		
		
		/**
		 * 
		 * @Description:保管期限修改历史批量导出
		 * @author:bao
		 * @time:2017年7月5日 上午10:59:02
		 */
		@RequestMapping(value = "/exportIdentifyInfo/{ids}")
		@ResponseBody
		@LogService(description = "保管期限修改历史批量导出")
		public ResultModel<String> exportDestroyHistory(
				HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable("ids")String ids){
			ResultModel<String> resultModel = new ResultModel<String>();
			try{
					List<DtoIdentifyContent> identifyContents = new ArrayList<DtoIdentifyContent>();
					QueryBuilder queryBuilder = new QueryBuilder();
					if(StringUtil.isEmpty(ids)){
						queryBuilder.addCondition(new QueryCondition("bm",null, ConditionType.NNULL, FieldType.STRING,null));
						identifyContents = identifyInfoService.getIdentifyInfoTables(queryBuilder);
					}else{
						queryBuilder.addCondition(new QueryCondition("id",ids,ConditionType.IN, FieldType.STRING, null));
						identifyContents = identifyInfoService.getIdentifyInfoTables(queryBuilder);
					}
				
					
	
					// 获得所有数据
			        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
					HSSFSheet sheet = workbook.createSheet("保管期限修改导出记录");		//生成工作页
					sheet.setDefaultColumnWidth(15);				//sheet默认列宽
					HSSFCellStyle style = workbook.createCellStyle();	//生成样式
					
					HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
					cellStyle.setWrapText(true);
					
					//导出excel的样式
					style.setFillForegroundColor(HSSFColor.WHITE.index);  
			        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
			        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
			        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
			        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
			        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
			        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
					
			        // 生成一个字体  
			        HSSFFont font = workbook.createFont();  
			        font.setColor(HSSFColor.BLACK.index);  
			        font.setFontHeightInPoints((short) 12);  
			        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
			        style.setFont(font);
					
					
					 HSSFRow rowHeader = sheet.createRow(0);
					 String[] headers = {"档号","鉴定内容","鉴定时间","鉴定人"};
					 for(int i = 0;i<headers.length;i++){
			        	HSSFCell cell = rowHeader.createCell(i);
						HSSFRichTextString text = new HSSFRichTextString(headers[i]);
						cell.setCellStyle(style);
						cell.setCellValue(text);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        	
					 }
				 
				 for(int i = 0;i<identifyContents.size();i++){
					DtoIdentifyContent dtoIdentifyContent = identifyContents.get(i);
					User jdr = commonService.findById(dtoIdentifyContent.getJdr(), User.class);
					HSSFRow row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(dtoIdentifyContent.getDh());
		        	row.createCell(1).setCellValue(dtoIdentifyContent.getJdnr());
		        	row.createCell(2).setCellValue(dtoIdentifyContent.getJdsj());
		        	row.createCell(3).setCellValue(StringUtil.isEmpty(jdr)?"":jdr.getName());
						
				}
					 
					
				OutputStream output=response.getOutputStream();  
		        response.reset();  
		        response.setHeader("Content-disposition", "attachment; filename="+new String("保管期限修改历史.xls".getBytes("utf-8"), "iso8859-1"));  
		        response.setContentType("application/msexcel");          
		        workbook.write(output);  
		        output.close();  
			}catch(Exception e){
				resultModel.setSuccess(false);
				resultModel.setMsg("批量导出失败");
			} 
			return resultModel;
				
			}
}
