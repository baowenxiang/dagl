package cn.proem.dagl.web.fileControl.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.Dictionary;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileControl.entity.FileControl;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.dagl.web.fileIdentify.dto.DtoIdentifyContent;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping("/w/ykfda")
public class OpenedFileControlController extends BaseCtrlModel {
	
	@Autowired
	private FileControlService fileControlService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private DicManagerService dicManagerService;
	/**
	 * 初始化公开档案页面
	 * @return
	 */
	@RequestMapping("/initview")
	public ModelAndView initFileControlView(){
		
		ModelAndView view = this.createNormalView("/web/openedFileControl/openedInitView.vm");
		
		return view;
		
	}
	/**
	 * 获取公开档案列表
	 */
	@RequestMapping("/getOpenedFileList")
	@ResponseBody
	public String getOpenedFileList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<FileControl> dataGrid = fileControlService.getOpenedFileDataGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
		
	}
	
	@RequestMapping("/cancelOpenedFile")
	@ResponseBody
	public ResultModel<String> cancelOpenedFile(HttpServletRequest requset,
			@RequestParam(value="ids[]")String[] ids){
		ResultModel<String> rm = new ResultModel<String>();
		
			try {
				for(String id : ids){
					FileControl fileControl = commonService.findById(id, FileControl.class);
					String daid  = fileControl.getDaid();
					String bm = fileControl.getBm();
				    fileControlService.deleteOpenedFile(id);
				    try{
				    	BaseEntityInf entity = customArchiveService.getEntity(bm, daid);
				    	entity.set("hkkz", "3");
				    	customArchiveService.update(entity);
				    	
				    	
				    }catch(Exception ex){
				    	
				    }
				}
			} catch (ServiceException e) {
				rm.setSuccess(false);
				rm.setMsg("开放档案撤销失败");
				e.printStackTrace();
			}
		
		
		
		return rm;
		
	}
	
	/**
	 * 
	 * @Description:保管期限修改历史批量导出
	 * @author:bao
	 * @time:2017年7月5日 上午10:59:02
	 */
	@RequestMapping(value = "/exportOpenedFileInfo/{ids}")
	@ResponseBody
	@LogService(description = "已开放档案批量导出")
	public ResultModel<String> exportDestroyHistory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("ids")String ids){
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
				List<FileControl> fileControls = null;
				QueryBuilder queryBuilder = new QueryBuilder();
				if(StringUtil.isEmpty(ids)){
					queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
					fileControls = fileControlService.getOpenedFileDataGrid(queryBuilder, 0,-1).getExhibitDatas();
				}else{
					queryBuilder.addCondition(new QueryCondition("id",ids,ConditionType.IN, FieldType.STRING, null));
					fileControls = fileControlService.getOpenedFileDataGrid(queryBuilder, 0,-1).getExhibitDatas();
				}
			
				

				// 获得所有数据
		        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
				HSSFSheet sheet = workbook.createSheet("已开放档案导出记录");		//生成工作页
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
				 String[] headers = {"档号","题名","责任者","成文日期","全宗号","年度号","文号","目录号","案卷号","保管期限"};
				 for(int i = 0;i<headers.length;i++){
		        	HSSFCell cell = rowHeader.createCell(i);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellStyle(style);
					cell.setCellValue(text);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        	
				 }
			 
			 for(int i = 0;i<fileControls.size();i++){
				FileControl fileControl = fileControls.get(i);
				HSSFRow row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(fileControl.getDh());
	        	row.createCell(1).setCellValue(fileControl.getTm());
	        	row.createCell(2).setCellValue(fileControl.getZrz());
	        	row.createCell(3).setCellValue(fileControl.getCwrq());
	        	row.createCell(4).setCellValue(fileControl.getQzh());
	        	row.createCell(5).setCellValue(fileControl.getNdh());
	        	row.createCell(6).setCellValue(fileControl.getWh());
	        	row.createCell(7).setCellValue(fileControl.getMlh());
	        	row.createCell(8).setCellValue(fileControl.getAjh());
	        	row.createCell(9).setCellValue(getDicTitle("bgqx",fileControl.getBgqx()));
					
			}
				 
				
			OutputStream output=response.getOutputStream();  
	        response.reset();  
	        response.setHeader("Content-disposition", "attachment; filename="+new String("已开放档案.xls".getBytes("utf-8"), "iso8859-1"));  
	        response.setContentType("application/msexcel");          
	        workbook.write(output);  
	        output.close();  
		}catch(Exception e){
			resultModel.setSuccess(false);
			resultModel.setMsg("批量导出失败");
		} 
		return resultModel;
			
		}
	
	
	
	private String getDicTitle(String dno, String dvno){
	    List<DictionaryValue> dictionaryValues = dicManagerService.getDicValueList(dno);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(dvno)){
	             return dic.getDvalue();
	        }
	    }
	    return "";
	}

}
