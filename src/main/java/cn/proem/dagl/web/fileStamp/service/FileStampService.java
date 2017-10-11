package cn.proem.dagl.web.fileStamp.service;

import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.exception.ServiceException;

public interface FileStampService {
	public void doFileStamp(Long fileSize,String uuid,String stampPath) throws ServiceException ;
}
