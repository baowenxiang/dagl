package cn.proem.dagl.web.fileStamp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.fileStamp.service.FileStampService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.service.YwgjService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;


@Service
@Transactional
public class FileStampServiceImpl implements FileStampService {
	@Autowired
    private GeneralDao generalDao;
	@Autowired
	private YwgjService ywgjService;
	
	@Override
	@Transactional
	@LogService(description = "增加档号章")
	public void doFileStamp(Long fileSize,String uuid, String stampPath) throws ServiceException{
		
		Ywgj ywgj = generalDao.findById(uuid, Ywgj.class);
		
		Ywgj newYwgj = new Ywgj();
		newYwgj.setCreateTime(ywgj.getCreateTime()); 
		newYwgj.setScrq(ywgj.getScrq());
		newYwgj.setScz(ywgj.getScz());
		newYwgj.setWjdx(fileSize);
		newYwgj.setWjdz(stampPath);
		newYwgj.setWjlx(ywgj.getWjlx());
		newYwgj.setWjm(ywgj.getWjm());
		newYwgj.setXsxh(ywgj.getXsxh());
		newYwgj.setZlsj(ywgj.getZlsj());
		
		
		ywgj.setDelFlag(1);
		generalDao.save(newYwgj);
		generalDao.update(ywgj);
	}
}
