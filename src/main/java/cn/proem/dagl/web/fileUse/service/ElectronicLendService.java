package cn.proem.dagl.web.fileUse.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileUse.dto.DtoLendBase;
import cn.proem.dagl.web.fileUse.entity.ElectronicLend;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;

public interface ElectronicLendService {
	
	/**
	 * @Description 获得电子借阅集合
	 * @MethodName getElectronicLendList
	 * @author bao
	 * @date 2017年5月14日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<ElectronicLend>
	 */
	public DataGrid<DtoLendBase> getElectronicLendList(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	
	
	/**
	 * @Description 保存或者更新电子借阅
	 * @MethodName saveOrUpdate
	 * @author bao
	 * @date 2017年5月15日
	 * @param electronicLend
	 * @throws ServiceException void
	 */
	public void saveOrUpdate(ElectronicLend electronicLend) throws ServiceException;
	
	/**
	 * @Description 删除电子借阅
	 * @MethodName delete
	 * @author bao
	 * @date 2017年5月23日
	 * @param id
	 * @throws ServiceException void
	 */
	public void delete(String id) throws ServiceException;
	
	/**
	 * @Description:生成excel记录文件
	 * @author:bao
	 * @time:2017年7月4日 下午2:24:53
	 */
	public CommonFile exportDefFromTable(List<String> idList) throws Exception;
	
	/**
	 * 
	 * @Description:查询档案记录
	 * @author:bao
	 * @time:2017年7月5日 上午9:28:35
	 */
	public List<String> getElectronicLendIds(QueryBuilder queryBuilder);
	
}
