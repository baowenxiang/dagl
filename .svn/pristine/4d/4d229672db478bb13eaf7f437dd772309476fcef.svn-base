package cn.proem.dagl.web.eep.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.dagl.core.ca.PCS;
import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.eep.entity.EepEntity;
import cn.proem.dagl.web.eep.service.EEPService;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileBase;
import cn.proem.dagl.web.fileIdentify.service.IdentifyService;
import cn.proem.dagl.web.filePreview.dto.DtoPreviewFile;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.eep.entity.AgentEnt;
import cn.proem.eep.entity.AgentEntBlock;
import cn.proem.eep.entity.ArchivalNo;
import cn.proem.eep.entity.BusEnt;
import cn.proem.eep.entity.BusEntBlock;
import cn.proem.eep.entity.CertiBlock;
import cn.proem.eep.entity.ContentDesc;
import cn.proem.eep.entity.DocData;
import cn.proem.eep.entity.Document;
import cn.proem.eep.entity.ElecRecdsEncaPkg;
import cn.proem.eep.entity.ElecSignature;
import cn.proem.eep.entity.ElecsignatureBlock;
import cn.proem.eep.entity.EncaContent;
import cn.proem.eep.entity.Encode;
import cn.proem.eep.entity.EncodeData;
import cn.proem.eep.entity.FormalFeature;
import cn.proem.eep.entity.LockSignature;
import cn.proem.eep.entity.PrivilegeManagement;
import cn.proem.eep.entity.RecdData;
import cn.proem.eep.entity.RecdEntity;
import cn.proem.eep.entity.RecdEntityBlock;
import cn.proem.eep.entity.SignedObj;
import cn.proem.eep.entity.StemFrom;
import cn.proem.eep.entity.StorageLocation;
import cn.proem.eep.util.Base64Util;
import cn.proem.eep.util.EEPUtil;
import cn.proem.eep.util.JaxbUtil;
import cn.proem.suw.web.common.constant.FileType;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping(value = "/w/example/eep")
public class EEPController extends BaseCtrlModel {

	@Autowired
	private CustomArchiveService customArchiveService;

	@Autowired
	private DicManagerService dicService;

	@Autowired
	private IdentifyService identifyService;

	@Autowired
	private FileControlService fileControlService;

	@Autowired
	private EEPService eepservice;

