package cn.proem.dagl.web.fileCompilation.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileCompilation.entity.CompileResult;
import cn.proem.dagl.web.fileCompilation.entity.CompileResultMiddle;
import cn.proem.dagl.web.fileCompilation.service.FileCompilationService;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileBase;
import cn.proem.dagl.web.flow.service.FlowService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.preArchive.service.YwgjService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.dagl.web.upload.service.UploadService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

/**
 * 档案编研控制层 
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/w/compilation")
public class FileCompilationController extends BaseCtrlModel {
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DicManagerService dicService;
	@Autowired
	private FileCompilationService fileCompilationService;
	@Autowired
	private YwgjService ywgjService;
	@Autowired
	 private UploadService uploadService;
	@Autowired
	 private FlowService flowService;
	
	
	private String valueOf(Object obj) {
	    return (obj == null) ? "" : obj.toString();
	}
	 /**
     * 
     * 
     * @Method: nowTime 
     * @Description: 获得当前时间
     * @return
     */
    private String nowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
	
	/**
	 * @Description 编研制作初始化页面
	 * @MethodName initMessagePage
	 * @author bao
	 * @date 2017年6月5日
	 * @return ModelAndView
	 */
	@RequestMapping("/compileMade/initView")
	public ModelAndView initCompileMade(){
		ModelAndView view = this.createNormalView("/web/fileCompilation/compileMade.vm");
		
		return view;
		
	}
	
	/**
	 * @Description:进入导入页面
	 * @author:bao
	 * @time:2017年7月25日 上午11:52:49
	 */
	@RequestMapping(value = "/importView")
	public ModelAndView importView(HttpServletRequest request)
			throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileCompilation/importView.vm");
		return modelAndView;
	}
	
	/**
	 * @Description 编研成果初始化页面
	 * @MethodName initMessagePage
	 * @author bao
	 * @date 2017年6月5日
	 * @return ModelAndView
	 */
	@RequestMapping("/compileResult/initView")
	public ModelAndView initCompileResult(){
		ModelAndView view = this.createNormalView("/web/fileCompilation/compileResult.vm");
		
		return view;
		
	}
	
	/**
	 * @Description	编写编研表单页面 
	 * @MethodName toCompileFormView
	 * @author bao
	 * @date 2017年6月5日
	 * @return ModelAndView
	 */
	@RequestMapping("/compileMade/toCompileFormView")
	public ModelAndView toCompileFormView(){
		ModelAndView view = this.createNormalView("/web/fileCompilation/compileForm.vm");
		
		return view;
		
	}
	
	/**
	 * @Description	编写编研表单页面 
	 * @MethodName toCompileFormView
	 * @author bao
	 * @date 2017年6月5日
	 * @return ModelAndView
	 */
	@RequestMapping("/compileResult/toAddFileFormView")
	public ModelAndView toAddFileFormView(String id){
		ModelAndView view = this.createNormalView("/web/fileCompilation/addFileForm.vm");
		view.addObject("id", id);
		return view;
		
	}
	
	
	/**
	 * 获得字典标题值
	 * @param did
	 * @param val
	 * @return
	 */
	private String getDicTitle(String did, String val){
	    List<DictionaryValue> dictionaryValues = dicService.getDicValueList(did);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(val)){
	            return dic.getDvalue();
	        }
	    }
	    return null;
	}
	
	
	/**
	 * @Description 获得档案集合
	 * @MethodName getFilesInfo
	 * @author bao
	 * @date 2017年5月21日
	 * @param dtGridPager
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/getFilesInfo")
	@ResponseBody
	@LogService(description = "获取档案集合")
	public String getFilesInfo(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		//获得表名
		String tablename = String.valueOf(query.getParameters().get("tablename"));
		if(StringUtil.isNotEmpty(tablename)){
			//3 档案 4 审批中 5已拒绝 
			String condition1;
			if(query.getParameters().get("tm")!=null){
				String tm = String.valueOf(query.getParameters().get("tm"));
				condition1 = "delflag = '0' AND isarchive IN (3,4,5,7) AND NVL(tm,0) like '%"+tm+"%'";
			}else{
				condition1 = "delflag = '0' AND isarchive IN (3,4,5,7)";
			}
			dataGrid.setRecordCount(customArchiveService.getEntitiesByConditions(tablename, condition1).size());
			
			List<BaseEntityInf> records = customArchiveService.getEntitiesByPaging(tablename, condition1, dataGrid.getStartRecord(),dataGrid.getPageSize());
			
			for(BaseEntityInf entity : records){
				DtoFileBase dtoFileBase = new DtoFileBase();
				dtoFileBase.setUuid(this.valueOf(entity.get("UUID")));
				dtoFileBase.setDh(this.valueOf(entity.get("DH")));
				dtoFileBase.setTm(this.valueOf(entity.get("TM")));
				dtoFileBase.setWh(this.valueOf(entity.get("WH")));
				dtoFileBase.setZrz(this.valueOf(entity.get("ZRZ")));
				dtoFileBase.setMj(this.getDicTitle("mj", this.valueOf(entity.get("MJ"))));
				dtoFileBase.setBgqx(this.getDicTitle("bgqx", this.valueOf(entity.get("BGQX"))));
				dtoFileBase.setCwrq(this.valueOf(entity.get("CWRQ")));
				dtoFileBases.add(dtoFileBase);
			}
			
			dataGrid.setExhibitDatas(dtoFileBases);
		}else{
			dataGrid.setRecordCount(0);
			dataGrid.setExhibitDatas(dtoFileBases);
		}
		
		return JSON.toJSONStringWithDateFormat(dataGrid,
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * @Description 分页获得编研目录
	 * @MethodName getCompileResultByPage
	 * @author bao
	 * @date 2017年6月6日
	 * @param dtGridPager
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/getCompileResultByPage")
	@ResponseBody
	@LogService(description = "分页获取编研目录")
	public String getCompileResultByPage(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
		
		return JSON.toJSONStringWithDateFormat(fileCompilationService.getCompileResultGrid(queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	@RequestMapping(value = "/getFileCompileResultByPage")
	@ResponseBody
	@LogService(description = "分页获取编研档案")
	public String getFileCompileResultByPage(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		
		if(query.getParameters().get("compileResultIds")==null){
			dataGrid.setRecordCount(0);
			dataGrid.setExhibitDatas(dtoFileBases);
		}else{
			List<String> compileResultIds =  (List<String>)query.getParameters().get("compileResultIds");
			String compileResultIdStr = StringUtils.join(compileResultIds.toArray(), ",");
			queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0),ConditionType.EQ, FieldType.INTEGER, null));
			queryBuilder.addCondition(new QueryCondition("id",compileResultIdStr,ConditionType.IN, FieldType.STRING, "compileResult"));
			DataGrid<CompileResultMiddle> dataGrid1 = fileCompilationService.getCompileResultMiddleGrid(queryBuilder, query.getNowPage(), query.getPageSize());
			
			dataGrid.setRecordCount(dataGrid1.getRecordCount());
			for(CompileResultMiddle compileResultMiddle : dataGrid1.getExhibitDatas()){
				BaseEntityInf entity = customArchiveService.getEntity(compileResultMiddle.getBm(), compileResultMiddle.getDaid());
				DtoFileBase dtoFileBase = new DtoFileBase();
				dtoFileBase.setUuid(this.valueOf(entity.get("UUID")));
				dtoFileBase.setDh(this.valueOf(entity.get("DH")));
				dtoFileBase.setTm(this.valueOf(entity.get("TM")));
				dtoFileBase.setWh(this.valueOf(entity.get("WH")));
				dtoFileBase.setZrz(this.valueOf(entity.get("ZRZ")));
				dtoFileBase.setMj(this.getDicTitle("mj", this.valueOf(entity.get("MJ"))));
				dtoFileBase.setBgqx(this.getDicTitle("bgqx", this.valueOf(entity.get("BGQX"))));
				dtoFileBase.setCwrq(this.valueOf(entity.get("CWRQ")));
				dtoFileBase.setBm(compileResultMiddle.getBm());
				dtoFileBases.add(dtoFileBase);
			}
			
			dataGrid.setExhibitDatas(dtoFileBases);
			
		}
		return JSON.toJSONStringWithDateFormat(dataGrid,
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**保存编研信息
	 * @Description 
	 * @MethodName saveCompileForm
	 * @author bao
	 * @date 2017年6月5日
	 * @param request
	 * @param obj
	 * @return ResultModel<String>
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/saveCompileForm")
	@ResponseBody
	@LogService(description = "保存编研信息")
	public ResultModel<String> saveCompileForm(HttpServletRequest request, @RequestBody Map<String, Object> obj) {
		ResultModel<String> resultModel = new ResultModel<String>();
		try{
			String tm = (String)obj.get("tm");
			String bz = (String)obj.get("bz");
			User user = this.getCurrentUser(request);
			
			CompileResult compileResult = new CompileResult(nowTime(), tm, bz, user);
			String compileResultId = fileCompilationService.saveCompileResult(compileResult);
			List<Map<String,String>> records = (List<Map<String,String>>)obj.get("records");
			for(Map<String,String> record : records){
				String bm = record.get("bm");
				String uuid = record.get("uuid");
				CompileResultMiddle compileResultMiddle = new CompileResultMiddle();
				compileResultMiddle.setBm(bm);
				compileResultMiddle.setCompileResult(commonService.findById(compileResultId, CompileResult.class));
				compileResultMiddle.setDaid(uuid);
				fileCompilationService.saveCompileResultMiddle(compileResultMiddle);
			}
		
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("保存失败");
		}
		return resultModel;
	}
	
	/**
	 * @Description 跳转查询页面
	 * @MethodName queryView
	 * @author bao
	 * @date 2017年5月31日
	 * @param request
	 * @param tablename
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/queryView")
	public ModelAndView queryView(HttpServletRequest request,String tablename) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileCompilation/queryView.vm");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}
	
	/**
	 * @Description 批量删除
	 * @MethodName batchdelete
	 * @author bao
	 * @date 2017年6月6日
	 * @param request
	 * @param fileids
	 * @return ResultModel<String>
	 */
	@RequestMapping(value = "/batchdelete")
	@ResponseBody
	@LogService(description = "批量删除编研成果")
	public ResultModel<String> batchdelete(HttpServletRequest request,@RequestBody Map<String, Object> obj) {
		ResultModel<String> resultModel = new ResultModel<String>();
		List<String> fileids = (List<String>)obj.get("fileids");
		try {
			for(String fileid : fileids){
				fileCompilationService.deleteCompileResult(fileid);
			}
		} catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("删除编研成果失败");
		}
		return resultModel;
	}
	
	
	/**
	 * @Description 编研上传附件
	 * @MethodName uploadFile
	 * @author bao
	 * @date 2017年4月20日
	 * @param request
	 * @param attachments
	 * @return ResultModel<String>
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public ResultModel<String> uploadFile(HttpServletRequest request,
			@RequestParam("attachment[]") MultipartFile[] attachments,
			@RequestParam("dataCollectId") String dataCollectId){
		ResultModel<String> resultModel = new ResultModel<String>();
		
		//解析数据
		
		if(StringUtil.isEmpty(dataCollectId)){
			dataCollectId = UUID.randomUUID().toString();
		}
		List<String> msg = new ArrayList<String>();
		try {
			if (attachments != null) {
				for(int i=0;i<attachments.length;i++){
					MultipartFile atta = attachments[i];
					Ywgj ywgj = new Ywgj();
					if (!atta.isEmpty() && (atta != null && !"".equals(atta.getOriginalFilename()))) {
						String extName = atta.getOriginalFilename().substring(
								atta.getOriginalFilename().lastIndexOf("."));// 获得后缀
						String fileType = atta.getOriginalFilename().substring(
								atta.getOriginalFilename().lastIndexOf(".")+1);// 获得后缀
						String filePath = UUID.randomUUID().toString() + extName;
						String fileName = atta.getOriginalFilename();
						
						msg.add(fileName);
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						
						String path = simpleDateFormat.format(new Date());
						
						String realpath = this.getFilePath() + Path.UPLOAD_BYGJ_FILE_PATH + path +"/" + filePath;
						File fileTo = new File(realpath);
						if (!fileTo.exists()) {
							fileTo.mkdirs();
						}
						atta.transferTo(fileTo);
						
						ywgj.setXsxh(i+1);
						ywgj.setWjdz(Path.UPLOAD_BYGJ_FILE_PATH+path+File.separator+ filePath);
						ywgj.setScrq(new Date());
						ywgj.setScz(getCurrentUser(request).getId());
						ywgj.setWjlx(fileType);
						ywgj.setWjdx(atta.getSize());
						ywgj.setWjm(fileName);
						ywgj.setZlsj(dataCollectId);
						try {
							ywgjService.saveAttachment(ywgj);
						} catch (ServiceException e) {
							resultModel.setSuccess(false);
							resultModel.setMsg(e.getMessage());
						} catch (Exception e) {
							resultModel.setSuccess(false);
							resultModel.setMsg(e.getMessage());
						}
					}
				}
			}
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("附件上传失败");
		}
		resultModel.setMsg("资料"+StringUtils.join(msg.toArray(),",")+"上传成功");
		resultModel.setData(dataCollectId);
		return resultModel;
	}
	
	@RequestMapping(value = "/downloadTemplet")
	@ResponseBody
	@LogService(description = "获取编研成果上传模版")
	public void downloadTempletDef(HttpServletRequest request,HttpServletResponse response) {
		try{
			 String cnNmae = "编研目录上传模版.xls";
			
			
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
	        
	        String[] headers = {"成果名称","编研日期","备注"};
	        
	        //生成表格标题行
	        HSSFRow row = sheet.createRow(0);
	        for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellStyle(style);
				cell.setCellValue(text);
	       
	        }
		    response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+new String(cnNmae.getBytes("utf-8"), "iso8859-1"));// 设置头信息
			response.setCharacterEncoding("utf-8");
		  	OutputStream outputStream = response.getOutputStream();
			
		  	
		  	workbook.write(outputStream);
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/importTemplet")
	@ResponseBody
	@LogService(description = "编研成果导入")
	public ResultModel<String> importTemplet1(HttpServletRequest request,@RequestParam("attachment[]") MultipartFile[] attachments){
		ResultModel<String> resultModel = new ResultModel<String>();
		String path = Path.UPLOAD_IMPORT_FILE_PATH;
		try{
			if (attachments != null){
				for (MultipartFile atta : attachments) {
					String uuidname = uploadService.upload(request,atta, path);
					String allpath = this.getFilePath()+path+uuidname;

					
					
					Workbook wookbook = WorkbookFactory.create(new FileInputStream(allpath));
					//用于放ImpExcelBean的list
					//将Excel的各行记录放入ImpExcelBean的list里面
					//WorkbookFactory是用来将Excel内容导入数据库的一个类
					Sheet sheet = wookbook.getSheetAt(0);//统计excel的行数
					
					
					int rowLen = sheet.getPhysicalNumberOfRows();//excel总行数，记录数=行数-1
					//导入各条记录
					for (int i = 1; i < rowLen; i++) {
						CompileResult compileResult = new CompileResult();
						Row row = sheet.getRow(i);
						compileResult.setTm(getValue(row.getCell(0)));
						compileResult.setByrq(getValue(row.getCell(1)));
						compileResult.setBz(getValue(row.getCell(2)));
						compileResult.setByr(this.getCurrentUser(request));
						fileCompilationService.saveCompileResult(compileResult);
					}
					
				
					
				}
			}
			
		}catch(ServiceException e){
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		}catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("导入失败");
		}
		return resultModel;
	}
	
	 private String getValue(Cell cell) {
	        if (cell == null)
	            return "";
	        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(cell.getBooleanCellValue()).trim();
	        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(cell.getNumericCellValue()).trim();
	        } else {
	            return String.valueOf(cell.getStringCellValue()).trim();
	        }
	    }
	 
	 
	 	@RequestMapping(value = "/exportCompilationZip/{ids}")
		@ResponseBody
		@LogService(description = "档案自定义表单部分批量导出")
	 	public ResultModel<String> exportCompilationZip(
				HttpServletRequest request,
				HttpServletResponse response,
				@PathVariable("ids")String ids){
	 		ResultModel<String> resultModel = new ResultModel<String>();
	 		try{
	 			List<CommonFile> commonFiles = new ArrayList<CommonFile>();
				QueryBuilder queryBuilder = new QueryBuilder();
				queryBuilder.addCondition(new QueryCondition("id",ids,ConditionType.IN, FieldType.STRING, null));
				List<CompileResult> compileResults = fileCompilationService.getCompileResults(queryBuilder);
				//生成excel
				
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
		        
		        String[] headers = {"成果名称","编研日期","备注"};
		        
		        //生成表格标题行
		        HSSFRow row = sheet.createRow(0);
		        for (int i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellStyle(style);
					cell.setCellValue(text);
		       
		        }
		        for(int i = 0;i<compileResults.size();i++){
		        	CompileResult compileResult = compileResults.get(i);
		        	HSSFRow hSSFRow = sheet.createRow(i+1);
		        	hSSFRow.createCell(0).setCellValue(compileResult.getTm());
		        	hSSFRow.createCell(1).setCellValue(compileResult.getByrq());
		        	hSSFRow.createCell(2).setCellValue(compileResult.getBz());
		        }
		        
		        String realpath = Path.UPLOAD_IMPORT_FILE_PATH + UUID.randomUUID().toString() + ".xls";	//
				String filename = "编研目录导出详情.xls";
				
				File fileTo = new File(this.getFilePath()+realpath);
				if (!fileTo.exists()) {
					fileTo.mkdirs();
				}
				fileTo.delete();
				//本地
				OutputStream outputStream = new FileOutputStream(new File(this.getFilePath()+realpath));
				workbook.write(outputStream);
				outputStream.close();
				
				CommonFile commonFileExcel = new CommonFile(realpath, filename);
				commonFiles.add(commonFileExcel);
				for(CompileResult compileResult:compileResults){
					List<CommonFile> commonFiles1 =  flowService.getFilesByDataId(compileResult.getId());
					commonFiles.addAll(commonFiles1);
				}
				
				
				
				
				//生成的ZIP文件名  
				Calendar cld=Calendar.getInstance();
				int millisecond=cld.get(Calendar.MILLISECOND);
		        String tmpFileName = "编研目录导出详情"+"-"+millisecond+".zip"; 
		        //在服务器端创建打包下载的临时文件
		    	String strZipPath= this.getFilePath()+Path.UPLOAD_IMPORT_FILE_PATH+ tmpFileName ;
		    	File file = new File(strZipPath);
		        //循环获得需要打包的文件		            
		         List<File> files=new ArrayList<File>();
		         
		         for(CommonFile commonFile:commonFiles){
		        	 String realPath=this.getFilePath()+ commonFile.getRealpath();
		             files.add(new File(realPath));		            	
		         }
		         
		         //文件输出流
		         FileOutputStream outStream = new FileOutputStream(file);
		         //压缩流
		         @SuppressWarnings("resource")
		         ZipOutputStream toClient = new ZipOutputStream(outStream);
		         toClient.setEncoding("gbk");
		         // 压缩文件列表中的文件
		         int size = files.size();
		         for(int i = 0; i < size; i++) {
		        	 File file1 = (File) files.get(i);
		        	 //zipFile(file, outputStream);
		        	 // 将文件写入到zip文件中
		        	 if(file1.exists()){
		        		 if(file1.isFile()){
		        			 FileInputStream inStream = new FileInputStream(file1);
		        			 BufferedInputStream bInStream = new BufferedInputStream(inStream);
		        			 ZipEntry entry = new ZipEntry(commonFiles.get(i).getFilename());
		        			 toClient.putNextEntry(entry);
		        			 final int MAX_BYTE = 10000 * 1024 *1024;    //最大的流为10M
		        			 long streamTotal = 0;                    //接受流的容量
		        			 int streamNum = 0;                      //流需要分开的数量
		        			 int leaveByte = 0;                      //文件剩下的字符数
		        			 byte[] inOutbyte;                       //byte数组接受文件的数据
		        			 streamTotal = bInStream.available();                        //通过available方法取得流的最大字符数
		        			 streamNum = (int)Math.floor(streamTotal / MAX_BYTE);    //取得流文件需要分开的数量
		        			 leaveByte = (int)streamTotal % MAX_BYTE;                //分开文件之后,剩余的数量
		        			 if (streamNum > 0) {
		        				   for(int j = 0; j < streamNum; ++j){
		        				       inOutbyte = new byte[MAX_BYTE];
		        				       //读入流,保存在byte数组
		        				       bInStream.read(inOutbyte, 0, MAX_BYTE);
		        				       toClient.write(inOutbyte, 0, MAX_BYTE);  //写出流
		        				                  }
		        			          }            
		        			 
		        			 //写出剩下的流数据
		        			  inOutbyte = new byte[leaveByte];
		        			  bInStream.read(inOutbyte, 0, leaveByte);
		        			  toClient.write(inOutbyte);
		        			  toClient.closeEntry();     //Closes the current ZIP entry and positions the stream for writing the next entry
		        			  bInStream.close();    //关闭
		        		      inStream.close();		        			 		        			 		        			 
		        		 }
		        	 }else{
		        		 continue;
		        	 }
		         }
		         
		         toClient.close();
		         outStream.close();     
		 		    
		       //下载zip文件		          	  
		       InputStream fin = null;  
		       ServletOutputStream out1 = null;  
		  		try {  
		  			file = new File(strZipPath); 
		   	        fin = new FileInputStream(file);     	               
		   	            response.setCharacterEncoding("utf-8");
		   	            //获得zip文件的后缀
		   	            String extName = tmpFileName.substring(tmpFileName.lastIndexOf(".")+1);
		   	            response.setContentType("application/"+extName);  
		   	            
		   	            boolean isMSIE = false;
		   	            String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
		   	            String userAgent = request.getHeader("User-Agent");
		   	            for (String signal : IEBrowserSignals) {
		   	                if (userAgent.contains(signal))
		   	                	isMSIE = true;
		   	            }
		   	            if (isMSIE) {
		   	                // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
		   		            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(tmpFileName, "UTF-8"));  
		   	            } else {
		   	            	// 设置浏览器以下载的方式处理该文件默认名为resume.doc  
		   		            response.addHeader("Content-Disposition", "attachment;filename="+ new String(tmpFileName.getBytes("UTF-8"),"ISO8859-1"));  
		   	            }
		   	            
		   	              
		   	            out1 = response.getOutputStream();  
		   	            byte[] buffer2 = new byte[512];  // 缓冲区  
		   	            int bytesToRead = -1;  
		   	            // 通过循环将读入的Word文件的内容输出到浏览器中  
		   	            while((bytesToRead = fin.read(buffer2)) != -1) {  
		   	                out1.write(buffer2, 0, bytesToRead);  
		   	            }  
			   	  } finally {  
			            if(fin != null) fin.close();  
			            if(out1 != null) out1.close();  
			   	  }
		      	//删除临时文件
		      	file.delete();
		      	
		      	File excelFile = new File(this.getFilePath()+commonFileExcel.getRealpath());
				excelFile.delete();
				
	 		}catch(Exception e){
	 			resultModel.setSuccess(false);
				resultModel.setMsg("批量导出失败");
	 		}
			return resultModel;
	 	}
	
}
