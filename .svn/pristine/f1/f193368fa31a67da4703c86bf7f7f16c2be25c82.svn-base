package cn.proem.dagl.web.fileUse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.fileUse.service.HumitureService;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.docu.entity.DocuAttachment;

@Service
public class HumitureServiceImpl implements HumitureService {
	@Autowired
	private GeneralDao generalDao;
	
	@Override
	public List<DocuAttachment> getAttachmentsByDocId(String docDetailId) {
		QueryBuilder queryBuilder = new QueryBuilder();
		List<DocuAttachment> docuAttachments;
		if(StringUtil.isEmpty(docDetailId)){
			docuAttachments  = new ArrayList<DocuAttachment>();
		}else{
			queryBuilder.addCondition(new QueryCondition("ysjid", docDetailId, ConditionType.EQ,FieldType.STRING,null));
			docuAttachments = generalDao.queryByCriteria(DocuAttachment.class, queryBuilder, null, 0, -1);
		}
		
		return docuAttachments;
	}

}
