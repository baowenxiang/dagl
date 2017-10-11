package cn.proem.dagl.web.common.entity;

import cn.proem.dagl.web.table.entity.inf.Tag;

public class Option implements Tag{
    private String title;
    private String val;
    private Boolean selected;
    private String html = "<option %s %s>%s</option>";
    
    public Option(String title, String val){
        this.title = title;
        this.val =val;
        this.selected = false;
    }
    
    public Option(String title, String val, Boolean selected){
        this.title = title;
        this.val = val;
        this.selected = selected;
    }

    @Override
    public String html() {
       String isSelected = "";
        if(val!=null){
           val = "value='" + val + "'";
       }
       if(selected){
           isSelected = "selected";
       }
       return String.format(html, val, isSelected, title);
    }
    @Override
    public void setVal(String val) {
        this.val = val;
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
    public void set(String name, Object selected) {
        if("selected".equals(name)){
            selected = (Boolean)selected;
        }
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }  
    
}
