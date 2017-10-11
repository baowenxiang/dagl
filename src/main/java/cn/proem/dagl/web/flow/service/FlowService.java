package cn.proem.dagl.web.flow.service;

import java.util.List;

import cn.proem.bpm.entity.ProcTask;
import cn.proem.core.entity.User;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.tools.util.CommonFile;

public interface FlowService {
	/**
	 * @Description 根据资料id获得原文
	 * @MethodName getFileBydataId
	 * @author bao
	 * @date 2017年5月9日
	 * @param id
	 * @return List<Ywgj>
	 */
	public List<Ywgj> getFileBydataId(String id);
	
	/**
	 * @Description 根据资料id获得CommonFile
	 * @MethodName getFilesByDataId
	 * @author bao
	 * @date 2017年5月11日
	 * @param dataId
	 * @return List<CommonFile>
	 */
	public List<CommonFile> getFilesByDataId(String dataId);
	
	
	public DataGrid<User> getNextHandlerList(QueryBuilder queryBuilder, String processId, User user, int nowPage, int pageSize);
	
	public DataGrid<User> getNextHandlerList(QueryBuilder queryBuilder,
			String processId, String businessId, User user, String operate, int nowpage,
			int pageSize);
	
	/**
	 * @Description:
	 * @author:bao
	 * @time:2017年7月17日 上午10:22:56
	 */
	public List<ProcTask> checkFlowState(String businessId);
}
