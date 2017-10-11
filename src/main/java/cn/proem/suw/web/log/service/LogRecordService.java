package cn.proem.suw.web.log.service;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.log.eneity.CXFLog;


public interface LogRecordService {
	
	public void save(CXFLog log) throws ServiceException;

}
