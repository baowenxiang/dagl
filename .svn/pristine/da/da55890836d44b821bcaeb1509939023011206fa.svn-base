package cn.proem.dagl.web.table.dao;

import java.util.List;
import java.util.Map;

import cn.proem.core.model.Order;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;

/**
 * 通用Dao
 * 
 * @author Denny
 */
public interface GeneralServiceDao
{

    /**
     * 保存
     * 
     * @param entity
     *            实体
     * @return 主键
     */
    String save(Object entity);

    /**
     * 更新
     * 
     * @param entity
     *            实体
     */
    void update(Object entity);

    /**
     * 保存或更新实体
     * 
     * @param entity
     *            实体对象
     */
    void saveOrUpdate(Object entity);

    /**
     * 保存或更新
     * 
     * @param entity
     *            实体
     */
    void delete(Object entity);

    /**
     * 根据ID删除
     * 
     * @param id
     *            id
     * @param cls
     *            实体类型
     */
    void deleteById(String id, Class<?> cls);

    /**
     * 根据主键获取对象
     * 
     * @param <T>
     *            泛型
     * @param id
     *            主键
     * @param cls
     *            实体类型
     * @return T
     */
    <T> T findById(String id, Class<T> cls);

    /**
     * 根据唯一字段获取对象
     * 
     * @param <T>
     *            泛型
     * @param property
     *            唯一字段名称
     * @param value
     *            唯一字段值
     * @param cls
     *            实体类型
     * @return T
     */
    <T> T findUniqueByProperty(String property, Object value, Class<T> cls);

    /**
     * 查询列表
     * 
     * @param <T>
     *            泛型
     * @param cls
     *            实体类型
     * @param queryBuilder
     *            查询条件
     * @param orders
     *            排序
     * @param startIndex
     *            开始下标
     * @param size
     *            查询数量
     * @return 分页查询结果集
     */
    <T> List<T> queryByCriteria(Class<T> cls, QueryBuilder queryBuilder, Order[] orders, int startIndex, int size);

    /**
     * 统计数量
     * 
     * @param cls
     *            实体类型
     * @param queryBuilder
     *            查询条件
     * @return 数据记录数
     */
    int countByCriteria(Class<?> cls, QueryBuilder queryBuilder);

    /**
     * 获取单个对象
     * 
     * @param sql
     *            自定义SQL语句
     * @param objs
     *            查询参数
     * @return Map<String,Object> 根据select条件封闭的map对象
     */
    Map<String, Object> getObject(String sql, Object... objs);

    /**
     * 获取单个对象
     * 
     * @param sql
     *            自定义SQL语句
     * @param params
     *            查询参数
     * @return Map<String,Object> 根据select条件封闭的map对象
     */
    Map<String, Object> getObject(String sql, Map<String, Object> params);

    /**
     * 获取单个对象
     * 
     * @param sql
     *            自定义SQL语句
     * @param conditions
     *            查询参数
     * @return Map<String,Object> 根据select条件封闭的map对象
     */
    Map<String, Object> getObject(String sql, List<QueryCondition> conditions);

    /**
     * 获取单个对象
     * 
     * @param sql
     *            查询SQL语句
     * @param conditions
     *            查询条件
     * @param orders
     *            排序
     * @param isWhereStart
     *            条件是否从where开始（false:从AND开始，否则WHERE开始）
     * @return Map<String,Object>
     */
    Map<String, Object> getObject(String sql, List<QueryCondition> conditions, Order[] orders, boolean isWhereStart);

    /**
     * 获取对象集合
     * 
     * @param sql
     *            自定义SQL语句
     * @param startIndex
     *            查询开始下标
     * @param size
     *            获取数据记录数
     * @param objs
     *            查询参数
     * @return List<Map<String,Object>> 根据select条件封闭的map对象集合
     */
    List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, Object... objs);

    /**
     * 获取对象集合
     * 
     * @param sql
     *            自定义SQL语句
     * @param startIndex
     *            查询开始下标
     * @param size
     *            获取数据记录数
     * @param params
     *            查询参数
     * @return List<Map<String,Object>> 根据select条件封闭的map对象集合
     */
    List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, Map<String, Object> params);

    /**
     * 获取对象集合
     * 
     * @param sql
     *            自定义SQL语句
     * @param startIndex
     *            查询开始下标
     * @param size
     *            获取数据记录数
     * @param conditions
     *            查询参数
     * @return List<Map<String,Object>> 根据select条件封闭的map对象集合
     */
    List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, List<QueryCondition> conditions);

    /**
     * 获取对象集合
     * 
     * @param sql
     *            查询SQL语句
     * @param conditions
     *            查询条件
     * @param startIndex
     *            查询开始下标
     * @param size
     *            获取数据记录数
     * @param orders
     *            排序
     * @param isWhereStart
     *            条件是否从where开始（false:从AND开始，否则WHERE开始）
     * @return Map<String,Object>
     */
    List<Map<String, Object>> getObjectList(String sql, int startIndex, int size, List<QueryCondition> conditions, Order[] orders,
            boolean isWhereStart);

    /**
     * 执行SQL语句
     * 
     * @param sql
     *            SQL语句
     * @param objs
     *            参数
     * @return int 操作成功记录数
     */
    int executeSQL(String sql, Object... objs);

    /**
     * 执行SQL语句
     * 
     * @param sql
     *            SQL语句
     * @param params
     *            参数
     * @return int 操作成功记录数
     */
    int executeSQL(String sql, Map<String, Object> params);

    /**
     * 强制提交
     */
    void flush();

    /**
     * 合并对象
     * 
     * @param object
     *            对象
     */
    void merge(Object object);
    
    
    @SuppressWarnings("rawtypes")
    List getEntities(String clazz, QueryBuilder queryBuilder, Order[] orders, int startIndex, int size);

}