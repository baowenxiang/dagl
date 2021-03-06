package cn.proem.dagl.web.dataCenter.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.entity.DField;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dataCenter.entity.ImportCenter;
import cn.proem.dagl.web.dataCenter.service.DataCenterService;
import cn.proem.dagl.web.flow.service.FlowService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.service.YwgjService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.service.ToolsService;
import cn.proem.dagl.web.upload.service.UploadService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

/**
 * 数据中心控制层
 * @author bao
 *
 */
@Controller
@RequestMapping(value = "/w/dataCenter")
public class DataCenterController extends BaseCtrlModel{
		@Autowired
	    private CommonService commonService;
		@Autowired
		private DataCenterService dataCenterService;
		@Autowired
		private UploadService uploadService;
		@Autowired
		private ToolsService toolsService;
		@Autowired
		private FlowService flowService;
		@Autowired
		private CustomArchiveService customArchiveService;
		@Autowired
		private GeneralDao generalDao;
		@Autowired
		private YwgjService ywgjService;
		/**
		 * @Description 去导入中心界面
		 * @MethodName toImportCenterView
		 * @author bao
		 * @date 2017年5月10日
		 * @param request
		 * @return
		 * @throws ServiceException ModelAndView
		 */
	 	@RequestMapping(value = "/toImportCenterView")
		public ModelAndView toImportCenterView(HttpServletRequest request) throws ServiceException {
			ModelAndView modelAndView = this.createNormalView("/web/dataCenter/importCenter.vm");
			return modelAndView;
		}
	 	
	 	
	 	/**
	 	 * @Description 去增加导入中心页面
	 	 * @MethodName addImportCenterView
	 	 * @author bao
	 	 * @date 2017年6月6日
	 	 * @param request
	 	 * @return
	 	 * @throws ServiceException ModelAndView
	 	 */
	 	@RequestMapping(value = "/addImportCenterView")
		public ModelAndView addImportCenterView(HttpServletRequest request) throws ServiceException {
			ModelAndView modelAndView = this.createNormalView("/web/dataCenter/addImportCenter.vm");
			return modelAndView;
		}
	 	
	 	
	 	/**
	 	 * @Description 去导出中心界面
	 	 * @MethodName toExportCenterView
	 	 * @author bao
	 	 * @date 2017年5月10日
	 	 * @param request
	 	 * @return
	 	 * @throws ServiceException ModelAndView
	 	 */
	 	@RequestMapping(value = "/toExportCenterView")
		public ModelAndView toExportCenterView(HttpServletRequest request) throws ServiceException {
			ModelAndView modelAndView = this.createNormalView("/web/dataCenter/exportCenter.vm");
			return modelAndView;
		}
	 	
	 	/**
	 	 * @Description 获得导入中心数据
	 	 * @MethodName getImportCenterList
	 	 * @author bao
	 	 * @date 2017年5月10日
	 	 * @param request
	 	 * @param dtGridPager
	 	 * @return String
	 	 */
	 	@RequestMapping(value = "/getImportCenterList")
	 	@ResponseBody
	 	@LogService(description = "获得导入中心数据")
	 	public String getImportCenterList(HttpServletRequest request, String dtGridPager){
	 		User user = this.getCurrentUser(request);
			DataGridQuery query = parseToQuery(dtGridPager);
			
			QueryBuilder queryBuilder = query.generateQueryBuilder();
			
			queryBuilder.addCondition(new QueryCondition("delFlag", 0, ConditionType.EQ, FieldType.INTEGER, null));
			
			return JSON.toJSONStringWithDateFormat(
					dataCenterService.getImportCenterList(queryBuilder, query.getNowPage(), query.getPageSize()),
	                "M-d HH:mm",
	                SerializerFeature.WriteDateUseDateFormat,
	                SerializerFeature.DisableCircularReferenceDetect);
	 	}
	 	
