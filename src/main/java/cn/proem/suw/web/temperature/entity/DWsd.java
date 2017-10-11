package cn.proem.suw.web.temperature.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 
 * @ClassName DWsd
 * @Description 温湿度记录表
 * @author poole
 * @date 2017年6月1日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "man_dabg_wsd")
public class DWsd {
    
    /**
     * 温度ID
     */
    @Id
    @Column(length = 30 ,nullable = false)
    private String id;
    
    /**
     * 记录日期
     */
    @Column(nullable = false)
    private Date jlrq;
    
    /**
     * 天气
     */
    @Column(length = 30)
    private String tq;
    
    /**
     * 时间
     */
    @Column(length = 10 ,nullable = false)
    private String wsdsj;
    
    /**
     * 记录人
     */
    @Column(length = 30 ,nullable = false)
    private String jlr;
    
    /**
     * 温度
     */
    @Column(length = 10 ,nullable = false)
    private String wd;
    
    /**
     * 湿度
     */
    @Column(length = 10 ,nullable = false)
    private String sd;
    
    /**
     * 效果温度
     */
    @Column(length = 10)
    private String xgwd;
    
    /**
     * 效果湿度
     */
    @Column(length = 10)
    private String xgsd;        
    
    /**
     * 采取措施
     */
    @Column(length = 30)
    private String cqcs;
    
    /**
     * 备注
     */
    @Column(length = 254)
    private String bz;

    public String getId() {
        return id;
    }

    public Date getJlrq() {
        return jlrq;
    }

    public String getTq() {
        return tq;
    }

    public String getWsdsj() {
        return wsdsj;
    }

    public String getJlr() {
        return jlr;
    }

    public String getWd() {
        return wd;
    }

    public String getSd() {
        return sd;
    }

    public String getXgwd() {
        return xgwd;
    }

    public String getXgsd() {
        return xgsd;
    }

    public String getCqcs() {
        return cqcs;
    }

    public String getBz() {
        return bz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJlrq(Date jlrq) {
        this.jlrq = jlrq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }

    public void setWsdsj(String wsdsj) {
        this.wsdsj = wsdsj;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public void setXgwd(String xgwd) {
        this.xgwd = xgwd;
    }

    public void setXgsd(String xgsd) {
        this.xgsd = xgsd;
    }

    public void setCqcs(String cqcs) {
        this.cqcs = cqcs;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
    
}
