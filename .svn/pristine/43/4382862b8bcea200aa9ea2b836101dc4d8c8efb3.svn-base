package cn.proem.dagl.web.dataCenter.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.dataCenter.entity.ImportCenter;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 数据中心服务层
 * @author lenovo
 *
 */
public interface DataCenterService {
	/**
	 * @Description 获得导入中心数据集合
	 * @MethodName getImportCenterList
	 * @author bao
	 * @date 2017年5月10日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<ImportCenter>
	 */
	public DataGrid<ImportCenter> getImportCenterList(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	/**
	 * @Description 报错导入中心数据
	 * @MethodName saveImportCenter
	 * @author bao
	 * @date 2017年5月10日
	 * @param importCenter
	 * @return
	 * @throws ServiceException String
	 */
	public String saveImportCenter(ImportCenter importCenter) throws ServiceException;
	
	/**
	 * @Description 根据导入id逻辑删除导入中心数据
	 * @MethodName deleteImportCenter
	 * @author bao
	 * @date 2017年5月10日
	 * @param id
	 * @throws ServiceException void
	 */
	public void deleteImportCenter(String id) throws ServiceException;
	
	
	/**
	 * @Description 根据表名部门数据导出文件返回文件信息
	 * @MethodName exportDefFromTable
	 * @author bao
	 * @date 2017年5月17日
	 * @param tablename
	 * @return
	 * @throws Exception CommonFile
	 */
	public CommonFile exportDefFromTable(List<BaseEntityInf> records,String tablename) throws Exception;
	
	
	/**
	 * @Description 修改资料收集
	 * @MethodName updateZlsj
	 * @author bao
	 * @date 2017年4月22日
	 * @param zlsj
	 * @throws ServiceException void
	 */
	public void updateImportCenter(ImportCenter importCenter) throws ServiceException;
}
