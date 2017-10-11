package cn.proem.dagl.web.archives.entity;

public class DOrder {
    // 降序
    public static final String DESC = "desc";
    // 升序
    public static final String ASC = "asc";

    private String fieldname;
    private String order;
    
    /**
     * 
     * @param filename
     */
    public DOrder(String filename){
        this.fieldname = filename;
        this.order = "";
    }
    /**
     * 排序对象构造器
     * @param filename 字段名
     * @param order 排序方式
     */
    public DOrder(String filename, String order){
        this.fieldname = filename;
        this.order = order;
    }
    /**
     * 字段名
     * @return
     */
    public String getFieldname() {
        return fieldname;
    }
    
    /**
     * 字段名
     * @param fieldname
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }
    
    /**
     * 排序方式
     * @return
     */
    public String getOrder() {
        return order;
    }
    
    /**
     * 排序方式
     * @param order
     */
    public void setOrder(String order) {
        this.order = order;
    }
    
    public String order(){
        return String.format("%s %s", this.fieldname, this.order);
    }
    
}
