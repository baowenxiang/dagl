package cn.proem.dagl.web.table.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * Java基本数据类型
 * @author tangcc
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "man_dagl_wsd")
public class WsdEntity extends MappedEntityModel{
	/**
	 * 记录日期
	 */
	@Column(name = "jlrq")
	private Date jlrq;
	
	/**
	 * 天气
	 */
	@Column(name = "tq")
	private String tq;
	
	/**
	 * 时间
	 */
	@Column(name = "wsdsj")
	private String wsdsj;
	
	/**
	 * 记录人
	 */
	@Column(name = "jlr")
	private String jlr;
	
	/**
	 * 温度
	 */
	@Column(name = "wd")
	private String wd;
	
	/**
	 * 湿度
	 */
	@Column(name = "sd")
	private Integer sd;
	
	/**
	 * 效果温度
	 */
	@Column(name = "xgwd")
	private int xgwd;
	
	/**
	 * 效果湿度
	 */
	@Column(name = "xgsd")
	private int xgsd;
	
	/**
	 * 采取措施
	 */
	@Column(name = "cqcs")
	private String cqcs;
	
	/**
	 * 备注
	 */
	@Column(name = "bz")
	private String bz;

	public Date getJlrq() {
		return jlrq;
	}

	public void setJlrq(Date jlrq) {
		this.jlrq = jlrq;
	}

	public String getTq() {
		return tq;
	}

	public void setTq(String tq) {
		this.tq = tq;
	}

	public String getWsdsj() {
		return wsdsj;
	}

	public void setWsdsj(String wsdsj) {
		this.wsdsj = wsdsj;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public Integer getSd() {
		return sd;
	}

	public void setSd(Integer sd) {
		this.sd = sd;
	}

	public int getXgwd() {
		return xgwd;
	}

	public void setXgwd(int xgwd) {
		this.xgwd = xgwd;
	}

	public int getXgsd() {
		return xgsd;
	}

	public void setXgsd(int xgsd) {
		this.xgsd = xgsd;
	}

	public String getCqcs() {
		return cqcs;
	}

	public void setCqcs(String cqcs) {
		this.cqcs = cqcs;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}