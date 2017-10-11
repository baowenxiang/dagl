package cn.proem.dagl.web.dicManager.service;

import java.util.List;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.dicManager.entity.Dictionary;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.suw.web.common.exception.ServiceException;


public interface DicManagerService {
	
	public DataGrid<Dictionary> getDictionaryDateGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	public void saveOrUpdateDictionary(Dictionary dictionary) throws ServiceException;
	
	public void deleteDictionaryById(String id) throws ServiceException;
	
	public Dictionary getDictionaryByDno(String dno);
	
	public DataGrid<DictionaryValue> getDictionaryValueDateGrid(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	public void saveOrUpdateDicValue(DictionaryValue dicValue) throws ServiceException;
	
	public void deleteDicValueById(String id) throws ServiceException;
	
	public List<DictionaryValue> getDicValueList(String dno);
	
	/**
	 * @MethodName getDicValByDvno
	 * @Description 根据内容编号查询该项Id
	 * @author chenxiaofen
	 * @date 2017年6月8日
	 * @param dvno
	 * @return
	 */
	public DictionaryValue getDicValByDvno(String dvno);
	
	/**
	 * 
	 * @Description:通过字典项和字典值获得字典值对象
	 * @author:bao
	 * @time:2017年7月13日 下午2:50:37
	 */
	public DictionaryValue getDicValByDnoAndDvno(String dno,String dvno);
}
