package cn.proem.suw.web.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName BaseEntity
 * @Description 数据库映射基本字段
 * @author Pan Jilong
 * @date 2016年12月30日
 */
@MappedSuperclass
public class MappedEntityModel {
	/**
	 * 主键 uuid
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 40)
	protected String id;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = false)
	protected Date createTime = new Date();
	
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	protected Date updateTime = new Date();
	
	/**
	 * 创建人id
	 */
	@Column(name = "create_id", length = 40)
	protected String createId;
	
	/**
	 * 更新人id
	 */
	@Column(name = "update_id", length = 40)
	protected String updateId;
	
	/**
	 * 逻辑删除标识 0: 未删除, 1:已删除
	 */
	@Column(name = "del_flag")
	protected int delFlag = 0;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createId='" + createId + '\'' +
				", updateId='" + updateId + '\'' +
				", delFlag=" + delFlag +
				'}';
	}
}
