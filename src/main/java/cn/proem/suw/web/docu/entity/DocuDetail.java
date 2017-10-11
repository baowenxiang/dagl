package cn.proem.suw.web.docu.entity;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;

/**
 * @ClassName Docu
 * @Description 文档表
 * @author Pan Jilong
 * @date 2017年3月29日
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "POA_DOCU_DETAIL")
public class DocuDetail implements BaseEntityInf{

	/**
	 * ID
	 */
	@Id
	@Column(name = "id", length = 40)
	private String id;

	@Column
	private Integer delFlag = 0;
	
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 档号
	 */
	@Column
	private String dh;
	/**
	 * 全宗号
	 */
	@Column
	private String qzh;
	/**
	 * 目录号
	 */
	@Column
	private String mlh;
	/**
	 * 所在页号
	 */
	@Column
	private String szyh;
	/**
	 * 分类号
	 */
	@Column
	private String flh;
	/**
	 * 档案馆代码
	 */
	@Column
	private String dagdm;
	/**
	 * 件号
	 */
	@Column
	private String jh;
	/**
	 * 归档年度
	 */
	@Column(nullable=false)
	private String gdnd;
	/**
	 * 电子文档号
	 */
	@Column
	private String dzwdh;
	/**
	 * 缩微号
	 */
	@Column
	private String swh;
	/**
	 * 题名
	 */
	@Column(nullable=false)
	private String tm;
	/**
	 * 成文日期
	 */
	@Column(nullable=false)
	private String cwrq;
	/**
	 * 文号
	 */
	@Column
	private String wh;
	/**
	 * 责任者
	 */
	@Column(nullable=false)
	private String zrz;
	/**
	 * 稿本
	 */
	@Column
	private String gb;
	/**
	 * 文种
	 */
	@Column
	private String wz;
	/**
	 * 密级
	 */
	@Column
	private String mj;
	/**
	 * 保管期限
	 */
	@Column
	private String bgqx;
	/**
	 * 载体规格
	 */
	@Column
	private String ztgg;
	/**
	 * 载体类型
	 */
	@Column
	private String ztlx;
	/**
	 * 载体数量
	 */
	@Column
	private String ztsl;
	/**
	 * 载体单位
	 */
	@Column
	private String ztdw;
	/**
	 * 主题词
	 */
	@Column
	private String ztc;
	/**
	 * 全文标识
	 */
	@Column
	private String qwbs;
	/**
	 * 相关部门
	 */
	@Column(nullable=false)
	private String xgjg;
	/**
	 * 业务档案类型
	 */
	@Column(nullable=false)
	private String xbjg;
	/**
	 * 控制符
	 */
	@Column
	private String kzf;
	/**
	 * 备注
	 */
	@Column
	private String bz;
	/**
	 * 流程发起主办部门
	 */
	@Column
	private String lcfqrszbm;
	/**
	 * 流程发起人
	 */
	@Column
	private String lcfqr;
	/**
	 * 文件内容
	 */
	@Column
	private String wjnr;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getZtsl() {
		return ztsl;
	}
	public void setZtsl(String ztsl) {
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
	public String getXgjg() {
		return xgjg;
	}
	public void setXgjg(String xgjg) {
		this.xgjg = xgjg;
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
	@Override
	public String toString() {
		return "Docu [id=" + id + ", dh=" + dh + ", qzh=" + qzh + ", mlh="
				+ mlh + ", szyh=" + szyh + ", flh=" + flh + ", dagdm=" + dagdm
				+ ", jh=" + jh + ", gdnd=" + gdnd + ", dzwdh=" + dzwdh
				+ ", swh=" + swh + ", tm=" + tm + ", cwrq=" + cwrq + ", wh="
				+ wh + ", zrz=" + zrz + ", gb=" + gb + ", wz=" + wz + ", mj="
				+ mj + ", bgqx=" + bgqx + ", ztgg=" + ztgg + ", ztlx=" + ztlx
				+ ", ztsl=" + ztsl + ", ztdw=" + ztdw + ", ztc=" + ztc
				+ ", qwbs=" + qwbs + ", xgjg=" + xgjg + ", xbjg=" + xbjg
				+ ", kzf=" + kzf + ", bz=" + bz + ", lcfqrszbm=" + lcfqrszbm
				+ ", lcfqr=" + lcfqr + ", wjnr=" + wjnr + "]";
	}
	
	public void set(String colname, Object val){
        Field field;
        try {
            field = this.getClass().getDeclaredField(colname);
            field.setAccessible(true);
            field.set(this, val);
        } catch (NoSuchFieldException e1) {
            try {
                field = this.getClass().getSuperclass().getSuperclass().getDeclaredField(colname);
                field.setAccessible(true);
                field.set(this, val);
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T>T get(String colname){
        Field field;
        try {
            field = this.getClass().getDeclaredField(colname);
            field.setAccessible(true);
            return (T) field.get(this);
        } catch (NoSuchFieldException e1) {
        	try {
                field = this.getClass().getSuperclass().getSuperclass().getDeclaredField(colname);
                field.setAccessible(true);
                return (T) field.get(this);
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
         
    }
}
