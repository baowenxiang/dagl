package cn.proem.suw.web.common.dto;

public class DtoStatis {
    // 实体名
    private String name;
    // 类型名
    private String staticName;
    // 统计数量
    private String num;
    
    /**
     * 实体名
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * 类型名
     * @return
     */
    public String getStaticName() {
        return staticName;
    }
    
    /**
     * 统计数量
     * @return
     */
    public String getNum() {
        return num;
    }
    
    /**
     * 实体名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 类型名
     * @param staticName
     */
    public void setStaticName(String staticName) {
        this.staticName = staticName;
    }
    
    /**
     * 统计数量
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }
}
