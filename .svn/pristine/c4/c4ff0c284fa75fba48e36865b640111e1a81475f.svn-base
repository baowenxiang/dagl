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
import cn.proem.dagl.web.filePreview.service.UploadPreviewService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 获得原文文件
 * @author tangcc
 *
 */
@Service
@Transactional
public class UploadPreviewServiceImpl implements UploadPreviewService {
    @Autowired
    private GeneralDao generalDao;
    
    @Override
    public Ywgj getFile(String id) throws ServiceException {
        // 通过文件唯一主键查询原文表中的文件
        return generalDao.findById(id, Ywgj.class);
    }

	@Override
	public List<Ywgj> getYwgjFileList() {
		QueryBuilder qb = new QueryBuilder();
		qb.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		return generalDao.queryByCriteria(Ywgj.class, qb, null, 0, -1);
	}
    
    

}
