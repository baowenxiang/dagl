package cn.proem.dagl.web.scheduled.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * @ClassName ScheduledTask
 * @Description 定时任务配置
 * @author poole
 * @date 2017年6月5日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_scheduledtasks")
public class ScheduledTask extends MappedEntityModel{
    /**
     * 任务名
     */
    @Column(length=30)
    private String taskname;
    
    /**
     * 执行时间
     */
    @Column(length=12)
    private String executetime;
    
    /**
     * 任务地址
     */
    @Column(length=255)
    private String url;
    
    /**
     * 上次执行状态
     */
    @Column(length=10)
    private String state;

    /**
     * @return 任务名
     */
    public String getTaskname() {
        return taskname;
    }

    /**
     * @return 任务执行时间
     */
    public String getExecutetime() {
        return executetime;
    }

    /**
     * @return 任务地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return 上次执行状态
     */
    public String getState() {
        return state;
    }

    /**
     * @param 任务名
     */
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    /**
     * @param 任务执行时间
     */
    public void setExecutetime(String executetime) {
        this.executetime = executetime;
    }

    /**
     * @param 任务地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param 上次任务状态
     */
    public void setState(String state) {
        this.state = state;
    }
    
    
    
    
}