	 	/**
	 	 * @Description 上传档案导入模版文件
	 	 * @MethodName uploadFile
	 	 * @author bao
	 	 * @date 2017年5月10日
	 	 * @param request
	 	 * @param attachments
	 	 * @return ResultModel<String>
	 	 */
	 	@RequestMapping(value = "/uploadImportFile")
		@ResponseBody
		@LogService(description = "上传档案导入模板文件")
		public ResultModel<String> uploadFile(HttpServletRequest request,
					@RequestParam("attachment[]") MultipartFile[] attachments,
					@RequestParam("tablename") String tablename,
					@RequestParam("tablenamecn") String tablenamecn){
	 		ResultModel<String> resultModel = new ResultModel<String>();
	 		String importCenterId = "";
	 		if(attachments!=null && attachments.length>0){
	 			try{
		 			for(MultipartFile atta:attachments){
		 				String fileName = atta.getOriginalFilename();
		 				
		 				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						String datePath = simpleDateFormat.format(new Date());
						String uploadPath = Path.UPLOAD_IMPORT_FILE_PATH+datePath+"/";
						
							String uuidName = uploadService.upload(request,atta, uploadPath);
							
							ImportCenter importCenter = new ImportCenter();
							importCenter.setTablenamecn(tablenamecn);
							importCenter.setTablename(tablename);
							importCenter.setUploadpath(uploadPath+uuidName);
							importCenter.setFilename(fileName);
							importCenter.setStatus(0);
							importCenterId = dataCenterService.saveImportCenter(importCenter);
		 			}		
				}catch (ServiceException e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				}catch (Exception e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				}
	 		}
	 		resultModel.setData(importCenterId);
	 		return resultModel;
	 	}
	 	
