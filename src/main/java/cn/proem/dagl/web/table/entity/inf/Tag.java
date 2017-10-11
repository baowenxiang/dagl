package cn.proem.dagl.web.table.entity.inf;

public interface Tag {
    
    /**
     * 返回页面
     * @return
     */
    String html();
    
    /**
     * 设置值
     * @param val
     */
    void setVal(String val);
    
    /**
     * 控件显示名
     * @param val
     */
    void setTitle(String title);
    
    /**
     * 设置标签基本模板
     * @param html
     */
    void setHtml(String html);
    
    /**
     * 设置标签name
     * @param html
     */
    void setName(String name);
    /**
     * @MethodName setHide
     * @Description 设置是否可见
     * @author chenxiaofen
     * @date 2017年5月17日
     * @param tf
     */
    void setHide(boolean tf);
    /**
     * @MethodName setIsEditable
     * @Description 设置是否可编辑
     * @author chenxiaofen
     * @date 2017年5月17日
     * @param tf
     */
    void setIsEditable(boolean tf);
    /**
     * @MethodName setRequired
     * @Description 设置时候
     * @author chenxiaofen
     * @date 2017年5月17日
     * @param tf
     */
    void setRequired(boolean tf);
    
    /**
     * 设置特定属性
     * @param name
     * @param obj
     */
    void set(String name, Object obj);
    
}
