package cn.proem.dagl.web.archives.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_tablename")
public class DTableName extends MappedEntityModel {
	
	/**
	 * 表名
	 */
	@Column(name = "bm",length = 30)
	private String bm;
	/**
	 * 表中文名
	 */
	@Column(name = "zwm",length = 100)
	private String zwm;
	/**
	 * 是否专业表
	 */
	@Column(name = "sfzym",length = 1)
	private String sfzym;
	/**
	 * 说明
	 */
	@Column(name = "sm",length = 1000)
	private String sm;
	/**
	 * 列表第一列是否显示复选框
	 */
	@Column(name = "sfxsfxk",length = 1)
	private String sfxsfxk;
	/**
	 * 弹出框的列数
	 */
	@Column(name = "columns")
	private Integer columns;
	/**
	 * 统计时间的字段名
	 */
	@Column(name = "tjsjzdm",length = 30)
	private String tjsjzdm;
	/**
	 *是否档案基础表 
	 */
	@Column(name = "sfdajcb",length = 1)
	private String sfdajcb;
	/**
	 *是否动态表 是则可配置该表 
	 */
	@Column(name = "sfdtb",length = 1)
	private String sfdtb;
	
	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getZwm() {
		return zwm;
	}

	public void setZwm(String zwm) {
		this.zwm = zwm;
	}

	public String getSfzym() {
		return sfzym;
	}

	public void setSfzym(String sfzym) {
		this.sfzym = sfzym;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getSfxsfxk() {
		return sfxsfxk;
	}

	public void setSfxsfxk(String sfxsfxk) {
		this.sfxsfxk = sfxsfxk;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public String getTjsjzdm() {
		return tjsjzdm;
	}

	public void setTjsjzdm(String tjsjzdm) {
		this.tjsjzdm = tjsjzdm;
	}

	public String getSfdajcb() {
		return sfdajcb;
	}

	public void setSfdajcb(String sfdajcb) {
		this.sfdajcb = sfdajcb;
	}

	public String getSfdtb() {
		return sfdtb;
	}

	public void setSfdtb(String sfdtb) {
		this.sfdtb = sfdtb;
	}
	
	

}
