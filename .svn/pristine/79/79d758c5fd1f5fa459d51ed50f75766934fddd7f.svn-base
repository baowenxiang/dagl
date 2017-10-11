package cn.proem.dagl.web.systemSetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * @ClassName SystemLog
 * @Description 系统日志实体类
 * @author chenxiaofen
 * @date 2017年5月9日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_SYSTEM_LOG")
public class SystemLog extends MappedEntityModel {
	/**
	 * 日志保存时间
	 */
	@Column(name = "time")
	private String time;
	/**
	 * 日志类型
	 */
	@Column(name = "type")
	private String type;
	/**
	 * 日志内容
	 */
	@Column(name = "content")
	private String content;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
