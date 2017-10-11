package cn.proem.dagl.web.oaservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "INFO_YWGJ")
public class InfoYwgj {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "xsxh")
	private int xsxh;    //显示序号
	@Column(name = "wjdz")
	private String wjdz;   //文件地址
	@Column(name = "scrq")
    private Date scrq;
	@Column(name = "scz")
    private String scz;  //生成者
	@Column(name = "dalx")
    private String dalx;//档案类型
	@Column(name = "wjlx")
    private String wjlx;//文件类型
	@Column(name = "wjdx")
    private Integer wjdx;     //大小
    
    //衍生字段
    private String wjdxstr;//文件大小字符串
    private String scrqstr;//上传日期字符串
	@Column(name="zlsj")
    private String zlsj;//原文id

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZlsj() {
		return zlsj;
	}

	public void setZlsj(String zlsj) {
		this.zlsj = zlsj;
	}

	private String wjm;

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public int getXsxh() {
		return xsxh;
	}

	public void setXsxh(int xsxh) {
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

	public String getDalx() {
		return dalx;
	}

	public void setDalx(String dalx) {
		this.dalx = dalx;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public Integer getWjdx() {
		return wjdx;
	}

	public void setWjdx(Integer wjdx) {
		this.wjdx = wjdx;
	}

	public String getWjdxstr() {
		return wjdxstr;
	}

	public void setWjdxstr(String wjdxstr) {
		this.wjdxstr = wjdxstr;
	}

	public String getScrqstr() {
		return scrqstr;
	}

	public void setScrqstr(String scrqstr) {
		this.scrqstr = scrqstr;
	}


	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}
}
