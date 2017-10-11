package cn.proem.dagl.web.preArchive.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;




/**
 * 资料收集表
 * @author bao
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_ZLZL_ZLSJ")
public class Zlsj extends MappedEntityModel{
	/**
	 * 题名
	 */
	@Column(name = "tm")
	private String tm;
	
	/**
	 * 责任者
	 */
	@Column(name = "zrz")
	private String zrz;
	
	/**
	 * 成文日期
	 */
	@Column(name = "cwrq")
	private String cwrq;
	
	/**
	 * 文号
	 */
	@Column(name = "wh")
	private String wh;
	
	/**
	 * 导入者
	 */
	@Column(name = "drz")
	private String drz;
	
	/**
	 * 导入时间
	 */
	@Column(name = "drsj")
	private String drsj;
	
	/**
	 * 预归档表名
	 */
	@Column(name = "zlfl")
	private String zlfl;

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getZrz() {
		return zrz;
	}

	public void setZrz(String zrz) {
		this.zrz = zrz;
	}

	public String getCwrq() {
		return cwrq;
	}

	public void setCwrq(String cwrq) {
		this.cwrq = cwrq;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getDrz() {
		return drz;
	}

	public void setDrz(String drz) {
		this.drz = drz;
	}

	public String getDrsj() {
		return drsj;
	}

	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}

	public String getZlfl() {
		return zlfl;
	}

	public void setZlfl(String zlfl) {
		this.zlfl = zlfl;
	}
	
	
	
}