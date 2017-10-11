package cn.proem.suw.web.log.eneity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_DOCU_LOG")
public class CXFLog extends MappedEntityModel {
	/**
	 * 文档或附件相关的id
	 */
	@Column
	private String refId;
	/**
	 * 标题
	 */
	@Column
	private String title;
	/**
	 * 信息
	 */
	@Column
	private String msg = "操作成功";
	/**
	 * 状态
	 */
	@Column
	private Integer state = 0;
	/**
	 * 接口名
	 */
	@Column
	private String infName;
	/**
	 * 方法名
	 */
	@Column
	private String methodName;

	

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getInfName() {
		return infName;
	}

	public void setInfName(String infName) {
		this.infName = infName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
}
