package cn.proem.dagl.web.fileIdentify.dto;

import javax.persistence.Column;

public class DtoIdentifyContent {
private String id;
	
	/**
	 * 表名
	 */
	private String bm;
	
	/**
	 * 档号
	 */
	private String dh;
	
	/**
	 * 鉴定id
	 */
	private String jdid;
	
	/**
	 * 鉴定内容
	 */
	private String jdnr;
	
	/**
	 * 鉴定时间
	 */
	private String jdsj;
	
	/**
	 * 鉴定人
	 */
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

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
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

	public DtoIdentifyContent(String id, String bm, String dh, String jdid,
			String jdnr, String jdsj, String jdr) {
		super();
		this.id = id;
		this.bm = bm;
		this.dh = dh;
		this.jdid = jdid;
		this.jdnr = jdnr;
		this.jdsj = jdsj;
		this.jdr = jdr;
	}

	public DtoIdentifyContent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
