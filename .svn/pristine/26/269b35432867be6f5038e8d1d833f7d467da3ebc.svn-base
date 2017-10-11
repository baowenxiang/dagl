package cn.proem.dagl.web.fileIdentify.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.fileIdentify.dto.DtoIdentifyContent;
import cn.proem.dagl.web.fileIdentify.entity.IdentifyContent;
import cn.proem.dagl.web.fileIdentify.service.IdentifyInfoService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.util.StringUtil;

@Service
public class IdentifyInfoServiceImple implements IdentifyInfoService {
	@Autowired
	private GeneralDao generalDao;
	@Autowired
	private CustomArchiveService customArchiveService;
	
	private String valueOf(Object obj) {
	    return (obj == null) ? "" : obj.toString();
	}
	
	@Override
	public DataGrid<DtoIdentifyContent> getIdentifyInfoTables(QueryBuilder queryBuilder,int nowPage, int pageSize) {
		DataGrid<DtoIdentifyContent> dataGrid = new DataGrid<DtoIdentifyContent>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(IdentifyContent.class, queryBuilder));
		List<DtoIdentifyContent> dtoIdentifyContents = new ArrayList<DtoIdentifyContent>();
		
		List<IdentifyContent> list = generalDao.queryByCriteria(IdentifyContent.class, queryBuilder, null,dataGrid.getStartRecord(), dataGrid.getPageSize());
		for(IdentifyContent identifyContent : list){
			String dh; 
			try{
				BaseEntityInf entity = customArchiveService.getEntity(identifyContent.getBm(), identifyContent.getDaid());
				dh = this.valueOf(entity.get("dh"));
				
				DtoIdentifyContent dtoIdentifyContent = new DtoIdentifyContent(
						identifyContent.getId(),
						identifyContent.getBm(), 
						dh, 
						identifyContent.getDaid(), 
						identifyContent.getJdnr(), 
						identifyContent.getJdsj(), 
						identifyContent.getJdr());
			
				dtoIdentifyContents.add(dtoIdentifyContent);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		
		
		dataGrid.setExhibitDatas(dtoIdentifyContents);
		return dataGrid;
	}

	@Override
	public List<DtoIdentifyContent> getIdentifyInfoTables(
			QueryBuilder queryBuilder) {
		List<DtoIdentifyContent> dtoIdentifyContents = new ArrayList<DtoIdentifyContent>();
		
		List<IdentifyContent> list = generalDao.queryByCriteria(IdentifyContent.class, queryBuilder, null,0, -1);
		for(IdentifyContent identifyContent : list){
			String dh; 
			try{
				BaseEntityInf entity = customArchiveService.getEntity(identifyContent.getBm(), identifyContent.getDaid());
				dh = this.valueOf(entity.get("dh"));
				
				DtoIdentifyContent dtoIdentifyContent = new DtoIdentifyContent(
						identifyContent.getId(),
						identifyContent.getBm(), 
						dh, 
						identifyContent.getDaid(), 
						identifyContent.getJdnr(), 
						identifyContent.getJdsj(), 
						identifyContent.getJdr());
			
				dtoIdentifyContents.add(dtoIdentifyContent);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return dtoIdentifyContents;
	}
}
