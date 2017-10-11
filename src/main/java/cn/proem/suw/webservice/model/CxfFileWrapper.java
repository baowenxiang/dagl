package cn.proem.suw.webservice.model;

import javax.activation.DataHandler;

public class CxfFileWrapper {
	//附件ID
	private String id;
	//目录ID
	private String ysjid;
	// 文件名
    private String fileName = null;
    // 文件扩展名
    private String fileExtension = null;
    // 文件二进制数据
    private DataHandler file = null;

    public final String getFileName() {
        return fileName;
    }

    public final void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public final String getFileExtension() {
        return fileExtension;
    }

    public final void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public final DataHandler getFile() {
        return file;
    }

    public final void setFile(DataHandler file) {
        this.file = file;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYsjid() {
		return ysjid;
	}

	public void setYsjid(String ysjid) {
		this.ysjid = ysjid;
	}
}
