package cn.proem.suw.web.workflow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * @ClassName FlowAttachment
 * @Description 流程附件实体类
 * @author Pan Jilong
 * @date 2017年2月28日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "POA_WORKFLOW_ATTACHMENT")
public class Attachment extends MappedEntityModel {
	/**
	 * 附件名称
	 */
	@Column
	private String name;
	/**
	 * 逻辑名称
	 */
	@Column(name = "logic_name")
	private String logicName;
	/**
	 * 路径
	 */
	@Column
	private String path;
	/**
	 * 所属流程
	 */
	@Column(name = "process_id")
	private String processId;
	/**
	 * 业务id
	 */
	@Column(name = "business_id")
	private String businessId;
	/**
	 * 所属部门
	 */
	@Column(name = "dept_id")
	private String deptId;
	/**
	 * 附件类型   P图片	F文件	S音频
	 */
	@Column
	private String type;
	/**
	 * 排序
	 */
	@Column(name = "order_num")
	private String order;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogicName() {
		return logicName;
	}
	public void setLogicName(String logicName) {
		this.logicName = logicName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
