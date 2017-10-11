package cn.proem.suw.web.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.Department;
import cn.proem.core.entity.Duty;
import cn.proem.core.entity.Role;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.entity.UserMailConfig;
import cn.proem.core.entity.UserRole;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.suw.web.common.exception.DeptNotExistException;
import cn.proem.suw.web.common.exception.DutyNotExistException;
import cn.proem.suw.web.common.exception.ExistUserDeptDutyException;
import cn.proem.suw.web.common.exception.ExistUsernameException;
import cn.proem.suw.web.common.exception.IdNotExitException;
import cn.proem.suw.web.common.exception.NullDeptException;
import cn.proem.suw.web.common.exception.NullPasswordException;
import cn.proem.suw.web.common.exception.NullUsernameException;
import cn.proem.suw.web.common.exception.PasswordException;
import cn.proem.suw.web.common.exception.UserNotExistException;
import cn.proem.suw.web.common.service.UserManagementService;

/**
 * 用户管理服务实现
 * 
 * @author Denny
 */
@Service
public class UserManagementServiceImpl implements UserManagementService
{

    @Resource
    private GeneralDao generalDao;

    @Override
    public DataGrid<UserDepartment> query(QueryBuilder queryBuilder, int page, int pageSize)
    {
        DataGrid<UserDepartment> dataGrid = new DataGrid<UserDepartment>();
        dataGrid.setNowPage(page);
        dataGrid.setPageSize(pageSize);
        dataGrid.setRecordCount(generalDao.countByCriteria(UserDepartment.class, queryBuilder));
        dataGrid.setExhibitDatas(generalDao.queryByCriteria(
                UserDepartment.class,
                queryBuilder,
                new Order[] {},
                dataGrid.getStartRecord(),
                dataGrid.getPageSize()));
        return dataGrid;
    }

    @Override
    @Transactional
    @LogService(description = "保存用户")
    public void addUser(User user, List deptDutys, String roles) throws NullUsernameException, ExistUsernameException, DeptNotExistException,
            DutyNotExistException, NullDeptException, NullPasswordException, ExistUserDeptDutyException
    {
        if (user.getUsername() == null || user.getUsername().trim().length() == 0)
        {
            throw new NullUsernameException();
        }
        if (user.getPassword() == null || "".equals(user.getPassword()))
        {
            throw new NullPasswordException();
        }
        // 查询数据库中是否已存在改用户名
        User userSel = generalDao.findUniqueByProperty("username", user.getUsername(), User.class);
        if (userSel != null)
        {
            throw new ExistUsernameException();
        }
        user.setId(UUID.randomUUID().toString());
        // 将user信息插入到user表中
        generalDao.save(user);

        // 插入到ptp_user_department
        for (Object object : deptDutys)
        {
            JSONObject jsonObject = JSONObject.fromObject(object);
            String deptId = jsonObject.getString("deptId");
            String dutyCode = jsonObject.getString("dutyCode");
            Department dept = generalDao.findById(deptId, Department.class);
            Duty duty = generalDao.findUniqueByProperty("code", dutyCode, Duty.class);

            UserDepartment ud = new UserDepartment();
            if (dept == null || dept.getId() == null || "".equals(dept.getId()))
            {
                throw new NullDeptException();
            }
            if (dept != null && dept.getId() != null && !"".equals(dept.getId()))
            {
                Department de = generalDao.findUniqueByProperty("id", dept.getId(), Department.class);
                if (de == null)
                {
                    throw new DeptNotExistException();
                }
                ud.setDepartment(de);
            }
            if (duty != null && duty.getCode() != null && !"".equals(duty.getCode()))
            {
                Duty d = generalDao.findUniqueByProperty("code", duty.getCode(), Duty.class);
                if (d == null)
                {
                    throw new DutyNotExistException();
                }
                ud.setDuty(d);
            }
            if (ud.getDepartment() != null || ud.getDuty() != null)
            {
                ud.setUser(user);
            }
            generalDao.save(ud);

        }

        // 保存角色
        if (roles != null && roles.length() > 0)
        {
            String[] arr = roles.split(",");
            for (String id : arr)
            {
                UserRole userRole = new UserRole();
                userRole.setRole(generalDao.findById(id, Role.class));
                userRole.setUser(user);
                generalDao.save(userRole);
            }
        }
    }

