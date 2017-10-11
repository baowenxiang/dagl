package cn.proem.suw.web.common.service;

import java.util.List;
import java.util.Map;

import cn.proem.core.entity.Role;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.entity.UserMailConfig;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
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

/**
 * 用户管理服务
 * 
 * @author Denny
 */
public interface UserManagementService
{

    /**
     * 查询用户列表
     * 
     * @param queryBuilder
     *            查询表达式
     * @param page
     *            页码
     * @param pageSize
     *            每页显示条数
     * @return DataGrid<UserDepartment>
     */
    DataGrid<UserDepartment> query(QueryBuilder queryBuilder, int page, int pageSize);

    /**
     * @Title resetPwd
     * @Description 重置密码
     * @author Pan Jilong
     * @date 2016年12月13日
     * @param userDepartment
     *            用户
     * @param password2
     *            密码
     * @throws UserNotExistException
     *             UserNotExistException
     * @throws PasswordException
     *             PasswordException
     * @throws NullPasswordException
     *             NullPasswordException
     */
    void resetPwd(UserDepartment userDepartment, String password2) throws UserNotExistException, PasswordException, NullPasswordException;

    /**
     * @Title UserDepartment
     * @Description 获取人员部门岗位关系
     * @author Pan Jilong
     * @date 2016年12月13日
     * @param userId
     *            用户ID
     * @return UserDepartment
     * @throws UserNotExistException
     *             UserNotExistException
     */
    UserDepartment getUserDept(String userId) throws UserNotExistException;

    /**
     * @Description 根据用户名查询用户信息
     * @param username
     *            用户名
     * @return User
     * @throws UserNotExistException
     *             UserNotExistException
     */
    User getUser(String username) throws UserNotExistException;

    /**
     * @Description 删除人员部门岗位关系
     * @param udId
     *            用户部门关系Id
     * @throws IdNotExitException
     *             IdNotExitException
     */
    void deleteUserDept(String udId) throws IdNotExitException;

    /**
     * 根据userid，查找角色集合
     * 
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(String userId);

    /**
     * 获取用户邮件设置信息
     * 
     * @param userid
     *            用户ID
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> getUserMailConfigs(String userid);

    /**
     * 设置邮件提醒
     * 
     * @param user
     *            用户
     * @param configs
     *            邮件设置
     */
    void setUserMailConfigs(User user, List<UserMailConfig> configs);

    void updateUser(String udId, User user, List deptDutys, String roles) throws UserNotExistException, NullUsernameException,
            ExistUsernameException, DeptNotExistException, DutyNotExistException, NullDeptException, ExistUserDeptDutyException;

    void addUser(User user, List deptDutys, String roles) throws NullUsernameException, ExistUsernameException, DeptNotExistException,
            DutyNotExistException, NullDeptException, NullPasswordException, ExistUserDeptDutyException;

    /**
     * 获取用户部门职务集合
     * 
     * @param id
     * @return
     */
    List<UserDepartment> getUserDepts(String id) throws UserNotExistException;

}