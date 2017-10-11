package cn.proem.dagl.web.systemSetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;
/**
 * @ClassName SkinSetting
 * @Description 系统设置
 * @author chenxiaofen
 * @date 2017年5月13日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PDAGL_SKIN")
public class SkinSetting extends MappedEntityModel {
	/**
	 * 人员id
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 皮肤对应的class
	 */
	@Column(name = "skinClass")
	private String skinClass;
	/**
	 * 平台首页名称
	 */
	@Column(name = "plate_lg_name")
	private String plateLgName;
	/**
	 * 平台首页缩略名
	 */
	@Column(name = "plate_mini_name")
	private String plateMiniName;
	/**
	 * 登录页面背景图
	 */
	@Column(name = "bg_pic_path")
	private String bgPicPath;
	/**
	 * 平台首页logo
	 */
	@Column(name = "header_pic_path")
	private String headerPicPath;
	/**
	 * 平台皮肤图片
	 */
	@Column(name = "skin_pic_path")
	private String skinPicPath;
	public String getPlateLgName() {
		return plateLgName;
	}
	public void setPlateLgName(String plateLgName) {
		this.plateLgName = plateLgName;
	}
	public String getPlateMiniName() {
		return plateMiniName;
	}
	public void setPlateMiniName(String plateMiniName) {
		this.plateMiniName = plateMiniName;
	}
	public String getBgPicPath() {
		return bgPicPath;
	}
	public void setBgPicPath(String bgPicPath) {
		this.bgPicPath = bgPicPath;
	}
	public String getHeaderPicPath() {
		return headerPicPath;
	}
	public void setHeaderPicPath(String headerPicPath) {
		this.headerPicPath = headerPicPath;
	}
	public String getSkinPicPath() {
		return skinPicPath;
	}
	public void setSkinPicPath(String skinPicPath) {
		this.skinPicPath = skinPicPath;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSkinClass() {
		return skinClass;
	}
	public void setSkinClass(String skinClass) {
		this.skinClass = skinClass;
	}
}
