package cn.proem.dagl.web.preArchive.dto;

import java.util.List;


import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.entity.Zlsj;

public class DtoZlsj {
	
	//对应资料收集信息
	private Zlsj data;
	
	//对应原文集合
	private List<Ywgj> files;

	public Zlsj getData() {
		return data;
	}

	public void setData(Zlsj data) {
		this.data = data;
	}

	public List<Ywgj> getFiles() {
		return files;
	}

	public void setFiles(List<Ywgj> files) {
		this.files = files;
	}
	
	
	
}
