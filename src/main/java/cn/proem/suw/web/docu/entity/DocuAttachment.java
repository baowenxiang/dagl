package cn.proem.suw.web.docu.entity;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;

/**
 * @ClassName DocuAttachment
 * @Description 文档附件表
 * @author Pan Jilong
 * @date 2017年3月29日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "POA_DOCU_ATTACHMENT")
public class DocuAttachment implements BaseEntityInf{

	/**
	 * ID
	 */
	@Id
	@Column(name = "id", length = 40)
	private String id;
	
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column
	private Integer delFlag = 0;
	
	/**
	 * 目录ID
	 */
	@Column(nullable=false)
	private String ysjid;
	/**
	 * 附件名称
	 */
	@Column(nullable=false)
	private String fjmc;
	/**
	 * 附件内容
	 */
	@Column(nullable=false)
	private String fjnr;
	/**
	 * 附件后缀
	 */
	@Column(nullable=false)
	private String fjhz;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYsjid() {
		return ysjid;
	}
	public void setYsjid(String ysjid) {
		this.ysjid = ysjid;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getFjnr() {
		return fjnr;
	}
	public void setFjnr(String fjnr) {
		this.fjnr = fjnr;
	}
	public String getFjhz() {
		return fjhz;
	}
	public void setFjhz(String fjhz) {
		this.fjhz = fjhz;
	}
	public void set(String colname, Object val){
        Field field;
        try {
            field = this.getClass().getDeclaredField(colname);
            field.setAccessible(true);
            field.set(this, val);
        } catch (NoSuchFieldException e1) {
            try {
                field = this.getClass().getSuperclass().getSuperclass().getDeclaredField(colname);
                field.setAccessible(true);
                field.set(this, val);
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T>T get(String colname){
        Field field;
        try {
            field = this.getClass().getDeclaredField(colname);
            field.setAccessible(true);
            return (T) field.get(this);
        } catch (NoSuchFieldException e1) {
        	try {
                field = this.getClass().getSuperclass().getSuperclass().getDeclaredField(colname);
                field.setAccessible(true);
                return (T) field.get(this);
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
         
    }
	
}
