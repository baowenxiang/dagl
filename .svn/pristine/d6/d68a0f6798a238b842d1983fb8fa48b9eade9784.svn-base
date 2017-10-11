package cn.proem.suw.web.auth.service;

import java.util.List;

import java.util.Map;

import cn.proem.core.entity.Privilege;
import cn.proem.core.entity.PrivilegeButton;
import cn.proem.core.entity.PrivilegeMenu;
import cn.proem.core.entity.User;
import cn.proem.core.model.Module;

/**
 * 权限管理服务
 * 
 * @author Denny
 */
public interface PrivilegeManagementService
{

    /**
     * 查询权限
     * 
     * @param type
     *            权限类型
     * @param text
     *            查询关键字
     * @param parentCode
     *            父代码
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> queryPrivilege(String type, String text, String parentCode);

    /**
     * 添加菜单权限
     * 
     * @param privilege
     *            权限
     */
    void addMenuPrivilege(PrivilegeMenu privilege);

    /**
     * 添加按钮权限
     * 
     * @param privilege
     *            权限
     */
    void addButtonPrivilege(PrivilegeButton privilege);

    /**
     * 更新菜单权限
     * 
     * @param privilege
     *            权限
     */
    void updateMenuPrivilege(PrivilegeMenu privilege);

    /**
     * 更新按钮权限
     * 
     * @param privilege
     *            权限
     */
    void updateButtonPrivilege(PrivilegeButton privilege);

    /**
     * 删除权限
     * 
     * @param privilege
     *            权限
     */
    void deletePrivilege(Privilege privilege);

    /**
     * 获取菜单权限
     * 
     * @param code
     *            代码
     * @return PrivilegeMenu
     */
    PrivilegeMenu getPrivilegeMenu(String code);

    /**
     * 权限按钮权限
     * 
     * @param code
     *            代码
     * @return PrivilegeButton
     */
    PrivilegeButton getPrivilegeButton(String code);

    /**
     * 获取菜单
     * 
     * @param type
     *            类型
     * @return List<PrivilegeMenu>
     */
    List<PrivilegeMenu> getPrivilegeMenus(String type);

    /**
     * 获取按钮菜单
     * 
     * @param type
     *            类型
     * @return List<PrivilegeButton>
     */
    List<PrivilegeButton> getPrivilegeButtons(String type);
    
    /**
     * @Method: getPrivilegeButtons 
     * @Description: 获得按钮
     * @param user
     * @return
     */
    List<Module> getPrivilegeButtons(User user);
    
    /**
     * @Method: getPrivilegeButtons 
     * @Description: 获得按钮
     * @param user
     * @return
     */
    List<Module> getPrivilegeButtons(User user, String parent);
    
    /**
    * @Method: getPrivilegeButtons 
    * @Description: 获得按钮
    * @param user
    * @param parent
    * @param withChildren
    * @param type
    * @return
    */
    List<Module> getPrivilegeButtons(User user, String parent, boolean withChildren, int type);
    
    /**
     * @Method: hasPrivilege 
     * @Description: 存在权限
     * @param username
     * @param privilege
     * @return
     */
    boolean hasPrivilege(String username, String privilege);
}