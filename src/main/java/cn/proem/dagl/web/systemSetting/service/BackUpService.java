package cn.proem.dagl.web.systemSetting.service;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.systemSetting.entity.BackUp;
import cn.proem.suw.web.common.exception.ServiceException;

public interface BackUpService {
	/**
	 * @MethodName getBackUpDataGrid
	 * @Description 获取备份记录
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	public DataGrid<BackUp> getBackUpDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	/**
	 * @MethodName saveBackUp
	 * @Description 备份记录保存
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param backup
	 * @throws ServiceException
	 */
	public void saveBackUp(BackUp backup) throws ServiceException;
	/**
	 * @MethodName deleteBackUp
	 * @Description 删除备份记录
	 * @author chenxiaofen
	 * @date 2017年5月10日
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteBackUp(String id) throws ServiceException;
}
