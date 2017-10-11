package cn.proem.dagl.web.common.entity;

import cn.proem.dagl.web.table.entity.inf.Tag;

/**
 * 快速查询条件
 * @author tangcc
 */
public class SearchInput implements Tag {
    private String html = "<div class='form-group'>\n"
                             + "    <label class='form-label'><small>%s</small></label>\n" //标题名
                             + "    %s\n"   //输入控件
                             + "</div>";
    
    private Tag tag;
    private String title;
    
    @Override
    public String html() {
       return String.format(this.html, this.title, this.tag.html());
    }

    @Override
    public void setVal(String val) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setHtml(String html) {
     // TODO Auto-generated method stub

    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        if("Input".equals(name)){
            this.tag = (Tag) obj;
        }
    }

}
