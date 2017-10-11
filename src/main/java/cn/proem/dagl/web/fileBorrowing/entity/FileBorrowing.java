package cn.proem.dagl.web.fileBorrowing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.proem.core.entity.Department;
import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="PDAGL_FILE_BORROWING")
public class FileBorrowing extends MappedEntityModel{
	/**
	 * 题名
	 */
	@Column
	private String tm;
	/**
	 * 借阅人id
	 */
	@Column
	private String jyrid;
	/**
	 * 借阅人
	 */
	@Column
	private String jyr;
	/**
	 * 借阅时间
	 */
	@Column
	private String jysj;
	/**
	 * 拟归还时间
	 */
	@Column
	private String nghsj;
	/**
	 * 归还时间
	 */
	@Column
	private String ghsj;
	/**
	 * 借阅状态
	 */
	@Column
	private String jyzt = "0";
	/**
	 * 表名
	 */
	@Column
	private String bm;
	/**
	 * 档号
	 */
	@Column
	private String dh;
	/**
	 * 录入时间
	 */
	@Column
	private Date lrsj;
	/**
	 * 文号
	 */
	@Column
	private String wh;
	/**
	 * 分数
	 */
	@Column
	private Integer fh;
	/**
	 * 经办人
	 */
	@Column
	private String jbr;
	/**
	 * 批准人
	 */
	@Column
	private String pzr;
	/**
	 * 机构名称
	 */
	@Column
	private String jgmc;
	/**
	 * 全文标识
	 */
	@Column
	private String qwbs;
	/**
	 * 借阅效果
	 */
	@Column
	private String jyxg;
	/**
	 * 借阅目的
	 */
	@Column
	private String jymd;
	/**
	 * 备注
	 */
	@Column
	private String bz;
	
	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	/**
	 * 部门id
	 */
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getJyrid() {
		return jyrid;
	}

	public void setJyrid(String jyrid) {
		this.jyrid = jyrid;
	}

	public String getJyr() {
		return jyr;
	}

	public void setJyr(String jyr) {
		this.jyr = jyr;
	}


	public String getNghsj() {
		return nghsj;
	}

	public void setNghsj(String nghsj) {
		this.nghsj = nghsj;
	}

	public String getGhsj() {
		return ghsj;
	}

	public void setGhsj(String ghsj) {
		this.ghsj = ghsj;
	}

	public String getJyzt() {
		return jyzt;
	}

	public void setJyzt(String jyzt) {
		this.jyzt = jyzt;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public Integer getFh() {
		return fh;
	}

	public void setFh(Integer fh) {
		this.fh = fh;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getPzr() {
		return pzr;
	}

	public void setPzr(String pzr) {
		this.pzr = pzr;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getQwbs() {
		return qwbs;
	}

	public void setQwbs(String qwbs) {
		this.qwbs = qwbs;
	}

	public String getJyxg() {
		return jyxg;
	}

	public void setJyxg(String jyxg) {
		this.jyxg = jyxg;
	}

	public String getJymd() {
		return jymd;
	}

	public void setJymd(String jymd) {
		this.jymd = jymd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
	
	

}
