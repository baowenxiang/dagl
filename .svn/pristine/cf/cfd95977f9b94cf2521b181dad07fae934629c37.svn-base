package cn.proem.dagl.web.fileNum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * @ClassName FileNumRule
 * @Description 档号生成规则
 * @author chenxiaofen
 * @date 2017年5月13日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_DAH_RULES")
public class FileNumRule extends MappedEntityModel {
	/**
	 * 档案类型
	 */
	@Column(name = "type")
	private String type;
	/**
	 * 序号
	 */
	@Column(name = "num")
	private String num;
	/**
	 * 值
	 */
	@Column(name = "value")
	private String value;
	/**
	 * 字段类型
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 当前已使用的流水号
	 */
	@Column(name = "serialNum")
	private int serialNum;
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
