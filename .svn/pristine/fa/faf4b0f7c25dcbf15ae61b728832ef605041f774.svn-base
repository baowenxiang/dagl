package cn.proem.suw.web.docu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "POA_DOCU_WDETAIL")
public class DocWDetail {
    
    /**
     * ID
     */
    @Id
    @Column(name = "uuid", length = 40)
    private String id;
    
    /**
     * 目录ID
     */
    @Column(nullable=false)
    private String ysjid;
    
    /**
     * 业务标识符
     */
    @Column(nullable=false)
    private String ywbsf;
    
    /**
     * 机构人员标识符 
     */
    @Column(nullable=false)
    private String jgrymc;
    
    /**
     * 文件标识符
     */
    @Column(nullable=false)
    private String wjbsf;
    
    /**
     * 业务状态
     */
    @Column(nullable=false)
    private String ywzt;
    
    /**
     * 业务行为
     */
    @Column(nullable=false)
    private String ywxw;
    
    /**
     * 行为时间
     */
    @Column(nullable=false)
    private String xwsj;
    
    /**
     * 行为依据
     */
    @Column(length = 2000)
    private String xwyj;
    
    /**
     * 行为描述
     */
    @Column(length = 2000)
    private String xwms;
    
    /**
     * 删除标识符
     */
    @Column(nullable=false)
    private Integer delFlag = 0;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the ysjid
     */
    public String getYsjid() {
        return ysjid;
    }

    /**
     * @return the ywbsf
     */
    public String getYwbsf() {
        return ywbsf;
    }

    /**
     * @return the jgrymc
     */
    public String getJgrymc() {
        return jgrymc;
    }

    /**
     * @return the wjbsf
     */
    public String getWjbsf() {
        return wjbsf;
    }

    /**
     * @return the ywzt
     */
    public String getYwzt() {
        return ywzt;
    }

    /**
     * @return the ywxw
     */
    public String getYwxw() {
        return ywxw;
    }

    /**
     * @return the xwsj
     */
    public String getXwsj() {
        return xwsj;
    }

    /**
     * @return the xwyj
     */
    public String getXwyj() {
        return xwyj;
    }

    /**
     * @return the xwms
     */
    public String getXwms() {
        return xwms;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param ysjid the ysjid to set
     */
    public void setYsjid(String ysjid) {
        this.ysjid = ysjid;
    }

    /**
     * @param ywbsf the ywbsf to set
     */
    public void setYwbsf(String ywbsf) {
        this.ywbsf = ywbsf;
    }

    /**
     * @param jgrymc the jgrymc to set
     */
    public void setJgrymc(String jgrymc) {
        this.jgrymc = jgrymc;
    }

    /**
     * @param wjbsf the wjbsf to set
     */
    public void setWjbsf(String wjbsf) {
        this.wjbsf = wjbsf;
    }

    /**
     * @param ywzt the ywzt to set
     */
    public void setYwzt(String ywzt) {
        this.ywzt = ywzt;
    }

    /**
     * @param ywxw the ywxw to set
     */
    public void setYwxw(String ywxw) {
        this.ywxw = ywxw;
    }

    /**
     * @param xwsj the xwsj to set
     */
    public void setXwsj(String xwsj) {
        this.xwsj = xwsj;
    }

    /**
     * @param xwyj the xwyj to set
     */
    public void setXwyj(String xwyj) {
        this.xwyj = xwyj;
    }

    /**
     * @param xwms the xwms to set
     */
    public void setXwms(String xwms) {
        this.xwms = xwms;
    }

    /**
     * @return the delFlag
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
    
}
