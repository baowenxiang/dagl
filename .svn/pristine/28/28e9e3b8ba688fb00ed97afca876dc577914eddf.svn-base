package cn.proem.suw.web.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.Privilege;
import cn.proem.core.entity.PrivilegeButton;
import cn.proem.core.entity.PrivilegeMenu;
import cn.proem.core.entity.User;
import cn.proem.core.model.Module;
import cn.proem.core.util.BeanUtils;
import cn.proem.core.util.CommonUtil;
import cn.proem.suw.util.StringUtil;

/**
 * 权限服务实现
 * 
 * @author Denny
 */
@Service
public class PrivilegeManagementServiceImpl implements cn.proem.suw.web.auth.service.PrivilegeManagementService
{

    @Resource
    private GeneralDao generalDao;

    @Override
    public List<PrivilegeMenu> getPrivilegeMenus(String type)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("SELECT DISTINCT p.code AS \"code\",p.name AS \"name\",p.remark AS \"remark\", ");
        sql.append("p.order_num AS \"order\",p.url AS \"url\",p.icon AS \"icon\",p.parent_code AS \"parentCode\" FROM ptp_privilege p");
        sql.append(" WHERE p.privilege_type = '" + type + "' ");
        sql.append(" ORDER BY p.order_num");
        List<Map<String, Object>> list = generalDao.getObjectList(sql.toString(), 0, -1, params);
        List<PrivilegeMenu> privileges = BeanUtils.cloneMapList(PrivilegeMenu.class, list);
        for (int i = 0; i < privileges.size(); i++)
        {
            String code = list.get(i).get("parentCode") == null ? "" : list.get(i).get("parentCode").toString();
            if (StringUtil.isNotEmpty(code))
            {
                PrivilegeMenu p = this.getPrivilegeMenu(code);
                privileges.get(i).setParent(p);
            }
        }
        return privileges;

    }

    @Override
    public List<PrivilegeButton> getPrivilegeButtons(String type)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("SELECT DISTINCT p.code AS \"code\",p.name AS \"name\",p.remark AS \"remark\", ");
        sql.append("p.order_num AS \"order\",p.url AS \"url\",p.icon AS \"icon\",p.parent_code AS \"parentCode\" FROM ptp_privilege p");
        sql.append(" WHERE p.privilege_type = '" + type + "' ");
        sql.append(" ORDER BY p.order_num");
        List<Map<String, Object>> list = generalDao.getObjectList(sql.toString(), 0, -1, params);
        List<PrivilegeButton> privileges = BeanUtils.cloneMapList(PrivilegeButton.class, list);
        for (int i = 0; i < privileges.size(); i++)
        {
            String code = list.get(i).get("parentCode") == null ? "" : list.get(i).get("parentCode").toString();
            if (StringUtil.isNotEmpty(code))
            {
                PrivilegeButton p = this.getPrivilegeButton(code);
                privileges.get(i).setParent(p);
            }
        }
        return privileges;
    }

    @Override
    public List<Map<String, Object>> queryPrivilege(String type, String text, String parentCode)
    {
        List<Map<String, Object>> results = null;
        if (text == null)
        {
            text = "";
        }
        if (parentCode == null || parentCode.trim().length() == 0)
        {
            StringBuffer str = new StringBuffer();
            str.append("SELECT code as \"id\",name as \"text\",order_num AS \"order_num\",parent_code AS \"parent_code\",icon as \"micon\",url AS \"url\" ");
            str.append(" FROM ptp_privilege WHERE privilege_type=? AND (parent_code IS NULL OR parent_code = '') AND name LIKE ? ORDER BY order_num");
            results = generalDao.getObjectList(str.toString(), 0, -1, type, "%" + text + "%");
        } else
        {
            StringBuffer str = new StringBuffer();
            str.append("SELECT code as \"id\",name as \"text\",order_num AS \"order_num\",parent_code AS \"parent_code\",icon as \"micon\",url AS \"url\"");
            str.append(" FROM ptp_privilege WHERE privilege_type=? AND parent_code = ? AND name LIKE ? ORDER BY order_num");
            results = generalDao.getObjectList(str.toString(), 0, -1, type, parentCode, "%" + text + "%");
        }
        for (Map<String, Object> obj : results)
        {
            obj.put("children", queryPrivilege(type, null, String.valueOf(obj.get("id"))));
        }
        return results;
    }

    @Override
    @Transactional
    public void addMenuPrivilege(PrivilegeMenu privilege)
    {
        generalDao.save(privilege);
    }

    @Override
    @Transactional
    public void addButtonPrivilege(PrivilegeButton privilege)
    {
        generalDao.save(privilege);
    }

    @Override
    @Transactional
    public void updateMenuPrivilege(PrivilegeMenu privilege)
    {
        generalDao.update(privilege);
    }

    @Override
    @Transactional
    public void updateButtonPrivilege(PrivilegeButton privilege)
    {
        generalDao.update(privilege);
    }

    @Override
    @Transactional
    public void deletePrivilege(Privilege privilege)
    {
        generalDao.delete(privilege);
    }

    @Override
    public PrivilegeMenu getPrivilegeMenu(String code)
    {
        return generalDao.findById(code, PrivilegeMenu.class);
    }

    @Override
    public PrivilegeButton getPrivilegeButton(String code)
    {
        return generalDao.findById(code, PrivilegeButton.class);
    }
    
    @Override
    public List<Module> getPrivilegeButtons(User user) {
        return getPrivilegeButtons(user, null, true, -1);
    }
    
    @Override
    public List<Module> getPrivilegeButtons(User user, String parent) {
        // TODO Auto-generated method stub
        return getPrivilegeButtons(user, parent, true, -1);
    }

    @Override
    public List<Module> getPrivilegeButtons(User user, String parent, boolean withChildren, int type) {
        if (user == null)
        {
            return null;
        }
        String username = user.getUsername();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("SELECT DISTINCT p.code AS \"code\",p.name AS \"name\",p.remark AS \"remark\", ");
        sql.append("p.order_num AS \"order\",p.url AS \"url\",p.icon AS \"icon\" FROM ptp_privilege p");
        sql.append(" LEFT JOIN ptp_role_privilege up ON p.code = up.privilege_code ");
        sql.append(" LEFT JOIN ptp_role r ON r.id = up.role_id ");
        sql.append(" LEFT JOIN ptp_user_role ur ON r.id = ur.role_id ");
        sql.append(" LEFT JOIN ptp_user u ON u.id = ur.user_id WHERE p.privilege_type = 'BUTTON' ");
        if (username != null && !"admin".equals(username))
        {
            sql.append(" AND u.username = :username");
            params.put("username", username);
        }
        if (parent != null && parent.trim().length() > 0)
        {
            sql.append(" AND p.parent_code = :code ");
            params.put("code", parent);
        } else
        {
            sql.append(" AND (p.parent_code IS NULL OR LTRIM(RTRIM(p.parent_code)) = '')");
        }
        switch (type)
        {
        case 0:
            sql.append(" AND (p.is_system = 0 OR p.is_system IS NULL) ");
            break;
        case 1:
            sql.append(" AND p.is_system = 1 ");
            break;
        default:
            break;
        }
        sql.append(" ORDER BY p.order_num");
        List<Map<String, Object>> list = generalDao.getObjectList(sql.toString(), 0, -1, params);
        List<Module> modules = BeanUtils.cloneMapList(Module.class, list);
        if (withChildren)
        {
            for (Module module : modules)
            {
                if (module.getCode() == null)
                {
                    continue;
                }
                module.setChildren(getPrivilegeButtons(user, module.getCode()));
            }
        }
        return modules;
    }

    @Override
    public boolean hasPrivilege(String username, String privilege) {
        if ("admin".equals(username))
        {
            return true;
        }
        StringBuffer sql = new StringBuffer("SELECT COUNT(u.id) AS \"cnt\" ");
        sql.append(" FROM ptp_user u,ptp_user_role ur,ptp_role r,ptp_role_privilege rp,ptp_privilege p");
        sql.append(" WHERE u.id = ur.user_id AND ur.role_id = r.id AND r.id = rp.role_id");
        sql.append(" AND rp.privilege_code = p.code AND u.username = ? AND p.code = ?");
        Map<String, Object> map = generalDao.getObject(sql.toString(), username, privilege);
        return CommonUtil.objectToInt(map.get("cnt")) > 0;
    }

   



}