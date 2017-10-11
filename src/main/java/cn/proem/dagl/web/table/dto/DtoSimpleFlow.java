package cn.proem.dagl.web.table.dto;

import javax.persistence.Column;

/**
 * @ClassName DtoSimpleFlow
 * @Description 流程实例列表使用的信息
 * @author Pan Jilong
 * @date 2017年2月25日
 */
public class DtoSimpleFlow {

	private String currentNodeName;		//当前结点名称
	private String uuid;//主键
	private String businessId;					//业务id
	private String tm;//题名
	private String zrz;//责任者
	private String cwrq;//成文日期
	private String wh;//文号
	private String dh;//档号
	private String dataId;//资料Id
	private String archiveType;//归档类型
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getCurrentNodeName() {
		return currentNodeName;
	}
	public void setCurrentNodeName(String currentNodeName) {
		this.currentNodeName = currentNodeName;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getZrz() {
		return zrz;
	}
	public void setZrz(String zrz) {
		this.zrz = zrz;
	}
	public String getCwrq() {
		return cwrq;
	}
	public void setCwrq(String cwrq) {
		this.cwrq = cwrq;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getArchiveType() {
		return archiveType;
	}
	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
	}
	
	
	
}
