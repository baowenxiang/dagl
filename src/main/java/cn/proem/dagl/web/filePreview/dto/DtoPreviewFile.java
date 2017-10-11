package cn.proem.dagl.web.filePreview.dto;

public class DtoPreviewFile {
    // 文件名
    private String name;
    // 文件服务器位置
    private String cache;
    // 文件类型
    private String type;  
    // 相关文档记录
    private String record;
    
    /**
     * 获得文件名 
     * @return 文件名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置文件名
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获得文件地址
     * @return 文件地址
     */
    public String getCache() {
        return cache;
    }
    
    /**
     * 设置文件地址
     * @param type 文件地址
     */
    public void setCache(String cache) {
        this.cache = cache;
    }
    
    /**
     * 获得文件类型
     * @param type 文件类型
     */
    public String getType() {
        return type;
    }
    
    /**
     * 设置文件类型
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * 获得档案/资料/目录 id
     * @return 相关文档ID
     */
    public String getRecord() {
        return record;
    }
    
    /**
     * 设置档案/资料/目录 id
     * @param record 相关文档id
     */
    public void setRecord(String record) {
        this.record = record;
    }

}
