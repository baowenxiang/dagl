package cn.proem.dagl.web.fileIdentify.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.proem.core.entity.User;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileIdentify.entity.FileIdentify;
import cn.proem.suw.web.common.exception.ServiceException;

public interface DestroyHistroyService {
	/**
	 * @Description 获得销毁的档案列表
	 * @MethodName getDestroyTables
	 * @author bao
	 * @date 2017年5月21日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<FileIdentify>
	 */
	public DataGrid<FileIdentify> getDestroyTables(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	
	/**
	 * @Description 逻辑删除档案鉴定
	 * @MethodName deleteDestroyHistroy
	 * @author bao
	 * @date 2017年5月21日
	 * @param id
	 * @return void
	 */
	public void deleteDestroyHistroy(String id) throws ServiceException;
	
	/**
	 * @Description 撤销增加鉴定内容
	 * @MethodName addIdentifyContent
	 * @author bao
	 * @date 2017年5月21日
	 * @param identifyId
	 * @return
	 * @throws ServiceException String
	 */
	public String addIdentifyContent(User user,String identifyId)throws ServiceException;
	
	
	public List<FileIdentify> getDestroyTables(QueryBuilder queryBuilder);
}
