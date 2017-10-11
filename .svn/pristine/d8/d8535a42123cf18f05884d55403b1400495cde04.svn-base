package cn.proem.dagl.web.sysManage.service;


import cn.proem.core.entity.User;
import cn.proem.dagl.web.sysManage.entity.UserDetail;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 个人信息服务层
 * @author lenovo
 *
 */

public interface PersonalInfoService {
	
	/**
	 * @Description 根据登录用户获得用户详情
	 * @MethodName getUserDetailByUser
	 * @author bao
	 * @date 2017年4月24日
	 * @param user
	 * @return UserDetail
	 */
	public UserDetail getUserDetailByUser(User user);
	
	/**
	 * @Description 获得登录的次数
	 * @MethodName getLoginNum
	 * @author bao
	 * @date 2017年4月24日
	 * @param user
	 * @return Integer
	 */
	public Integer getLoginNum(User user);
	
	/**
	 * @Description 获得最近一次登录的时间
	 * @MethodName getLastTime
	 * @author bao
	 * @date 2017年4月24日
	 * @param user
	 * @return String
	 */
	public String getLastTime(User user);
	
	
	/**
	 * @Description 更新用户表
	 * @MethodName updateUser
	 * @author bao
	 * @date 2017年4月24日
	 * @param user
	 * @throws ServiceException void
	 */
	public void updateUser(User user)throws ServiceException;
	
	/**
	 * @Description 修改或者更新用户详情
	 * @MethodName saveOrUpdatUserDetail
	 * @author bao
	 * @date 2017年4月24日 void
	 */
	public void saveOrUpdatUserDetail(UserDetail userDetail)throws ServiceException;
	

}
