package cn.proem.dagl.web.sysManage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.proem.core.entity.User;
import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * 用户详情表
 * @author bao
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_USER_DETAIL")
public class UserDetail extends MappedEntityModel{
	/**
	 * 电话号码
	 */
	@Column(name = "telphone")
	private String telphone;
	
	/**
	 * 称呼
	 */
	@Column(name = "appellation")
	private String appellation;
	
	/**
	 * 说明
	 */
	@Column(name = "comment2")
	private String comment;
	
	/**
	 * 身份证号
	 */
	@Column(name = "idCard")
	private String idCard;
	
	
	/**
	 * 所属用户
	 */
	@OneToOne
	@JoinColumn(name = "user2")
	private User user;
	
	/**
	 * 图片路径
	 */
	@Column(name = "picPath")
	private String picPath;
	
	/**
	 * 图片名
	 */
	@Column(name = "picName")
	private String picName;
	
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}



	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	
	
}