    @Override
    @Transactional
    @LogService(description = "修改用户")
    public void updateUser(String udId, User user, List deptDutys, String roles) throws UserNotExistException, NullUsernameException,
            ExistUsernameException, DeptNotExistException, DutyNotExistException, NullDeptException, ExistUserDeptDutyException
    {

        // 判断页面传过来的用户在数据库中是否已经存在。
        User userSel = generalDao.findUniqueByProperty("username", user.getUsername(), User.class);
        if (userSel != null && !user.getId().equals(userSel.getId()))
        {
            throw new ExistUsernameException();
        }
        QueryBuilder qb = new QueryBuilder();
        qb.addCondition(new QueryCondition("id", user.getId(), ConditionType.EQ, FieldType.STRING, "user"));
        List<UserDepartment> list = generalDao.queryByCriteria(UserDepartment.class, qb, null, 0, -1);
        if (list != null && list.size() > 0)
        {
            for (UserDepartment ud : list)
            {
                generalDao.delete(ud);
            }
        }
        for (Object object : deptDutys)
        {
            UserDepartment userDepartment = new UserDepartment();
            JSONObject jsonObject = JSONObject.fromObject(object);
            String deptId = jsonObject.getString("deptId");
            String dutyCode = jsonObject.getString("dutyCode");
            Department dept = generalDao.findById(deptId, Department.class);
            Duty duty = generalDao.findUniqueByProperty("code", dutyCode, Duty.class);
            if (dept == null)
            {
                throw new DeptNotExistException();
            }
            if (duty == null)
            {
                throw new DutyNotExistException();
            }
            userDepartment.setUser(user);
            userDepartment.setDepartment(dept);
            userDepartment.setDuty(duty);
            generalDao.save(userDepartment);
        }

        user.setPassword(userSel.getPassword());
        generalDao.update(user);

        // 更新角色
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", user.getId(), ConditionType.EQ, FieldType.STRING, "user"));
        List<UserRole> userRoles = generalDao.queryByCriteria(UserRole.class, queryBuilder, null, 0, -1);
        if (userRoles != null && userRoles.size() > 0)
        {
            for (UserRole userRole : userRoles)
            {
                generalDao.delete(userRole);
            }
        }
        if (roles != null && roles.length() > 0)
        {
            String[] arr = roles.split(",");
            for (String id : arr)
            {
                UserRole userRole = new UserRole();
                userRole.setRole(generalDao.findById(id, Role.class));
                userRole.setUser(user);
                generalDao.save(userRole);
            }
        }
    }

    @Override
    @Transactional
    @LogService(description = "修改密码")
    public void resetPwd(UserDepartment userDepartment, String password2) throws UserNotExistException, PasswordException, NullPasswordException
    {

        UserDepartment ud = generalDao.findById(userDepartment.getId(), UserDepartment.class);
        if (ud.getUser().getId() == null)
        {
            throw new UserNotExistException();
        }
        User oldUser = generalDao.findById(ud.getUser().getId(), User.class);
        if (oldUser == null)
        {
            throw new UserNotExistException();
        }
        if (ud.getUser().getPassword() == null || "".equals(ud.getUser().getPassword()) || password2 == null || "".equals(password2))
        {
            throw new NullPasswordException();
        }
        oldUser.setPassword(password2);
        generalDao.update(oldUser);
    }

    @Override
    public UserDepartment getUserDept(String udId) throws UserNotExistException
    {
        UserDepartment ud = new UserDepartment();
        if (udId == null)
        {
            throw new UserNotExistException();
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", udId, ConditionType.EQ, FieldType.STRING, null));
        List<UserDepartment> list = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 1, -1);
        if (list != null && list.size() > 0)
        {
            ud = list.get(0);
        }
        return ud;
    }

