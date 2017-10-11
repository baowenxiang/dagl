package cn.proem.suw.web.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.Department;
import cn.proem.core.entity.RoleGroup;
import cn.proem.core.entity.SystemCode;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.entity.UserRole;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private GeneralDao generalDao;
	
	@Override
	public <T>T findById(String id, Class<T> cls) {
		return (T) generalDao.findById(id, cls);
	}

	@Override
	public String findDeptByUserId(String userId) {
		String deptId = "";
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id", userId, ConditionType.EQ, FieldType.STRING, "user"));
		List<UserDepartment> uds = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 1, -1);
		if (uds != null && uds.size() > 0) {
			deptId = uds.get(0).getDepartment().getId();
		}
		return deptId;
	}

	@Override
	public List<User> findUsersByRoleId(String rid) {
		List<User> us = new ArrayList<User>();
		//添加条件
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id", rid, ConditionType.EQ, FieldType.STRING, "role"));
		List<UserRole> UserRoles = generalDao.queryByCriteria(UserRole.class, queryBuilder, null, 0, -1);
		if (UserRoles != null && UserRoles.size() > 0) {
			for (UserRole userRole : UserRoles) {
				us.add(userRole.getUser());
			}
		}
		return us;
	}

	@Override
	public List<User> findUsersByDeptId(String did) {
		List<User> us = new ArrayList<User>();
		//根据部门找人员
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id", did, ConditionType.EQ, FieldType.STRING, "department"));
		List<UserDepartment> uds = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 0, -1);
		if (uds != null && uds.size() > 0) {
			for (UserDepartment userDepartment : uds) {
				us.add(userDepartment.getUser());
				//查找是否有子部门
				queryBuilder = new QueryBuilder();
				//当前部门
				queryBuilder.addCondition(new QueryCondition("id", userDepartment.getDepartment().getId(), ConditionType.EQ, FieldType.STRING, "parent"));
				List<Department> childs = generalDao.queryByCriteria(Department.class, queryBuilder, null, 0, -1);
				if (childs != null && childs.size() > 0) {
					for (Department department : childs) {
						findUsersByDeptId(department.getId());
					}
				}
			}
		}
		return us;
	}

	@Override
	public List<User> findUsersByDutyId(String dutyid) {
		List<User> us = new ArrayList<User>();
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id", dutyid, ConditionType.EQ, FieldType.STRING, "duty"));
		List<UserDepartment> uds = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 0, -1);
		if (uds != null && uds.size() > 0) {
			for (UserDepartment userDepartment : uds) {
				us.add(userDepartment.getUser());
			}
		}
		return us;
	}


	@Override
	public List<SystemCode> findDictionaryValues(String code) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("code", code, ConditionType.EQ, FieldType.STRING));
		queryBuilder.addCondition(new QueryCondition("id", null, ConditionType.NNULL, FieldType.STRING, "parent"));
		return generalDao.queryByCriteria(SystemCode.class, queryBuilder, new Order[]{new Order("order", OrderType.ASC)}, 0, -1);
	}

	@Override
	public SystemCode findDictionaryValue(String value) {
		return generalDao.findUniqueByProperty("value", value, SystemCode.class);
	}

	@Override
	public UserDepartment findUserDepartmentByUserId(String userId) throws ServiceException {
		UserDepartment ud = null;
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("id", userId, ConditionType.EQ, FieldType.STRING, "user"));
		List<UserDepartment> uds = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 0, -1);
		if (uds != null && uds.size() > 0) {
			ud = uds.get(0);
		}
		if (ud == null) {
			throw new ServiceException("该用户没有设置部门!");
		}
		return ud;
	}

	@Override
	public List<Department> findDepartmentList() {
		List<Department> list = generalDao.queryByCriteria(Department.class, null, null, 0, -1);
		return list;
	}

	@Override
	public List<RoleGroup> findRoleGroupByUser(User user) {
		List<RoleGroup> roleGroups = new ArrayList<RoleGroup>();
		
		QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", user.getId(), ConditionType.EQ, FieldType.STRING, "user"));
        List<UserRole> userRoles = generalDao.queryByCriteria(UserRole.class, queryBuilder, null, 0, -1);
        
        for(UserRole userRole : userRoles){
        	roleGroups.add(userRole.getRole().getGroup());
        }
        
		return roleGroups;
	}

	@Override
	public Boolean isAdminOrFileAdmin(User user) {
		Boolean flag = false;
		QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", user.getId(), ConditionType.EQ, FieldType.STRING, "user"));
        List<UserRole> userRoles = generalDao.queryByCriteria(UserRole.class, queryBuilder, null, 0, -1);
        
        for(UserRole userRole : userRoles){
        	String roleGroupName = userRole.getRole().getGroup().getName();
        	if("档案管理员".equals(roleGroupName) || "管理员".equals(roleGroupName)){
        		flag = true;
        		break;
        	}
        }
        
        return flag;
	}
	
	

}
