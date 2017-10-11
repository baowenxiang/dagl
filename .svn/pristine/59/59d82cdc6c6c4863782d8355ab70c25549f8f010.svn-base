package cn.proem.dagl.web.sysManage.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.entity.log.LoginLog;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.sysManage.entity.UserDetail;
import cn.proem.dagl.web.sysManage.service.PersonalInfoService;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 个人信息服务层实现类
 * @author lenovo
 *
 */
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
	@Autowired
	private GeneralDao generalDao;

	@Override
	public UserDetail getUserDetailByUser(User user) {
		return generalDao.findUniqueByProperty("user", user, UserDetail.class);
	}

	@Override
	public Integer getLoginNum(User user) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id",user.getId(),ConditionType.EQ, FieldType.STRING, "user"));
		return generalDao.countByCriteria(LoginLog.class, queryBuilder);
	}

	@Override
	public String getLastTime(User user) {
		QueryBuilder queryBuilder = new QueryBuilder();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		queryBuilder.addCondition(new QueryCondition("id",user.getId(),ConditionType.EQ, FieldType.STRING, "user"));
		List<LoginLog> logins = generalDao.queryByCriteria(LoginLog.class, queryBuilder, new Order[]{new Order("loginTime", OrderType.DESC)}, 0, -1);
		if(logins.size()>0 && logins!=null){
			return simpleDateFormat.format(logins.get(0).getLoginTime());
		}
		return null;
	}

	@Override
	@Transactional
	@LogService(description = "修改人员信息")
	public void updateUser(User user) throws ServiceException {
		generalDao.update(user);
	}

	@Override
	@Transactional
	@LogService(description = "保存或修改人员详情")
	public void saveOrUpdatUserDetail(UserDetail userDetail)
			throws ServiceException {
		generalDao.saveOrUpdate(userDetail);
	}
	

}
