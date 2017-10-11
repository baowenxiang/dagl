package cn.proem.dagl.web.systemSetting.service;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.systemSetting.entity.SystemLog;
/**
 * @ClassName SystemErrorLogService
 * @Description 系统异常日志服务层
 * @author chenxiaofen
 * @date 2017年5月9日
 */
public interface SystemErrorLogService {
	/**
	 * @MethodName saveLog
	 * @Description 保存日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param log
	 * @throws ServiceException
	 */
	public void saveLog(SystemLog log) throws ServiceException;
	/**
	 * @MethodName getErrorDataGrid
	 * @Description 
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	public DataGrid<SystemLog> getErrorDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	/**
	 * @MethodName deleteErrorLog
	 * @Description 逻辑删除系统日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteErrorLog(String id) throws ServiceException;
}
