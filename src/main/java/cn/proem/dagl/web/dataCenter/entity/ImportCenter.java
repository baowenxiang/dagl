package cn.proem.dagl.web.dataCenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * 导入中心表
 * @author bao
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_IMPORT_CENTER")
public class ImportCenter extends MappedEntityModel{
	/**
	 * 档案表名
	 */
	@Column
	private String tablename;
	
	/**
	 * 档案中文名
	 */
	@Column
	private String tablenamecn;
	
	
	/**
	 * 上传路径
	 */
	@Column
	private String uploadpath;
	
	/**
	 * 上传的文件名
	 */
	private String filename;
	
	/**
	 * 状态值
	 */
	private Integer status;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getUploadpath() {
		return uploadpath;
	}

	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTablenamecn() {
		return tablenamecn;
	}

	public void setTablenamecn(String tablenamecn) {
		this.tablenamecn = tablenamecn;
	}
	
	
}
