package cn.proem.dagl.web.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.message.entity.Message;
import cn.proem.dagl.web.message.service.MessageService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private GeneralDao generalDao;
	
	/**
	 * 获取message列表
	 */
	@Override
	public DataGrid<Message> getMessageDateGrid(QueryBuilder queryBuilder,
			int nowPage, int pageSize) {
		DataGrid<Message> dataGrid = new DataGrid<Message>(nowPage,pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(Message.class, queryBuilder));
		
		List<Message> list = new ArrayList<Message>();
		list = generalDao.queryByCriteria(Message.class, queryBuilder, null, dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	/**
	 * 根据id逻辑删除message
	 */
	@Override
	@Transactional
	@LogService(description="删除message")
	public void deleteMessageById(String id) {
		// TODO Auto-generated method stub
		Message message = generalDao.findById(id, Message.class);
		message.setDelFlag(1);
		generalDao.update(message);
	}
	/**
	 * 保存或者更新消息
	 */
	@Override
	@Transactional
	@LogService(description="保存或更新message")
	public void saveOrUpdateMessage(Message message) throws ServiceException{
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(message.getId())) {
			generalDao.save(message);
		}else{
			generalDao.update(message);
		}
	}
	/**
	 * 根据name查找user
	 */
	@Override
	public User getUserByName(String name) {
		User user = new User();
		QueryBuilder qb = new QueryBuilder();
		qb.addCondition(new QueryCondition("name",name,ConditionType.EQ,FieldType.STRING,null));
		List<User> list= generalDao.queryByCriteria(User.class,qb,null,0,-1);
		if(list != null && list.size()>0){
			user = list.get(0);
		}
		
		return user;
	}
	/**
	 * 获取未读消息
	 */
	@Override
	public List<Message> getUnreadMessageList(QueryBuilder queryBuilder) {
		// TODO Auto-generated method stub
//		QueryBuilder qb = new QueryBuilder();
//		qb.addCondition(new QueryCondition("isRead","isRead_0",ConditionType.EQ,FieldType.STRING,null));
//		User receUser = this.getCurrentUser(request);
//		String receUser_id = receUser.getId();
//		qb.addCondition(new QueryCondition("id",receUser_id,ConditionType.EQ,FieldType.STRING,"receUser"));
//		qb.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		List<Message> list= generalDao.queryByCriteria(Message.class, queryBuilder, null, 0, -1);
		
		return list;
	}
	@Override
	public List<User> findAllUsers() {
		return generalDao.queryByCriteria(User.class, new QueryBuilder(), null, 0, -1);
	}

}
