package cn.proem.suw.web.common.model;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.dagl.web.systemSetting.entity.SystemLog;
//import cn.proem.dagl.web.systemSetting.entity.UserLog;
import cn.proem.dagl.web.systemSetting.service.SystemErrorLogService;
import cn.proem.dagl.web.systemSetting.service.UserLogService;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * @ClassName BaseController
 * @Description controller基类
 * @author Tcc
 * @date 2017年4月17日
 */
public class BaseCtrlModel extends cn.proem.core.controller.BaseController {
	@Autowired
	private SystemErrorLogService systemLogService;
	@Autowired
	private UserLogService userLogService;
//	protected Logger  logger = LoggerFactory.getLogger(this.getClass()); 
	/**
	 * @MethodName 
	 * @Description 系统日志处理
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param msg
	 * @param args
	 * @throws ServiceException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("unused")
	public void saveLog(String msg) throws ServiceException{
		
//		logger.error(msg);
		SystemLog log = new SystemLog();
		log.setContent(msg);
		log.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		log.setType("error");
		systemLogService.saveLog(log);
	}
	/**
	 * @MethodName saveUserLog
	 * @Description 用户日志处理
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param msg
	 * @throws ServiceException
	 */
//	@SuppressWarnings("unused")
//	public void saveUserLog(UserLog log) throws ServiceException{
//		log.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		userLogService.saveLog(log);
//	}
	/*==========================================路径的获取============================================*/
	/**
	 * @Title getProjectPath
	 * @Description 获取项目所在路径
	 * @author Pan Jilong
	 * @date 2017年1月5日
	 * @return
	 */
	public String getProjectPath() {
    	String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
        String tempdir;
        String classPath[] = classesPath.split("webapps");
        tempdir = classPath[0];
        if (!"/".equals(tempdir.substring(tempdir.length()))) {
            tempdir += File.separator;
        }
        return tempdir;
    }
	/**
	 * @Title getFilePath
	 * @Description 文件资源所在路径
	 * @author Pan Jilong
	 * @date 2017年1月9日
	 * @return
	 */
	public String getFilePath() {
		String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
        String tempdir;
        String classPath[] = classesPath.split("webapps");
        tempdir = classPath[0];
        tempdir += "webapps/";
        tempdir += File.separator;
        return tempdir;
	}
	
	/*==========================================后台页面的布局模板============================================*/
	/**
	 * @Title createWrapperLayoutView
	 * @Description 整体布局模板, 一般只用于index的整个框架布局
	 * @author Pan Jilong
	 * @date 2016年12月29日
	 * @param path
	 * @return
	 */
	protected ModelAndView createWrapperView(String path) {
		return createWrapperView(path, null);
	}
	/**
	 * @Title createLayoutView
	 * @Description 一张空白的普通页面, 用于主体内容设置
	 * @author Pan Jilong
	 * @date 2016年12月29日
	 * @param path
	 * @return
	 */
	protected ModelAndView createNormalView(String path) {
		return createNormalView(path, null);
	}
	/**
	 * @Title createFixedView
	 * @Description 带有操作面板的固定布局模板, 用于主体内容设置
	 * @author Pan Jilong
	 * @date 2016年12月29日
	 * @param path
	 * @return
	 */
	protected ModelAndView createFixedView(String path) {
		return createFixedView(path, null);
	}
	
	protected ModelAndView createWrapperView(String path, String layout) {
		ModelAndView view = new ModelAndView();
		if (layout == null){
			view.setViewName("web/layout/layout_wrapper");
		}else{
			view.setViewName(layout);
		}
		view.addObject("contentPath", path);
		return view;
	}
	protected ModelAndView createNormalView(String path, String layout) {
		ModelAndView view = new ModelAndView();
		if (layout == null){
			view.setViewName("web/layout/layout_normal");
		}else{
			view.setViewName(layout);
		}
		view.addObject("contentPath", path);
		return view;
	}
	protected ModelAndView createFixedView(String path, String layout) {
		ModelAndView view = new ModelAndView();
		if (layout == null){
			view.setViewName("web/layout/layout_fixed");
		}else{
			view.setViewName(layout);
		}
		view.addObject("contentPath", path);
		return view;
	}
	
}
	
	
