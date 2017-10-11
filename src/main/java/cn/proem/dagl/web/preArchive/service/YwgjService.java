package cn.proem.dagl.web.preArchive.service;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.preArchive.dto.DtoZlsj;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 资料收集服务层
 * @author lenovo
 *
 */
public interface YwgjService {
	
	
	
	/**
	 * @Description 
	 * @MethodName saveAttachment
	 * @author bao
	 * @date 2017年4月20日
	 * @param ywgj
	 * @throws ServiceException void
	 */
	public void saveAttachment(Ywgj ywgj) throws ServiceException;
	
	
	/**
	 * @MethodName deleteAttachment
	 * @Description 逻辑删除附件
	 * @author Pan Jilong
	 * @date 2017年2月28日
	 * @param id
	 * @throws ServiceException 
	 */
	public void removeAttachment(String id) throws ServiceException;
}
