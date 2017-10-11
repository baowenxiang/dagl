package cn.proem.dagl.web.oaservice.entity;



public class OAGwObj {
	private String formId;//公文单ID..0
	private String tm;//文件标题..1..tm
	private String flh;//公文类型..3..flh
	private String wh;//公文文号..5..wh
	private String mj;//文件密级..7..mj
	private String zrz;//发文单位;主送单位..14;11..zrz1;zrz2
	private String cwrq;//签发日期..16..cwrq
	private String ztc;//主题词..19..ztc
//	private String cjrq;//创建日期..21..cjrq
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getFlh() {
		return flh;
	}
	public void setFlh(String flh) {
		this.flh = flh;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
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
	public String getZtc() {
		return ztc;
	}
	public void setZtc(String ztc) {
		this.ztc = ztc;
	}
	public TGdwjObj toGdwjObj(){
		
		TGdwjObj gdwjObj = new TGdwjObj();
		
		gdwjObj.setFormId(this.getFormId());
		gdwjObj.setTm(this.getTm());
		gdwjObj.setFlh(this.getFlh());
		gdwjObj.setWh(this.getWh());
		gdwjObj.setMj(this.getMj());
		gdwjObj.setZrz(this.getZrz());
		gdwjObj.setCwrq(this.getCwrq());
		gdwjObj.setZtc(this.getZtc());
//		gdwjObj.setExtField1(this.getCjrq());
		
		return gdwjObj;
	}
}
