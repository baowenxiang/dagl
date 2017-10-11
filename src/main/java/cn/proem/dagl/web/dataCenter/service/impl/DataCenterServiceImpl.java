package cn.proem.dagl.web.dataCenter.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dataCenter.entity.ImportCenter;
import cn.proem.dagl.web.dataCenter.service.DataCenterService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;
@Service
public class DataCenterServiceImpl implements DataCenterService {
	@Autowired
	private GeneralDao generalDao;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private DicManagerService dicService;

	@Override
	public DataGrid<ImportCenter> getImportCenterList(QueryBuilder queryBuilder, int nowPage, int pageSize) {
		DataGrid<ImportCenter> dataGrid = new DataGrid<ImportCenter>(nowPage, pageSize);
		
		dataGrid.setRecordCount(generalDao.countByCriteria(ImportCenter.class, queryBuilder));
		dataGrid.setExhibitDatas(
				generalDao.queryByCriteria(ImportCenter.class, queryBuilder,null,dataGrid.getStartRecord() , dataGrid.getPageSize())
		);
		
		return dataGrid;
	}

	@Override
	@Transactional
	@LogService(description = "保存导入中心数据")
	public String saveImportCenter(ImportCenter importCenter)throws ServiceException {
		return generalDao.save(importCenter);
	}

	@Override
	@Transactional
	@LogService(description = "根据主键删除导入中心数据")
	public void deleteImportCenter(String id) throws ServiceException {
		ImportCenter importCenter = generalDao.findById(id,ImportCenter.class);
		importCenter.setDelFlag(1);
		generalDao.update(importCenter);
		
	}

	
	/**
	 * @Title getFilePath
	 * @Description 文件资源所在路径
	 * @author Pan Jilong
	 * @date 2017年1月9日
	 * @return
	 */
	public String getFilePath() {
		String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
        String tempdir;
        String classPath[] = classesPath.split("webapps");
        tempdir = classPath[0];
        tempdir += "webapps/";
        tempdir += File.separator;
        return tempdir;
	}


	@Override
	public CommonFile exportDefFromTable(List<BaseEntityInf> records,String tablename) throws Exception {

		// 获得表的定义
		String tabledefined = "pdagl_tablename";
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("bm", tablename);
		BaseEntityInf table = customArchiveService.getEntityByConditions(tabledefined, conditions);
		// 获得表的所有字段
		conditions = new HashMap<String, Object>();
		conditions.put("tableNameId", table.get("id"));
		List<BaseEntityInf> fields = customArchiveService.getEntitiesByConditions("pdagl_tablefield", conditions);
		
        // 获得所有数据
        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
		HSSFSheet sheet = workbook.createSheet();		//生成工作页
		sheet.setDefaultColumnWidth(15);				//sheet默认列宽
		HSSFCellStyle style = workbook.createCellStyle();	//生成样式
		
		HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
		cellStyle.setWrapText(true);
		
		//导出excel的样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        style.setFont(font);
        
       /* //生成表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < fields.size(); i++) {
        	
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString((String)(fields.get(i).get("zdzwm")));
			cell.setCellStyle(style);
			cell.setCellValue(text);
       
        }
        
        
		if(records != null && records.size()>0){
			for(int j = 0; j < records.size(); j ++) {
				row = sheet.createRow(j+1);
				for (int i = 0; i < fields.size(); i++) {
					HSSFCell hssfCell = row.createCell(i);	//生成列   
					String value;
					if(records.get(j).get((String)fields.get(i).get("zdywm")) == null){
						value = "";
					}else{
						value = String.valueOf(records.get(j).get((String)fields.get(i).get("zdywm")));
					}
					hssfCell.setCellValue(value);
				}
			}
		}*/
		
        for(int i = 0;i<fields.size();i++){
        	if("fieldid".equalsIgnoreCase((String)fields.get(i).get("zdywm"))){
        		fields.remove(i); 
			}
        }
        
        //Excel行
        
		for(int i = 0;i<records.size()+1;i++){
			
			
			HSSFRow row = sheet.createRow(i);
			for(int j = 0;j<fields.size();j++){
				
				
				BaseEntityInf field = fields.get(j);
				
				
				HSSFCell cell = row.createCell(j);
				//绘制表头
				if(i == 0){
					HSSFRichTextString text = new HSSFRichTextString((String)(field.get("zdzwm")));
					cell.setCellStyle(style);
					cell.setCellValue(text);
					continue;
				}
				
				
				BaseEntityInf record = records.get(i-1);
				
				 // 判断列是否是字典值
                String did = field.get("did");
                // 列标题编辑
                String zdywm = field.get("zdywm");
                String value;
                
                //判断字典项
				if(did == null || "".equals(did)){
                    // 列值编辑
                    if(record.get(zdywm)!=null){
                    	value = record.get(zdywm);
                    }else{
                    	value = "";
                    }
                }else{
                    if("4".equals(field.get("zdlx"))){
                        String vals = record.get(zdywm);
                        if(vals!=null){
                            String rets[] = vals.split(",");
                            vals = "";
                            vals = this.getDicTitle(did, rets[0]);
                            for(int k=1;k<rets.length;k++){
                                vals += "," + this.getDicTitle(did, rets[k]);
                            }
                            value = vals;
                        }else{
                        	value = "";
                        }
                    }else{
                    	value = this.getDicTitle(did, (String) record.get(zdywm));
                    }
                }
				
				
				cell.setCellValue(value);
				
				
			}
		}
		
		String realpath = Path.UPLOAD_IMPORT_FILE_PATH + UUID.randomUUID().toString() + ".xls";	//
		String filename = tablename + ".xls";
		
		File fileTo = new File(this.getFilePath()+realpath);
		if (!fileTo.exists()) {
			fileTo.mkdirs();
		}
		fileTo.delete();
		//本地
		OutputStream outputStream = new FileOutputStream(new File(this.getFilePath()+realpath));
		workbook.write(outputStream);
		outputStream.close();
		
		CommonFile commonFile = new CommonFile(realpath, filename);
		
		return commonFile;
	
	}

	@Override
	@Transactional
	@LogService(description = "修改导入中心数据")
	public void updateImportCenter(ImportCenter importCenter)throws ServiceException {
		if (StringUtil.isEmpty(importCenter.getId())) {
			throw new ServiceException("导入中心数据id不能为空");
		}
		generalDao.update(importCenter);
		
	}

		
	/**
	 * 获得字典标题值
	 * @param did
	 * @param val
	 * @return
	 */
	private String getDicTitle(String did, String val){
	    List<DictionaryValue> dictionaryValues = dicService
                .getDicValueList(did);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(val)){
	            return dic.getDvalue();
	        }
	    }
	    return null;
	}
}
