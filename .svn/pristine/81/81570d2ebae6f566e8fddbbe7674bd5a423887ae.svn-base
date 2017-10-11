package cn.proem.dagl.web.message.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.proem.core.entity.User;
import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="PFM_MESSAGE")
public class Message extends MappedEntityModel {
	
	/**
	 * 发送者id
	 */
	@ManyToOne
	@JoinColumn(name="sendUser_id")
	private User sendUser;
	/**
	 * 接受者id
	 */
	@ManyToOne
	@JoinColumn(name="receUser_id")
	private User receUser;
	/**
	 * 发送时间
	 */
	@Column(name="sendTime")
	private Date sendTime;
	/**
	 * 接收时间
	 */
	@Column(name="receTime")
	private Date receTime;
	/**
	 * 消息内容
	 */
	@Column(name="content")
	private String content;
	/**
	 * 消息是否被读：isRead_0未读，isRead_1已读
	 */
	@Column(name="isRead")
	private String isRead;
	/**
	 * 消息类型
	 */
	@Column(name="type")
	private String type;




	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public User getReceUser() {
		return receUser;
	}

	public void setReceUser(User receUser) {
		this.receUser = receUser;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceTime() {
		return receTime;
	}

	public void setReceTime(Date receTime) {
		this.receTime = receTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
	

}
