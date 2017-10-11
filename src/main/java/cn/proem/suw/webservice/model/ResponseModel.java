package cn.proem.suw.webservice.model;

public class ResponseModel {

	private boolean success = Boolean.TRUE;
	private String msg = "操作成功";
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
