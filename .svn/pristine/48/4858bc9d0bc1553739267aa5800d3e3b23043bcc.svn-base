package cn.proem.dagl.web.temperature.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.util.BeanUtils;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.temperature.entity.DtoDate;
import cn.proem.dagl.web.temperature.service.TemperatureService;
import cn.proem.dagl.web.upload.service.UploadService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.DateUtil;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.temperature.entity.DWsd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * @ClassName TemperatureController
 * @Description 温湿度记录管理层
 * @author chenxiaofen
 * @date 2017年6月5日
 */
@Controller
@RequestMapping(value = "/w/temperature")
public class TemperatureController extends BaseCtrlModel {
	@Autowired
	private TemperatureService temperatureService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UploadService uploadService;
	/**
	 * @MethodName initTable
	 * @Description 初始化温湿度记录列表
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param request
	 * @param tablename
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/initTable")
	public ModelAndView initTable() throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/temperature/list.vm");
		return modelAndView;
	}
	/**
	 * @MethodName getRecordList
	 * @Description 分页获取记录
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param dtGridPager
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
    @RequestMapping(value = "/getRecordList")
	@LogService(description = "分页获取温湿度记录列表")
    public String getRecordList(String dtGridPager, HttpServletRequest request) throws ServiceException {
		DataGridQuery query = parseToQuery(dtGridPager == null ? ""
				: dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
        return JSON.toJSONStringWithDateFormat(temperatureService.getRecordList(
        		queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
    }
	/**
	 * @MethodName toDetailPage
	 * @Description 跳转详情页面
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/toDetailPage/{id}")
	public ModelAndView toDetailPage(HttpServletRequest request,
			@PathVariable("id") String id) throws ServiceException {

	    ModelAndView modelAndView = this
				.createNormalView("/web/temperature/detail.vm");
	    modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	/**
	 * @Description:跳转温湿度增加页面
	 * @author:bao
	 * @time:2017年6月22日 上午11:15:12
	 */
	@RequestMapping(value = "/toAddDetailPage")
	public ModelAndView toAddDetailPage(HttpServletRequest request) throws ServiceException {

	    ModelAndView modelAndView = this
				.createNormalView("/web/temperature/addDetail.vm");
		return modelAndView;
	}
	
	/**
	 * @MethodName detail
	 * @Description 根据id查询记录详情
	 * @author chenxiaofen
	 * @date 2017年6月5日
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/detail")
	@LogService(description = "获取温湿度记录详情")
    public ResultModel<DWsd> detail(String id) {
		ResultModel<DWsd> resultModel = new ResultModel<DWsd>();
//		commonService.findById(id, DWsd.class);
		resultModel.setData(commonService.findById(id, DWsd.class));
		resultModel.setSuccess(true);
        return resultModel;
    }
	@ResponseBody
    @RequestMapping(value = "/saveOrUpdate")
	@LogService(description = "保存或修改温湿度记录")
    public ResultModel<DWsd> saveOrUpdate(@RequestBody Map<String, Object> obj) {
		ResultModel<DWsd> resultModel = new ResultModel<DWsd>();
		DWsd dwsd = BeanUtils.cloneObject(DWsd.class, obj.get("dwsd"));
		try {
			temperatureService.saveOrupdate(dwsd);
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			e.printStackTrace();
			resultModel.setMsg("数据保存失败");
		}
        return resultModel;
    }
	/**
	 * @MethodName delete
	 * @Description 删除温湿度记录
	 * @author chenxiaofen
	 * @date 2017年6月6日
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/delete")
	@LogService(description = "删除温湿度记录")
    public ResultModel<DWsd> delete(String id) {
		ResultModel<DWsd> resultModel = new ResultModel<DWsd>();
		try {
			temperatureService.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			resultModel.setSuccess(false);
			resultModel.setMsg("删除失败");
		}
        return resultModel;
    }
	/**
	 * @MethodName getRecordByYearAndMonth
	 * @Description 根据年月获取温湿度记录的统计报表数据(目测暂时闲置~~)
	 * @author chenxiaofen
	 * @date 2017年6月6日
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/getRecordByYearAndMonth")
	@LogService(description = "根据年月获取温湿度记录并形成报表")
    public ResultModel<Map<String,Object>> getRecordByYearAndMonth(String year,String month) {
		ResultModel<Map<String,Object>> resultModel = new ResultModel<Map<String,Object>>();
		resultModel.setDatas(temperatureService.getDataList(year, month));
        return resultModel;
    }
	
	@ResponseBody
    @RequestMapping(value = "/getRecordByWeek")
	@LogService(description = "按周查看温湿度记录报表")
    public ResultModel<Map<String,Object>> getRecordByWeek(String weekFormat) {
		ResultModel<Map<String,Object>> resultModel = new ResultModel<Map<String,Object>>();
		resultModel.setDatas(temperatureService.getDataByWeek(weekFormat));
        return resultModel;
    }
	/**
	 * @MethodName getWeekList
	 * @Description 获取日期下拉选的值
	 * @author chenxiaofen
	 * @date 2017年6月6日
	 * @return
	 */
	@RequestMapping(value = "/getDateList")
	@ResponseBody
	@LogService(description = "获取按周查看的日期列表")
	public ResultModel<DtoDate> getDateList(HttpServletRequest request, int num, String lastDate) {
		num = 10;
		ResultModel<DtoDate> resultModel = new ResultModel<DtoDate>();
		List<DtoDate> dateList = new ArrayList<DtoDate>();
		dateList.addAll(this.getWeekList(num, lastDate));
		resultModel.setDatas(dateList);
		return resultModel;
	}
	public List<DtoDate> getWeekList(int num, String lastDate) {
		SimpleDateFormat simNow = new SimpleDateFormat("M月d日");
		//SimpleDateFormat simOld = new SimpleDateFormat("yyyy年M月d日");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<DtoDate> dateList = new ArrayList<DtoDate>();
		
		try {
			for (int i = 0; i < num; i++) {
				String[] dates = null;
				if (i == 0) {
					if (StringUtil.isEmpty(lastDate)){
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						calendar.add(Calendar.DAY_OF_YEAR, 1);
						lastDate = sdf.format(calendar.getTime());
					}
				} else if (i > 0) {
					lastDate = dateList.get(i - 1).getFormatDate();
				}
				dates = lastDate.split(" ");
				Date date = sdf.parse(dates[0]);
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    cal.add(Calendar.DAY_OF_YEAR, -1);
			
			    DtoDate dtoDate = new DtoDate();
			    Date f = DateUtil.getFirstDayOfWeek(new Date(cal.getTimeInMillis()));
			    Date l = DateUtil.getLastDayOfWeek(new Date(cal.getTimeInMillis()));
			    cal.setTime(f);
			    int weekNum = cal.get(Calendar.WEEK_OF_MONTH);
			    String dateStr = simNow.format(f) + "-" + simNow.format(l) + "(本月第"+weekNum+"周)";
			    String formatDateStr = sdf.format(f) + " " + sdf.format(l);
			    
				dtoDate.setDate(dateStr);
				dtoDate.setFormatDate(formatDateStr);
			    
				dateList.add(dtoDate);
			}
		} catch (Exception e) {
		}
		return dateList;
	}
	
