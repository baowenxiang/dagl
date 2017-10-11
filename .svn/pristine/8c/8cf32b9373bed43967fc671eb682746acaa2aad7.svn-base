package cn.proem.dagl.web.systemSetting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.log.OperationLog;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
//import cn.proem.dagl.web.systemSetting.entity.UserLog;
import cn.proem.dagl.web.systemSetting.service.UserLogService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class UserLogServiceImpl implements UserLogService {
	@Autowired
	private GeneralDao generalDao;
//	@Override
//	@Transactional
//	public void saveLog(UserLog log) throws ServiceException {
//		if(StringUtil.isEmpty(log)) {
//			throw new ServiceException("异常信息为空");
//		}
////		generalDao.save(log);
//	}
	@Override
	public DataGrid<OperationLog> getUserLogDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize) {
		DataGrid<OperationLog> dataGrid = new DataGrid<OperationLog>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(OperationLog.class, queryBuilder));
		
		List<OperationLog> list = new ArrayList<OperationLog>();
		list = generalDao.queryByCriteria(OperationLog.class, queryBuilder, new Order[] { new Order("createTime", OrderType.DESC) },dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	@Override
	@Transactional
	@LogService(description = "删除用户日志")
	public void deleteUserLog(String id) throws ServiceException {
		if(StringUtil.isEmpty(id)) {
			throw new ServiceException("日志id不能为空");
		}
//		OperationLog log = generalDao.findById(id,OperationLog.class);
//		log.setDelFlag(1);
//		generalDao.update(log);
		generalDao.deleteById(id, OperationLog.class);
	}
}
