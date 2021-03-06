package cn.proem.dagl.web.message.service;

import java.util.List;

import cn.proem.core.entity.User;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.message.entity.Message;
import cn.proem.suw.web.common.exception.ServiceException;

public interface MessageService {
	/**
	 * 获取消息列表
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	public DataGrid<Message> getMessageDateGrid(QueryBuilder queryBuilder, int nowPage, int pageSize);
	
	public void deleteMessageById(String id);
	
	public void saveOrUpdateMessage(Message message) throws ServiceException;
	
	public User getUserByName(String name);
	
	public List<Message> getUnreadMessageList(QueryBuilder queryBuilder);
	
	
	/**
	 * @Description:查询所有用户
	 * @author:bao
	 * @time:2017年6月27日 下午5:06:49
	 */
	public List<User> findAllUsers();

}
