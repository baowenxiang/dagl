package cn.proem.dagl.web.fileUse.entity;

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
@Table(name = "pfm_fileuse_humiture")
public class Humiture extends MappedEntityModel{
	/**
	 * 记录日期
	 */
	@Column(name = "recordtime")
	private String recordtime;
	
	/**
	 * 天气
	 */
	@Column(name = "wealth")
	private String wealth;
	
	/**
	 * 时间
	 */
	@Column(name = "time")
	private String time;
	
	/**
	 * 记录人
	 */
	@Column(name = "recorder")
	private String recorder;
	
	/**
	 * 温度
	 */
	@Column(name = "temp")
	private Integer temp;
	
	/**
	 * 湿度
	 */
	@Column(name = "humi")
	private Integer humi;
	
	/**
	 * 效果温度
	 */
	@Column(name = "restemp")
	private Integer restemp;
	
	/**
	 * 效果湿度
	 */
	@Column(name = "reshumi")
	private Integer reshumi;
	
	/**
	 * 采取措施
	 */
	@Column(name = "measure")
	private String measure;
	
	/**
	 * 备注
	 */
	@Column(name = "comment2")
	private String comment;

	
}