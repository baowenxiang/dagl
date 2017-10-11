package cn.proem.dagl.web.preArchive.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.oaservice.entity.TGdfjObj;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class OAfjServiceImpl implements cn.proem.dagl.web.preArchive.service.OAfjService {
    @Autowired
    private GeneralDao generalDao;
    
    @Override
    public List<Ywgj> getFileBydataId(String id) {
        QueryBuilder queryBuilder = new QueryBuilder();
        List<Ywgj> ywgjs;
        if(StringUtil.isEmpty(id)){
            ywgjs  = new ArrayList<Ywgj>();
        }else{
            queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0), ConditionType.EQ,FieldType.INTEGER, null));
            queryBuilder.addCondition(new QueryCondition("zlsj", id, ConditionType.EQ, null));
            ywgjs = generalDao.queryByCriteria(Ywgj.class, queryBuilder, new Order[]{new Order("xsxh", OrderType.ASC)}, 0, -1);
        }
        return ywgjs;
    }

}
