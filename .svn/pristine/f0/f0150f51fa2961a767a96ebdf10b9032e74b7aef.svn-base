package cn.proem.dagl.web.table.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.table.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private GeneralDao generalDao;
    
    @Override
    public boolean hasDalx(String userid, String dalx) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.roleid, ");
        sql.append("       t2.bm, ");
        sql.append("       t3.user_id ");
        sql.append("  from pdagl_role_dalx t1");
        sql.append(" inner join pdagl_tablename t2");
        sql.append("    on t2.id = t1.dalxid");
        sql.append(" inner join ptp_user_role t3");
        sql.append("    on t3.role_id = t1.roleid");
        sql.append(" where t3.user_id = '");
        sql.append(userid);
        sql.append("'   and t2.bm = '");
        sql.append(dalx);
        sql.append("'");
        List<Map<String, Object>> list = generalDao.getObjectList(sql.toString(), 0, -1, params);
        if(list.size() > 0){
            return true;
        }
        return false;
    }

}
