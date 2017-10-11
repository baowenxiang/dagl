package cn.proem.dagl.web.systemSetting.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.entity.log.OperationLog;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.core.util.BeanUtils;
import cn.proem.dagl.web.systemSetting.entity.BackUp;
import cn.proem.dagl.web.systemSetting.entity.SkinSetting;
import cn.proem.dagl.web.systemSetting.entity.SystemLog;
import cn.proem.dagl.web.systemSetting.entity.ZipUtil;
import cn.proem.dagl.web.systemSetting.service.BackUpService;
import cn.proem.dagl.web.systemSetting.service.SettingService;
import cn.proem.dagl.web.systemSetting.service.SystemErrorLogService;
import cn.proem.dagl.web.systemSetting.service.UserLogService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.webservice.model.ResponseModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * @ClassName SettingController
 * @Description 系统设置控制层
 * @author chenxiaofen
 * @date 2017年5月5日
 */
@Controller
@RequestMapping(value = "/w/setting")
public class SettingController extends BaseCtrlModel {
	@Autowired
	private SettingService settingService;
	@Autowired
	private SystemErrorLogService systemErrorLogService;
	@Autowired
	private UserLogService userLogService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private BackUpService backUpService;
	/**
	 * @MethodName toSettingPage
	 * @Description 跳转到主题设置页面
	 * @author chenxiaofen
	 * @date 2017年5月5日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/toSettingPage")
	public ModelAndView toSettingPage(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/setting/setting.vm");
		return modelAndView;
	}
	/**
	 * @MethodName saveSkinMsg
	 * @Description 保存用户的个性化设置
	 * @author chenxiaofen
	 * @date 2017年5月5日
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws  
	 */
	@RequestMapping(value = "/saveSettingMsg")
	@ResponseBody
	@LogService(description = "保存系统设置")
    public ResponseModel saveSettingMsg(HttpServletRequest request,
    		@RequestBody Map<String, Object> obj) throws ServiceException {
		ResponseModel responseModal = new ResponseModel();
		User user = this.getCurrentUser(request);
		
		 SkinSetting setting = BeanUtils.cloneObject(SkinSetting.class, obj.get("setting"));
		
		setting.setUserId(user.getId());
		settingService.saveSkin(setting);
		responseModal.setSuccess(true);
        return responseModal;
    }
	@RequestMapping(value = "/saveSystemPic")
	@ResponseBody
	@LogService(description = "保存或修改系统背景图和首页Header图片")
    public String saveSystemPic(HttpServletRequest request,
    		@RequestParam(value="bgAttachment",required = false) MultipartFile bgAttachment,
    		@RequestParam(value="headerAttachment",required = false) MultipartFile headerAttachment,
    		@RequestParam(value="skinAttachment",required = false) MultipartFile skinAttachment
    		) throws ServiceException {
		User user = this.getCurrentUser(request);
		
		SkinSetting setting = settingService.getSettingMsg();
		String path = "";
		if (bgAttachment != null) {
			String realpath = this.upload(bgAttachment);
			setting.setBgPicPath(realpath);
			path = realpath;
		}
		if (headerAttachment != null) {
			String realpath = this.upload(headerAttachment);
			setting.setHeaderPicPath(realpath);
			path += ",";
			path += realpath;
		}
		String skinPath = "";
		//皮肤图片
		if (skinAttachment != null) {
			String realpath = this.upload(skinAttachment);
			setting.setSkinPicPath(realpath);
			skinPath = realpath;
		}
		setting.setUserId(user.getId());
		settingService.saveSkin(setting);
		if(bgAttachment != null || headerAttachment != null) {
			return path;
		}
		if (skinAttachment != null) {
			return skinPath;
		}
//		responseModal.setSuccess(true);
        return null;
    }
	public String upload(MultipartFile attachment){
		if (!attachment.isEmpty() && (attachment != null && !"".equals(attachment.getOriginalFilename()))) {
			String extName = attachment.getOriginalFilename().substring(
					attachment.getOriginalFilename().lastIndexOf("."));// 获得后缀
			String filePath = UUID.randomUUID().toString() + extName;
			String realpath = Path.UPLOAD_BGPIC_PATH + File.separator + "/" + filePath;
			File fileTo = new File(this.getFilePath() + realpath);
			if (!fileTo.exists()) {
				fileTo.mkdirs();
			}
			try {
				attachment.transferTo(fileTo);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return realpath;
		}
		return null;
	}
	/**
	 * @MethodName getSkinMsg
	 * @Description 获取皮肤设置(目测闲置了~~)
	 * @author chenxiaofen
	 * @date 2017年5月8日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
//	@ResponseBody
//    @RequestMapping(value = "/getSkinMsg")
//    public ResultModel<SkinSetting> getSkinMsg(HttpServletRequest request) throws ServiceException {
//		ResultModel<SkinSetting> resultModal = new ResultModel<SkinSetting>();
//		resultModal.setData(settingService.getSettingMsg());
//        return resultModal;
//    }
	/**
	 * @MethodName getSettingMsg
	 * @Description 获取默认的系统设置
	 * @author chenxiaofen
	 * @date 2017年5月12日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
    @RequestMapping(value = "/getSettingMsg")
	@LogService(description = "获取当前系统设置")
    public ResultModel<SkinSetting> getSettingMsg(HttpServletRequest request) throws ServiceException {
		ResultModel<SkinSetting> resultModal = new ResultModel<SkinSetting>();
		SkinSetting skinSetting = settingService.getSettingMsg();
		if(StringUtil.isNotEmpty(skinSetting)) {
			if(StringUtil.isEmpty(skinSetting.getPlateLgName())) {
				String sysName = ConfigReader.readSystemName();
				skinSetting.setPlateLgName(sysName);
				skinSetting.setPlateMiniName(sysName.substring(0, 1));
			}
		}
		resultModal.setData(skinSetting);
        return resultModal;
    }
	/**
	 * @MethodName systemLog
	 * @Description 跳转系统日志页面
	 * @author chenxiaofen
	 * @date 2017年5月8日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
    @RequestMapping(value = "/systemLog")
	public ModelAndView getSystemLogList(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/setting/systemLog.vm");
		return modelAndView;
	}
	/**
	 * @MethodName getSystemLogList
	 * @Description 获取系统日志列表
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSystemLogList")
	@ResponseBody
	@LogService(description = "获取系统日志列表")
	public String getSystemLogList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		
		return JSON.toJSONStringWithDateFormat(systemErrorLogService.getErrorDataGrid(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * @MethodName getErrorLogInfoById
	 * @Description 获取日志详情
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getErrorLogInfoById")
	@ResponseBody
	@LogService(description = "查看系统日志详情")
	public ResultModel<SystemLog> getErrorLogInfoById(HttpServletRequest request,@RequestParam("id")String id){
		ResultModel<SystemLog> resultModel = new ResultModel<SystemLog>();
		
		SystemLog log = commonService.findById(id, SystemLog.class);
		
		resultModel.setData(log);
		return resultModel;
	}
	/**
	 * @MethodName deleteErrorLogById
	 * @Description 删除系统日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteErrorLogById")
	@ResponseBody
	@LogService(description = "删除系统日志")
	public ResultModel<String> deleteErrorLogById(HttpServletRequest request,String id) throws ServiceException{
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
			systemErrorLogService.deleteErrorLog(id);
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
			this.saveLog(e.getClass().toString());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除日志失败");
			this.saveLog(e.getClass().toString());
		}
		return resultModel;
	}
	/**
	 * @MethodName getUserLogList
	 * @Description 跳转用户日志页面
	 * @author chenxiaofen
	 * @date 2017年5月8日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
    @RequestMapping(value = "/userLog")
	public ModelAndView getUserLogList(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/setting/userLog.vm");
		return modelAndView;
	}
	/**
	 * @MethodName getUserLogList
	 * @Description 获取用户日志列表
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserLogList")
	@ResponseBody
	@LogService(description = "获取用户操作日志列表")
	public String getUserLogList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		
		return JSON.toJSONStringWithDateFormat(userLogService.getUserLogDataGrid(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * @MethodName getUserLogInfoById
	 * @Description 获取日志详情
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUserLogInfoById")
	@ResponseBody
	@LogService(description = "获取用户操作日志详情")
	public ResultModel<OperationLog> getUserLogInfoById(HttpServletRequest request,@RequestParam("id")String id){
		ResultModel<OperationLog> resultModel = new ResultModel<OperationLog>();
		
		OperationLog log = commonService.findById(id, OperationLog.class);
		
		resultModel.setData(log);
		return resultModel;
	}
	/**
	 * @MethodName deleteUserLogById 
	 * @Description 删除日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteUserLogById")
	@ResponseBody
	@LogService(description = "删除用户操作日志")
	public ResultModel<String> deleteUserLogById(HttpServletRequest request,String id) throws ServiceException{
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
			userLogService.deleteUserLog(id);
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
			this.saveLog(e.getClass().toString());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除日志失败");
			this.saveLog(e.getClass().toString());
		}
		return resultModel;
	}
	/**
	 * @MethodName toBackupPage
	 * @Description 跳转到数据库备份页面
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/toBackupPage")
	public ModelAndView toBackupPage(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/setting/backup.vm");
		return modelAndView;
	}
	/**
	 * @MethodName getBackUpList
	 * @Description 获取备份列表
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBackUpList")
	@ResponseBody
	@LogService(description = "获取数据库备份列表")
	public String getBackUpList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		
		return JSON.toJSONStringWithDateFormat(backUpService.getBackUpDataGrid(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * @MethodName deleteBackupRecordById 
	 * @Description 删除备份记录
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteBackupRecordById")
	@ResponseBody
	@LogService(description = "删除数据库备份记录")
	public ResultModel<String> deleteBackupRecordById(HttpServletRequest request,String id) throws ServiceException{
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
			backUpService.deleteBackUp(id);
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
			this.saveLog(e.getClass().toString());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除日志失败");
			this.saveLog(e.getClass().toString());
		}
		return resultModel;
	}
	/**
	 * @MethodName getBackup
	 * @Description 点击备份按钮进行数据库备份
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param request
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/backup")
	@ResponseBody
	@LogService(description = "手动进行数据库备份")
	public ResultModel<String> getBackup(HttpServletRequest request,HttpServletResponse response,String fileName,String content) {
		ResultModel<String> resultModal = new ResultModel<String>();
//		backup(response,fileName);
		String path = backupTimer(fileName);
		BackUp backup = new BackUp();
		backup.setFileName(fileName + ".dmp");
		backup.setUserName(this.getCurrentUser(request).getName());
		backup.setPath(path);
		backup.setContent(content);
		try {
			backUpService.saveBackUp(backup);
			resultModal.setSuccess(true);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultModal.setSuccess(false);
		}
		return resultModal;
	}
	public static String backupTimer(String name) {
		String path = "";
        try {
			
            Runtime rt = Runtime.getRuntime();
            
            //读取config.properties文件，获取ip以及数据库用户名密码
            String ip = ConfigReader.readIP();
            String userName = ConfigReader.readUserName();
            String password = ConfigReader.readPassword();
            String severName = ConfigReader.readSeverName();
            
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //调用Oracle的命令
            Process child = rt
    				.exec("exp " + userName + "/" + password + "@" + ip + "/" + severName + " file=" + Path.BACKUP_PATH + name + time + ".dmp");
            path = Path.BACKUP_PATH + name + time + ".dmp";
            //调用Oracle安装目录的命令(本地)
//            Process child = rt
//    				.exec("exp " + "DROA" + "/" + "123456" + "@" + "orcl" + " file=" + Path.BACKUP_PATH + "/" + time + ".dmp");
            final InputStream is1 = child.getInputStream();  
            new Thread(new Runnable() {  
                public void run() {  
                    BufferedReader br = new BufferedReader(new InputStreamReader(is1));  
                    String info;  
                    try {  
                        while ((info=br.readLine()) != null){  
                        System.out.println("info: "+info);   
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start(); // 启动单独的线程来清空process.getInputStream()的缓冲区  
            InputStream is2 = child.getErrorStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));  
            // 保存输出结果  
            StringBuilder buf = new StringBuilder();  
            String line = null;  
            int i=0;  
            while ((line = br2.readLine()) != null){  
	            // 循环等待ffmpeg进程结束  
            	System.out.println("info: " +line);  
	            buf.append(line);  
            }  
            try {  
            	if(buf.toString().contains("ORA-")&&buf.toString().contains("EXP-")){  
            		System.err.println("备份失败！");  
            		child.destroy();  
            	}else{  
            		i=child.waitFor();   
            		System.out.println("over status: "+i);  
            	}  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("备份结束...");
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return path;
    }
	/**
	 * @MethodName fileBackUp
	 * @Description uploadFile文件夹自动备份接口
	 * @author chenxiaofen
	 * @date 2017年5月16日
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/fileBackUp")
	@ResponseBody
	@LogService(description = "文件夹备份")
	public ResultModel<String> fileBackUp() throws ServiceException{
		ResultModel<String> resultModal = new ResultModel<String>();
		
		String time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		File file = new File(Path.FILE_BACKUP_PATH);
        if(!file.exists()){
        	file.mkdir();
        }
		ZipUtil zca = new ZipUtil(Path.FILE_BACKUP_PATH + time + ".zip");
		zca.compressExe(this.getFilePath() + "\\uploadFile");
//        zca.compressExe("E:\\java\\tts9\\dagl-apache-tomcat-8.0.14\\webapps\\uploadFile");
        
		resultModal.setSuccess(true);
		return resultModal;
	}
	
}
