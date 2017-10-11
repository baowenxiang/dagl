package cn.proem.dagl.web.fileIdentify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/***
 * 档案鉴定表
 * @author lenovo
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_FILE_IDENTIFY")
public class FileIdentify{
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 40)
	private String id;
	
	
	/**
	 * 逻辑删除标识 0: 未删除, 1:已删除
	 */
	@Column(name = "del_flag")
	protected int delFlag = 0;
	
	/**
	 * 档号
	 */
	@Column(name = "dh", length = 30)
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
	private String jdsj;
	
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
	private String ztsl;
	
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
	 * 文号
	 */
	@Column
	private String fid;
	
	/**
	 * 功能名
	 */
	@Column
	private String fname;
	
	/**
	 * 功能名
	 */
	@Column
	private String pid;
	
	/**
	 * 菜单级别
	 */
	@Column
	private String level2;
	
	/**
	 * 鉴定级别
	 */
	@Column
	private String jdnr;
	
	/**
	 * 表名
	 */
	@Column
	private String bm;
	
	/**
	 * 销毁档案id
	 */
	@Column
	private String daid;

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

	public String getJdsj() {
		return jdsj;
	}

	public void setJdsj(String jdsj) {
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

	public String getZtsl() {
		return ztsl;
	}

	public void setZtsl(String ztsl) {
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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLevel() {
		return level2;
	}

	public void setLevel(String level) {
		this.level2 = level;
	}

	public String getJdnr() {
		return jdnr;
	}

	public void setJdnr(String jdnr) {
		this.jdnr = jdnr;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
}
