package cn.proem.dagl.web.fileTransfer.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.Department;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileControl.entity.FileControl;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.dagl.web.fileIdentify.dto.DtoIdentifyContent;
import cn.proem.dagl.web.fileTransfer.entity.TransferRecorder;
import cn.proem.dagl.web.fileTransfer.service.FileTransferService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping("/w/fileTransfered")
public class TransferedFileController extends BaseCtrlModel {
	
	@Autowired
	private FileTransferService fileTransferService;
	@Autowired
	CommonService commonService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private DicManagerService dicService;
	
	
	/**
	 * 初始化已移交档案页面
	 * @return
	 */
	@RequestMapping("/initview")
	public ModelAndView initFileControlView(){
		
		ModelAndView view = this.createNormalView("/web/fileTransfer/transferedInitView.vm");
		
		return view;
		
	}
	
	/**
	 * 获取公开档案列表
	 */
	@RequestMapping("/getTransferedFileList")
	@ResponseBody
	@LogService(description = "获取公开档案列表")
	public String getTransferedFileList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		//QueryBuilder queryBuilder = query.generateQueryBuilder();
		//queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.SQL,FieldType.INTEGER,null));
		//DataGrid<TransferRecorder> dataGrid = fileTransferService.getTransferedFileDataGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		//dataGrid.getExhibitDatas();
		
