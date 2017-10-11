package cn.proem.dagl.web.fileIdentify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 鉴定内容
 * @author lenovo
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_IDENTIFY_CONTENT")
public class IdentifyContent {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 40)
	private String id;
	
	/**
	 * 表名
	 */
	@Column
	private String bm;
	
	/**
	 * 档案id
	 */
	@Column
	private String daid;
	
	/**
	 * 鉴定id
	 */
	@Column
	private String jdid;
	
	/**
	 * 鉴定内容
	 */
	@Column
	private String jdnr;
	
	/**
	 * 鉴定时间
	 */
	@Column
	private String jdsj;
	
	/**
	 * 鉴定人
	 */
	@Column
	private String jdr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}

	public String getJdid() {
		return jdid;
	}

	public void setJdid(String jdid) {
		this.jdid = jdid;
	}

	public String getJdnr() {
		return jdnr;
	}

	public void setJdnr(String jdnr) {
		this.jdnr = jdnr;
	}

	public String getJdsj() {
		return jdsj;
	}

	public void setJdsj(String jdsj) {
		this.jdsj = jdsj;
	}

	public String getJdr() {
		return jdr;
	}

	public void setJdr(String jdr) {
		this.jdr = jdr;
	}
	
	
}
