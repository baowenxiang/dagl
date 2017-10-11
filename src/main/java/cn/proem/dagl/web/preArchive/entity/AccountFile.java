package cn.proem.dagl.web.preArchive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.dagl.web.flow.entity.FileEntity;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pfm_account_file")
public class AccountFile extends FileEntity{
	
	/**
	 * 题名
	 */
	@Column
	private String tm;
	
	/**
	 * 责任者
	 */
	@Column
	private String zrz;
	
	/**
	 * 成文日期
	 */
	@Column
	private String cwrq;
	
	/**
	 * 文号
	 */
	@Column
	private String wh;
	
	
	
	/**
	 * 档号
	 */
	@Column
	private String dh;
	
	/**
	 * 档案馆代码
	 */
	@Column
	private String dagdm;
	
	/**
	 * 全宗号
	 */
	@Column
	private String qzh;
	
	/**
	 * 目录号
	 */
	@Column
	private String mlh;
	
	/**
	 * 案卷号
	 */
	@Column
	private String ajh;
	
	/**
	 * 所在页号
	 */
	@Column
	private String szyh;
	
	/**
	 * 分类号
	 */
	@Column
	private String flh;
	
	/**
	 * 会计类别1
	 */
	@Column
	private String kjlb1;
	
	/**
	 * 会计类别2
	 */
	@Column
	private String wjkjlb2;
	
	/**
	 * 会计凭证号
	 */
	@Column
	private String kjpzh;
	
	/**
	 * 密级
	 */
	@Column
	private String mj;
	
	/**
	 * 保管期限
	 */
	@Column
	private String bgqx;
	
	/**
	 * 载体规格
	 */
	@Column
	private String ztgg;
	
	/**
	 * 载体类型
	 */
	@Column
	private String ztlx;
	
	/**
	 * 载体数量
	 */
	@Column
	private Integer ztsl;
	
	/**
	 * 载体单位
	 */
	@Column
	private String ztdw;
	
	/**
	 * 全文标识
	 */
	@Column
	private String qwbs;
	
	/**
	 * 控制符
	 */
	@Column
	private String kzf;
	
	/**
	 * 备注
	 */
	@Column
	private String bz;
	
	/**
	 * 是否鉴定
	 */
	@Column
	private String sfjd;
	
	/**
	 * 是否预归档
	 */
	@Column
	private String sfygd;
	
	
	public String getDh() {
		return dh;
	}
	
	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getDagdm() {
		return dagdm;
	}

	public void setDagdm(String dagdm) {
		this.dagdm = dagdm;
	}

	public String getQzh() {
		return qzh;
	}

	public void setQzh(String qzh) {
		this.qzh = qzh;
	}

	public String getMlh() {
		return mlh;
	}

	public void setMlh(String mlh) {
		this.mlh = mlh;
	}

	public String getAjh() {
		return ajh;
	}

	public void setAjh(String ajh) {
		this.ajh = ajh;
	}

	public String getSzyh() {
		return szyh;
	}

	public void setSzyh(String szyh) {
		this.szyh = szyh;
	}


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

	public String getFlh() {
		return flh;
	}

	public void setFlh(String flh) {
		this.flh = flh;
	}

	public String getKjlb1() {
		return kjlb1;
	}

	public void setKjlb1(String kjlb1) {
		this.kjlb1 = kjlb1;
	}

	public String getWjkjlb2() {
		return wjkjlb2;
	}

	public void setWjkjlb2(String wjkjlb2) {
		this.wjkjlb2 = wjkjlb2;
	}

	public String getKjpzh() {
		return kjpzh;
	}

	public void setKjpzh(String kjpzh) {
		this.kjpzh = kjpzh;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getBgqx() {
		return bgqx;
	}

	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}

	public String getZtgg() {
		return ztgg;
	}

	public void setZtgg(String ztgg) {
		this.ztgg = ztgg;
	}

	public String getZtlx() {
		return ztlx;
	}

	public void setZtlx(String ztlx) {
		this.ztlx = ztlx;
	}

	public Integer getZtsl() {
		return ztsl;
	}

	public void setZtsl(Integer ztsl) {
		this.ztsl = ztsl;
	}

	public String getZtdw() {
		return ztdw;
	}

	public void setZtdw(String ztdw) {
		this.ztdw = ztdw;
	}

	public String getQwbs() {
		return qwbs;
	}

	public void setQwbs(String qwbs) {
		this.qwbs = qwbs;
	}

	public String getKzf() {
		return kzf;
	}

	public void setKzf(String kzf) {
		this.kzf = kzf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfjd() {
		return sfjd;
	}

	public void setSfjd(String sfjd) {
		this.sfjd = sfjd;
	}

	public String getSfygd() {
		return sfygd;
	}

	public void setSfygd(String sfygd) {
		this.sfygd = sfygd;
	}

	
	
}
