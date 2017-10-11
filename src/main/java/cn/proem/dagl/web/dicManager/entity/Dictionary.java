package cn.proem.dagl.web.dicManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="PDAGL_SYS_DIC")
public class Dictionary extends MappedEntityModel {
	/**
	 * 字典项编号
	 */
	@Column
	private String dno;
	/**
	 * 字典项名称
	 */
	@Column
	private String dname;
	/**
	 * 字典项描述
	 */
	@Column
	private String ddescr;

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDdescr() {
		return ddescr;
	}

	public void setDdescr(String ddescr) {
		this.ddescr = ddescr;
	}
	
	

}
