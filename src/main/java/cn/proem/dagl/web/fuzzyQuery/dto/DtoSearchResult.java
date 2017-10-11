package cn.proem.dagl.web.fuzzyQuery.dto;

import java.util.Date;

public class DtoSearchResult {
	
	/**
     * @return the dalx
     */
    public String getDalx() {
        return dalx;
    }

    /**
     * @return the tm
     */
    public String getTm() {
        return tm;
    }

    /**
     * @return the zrz
     */
    public String getZrz() {
        return zrz;
    }

    /**
     * @return the cwrq
     */
    public String getCwrq() {
        return cwrq;
    }

    /**
     * @return the bgqx
     */
    public String getBgqx() {
        return bgqx;
    }

    /**
     * @param dalx the dalx to set
     */
    public void setDalx(String dalx) {
        this.dalx = dalx;
    }

    /**
     * @param tm the tm to set
     */
    public void setTm(String tm) {
        this.tm = tm;
    }

    /**
     * @param zrz the zrz to set
     */
    public void setZrz(String zrz) {
        this.zrz = zrz;
    }

    /**
     * @param cwrq the cwrq to set
     */
    public void setCwrq(String cwrq) {
        this.cwrq = cwrq;
    }

    /**
     * @param bgqx the bgqx to set
     */
    public void setBgqx(String bgqx) {
        this.bgqx = bgqx;
    }

    private String fileId;
	
	private String title;
	
	private String content;
	
	private String address;
	
	private String fileCreateTime;
	
	private Date time;
	
	private String dh;
	
	//档案类型、档号、题名、责任者、成文日期保管期限
	private String dalx;
	private String tm;
	private String zrz;
	private String cwrq;
	private String bgqx;
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	private String fileLastModifiedTime;

    private String dm;
	public String getFileCreateTime() {
		return fileCreateTime;
	}

	public void setFileCreateTime(String fileCreateTime) {
		this.fileCreateTime = fileCreateTime;
	}

	public String getFileLastModifiedTime() {
		return fileLastModifiedTime;
	}

	public void setFileLastModifiedTime(String fileLastModifiedTime) {
		this.fileLastModifiedTime = fileLastModifiedTime;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

    /**
     * @return the dh
     */
    public String getDh() {
        return dh;
    }

    /**
     * @param dh the dh to set
     */
    public void setDh(String dh) {
        this.dh = dh;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }
	
    public String getDm(){
        return this.dm;
    }
	

}
