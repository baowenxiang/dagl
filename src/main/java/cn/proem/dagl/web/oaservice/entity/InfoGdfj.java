package cn.proem.dagl.web.oaservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "INFO_GDFJ")
public class InfoGdfj {
	@Id
	@Column(name = "id")
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "daId")
	private String daId;
	public String getDaId() {
		return daId;
	}
	public void setDaId(String daId) {
		this.daId = daId;
	}
	@Column(name = "delflag")
	private int delflag;
	public int getDelflag() {
		return delflag;
	}
	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}
	@Column(name = "fjmc")
	private String fjmc;   //附件名称
	@Column(name = "fjnr")
	private byte[] fjnr;   //附件内容
	@Column(name = "fjhz")
	private String fjhz;    //附件后缀
	@Column(name = "isarchive")
	private int isArchive;	//是否归档文件
	public int getIsArchive() {
		return isArchive;
	}
	public void setIsArchive(int isArchive) {
		this.isArchive = isArchive;
	}

	private String ywurl;//原文的链接信息，获取list时，需要填写




	public String getFjmc() {
		return fjmc;
	}


	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}


	public byte[] getFjnr() {
		return fjnr;
	}


	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}


	public String getFjhz() {
		return fjhz;
	}


	public void setFjhz(String fjhz) {
		this.fjhz = fjhz;
	}


	public String getYwurl() {
		return ywurl;
	}


	public void setYwurl(String ywurl) {
		this.ywurl = ywurl;
	}
}
