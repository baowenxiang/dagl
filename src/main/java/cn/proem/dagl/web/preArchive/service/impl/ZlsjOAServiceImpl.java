package cn.proem.dagl.web.preArchive.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.oaservice.entity.TGdwjObj;
import cn.proem.dagl.web.preArchive.service.ZlsjOAService;
import cn.proem.suw.web.common.exception.ServiceException;

@Service
public class ZlsjOAServiceImpl implements ZlsjOAService {
	@Autowired
	private GeneralDao generalDao;

    @Override
    public DataGrid<TGdwjObj> getZlsjOADataGrid(QueryBuilder queryBuilder, int nowPage, int pageSize) {
        DataGrid<TGdwjObj> dataGrid = new DataGrid<TGdwjObj>(nowPage, pageSize);
        dataGrid.setRecordCount(generalDao.countByCriteria(TGdwjObj.class, queryBuilder));
        List<TGdwjObj> list = new ArrayList<TGdwjObj>();
        list = generalDao.queryByCriteria(TGdwjObj.class, queryBuilder, new Order[]{new Order("cwrq",OrderType.ASC)},dataGrid.getStartRecord(), dataGrid.getPageSize());
        dataGrid.setExhibitDatas(list);
        return dataGrid;
    }

    @Override
    @Transactional
    @LogService(description = "删除对应资料")
    public void deleteZlsj(String zlsjId) throws ServiceException {
        TGdwjObj zlsj = generalDao.findById(zlsjId,TGdwjObj.class);
        zlsj.setDelFlag(1);
        generalDao.update(zlsj);
    }
	
}