    @Override
    public User getUser(String username) throws UserNotExistException
    {
        User user = new User();
        if ("".equals((String) username.trim()))
        {
            throw new UserNotExistException();
        } else
        {
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addCondition(new QueryCondition("username", username, ConditionType.EQ, FieldType.STRING, null));
            List<User> list = generalDao.queryByCriteria(User.class, queryBuilder, null, 1, -1);
            if (list != null && list.size() > 0)
            {
                user = list.get(0);
            }
            return user;
        }
    }

    @Override
    @Transactional
    @LogService(description = "删除用户及部门职位关系")
    public void deleteUserDept(String udId) throws IdNotExitException
    {
        if (udId == null)
        {
            throw new IdNotExitException();
        }
        UserDepartment userDepartment = generalDao.findById(udId, UserDepartment.class);
        String userId = userDepartment.getUser().getId();
        generalDao.deleteById(udId, UserDepartment.class);
        generalDao.deleteById(userId, User.class);

    }

    @Override
    public List<Role> getRolesByUserId(String userId)
    {
        List<Role> roles = new ArrayList<Role>();
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", userId, ConditionType.EQ, FieldType.STRING, "user"));
        List<UserRole> userRoles = generalDao.queryByCriteria(UserRole.class, queryBuilder, null, 0, -1);
        if (userRoles != null && userRoles.size() > 0)
        {
            for (UserRole userRole : userRoles)
            {
                roles.add(userRole.getRole());
            }
        }
        return roles;
    }

    @Override
    public List<UserDepartment> getUserDepts(String userId) throws UserNotExistException
    {
        List<UserDepartment> userDepartments = new ArrayList<UserDepartment>();
        if (userId == null)
        {
            throw new UserNotExistException();
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", userId, ConditionType.EQ, FieldType.STRING, "user"));
        List<UserDepartment> list = generalDao.queryByCriteria(UserDepartment.class, queryBuilder, null, 1, -1);
        if (list != null && list.size() > 0)
        {
            userDepartments = list;
        }
        return userDepartments;
    }

    @Override
    public List<Map<String, Object>> getUserMailConfigs(String userid)
    {
        StringBuffer sql = new StringBuffer("SELECT process.id AS  \"id\",process.name AS  \"name\", ");
        sql.append("(case when dd.target IS NULL then 0 else 1 end) AS \"ddtx\",(case when dq.target IS NULL then 0 else 1 end) AS \"dqtx\" ");
        sql.append(" FROM PTP_BPM_PROCESS process ");
        sql.append(" left join (").append(getJoinUserSQL(userid, UserMailConfig.FLOW_ARRIVE)).append(") dd ON dd.target = process.id ");
        sql.append(" left join (").append(getJoinUserSQL(userid, UserMailConfig.FLOW_EXPIRE)).append(") dq ON dq.target = process.id ");
        sql.append(" ORDER BY process.id");
        return generalDao.getObjectList(sql.toString(), 0, -1);
    }

    private String getJoinUserSQL(String userid, String type)
    {
        StringBuffer sql = new StringBuffer("SELECT TARGET FROM PTP_USER_MAIL_CONFIG WHERE USER_ID = '");
        sql.append(userid).append("' AND TYPE = '");
        sql.append(type).append("'");
        return sql.toString();
    }

    @Override
    @Transactional
    public void setUserMailConfigs(User user, List<UserMailConfig> configs)
    {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id", user.getId(), ConditionType.EQ, FieldType.STRING, "user"));
        List<UserMailConfig> oldConfigs = generalDao.queryByCriteria(UserMailConfig.class, queryBuilder, null, 0, -1);
        if (!CollectionUtils.isEmpty(oldConfigs))
        {
            for (UserMailConfig cfg : oldConfigs)
            {
                generalDao.delete(cfg);
            }
        }
        for (UserMailConfig cfg : configs)
        {
            cfg.setUser(user);
            generalDao.save(cfg);
        }
    }

}