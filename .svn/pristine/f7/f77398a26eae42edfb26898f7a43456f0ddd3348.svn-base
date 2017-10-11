package cn.proem.dagl.web.fileNum.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.fileNum.entity.FileNumRule;
import cn.proem.dagl.web.fileNum.service.FileNumService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;
@Service
public class FileNumServiceImpl implements FileNumService {
	@Autowired
	private GeneralDao generalDao;
	@Override
	@Transactional
	@LogService(description = "保存档号规则")
	public void saveRules(FileNumRule rule) throws ServiceException {
		if(StringUtil.isEmpty(rule)) {
			throw new ServiceException("档号规则为空");
		}
		if(StringUtil.isEmpty(rule.getId())) {
			generalDao.save(rule);
		}else {
			generalDao.update(rule);
		}
	}
	@Override
	public List<FileNumRule> getRule(String type) throws ServiceException {
		if(StringUtil.isEmpty(type)) {
			throw new ServiceException("档案类型为空");
		}
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("type",type,ConditionType.EQ,FieldType.STRING,null));
//		generalDao.queryByCriteria(FileNumRule.class, queryBuilder, new Order[]{new Order("num",OrderType.ASC)}, 0, -1);
		return generalDao.queryByCriteria(FileNumRule.class, queryBuilder, new Order[]{new Order("num",OrderType.ASC)}, 0, -1);
	}
	@Override
	@Transactional
	@LogService(description = "删除档号规则")
	public void deleteRule(String id) throws ServiceException {
		if(StringUtil.isEmpty(id)) {
			throw new ServiceException("档号规则id不存在");
		}
		generalDao.deleteById(id, FileNumRule.class);
	}
	@Override
	public BaseEntityInf getObj(String className) throws ServiceException {
		List<BaseEntityInf> list;
		try {
			list = (List<BaseEntityInf>)generalDao.queryByCriteria(Class.forName(className), new QueryBuilder(), new Order[]{new Order("createTime", OrderType.DESC)}, 0, -1);
			if(list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional
	@LogService(description = "根据规则获取档号")
	public String getDahByType(String type) throws ServiceException {
		//排序后的档号规则
		List<FileNumRule> rule = this.getRule(type);
		//根据档号规则拼接档号
		String dhRule = "";
		if(rule != null && rule.size() > 0) {
			for(FileNumRule r : rule) {
				if("text".equals(r.getTitle())) {
					dhRule += r.getValue();
				}else if("year".equals(r.getTitle())) {
					//获取当前年份
					int year = Calendar.getInstance().get(Calendar.YEAR);
					dhRule += year;
				}else if("month".equals(r.getTitle())) {
					//获取当前月份
					int month = Calendar.getInstance().get(Calendar.MONDAY) + 1;
					dhRule += new DecimalFormat("00").format(month);
				}else if("date".equals(r.getTitle())) {
					//获取当前日期
					int date = Calendar.getInstance().get(Calendar.DATE);
					dhRule += new DecimalFormat("00").format(date);
				}else if("number".equals(r.getTitle())) {
					dhRule += String.format("%0" + r.getValue() + "d",r.getSerialNum() + 1);
					//取走一个档号后，流水号加1并保存到数据库
					r.setSerialNum(r.getSerialNum() + 1);
					this.saveRules(r);
				}else {
					dhRule += r.getValue();
				}
			}
		}	
		return dhRule;
	}
	@Override
	public DTableName gettableNameByName(String name) throws ServiceException {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("bm",name,ConditionType.EQ,FieldType.STRING,null));
//		generalDao.queryByCriteria(FileNumRule.class, queryBuilder, new Order[]{new Order("num",OrderType.ASC)}, 0, -1);
		List<DTableName> tlist = generalDao.queryByCriteria(DTableName.class, queryBuilder, null, 0, -1);
		if(tlist != null && tlist.size() > 0) {
			return tlist.get(0);
		}
		return null;
	}

}
