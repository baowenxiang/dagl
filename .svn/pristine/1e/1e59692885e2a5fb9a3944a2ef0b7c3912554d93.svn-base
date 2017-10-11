package cn.proem.dagl.web.tools.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.exception.ServiceException;


public interface ToolsService {
	/**
	 * @Description 打包下载
	 * @MethodName batchDownLoad
	 * @author bao
	 * @date 2017年5月11日
	 * @param request
	 * @param resp
	 * @param paths
	 * @param name void
	 */
	public void batchDownLoad(HttpServletRequest request, HttpServletResponse resp,List<CommonFile> commonFiles,String name) throws Exception;

	/**
	 * @Description 根据表名查询字典并导入
	 * @MethodName importFromExcel
	 * @author bao
	 * @date 2017年5月13日
	 * @param fullpath
	 * @param tablename
	 * @throws Exception
	 * @throws ServiceException void
	 */
	public void importFromExcel(String fullpath, String tablename) throws Exception, ServiceException;
	
	/**
	 * @Description 根据表名查询字典并导入
	 * @MethodName importFromExcel
	 * @author bao
	 * @date 2017年5月13日
	 * @param fullpath
	 * @param tablename
	 * @param extraParams
	 * @throws Exception
	 * @throws ServiceException void
	 */
	public void importFromExcel(String fullpath, String tablename,Map<String ,Object> extraParams)  throws Exception, ServiceException;
	
	/**
	 * @Description 自定义表单的导入操作
	 * @MethodName importFileFromExcel
	 * @author bao
	 * @date 2017年5月17日
	 * @param fullpath
	 * @param tablename
	 * @param extraParams
	 * @throws Exception
	 * @throws ServiceException void
	 */
	public void importFileFromExcel(String fullpath, String tablename,Map<String, Object> extraParams) throws Exception, ServiceException;

	List<String> importFileFromExcel(String fullpath, String tablename,
			Map<String, Object> extraParams, List<String> errors)
			throws Exception, ServiceException;
}
