package cn.proem.dagl.web.fileCompilation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.proem.core.entity.User;
import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_COMPILE_RESULT")
public class CompileResult extends MappedEntityModel{
	/**
	 * 编研日期
	 */
	@Column(name = "byrq")
	private String byrq;
	
	/**
	 * 题名
	 */
	@Column(name = "tm")
	private String tm;
	
	/**
	 * 备注
	 */
	@Column(name = "bz")
	private String bz;
	
	/**
	 * 编研人
	 */
	@ManyToOne
	@JoinColumn(name = "byrid")
	private User byr;

	public String getByrq() {
		return byrq;
	}

	public void setByrq(String byrq) {
		this.byrq = byrq;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public User getByr() {
		return byr;
	}

	public void setByr(User byr) {
		this.byr = byr;
	}

	public CompileResult(String byrq, String tm, String bz, User byr) {
		super();
		this.byrq = byrq;
		this.tm = tm;
		this.bz = bz;
		this.byr = byr;
	}

	public CompileResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}


