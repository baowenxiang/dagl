package cn.proem.dagl.web.fileCompilation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.proem.core.entity.User;
import cn.proem.suw.web.common.model.MappedEntityModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PFM_COMPILE_RESULT_MIDDLE")
public class CompileResultMiddle extends MappedEntityModel{
	
	/**
	 * 表名
	 */
	@Column(name = "bm")
	private String bm;
	
	/**
	 * 档案id
	 */
	@Column(name = "daid")
	private String daid;
	
	
	/**
	 * 编研成果id
	 */
	@ManyToOne
	@JoinColumn(name = "compileResuleId")
	private CompileResult compileResult;

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}

	public CompileResult getCompileResult() {
		return compileResult;
	}

	public void setCompileResult(CompileResult compileResult) {
		this.compileResult = compileResult;
	}
	
	
	
}


