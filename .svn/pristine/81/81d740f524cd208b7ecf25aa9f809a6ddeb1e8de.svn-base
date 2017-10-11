package cn.proem.dagl.web.temperature.service;

import java.util.List;
import java.util.Map;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.temperature.entity.DWsd;

public interface TemperatureService {
	/**
	 * @MethodName getRecordList
	 * @Description 分页获取温湿度记录表中的数据
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @return
	 */
	public DataGrid<DWsd> getRecordList(QueryBuilder queryBuilder, int nowPage,
			int pageSize);
	/**
	 * @MethodName saveOrupdate
	 * @Description 保存或修改数据
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param dwsd
	 * @throws ServiceException
	 */
	public void saveOrupdate(DWsd dwsd) throws ServiceException;
	/**
	 * @MethodName delete
	 * @Description 删除记录
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param id
	 * @throws ServiceException
	 */
	public void delete(String id) throws ServiceException;
	/**
	 * @MethodName getDataList
	 * @Description 根据时间获取统计报表的数据
	 * @author chenxiaofen
	 * @date 2017年6月6日
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> getDataList(String year,String  month);
	/**
	 * @MethodName getDataByWeek
	 * @Description 根据周获取温湿度记录
	 * @author chenxiaofen
	 * @date 2017年6月6日
	 * @param week
	 * @return
	 */
	public List<Map<String,Object>> getDataByWeek(String week);
	
	/**
	 * 
	 * @Description:导入excel
	 * @author:bao
	 * @time:2017年7月4日 上午9:37:58
	 */
	List<String> importFileFromExcel(String fullpath)throws Exception, ServiceException;
	
	List<DWsd> getTemperatures(List<String> ids);
}
