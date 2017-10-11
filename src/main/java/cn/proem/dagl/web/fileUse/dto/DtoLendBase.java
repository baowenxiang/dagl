package cn.proem.dagl.web.fileUse.dto;

import cn.proem.core.entity.User;

public class DtoLendBase {
		//电子借阅id
		private String id;
		//题名
		private String tm;
		
		// 文号
		private String wh;
		
		//档号
		private String dh;
		
		//档案id
		private String daid;
		
		//备注
		private String bz;
		
		//借阅状态
		private String jyzt;
		
		//借阅目的
		private String jymd;
		
		//借阅时间
		private String jysj;
		
		//借阅效果
		private String jyxg;
		//表名
		private String bm;
		//表中文名
		private String cnbm;
		//借阅人
		private User  jyr;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getDh() {
			return dh;
		}
		public void setDh(String dh) {
			this.dh = dh;
		}
		public String getDaid() {
			return daid;
		}
		public void setDaid(String daid) {
			this.daid = daid;
		}
		public String getBz() {
			return bz;
		}
		public void setBz(String bz) {
			this.bz = bz;
		}
		public String getJyzt() {
			return jyzt;
		}
		public void setJyzt(String jyzt) {
			this.jyzt = jyzt;
		}
		public String getJymd() {
			return jymd;
		}
		public void setJymd(String jymd) {
			this.jymd = jymd;
		}
		public String getJysj() {
			return jysj;
		}
		public void setJysj(String jysj) {
			this.jysj = jysj;
		}
		public String getJyxg() {
			return jyxg;
		}
		public void setJyxg(String jyxg) {
			this.jyxg = jyxg;
		}
		public String getBm() {
			return bm;
		}
		public void setBm(String bm) {
			this.bm = bm;
		}
		public String getCnbm() {
			return cnbm;
		}
		public void setCnbm(String cnbm) {
			this.cnbm = cnbm;
		}
		public User getJyr() {
			return jyr;
		}
		public void setJyr(User jyr) {
			this.jyr = jyr;
		}
		
		
		
}
