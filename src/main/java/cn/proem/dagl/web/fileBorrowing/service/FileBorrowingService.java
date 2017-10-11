package cn.proem.dagl.web.fileBorrowing.service;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileBorrowing.entity.FileBorrowing;
import cn.proem.suw.web.common.exception.ServiceException;

public interface FileBorrowingService {
	/**
	 * 获取借阅信息（）
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	public DataGrid<FileBorrowing> getFileBorrowingDataGrid(QueryBuilder queryBuilder,int nowPage,int pageSize);
	/**
	 * 借阅归还
	 * @param id
	 */
	public void fileReturn(String id,String jyxg) throws ServiceException;
	
	public void saveJyxx(FileBorrowing fb) throws ServiceException;
	
	public void deleteJyxx(String id) throws ServiceException;
	
	

}
