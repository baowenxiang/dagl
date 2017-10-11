package cn.proem.dagl.web.archives.entity;

public class DField {
    /**
     * 记录唯一编号
     */
    public static final String UUID = "uuid";
    /**
     * 挂接原文编号
     */
    public static final String DATAID = "dataid";
    /**
     * 案件级文件编号
     */
    public static final String FILEID = "fileid";
    /**
     * 档案状态
     */
    public static final String ISARCHIVE = "isarchive";
    /**
     * 逻辑删除
     */
    public static final String DELFLAG = "delflag";
    /**
     * 现在所属部门
     */
    public static final String NOWCAMP = "companynum";
    /**
     * 以往所属部门
     */
    public static final String OLDCAMP = "evercompanynum";
    /**
     * 新建人员
     */
    public static final String CREATEUSER = "createuser";
    /**
     * 更新人员
     */
    public static final String UPDATEUSER = "updateuser";
    /**
     * 修改时间
     */
    public static final String UPDATETIME = "updatetime";
    /**
     * 新建时间
     */
    public static final String CREATETIME = "createtime";
    /**
     * 是否划控
     */
    public static final String HKKZ = "hkkz";
    /**
     * 是否鉴定
     */
    public static final String SFJD = "sfjd";
    
    
    // 一般默认值
    public static final String ZERO = "0";
    public static final String BLANK = "";
    
    // 字段名
    private String name;
    // 字段类型
    private String type;
    // 字段最大长度
    private Integer maxlen;
    // 精度
    private Integer precision;
    // 小数位数
    private int dot;
    // 字段是否为空（默认可以为空）
    private Boolean nullable = true;
    // 默认值
    private String defu;

    /**
     * 字段定义构造器
     * @param name 字段名
     */
    public DField(String name){
        this.name = name.toUpperCase();
    }
    
    /**
     * 默认值
     * @return
     */
    public String getDefu() {
        return defu;
    }

     /**
      * 默认值
      * @param defu
      */
    public void setDefu(String defu) {
        this.defu = defu;
    }

    /**
     * 字段名
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * 字段名
     * @param name
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
    }
    
    /**
     * 字段类型
     * @return
     */
    public String getType() {
        return type;
    }
    
    /**
     * 字段类型
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * 字段类型（同时设置最大长度）
     * @param type
     */
    public void setType(String type, Integer maxlen) {
        this.type = type;
        this.maxlen = maxlen;
    }
    
    /**
     * 字段类型（同时设置最大长度，是否为空）
     * @param type
     */
    public void setType(String type, Integer maxlen, Boolean nullable) {
        this.type = type;
        this.maxlen = maxlen;
        this.nullable = nullable;
    }
    
    /**
     * 设置字段类型（同时设置精度和小数位数）
     * @param type
     * @param precision
     * @param dot
     */
    public void setType(String type, Integer precision, Integer dot){
        this.type = type;
        this.precision = precision;
        this.dot = dot;
    }
    
    /**
     * 设置字段类型（同时设置精度和小数位数，是否为空）
     * @param type
     * @param precision
     * @param dot
     * @param nullable
     */
    public void setType(String type, Integer precision, Integer dot, Boolean nullable){
        this.type = type;
        this.precision = precision;
        this.dot = dot;
        this.nullable = nullable;
    }
    
    /**
     * 最大长度
     * @return
     */
    public Integer getMaxlen() {
        return maxlen;
    }
    
    /**
     * 最大长度
     * @param maxlen
     */
    public void setMaxlen(int maxlen) {
        this.maxlen = maxlen;
    }
    
    /**
     * 精度
     * @return
     */
    public int getPrecision() {
        return precision;
    }
    
    /**
     * 精度
     * @param precision
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }
    
    /**
     * 小数位数
     * @return
     */
    public int getDot() {
        return dot;
    }
    
    /**
     * 小数位数
     * @param dot
     */
    public void setDot(int dot) {
        this.dot = dot;
    }
    
    /**
     * 是否为空
     * @return
     */
    public Boolean getNullable() {
        return nullable;
    }
    
    /**
     * 是否为空
     * @param nullable
     */
    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }
    
    
}
