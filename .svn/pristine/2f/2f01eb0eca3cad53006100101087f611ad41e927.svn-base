package cn.proem.dagl.web.common.entity;

import cn.proem.dagl.web.table.entity.inf.Tag;

public class SimpleInput implements Tag {

    private String html = "<input name='%s' type='text' class='%s'>";
    // 字段名
    private String name;
    // 属性
    private String clz;

    @Override
    public String html() {
        return String.format(html, this.name, this.clz);
    }

    @Override
    public void setVal(String val) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setTitle(String title) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHtml(String html) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHide(boolean tf) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setIsEditable(boolean tf) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRequired(boolean tf) {
        // TODO Auto-generated method stub

    }

    @Override
    public void set(String name, Object obj) {
        if("class".equals(name)){
            this.clz = (String) obj;
        }
    }
}
