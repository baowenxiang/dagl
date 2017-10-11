package cn.proem.dagl.web.oaservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "T_GDWJ")
public class TGdwjObj extends MappedEntityModel {

	@Column(name = "formId")
	private String formId;     //档案随机码
	@Column(name = "dh")
	private String dh;	//档号
	@Column(name = "qzh")
	private String	qzh;  //全宗号
	@Column(name = "ndh")
	private String	ndh;    //年度号
	@Column(name = "mlh")
	private String	mlh;  	//目录号
	@Column(name = "szyh")
	private String	szyh;	///所在页号
	@Column(name = "flh")
	private String	flh;   	//分类号
	@Column(name = "dagdm")
	private String	dagdm;	 //档案馆代码
	@Column(name = "zzjgdm")
	private String	zzjgdm;	 //组织机构代码
	@Column(name = "zzwtdh")
	private String	zzwtdh;  //组织问题代号
	@Column(name = "jh")
	private String	jh; 	//件号
	@Column(name = "gdnd")
	private String	gdnd;	//归档年度
	@Column(name = "dzwdh")
	private String	dzwdh;	//电子文档号
	@Column(name = "swh")
	private String	swh; 	//微缩号
	@Column(name = "tm")
	private String	tm; 	//题名
	@Column(name = "cwrq")
	private String	cwrq;	//成文日期
	@Column(name = "wh")
	private String	wh;  	//文号
	@Column(name = "zrz")
	private String	zrz;	//责任者
	@Column(name = "gb")
	private String	gb; 	//稿本
	@Column(name = "wz")
	private String	wz; 	//文种
	@Column(name = "mj")
	private String	mj;	 //密级
	@Column(name = "bgqx")
	private String	bgqx;	//保管期限
	@Column(name = "ztgg")
	private String	ztgg;	//载体规格
	@Column(name = "ztlx")
	private String	ztlx;	//载体类型
	@Column(name = "ztsl")
	private Integer	ztsl;	//载体数量
	@Column(name = "ztdw")
	private String	ztdw;	//载体单位
	@Column(name = "ztc")
	private String	ztc;	//主题词
	@Column(name = "qwbs")
	private String	qwbs;	//全文标识
	@Column(name = "zbjg")
	private String	zbjg;	//主办机构
	@Column(name = "xbjg")
	private String	xbjg;	//协办机构
	@Column(name = "kzf")
	private String	kzf; 	//控制符
	@Column(name = "bz")
	private String	bz;  	//备注
	@Column(name = "sfygd")
	private String sfygd; //是否预归档
	@Column(name = "lcfqrszbm")
	private String lcfqrszbm;     //流程发起人所在部门
	@Column(name = "lcfqr")
	private String lcfqr;    //流程发起人
	@Column(name = "wjnr")
	private String wjnr;    //文件内容
	@Column(name = "isarchive")
	private int isArchive;	//是否归档文件
	public int getIsArchive() {
		return isArchive;
	}
	public void setIsArchive(int isArchive) {
		this.isArchive = isArchive;
	}
	@Column(name = "delflag")
	private int delflag;
	public int getDelflag() {
		return delflag;
	}
	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}
	@Column(name = "extField1")
	private String extField1;  //可扩展字段
	@Column(name = "extField2")
	private String extField2;  //可扩展字段
	@Column(name = "extField3")
	private String extField3;  //可扩展字段
	@Column(name = "extField4")
	private String extField4;  //可扩展字段
	@Column(name = "extField5")
	private String extField5;  //可扩展字段
	@Column(name = "extField6")
	private String extField6;  //可扩展字段
	@Column(name = "extField7")
	private String extField7;  //可扩展字段
	@Column(name = "extField8")
	private String extField8;  //可扩展字段
	@Column(name = "extField9")
	private String extField9;  //可扩展字段
	@Column(name = "extField10")
	private String extField10;  //可扩展字段
	@Column(name = "extField11")
	private String extField11;  //可扩展字段
	@Column(name = "extField12")
	private String extField12;  //可扩展字段
	@Column(name = "extField13")
	private String extField13;  //可扩展字段
	@Column(name = "extField14")
	private String extField14;  //可扩展字段
	@Column(name = "extField15")
	private String extField15;  //可扩展字段
	@Column(name = "extField16")
	private String extField16;  //可扩展字段
	@Column(name = "extField17")
	private String extField17;  //可扩展字段
	@Column(name = "extField18")
	private String extField18;  //可扩展字段
	@Column(name = "extField19")
	private String extField19;  //可扩展字段
	@Column(name = "extField20")
	private String extField20;  //可扩展字段
	@Column(name = "tysfjdpe")
	private String sfjd;	
	@Column(name = "ywurl")
	private String ywurl;//原文的链接信息，获取list时，需要填写
	public String getYwurl() {
		return ywurl;
	}
	public void setYwurl(String ywurl) {
		this.ywurl = ywurl;
	}
	public String getSfjd() {
		return sfjd;
	}
	public void setSfjd(String sfjd) {
		this.sfjd = sfjd;
	}
	private String bm; //用于添加或修改记录前的查询用
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getQzh() {
		return qzh;
	}
	public void setQzh(String qzh) {
		this.qzh = qzh;
	}
	public String getNdh() {
		return ndh;
	}
	public void setNdh(String ndh) {
		this.ndh = ndh;
	}
	public String getMlh() {
		return mlh;
	}
	public void setMlh(String mlh) {
		this.mlh = mlh;
	}
	public String getSzyh() {
		return szyh;
	}
	public void setSzyh(String szyh) {
		this.szyh = szyh;
	}
	public String getFlh() {
		return flh;
	}
	public void setFlh(String flh) {
		this.flh = flh;
	}
	public String getDagdm() {
		return dagdm;
	}
	public void setDagdm(String dagdm) {
		this.dagdm = dagdm;
	}
	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getZzwtdh() {
		return zzwtdh;
	}
	public void setZzwtdh(String zzwtdh) {
		this.zzwtdh = zzwtdh;
	}
	public String getJh() {
		return jh;
	}
	public void setJh(String jh) {
		this.jh = jh;
	}
	public String getGdnd() {
		return gdnd;
	}
	public void setGdnd(String gdnd) {
		this.gdnd = gdnd;
	}
	public String getDzwdh() {
		return dzwdh;
	}
	public void setDzwdh(String dzwdh) {
		this.dzwdh = dzwdh;
	}
	public String getSwh() {
		return swh;
	}
	public void setSwh(String swh) {
		this.swh = swh;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
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
	public String getZrz() {
		return zrz;
	}
	public void setZrz(String zrz) {
		this.zrz = zrz;
	}
	public String getGb() {
		return gb;
	}
	public void setGb(String gb) {
		this.gb = gb;
	}
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
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
	public String getZtgg() {
		return ztgg;
	}
	public void setZtgg(String ztgg) {
		this.ztgg = ztgg;
	}
	public String getZtlx() {
		return ztlx;
	}
	public void setZtlx(String ztlx) {
		this.ztlx = ztlx;
	}
	public Integer getZtsl() {
		return ztsl;
	}
	public void setZtsl(Integer ztsl) {
		this.ztsl = ztsl;
	}
	public String getZtdw() {
		return ztdw;
	}
	public void setZtdw(String ztdw) {
		this.ztdw = ztdw;
	}
	public String getZtc() {
		return ztc;
	}
	public void setZtc(String ztc) {
		this.ztc = ztc;
	}
	public String getQwbs() {
		return qwbs;
	}
	public void setQwbs(String qwbs) {
		this.qwbs = qwbs;
	}
	public String getZbjg() {
		return zbjg;
	}
	public void setZbjg(String zbjg) {
		this.zbjg = zbjg;
	}
	public String getXbjg() {
		return xbjg;
	}
	public void setXbjg(String xbjg) {
		this.xbjg = xbjg;
	}
	public String getKzf() {
		return kzf;
	}
	public void setKzf(String kzf) {
		this.kzf = kzf;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSfygd() {
		return sfygd;
	}
	public void setSfygd(String sfygd) {
		this.sfygd = sfygd;
	}
	public String getLcfqrszbm() {
		return lcfqrszbm;
	}
	public void setLcfqrszbm(String lcfqrszbm) {
		this.lcfqrszbm = lcfqrszbm;
	}
	public String getLcfqr() {
		return lcfqr;
	}
	public void setLcfqr(String lcfqr) {
		this.lcfqr = lcfqr;
	}
	public String getWjnr() {
		return wjnr;
	}
	public void setWjnr(String wjnr) {
		this.wjnr = wjnr;
	}
	public String getExtField1() {
		return extField1;
	}
	public void setExtField1(String extField1) {
		this.extField1 = extField1;
	}
	public String getExtField2() {
		return extField2;
	}
	public void setExtField2(String extField2) {
		this.extField2 = extField2;
	}
	public String getExtField3() {
		return extField3;
	}
	public void setExtField3(String extField3) {
		this.extField3 = extField3;
	}
	public String getExtField4() {
		return extField4;
	}
	public void setExtField4(String extField4) {
		this.extField4 = extField4;
	}
	public String getExtField5() {
		return extField5;
	}
	public void setExtField5(String extField5) {
		this.extField5 = extField5;
	}
	public String getExtField6() {
		return extField6;
	}
	public void setExtField6(String extField6) {
		this.extField6 = extField6;
	}
	public String getExtField7() {
		return extField7;
	}
	public void setExtField7(String extField7) {
		this.extField7 = extField7;
	}
	public String getExtField8() {
		return extField8;
	}
	public void setExtField8(String extField8) {
		this.extField8 = extField8;
	}
	public String getExtField9() {
		return extField9;
	}
	public void setExtField9(String extField9) {
		this.extField9 = extField9;
	}
	public String getExtField10() {
		return extField10;
	}
	public void setExtField10(String extField10) {
		this.extField10 = extField10;
	}
	public String getExtField11() {
		return extField11;
	}
	public void setExtField11(String extField11) {
		this.extField11 = extField11;
	}
	public String getExtField12() {
		return extField12;
	}
	public void setExtField12(String extField12) {
		this.extField12 = extField12;
	}
	public String getExtField13() {
		return extField13;
	}
	public void setExtField13(String extField13) {
		this.extField13 = extField13;
	}
	public String getExtField14() {
		return extField14;
	}
	public void setExtField14(String extField14) {
		this.extField14 = extField14;
	}
	public String getExtField15() {
		return extField15;
	}
	public void setExtField15(String extField15) {
		this.extField15 = extField15;
	}
	public String getExtField16() {
		return extField16;
	}
	public void setExtField16(String extField16) {
		this.extField16 = extField16;
	}
	public String getExtField17() {
		return extField17;
	}
	public void setExtField17(String extField17) {
		this.extField17 = extField17;
	}
	public String getExtField18() {
		return extField18;
	}
	public void setExtField18(String extField18) {
		this.extField18 = extField18;
	}
	public String getExtField19() {
		return extField19;
	}
	public void setExtField19(String extField19) {
		this.extField19 = extField19;
	}
	public String getExtField20() {
		return extField20;
	}
	public void setExtField20(String extField20) {
		this.extField20 = extField20;
	}
}
