package cn.proem.dagl.web.systemSetting.service;

import cn.proem.core.entity.log.OperationLog;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
//import cn.proem.dagl.web.systemSetting.entity.UserLog;
import cn.proem.suw.web.common.exception.ServiceException;
/**
 * @ClassName UserLogService
 * @Description 用户日志服务层
 * @author chenxiaofen
 * @date 2017年5月9日
 */
public interface UserLogService {
	/**
	 * @MethodName saveLog
	 * @Description 保存日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param log
	 * @throws ServiceException
	 */
//	public void saveLog(UserLog log) throws ServiceException;
	/**
	 * @MethodName getUserLogDataGrid
	 * @Description 根据id获取日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	public DataGrid<OperationLog> getUserLogDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	/**
	 * @MethodName deleteUserLog
	 * @Description 逻辑删除用户日志
	 * @author chenxiaofen
	 * @date 2017年5月9日
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteUserLog(String id) throws ServiceException;
}
