package cn.proem.dagl.web.common.entity;

import cn.proem.dagl.web.table.entity.inf.Tag;

public class Radio implements Tag {
	/**
	 * 标签
	 */
	private String html;
	/**
	 * 标题
	 */
	private String title;


	/**
	 * 值
	 */
	private String val;
	/**
	 * 字段名
	 */
	private String name;
	/**
	 * 是否可编辑
	 */
	private boolean isEditable;
	private boolean isRequired;
	private boolean isHide;
	public Radio(){
		this.html = "<div class=\"layui-form-item\">\n"
				  + "	<label class=\"layui-form-label\">NOTNULL%s</label>\n"
				  + "	<div class=\"layui-input-block\">\n"
//				  + "		<input type=\"radio\" name=\"sex\" value=\"男\" title=\"男\">"
				  + "			%s"
				  + "	</div>\n"
				  + "</div>\n";
		this.isEditable = true;
		this.isHide = false;
		this.isRequired = false;
	}
	@Override
	public String html() {
		if(isHide) {
			this.html = html.replace("isHide", "hide");
		}else {
			this.html = html.replace("isHide", "");
		}
		if(isEditable) {
			this.html = html.replace("isReadonly", "");
		}else {
			this.html = html.replace("isReadonly", "readonly");
		}
		if(isRequired) {
			this.html = html.replace("isRequired", "required");
			this.html = html.replace("NOTNULL","<span class=\"notNull\"> * </span>");
		}else {
			this.html = html.replace("isRequired", "");
			this.html = html.replace("NOTNULL", "");
		}
		return String.format(this.html,this.title,this.val);
	}

	@Override
	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void set(String name, Object obj) {
	}
	@Override
	public void setHide(boolean tf) {
		this.isHide = tf;
	}
	@Override
	public void setIsEditable(boolean tf) {
		this.isEditable = tf;
	}
	@Override
	public void setRequired(boolean tf) {
		this.isRequired = tf;
	}

}
