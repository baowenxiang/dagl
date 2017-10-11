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
import cn.proem.dagl.web.systemSetting.entity.BackUp;
import cn.proem.dagl.web.systemSetting.service.BackUpService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;
@Service
public class BackUpServiceImpl implements BackUpService {
	@Autowired
	private GeneralDao generalDao;
	@Override
	public DataGrid<BackUp> getBackUpDataGrid(QueryBuilder queryBuilder,int nowPage, int pageSize) {
		DataGrid<BackUp> dataGrid = new DataGrid<BackUp>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(BackUp.class, queryBuilder));
		
		List<BackUp> list = new ArrayList<BackUp>();
		list = generalDao.queryByCriteria(BackUp.class, queryBuilder, null,dataGrid.getStartRecord(), dataGrid.getPageSize());
		
		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	@Override
	@Transactional
	@LogService(description = "保存备份记录")
	public void saveBackUp(BackUp backup) throws ServiceException {
		if(StringUtil.isEmpty(backup)) {
			throw new ServiceException("备份数据为空");
		}
		generalDao.save(backup);
	}
	@Override
	@Transactional
	@LogService(description = "删除备份记录")
	public void deleteBackUp(String id) throws ServiceException {
		if(StringUtil.isEmpty(id)) {
			throw new ServiceException("备份记录ID为空");
		}
		generalDao.deleteById(id, BackUp.class);
	}
}
