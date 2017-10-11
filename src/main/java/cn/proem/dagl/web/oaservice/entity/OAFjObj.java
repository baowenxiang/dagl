package cn.proem.dagl.web.oaservice.entity;


public class OAFjObj {
	private String formId;//公文ID
	private String fileId;//文件ID
	private String fileSuffix;//文件后缀名
	private String fileName;//文件名称
	private String fileDir;//文件路径
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public TGdfjObj toGdfjObj(){
		TGdfjObj gdfjObj = new TGdfjObj();
		gdfjObj.setId(this.getFormId());
		gdfjObj.setFjmc(this.getFileName());
		gdfjObj.setFjhz(this.getFileSuffix());
		return gdfjObj;
	}
}
