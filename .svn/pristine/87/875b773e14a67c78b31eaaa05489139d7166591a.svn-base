package cn.proem.dagl.web.fileNum.service;

import java.util.List;

import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.fileNum.entity.FileNumRule;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * @ClassName FileNumService
 * @Description 档号生成规则
 * @author chenxiaofen
 * @date 2017年5月13日
 */
public interface FileNumService {
	/**
	 * @MethodName saveRules
	 * @Description 保存档号生成规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param rule
	 */
	void saveRules(FileNumRule rule) throws ServiceException;
	
	/**
	 * @MethodName getRule
	 * @Description 获取档号生成规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	List<FileNumRule> getRule(String type) throws ServiceException;
	/**
	 * @MethodName deleteRule
	 * @Description 删除档号规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param id
	 * @throws ServiceException
	 */
	void deleteRule(String id) throws ServiceException;
	/**
	 * @MethodName getObj
	 * @Description 
	 * @author chenxiaofen
	 * @date 2017年5月15日
	 * @return
	 * @throws ServiceException
	 */
	BaseEntityInf getObj(String className)throws ServiceException;
	/**
	 * @MethodName getDahByType
	 * @Description 根据档案类型获取档号
	 * @author chenxiaofen
	 * @date 2017年5月16日
	 * @param type
	 * @return
	 */
	String getDahByType(String type)throws ServiceException;
	/**
	 * @MethodName gettableNameByName
	 * @Description 根据表名查数据
	 * @author chenxiaofen
	 * @date 2017年5月19日
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	DTableName gettableNameByName(String name) throws ServiceException;
}
