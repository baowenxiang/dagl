package cn.proem.suw.web.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.log.eneity.CXFLog;
import cn.proem.suw.web.log.service.LogRecordService;


@Service
public class LogRecordServiceImpl implements LogRecordService {
	
	@Autowired
	private GeneralDao generalDao;
	
	@Override
	@Transactional
	public void save(CXFLog log) throws ServiceException{
		generalDao.save(log);

	}

	
}