		String tablename = " PFM_FILE_TRANSFER ";
		DataGrid<Map<String,Object>> grid =  fileTransferService.getAllTransferedFileDataGrid(tablename, query.getNowPage(), query.getPageSize());
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map :grid.getExhibitDatas()){
			Map<String,Object> dataMap = new HashMap<String, Object>();
			dataMap.put("tm", map.get("TM"));
			dataMap.put("uuid", map.get("UUID"));
			dataMap.put("userId", map.get("CREATE_ID"));
			dataMap.put("startTime", map.get("START_TIME"));
			dataMap.put("endTime", map.get("END_TIME"));
			
			dataMap.put("nodeName", map.get("STATE"));
			dataMap.put("oldCompany", map.get("TRANSFCOMPANY_ID")==null?"":commonService.findById(map.get("TRANSFCOMPANY_ID").toString(), Department.class).getFullName());
			dataMap.put("newCompany", map.get("RECEICOMPANY_ID")==null?"":commonService.findById(map.get("RECEICOMPANY_ID").toString(), Department.class).getFullName());
			dataList.add(dataMap);
		}
		grid.setExhibitDatas(dataList);
		
		
		return JSON.toJSONStringWithDateFormat(grid, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
		
	}
	
	
	@RequestMapping("/getUserById")
	@ResponseBody
	@LogService(description = "根据id获得用户对象")
	public ResultModel<User> getUserById(HttpServletRequest request,@RequestBody Map<String,String> obj){
		ResultModel<User> resultModel = new ResultModel<User>();
		
		User user = commonService.findById(obj.get("userId"), User.class);
		
		resultModel.setData(user);
		return resultModel;
	}
	
	
	
	/**
	 * 
	 * @Description:保管期限修改历史批量导出
	 * @author:bao
	 * @time:2017年7月5日 上午10:59:02
	 */
	@RequestMapping(value = "/exportTransferedInfo/{ids}")
	@ResponseBody
	@LogService(description = "档案移交记录批量导出")
	public ResultModel<String> exportDestroyHistory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("ids")String ids){
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
				List<TransferRecorder> transferedFiles = null;
				if(StringUtil.isEmpty(ids)){
					transferedFiles = fileTransferService.getTransferedFileDataGrid(new QueryBuilder(), 0, -1).getExhibitDatas();
				}else{
					/*List<String> idList = new ArrayList<String>();
 					String[] idArr = ids.split(",");
					for(String id : idArr){
						idList.add("'"+id+"'");
					}
					
					ids = StringUtils.join(idList, ",");*/
					QueryBuilder queryBuilder = new QueryBuilder();
					queryBuilder.addCondition(new QueryCondition("daid", ids,ConditionType.IN, FieldType.STRING, null));
					transferedFiles = fileTransferService.getTransferedFileDataGrid(queryBuilder, 0, -1).getExhibitDatas();
				}
			
				

				// 获得所有数据
		        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
				HSSFSheet sheet = workbook.createSheet("档案移交记录");		//生成工作页
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
				 //String[] headers = {"批量移交标题","创建人","创建时间","结束时间","移交公司","接受公司"};
				 String[] headers = {"档号","题名","责任者","成文日期","密级","保管期限"};
				 for(int i = 0;i<headers.length;i++){
		        	HSSFCell cell = rowHeader.createCell(i);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellStyle(style);
					cell.setCellValue(text);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        	
				 }
			 
			/* for(int i = 0;i<transferedFiles.size();i++){
				 Map<String, Object> map = transferedFiles.get(i);
				User jdr = commonService.findById(String.valueOf(map.get("CREATE_ID")), User.class);
				HSSFRow row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(String.valueOf(map.get("TM")));
	        	row.createCell(1).setCellValue(StringUtil.isEmpty(jdr)?"":jdr.getName());
	        	row.createCell(2).setCellValue(String.valueOf(map.get("START_TIME")));
	        	row.createCell(3).setCellValue(String.valueOf(map.get("END_TIME")));
	        	row.createCell(4).setCellValue(map.get("TRANSFCOMPANY_ID")==null?"":commonService.findById(map.get("TRANSFCOMPANY_ID").toString(), Department.class).getFullName());
	        	row.createCell(5).setCellValue(map.get("RECEICOMPANY_ID")==null?"":commonService.findById(map.get("RECEICOMPANY_ID").toString(), Department.class).getFullName());
					
			}*/
			 
			 for(int i = 0;i<transferedFiles.size();i++){
				 TransferRecorder transferRecorder = transferedFiles.get(i);
				 HSSFRow row = sheet.createRow(i+1);
				 BaseEntityInf entity = null;
				 if(StringUtil.isNotEmpty(transferRecorder.getDh())){
					 String businessId = transferRecorder.getDh();
					 String tablename = businessId.substring(0, businessId.indexOf("@"));
					 String uuid = businessId.substring(businessId.indexOf("@")+1);
					 entity = customArchiveService.getEntity(tablename,uuid);
				 }
				 row.createCell(0).setCellValue(String.valueOf(entity.get("DH")));
				 row.createCell(1).setCellValue(StringUtil.isEmpty(transferRecorder.getApprover())?"":transferRecorder.getApprover());
				 row.createCell(2).setCellValue(StringUtil.isEmpty(transferRecorder.getZrz())?"":transferRecorder.getZrz());
				 row.createCell(3).setCellValue(StringUtil.isEmpty(transferRecorder.getCwrq())?"":String.valueOf(transferRecorder.getCwrq()));
				 row.createCell(4).setCellValue(StringUtil.isEmpty(transferRecorder.getMj())?"": getDicTitle("mj",transferRecorder.getMj()));
				 row.createCell(5).setCellValue(StringUtil.isEmpty(transferRecorder.getBgqx())?"":getDicTitle("bgqx",transferRecorder.getBgqx()));
				 
			 }
				 
				
			OutputStream output=response.getOutputStream();  
	        response.reset();  
	        response.setHeader("Content-disposition", "attachment; filename="+new String("档案移交记录详情.xls".getBytes("utf-8"), "iso8859-1"));  
	        response.setContentType("application/msexcel");          
	        workbook.write(output);  
	        output.close();  
		}catch(Exception e){
			resultModel.setSuccess(false);
			resultModel.setMsg("批量导出失败");
		} 
		return resultModel;
			
		}
	
	private String getDicTitle(String did, String val){
	    List<DictionaryValue> dictionaryValues = dicService
                .getDicValueList(did);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(val)){
	            return dic.getDvalue();
	        }
	    }
	    return "";
	}

}
