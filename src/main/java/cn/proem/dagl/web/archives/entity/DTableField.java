package cn.proem.dagl.web.archives.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_tablefield")
public class DTableField extends MappedEntityModel {
	
	/**
	 * 表名
	 */
	@ManyToOne
	@JoinColumn(name="tableNameId")
	private DTableName tableName;
	/**
	 * 字段英文名
	 */
	@Column(name = "zdywm",length = 200)
	private String zdywm;
	/**
	 * 字段中文名
	 */
	@Column(name = "zdzwm",length = 200)
	private String zdzwm;
	/**
	 * 字段类型
	 */
	@Column(name = "zdlx",length = 30)
	private String zdlx;
	/**
	 * 字段长度
	 */
	@Column(name = "zdcd")
	private Integer zdcd;
	/**
	 * 默认值
	 */
	@Column(name = "mrz",length = 254)
	private String mrz;
	/**
	 * 是否查询项
	 */
	@Column(name = "sfcxx",length = 1)
	private String sfcxx;
	/**
	 * 是否概要信息项
	 */
	@Column(name = "sfgyxxx",length = 1)
	private String sfgyxxx;
	/**
	 * 是否可扩展字段
	 */
	@Column(name = "sfkzzd",length = 1)
	private String sfkzzd;
	/**
	 * 是否使用
	 */
	@Column(name = "sfsy",length = 1)
	private String sfsy;
	/**
	 * 是否可编辑
	 */
	@Column(name = "sfkbj",length = 1)
	private String sfkbj;
	/**
	 * 宽度
	 */
	@Column(name = "width")
	private Integer width;
	/**
	 *对齐方式 
	 */
	@Column(name = "align",length = 10)
	private String align;
	/**
	 *是否报表信息项 
	 */
	@Column(name = "sfbbxxx",length = 1)
	private String sfbbxxx;
	/**
	 * 对应的字典did
	 */
	@Column(name = "did")
	private String did;
	/**
	 * 是否冻结列
	 */
	@Column(name = "sfdjl",length = 1)
	private String sfdjl;
	/**
	 * 是否必填项
	 */
	@Column(name = "sfbtx",length = 1)
	private String sfbtx;
	/**
	 * 显示序号
	 */
	@Column(name = "xsxh")
	private Integer xsxh;
	/**
	 * 说明
	 */
	@Column(name = "sm",length = 254)
	private String sm;
 	/**
 	 * 日期格式
 	 */
	@Column(name = "rqgs",length = 20)
	private String rqgs;
	/**
	 * 占用行数
	 */
	@Column(name = "zyls")
	private Integer zyls;
	/**
	 * 占用列数
	 */
	@Column(name = "zyhs")
	private Integer zyhs;
	/**
	 * 排序类型 ;0不排序1升序2降序
	 */
	@Column(name = "pxlx",length = 10)
	private String pxlx;
	/**
	 * 是否可修改
	 */
	@Column(name = "sfkxg",length = 1)
	private String sfkxg;

	
	
	public DTableName getTableName() {
		return tableName;
	}

	public void setTableName(DTableName tableName) {
		this.tableName = tableName;
	}

	public String getZdywm() {
		return zdywm;
	}

	public void setZdywm(String zdywm) {
		this.zdywm = zdywm;
	}

	public String getZdzwm() {
		return zdzwm;
	}

	public void setZdzwm(String zdzwm) {
		this.zdzwm = zdzwm;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public Integer getZdcd() {
		return zdcd;
	}

	public void setZdcd(Integer zdcd) {
		this.zdcd = zdcd;
	}

	public String getMrz() {
		return mrz;
	}

	public void setMrz(String mrz) {
		this.mrz = mrz;
	}

	public String getSfcxx() {
		return sfcxx;
	}

	public void setSfcxx(String sfcxx) {
		this.sfcxx = sfcxx;
	}

	public String getSfgyxxx() {
		return sfgyxxx;
	}

	public void setSfgyxxx(String sfgyxxx) {
		this.sfgyxxx = sfgyxxx;
	}

	public String getSfkzzd() {
		return sfkzzd;
	}

	public void setSfkzzd(String sfkzzd) {
		this.sfkzzd = sfkzzd;
	}

	public String getSfsy() {
		return sfsy;
	}

	public void setSfsy(String sfsy) {
		this.sfsy = sfsy;
	}

	public String getSfkbj() {
		return sfkbj;
	}

	public void setSfkbj(String sfkbj) {
		this.sfkbj = sfkbj;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getSfbbxxx() {
		return sfbbxxx;
	}

	public void setSfbbxxx(String sfbbxxx) {
		this.sfbbxxx = sfbbxxx;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getSfdjl() {
		return sfdjl;
	}

	public void setSfdjl(String sfdjl) {
		this.sfdjl = sfdjl;
	}

	public String getSfbtx() {
		return sfbtx;
	}

	public void setSfbtx(String sfbtx) {
		this.sfbtx = sfbtx;
	}

	public Integer getXsxh() {
		return xsxh;
	}

	public void setXsxh(Integer xsxh) {
		this.xsxh = xsxh;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getRqgs() {
		return rqgs;
	}

	public void setRqgs(String rqgs) {
		this.rqgs = rqgs;
	}

	public Integer getZyls() {
		return zyls;
	}

	public void setZyls(Integer zyls) {
		this.zyls = zyls;
	}

	public Integer getZyhs() {
		return zyhs;
	}

	public void setZyhs(Integer zyhs) {
		this.zyhs = zyhs;
	}

	public String getPxlx() {
		return pxlx;
	}

	public void setPxlx(String pxlx) {
		this.pxlx = pxlx;
	}

	public String getSfkxg() {
		return sfkxg;
	}

	public void setSfkxg(String sfkxg) {
		this.sfkxg = sfkxg;
	}
	
	

}
