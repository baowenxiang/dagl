package cn.proem.dagl.web.oaservice.entity;

import java.util.Date;

import javax.persistence.Column;

public class TYwgjQueryObj {
//	@Column(name = "id")
	private String id;     //档号
//	@Column(name = "xsxh")
	private int xsxh;    //显示序号
//	@Column(name = "wjdz")
	private String wjdz;   //文件地址
//	@Column(name = "scrq1")
    private Date scrq1;   //生成日期1
//	@Column(name = "type")
    private Date scrq2;   //生成日期2
//	@Column(name = "scz")
    private String scz;  //生成者
//	@Column(name = "dalx")
    private String dalx;
//	@Column(name = "wjlx")
    private String wjlx;
//	@Column(name = "wjdx")
    private Integer wjdx;     //大小
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getScrq1() {
		return scrq1;
	}
	public void setScrq1(Date scrq1) {
		this.scrq1 = scrq1;
	}
	public Date getScrq2() {
		return scrq2;
	}
	public void setScrq2(Date scrq2) {
		this.scrq2 = scrq2;
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
}
