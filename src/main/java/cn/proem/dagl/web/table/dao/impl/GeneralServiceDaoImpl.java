package cn.proem.dagl.web.table.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import cn.proem.core.exception.DBException;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.core.support.DaoSupport;
import cn.proem.dagl.web.table.dao.GeneralServiceDao;

/**
 * 通用Dao实现
 * 
 * @author Denny
 */
@Repository(value = "generalServiceDaoImpl")
@SuppressWarnings("unchecked")
public class GeneralServiceDaoImpl extends DaoSupport implements GeneralServiceDao
{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public String save(Object entity)
    {
        return String.valueOf(getCurrentSession().save(entity));
    }

    @Override
    public void update(Object entity)
    {
        try
        {
            getCurrentSession().merge(entity);
        } catch (Exception e)
        {
            throw new DBException("更新数据失败" + e.getMessage(), e);
        }
    }

    @Override
    public void saveOrUpdate(Object entity)
    {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Object entity)
    {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(String id, Class<?> cls)
    {
        try
        {
            Object entity = findById(id, cls);
            Assert.notNull(entity, "未找到要删除的对象" + id);
            getCurrentSession().delete(entity);
        } catch (Exception e)
        {
            throw new DBException("删除数据失败", e);
        }
    }

    @Override
    public <T> T findById(String id, Class<T> cls)
    {
        return (T) getCurrentSession().get(cls, id);
    }

    @Override
    public <T> T findUniqueByProperty(String property, Object value, Class<T> cls)
    {
        Criterion criterion = Restrictions.eq(property, value);
        return (T) createCriteria(cls, criterion).uniqueResult();
    }

    @Override
    public <T> List<T> queryByCriteria(Class<T> cls, QueryBuilder queryBuilder, Order[] orders, int startIndex, int size)
    {
        Criteria criteria = getCurrentSession().createCriteria(cls);
        fillConditions(criteria, queryBuilder);
        if (orders != null && orders.length > 0)
        {
            for (Order order : orders)
            {
                if (order.getAlias() != null && order.getAlias().length() > 0 && !existAlias(criteria, order.getAlias()))
                {
                    criteria.createAlias(order.getAlias(), order.getAlias());
                }
                if (OrderType.ASC.equals(order.getType()))
                {
                    criteria.addOrder(org.hibernate.criterion.Order.asc(order.getName()));
                } else
                {
                    criteria.addOrder(org.hibernate.criterion.Order.desc(order.getName()));
                }
            }
        }
        if (size > 0)
        {
            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(size);
        }
        return criteria.list();
    }
    
    @SuppressWarnings("rawtypes")
    public List getEntities(String clazz, QueryBuilder queryBuilder, Order[] orders, int startIndex, int size){
        Criteria criteria;
        try {
            criteria = getCurrentSession().createCriteria(Class.forName(clazz));
            fillConditions(criteria, queryBuilder);
            if (orders != null && orders.length > 0)
            {
                for (Order order : orders)
                {
                    if (order.getAlias() != null && order.getAlias().length() > 0 && !existAlias(criteria, order.getAlias()))
                    {
                        criteria.createAlias(order.getAlias(), order.getAlias());
                    }
                    if (OrderType.ASC.equals(order.getType()))
                    {
                        criteria.addOrder(org.hibernate.criterion.Order.asc(order.getName()));
                    } else
                    {
                        criteria.addOrder(org.hibernate.criterion.Order.desc(order.getName()));
                    }
                }
            }
            if (size > 0)
            {
                criteria.setFirstResult(startIndex);
                criteria.setMaxResults(size);
            }
            return criteria.list();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countByCriteria(Class<?> cls, QueryBuilder queryBuilder)
    {
        Criteria criteria = getCurrentSession().createCriteria(cls);
        fillConditions(criteria, queryBuilder);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    @Override
    protected Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 填充数组参数
     * 
     * @param query
     *            SQLQuery
     * @param objs
     *            objs
     */
    private void fillArrayParams(SQLQuery query, Object[] objs)
    {
        for (int i = 0; i < objs.length; i++)
        {
            if (objs[i] == null)
            {
                continue;
            }
            if (objs[i] instanceof Integer)
            {
                query.setInteger(i, (Integer) objs[i]);
            } else if (objs[i] instanceof Float)
            {
                query.setFloat(i, (Float) objs[i]);
            } else if (objs[i] instanceof Double)
            {
                query.setDouble(i, (Double) objs[i]);
            } else if (objs[i] instanceof Date)
            {
                query.setDate(i, (Date) objs[i]);
            } else if (objs[i] instanceof Boolean)
            {
                query.setBoolean(i, (Boolean) objs[i]);
            } else
            {
                query.setString(i, objs[i].toString());
            }
        }
    }

    /**
     * 填充Map参数
     * 
     * @param query
     *            SQLQuery
     * @param params
     *            params
     */
    private void fillMapParams(SQLQuery query, Map<String, Object> params)
    {
        Object value = null;
        for (Entry<String, Object> entry : params.entrySet())
        {
            value = entry.getValue();
            if (value == null)
            {
                continue;
            }
            if (value instanceof Integer)
            {
                query.setInteger(entry.getKey(), (Integer) value);
            } else if (value instanceof Float)
            {
                query.setFloat(entry.getKey(), (Float) value);
            } else if (value instanceof Double)
            {
                query.setDouble(entry.getKey(), (Double) value);
            } else if (value instanceof Date)
            {
                query.setDate(entry.getKey(), (Date) value);
            } else if (value instanceof Boolean)
            {
                query.setBoolean(entry.getKey(), (Boolean) value);
            } else
            {
                query.setString(entry.getKey(), value.toString());
            }
        }
    }

    @Override
    public Map<String, Object> getObject(String sql, Object... objs)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        fillArrayParams(query, objs);
        List<Map<String, Object>> list = query.list();
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Map<String, Object> getObject(String sql, Map<String, Object> params)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        fillMapParams(query, params);
        List<Map<String, Object>> list = query.list();
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Map<String, Object> getObject(String sql, List<QueryCondition> conditions)
    {
        return getObject(sql, conditions, null, true);
    }

    @Override
    public List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, Object... objs)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        fillArrayParams(query, objs);
        if (size > 0)
        {
            query.setFirstResult(startIndex);
            query.setMaxResults(size);
        }
        return query.list();
    }

    @Override
    public List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, Map<String, Object> params)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        fillMapParams(query, params);
        if (size > 0)
        {
            query.setFirstResult(startIndex);
            query.setMaxResults(size);
        }
        return query.list();
    }

