package cn.proem.dagl.web.fileControl.service;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileControl.entity.FileControl;
import cn.proem.suw.web.common.exception.ServiceException;

public interface FileControlService {
	
	public void save(FileControl fileControl);
	
	public DataGrid<FileControl> getOpenedFileDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	public void deleteOpenedFile(String id) throws ServiceException;
	
	public boolean hasField(String tablename, String fieldname) throws ServiceException;

}
