package cn.proem.dagl.web.filePreview.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.filePreview.service.AttachPreviewService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.docu.entity.DocuAttachment;

/**
 * 获得附件文件
 * @author tangcc
 *
 */
@Service
@Transactional
public class AttachPreviewServiceImpl implements AttachPreviewService{
    @Autowired
    private GeneralDao generalDao;
    
    @Override
    public DocuAttachment getFile(String id) throws ServiceException {
        // 通过文件唯一主键查询附件表中的文件
        return generalDao.findById(id, DocuAttachment.class);
    }

	@Override
	public List<DocuAttachment> getDocuAttachmentList() {
		QueryBuilder qb = new QueryBuilder();
		qb.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		return generalDao.queryByCriteria(DocuAttachment.class, qb, null, 0, -1);
	}
    
    
}