    @Override
    public List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, List<QueryCondition> conditions)
    {
        return getObjectList(sql, startIndex, size, conditions, null, true);
    }

    @Override
    public int executeSQL(String sql, Object... objs)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        fillArrayParams(query, objs);
        return query.executeUpdate();
    }

    @Override
    public int executeSQL(String sql, Map<String, Object> params)
    {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        fillMapParams(query, params);
        return query.executeUpdate();
    }

    @Override
    public Map<String, Object> getObject(String sql, List<QueryCondition> conditions, Order[] orders, boolean isWhereStart)
    {
        SQLQuery query = getSQLQuery(sql, new QueryBuilder(conditions), orders, isWhereStart);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (!CollectionUtils.isEmpty(conditions))
        {
            for (QueryCondition condition : conditions)
            {
                fillConditionValue(query, condition);
            }
        }
        List<Map<String, Object>> list = query.list();
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, List<QueryCondition> conditions, Order[] orders,
            boolean isWhereStart)
    {
        SQLQuery query = getSQLQuery(sql, new QueryBuilder(conditions), orders, isWhereStart);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (!CollectionUtils.isEmpty(conditions))
        {
            for (QueryCondition condition : conditions)
            {
                fillConditionValue(query, condition);
            }
        }
        if (size > 0)
        {
            query.setFirstResult(startIndex);
            query.setMaxResults(size);
        }
        return query.list();
    }

    @Override
    public void flush()
    {
        getCurrentSession().flush();
    }

    @Override
    public void merge(Object object)
    {
        getCurrentSession().merge(object);
    }

}