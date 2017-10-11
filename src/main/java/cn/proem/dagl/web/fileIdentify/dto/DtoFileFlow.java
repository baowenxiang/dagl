package cn.proem.dagl.web.fileIdentify.dto;

public class DtoFileFlow {
	//流程业务id
	private String businessId;
		
	//节点名称
	private String nodeName;
	
	private String uuid;//档案的唯一标识
	
	private String dh;//档号
	
	private String tm;//题名
	
	private String wh;//文号
	
	private String zrz;//责任者
	
	private String cwrq;//成文日期
	
	private String mj;//密级
	
	private String bgqx;//保管期限
	
	private String oldCompany;
	
	private String newCompany;
	
	
	private String newbgqx;//申请修改的保管期限

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
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

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getBgqx() {
		return bgqx;
	}

	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNewbgqx() {
		return newbgqx;
	}

	public void setNewbgqx(String newbgqx) {
		this.newbgqx = newbgqx;
	}
	
	public String getOldCompany() {
		return oldCompany;
	}

	public void setOldCompany(String oldCompany) {
		this.oldCompany = oldCompany;
	}

	public String getNewCompany() {
		return newCompany;
	}

	public void setNewCompany(String newCompany) {
		this.newCompany = newCompany;
	}

	
	
	
}
