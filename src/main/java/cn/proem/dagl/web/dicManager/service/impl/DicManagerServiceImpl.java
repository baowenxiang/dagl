package cn.proem.dagl.web.dicManager.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.dicManager.entity.Dictionary;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;


@Service
public class DicManagerServiceImpl implements DicManagerService {
	
	@Autowired
	private GeneralDao generalDao;
	
	private Map<String, List<DictionaryValue>> dicVals = new HashMap<String, List<DictionaryValue>>();
	/**
	 * 获取字典项列表
	 */
	@Override
	public DataGrid<Dictionary> getDictionaryDateGrid(
			QueryBuilder queryBuilder, int nowPage, int pageSize) {
		
		DataGrid<Dictionary> dataGrid = new DataGrid<Dictionary>(nowPage,pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(Dictionary.class, queryBuilder));
		
		List<Dictionary> list = new ArrayList<Dictionary>();
		list = generalDao.queryByCriteria(Dictionary.class, queryBuilder, null, dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(list);
		return dataGrid;
		
	}
	
	/**
	 * 保存或更新字典项
	 */
	@Override
	@Transactional
	@LogService(description="保存或更新字典项")
	public void saveOrUpdateDictionary(Dictionary dictionary)
			throws ServiceException {
	    if(dicVals.get(dictionary.getDno()) != null){
	        dicVals.remove(dictionary.getDno());
	    }
		if (StringUtil.isEmpty(dictionary.getId())) {
			generalDao.save(dictionary);
		}else{
			generalDao.update(dictionary);
		}
		
	}
	
	/**
	 * 根据id逻辑删除字典项
	 */
	/*@Override
	@Transactional
	@LogService(description="逻辑删除字典项")
	public void deleteDictionaryById(String id) {
		// TODO Auto-generated method stub
		Dictionary dictionary = generalDao.findById(id, Dictionary.class);
		if(dicVals.get(dictionary.getDno()) != null){
		    dicVals.remove(dictionary.getDno());   
		}
		dictionary.setDelFlag(1);
		generalDao.update(dictionary);
	}*/
	@Override
	@Transactional
	@LogService(description="物理删除字典项")
	public void deleteDictionaryById(String id) {
		// TODO Auto-generated method stub
		Dictionary dictionary = generalDao.findById(id, Dictionary.class);
		if(dicVals.get(dictionary.getDno()) != null){
		    dicVals.remove(dictionary.getDno());   
		}
		
		generalDao.delete(dictionary);
		
	}
	
	
	@Override
	public Dictionary getDictionaryByDno(String dno) {
		Dictionary dictionary = generalDao.findUniqueByProperty("dno", dno, Dictionary.class);
		return dictionary;
	}


	/**
	 * 获取字典值列表
	 */
	@Override
	public DataGrid<DictionaryValue> getDictionaryValueDateGrid(
			QueryBuilder queryBuilder, int nowPage, int pageSize) {
		
		DataGrid<DictionaryValue> dataGrid = new DataGrid<DictionaryValue>(nowPage,pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(DictionaryValue.class, queryBuilder));
		
		List<DictionaryValue> list = new ArrayList<DictionaryValue>();
		list = generalDao.queryByCriteria(DictionaryValue.class, queryBuilder, null, dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(this.getSorted(list));
		return dataGrid;
		
	}
	/**
	 *保存或更新字典值 
	 */
	@Override
	@Transactional
	@LogService(description="保存或更新字典值")
	public void saveOrUpdateDicValue(DictionaryValue dicValue)
			throws ServiceException {
	    String dno = dicValue.getDictionary().getDno();
	    if(dicVals.get(dno) != null){
	        dicVals.remove(dno);
	    }
		if (StringUtil.isEmpty(dicValue.getId())) {
			generalDao.save(dicValue);
		}else{
			generalDao.update(dicValue);
		}
		
	}
	/*
	@Override
	@Transactional
	@LogService(description="逻辑删除字典值")
	public void deleteDicValueById(String id) throws ServiceException {
		DictionaryValue dicValue = generalDao.findById(id, DictionaryValue.class);
		String dno = dicValue.getDictionary().getDno();
		if(dicVals.get(dno) != null){
		    dicVals.remove(dno);
		}
		dicValue.setDelFlag(1);
		generalDao.update(dicValue);
	}
	*/
	
	@Override
	@Transactional
	@LogService(description="物理删除字典值")
	public void deleteDicValueById(String id) throws ServiceException {
		DictionaryValue dicValue = generalDao.findById(id, DictionaryValue.class);
		String dno = dicValue.getDictionary().getDno();
		if(dicVals.get(dno) != null){
		    dicVals.remove(dno);
		}
		generalDao.delete(dicValue);
	}
	
	
	/**
	 * 获取字典值列表
	 */
	@Override
	public List<DictionaryValue> getDicValueList(String dno) {
	    if(dicVals.get(dno) != null){
	        return dicVals.get(dno);
	    }
		List<DictionaryValue> dicValueList= new ArrayList<DictionaryValue>();
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("dno",dno,ConditionType.EQ,FieldType.STRING, "dictionary"));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.valueOf(0),ConditionType.EQ,FieldType.INTEGER, null));
		dicValueList = generalDao.queryByCriteria(DictionaryValue.class, queryBuilder, new Order[]{new Order("xsxh", OrderType.ASC)}, 0, -1);
		return getSorted(dicValueList);
	}

	@Override
	public DictionaryValue getDicValByDvno(String dvno) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("dvno",dvno,ConditionType.EQ,FieldType.STRING,null));
//		generalDao.queryByCriteria(FileNumRule.class, queryBuilder, new Order[]{new Order("num",OrderType.ASC)}, 0, -1);
		List<DictionaryValue> tlist = generalDao.queryByCriteria(DictionaryValue.class, queryBuilder, null, 0, -1);
		if(tlist != null && tlist.size() > 0) {
			return tlist.get(0);
		}
		return null;
	}
	
	
	public List<DictionaryValue> getSorted(List<DictionaryValue> dicts){
	    Collections.sort(dicts, new Comparator<DictionaryValue>(){  
	        
            /*  
             * int compare(DictionaryValue o1, DictionaryValue o2) 返回一个基本类型的整型，  
             * 返回负数表示：o1小于o2，  
             * 返回0 表示：o1和o2相等，  
             * 返回正数表示：o1大于o2。  
             */  
            public int compare(DictionaryValue o1, DictionaryValue o2) {  
              
                // 按照字典的显示序号进行显示
                if(o1.getXsxh() == null || o2.getXsxh() == null){
                    return 1;
                }
                try {
                    if(Integer.parseInt(o1.getXsxh()) > Integer.parseInt(o2.getXsxh())){  
                        return 1;
                    }  
                    if(Integer.parseInt(o1.getXsxh()) == Integer.parseInt(o2.getXsxh())){  
                        return 0;
                    }
                    return -1; 
                } catch (Exception e){
                    e.printStackTrace();
                    return 1;
                }
            }  
        });
	    
	    return dicts;
	}

	@Override
	public DictionaryValue getDicValByDnoAndDvno(String dno, String dvno) {
		DictionaryValue dictionaryValue = null;
		
		List<DictionaryValue> dictionaryValues = this.getDicValueList(dno);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(dvno)){
	        	dictionaryValue = dic;
	        	break;
	        }
	    }
		return dictionaryValue;
	}

}
