package cn.proem.dagl.web.fileTransfer.service;

import java.util.List;
import java.util.Map;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileTransfer.entity.TransferRecorder;
import cn.proem.suw.web.common.exception.ServiceException;

public interface FileTransferService {
	
	public void save(TransferRecorder transferRecorder) throws ServiceException;
	
	public DataGrid<TransferRecorder> getTransferedFileDataGrid(QueryBuilder queryBuilder, int nowPage, int pageSize);
	
	//public DataGrid<Map<String,Object>> getTransferedInFileDataGrid(List<String> uuidList,int nowPage, int pageSize);
	
	/**
	 * 
	 * @Description:已结束的记录
	 * @author:bao
	 * @time:2017年6月27日 上午11:12:54
	 */
	public DataGrid<Map<String,Object>> getTransferedFileDataGrid(String tablename,String condition, int nowPage, int pageSize);
	
	/**
	 * 
	 * @Description:获得所有的记录
	 * @author:bao
	 * @time:2017年6月27日 上午11:13:33
	 */
	public DataGrid<Map<String,Object>> getAllTransferedFileDataGrid(String tablename, int nowPage, int pageSize);
	
	public List<TransferRecorder> getTransferRecords(String daid);
	
	/**
	 * 
	 * @Description:获得移交记录
	 * @author:bao
	 * @time:2017年7月6日 下午3:56:23
	 */
	public List<Map<String,Object>> getAllTransferedFileDataGrid(String tablename,String condition);
}
