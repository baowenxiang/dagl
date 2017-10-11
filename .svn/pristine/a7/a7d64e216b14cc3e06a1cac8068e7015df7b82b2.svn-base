package cn.proem.suw.web.common.model;

import java.util.List;

/**
 * @ClassName ResultModel
 * @Description 返回值对象模型
 * @author Tcc
 * @date 2017年4月17日
 */
public class ResultModel<T> {

	private boolean success = true;		//是否成功	:: true/false
	private String msg = "操作成功!";	//信息描述	:: 名称不能为空...
	private String errorCode;			//错误代码
	private List<T> datas;				//数据集合
	private String params;
	private T data;
	
	
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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
