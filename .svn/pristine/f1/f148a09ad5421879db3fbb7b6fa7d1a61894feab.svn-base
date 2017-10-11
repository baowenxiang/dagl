package cn.proem.dagl.web.preArchive.service;

import java.util.List;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.preArchive.dto.DtoZlsj;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 资料收集服务层
 * @author lenovo
 *
 */
public interface ZlsjService {
	/**
	 * @Description 获得资料收集的分页信息
	 * @MethodName getZlsjDataGrid
	 * @author bao
	 * @date 2017年4月20日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<Zlsj>
	 */
	public DataGrid<Zlsj> getZlsjDataGrid(QueryBuilder queryBuilder, int nowPage,int pageSize);
	
	
	public DataGrid<Ywgj> getYwgjDataGrid(QueryBuilder queryBuilder, int nowPage,int pageSize);
	
	
	/**
	 * @Description 保存资料收集
	 * @MethodName saveAttachment
	 * @author bao
	 * @date 2017年4月20日
	 * @param Zlsj
	 * @return
	 * @throws ServiceException String
	 */
	public String saveAttachment(Zlsj zlsj) throws ServiceException;
	
	/**
	 * @Description	逻辑删除资料下的原文
	 * @MethodName deleteYwgz
	 * @author bao
	 * @date 2017年4月21日
	 * @param zlsjId
	 * @return
	 * @throws ServiceException String
	 */
	public Boolean deleteYwgz(String zlsjId);
	
	/**
	 * @Description 逻辑删除资料
	 * @MethodName deleteZlsj
	 * @author bao
	 * @date 2017年4月21日
	 * @param zlsjId
	 * @return
	 * @throws ServiceException String
	 */
	public void deleteZlsj(String zlsjId) throws ServiceException;
	
	/**
	 * @Description 通过资料收集id获得原文集合
	 * @MethodName getFilesByZlsjId
	 * @author bao
	 * @date 2017年4月21日
	 * @param id
	 * @return List<Ywgj>
	 */
	public List<Ywgj> getFilesByZlsjId(String id);
	
	/**
	 * @Description 修改资料收集
	 * @MethodName updateZlsj
	 * @author bao
	 * @date 2017年4月22日
	 * @param zlsj
	 * @throws ServiceException void
	 */
	public void updateZlsj(Zlsj zlsj) throws ServiceException;
}
