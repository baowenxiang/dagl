package cn.proem.dagl.web.common.entity;

import java.util.List;
import java.util.Map;

import cn.proem.dagl.web.table.entity.inf.Tag;

public class SimpleSelect implements Tag {
    
    private String html = "<div class='form-group'>\n"
                        + "    <select name='%s' class='%s'>\n"
                        // + "        <option>Alaska</option>"
                        + "%s"
                        + "    </select>\n"
                        + "</div>";
    private String name;
    private String clz;
    private List<Option> options;
    
    @Override
    public String html() {
        StringBuilder ops = new StringBuilder();
        if(options != null){
            for(Option option: options){
                ops.append(option.html());
            }
            return String.format(html, name, clz, ops.toString());
        }else{
            return String.format(html, name, clz, "<option value=''></option>");
        }
        
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

    @SuppressWarnings("unchecked")
    @Override
    public void set(String name, Object obj) {
        // TODO Auto-generated method stub
        if("options".equals(name)){
            this.options = (List<Option>) obj;
        }else if("class".equals(name)){
            this.clz = (String) obj;
        }
    }

}
