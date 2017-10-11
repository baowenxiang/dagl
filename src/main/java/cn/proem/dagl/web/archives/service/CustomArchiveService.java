package cn.proem.dagl.web.archives.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.archives.entity.DOrder;
import cn.proem.dagl.web.archives.entity.DTable;
import cn.proem.dagl.web.archives.entity.DTableField;
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;


/**
 * 自定义表单服务
 */
public interface CustomArchiveService
{
      /**
       * 创建表
       * @param tablename
       */
      void createAchive(String tablename);
      
      /**
       * 创建表
       * @param tablename
       */
      void alterAchive(String tablename) throws ServiceException;
      
      /**
       * 创建表
       * @param tablename
       * @throws ServiceException 
       */
      void alterAchive(DTable table) throws ServiceException;
      
      /**
       * 创建表
       * @param table 表对象
       * @return
       */
      @Transactional
      @LogService(description = "根据自定义档案，新建档案表")
      void createTable(DTable table);

      /**
       * 删除表
       * @param tablename 表名
       */
      void dropTable(String tablename);
      
      /**
       * 重新建表
       * @param table 表对象
       */
      void dropAndCreateTable(DTable table);
      
      /**
       * 获得新记录对象
       * @param tablename 表名
       * @return 
       */
      BaseEntityInf getEntity(String tablename);
      
      /**
       * 获得记录对象
       * @param tablename 表名
       * @param id 记录uuid
     * @return 
       */
      BaseEntityInf getEntity(String tablename, String id);
      
      /**
       * 获得全部记录对象
       * @param tablename 表名
       * @return 
       */
      List<BaseEntityInf> getAllEntities(String tablename);
      
      /**
       * 获得全部记录对象
       * @param tablename 表名
       * @return 
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, Map<String, Object> conditions);
      
      /**
       * 获得全部记录对象
       * @param tablename 表名
       * @return 
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, Map<String, Object> conditions, boolean like);
      
      /**
       * 获得全部记录对象并排序
       * @param tablename
       * @param conditions
       * @param orders
       * @return
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, Map<String, Object> conditions, List<DOrder> orders);
      
      /**
       * 获得全部记录对象并排序
       * @param tablename
       * @param conditions
       * @param order
       * @return
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, Map<String, Object> conditions, DOrder order);
      
      /**
       * 条件查询
       * @param tablename
       * @param conditions
       * @return
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, String conditions);
      
      /**
       * 条件查询并排序
       * @param tablename
       * @param conditions
       * @param orders
       * @return
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, String conditions, List<DOrder> orders);
      
      /**
       * 条件查询并排序
       * @param tablename
       * @param conditions
       * @param order
       * @return
       */
      List<BaseEntityInf> getEntitiesByConditions(String tablename, String conditions, DOrder order);
      
      /**
       * 获得全部记录对象
       * @param tablename 表名
       * @return 
       */
      BaseEntityInf getEntityByConditions(String tablename, Map<String, Object> conditions);
      
      /**
       * 保存记录
       * @param entity
     * @return 
       */
      String save(BaseEntityInf entity) throws ServiceException;
      
      /**
       * 保存记录
       * @param entity
       */
      void update(BaseEntityInf entity) throws ServiceException;
      
      /**
       * 删除记录
       * @param entity
       */
      void del(BaseEntityInf entity) throws ServiceException;

      /**
       * 查询表名列表
       * @param queryBuilder
       * @param nowPage
       * @param pageSize
       * @return
       */
      public DataGrid<DTableName> getTableNameDateGrid(
            QueryBuilder queryBuilder, int nowPage, int pageSize);
      /**
       * 新增或修改表
       * @param tableName
       * @throws ServiceException
       */
      public void saveOrUpdateDTableName(DTableName tableName) throws ServiceException;
      /**
       * 逻辑删除
       * @param id
       * @throws ServiceException
       */
      public void deleteDTableNameById(String id) throws ServiceException;
      
      /**
       * 查询字段名列表
       * @param queryBuilder
       * @param nowPage
       * @param pageSize
       * @return
       */
      public DataGrid<DTableField> getTableFieldDateGrid(
                QueryBuilder queryBuilder, int nowPage, int pageSize);
      /**
       * 新增或修改表字段
       * @param tableName
       * @throws ServiceException
       */
      public void saveOrUpdateDTableField(DTableField tableField) throws ServiceException;
      /**
       * 逻辑删除表字段
       * @param id
       * @throws ServiceException
       */
      public void deleteDTableFieldById(String id) throws ServiceException;
      
      /**
       * 获得表名
       * @param tableName
       * @return
       */
      public DTableName getDTableNameByTableName(String tableName);

      /**
       * 保存或者更新
       * @param entity
       * @return
       * @throws ServiceException
       */
      void saveOrUpdate(BaseEntityInf entity) throws ServiceException;
      
      /**
       * 数据库是否存在表
       * @param tablename
       * @return
       * @throws ServiceException
       */
      Boolean isExists(String tablename) throws ServiceException;
      
      /**
       * @Description 分页查询
       * @MethodName getEntitiesByPaging
       * @author bao
       * @date 2017年5月24日
       * @param tablename
       * @param coditions
       * @param startRecord
       * @param pageSize
       * @return List<BaseEntityInf>
       */
      public List<BaseEntityInf> getEntitiesByPaging(String tablename,String conditions, Integer startRecord,Integer pageSize);
      
      /**
       * @Description 分页
       * @MethodName getEntitiesByPaging
       * @author bao
       * @date 2017年5月25日
       * @param tablename
       * @param conditions
       * @param startRecord
       * @param pageSize
       * @return List<BaseEntityInf>
       */
      public List<BaseEntityInf> getEntitiesByPaging(String tablename,Map<String,Object> conditions, Integer startRecord,Integer pageSize);
      
    /**
     * 
     * @Method: getEntitiesByPaging 
     * @Description: 普通条件加负责条件
     * @param tablename
     * @param conditions and条件
     * @param othersql 拼接其他条件
     * @param startRecord
     * @param pageSize
     * @return
     */
      public List<BaseEntityInf> getEntitiesByPaging(String tablename,Map<String,Object> conditions,String othersql, Integer startRecord,Integer pageSize);
      
      /**
       * 获得表字段
       * @param tablename
       * @return
       */
      List<BaseEntityInf> getFields(String tablename);

    List<BaseEntityInf> getLikeEntitiesByPaging(String tablename, Map<String, Object> conditions, String othersql,
            Integer startRecord, Integer pageSize);
      
}