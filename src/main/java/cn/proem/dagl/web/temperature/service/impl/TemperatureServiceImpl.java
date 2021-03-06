package cn.proem.dagl.web.temperature.service.impl;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.temperature.service.TemperatureService;
import cn.proem.dagl.web.temperature.util.TimeUtil;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.temperature.entity.DWsd;
@Service
public class TemperatureServiceImpl implements TemperatureService {
	@Resource
	private GeneralDao generalDao;
	@Override
	public DataGrid<DWsd> getRecordList(QueryBuilder queryBuilder, int nowPage,
			int pageSize) {
		DataGrid<DWsd> dataGrid = new DataGrid<DWsd>(nowPage, pageSize);
		dataGrid.setRecordCount(generalDao.countByCriteria(
				DWsd.class, queryBuilder));
		//数据集合
		List<DWsd> list = generalDao.queryByCriteria(
				DWsd.class, 
				queryBuilder, 
				new Order[]{new Order("jlrq",OrderType.DESC)}, 
				0, -1);

		dataGrid.setExhibitDatas(list);
		return dataGrid;
	}
	@Override
	@Transactional
	@LogService(description = "修改温湿度记录")
	public void saveOrupdate(DWsd dwsd) {
		if(StringUtil.isEmpty(dwsd.getId())){
			dwsd.setId(UUID.randomUUID().toString());
			generalDao.save(dwsd);
		}else{
			generalDao.update(dwsd);
		}
	}
	@Override
	@Transactional
	@LogService(description = "删除温湿度记录")
	public void delete(String id)
			throws cn.proem.suw.web.common.exception.ServiceException {
		if(StringUtil.isEmpty(id)) {
			try {
				throw new ServiceException("ID不能为空");
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		generalDao.delete(generalDao.findById(id, DWsd.class));
	}
	public List<Map<String, Object>> getDataList(String year,String  month) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> wdmapList = new HashMap<String,Object>();
		Map<String,Object> sdmapList = new HashMap<String,Object>();
		if(StringUtil.isEmpty(month)) {	//判断参数month是否为空
//			list = this.getTotalNumByCarTypeAndYear(carType,year);	
//			for(Map<String,Object> map : list) {
//				mapList.put(String.valueOf(map.get("months")), map.get(param));
//			}
		}else {
//			String sql = "SELECT * FROM man_dabg_wsd WHERE TO_CHAR(jlrq, 'yyyy-MM')='" + year + "-" + month + "' ORDER BY JLRQ";
			if(month.length() < 2) {
				month = "0" + month;
			}
			String sql = "SELECT MT.\"day_num\" day, NVL(wd,0) as wd, NVL (sd, 0) as sd "
					+ "FROM \"pdagl_day\" MT LEFT JOIN( "
					+ "SELECT T.*, TO_CHAR (jlrq, 'dd') M FROM "
					+ "( SELECT * FROM man_dabg_wsd "
					+ "WHERE TO_CHAR (jlrq, 'yyyy-MM') = '" + year + "-" + month + "' "
					+ "ORDER BY JLRQ ) T ) T "
					+ "ON T.M = MT.\"day_num\" "
					+ "ORDER BY MT.\"day_num\"";
			//获取指定年月的所有温湿度记录
			List<Map<String,Object>> dwsdMaps = generalDao.getObjectList(sql, 0, -1, new Object[]{});
			for(Map<String,Object> map : dwsdMaps) {
				wdmapList.put(String.valueOf(map.get("DAY")), String.valueOf(map.get("WD")));
				sdmapList.put(String.valueOf(map.get("DAY")), String.valueOf(map.get("SD")));
			}
			list.add(wdmapList);
			list.add(sdmapList);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getDataByWeek(String week) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> wdmapList = new HashMap<String,Object>();
		Map<String,Object> sdmapList = new HashMap<String,Object>();
		String startDate = week.split(" ")[0];
		String endDate = week.split(" ")[1];
		String sql = "SELECT MT.\"week\", jlrq, trunc(NVL(wd, 0), 2) as wd, trunc(NVL (sd, 0), 2) as sd "
				+ "FROM \"pdagl_week\" MT LEFT JOIN ( "
				+ "SELECT T.*, to_char(jlrq,'D') M FROM ("
				+ "SELECT * FROM "
				+ "(select jlr,  "
				+ " to_date(to_char(jlrq, 'yyyy-mm-dd'), 'yyyy-mm-dd') as jlrq, "
                + " wsdsj as wsdsj, "
                + " avg(wd) as wd, "
                + " avg(sd) as sd "
                + " from man_dabg_wsd "
                + " group by jlr, to_char(jlrq, 'yyyy-mm-dd'), wsdsj "
                + " order by jlrq, wsdsj) " 
				+ " WHERE \"TO_CHAR\"(jlrq,'yyyy-MM-dd')	"
				+ "BETWEEN '" + startDate + "' AND '" + endDate + "' ) T ) T "
				+ "ON T.M = MT.\"week\"  AND T.WSDSJ = MT.\"time\" "
				+ "ORDER BY MT.\"week\",MT.\"time\" DESC";
		//获取指定日期范围的温湿度记录
		List<Map<String,Object>> dwsdMaps = generalDao.getObjectList(sql, 0, -1, new Object[]{});
//		for(Map<String,Object> map : dwsdMaps) {
		for(int i = 2;i<dwsdMaps.size();i++) {
			wdmapList.put(String.valueOf(i), String.valueOf(dwsdMaps.get(i).get("WD")));
			sdmapList.put(String.valueOf(i), String.valueOf(dwsdMaps.get(i).get("SD")));
			wdmapList.put(String.valueOf(dwsdMaps.size()), String.valueOf(dwsdMaps.get(0).get("WD")));
			sdmapList.put(String.valueOf(dwsdMaps.size()), String.valueOf(dwsdMaps.get(0).get("SD")));
			wdmapList.put(String.valueOf(dwsdMaps.size() + 1), String.valueOf(dwsdMaps.get(1).get("WD")));
			sdmapList.put(String.valueOf(dwsdMaps.size() + 1), String.valueOf(dwsdMaps.get(1).get("SD")));
		}
		list.add(wdmapList);
		list.add(sdmapList);
		return list;
	}
	@Override
	public List<String> importFileFromExcel(String fullpath) throws Exception,ServiceException {
		// 错误信息格式
		String errorinfo = "第%d行记录导入失败,原因是:%s";
		String total = "本次导入共计:%d条,成功:%d条,失败: %d条";
		// 记录总条数
		Integer totolCount = 0;
		// 导入成功条数
		Integer successCount = 0;
		// 导入失败条数
		Integer errorCount = 0;
		
		
		List<String> list = new ArrayList<String>();
		//1.得到Excel常用对象
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fullpath));  
		//2.得带Excek工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		//3.得到Excel工作表对象
		HSSFSheet sheet = wb.getSheetAt(0); 
		//4.得到Excel工作表的行  
		int rowNum = sheet.getLastRowNum();
		
		//总列数
        HSSFRow row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();
        totolCount = rowNum-1;
        for(int i = 2;i<=rowNum;i++){
        	row = sheet.getRow(i);
        	Boolean jlrqFlag = TimeUtil.valiDateTimeWithLongFormat(getCellFormatValue(row.getCell(0)));
        	Boolean wsdsjFlag = TimeUtil.checkTime(getCellFormatValue(row.getCell(2)));
        	if(!jlrqFlag || !wsdsjFlag){
        		errorCount++;
        		if(!jlrqFlag){
        			list.add(String.format(errorinfo,i+1,"记录日期格式输入错误。"));

        		}else if(!jlrqFlag){
        			list.add(String.format(errorinfo,i+1,"时间格式输入错误。"));
        		}
        		continue;	
        	}
        	successCount++;
        	DWsd dWsd = new DWsd();
        	//dWsd.setJlrq(jlrq);
        	dWsd.setId(UUID.randomUUID().toString());
        	dWsd.setJlrq(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getCellFormatValue(row.getCell(0))));
        	dWsd.setTq(getCellFormatValue(row.getCell(1)));
        	dWsd.setWsdsj(getCellFormatValue(row.getCell(2)));
        	dWsd.setJlr(getCellFormatValue(row.getCell(3)));
        	dWsd.setWd(getCellFormatValue(row.getCell(4)));
        	dWsd.setSd(getCellFormatValue(row.getCell(5)));
        	//dWsd.setXgwd(getStringCellValue(row.getCell(6)));
        	//dWsd.setXgsd(getStringCellValue(row.getCell(7)));
        	dWsd.setCqcs(getCellFormatValue(row.getCell(6)));
        	dWsd.setBz(getCellFormatValue(row.getCell(7)));
        	
        	generalDao.save(dWsd);
        	
        }
        list.add(String.format(total,totolCount,successCount,errorCount));
        return list;
		
	}
	
	
	
	 private String getStringCellValue(HSSFCell cell) {
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        if (cell == null) {
	            return "";
	        }
	        return strCell;
	    }
	 
	 	private String getDateCellValue(HSSFCell cell) {
	        String result = "";
	        try {
	            int cellType = cell.getCellType();
	            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
	                Date date = cell.getDateCellValue();
	                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
	            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
	                String date = getStringCellValue(cell);
	                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
	            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
	                result = "";
	            }
	        } catch (Exception e) {
	            System.out.println("日期格式不正确!");
	            e.printStackTrace();
	        }
	        return result;
	    }
	 	
	 	 private String getCellFormatValue(HSSFCell cell) {
	         String cellvalue = "";
	         if (cell != null) {
	             // 判断当前Cell的Type
	             switch (cell.getCellType()) {
	             // 如果当前Cell的Type为NUMERIC
	             case HSSFCell.CELL_TYPE_NUMERIC:
	             case HSSFCell.CELL_TYPE_FORMULA: {
	                 // 判断当前的cell是否为Date
	                 if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                     // 如果是Date类型则，转化为Data格式
	                     
	                     //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                     //cellvalue = cell.getDateCellValue().toLocaleString();
	                     
	                     //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                     Date date = cell.getDateCellValue();
	                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                     cellvalue = sdf.format(date);
	                     
	                 }
	                 // 如果是纯数字
	                 else {
	                     // 取得当前Cell的数值
	                     //cellvalue = String.valueOf(cell.getNumericCellValue());
	                     
	                     cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                     String temp = cell.getStringCellValue();
	                     if (temp.indexOf(".") > -1) {  
	                    	 cellvalue = String.valueOf(new Double(temp)).trim();  
	                     } else {  
	                    	 cellvalue = temp.trim();  
	                     }  
	                     
	                 }
	                 break;
	             }
	             // 如果当前Cell的Type为STRIN
	             case HSSFCell.CELL_TYPE_STRING:
	                 // 取得当前的Cell字符串
	                 cellvalue = cell.getRichStringCellValue().getString();
	                 break;
	             // 默认的Cell值
	             default:
	                 cellvalue = " ";
	             }
	         } else {
	             cellvalue = "";
	         }
	         return cellvalue;

	     }
		@Override
		public List<DWsd> getTemperatures(List<String> ids) {
			List<DWsd> temperatures = new ArrayList<DWsd>();
			if(ids.size()==0){
				temperatures = generalDao.queryByCriteria(DWsd.class, new QueryBuilder(), null, 0, -1);
			}else{
				for(String id : ids){
					DWsd dWsd = generalDao.findById(id, DWsd.class);
					temperatures.add(dWsd);
				}
			}
			
			return temperatures;
		}
	 
}
