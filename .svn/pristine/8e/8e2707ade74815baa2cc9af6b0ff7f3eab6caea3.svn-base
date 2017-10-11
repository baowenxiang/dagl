package cn.proem.suw.web.common.service;

import java.util.List;

import cn.proem.core.entity.Department;
import cn.proem.core.entity.RoleGroup;
import cn.proem.core.entity.SystemCode;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.bpm.entity.Process;

/**
 * @ClassName CommonService
 * @Description 公共业务接口
 * @author Tcc
 * @date 2017年2月28日
 */
public interface CommonService {

	/**
	 * @MethodName findById
	 * @Description 根据id, 获取对象
	 * @author Pan Jilong
	 * @date 2017年2月27日
	 * @param uid
	 * @return
	 */
	<T>T findById(String id, Class<T> cls);

	/**
	 * @MethodName findDeptByUserId
	 * @Description 获取部门, 根据用户id
	 * @author Pan Jilong
	 * @date 2017年2月28日
	 * @param id
	 * @return
	 */
	String findDeptByUserId(String userId);

	/**
	 * @MethodName findUsersByRoleId
	 * @Description 根据角色ID查找人员
	 * @author Pan Jilong
	 * @date 2017年3月3日
	 * @param rid
	 * @return
	 */
	List<User> findUsersByRoleId(String rid);

	/**
	 * @MethodName findUsersByDeptId
	 * @Description 根据部门ID， 查找到所有人员， 包括子部门的人员
	 * @author Pan Jilong
	 * @date 2017年3月3日
	 * @param did
	 * @return
	 */
	List<User> findUsersByDeptId(String did);

	/**
	 * @MethodName findUsersByDutyId
	 * @Description 根据职务ID， 查找到所有人员
	 * @author Pan Jilong
	 * @date 2017年3月3日
	 * @param dutyid
	 * @return
	 */
	List<User> findUsersByDutyId(String dutyid);

	
	/**
	 * @MethodName findDictionaryValues
	 * @Description 根据code，获取字典值集合
	 * @author Pan Jilong
	 * @date 2017年3月21日
	 * @param code
	 * @return
	 */
	List<SystemCode> findDictionaryValues(String code);
	/**
	 * @MethodName findDictionaryValue
	 * @Description 根据value，获取字典值
	 * @author fang
	 * @date 2017年4月14日
	 * @param value
	 * @return
	 */
	SystemCode findDictionaryValue(String value);
	
	/**
	 * @Method: findUserDepartmentByUserId 
	 * @Description: 更加用户获得所在部门
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	UserDepartment findUserDepartmentByUserId(String userId) throws ServiceException;
	/**
	 * 获取所有部门
	 * @return
	 */
	List<Department> findDepartmentList();
	
	/**
	 * @Description 查询用户所在的角色组
	 * @MethodName findRoleGroupByUser
	 * @author bao
	 * @date 2017年6月9日
	 * @param user
	 * @return RoleGroup
	 */
	List<RoleGroup> findRoleGroupByUser(User user);
	
	/**
	 * @Description 是否是档案管理员或者是管理员
	 * @MethodName isAdminOrFileAdmin
	 * @author bao
	 * @date 2017年6月9日
	 * @param user
	 * @return Boolean
	 */
	Boolean isAdminOrFileAdmin(User user);
}