	/**
	 * @Description 批量删除
	 * @MethodName batchdelete
	 * @author bao
	 * @date 2017年5月18日
	 * @param request
	 * @param tablename
	 * @param fileids
	 * @return ResultModel<String>
	 */
	@RequestMapping(value = "/batchdelete")
	@ResponseBody
	@LogService(description = "批量删除")
	public ResultModel<String> batchdelete(HttpServletRequest request,
			@RequestParam(value = "fileids[]",required = false)  String[]  fileids) {
		ResultModel<String> resultModel = new ResultModel<String>();
		// 获取表定义
		try {
			for(String fileid : fileids){
				temperatureService.delete(fileid);
			}
			resultModel.setMsg("删除成功");
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除失败");
			e.printStackTrace();
		}

		return resultModel;
	}
	
	/**
	 * 
	 * @Description:下载温湿度模版
	 * @author:bao
	 * @time:2017年7月3日 上午11:04:25
	 */
	@RequestMapping(value = "/downloadTemplet")
	@LogService(description = "下载温湿度模版")
	public ResultModel<String> downloadTemplet(HttpServletRequest request,HttpServletResponse response){
		ResultModel<String> resultModel = new ResultModel<String>();
		
		try{
			String[] headers = {"记录日期","天气","时间","记录人","温度","湿度","采取措施","备注"};
			
			
			HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
			HSSFSheet sheet = workbook.createSheet("温湿度模版");		//生成工作页
			sheet.setDefaultColumnWidth(15);				//设置表格默认列宽度为15个字节
			
			HSSFCellStyle style = workbook.createCellStyle();	//生成样式HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
			
			HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
			cellStyle.setWrapText(true);
			
			HSSFDataFormat format = workbook.createDataFormat();
			
			//导出excel的样式
			style.setDataFormat(format.getFormat("@"));
			
			
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
	        // 把字体应用到当前的样式
	        style.setFont(font);
	        
	        
	        
	        //备注信息 
	        /*HSSFCellStyle style1 = workbook.createCellStyle();
	        style1.setFillForegroundColor(HSSFColor.WHITE.index);  
	        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	        HSSFFont font1 = workbook.createFont();
	        font1.setFontHeightInPoints((short) 12);  
	        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	        font1.setColor(HSSFColor.RED.index);  
	        style1.setFont(font1);*/
	        CellRangeAddress cra=new CellRangeAddress(0, 0, 0, headers.length-1);
	        sheet.addMergedRegion(cra);
	        sheet.createRow(0).createCell(0).setCellValue(new HSSFRichTextString("输入的记录日期格式为:yyyy-MM-dd HH:mm:ss,时间格式为:HH:mm") );;
	        //sheet.createRow(0).createCell(0).setCellStyle(style1);
	        
	        //生成表格标题行
	        HSSFRow row = sheet.createRow(1);
	        for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellStyle(style);
				cell.setCellValue(text);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }
		    response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+new String("温湿度模版.xls".getBytes("utf-8"), "iso8859-1"));// 设置头信息
			response.setCharacterEncoding("utf-8");
		  	OutputStream outputStream = response.getOutputStream();
			
		  	
		  	workbook.write(outputStream);
			outputStream.close();
	        
		}catch(Exception e){
			
		}
		
		return resultModel;
	}
	
	/**
	 * 
	 * @Description:导入页面
	 * @author:bao
	 * @time:2017年7月3日 下午1:31:33
	 */
	@RequestMapping(value = "/importView")
	public ModelAndView importView(HttpServletRequest request)throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/temperature/import.vm");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * @Description:导入模板
	 * @author:bao
	 * @time:2017年7月3日 下午1:31:44
	 */
	@RequestMapping(value = "/importFileDef")
	@ResponseBody
	@LogService(description = "温湿度上传模板并导入")
	public ResultModel<String> importFileTemplet(HttpServletRequest request,@RequestParam("attachment[]") MultipartFile[] attachments){
		ResultModel<String> resultModel = new ResultModel<String>();
		List<String> infos = new ArrayList<String>();
		User user = this.getCurrentUser(request);
		String path = Path.UPLOAD_IMPORT_FILE_PATH;
		try{
			if(attachments != null){
				for (MultipartFile atta : attachments) {
					String uuidname = uploadService.upload(request,atta, path);
					
					
					String fullpath = this.getFilePath()+path+uuidname;
					infos = temperatureService.importFileFromExcel(fullpath);
					
				}
			}
			resultModel.setDatas(infos);
		}catch(ServiceException e){
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		}catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("导入失败");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/exportFile/{ids}")
	@ResponseBody
	@LogService(description = "温湿度excel导出")
	public ResultModel<String> exportFile(HttpServletRequest request,
					HttpServletResponse response,
					@PathVariable("ids")String ids){
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
			List<String> idList = new ArrayList<String>();
			if(!StringUtil.isEmpty(ids)){
				idList = Arrays.asList(ids.split(","));
			}
			List<DWsd> temperatures = temperatureService.getTemperatures(idList);
			
			
	
	        // 获得所有数据
	        HSSFWorkbook workbook = new HSSFWorkbook();			//生成工作簿
			
			
			HSSFSheet sheet = workbook.createSheet("温湿度导出数据");		//生成工作页
			sheet.setDefaultColumnWidth(15);				//设置表格默认列宽度为15个字节
			
			HSSFCellStyle style = workbook.createCellStyle();	//生成样式HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
			
			HSSFCellStyle cellStyle=workbook.createCellStyle();       //设置自动换行的样式
			cellStyle.setWrapText(true);
			
			HSSFDataFormat format = workbook.createDataFormat();
			
			//导出excel的样式
			style.setDataFormat(format.getFormat("@"));

			
			
			
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
	        // 把字体应用到当前的样式
	        style.setFont(font);
			
			
			
	        //绘制表头行
	        HSSFRow rowHeader = sheet.createRow(0);
	        String[] headers = {"记录日期","天气","时间","记录人","温度","湿度","采取措施","备注"};
	        for(int i = 0;i<headers.length;i++){
	        	HSSFCell cell = rowHeader.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellStyle(style);
				cell.setCellValue(text);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        	
	        }
	        for(int j = 0;j<temperatures.size();j++){
	        	DWsd dWsd = temperatures.get(j);
	        	HSSFRow row = sheet.createRow(j+1);
	        	row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dWsd.getJlrq()));
	        	row.createCell(1).setCellValue(dWsd.getTq());
	        	row.createCell(2).setCellValue(dWsd.getWsdsj());
	        	row.createCell(3).setCellValue(dWsd.getJlr());
	        	row.createCell(4).setCellValue(dWsd.getWd());
	        	row.createCell(5).setCellValue(dWsd.getSd());
	        	row.createCell(6).setCellValue(dWsd.getCqcs());
	        	row.createCell(7).setCellValue(dWsd.getBz());
	        	
	        }
	        
	        OutputStream output=response.getOutputStream();  
	        response.reset();  
	        response.setHeader("Content-disposition", "attachment; filename="+new String("温湿度导出数据.xls".getBytes("utf-8"), "iso8859-1"));  
	        response.setContentType("application/msexcel");          
	        workbook.write(output);  
	        output.close();  
		}catch(Exception e){
			resultModel.setSuccess(false);
			resultModel.setMsg("批量导出失败");
		}
		return resultModel;
	}
	
}
