package cn.proem.dagl.web.fileUse.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.fileUse.entity.ElectronicLend;
import cn.proem.dagl.web.fileUse.service.ElectronicLendService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping(value = "/w/fileuse/electronicLendHistory")
public class ElectronicLendHistoryController extends BaseCtrlModel{
    @Autowired
    private CommonService commonService;
    @Autowired
    private ElectronicLendService electronicLendService;
    
	
	@RequestMapping(value = "/allLendInfoView")
	public ModelAndView allLendInfoView(HttpServletRequest request) {
		ModelAndView modelAndView = this.createNormalView("/web/fileUse/electronicLend/allLendInfoHistory.vm");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/getElectronicLendList")
	@ResponseBody
	@LogService(description = "获取电子借阅集合")
	public String getElectronicLendList(String dtGridPager, HttpServletRequest request){
		User user = this.getCurrentUser(request);
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		queryBuilder.addCondition(new QueryCondition("jyzt","未登记,已登记,已拒绝",ConditionType.IN, FieldType.STRING, null));
		try {
			UserDepartment department = commonService.findUserDepartmentByUserId(user.getId());
			if(commonService.isAdminOrFileAdmin(user)){
				List<User> users = commonService.findUsersByDeptId(department.getDepartment().getId());
				List<String> userList = new ArrayList<String>();
				for(User u : users){
					userList.add(u.getId());
				}
				String str = StringUtils.join(userList.toArray(), ",");
				if(users.size()>0){
					queryBuilder.addCondition(new QueryCondition("id", str,ConditionType.IN, FieldType.STRING, "jyr"));
				}else{
					queryBuilder.addCondition(new QueryCondition("id", null,ConditionType.NULL, FieldType.STRING, "jyr"));
				}
			}else{
				queryBuilder.addCondition(new QueryCondition("id", user.getId(),ConditionType.EQ, FieldType.STRING, "jyr"));
			}
		
		} catch (ServiceException e) {
			return JSON.toJSONStringWithDateFormat("",
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect);
		}
		
		return JSON.toJSONStringWithDateFormat(electronicLendService.getElectronicLendList(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	@LogService(description = "根据电子借阅di删除电子借阅")
	public ResultModel<String> delete(HttpServletRequest request,@RequestParam("id")String id){
		ResultModel<String> resultModel = new ResultModel<String>();
		try {
			electronicLendService.delete(id);
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除电子借阅失败");
		}
		return resultModel;
	}
	
	
	@RequestMapping(value = "/getAllElectronicLendList")
	@ResponseBody
	@LogService(description = "获取所有电子借阅集合")
	public String getAllElectronicLendList(String dtGridPager, HttpServletRequest request){
		User user = this.getCurrentUser(request);
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		queryBuilder.addCondition(new QueryCondition("jyzt","审核中,借阅成功",ConditionType.IN, FieldType.STRING, null));
		try {
			UserDepartment department = commonService.findUserDepartmentByUserId(user.getId());
			//if("fileAdmin".equalsIgnoreCase(department.getDuty().getCode()) || "depLeader".equalsIgnoreCase(department.getDuty().getCode())){
			if(commonService.isAdminOrFileAdmin(user)){
				List<User> users = commonService.findUsersByDeptId(department.getDepartment().getId());
				List<String> userList = new ArrayList<String>();
				for(User u : users){
					userList.add(u.getId());
				}
				String str = StringUtils.join(userList.toArray(), ",");
				if(users.size()>0){
					queryBuilder.addCondition(new QueryCondition("id", str,ConditionType.IN, FieldType.STRING, "jyr"));
				}else{
					queryBuilder.addCondition(new QueryCondition("id", null,ConditionType.NULL, FieldType.STRING, "jyr"));
				}
			}else{
				queryBuilder.addCondition(new QueryCondition("id", user.getId(),ConditionType.EQ, FieldType.STRING, "jyr"));
			}
		} catch (ServiceException e) {
			return JSON.toJSONStringWithDateFormat("",
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect);
		}
		return JSON.toJSONStringWithDateFormat(electronicLendService.getElectronicLendList(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}

		@RequestMapping(value = "/exportElectronicLend/{ids}")
		@ResponseBody
		@LogService(description = "档案自定义表单部分批量导出")
		public ResultModel<String> exportFileDef(
				HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable("ids")String ids){
			ResultModel<String> resultModel = new ResultModel<String>();
			try{
				User user = this.getCurrentUser(request);
				List<String> idList = new ArrayList<String>();
				if(StringUtil.isEmpty(ids)){
					QueryBuilder queryBuilder = new QueryBuilder();
					queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
					queryBuilder.addCondition(new QueryCondition("jyzt","审核中,借阅成功",ConditionType.IN, FieldType.STRING, null));
					UserDepartment department = commonService.findUserDepartmentByUserId(user.getId());
					if("fileAdmin".equalsIgnoreCase(department.getDuty().getCode()) || "depLeader".equalsIgnoreCase(department.getDuty().getCode())){
						List<User> users = commonService.findUsersByDeptId(department.getDepartment().getId());
						List<String> userList = new ArrayList<String>();
						for(User u : users){
							userList.add(u.getId());
						}
						String str = StringUtils.join(userList.toArray(), ",");
						if(users.size()>0){
							queryBuilder.addCondition(new QueryCondition("id", str,ConditionType.IN, FieldType.STRING, "jyr"));
						}else{
							queryBuilder.addCondition(new QueryCondition("id", null,ConditionType.NULL, FieldType.STRING, "jyr"));
						}
					}else{
						queryBuilder.addCondition(new QueryCondition("id", user.getId(),ConditionType.EQ, FieldType.STRING, "jyr"));
					}
					idList = electronicLendService.getElectronicLendIds(queryBuilder);
				}else{
						
					idList = Arrays.asList(ids.split(","));
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
					ElectronicLend electronicLend = commonService.findById(id, ElectronicLend.class);
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
				 
				OutputStream output=response.getOutputStream();  
		        response.reset();  
		        response.setHeader("Content-disposition", "attachment; filename="+new String("电子借阅记录.xls".getBytes("utf-8"), "iso8859-1"));  
		        response.setContentType("application/msexcel");          
		        workbook.write(output);  
		        output.close();  
					
			}catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			}catch(Exception e){
				resultModel.setSuccess(false);
				resultModel.setMsg("批量导出失败");
			} 
			return resultModel;
		}
		
}
