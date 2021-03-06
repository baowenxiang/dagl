package cn.proem.dagl.web.archives.entity;

import java.util.List;
import java.util.Map;

import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;

public class DEntity implements BaseEntityInf{

    // 存储对象值
    private Map<String, Object> vals;
    // 字段列表
    private List<String> fieldnames;
    // 记录对象主键
    private List<String> primary;
    // 记录对象查询条件
    private Map<String, Object> query;
    // 记录表名
    private String table;
    
    private static final String stringFormat = "'%s'";
    private static final String numFormat = "'%d'";
    private static final String stringlikeFormat = "'%%%s%%'";
    private static final String none = "null";

    /**
     * 查询结果
     * @return
     */
    public Map<String, Object> getVals() {
        return vals;
    }

    /**
     * 修改表名
     * @param table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * 获得表名
     * @return
     */
    public String getTable() {
        return table;
    }

    /**
     * 设置查询结果
     * @param vals
     */
    public void setVals(Map<String, Object> vals) {
        this.vals = vals;
    }

    /**
     * 记录对象构造器
     * @param table 表名
     * @param primary 主键
     * @param vals 记录值
     */
    public DEntity(String table, List<String> primary, Map<String, Object> vals){
        this.table = table;
        this.primary = primary;
        this.vals = vals;
    }
    
    /**
     * 设置字段值
     */
    @Override
    public void set(String colname, Object val) {
        vals.put(colname.toUpperCase(), val);
    }
    
    /**
     * 获得字段值
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String colname) {
        return (T) vals.get(colname.toUpperCase());
    }
    
    public String getValStr(){
        StringBuilder values = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        fields.append(" (");
        int cnt = 0;
        if(this.fieldnames == null){
            for(Map.Entry<String, Object> entry: vals.entrySet()){
                if(cnt == 0) {
                    fields.append(entry.getKey());
                    values.append(getFieldStr(entry.getKey()));
                }else{
                    values.append(", ");
                    values.append(getFieldStr(entry.getKey()));
                    fields.append(", ");
                    fields.append(entry.getKey());
                }
                cnt ++;
            }
        }else{
            for(String fieldname : fieldnames){
                if(cnt == 0) {
                    fields.append(fieldname.toUpperCase());
                    values.append(getFieldStr(fieldname.toUpperCase()));
                }else{
                    values.append(", ");
                    values.append(getFieldStr(fieldname.toUpperCase()));
                    fields.append(", ");
                    fields.append(fieldname.toUpperCase());
                }
                cnt ++;
            }
        }
        
        fields.append(") VALUES (");
        fields.append(values);
        fields.append(")");
        return fields.toString();
    }
    
    /**
     * 返回字段字符串
     * @param fieldname 字段名
     * @return
     */
    private String getFieldStr(String fieldname){
        if(vals.get(fieldname)==null){
            return none;
        }else if(vals.get(fieldname) instanceof String){
            return String.format(stringFormat, (String)vals.get(fieldname)); 
        }else if(vals.get(fieldname) instanceof Integer){
            return String.format(numFormat, (Integer)vals.get(fieldname));
        }else{
            return String.format(stringFormat, vals.get(fieldname).toString());
        }
    }
    
    public String getWhere(){
        StringBuilder where = new StringBuilder();
        where.append(" WHERE ");
        if(primary == null){
            return "";
        }else{
            for(int i=0; i<primary.size() ; i++){
                String fieldname = primary.get(i);
                if(i==0){
                    where.append(primary.get(i));
                }else{
                    where.append(" and ");
                    where.append(primary.get(i));
                }
                if(vals.get(fieldname)==null){
                    where.append(" is null");
                }else if(vals.get(fieldname) instanceof String){
                    where.append("=");
                    where.append(String.format(stringFormat, (String)vals.get(fieldname))); 
                }else if(vals.get(fieldname) instanceof Integer){
                    where.append("=");
                    where.append(String.format(numFormat, (Integer)vals.get(fieldname)));
                }else{
                    where.append("=");
                    where.append(String.format(stringFormat, vals.get(fieldname).toString()));
                }
            }
        }
        return where.toString();
    }
    
    /**
     * @Description 活动查询条件
     * @MethodName getQuery
     * @author bao
     * @date 2017年5月16日
     * @return String
     */
    public String getQuery(){
        StringBuilder where = new StringBuilder();
        where.append(" WHERE ");
        if(query == null){
            return "";
        }else{
        	int i = 0;
            for(String key: query.keySet()){
                String fieldname = key;
                if(i==0){
                    where.append(fieldname);
                }else{
                    where.append(" and ");
                    where.append(fieldname);
                }
                if(query.get(fieldname)==null){
                    where.append(" is null");
                }else if(query.get(fieldname) instanceof String){
                    where.append("=");
                    where.append(String.format(stringFormat, (String)query.get(fieldname))); 
                }else if(query.get(fieldname) instanceof Integer){
                    where.append("=");
                    where.append(String.format(numFormat, (Integer)query.get(fieldname)));
                }else{
                    where.append("=");
                    where.append(String.format(stringFormat, query.get(fieldname).toString()));
                }
                i++;
            }
        }
        return where.toString();
    }
    
    /**
     * @Description 活动查询条件
     * @MethodName getLikeQuery
     * @author bao
     * @date 2017年5月16日
     * @return String
     */
    public String getLikeQuery(){
        StringBuilder where = new StringBuilder();
        where.append(" WHERE ");
        if(query == null){
            return "";
        }else{
            int i = 0;
            for(String key: query.keySet()){
                String fieldname = key;
                if(i==0){
                    where.append(fieldname);
                }else{
                    where.append(" and ");
                    where.append(fieldname);
                }
                if(query.get(fieldname)==null){
                    where.append(" is null");
                }else if(query.get(fieldname) instanceof String){
                    where.append(" like ");
                    where.append(String.format(stringlikeFormat, (String)query.get(fieldname))); 
                }else if(query.get(fieldname) instanceof Integer){
                    where.append("=");
                    where.append(String.format(numFormat, (Integer)query.get(fieldname)));
                }else{
                    where.append("=");
                    where.append(String.format(stringFormat, query.get(fieldname).toString()));
                }
                i++;
            }
        }
        return where.toString();
    }
    
    /**
     * 获得主键
     * @return
     */
    public List<String> getPrimary() {
        return primary;
    }

    public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	/**
     * 设置主键
     * @param primary
     */
    public void setPrimary(List<String> primary) {
        this.primary = primary;
    }
    
    /**
     * 获得插入语句语句
     * @return 插入语句
     */
    public String save(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(this.table);
        sql.append(this.getValStr());
        return sql.toString();
    }
    
    /**
     * 获得删除语句
     * @return 删除语句
     */
    public String del(){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(this.table);
        sql.append(this.getWhere());
        return sql.toString();
    }

    /**
     * 对象字段列表
     * @return
     */
    public List<String> getFields() {
        return fieldnames;
    }
    
    /**
     * 对象字段列表
     * @param fieldnames
     */
    public void setFields(List<String> fieldnames) {
        this.fieldnames = fieldnames;
    }
}
