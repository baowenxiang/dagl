package cn.proem.dagl.web.dicManager.entity;

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
@Table(name="PDAGL_SYS_DICVALUE")
public class DictionaryValue extends MappedEntityModel {
	/**
	 * 字典项编号
	 */
	@ManyToOne
	@JoinColumn(name="did")
	private Dictionary dictionary;
	/**
	 * 字典值内容
	 */
	@Column
	private String dvalue;
	

	/**
	 * 字典值编号
	 */
	@Column
	private String dvno;
	/**
	 * 显示序号
	 */
	@Column
	private String xsxh;
	
	/**
	 * 字典值编号
	 * @return
	 */
	public String getDvno() {
		return dvno;
	}

	/**
	 * 字典值编号
	 * @param dvno
	 */
	public void setDvno(String dvno) {
		this.dvno = dvno;
	}

	/**
	 * 字典项编号
	 * @return
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}
	/**
	 * 字典项编号
	 * @param dictionary
	 */
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * @Description 字典内容
	 * @MethodName getDvalue
	 * @author bao
	 * @date 2017年5月16日
	 * @return String
	 */
	public String getDvalue() {
		return dvalue;
	}

	/**
	 * 字典内容
	 * @param dvalue
	 */
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	/**
	 * 显示序号
	 * @return
	 */
	public String getXsxh() {
		return xsxh;
	}

	/**
	 * 显示序号
	 * @param xsxh
	 */
	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	

}
