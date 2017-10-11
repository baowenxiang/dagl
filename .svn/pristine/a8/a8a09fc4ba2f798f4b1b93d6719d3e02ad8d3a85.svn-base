package cn.proem.dagl.web.filePreview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.filePreview.service.FilePreviewService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.docu.entity.DocuAttachment;
@Service
public class FilePreviewServiceImpl implements FilePreviewService {
	@Autowired
	private GeneralDao generalDao;
	@Override
	public DocuAttachment getFile(String id) throws ServiceException {
		return generalDao.findById(id, DocuAttachment.class);
	}

}
