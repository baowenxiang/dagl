package cn.proem.dagl.web.message.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * EEP已封包
 * @author 
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pdagl_message_jdda")
public class Jdda extends MappedEntityModel{
	/**
	 * 档案ID
	 */
	@Column(name = "daid", unique=true)
	private String daid;
	
	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}
	
}