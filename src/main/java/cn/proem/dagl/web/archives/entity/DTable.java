package cn.proem.dagl.web.archives.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTable {
    // 字符类型
    public static final String VARCHAR2 = "VARCHAR2";
    public static final String VARCHAR = "VARCHAR";
    public static final String CHAR = "CHAR";
    
    // 数值
    public static final String NUMBER = "NUMBER";
    public static final String INT = "INT";
    public static final String BIGINT = "BIGINT";
    public static final String FLOAT = "FLOAT";
    public static final String DOUBLE = "DOUBLE";
    
    // 日期
    public static final String DATE = "DATE";
    // 时间
    public static final String TIMESTAMP = "TIMESTAMP";
    
    // 二进制
    public static final String BLOB = "BLOB";
    // 大文本
    public static final String CLOB = "CLOB";
    
    private List<DField> fields = new ArrayList<DField>();
    private Boolean isnew = true;
    
    private String name;
    private String dll;
    
    // 主键
    private List<String> primary;
    
    
    /**
     * 获得表主键
     * @return
     */
    public List<String> getPrimary() {
        return primary;
    }

    /**
     * 设置表主键
     * @param primary
     */
    public void setPrimary(List<String> primary) {
        this.primary = primary;
    }

    /**
     * 表对象构造器
     * @param name 表名
     */
    public DTable(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     */
    public void add(String fieldname, String type){
        this.add(fieldname, type, true);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param max 最大长度
     */
    public void add(String fieldname, String type, Integer max){
        this.add(fieldname, type, max, true);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param max 最大长度
     */
    public void add(String fieldname, String type, Integer precision, Integer dot){
        this.add(fieldname, type, precision, dot, true);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param nullable 是否为空
     */
    public void add(String fieldname, String type, Boolean nullable){
        DField field = new DField(fieldname);
        field.setType(type);
        field.setNullable(nullable);
        this.fields.add(field);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param max 最大长度
     * @param nullable 是否为空
     */
    public void add(String fieldname, String type,Integer max, Boolean nullable){
        DField field = new DField(fieldname);
        field.setType(type);
        field.setMaxlen(max);
        field.setNullable(nullable);
        this.fields.add(field);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param max 最大长度
     * @param nullable 是否为空
     * @param defu 默认值
     */
    public void add(String fieldname, String type,Integer max, Boolean nullable, String defu){
        DField field = new DField(fieldname);
        field.setType(type);
        field.setMaxlen(max);
        field.setNullable(nullable);
        field.setDefu(defu);
        this.fields.add(field);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param precision 精度
     * @param dot 小数位数
     * @param nullable 是否为空
     */
    public void add(String fieldname, String type, Integer precision, Integer dot, Boolean nullable){
        DField field = new DField(fieldname);
        field.setType(type);
        field.setPrecision(precision);
        field.setDot(dot);
        field.setNullable(nullable);
        this.fields.add(field);
    }
    
    /**
     * 添加字段定义
     * @param fieldname 字段名
     * @param type 字段类型
     * @param precision 精度
     * @param dot 小数位数
     * @param nullable 是否为空
     * @param defu 默认值
     */
    public void add(String fieldname, String type, Integer precision, Integer dot, Boolean nullable, BigDecimal defu){
        DField field = new DField(fieldname);
        field.setType(type);
        field.setPrecision(precision);
        field.setDot(dot);
        field.setNullable(nullable);
        field.setDefu(defu.toString());
        this.fields.add(field);
    }

    /**
     * 获得表定义语言
     * @return
     */
    public String getDDL(){
        if(isnew || dll == null){
            this.isnew = false;
            StringBuffer sql = new StringBuffer();
            sql.append("CREATE TABLE ");
            sql.append(name);
            sql.append(" (");
            for(int i = 0 ; i < fields.size() ; i++){
                if(i == 0){
                    sql.append(getFieldDefine(fields.get(i)));
                }else{
                    sql.append(",");
                    sql.append(getFieldDefine(fields.get(i)));
                }
            }
            sql.append(this.getPrimaryKey(primary));
            sql.append(")");
            this.dll = sql.toString();
        }
        return this.dll;
    }
    
    /**
     * 获得字段SQL定义
     * @param field 字段名
     * @return
     */
    public String getFieldDefine(DField field){
        if(VARCHAR2.equals(field.getType())){
//            return String.format("%s varchar2(%d) %s"
//                                ,field.getName()
//                                ,field.getMaxlen()==null?255:field.getMaxlen()
//                                ,field.getNullable()?"":"NOT NULL");
            return String.format("%s varchar2(%d) %s"
                    ,field.getName()
                    ,field.getMaxlen()==null?255:field.getMaxlen()
                    ,"");
        }else if(INT.equals(field.getType())){
//            return String.format("%s int(%d)"
//                                ,field.getName() 
//                                ,field.getMaxlen()==null?255:field.getMaxlen()
//                                ,field.getNullable()?"":"NOT NULL");
            return String.format("%s int(%d) %s"
                    ,field.getName() 
                    ,field.getMaxlen()==null?255:field.getMaxlen()
                    ,"");
            
        }else if(BIGINT.equals(field.getType())){
/*            return String.format("%s bigint(%d)"
                                ,field.getName()
                                ,field.getMaxlen()==null?255:field.getMaxlen()
                                ,field.getNullable()?"":"NOT NULL");*/
            return String.format("%s bigint(%d) %s"
                    ,field.getName()
                    ,field.getMaxlen()==null?255:field.getMaxlen()
                    ,"");
        }else if(CHAR.equals(field.getType())){
            return String.format("%s char(%d) %s", field.getName()
                                ,field.getMaxlen()==null?255:field.getMaxlen()
                                ,"");
        }
        return field.getType();
    }
    
    public String getPrimaryKey(List<String> primary){
        StringBuilder pristr = new StringBuilder();
        for(int i = 0; i < primary.size(); i++){
            if(i==0){
                pristr.append(",CONSTRAINT TB_PK_");
                pristr.append(this.name);
                pristr.append(" PRIMARY KEY(");
                pristr.append(primary.get(i));
            }else{
                pristr.append(",");
                pristr.append(primary.get(i));
            }
        }
        if(pristr.length() > 0){
            pristr.append(")");
            return pristr.toString();
        }else{
            return "";
        }
    }
    /**
     * 修改字段定义
     * @param fieldname 字段名
     * @param field
     */
    public void setField(String fieldname, DField field){
        int idx = getFieldIdx(fieldname);
        if(idx > 0){            
            fields.set(idx, field);
        }
    }
    
    /**
     * 查询字段名对应字段序号
     * @param fieldname 字段名
     * @return
     */
    private int getFieldIdx(String fieldname){
        for(int i = 0 ;i < fields.size(); i++){
            if(fields.get(i).getName().equals(fieldname)){
                return i;
            }
        }
        return -1;
    }
    
    public DEntity createEntity(){
        Map<String, Object> vals = new HashMap<String, Object>();
        for(DField field : fields){
            vals.put(field.getName(), null);
        }
        return new DEntity(this.name, this.primary, vals);
    }
}
