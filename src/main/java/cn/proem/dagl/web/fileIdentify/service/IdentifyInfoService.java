package cn.proem.dagl.web.fileIdentify.service;


import java.util.List;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileIdentify.dto.DtoIdentifyContent;
import cn.proem.dagl.web.fileIdentify.entity.IdentifyContent;


public interface IdentifyInfoService {
	/**
	 * @Description 获得鉴定信息表格
	 * @MethodName getIdentifyInfoTables
	 * @author bao
	 * @date 2017年5月22日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<IdentifyContent>
	 */
	public DataGrid<DtoIdentifyContent> getIdentifyInfoTables(QueryBuilder queryBuilder,int nowPage, int pageSize);
	
	
	public List<DtoIdentifyContent> getIdentifyInfoTables(QueryBuilder queryBuilder);
}
