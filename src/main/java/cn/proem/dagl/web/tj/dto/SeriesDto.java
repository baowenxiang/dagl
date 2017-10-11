package cn.proem.dagl.web.tj.dto;

import java.util.List;

public class SeriesDto {
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @return the stack
     */
    public String getStack() {
        return stack;
    }
    /**
     * @return the data
     */
    public List<Object> getData() {
        return data;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @param stack the stack to set
     */
    public void setStack(String stack) {
        this.stack = stack;
    }
    /**
     * @param data the data to set
     */
    public void setData(List<Object> data) {
        this.data = data;
    }
    String name;
    String type;
    String stack;
    List<Object> data;
}
