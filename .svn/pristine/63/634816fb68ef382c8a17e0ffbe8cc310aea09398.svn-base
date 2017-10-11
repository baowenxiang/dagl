package cn.proem.dagl.web.systemSetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;
/**
 * @ClassName BackUp
 * @Description 备份实体类
 * @author chenxiaofen
 * @date 2017年5月10日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_BACK_UP")
public class BackUp extends MappedEntityModel {
	/**
	 * 文件名
	 */
	@Column(name = "file_name")
	private String fileName;
	/**
	 * 备份人员
	 */
//	@ManyToOne
	@Column(name = "user_name")
	private String userName;
	/**
	 * 备注
	 */
	@Column(name = "content")
	private String content;
	/**
	 * 备份存储地址
	 */
	@Column(name = "path")
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