	 	/**
	 	 * @Description 删除导入中心数据
	 	 * @MethodName deleteImportCenter
	 	 * @author bao
	 	 * @date 2017年5月10日
	 	 * @param request
	 	 * @param id
	 	 * @return ResultModel<String>
	 	 */
	 	@RequestMapping(value = "/deleteImportCenter")
		@ResponseBody
		@LogService(description = "删除导入中心数据")
	 	public ResultModel<String> deleteImportCenter(HttpServletRequest request,String id){
	 		ResultModel<String> resultModel = new ResultModel<String>();
			try {
				dataCenterService.deleteImportCenter(id);
			} catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			} catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("删除导入中心数据失败");
			}
			return resultModel;
	 	}
	 	
	 	
	 	/**
	 	 * @Description 批量导入
	 	 * @MethodName batchImport
	 	 * @author bao
	 	 * @date 2017年5月10日
	 	 * @param request
	 	 * @param ids
	 	 * @return ResultModel<String>
	 	 */
	 	@RequestMapping(value = "/batchImport", method = {RequestMethod.POST})
		@ResponseBody
		@LogService(description = "批量导入")
		public ResultModel<String> batchImport(HttpServletRequest request,@RequestBody String[] ids){
	 		ResultModel<String> resultModel = new ResultModel<String>();
	 		List<String> infos = new ArrayList<String>();
	 		
	 		String errorinfo = "导入失败的原文挂接:%s";
	    	String info = "本次导入原文共计:%d条,成功:%d条,失败: %d条";
	    	
			// 导入成功条数
			Integer successCount = 0;
			// 导入失败条数
			Integer errorCount = 0;
	    	
	 		User user = this.getCurrentUser(request);
	 		
	 		try{
	 			for(String id : ids){
	 				ImportCenter importCenter = commonService.findById(id, ImportCenter.class);
	 				String tablename = importCenter.getTablename();
	 				String uploadpath = importCenter.getUploadpath();
	 				
	 				Map<String,Object> extraParams = new HashedMap<String, Object>();
					extraParams.put(DField.DELFLAG, "0");
					extraParams.put(DField.ISARCHIVE, "3");
					extraParams.put(DField.SFJD, "sfjd_2");
					extraParams.put(DField.NOWCAMP, getDepartment(user));
					extraParams.put(DField.CREATETIME, nowTime());
					extraParams.put(DField.CREATEUSER, user.getId());
					infos = toolsService.importFileFromExcel(this.getFilePath()+uploadpath, tablename, extraParams,new ArrayList<String>());
					
					
					//查询原文挂接表
					List<Ywgj> ywgjs = flowService.getFileBydataId(id);
					for(Ywgj ywgj : ywgjs){
						String prefilename = ywgj.getWjm().substring(0,ywgj.getWjm().lastIndexOf("."));// 张三
						List<BaseEntityInf> entites = customArchiveService.getEntitiesByConditions(tablename, " DH LIKE '"+prefilename+"%'");
						if(entites.size() == 1){
							BaseEntityInf entity = entites.get(0);
							ywgj.setZlsj((String)entity.get("uuid"));
							generalDao.saveOrUpdate(ywgj);
							successCount++;
						}else{
							infos.add(String.format(errorinfo, ywgj.getWjm()));
							errorCount++;
						}
					}
					infos.add(String.format(info, successCount+errorCount,successCount,errorCount));
					
					
					importCenter.setStatus(1);
					dataCenterService.updateImportCenter(importCenter);
	 				
	 			}
	 			
	 		}catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			} catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("档案数据导入失败");
			}
	 		resultModel.setDatas(infos);
	 		return resultModel;
	 	}
	 	
	 	
	    private String getDepartment(User user) throws ServiceException{
	        // 用户部门
	        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
	        if(userdepartment != null){
	            // 部门
	            String department = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
	            return department;
	        }
	        return "";
	    }
	 	
	    /**
	     * 
	     * 
	     * @Method: nowTime 
	     * @Description: 获得当前时间
	     * @return
	     */
	    private String nowTime(){
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	        return df.format(new Date());// new Date()为获取当前系统时间
	    }
	    
	    /**
		 * @Description 修改资料收集(上传和修改操作)
		 * @MethodName uploadFile
		 * @author bao
		 * @date 2017年4月20日
		 * @param request
		 * @param attachments
		 * @return ResultModel<String>
		 */
		@RequestMapping(value = "/uploadFile")
		@ResponseBody
		public ResultModel<String> uploadFile(HttpServletRequest request,
				@RequestParam("attachment[]") MultipartFile[] attachments,
				@RequestParam("dataCollectId") String dataCollectId){
			ResultModel<String> resultModel = new ResultModel<String>();
			
			//解析数据
			
			if(StringUtil.isEmpty(dataCollectId)){
				dataCollectId = UUID.randomUUID().toString();
			}
			List<String> msg = new ArrayList<String>();
			try {
				if (attachments != null) {
					for(int i=0;i<attachments.length;i++){
						MultipartFile atta = attachments[i];
						Ywgj ywgj = new Ywgj();
						if (!atta.isEmpty() && (atta != null && !"".equals(atta.getOriginalFilename()))) {
							String extName = atta.getOriginalFilename().substring(
									atta.getOriginalFilename().lastIndexOf("."));// 获得后缀
							String fileType = atta.getOriginalFilename().substring(
									atta.getOriginalFilename().lastIndexOf(".")+1);// 获得后缀
							String filePath = UUID.randomUUID().toString() + extName;
							String fileName = atta.getOriginalFilename();
							
							msg.add(fileName);
							
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
							
							String path = simpleDateFormat.format(new Date());
							
							String realpath = this.getFilePath() + Path.UPLOAD_PLGJ_FILE_PATH + path +"/" + filePath;
							File fileTo = new File(realpath);
							if (!fileTo.exists()) {
								fileTo.mkdirs();
							}
							atta.transferTo(fileTo);
							
							ywgj.setXsxh(i+1);
							ywgj.setWjdz(Path.UPLOAD_PLGJ_FILE_PATH+path+File.separator+ filePath);
							ywgj.setScrq(new Date());
							ywgj.setScz(getCurrentUser(request).getId());
							ywgj.setWjlx(fileType);
							ywgj.setWjdx(atta.getSize());
							ywgj.setWjm(fileName);
							ywgj.setZlsj(dataCollectId);
							try {
								ywgjService.saveAttachment(ywgj);
							} catch (ServiceException e) {
								resultModel.setSuccess(false);
								resultModel.setMsg(e.getMessage());
							} catch (Exception e) {
								resultModel.setSuccess(false);
								resultModel.setMsg(e.getMessage());
							}
						}
					}
				}
			} catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("附件上传失败");
			}
			resultModel.setMsg("资料"+StringUtils.join(msg.toArray(),",")+"上传成功");
			resultModel.setData(dataCollectId);
			return resultModel;
		}
}
