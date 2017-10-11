package cn.proem.dagl.web.systemSetting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.systemSetting.entity.SystemLog;
import cn.proem.dagl.web.systemSetting.service.SystemErrorLogService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class SystemErrorLogServiceImpl implements SystemErrorLogService {
	@Autowired
	private GeneralDao generalDao;
	@Override
	@Transactional
	@LogService(description = "保存系统日志")
	public void saveLog(SystemLog log) throws ServiceException {
		if(StringUtil.isEmpty(log)) {
			throw new ServiceException("异常信息为空");
		}
		generalDao.save(log);
	}
	@Override
	public DataGrid<SystemLog> getErrorDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize) {
		DataGrid<SystemLog> dataGrid = new DataGrid<SystemLog>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(SystemLog.class, queryBuilder));
		
		List<SystemLog> list = new ArrayList<SystemLog>();
		list = generalDao.queryByCriteria(SystemLog.class, queryBuilder, null,dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	@Override
	@Transactional
	@LogService(description = "修改系统日志")
	public void deleteErrorLog(String id) throws ServiceException {
		SystemLog log = generalDao.findById(id,SystemLog.class);
		log.setDelFlag(1);
		generalDao.update(log);
	}
}
