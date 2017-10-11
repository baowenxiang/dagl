package cn.proem.dagl.web.eep.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * EEP已封包
 * @author 
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_eep_done")
public class EepEntity extends MappedEntityModel{
	/**
	 * 档案ID
	 */
	@Column(name = "daid", unique=true)
	private String daid;

	/**
	 * 封包地址
	 */
	@Column(name = "fengbao")
	private String fengbao;
	
	/**
	 * 档号
	 */
	@Column(name = "filename")
	private String filename;
	
	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}
	
	public String getFengBao() {
		return fengbao;
	}

	public void setFengBao(String fengbao) {
		this.fengbao = fengbao;
	}
	
	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}
}