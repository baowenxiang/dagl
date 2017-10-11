package cn.proem.dagl.web.fileControl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 档案划控实体类
 * @author Administrator
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_DAHK")
public class FileControl {
	/**
	 * 主键 uuid
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 40)
	private String id;
	/**
	 * 档案的id
	 */
	@Column
	private String daid;
	
	
	public String getDaid() {
		return daid;
	}
	public void setDaid(String daid) {
		this.daid = daid;
	}
	/**
	 * 档号
	 */
	@Column
	private String dh;
	/**
	 * 全宗号
	 */
	@Column
	private String qzh;
	/**
	 * 年度号
	 */
	@Column
	private String ndh;
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
	 * 类别
	 */
	@Column
	private String lb;
	/**
	 * 鉴定时间
	 */
	@Column
	private Date jdsj;
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
	 * 保管期限
	 */
	@Column
	private String bgqx;
	/**
	 * 题名
	 */
	@Column
	private String tm;
	/**
	 * 密级
	 */
	@Column
	private String mj;
	/**
	 * 载体类型
	 */
	@Column
	private String ztlx;
	/**
	 * 载体单位
	 */
	@Column
	private String ztdw;
	/**
	 * 载体数量
	 */
	@Column
	private Integer ztsl;
	/**
	 * 销毁原因
	 */
	@Column
	private String xhyy;
	/**
	 * 备注
	 */
	@Column
	private String bz;
	/**
	 * 文号
	 */
	@Column
	private String wh;
	/**
	 * 表名
	 */
	@Column
	private String bm;
	@Column
	private Integer delFlag = 0;
	
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getQzh() {
		return qzh;
	}
	public void setQzh(String qzh) {
		this.qzh = qzh;
	}
	public String getNdh() {
		return ndh;
	}
	public void setNdh(String ndh) {
		this.ndh = ndh;
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
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public Date getJdsj() {
		return jdsj;
	}
	public void setJdsj(Date jdsj) {
		this.jdsj = jdsj;
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
	public String getBgqx() {
		return bgqx;
	}
	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
	}
	public String getZtlx() {
		return ztlx;
	}
	public void setZtlx(String ztlx) {
		this.ztlx = ztlx;
	}
	public String getZtdw() {
		return ztdw;
	}
	public void setZtdw(String ztdw) {
		this.ztdw = ztdw;
	}
	public Integer getZtsl() {
		return ztsl;
	}
	public void setZtsl(Integer ztsl) {
		this.ztsl = ztsl;
	}
	public String getXhyy() {
		return xhyy;
	}
	public void setXhyy(String xhyy) {
		this.xhyy = xhyy;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	@Override
	public String toString() {
		return "FileControl [id=" + id + ", dh=" + dh + ", qzh=" + qzh
				+ ", ndh=" + ndh + ", mlh=" + mlh + ", ajh=" + ajh + ", lb="
				+ lb + ", jdsj=" + jdsj + ", zrz=" + zrz + ", cwrq=" + cwrq
				+ ", bgqx=" + bgqx + ", tm=" + tm + ", mj=" + mj + ", ztlx="
				+ ztlx + ", ztdw=" + ztdw + ", ztsl=" + ztsl + ", xhyy=" + xhyy
				+ ", bz=" + bz + ", wh=" + wh + ", bm=" + bm + "]";
	}
	
	

}
