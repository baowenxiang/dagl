package cn.proem.dagl.web.fileControl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileControl.entity.FileControl;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.suw.web.common.exception.ServiceException;

@Service
public class FileControlServiceImpl implements FileControlService {
	
	@Autowired
	private GeneralDao generalDao;
	
	/**
	 * 保存档案划控信息
	 */
	@Override
	@Transactional
	@LogService(description = "保存档案划控信息")
	public void save(FileControl fileControl) {
		generalDao.save(fileControl);
		
	}
	
	/**
	 * 获取所有公开文档
	 */
	@Override
	public DataGrid<FileControl> getOpenedFileDataGrid(
			QueryBuilder queryBuilder, int nowPage, int pageSize) {
		DataGrid<FileControl> dataGrid = new DataGrid<FileControl>(nowPage,pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(FileControl.class, queryBuilder));
		
		List<FileControl> list = generalDao.queryByCriteria(FileControl.class, queryBuilder, null, dataGrid.getStartRecord(), dataGrid.getPageSize());
		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	/**
	 * 逻辑删除开放档案
	 * 
	 */
	@Override
	@Transactional
	@LogService(description = "逻辑删除开放档案")
	public void deleteOpenedFile(String id) throws ServiceException{
		FileControl fileControl = generalDao.findById(id, FileControl.class);
		fileControl.setDelFlag(1);
		generalDao.update(fileControl);
		
	}

    @Override
    public boolean hasField(String tablename, String fieldname) throws ServiceException {
        String sql = "select * from v_pdagl_bdy where bm = '"+ tablename +"' and zdywm = '" + fieldname + "'";
        Map<String, Object> obj = generalDao.getObject(sql);
        if(obj != null && obj.size() > 0){
            return true;
        }
        return false;
    }
	
	

}
