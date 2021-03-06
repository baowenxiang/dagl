package cn.proem.dagl.web.fileControl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.util.BeanUtils;
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileControl.entity.FileControl;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileBase;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileFlow;
import cn.proem.dagl.web.fileIdentify.service.IdentifyService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.HandParam;
import cn.proem.suw.web.common.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
@RequestMapping("/w/dahk")
public class FileControlController extends BaseCtrlModel {
	
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private IdentifyService identifyService;
	@Autowired
	private FileControlService fileControlService;
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
	
	@RequestMapping("/initview")
	public ModelAndView initFileControlView(){
		
		ModelAndView view = this.createNormalView("/web/fileControl/initView.vm");
		
		return view;
		
	}
	
	/**
	 * @Description 跳转查询页面
	 * @MethodName queryView
	 * @author bao
	 * @date 2017年5月31日
	 * @param request
	 * @param tablename
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/queryView")
	public ModelAndView queryView(HttpServletRequest request,String tablename) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileControl/queryView.vm");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}
	
	/**
	 * 获取所有档案名
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDtablename")
	@ResponseBody
	@LogService(description = "获取所有档案名")
	public ResultModel<DTableName> getDtablename(HttpServletRequest request){
		ResultModel<DTableName> resultModel = new ResultModel<DTableName>();
		
		List<DTableName> dTableNames = identifyService.getAllDtablename();
		resultModel.setDatas(dTableNames);
		return resultModel;
	}
	/**
	 * 获取档案数据
	 * @return
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/getControlFiles")
	@ResponseBody
	@LogService(description = "获取档案数据")
	public String getControlFiles(String dtGridPager, HttpServletRequest request) throws ServiceException{
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		//获得表名
		String tablename = String.valueOf(query.getParameters().get("tablename"));
		if(StringUtil.isNotEmpty(tablename)){
			//3 档案 4 审批中 5已拒绝 
			String condition1;
			if(fileControlService.hasField(tablename, "mj")){
			    //密级（mj）为公开
			    if(query.getParameters().get("tm")!=null){
	                String tm = String.valueOf(query.getParameters().get("tm"));
	                condition1 = "delflag = '0' and mj = '0' and isarchive >= 3 and (hkkz is NULL or hkkz <> '1') and NVL(tm,0) like '%"+tm+"%'";
	            }else{
	                condition1 = "delflag = '0' and mj = '0' and isarchive >= 3 and (hkkz is NULL or hkkz <> '1')";
	            }
			}else{
			    //不存在密级（mj）字段，不提供划空功能
                condition1 = "1 = 0";
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
				dtoFileBase.setHkkz(this.valueOf(entity.get("hkkz")));
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
	 * 跳转到角色选择页面
	 * @param request
	 * @param tablename
	 * @param ids
	 * @param flag
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/nextstepView")
    public ModelAndView nextstepView(HttpServletRequest request,
    								@RequestParam(value="tablename") String tablename,
    								@RequestParam(value="ids")String ids,
    								@RequestParam(value="flag")String flag) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/fileControl/openNextstep.vm");
        //获得流程定义id
        String condition = " name = '档案划控流程'";
        List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
        String processId = processes.get(0).get("id");
        
        modelAndView.addObject("processId",processId);
        modelAndView.addObject("tablename", tablename);
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("flag", flag);
        
        return modelAndView;
	 }
	/**
	 * 发起流程
	 */
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/startProcess")
	@ResponseBody
	@LogService(description = "发起流程")
	public ResultModel<String> startProcess(HttpServletRequest request, @RequestBody Map<String, Object> obj) {
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
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
					entity.set("hkkz", "0");
					customArchiveService.update(entity);
			}
		}catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("提交失败");
		} 
		return resultModel;
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
				@SuppressWarnings("unchecked")
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
				
				//添加档案划控表
				FileControl fileControl = new FileControl();
				if(StringUtil.isNotEmpty(fileEntity.get("qzh"))){
					fileControl.setQzh(this.valueOf(fileEntity.get("qzh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("ndh"))){
					fileControl.setNdh(this.valueOf(fileEntity.get("ndh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("wh"))){
					fileControl.setWh(this.valueOf(fileEntity.get("wh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("mlh"))){
					fileControl.setMlh(this.valueOf(fileEntity.get("mlh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("ajh"))){
					fileControl.setAjh(this.valueOf(fileEntity.get("ajh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("ztdw"))){
					fileControl.setZtdw(this.valueOf(fileEntity.get("ztdw")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("ztlx"))){
					fileControl.setZtlx(this.valueOf(fileEntity.get("ztlx")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("ztsl"))){
					fileControl.setZtsl(Integer.parseInt(this.valueOf(fileEntity.get("ztsl"))));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("lb"))){
					fileControl.setLb(this.valueOf(fileEntity.get("lb")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("dh"))){
					fileControl.setDh(this.valueOf(fileEntity.get("dh")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("tm"))){
					fileControl.setTm(this.valueOf(fileEntity.get("tm")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("cwrq"))){
					fileControl.setCwrq(this.valueOf(fileEntity.get("cwrq")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("zrz"))){
					fileControl.setZrz(this.valueOf(fileEntity.get("zrz")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("bgqx"))){
					fileControl.setBgqx(this.valueOf(fileEntity.get("bgqx")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("mj"))){
					fileControl.setMj(this.valueOf(fileEntity.get("mj")));
				}
				if(StringUtil.isNotEmpty(tablename)){
					fileControl.setBm(tablename);
				}
				if(StringUtil.isNotEmpty(fileEntity.get("bz"))){
					fileControl.setBz(this.valueOf(fileEntity.get("bz")));
				}
				if(StringUtil.isNotEmpty(fileEntity.get("uuid"))){
					fileControl.setDaid(this.valueOf(fileEntity.get("uuid")));
				}
				fileControl.setJdsj(new Date());
				fileControlService.save(fileControl);
				//更新档案表划控字段，1为公开档案
				fileEntity.set("hkkz", "1");
				customArchiveService.update(fileEntity);
			}
			
			if("REJECT".equals(handParam.getOperate())){
				//更改为已拒绝状态
				BaseEntityInf entity = customArchiveService.getEntity(tablename, tableid);
				entity.set("hkkz", "2");
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
	 * @Description 待办列表页面
	 * @MethodName toDoTaskView
	 * @author bao
	 * @date 2017年5月7日
	 * @param request
	 * @return
	 * @throws ServiceException ModelAndView
	 */
     @RequestMapping(value = "/controlToDoTaskView")
     public ModelAndView controlToDoTaskView(HttpServletRequest request) throws ServiceException{
         ModelAndView modelAndView = this.createNormalView("/web/fileControl/openToDo.vm");
         
         //获得流程定义id
         String condition = " name = '档案划控流程'";
         List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
         String processId = processes.get(0).get("id");
         
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
							dtoFileFlow.setUuid(this.valueOf(entity.get("uuid")));
							dtoFileFlow.setDh(this.valueOf(entity.get("dh")));
							dtoFileFlow.setBgqx(this.valueOf(entity.get("bgqx")));
							dtoFileFlow.setWh(this.valueOf(entity.get("wh")));
							dtoFileFlow.setZrz(this.valueOf(entity.get("zrz")));
							dtoFileFlow.setTm(this.valueOf(entity.get("tm")));
							dicManagerService.getDictionaryByDno("mj");
							dtoFileFlow.setCwrq(this.valueOf(entity.get("cwrq")));
							dtoFileFlow.setMj(this.valueOf(entity.get("mj")));
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
	 * 获得字典标题值
	 * @param did
	 * @param val
	 * @return
	 */
	private String getDicTitle(String did, String val){
	    List<DictionaryValue> dictionaryValues = dicService.getDicValueList(did);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(val)){
	            return dic.getDvalue();
	        }
	    }
	    return null;
	}
	
}
