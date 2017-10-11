package cn.proem.dagl.web.fuzzyQuery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_QWJS")
public class ArchiveCollect {
    @Column
    private String zwm;
    @Column
    private String ywm;
    @Id
    @Column
    private String uuid;
    @Column
    private String dh;
    @Column
    private String companynum;
    @Column
    private String evercompanynum;
    /**
     * @return the zwm
     */
    public String getZwm() {
        return zwm;
    }
    /**
     * @return the ywm
     */
    public String getYwm() {
        return ywm;
    }
    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }
    /**
     * @return the dh
     */
    public String getDh() {
        return dh;
    }
    /**
     * @return the companynum
     */
    public String getCompanynum() {
        return companynum;
    }
    /**
     * @return the evercompanynum
     */
    public String getEvercompanynum() {
        return evercompanynum;
    }
    /**
     * @param zwm the zwm to set
     */
    public void setZwm(String zwm) {
        this.zwm = zwm;
    }
    /**
     * @param ywm the ywm to set
     */
    public void setYwm(String ywm) {
        this.ywm = ywm;
    }
    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    /**
     * @param dh the dh to set
     */
    public void setDh(String dh) {
        this.dh = dh;
    }
    /**
     * @param companynum the companynum to set
     */
    public void setCompanynum(String companynum) {
        this.companynum = companynum;
    }
    /**
     * @param evercompanynum the evercompanynum to set
     */
    public void setEvercompanynum(String evercompanynum) {
        this.evercompanynum = evercompanynum;
    }
    
    
    
    
}
