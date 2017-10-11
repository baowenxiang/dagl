package cn.proem.dagl.web.tj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_gcl_order")
public class GclOrder extends MappedEntityModel{
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the ywm
     */
    public String getYwm() {
        return ywm;
    }
    /**
     * @return the zwm
     */
    public String getZwm() {
        return zwm;
    }
    /**
     * @return the order
     */
    public Integer getOrder() {
        return ord;
    }
    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }
    /**
     * @param ywm the ywm to set
     */
    public void setYwm(String ywm) {
        this.ywm = ywm;
    }
    /**
     * @param zwm the zwm to set
     */
    public void setZwm(String zwm) {
        this.zwm = zwm;
    }
    /**
     * @param order the order to set
     */
    public void setOrder(Integer order) {
        this.ord = order;
    }
    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }
    /**
     * 字段英文名
     */
    @Column
    private String ywm;
    /**
     * 字段中文名
     */
    @Column
    private String zwm;
    /**
     * 字段显示序号
     */
    @Column
    private Integer ord;
    /**
     * 所属公司
     */
    @Column
    private String company;
    
    /**
     * 统计类型
     */
    @Column
    private String type;
}