	private String valueOf(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	private String getDicTitle(String did, String val) {
		List<DictionaryValue> dictionaryValues = dicService
				.getDicValueList(did);
		for (DictionaryValue dic : dictionaryValues) {
			// 获得字典内容
			if (dic.getDvno().equals(val)) {
				return dic.getDvalue();
			}
		}
		return null;
	}

	@RequestMapping(value = "/start/{tablename}")
	public ModelAndView starteep(HttpServletRequest request,
			@PathVariable("tablename") String tablename) {
		ModelAndView modelAndView = new ModelAndView("/web/eep/detail");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}

	@RequestMapping(value = "/read/{tablename}")
	public ModelAndView readeep(HttpServletRequest request,
			@PathVariable("tablename") String tablename) {
		ModelAndView modelAndView = this
				.createNormalView("/web/eep/readdetail.vm");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}

	@RequestMapping(value = "/read")
	public ModelAndView read(HttpServletRequest request) {
		ModelAndView modelAndView = this
				.createNormalView("/web/eep/readdetail.vm");
		return modelAndView;
	}
	
	@RequestMapping("/nopackaging")
	public ModelAndView initNoPackagingView() {
		ModelAndView modelAndView = this.createNormalView("/web/eep/nopackaging.vm");
		return modelAndView;
	}
	
	@RequestMapping("/packaging")
	public ModelAndView initPackagingView() {
		ModelAndView modelAndView = this.createNormalView("/web/eep/packaging.vm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getNoPackaging")
	@ResponseBody
	@LogService(description = "获取档案数据")
	public String getNoPackaging(String dtGridPager, HttpServletRequest request)throws ServiceException {
		DataGridQuery query = parseToQuery(dtGridPager == null ? "": dtGridPager);
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		// 获得表名
		String tablename = String.valueOf(query.getParameters().get("tablename"));
		// 如果表名不为空
		if (StringUtil.isNotEmpty(tablename)) {
			// String 类型 状态1
			String condition1;
			// 如果查询条件TM不为空
			if (query.getParameters().get("tm") != null) {
				// 如果查询条件有TM 
				String tm = String.valueOf(query.getParameters().get("tm"));
				// 查询条件
				condition1 = "delflag = '0' and not exists ( select * from pdagl_eep_done where daid = uuid) and isarchive >= 3  and NVL(tm,0) like '%" + tm + "%'";
			} else {
				// 状态1的条件
				condition1 = "delflag = '0'  and isarchive >= 3 and not exists ( select * from pdagl_eep_done where daid = uuid)";
			}
			// 设置表名 状态1 的大小
			dataGrid.setRecordCount(customArchiveService.getEntitiesByConditions(tablename, condition1).size());
			List<BaseEntityInf> records = customArchiveService.getEntitiesByPaging(tablename, condition1,dataGrid.getStartRecord(), dataGrid.getPageSize());
			for (BaseEntityInf entity : records) {
				DtoFileBase dtoFileBase = new DtoFileBase();
				dtoFileBase.setUuid(this.valueOf(entity.get("UUID")));
				dtoFileBase.setDh(this.valueOf(entity.get("DH")));
				dtoFileBase.setBgqx(this.getDicTitle("bgqx",this.valueOf(entity.get("BGQX"))));
				dtoFileBase.setWh(this.valueOf(entity.get("WH")));
				dtoFileBase.setZrz(this.valueOf(entity.get("ZRZ")));
				dtoFileBase.setTm(this.valueOf(entity.get("TM")));
				dtoFileBase.setCwrq(this.valueOf(entity.get("CWRQ")));
				dtoFileBase.setMj(this.getDicTitle("mj",this.valueOf(entity.get("MJ"))));
				dtoFileBases.add(dtoFileBase);
			}
			dataGrid.setExhibitDatas(dtoFileBases);
		} else {
			dataGrid.setRecordCount(0);
			dataGrid.setExhibitDatas(dtoFileBases);
		}
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value = "/getPackaging")
	@ResponseBody
	@LogService(description = "获取档案数据")
	public String getPackaging(String dtGridPager, HttpServletRequest request)throws ServiceException {
		DataGridQuery query = parseToQuery(dtGridPager == null ? "": dtGridPager);
		DataGrid<DtoFileBase> dataGrid = new DataGrid<DtoFileBase>(query.getNowPage(), query.getPageSize());
		List<DtoFileBase> dtoFileBases = new ArrayList<DtoFileBase>();
		// 获得表名
		String tablename = String.valueOf(query.getParameters().get("tablename"));
		// 如果表名不为空
		if (StringUtil.isNotEmpty(tablename)) {
			// String 类型 状态1
			String condition1;
			// 如果查询条件TM不为空
			if (query.getParameters().get("tm") != null) {
				// 如果查询条件有TM 
				String tm = String.valueOf(query.getParameters().get("tm"));
				// 查询条件
				condition1 = "delflag = '0' and  exists ( select * from pdagl_eep_done where daid = uuid) and isarchive >= 3  and NVL(tm,0) like '%" + tm + "%'";
			} else {
				// 状态1的条件
				condition1 = "delflag = '0'  and isarchive >= 3 and  exists ( select * from pdagl_eep_done where daid = uuid)";
			}
			// 设置表名 状态1 的大小
			dataGrid.setRecordCount(customArchiveService.getEntitiesByConditions(tablename, condition1).size());
			List<BaseEntityInf> records = customArchiveService.getEntitiesByPaging(tablename, condition1,dataGrid.getStartRecord(), dataGrid.getPageSize());
			for (BaseEntityInf entity : records) {
				DtoFileBase dtoFileBase = new DtoFileBase();
				dtoFileBase.setUuid(this.valueOf(entity.get("UUID")));
				dtoFileBase.setDh(this.valueOf(entity.get("DH")));
				dtoFileBase.setBgqx(this.getDicTitle("bgqx",this.valueOf(entity.get("BGQX"))));
				dtoFileBase.setWh(this.valueOf(entity.get("WH")));
				dtoFileBase.setZrz(this.valueOf(entity.get("ZRZ")));
				dtoFileBase.setTm(this.valueOf(entity.get("TM")));
				dtoFileBase.setCwrq(this.valueOf(entity.get("CWRQ")));
				dtoFileBase.setMj(this.getDicTitle("mj",this.valueOf(entity.get("MJ"))));
				dtoFileBases.add(dtoFileBase);
			}
			dataGrid.setExhibitDatas(dtoFileBases);
		} else {
			dataGrid.setRecordCount(0);
			dataGrid.setExhibitDatas(dtoFileBases);
		}
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	//改
	/**
	 * @Description 未封包跳转查询页面
	 * @MethodName queryView
	 * @author zhangq
	 * @date 2017年8月14日
	 * @param request
	 * @param tablename
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/NopackagingQueryView")
	public ModelAndView NopackagingQueryView(HttpServletRequest request,String tablename) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/eep/nopackagingQueryView.vm");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}
	
	/**
	 * @Description 已封包封包跳转查询页面
	 * @MethodName queryView
	 * @author zhangq
	 * @date 2017年8月14日
	 * @param request
	 * @param tablename
	 * @return
	 * @throws ServiceException ModelAndView
	 */
	@RequestMapping(value = "/PackagingQueryView")
	public ModelAndView PackagingQueryView(HttpServletRequest request,String tablename) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/eep/packagingQueryView.vm");
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}
	
	/**
 	 * @Description 根据资料的id获取原文信息
 	 * @MethodName getYwgj
 	 * @author bao
 	 * @date 2017年5月9日
 	 * @param request
 	 * @param id
 	 * @return
 	 * @throws ServiceException ResultModel<Ywgj>
 	 */
 	@RequestMapping(value = "/getYwgj")
  	@ResponseBody
  	@LogService(description = "根据唯一标识ID获取原文信息")
    public ResultModel<EepEntity> getYwgj(HttpServletRequest request,@RequestBody Map<String, String> obj) throws ServiceException {
  		ResultModel<EepEntity> resultModel = new ResultModel<EepEntity>();
  			
  		List<EepEntity> daids = eepservice.getFileBydataId(obj.get("id"));
  				//getFileBydataId(obj.get("id"));
  		
  		resultModel.setDatas(daids);
  		return resultModel;
  	}

	/**
	 * 获取所有档案名
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDtablename")
	@ResponseBody
	@LogService(description = "获取所有档案名")
	public ResultModel<DTableName> getDtablename(HttpServletRequest request) {
		ResultModel<DTableName> resultModel = new ResultModel<DTableName>();

		List<DTableName> dTableNames = identifyService.getAllDtablename();
		resultModel.setDatas(dTableNames);
		return resultModel;
	}

	// 上传
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public ResultModel<String> uploadFile(HttpServletRequest request,
			@RequestParam("attachment[]") MultipartFile[] attachments // 映射一个“attachment”请求，文件名包含2进制数据
	) {
		ResultModel<String> resultModel = new ResultModel<String>();
		List<String> datas = new ArrayList<String>();
		if (attachments != null) { // 如果附件不为空
			for (int i = 0; i < attachments.length; i++) {
				MultipartFile atta = attachments[i];
				if (!atta.isEmpty()
						&& (atta != null && !"".equals(atta
								.getOriginalFilename()))) {
					String extName = atta.getOriginalFilename().substring( // 把原始文件名赋值到extName
																			// 字符串上
							atta.getOriginalFilename().lastIndexOf(".")); // 获得后缀
					// String fileType = atta.getOriginalFilename().substring(
					// //把原始文件名赋值到
					// atta.getOriginalFilename().lastIndexOf(".")+1); // 获得后缀
					String filePath = UUID.randomUUID().toString() + extName; // UUID生成唯一数字。
					// String fileName = atta.getOriginalFilename();

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"yyyyMMdd"); // 日期-时间格式化

					String path = simpleDateFormat.format(new Date());
					datas.add(path); // 服务器地址
					datas.add(filePath); // 文件名
					String realpath = this.getFilePath() + Path.UPLOAD_EEP_PATH
							+ path + "/" + filePath; // 地址：文件资源路径+上传路径+日期+UUID数字文件名
					File fileTo = new File(realpath); // 文件赋值到fileTO
					if (!fileTo.exists()) { // 如果文件目录不存在
						fileTo.mkdirs(); // 那么创建一个目录
					}
					try {
						atta.transferTo(fileTo); // 将上传文件写入到制定文件夹
					} catch (IllegalStateException | IOException e) { //
						e.printStackTrace(); // 当try语句中出现异常时，会执行catch中的语句并打印错误信息的位置和原因。e为引用对象名称
						return resultModel;
					}
				}
			}
		}

		resultModel.setDatas(datas);
		// 解析数据
		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preview/{path}/{filePath}")
	@ResponseBody
	public ModelAndView previeweep(HttpServletRequest request,
			@PathVariable("path") String path,
			@PathVariable("filePath") String filePath) {
		ModelAndView modelAndView = new ModelAndView("/web/eep/preview");
		// String realpath = "realpath";
		// 封装包格式描述
		String fzbgems = "fzbgems";
		// 版本
		String bb = "bb";
		// 分装包类型
		String fzblx = "fzblx";
		// 封装包创建时间
		String fzbcjsj = "fzbcjsj";
		// 封装包创建单位
		String fzbcjdw = "fzbcjdw";
		// 档案馆代码
		String dagdm = "dagdm";
		// 全宗名称
		String qzmc = "qzmc";
		// 电子文件号
		String dzwjh = "dzwjh";
		// 全宗号
		String qzh = "qzh";
		// 目录号
		String mlh = "mlh";
		// 年度
		XMLGregorianCalendar nd;
		// 保管期限
		String bgqx = "bgqx";
		// 机构或问题
		String jghwt = "jghwt";
		// 类别号
		String lbh = "lbh";
		// 事编案卷号
		String sbajh = "sbajh";
		// 馆编卷号
		String gbajh = "gbajh";
		// 事编件号
		BigInteger sbjh;
		// 馆编件号
		BigInteger gbjh;
		// 页号
		String yh = "yh";
		// 题名
		String tm = "tm";
		// 责任者
		String zrz = "zrz";
		// 文件编号
		String wjbh = "wjbh";
		// 日期
		String rq = "rq";
		// 密级
		String mj = "mj";
		// 页数
		String ys = "ys";
		// 控制标识
		String kzbs = "kzbs";
		// 语种
		String yz = "yz";
		// 稿本
		String gb = "gb";
		// 关键词
		String gjc = "gjc";
		// 流水号
		String lsh = "lsh";
		// 拟稿人
		String ngr = "ngr";
		// 文件组合类型
		String wjzhlx = "wjzhlx";

		String realpath = this.getFilePath() + Path.UPLOAD_EEP_PATH + path
				+ "/" + filePath + ".eep"; // 前台页面中转的地址
		File file = new File(realpath); // 得到前台页面中转的地址
		BufferedReader reader = null; // 缓冲字符输入流
		StringBuilder xml = null; // 字符串变量
		try {
			reader = new BufferedReader(new FileReader(file));
			xml = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				xml.append(line); // 连接一个字符串到末尾
			}
			ElecRecdsEncaPkg pkg = JaxbUtil.converyToJavaBean(xml.toString(),
					ElecRecdsEncaPkg.class);
			pkg.getVersion();
			pkg.getSignedObj();

			// 断点
			JAXBElement<String> fond = (JAXBElement<String>) pkg.getSignedObj()
					.getEncaContent().getRecdEntityBlock().getRecdEntity()
					.getArchivalNo().getContent().get(1);
			qzh = fond.getValue();

			JAXBElement<String> directory = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(3);
			mlh = directory.getValue(); // kong

			JAXBElement<XMLGregorianCalendar> year = (JAXBElement<XMLGregorianCalendar>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(5);
			nd = year.getValue(); // xmlKONG

			JAXBElement<String> storagePeriod = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(7);
			bgqx = storagePeriod.getValue(); // kong

			JAXBElement<String> institutions = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(9);
			jghwt = institutions.getValue(); // 赋值为空 xml

			JAXBElement<String> classNO = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(11);
			lbh = classNO.getValue();

			JAXBElement<String> roomCase = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(13);
			sbajh = roomCase.getValue();

			JAXBElement<String> tubeCase = (JAXBElement<String>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(15);
			gbajh = tubeCase.getValue(); // kong

			JAXBElement<BigInteger> room = (JAXBElement<BigInteger>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(17);
			sbjh = room.getValue(); // KONG

			JAXBElement<BigInteger> tube = (JAXBElement<BigInteger>) pkg
					.getSignedObj().getEncaContent().getRecdEntityBlock()
					.getRecdEntity().getArchivalNo().getContent().get(19);
			gbjh = tube.getValue(); // KONG

			JAXBElement<String> page = (JAXBElement<String>) pkg.getSignedObj()
					.getEncaContent().getRecdEntityBlock().getRecdEntity()
					.getArchivalNo().getContent().get(21);
			yh = page.getValue();

			modelAndView.addObject("fzbgems", pkg.getEncaPkgFormatDesc());
			modelAndView.addObject("bb", pkg.getVersion().toString());
			modelAndView
					.addObject("fzblx", pkg.getSignedObj().getEncaPkgType()); // SignedObj
			modelAndView.addObject("fzbcjsj", pkg.getSignedObj()
					.getEncaPkgCreateTime()); // SignedObj
			modelAndView.addObject("fzbcjdw", pkg.getSignedObj()
					.getEncaPkgCreator()); // SignedObj
			modelAndView.addObject("dagdm", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getStemFrom()
					.getArchiveCode()); // 来源:被签名对象，封装内容，文件实体块，文件实体
			modelAndView.addObject("qzmc", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getStemFrom()
					.getFondName()); // 来源
			modelAndView.addObject("dzwjh", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getElecDocNo()); // 文件实体
			modelAndView.addObject("qzh", qzh); // 档号
			modelAndView.addObject("mlh", mlh); // 档号
			modelAndView.addObject("nd", nd); // 档号
			modelAndView.addObject("bgqx", bgqx); // 档号
			modelAndView.addObject("jghwt", jghwt); // 档号
			modelAndView.addObject("lbh", lbh); // 档号
			modelAndView.addObject("sbajh", sbajh); // 档号
			modelAndView.addObject("gbajh", gbajh); // 档号
			modelAndView.addObject("sbjh", sbjh); // 档号
			modelAndView.addObject("gbjh", gbjh); // 档号
			modelAndView.addObject("yh", yh); // 档号
			modelAndView.addObject("tm", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getTitle()); // 内容描述
			modelAndView.addObject("zrz", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getResponsePeople()); // 内容描述
			modelAndView.addObject("wjbh", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getFileNo()); // 内容描述
			modelAndView.addObject("rq", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getDateE()); // 内容描述
			modelAndView.addObject("mj", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getDense()); // 内容描述
			modelAndView.addObject("ys", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getFormalFeature()
					.getPageNum()); // 形式特征
			modelAndView.addObject("kzbs", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity()
					.getPrivilegeManagement().getControlId()); // 权限管理
			modelAndView.addObject("yz", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getFormalFeature()
					.getLang()); // 形式特征
			modelAndView.addObject("gb", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getFormalFeature()
					.getManuscript()); // 形式特征
			modelAndView.addObject("gjc", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getKeyWord()); // 内容描述
			modelAndView.addObject("wjbh", pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getContentDesc()
					.getFileNo()); // 内容描述
			modelAndView.addObject("lsh", lsh); // 无
			modelAndView.addObject("ngr", ngr); // 无
			modelAndView.addObject("wjzhlx", pkg.getSignedObj()
					.getEncaContent().getRecdEntityBlock().getRecdEntity()
					.getFormalFeature().getRecdGroupType());// 形式特征

			// 原文信息
			List<Map<String, Object>> ywlist = new ArrayList<Map<String, Object>>();
			List<Document> documents = pkg.getSignedObj().getEncaContent()
					.getRecdEntityBlock().getRecdEntity().getRecdData()
					.getDocument();
			for (Document doc : documents) {
				// obj.put("tm", doc.getTitle()); // 题名
				// obj.put("wdzcsm", doc.getStateOnMainOrAttament()); // 文档主从声明
				// obj.put("wdsj", doc.getDocData()); // 文档数据
				List<DocData> wdsjlist = doc.getDocData();
				for (DocData wdsj : wdsjlist) {
					List<Encode> bmlist = wdsj.getEncode();
					for (Encode bm : bmlist) {
						Map<String, Object> obj = new HashMap<String, Object>();
						obj.put("wdxh", doc.getRecdNo()); // 文档序号
						obj.put("wdbsf", doc.getDocumentId()); // 文档标识符
						String jsjwjm = bm.getElecAttribute() == null ? null
								: bm.getElecAttribute().getCompFileName();
						obj.put("jsjwjm", jsjwjm); // 计算机文件名
						obj.put("jsjwjdx", bm.getElecAttribute() == null ? null
								: bm.getElecAttribute().getCompFileSize());// 计算机文件大小
						obj.put("smfbl",
								bm.getDigitalAttribute() == null ? null : bm
										.getDigitalAttribute().getScanRatio());// 扫描分辨率
						obj.put("smscms",
								bm.getDigitalAttribute() == null ? null : bm
										.getDigitalAttribute().getScanClrMode());// 扫描色彩模式
						obj.put("fbmgjz", bm.getRenderKey()); // 反编码关键字
						if (bm.getEncodeData() != null) {
							String temp = ConfigReader.readAppHome()
									+ "/uploadFile/eep/temp";
							File dir = new File(temp);
							if (!dir.exists()) {
								dir.mkdirs();
							} else if (!dir.isDirectory()) {
								dir.delete();
								dir.mkdirs();
							}
							Base64Util.decoder2(bm.getEncodeData().getValue(),
									temp + "/" + jsjwjm);
							obj.put("yw", "/uploadFile/eep/temp/" + jsjwjm); // 原文地址
							bm.setEncodeData(new EncodeData());
						}
						ywlist.add(obj);
					}
				}
			}
			modelAndView.addObject("ywlist", ywlist);
			// 业务行为
			List<Map<String, Object>> ywxwlist = new ArrayList<Map<String, Object>>();
			List<BusEnt> ywstlist = pkg.getSignedObj().getEncaContent()
					.getBusEntBlock().getBusEnt();
			if (pkg.getSignedObj().getEncaContent().getBusEntBlock() != null) {
				for (BusEnt ywst : ywstlist) {
					Map<String, Object> ywxw = new HashMap<String, Object>();
					ywxw.put("ywbsf", ywst.getBusEntId()); // 业务标识符
					ywxw.put("jgrymc", ywst.getAgentEntId()); // 机构人员名称
					ywxw.put("xwyj", ywst.getBehaviorReason()); // 行为依据
					ywxw.put("xwsj", ywst.getBehaviorTime()); // 行为时间
					ywxw.put("xwms", ywst.getBehaviorDesc()); // 行为描述
					ywxw.put("ywzt", ywst.getBusState()); // 业务状态
					ywxw.put("ywxw", ywst.getBusBehavior()); // 业务行为
					ywxwlist.add(ywxw);
				}
				modelAndView.addObject("ywxwlist", ywxwlist);
			}

			// 部门用户
			if (pkg.getSignedObj().getEncaContent().getAgentEntBlock() != null) {
				List<Map<String, Object>> bmyhlist = new ArrayList<Map<String, Object>>();
				List<AgentEnt> jgrystlist = pkg.getSignedObj().getEncaContent()
						.getAgentEntBlock().getAgentEnt();
				for (AgentEnt jgry : jgrystlist) {
					Map<String, Object> bmry = new HashMap<String, Object>();
					bmry.put("jgrybsf", jgry.getAgentEntId()); // 机构人员标识符
					bmry.put("jgrylx", jgry.getInstPeopleType()); // 机构人员类型
					bmry.put("jgrymc", jgry.getInstPeopleName()); // 机构人员名称
					bmry.put("zzjgdm", jgry.getInstUnitCode()); // 组织机构代码
					bmry.put("grzw", jgry.getPeoplePosition()); // 个人职位
					bmry.put("ssdw", jgry.getPeoplePosition());// 所属单位
					bmyhlist.add(bmry);
				}
				modelAndView.addObject("bmyhlist", bmyhlist);
			}

			// 数字签名
			if (pkg.getElecsignatureBlock() != null) {
				List<Map<String, Object>> qmlist = new ArrayList<Map<String, Object>>();
				List<ElecSignature> dzqmlist = pkg.getElecsignatureBlock()
						.getElecSignature();
				for (ElecSignature dzqm : dzqmlist) {
					List<CertiBlock> certblocks = dzqm.getCertiBlock();
					for (CertiBlock certblock : certblocks) {
						Map<String, Object> qm = new HashMap<String, Object>();
						qm.put("qmbsf", dzqm.getSignatureId());// 签名标识符
						qm.put("qmgz", dzqm.getSignatureRole()); // 签名规则
						qm.put("qmsj", dzqm.getSignatureTime());// 签名时间
						qm.put("qmr", dzqm.getSignaturePeople());// 签名人
						qm.put("qmsfbs", dzqm.getSignatureAlgoId());// 签名算法标识
						qm.put("szzs", new String(certblock.getCertificate()
								.get(0)));// 数字证书

						qm.put("qmjg", dzqm.getSignatureResult());// 签名结果
						qmlist.add(qm);

						String temp = ConfigReader.readAppHome()
								+ "/uploadFile/eep/cer";
						File dir = new File(temp);
						if (!dir.exists()) {
							dir.mkdirs();
						} else if (!dir.isDirectory()) {
							dir.delete();
							dir.mkdirs();
						}
						String type = ConfigReader.readCAType();
						File cer = new File(temp + "/szzs." + type);
						PrintStream ps = new PrintStream(new FileOutputStream(
								cer));
						ps.print(new String(certblock.getCertificate().get(0)));
						ps.close();
						qm.put("szzspath", "/uploadFile/eep/cer/" + "szzs."
								+ type);
					}
				}
				modelAndView.addObject("qmlist", qmlist);

				// 锁定签名
				if (pkg.getLockSignature() != null) {
					LockSignature sddzqm = pkg.getLockSignature();
					Map<String, Object> sdqm = new HashMap<String, Object>();
					ElecSignature bsdqm = (ElecSignature) sddzqm
							.getLockedSignatureId();
					List<CertiBlock> certblocks = sddzqm.getCertiBlock();
					for (CertiBlock certblock : certblocks) {
						Map<String, Object> qm = new HashMap<String, Object>();
						sdqm.put("qmbsf", bsdqm.getSignatureId());// 签名标识符
						sdqm.put("qmgz", sddzqm.getSignatureRole()); // 签名规则
						sdqm.put("qmsj", sddzqm.getSignatureTime());// 签名时间
						sdqm.put("qmr", sddzqm.getSignaturePeople());// 签名人
						sdqm.put("qmsfbs", sddzqm.getSignatureAlgoId());// 签名算法标识
						sdqm.put("szzs", new String(certblock.getCertificate()
								.get(0)));// 数字证书
						sdqm.put("qmjg", sddzqm.getSignatureResult());// 签名结果

						String temp = ConfigReader.readAppHome()
								+ "/uploadFile/eep/cer";
						File dir = new File(temp);
						if (!dir.exists()) {
							dir.mkdirs();
						} else if (!dir.isDirectory()) {
							dir.delete();
							dir.mkdirs();
						}
						String type = ConfigReader.readCAType();
						File cer = new File(temp + "/sdszzs." + type);
						PrintStream ps = new PrintStream(new FileOutputStream(
								cer));
						ps.print(new String(certblock.getCertificate().get(0)));
						ps.close();
						sdqm.put("szzspath", "/uploadFile/eep/cer/" + "sdszzs."
								+ type);
					}
					modelAndView.addObject("sdqm", sdqm);
				}
			}

			// 封包结构
			modelAndView.addObject("fbjg", JaxbUtil.convertToxml(pkg));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView = new ModelAndView("/web/eep/preview-wrong"); // 上传文件格式错误跳转页面
			return modelAndView;
		}
		return modelAndView;
	}

	@RequestMapping(value = "/doeep")
	@ResponseBody
	public ResultModel<CommonFile> doeep(HttpServletRequest request) throws ServiceException {
		String tablename = request.getParameter("tablename");
		String[] uuids = request.getParameterValues("uuids[]");
		String sign = request.getParameter("sign");
		// 立档单位
		String lddw = eepservice.getProp("lddw");
		if (lddw == null)
			lddw = "立档单位-无";

		// 档号相关
		// 全宗号
		String qzh = "全宗号-无";
		// 目录号
		String mlh = "目录号-无";
		// 年度
		String nd = "2017";
		// 保管期限
		String bgqx = "保管期限-无";
		// 机构或问题
		String jghwt = "机构或问题-无";
		// 类别号
		String lbh = "类别号-无";
		// 室编案件号
		String sbajh = "室案件编号-无";
		// 馆编案件号
		String gbajh = "管案件编号-无";
		// 室编件号
		String sbjh = "0";
		// 馆编件号
		String gbjh = "0";
		// 页号
		String yh = "页号-无";

		// 题名
		String tm = "题名-无";
		// 责任者
		String zrz = "责任者-无";
		// 日期
		String rq = "日期-无";
		// 密级
		String mj = "密级-无";

		// 文件组合类型
		String wjzhlx = "文件组合类型-无";

		// 载体数组
		List<String> zts = new ArrayList<String>();
		String ztlx = "载体类型";
		String ztsl = "载体数量";
		String ztgg = "载体规格";
		String ztdw = "载体单位";

		// 知识产权说明
		String zscqsm = "知识产权说明-无";
		// 授权对象
		String sqdx = "授权对象-无";
		// 授权行为
		String sqxw = "授权行为-无";
		// 控制标识
		String kzbs = "授权行为-无";

		// 文档标识符
		String wdbsf = "文档标识符-无";
		// 组从文档声明
		String zcwdsm = "文档组从说明-无";
		// 文档序号
		String wdxh = "0";
		// 电子文件号
		String elecDocNo = "电子文件号-无";
		String jhcc = "聚合层次-无";

		// 业务相关信息
		String ywbsf = "业务标识符-无";
		// 机构人员标识符
		String jgrybsf = "机构人员标识符-无";
		// 文件标识符
		String wjbsf = "文件标识符-无";
		// 业务状态
		String ywzt = "业务状态-无";
		// 业务行为
		String ywxw = "业务行为-无";
		// 行为时间
		String xwsj = "行为时间-无";
		// 行为依据
		String xwyj = "行为依据-无";
		// 行为描述
		String xwms = "行为描述-无";

		// 机构标识符
		String jgbsf = "机构标识符-无";
		String jgrymc = "机构人员名称-无";
		String jgrylx = "机构人员类型-无";
		String zzjgdm = "组织机构代码-无";
		String grzw = "个人职位-无";
		String ssdw = "所属单位-无";

		ResultModel<CommonFile> resultModel = new ResultModel<CommonFile>();
		List<CommonFile> commonFiles = new ArrayList<CommonFile>();
		try {

			for (int idx = 0; idx < uuids.length; idx++) {
				System.out.println("档案标识: " + uuids[idx]);
				BaseEntityInf archive = null;
				try {
					archive = this.eepservice.getArchiveDetail(tablename,
							uuids[idx]);
					// 全宗号
					if (archive.get("qzh") != null)
						qzh = archive.get("qzh");
					// 目录号
					if (archive.get("mlh") != null)
						mlh = archive.get("mlh");
					// 年度
					if (archive.get("nd") != null)
						nd = archive.get("nd");
					// 保管期限
					if (archive.get("bgqx") != null)
						bgqx = archive.get("bgqx");
					// 机构或问题
					if (archive.get("jghwt") != null)
						jghwt = archive.get("jghwt");
					// 类别
					if (archive.get("lbh") != null)
						lbh = archive.get("lbh");
					// 室编案卷号
					if (archive.get("sbajh") != null)
						sbajh = archive.get("sbajh");
					// 馆编案卷号
					if (archive.get("gbajh") != null)
						gbajh = archive.get("gbajh");
					// 室编件号
					if (archive.get("sbjh") != null)
						sbjh = archive.get("sbjh");
					// 馆编件号
					if (archive.get("gjbh") != null)
						gbjh = archive.get("gjbh");
					// 页号
					if (archive.get("yh") != null)
						yh = archive.get("yh");
					// 题名
					if (archive.get("tm") != null)
						tm = archive.get("tm");
					// 责任者
					if (archive.get("zrz") != null)
						zrz = archive.get("zrz");
					// 密级
					if (archive.get("mj") != null)
						mj = archive.get("mj");
					// 日期
					if (archive.get("rq") != null)
						rq = archive.get("rq");
					// 文档序号
					if (archive.get("xh") != null)
						wdxh = archive.get("xh");
					// 文档标识
					if (archive.get("wdxh") != null) {
						wdbsf = "修改0-文档" + archive.get("wdxh");
						if ("1".equals(archive.get("wdxh"))) {
							wjzhlx = "单件";
						} else {
							wjzhlx = "组合文件";
						}
					} else {
						wdbsf = "修改0-文档1";
						wjzhlx = "单件";
					}
					;

					// 电子文档号
					if (archive.get("dzwdh") != null)
						elecDocNo = archive.get("dzwdh");

					// 业务信息相关
					if (archive.get("wjbsf") != null)
						wjbsf = archive.get("wjbsf");
					if (archive.get("ywbsf") != null)
						ywbsf = archive.get("ywbsf");
					if (archive.get("jgrybsf") != null)
						jgrybsf = archive.get("jgrybsf");
					if (archive.get("ywxw") != null)
						ywxw = archive.get("ywxw");
					if (archive.get("ywzt") != null)
						ywzt = archive.get("ywzt");
					if (archive.get("xwsj") != null)
						xwsj = archive.get("xwsj");

					if (archive.get("xwyj") != null)
						xwyj = archive.get("xwyj");
					if (archive.get("xwms") != null)
						xwms = archive.get("xwms");

					// 部门信息
					if (archive.get("jgbsf") != null)
						jgbsf = archive.get("jgbsf");
					if (archive.get("jgrymc") != null)
						jgrymc = archive.get("jgrymc");
					if (archive.get("jgrylx") != null)
						jgrylx = archive.get("jgrylx");
					if (archive.get("zzjgdm") != null)
						zzjgdm = archive.get("zzjgdm");
					if (archive.get("grzw") != null)
						grzw = archive.get("grzw");
					if (archive.get("ssdw") != null)
						ssdw = archive.get("ssdw");

					// 载体信息 载体类型－载体规格－载体数量－载体单位
					if (archive.get("ztlx") != null)
						ztlx = archive.get("ssdw");
					if (archive.get("ztgg") != null)
						ztgg = archive.get("ztgg");
					if (archive.get("ztsl") != null)
						ztsl = archive.get("ztsl");
					if (archive.get("ztdw") != null)
						ztdw = archive.get("ztdw");
					if ((ztlx != null) || (ztsl != null) || (ztdw != null)
							|| (ztgg != null)) {
						zts.add((ztlx != null ? ztlx + " " : "")
								+ (ztgg != null ? ztgg + " " : "")
								+ (ztsl != null ? ztsl + " " : "")
								+ (ztdw != null ? ztdw + " " : ""));
					}
				} catch (ServiceException e1) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e1.getMessage());
				}
				// 年度
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(Integer.parseInt(nd), 1, 1);
				XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(cal);
				Calendar c = Calendar.getInstance();// 获取一个日历实例
				c.set(2009, 1, 1);// 设定日历的日期
				Date version = c.getTime();
				EEPUtil util = new EEPUtil(EEPUtil.PKG_DESC, version);

				// 立档单位
				StemFrom stemFrom = util.createStemFrom(lddw);

				// 档号
				ArchivalNo archiveNo = util.createArchivalNo(qzh, mlh,
						calendar, bgqx, jghwt, lbh, sbajh, gbajh,
						new BigInteger(sbjh), new BigInteger(gbjh), yh);
				ContentDesc desc = util.createContentDesc(tm, zrz, rq, mj);
				FormalFeature feature = util.createFormalFeature(wjzhlx);
				// 存储位置
				List<String> carryers = new ArrayList<String>();
				for (int i = 0; i < zts.size(); i++) {
					carryers.add(zts.get(i));
				}
				StorageLocation location = util.createStorageLocation(carryers);
				// 权限管理
				PrivilegeManagement privilege = util.createPrivilegeManagement(
						zscqsm, sqdx, sqxw, kzbs);
				RecdEntityBlock recdEnt = null;
				// 文件数据
				// 档案原文获得
				try {
					List<DocData> datas = eepservice.getDocs(util, uuids[idx]);
					List<Document> docs = new ArrayList<Document>();

					docs.add(util
							.createDocument(tm, wdbsf, wdxh, zcwdsm, datas));
					RecdData recddata = util.createRecdData(docs);

					RecdEntity record = util.createRecdEntity(wjzhlx, stemFrom,
							elecDocNo, archiveNo, desc, feature, location,
							privilege, null, null, recddata);
					recdEnt = util.createRecdEntityBlock(record);
				} catch (ServiceException e) {
					resultModel.setSuccess(false);
					resultModel.setMsg("原文不存在");
					return resultModel; // 挂接文件不存在导致错误
				}
				List<BusEnt> busents = eepservice.getWDetail(util, uuids[idx]);
				
				BusEntBlock busEnt = util.createBusEntBlock(busents);

				List<AgentEnt> agents = new ArrayList<AgentEnt>();
				agents.add(util.createAgentEnt(jgbsf, jgrylx, jgrymc, zzjgdm,
						grzw));
				AgentEntBlock agentEnt = util.createAgentEntBlock(agents);

				User user = this.getCurrentUser(request);
				// 文件创建者
				String wjcjz = user.getUsername();
				// 封包内容
				EncaContent content = util.createEncaContent(recdEnt, busEnt,
						agentEnt);
				// 签名对象
				SignedObj signedobj = util.createSignedObj(new Date(), wjcjz,
						content);
				ElecRecdsEncaPkg pkg = null;
				if ("0".equals(sign)) {
					pkg = util.createElecRecdsEncaPkg(signedobj);
				} else {
					PCS pcs = new PCS(ConfigReader.readCAHost(),
							ConfigReader.readCAPort());
					String keyid = ConfigReader.readCAKeyid();
					String password = ConfigReader.readCAPassword();
					byte[] cert = pcs.getCertByKeyID(keyid).getBytes();
					List<byte[]> certs = new ArrayList<byte[]>();
					certs.add(cert);
					// 证书块
					List<CertiBlock> certblocks = new ArrayList<CertiBlock>();
					certblocks.add(util.createCertiBlock(certs));
					// 签名标识符
					String signid = eepservice.getProp("qmbsf");
					if (signid == null) {
						signid = "修改0-签名1";
					}
					String locksignid = signid;
					// 签名规则
					String rule = eepservice.getProp("qmgz");
					if (rule == null) {
						rule = "PKCS#7";
					}

					String lockrule = eepservice.getProp("sdqmgz");
					if (lockrule == null) {
						lockrule = "PKCS#7";
					}
					// 签名时间
					GregorianCalendar signcal = new GregorianCalendar();
					signcal.setTime(new Date());
					XMLGregorianCalendar time = DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(signcal);
					// 签名人
					String people = eepservice.getProp("qmr");
					if (people == null) {
						people = "签名人-无";
					}
					// 签名算法标识
					String algo = eepservice.getProp("qmsfbs");
					if (algo == null) {
						algo = "RSA-SHA1";
					}
					// 签名结果
					String pks1 = pcs.createPKCS1(keyid, password,
							replaceBlank(signedobj.toString()), "1", "0");
					List<ElecSignature> elecsignatures = new ArrayList<ElecSignature>();
					ElecSignature elecSignature = util.createElecSignature(
							signid, rule, time, people,
							Base64.encode(pks1.getBytes()), certblocks, algo);
					elecsignatures.add(elecSignature);
					ElecsignatureBlock elecsignatureBlock = util
							.createElecsignatureBlock(elecsignatures);
					System.out.println(JaxbUtil
							.convertToxml(elecsignatureBlock));
					signcal.setTime(new Date());
					time = DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(signcal);
					String lockpks1 = pcs.createPKCS1(keyid, password,
							replaceBlank(elecsignatureBlock.toString()), "1",
							"0");
					LockSignature lockSignature = util.createLockSignature(
							elecSignature, lockrule, time, people,
							Base64.encode(lockpks1.getBytes()), certblocks,
							algo);
					pkg = util.createElecRecdsEncaPkg(signedobj,
							elecsignatureBlock, lockSignature);
				}

				String filename = "新文件";
				if (archive.get("dh") != null) {
					filename = archive.get("dh");
				} else {
					filename = uuids[idx];
				}
				String eepfile = ConfigReader.readAppHome()
						+ Path.UPLOAD_EEP_PATH + filename + ".eep";
				System.out.print("开始输出EEP文件: '" + eepfile + "' ...");
				PrintWriter pw = new PrintWriter(eepfile);
				System.out.println("输出EEP文件： '" + eepfile + "'完成。");
				pw.print(JaxbUtil.convertToxml(pkg));
				pw.close();

				CommonFile commonFile = new CommonFile();
				commonFile.setRealpath(ConfigReader.readAppHome()
						+ Path.UPLOAD_EEP_PATH);
				commonFile.setFilename(filename + ".eep");
				commonFiles.add(commonFile);
				
				EepEntity da = new EepEntity();
				da.setDaid(uuids[idx]);
				da.setFengBao(Path.UPLOAD_EEP_PATH + filename + ".eep");
				da.setFileName(filename + ".eep");
				eepservice.saveDaid(da);
			}
		} catch (Exception e) {
			resultModel.setSuccess(false);
			// 前台页面提示错误
			resultModel.setMsg(e.getMessage());

			return resultModel;
		}

		resultModel.setSuccess(true);
		resultModel.setDatas(commonFiles);

		return resultModel;
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,String filename)throws IOException{
		String path = ConfigReader.readAppHome() + Path.UPLOAD_EEP_PATH;
		File file = new File(path + filename);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/preview")
	@ResponseBody
	public DtoPreviewFile init(HttpServletRequest request, String wjdz)
			throws ServiceException {
		String wjm = null;
		File yFile = null;
		String wjlx = null;
		if (StringUtil.isNotEmpty(wjdz)) {
			wjm = wjdz.substring(wjdz.lastIndexOf("/") + 1);
			wjlx = wjdz.substring(wjdz.lastIndexOf(".") + 1);
		}

		String outPutFilePath = getFilePath() + Path.UPLOAD_CACHE_PATH;// 输出目录
																		// G:/apache-tomcat-8.0.14/webapps/uploadFile/cache/
		File fileTo = new File(outPutFilePath);
		if (!fileTo.exists()) {
			fileTo.mkdirs();
		}

		yFile = new File(this.getFilePath() + wjdz);// G:/apache-tomcat-8.0.14/uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
		if (!yFile.exists()) {
			throw new ServiceException("文件不存在，或被删除");
		}
		// 绑定原文与资料主键
		// 返回前台预览文件
		DtoPreviewFile prefile = new DtoPreviewFile();
		prefile.setName(wjm);// 档案预览格式说明.txt
		prefile.setType(wjlx);// txt

		String filePath = wjdz;// /uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
		String fileAbsolutePath = getFilePath() + filePath; // G:/apache-tomcat-8.0.14/uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
		String newFileName = wjm.substring(0, wjm.lastIndexOf(".")) + ".html"; // 档案预览格式说明.html
		String newFileNameId = wjdz.substring(wjdz.lastIndexOf("/") + 1,
				wjdz.lastIndexOf("."));// \d86d2fd0-3e64-4745-9511-ee92d416af36
		// newVideoName = fileid + ".flv";
		File htmlFile = new File(outPutFilePath + newFileNameId + ".html");
		// if(!htmlFile.exists()){
		if (FileType.DOC.equals(wjlx) || FileType.DOCX.equals(wjlx)) {
			if (!htmlFile.exists()) {
				try {
					DataTransfer.convertWord2Html(fileAbsolutePath,
							outPutFilePath, newFileNameId + ".html");
					prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId
							+ ".html");
				} catch (TransformerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
			} else {
				prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId
						+ ".html");
			}

		} else if (FileType.XLS.equals(wjlx) || FileType.XLSX.equals(wjlx)) {
			if (!htmlFile.exists()) {
				try {
					DataTransfer.convertExceltoHtml(fileAbsolutePath,
							outPutFilePath, newFileNameId + ".html");
					prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId
							+ ".html");
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} else {
				prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId
						+ ".html");
			}

		} else if (FileType.PDF.equals(wjlx)) {
			/*
			 * if(!htmlFile.exists()){ String filePath1 =
			 * (this.getFilePath().substring
			 * (1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
			 * String destDir =
			 * Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH
			 * .lastIndexOf("/"));// /uploadFile/cache
			 * DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH,
			 * filePath1 + filePath.replaceAll("/", "\\\\"), filePath1 +
			 * destDir.replaceAll("/", "\\\\"), newFileNameId+".html");
			 * prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
			 * }else{
			 * prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html"); }
			 */

		} else if (FileType.TXT.equals(wjlx)) {
			File file = new File(fileAbsolutePath);
			try {
				InputStream in = new FileInputStream(file);
				byte[] b = new byte[3];
				in.read(b);
				in.close();
				if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
					prefile.setCache(wjdz);
				} else {
					File txtfile = new File(outPutFilePath + newFileNameId
							+ ".txt");
					if (!txtfile.exists()) {
						DataTransfer.txtConvert(fileAbsolutePath,
								outPutFilePath + newFileNameId + ".txt");
					}
					prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId
							+ ".txt");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (FileType.isPIC(wjlx)) {
			prefile.setCache(wjdz);
		} else if (FileType.TIF.equalsIgnoreCase(wjlx)) {
			File jpgfile = new File(outPutFilePath + newFileNameId + ".jpg");
			if (!jpgfile.exists()) {
				FileType.convertTifPic(fileAbsolutePath, outPutFilePath
						+ newFileNameId + ".jpg");
			}

			prefile.setCache(Path.UPLOAD_CACHE_PATH + newFileNameId + ".jpg");
		} else if (FileType.isAudioOrVedio(wjlx)) {
			File flvFile = new File(outPutFilePath + newFileNameId + ".flv");
			if (!flvFile.exists()) {
				// String tagfile = request.getServletContext().getRealPath("/")
				// + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/",
				// "\\") + newFileNameId+".flv";
				// String srcfile = request.getServletContext().getRealPath("/")
				// + File.separator + ".." + ywgj.getWjdz().replace("/", "\\");
				String srcfile1 = (this.getFilePath() + wjdz)
						.replace("/", "\\").substring(1).replace("\\\\", "\\");
				String tagfile1 = (outPutFilePath + newFileNameId + ".flv")
						.replace("/", "\\").substring(1).replace("\\\\", "\\");
				DataTransfer.processFfmpegOther(srcfile1, tagfile1);
			}
			prefile.setType("flv");
			prefile.setCache(newFileNameId);
		} else if (FileType.MP3.equalsIgnoreCase(wjlx)) {
			prefile.setType("mp3");
		} else {
			prefile.setType("others");
			prefile.setCache(wjdz);
		}
		return prefile;

	}

	@RequestMapping(value = "/viewAudio")
	@ResponseBody
	public ModelAndView viewAudio(String wjdz) {
		ModelAndView modelAndView = this
				.createNormalView("/web/preview/mp3.vm");
		try {
			String wjm = wjdz.substring(wjdz.lastIndexOf("/") + 1);
			String wjlx = wjdz.substring(wjdz.lastIndexOf(".") + 1);
			modelAndView.addObject("filename", wjm);
			modelAndView.addObject("filepath", wjdz);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/displayPDF/**")
	public void displayPDF(HttpServletResponse response,
			HttpServletRequest request) throws ServiceException {
		String tagname = extractPathFromPattern(request);
		try {
			String wjm = tagname.substring(tagname.lastIndexOf("/") + 1);

			File file = new File(this.getFilePath() + "/" + tagname);
			if (!file.exists()) {
				throw new ServiceException("文件不存在，或被删除");
			}
			FileInputStream fileInputStream = new FileInputStream(file);
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ wjm);
			response.setContentType("multipart/form-data");
			OutputStream outputStream = response.getOutputStream();
			IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String extractPathFromPattern(
			final HttpServletRequest request) {
		String path = (String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request
				.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern,
				path);
	}

	private static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
