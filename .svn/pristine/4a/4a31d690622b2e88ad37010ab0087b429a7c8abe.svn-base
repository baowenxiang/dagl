package cn.proem.dagl.web.fileUse.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.fileUse.dto.DtoLendBase;
import cn.proem.dagl.web.fileUse.entity.ElectronicLend;
import cn.proem.dagl.web.fileUse.service.ElectronicLendService;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.temperature.entity.DWsd;
@Service
public class ElectronicLendServiceImpl implements ElectronicLendService {
	@Autowired
	private GeneralDao generalDao;
    @Autowired
	private CustomArchiveService customArchiveService;
	
	@Override
	public DataGrid<DtoLendBase> getElectronicLendList(QueryBuilder queryBuilder, int nowPage, int pageSize) {
		DataGrid<DtoLendBase> dataGrid = new DataGrid<DtoLendBase>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(ElectronicLend.class, queryBuilder));
		
		List<ElectronicLend> list = generalDao.queryByCriteria(ElectronicLend.class, queryBuilder, null,dataGrid.getStartRecord(), dataGrid.getPageSize());
		List<DtoLendBase> dtoLendBases = new ArrayList<DtoLendBase>();
		for(ElectronicLend electronicLend : list){
			DtoLendBase dtoLendBase = new DtoLendBase();
			dtoLendBase.setId(electronicLend.getId());
			dtoLendBase.setBm(electronicLend.getBm());
			DTableName dTableName =  customArchiveService.getDTableNameByTableName(electronicLend.getBm());
			if(dTableName!=null){
				dtoLendBase.setCnbm(dTableName.getZwm());
			}else{
				dtoLendBase.setCnbm("");
			}
			dtoLendBase.setWh(electronicLend.getWh());
			dtoLendBase.setTm(electronicLend.getTm());
			dtoLendBase.setBz(electronicLend.getBz());
			dtoLendBase.setDaid(electronicLend.getDaid());
			dtoLendBase.setDh(electronicLend.getDh());
			dtoLendBase.setJyzt(electronicLend.getJyzt());
			dtoLendBase.setJymd(electronicLend.getJymd());
			dtoLendBase.setJysj(electronicLend.getJysj());
			dtoLendBase.setJyxg(electronicLend.getJyxg());
			dtoLendBase.setJyr(electronicLend.getJyr());
			
			dtoLendBases.add(dtoLendBase);
		}
		dataGrid.setExhibitDatas(dtoLendBases);
		
		return dataGrid;
	}

	@Override
	@Transactional
	@LogService(description = "保存或者修改电子借阅")
	public void saveOrUpdate(ElectronicLend electronicLend)throws ServiceException {
		if (StringUtil.isEmpty(electronicLend.getId())) {
			generalDao.save(electronicLend);
		}else{
			generalDao.update(electronicLend);
		}
		
	}
	
	
	@Override
	@Transactional
	@LogService(description = "删除电子借阅记录")
	public void delete(String id) throws ServiceException {
		ElectronicLend electronicLend = generalDao.findById(id,ElectronicLend.class);
		electronicLend.setDelFlag(1);
		generalDao.update(electronicLend);
	}

	@Override
	public CommonFile exportDefFromTable(List<String> idList) throws Exception {
		
		
		// 获得所有数据
        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
		HSSFSheet sheet = workbook.createSheet("电子借阅导出记录");		//生成工作页
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
		
		
		 HSSFRow rowHeader = sheet.createRow(0);
		 String[] headers = {"借阅状态","档号","借阅人","文号","题名","备注","借阅目的","借阅时间"};
		 for(int i = 0;i<headers.length;i++){
        	HSSFCell cell = rowHeader.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellStyle(style);
			cell.setCellValue(text);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        	
		 }
		
		for(int i = 0;i<idList.size();i++){
			String id = idList.get(i);
			ElectronicLend electronicLend = generalDao.findById(id, ElectronicLend.class);
			HSSFRow row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(electronicLend.getJyzt());
        	row.createCell(1).setCellValue(electronicLend.getDh());
        	row.createCell(2).setCellValue(electronicLend.getJyr().getName());
        	row.createCell(3).setCellValue(electronicLend.getWh());
        	row.createCell(4).setCellValue(electronicLend.getTm());
        	row.createCell(5).setCellValue(electronicLend.getBz());
        	row.createCell(6).setCellValue(electronicLend.getJymd());
        	row.createCell(7).setCellValue(electronicLend.getJysj());
			
		}
		 
		String realpath = Path.UPLOAD_IMPORT_FILE_PATH + UUID.randomUUID().toString() + ".xls";	//
		String filename =  "电子借阅记录.xls";
		
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

	private String getFilePath() {
		String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
        String tempdir;
        String classPath[] = classesPath.split("webapps");
        tempdir = classPath[0];
        tempdir += "webapps/";
        tempdir += File.separator;
        return tempdir;
	}

	@Override
	public List<String> getElectronicLendIds(QueryBuilder queryBuilder) {
		List<String> ids = new ArrayList<String>();
		List<ElectronicLend> list = generalDao.queryByCriteria(ElectronicLend.class, queryBuilder, null,0,-1);
		for(ElectronicLend electronicLend : list){
			ids.add(electronicLend.getId());
		}
		
		
		return ids;
	}
}
