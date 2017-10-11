package cn.proem.dagl.web.fileIdentify.controller;

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
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileIdentify.entity.FileIdentify;
import cn.proem.dagl.web.fileIdentify.service.DestroyHistroyService;
import cn.proem.dagl.web.fileIdentify.service.IdentifyService;
import cn.proem.dagl.web.fileUse.entity.ElectronicLend;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;


/**
 * 档案鉴定控制层
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/w/fileidentify/destroyHistory")
public class DestroyHistroyController extends BaseCtrlModel{
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private  DestroyHistroyService destroyHistroyService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private IdentifyService identifyService;
	@Autowired
	private DicManagerService dicManagerService;
	
	
	/**
	 * @Description 初始化页面
	 * @MethodName initView
	 * @author bao
	 * @date 2017年5月19日
	 * @param request
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/initView")
	public ModelAndView initView(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/destroyHistory.vm");
		return modelAndView;
	}
	
	/**
	 * @Description 获得销毁历史列表
	 * @MethodName getDestroyTables
	 * @author bao
	 * @date 2017年5月21日
	 * @param dtGridPager
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/getDestroyTables")
	@ResponseBody
	@LogService(description = "获取销毁历史列表")
	public String getDestroyTables(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("jdnr","销毁",ConditionType.EQ, FieldType.STRING, null));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		
		return JSON.toJSONStringWithDateFormat(destroyHistroyService.getDestroyTables(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * @Description 撤销销毁
	 * @MethodName getDestroyTables
	 * @author bao
	 * @date 2017年5月21日
	 * @param request
	 * @param ids
	 * @return ResultModel<String>
	 */
	@RequestMapping(value = "/cancelDestroy")
	@ResponseBody
	@LogService(description = "撤销销毁")
	public ResultModel<String> getDestroyTables(HttpServletRequest request,
									@RequestParam(value="ids[]")String[] ids){
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
			for(String id : ids){
				FileIdentify fileIdentify = commonService.findById(id,FileIdentify.class);
				
				BaseEntityInf entity = customArchiveService.getEntity(commonService.findById(id, FileIdentify.class).getBm(), fileIdentify.getDaid());
				
				entity.set("SFJD", "sfjd_2");
				customArchiveService.update(entity);
				destroyHistroyService.deleteDestroyHistroy(id);
				String jdnrId = destroyHistroyService.addIdentifyContent(this.getCurrentUser(request), id);
			}
			
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("撤销失败");
		}
		
		
		return resultModel;
	}
	
	/**
	 * 
	 * @Description:导出销毁历史
	 * @author:bao
	 * @time:2017年7月5日 上午10:03:12
	 */
	@RequestMapping(value = "/exportDestroyHistory/{ids}")
	@ResponseBody
	@LogService(description = "档案销毁历史批量导出")
	public ResultModel<String> exportDestroyHistory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("ids")String ids){
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
			List<FileIdentify> fileIdentifies = new ArrayList<FileIdentify>();
			QueryBuilder queryBuilder = new QueryBuilder();
			if(StringUtil.isEmpty(ids)){
				queryBuilder.addCondition(new QueryCondition("jdnr","销毁",ConditionType.EQ, FieldType.STRING, null));
				queryBuilder.addCondition(new QueryCondition("delFlag",Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
				fileIdentifies = destroyHistroyService.getDestroyTables(queryBuilder);
			}else{
				queryBuilder.addCondition(new QueryCondition("id",ids,ConditionType.IN, FieldType.STRING, null));
				fileIdentifies = destroyHistroyService.getDestroyTables(queryBuilder);
			}
			
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
			 String[] headers = {"档号","题名","责任者","成文日期","全宗号","年度号","文号","目录号","案卷号","保管期限"};
			 for(int i = 0;i<headers.length;i++){
	        	HSSFCell cell = rowHeader.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellStyle(style);
				cell.setCellValue(text);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        	
			 }
			
			 
			 
			for(int i = 0;i<fileIdentifies.size();i++){
				FileIdentify fileIdentify = fileIdentifies.get(i);
				HSSFRow row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(fileIdentify.getDh());
	        	row.createCell(1).setCellValue(fileIdentify.getTm());
	        	row.createCell(2).setCellValue(fileIdentify.getZrz());
	        	row.createCell(3).setCellValue(fileIdentify.getCwrq());
	        	row.createCell(4).setCellValue(fileIdentify.getQzh());
	        	row.createCell(5).setCellValue(fileIdentify.getNdh());
	        	row.createCell(6).setCellValue(fileIdentify.getWh());
	        	row.createCell(7).setCellValue(fileIdentify.getMlh());
	        	row.createCell(8).setCellValue(fileIdentify.getAjh());
	        	row.createCell(9).setCellValue(getDicTitle("bgqx",fileIdentify.getBgqx()));
				
			}
			 
			
			OutputStream output=response.getOutputStream();  
	        response.reset();  
	        response.setHeader("Content-disposition", "attachment; filename="+new String("档案销毁历史.xls".getBytes("utf-8"), "iso8859-1"));  
	        response.setContentType("application/msexcel");          
	        workbook.write(output);  
	        output.close();  
	}catch(Exception e){
		resultModel.setSuccess(false);
		resultModel.setMsg("批量导出失败");
	} 
	return resultModel;
		
	}
	
	public String getDicTitle(String dno, String dvno){
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
