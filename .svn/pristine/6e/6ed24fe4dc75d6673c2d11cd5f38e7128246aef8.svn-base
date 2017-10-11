package cn.proem.dagl.web.preArchive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.service.IDService;
import cn.proem.dagl.web.preArchive.service.YwgjService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class IDServiceImpl implements IDService {
	@Autowired
	private GeneralDao generalDao;
	//     出参         方法名                  入参
	@Override
	public Ywgj getYwgj(String zlid) { 
		Ywgj ywgj = null;
		
		try {
			//  创建查询条件
			QueryBuilder queryBuilder = new QueryBuilder();
			//查询条件  “列名” ，内容 ， 比较条件 ， 内容类型 ， null
			queryBuilder.addCondition(new QueryCondition("id",zlid,ConditionType.EQ, FieldType.STRING,null));
			queryBuilder.addCondition(new QueryCondition("delFlag",0,ConditionType.EQ, FieldType.INTEGER,null));
			//  执行查询条件
			ywgj = generalDao.queryByCriteria(Ywgj.class, queryBuilder, null, 0, -1).get(0);
		} catch (Exception e) {
			//  报错返回
			return null;
		}		
	//  正常返回
	return ywgj;
	}
	
}
