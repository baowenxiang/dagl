package cn.proem.dagl.web.preArchive.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_YWGJ")
public class Ywgj extends MappedEntityModel{
	
	/**
	 * 显示序号
	 */
	@Column(name = "xsxh")
	private Integer xsxh;
	
	/**
	 * 文件地址
	 */
	@Column(name = "wjdz")
	private String wjdz;
	
	/**
	 * 上传日期
	 */
	@Column(name = "scrq")
	private Date scrq; 
	
	/**
	 * 上传者
	 */
	@Column(name = "scz")
	private String scz;
	
	/**
	 * 文件类型
	 */
	@Column(name = "wjlx")
	private String wjlx;
	
	/**
	 * 文件大小
	 */
	@Column(name = "wjdx")
	private Long wjdx;
	
	/**
	 * 档案类型
	 */
	@Column(name = "dalx")
	private String dalx;
	
	/**
	 * 文件名
	 */
	@Column(name = "wjm")
	private String wjm;
	
	/**
	 * 原来文件地址
	 */
	@Column(name = "ywjdz")
	private String ywjdz;
	
	/**
	 * 档号
	 */
	@Column(name = "dh")
	private String dh;
	
	/**
	 * 所属资料收集id
	 * 
	 */
	@Column(name = "zlsj")
	private String zlsj;

	

	public Integer getXsxh() {
		return xsxh;
	}

	public void setXsxh(Integer xsxh) {
		this.xsxh = xsxh;
	}

	public String getWjdz() {
		return wjdz;
	}

	public void setWjdz(String wjdz) {
		this.wjdz = wjdz;
	}

	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	public String getScz() {
		return scz;
	}

	public void setScz(String scz) {
		this.scz = scz;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public Long getWjdx() {
		return wjdx;
	}

	public void setWjdx(Long wjdx) {
		this.wjdx = wjdx;
	}

	public String getDalx() {
		return dalx;
	}

	public void setDalx(String dalx) {
		this.dalx = dalx;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getYwjdz() {
		return ywjdz;
	}

	public void setYwjdz(String ywjdz) {
		this.ywjdz = ywjdz;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getZlsj() {
		return zlsj;
	}

	public void setZlsj(String zlsj) {
		this.zlsj = zlsj;
	}

	
	
	
}
