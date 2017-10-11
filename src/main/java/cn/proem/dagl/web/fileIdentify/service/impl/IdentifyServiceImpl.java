package cn.proem.dagl.web.fileIdentify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.fileIdentify.entity.FileIdentify;
import cn.proem.dagl.web.fileIdentify.entity.IdentifyContent;
import cn.proem.dagl.web.fileIdentify.service.IdentifyService;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * 档案鉴定控制层
 * @author lenovo
 *
 */
@Service
public class IdentifyServiceImpl implements IdentifyService {
	@Autowired
	private GeneralDao generalDao;

	@Override
	public List<DTableName> getAllDtablename() {
		
		return generalDao.queryByCriteria(DTableName.class, new QueryBuilder(), null, 0, -1);
	}
	
	
	@Override
	@Transactional
	@LogService(description = "保存档案鉴定信息")
	public String saveFileIdentify(FileIdentify fileIdentify) throws ServiceException {
		return generalDao.save(fileIdentify);
	}
	
	@Override
	@Transactional
	@LogService(description = "保存鉴定内容")
	public String saveIdentifyContent(IdentifyContent identifyContent) throws ServiceException {
		return generalDao.save(identifyContent);
	}
	
}	
