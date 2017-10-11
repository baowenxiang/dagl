package cn.proem.eep.entity;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import cn.proem.eep.util.Base64Util;
import cn.proem.eep.util.EEPUtil;
import cn.proem.eep.util.JaxbUtil;

public class Test {
	
	public static void main(String[] args) throws DatatypeConfigurationException{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		try {
			
			EEPUtil util = new EEPUtil(EEPUtil.PKG_DESC, new Date());
			// 立档单位
			StemFrom stemFrom = util.createStemFrom("三桥");
			// 电子文件号
			String elecDocNo = "";
			// 档号
			ArchivalNo archiveNo = util.createArchivalNo("全宗号", "目录号", calendar, "保管期限", 
														  "机构与问题", "类别号", "室案件编号", "馆案件编号", 
														  new BigInteger("10"), "200");	
			ContentDesc desc = util.createContentDesc("题名", "责任人", "2017-6-14", "密级");
			FormalFeature feature = util.createFormalFeature("文件组合类型");
			// 存储位置
			List<String> carryers = new ArrayList<String>();
			carryers.add("载体1");
			carryers.add("载体2");
			StorageLocation location = util.createStorageLocation(carryers);
			// 权限管理
			PrivilegeManagement privilege = util.createPrivilegeManagement("知识产权说明", "授权对象", "授权行为", "控制标识");
			// 文件数据
			List<DocData> datas = new ArrayList<DocData>();
			List<Encode> encodes = new ArrayList<Encode>();
			ElecAttribute elec = util.createElecAttribute("webservice系统接口调试文档（温度）.doc", "127 KB");
			String result = Base64Util.encoder("D:\\pdagl_week.sql");
			EncodeData data = util.createEncodeData("A1", null, result.getBytes());
			encodes.add(util.createEncode(elec, EEPUtil.CODE_DESC, "base64-doc", data));
			List<Document> docs = new ArrayList<Document>();
			datas.add(util.creatDocData("A1", encodes));
			docs.add(util.createDocument("题名", "文档标识符", "文档序号", "文档主从声明", datas));
			RecdData recddata = util.createRecdData(docs);
			// 
			RecdEntity record = util.createRecdEntity("聚合层次", stemFrom, elecDocNo, archiveNo, desc, feature, location, privilege, null, null, recddata);
			RecdEntityBlock recdEnt = util.createRecdEntityBlock(record);
			
			List<BusEnt> busents = new ArrayList<BusEnt>();
			busents.add(util.createBusEnt("业务标识符", "机构人员标识符", "文件标识符", "业务状态", "业务行为", "行为时间"));
			BusEntBlock busEnt = util.createBusEntBlock(busents);
			
			List<AgentEnt> agents = new ArrayList<AgentEnt>();
			agents.add(util.createAgentEnt("机构人员标识符", "机构人员名称"));
			AgentEntBlock agentEnt = util.createAgentEntBlock(agents);
			
			// 封包内容
			EncaContent content = util.createEncaContent(recdEnt, busEnt, agentEnt);
			// 签名对象
			SignedObj signedobj = util.createSignedObj(new Date(), "文件创建者", content);
		    ElecRecdsEncaPkg pkg = util.createElecRecdsEncaPkg(signedobj);
		    PrintWriter pw = new PrintWriter(new FileWriter("D:\\TEST.EEP"));
		    pw.print(JaxbUtil.convertToxml(pkg));
		    pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * xml对象创建
		 *//*
		ObjectFactory factory = new ObjectFactory();
		
		*//**
		 * 版本使用时间
		 *//*
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2009, 1, 1);
		XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		
		*//**
		 * EEP包创建时间
		 *//*
		GregorianCalendar time = new GregorianCalendar();
		cal.setTime(new Date());
		XMLGregorianCalendar createtime = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		
		*//**
		 * EEP包创建单位
		 *//*
		String creater = "长江三桥";
		
		*//**
		 * 档案馆名称
		 *//*
		String archiveName = "三桥";
		*//**
		 * 档案馆代码
		 *//*
		String archiveCode = "SanQiao";
		*//**
		 * 全宗名称
		 *//*
		String fondName = "普恩";
		*//**
		 * 立档单位名称
		 *//*
		String setUpUnit = "立档单位";
		*//**
		 * 电子文件号
		 *//*
		String elecDocNo = "测试文件";
		*//**
		 * 聚合层次
		 *//*
		String aggrhierarchy = "聚合层次";
		
		
		*//**
		 * 文件实体块
		 *//*
		RecdEntityBlock recdEntityBlock = factory.createRecdEntityBlock();
			// 文件实体
		RecdEntity recdEntity = new RecdEntity();
				// 聚合层次
		recdEntity.setAggrhierarchy(aggrhierarchy);
				// 来源
		StemFrom stemFrom = factory.createStemFrom();
		stemFrom.setArchiveCode(archiveCode);
		stemFrom.setArchiveName(archiveName);
		stemFrom.setFondName(fondName);
		stemFrom.setArchiveFromUnit(setUpUnit);
		recdEntity.setStemFrom(stemFrom);
				// 电子文件号
		recdEntity.setElecDocNo(elecDocNo);
				// 档号
		ArchivalNo archivalNo = factory.createArchivalNo();
		
		*//**
		 * 全宗号
		 *//*
		String fondNo = "全宗号";
		*//**
		 * 目录号
		 *//*
		String directoryNo = "目录号";
		*//**
		 * 年份
		 *//*
		GregorianCalendar dh_cal = new GregorianCalendar();
		dh_cal.setTime(new Date());
		XMLGregorianCalendar dh_year = DatatypeFactory.newInstance().newXMLGregorianCalendar(dh_cal);
		*//**
		 * 保管期限
		 *//*
		String storagePeriod = "保管期限";
		*//**
		 * 机构或问题
		 *//*
		String institutions = "机构或问题";
		*//**
		 * 类别号
		 *//*
		String classNo = "类别号";
		*//**
		 * 室编案卷号
		 *//*
		String roomCaseNo = "";
		*//**
		 * 馆编案卷号
		 *//*
		String tubeCaseNo = "";
		*//**
		 * 室编件号
		 *//*
		BigInteger roomNo = new BigInteger("20");
		*//**
		 * 馆编件号
		 *//*
		BigInteger tubeNo = new BigInteger("20");
		*//**
		 * 页号
		 *//*
		String pageNo = "页号";
		
		
		
		
		
		
					// 全宗号
		archivalNo.getContent().add(factory.createFondNo(fondNo));
					// 目录号
		archivalNo.getContent().add(factory.createDirectoryNo(directoryNo));
					// 年度
		archivalNo.getContent().add(factory.createYear(dh_year));
					// 保管期限
		archivalNo.getContent().add(factory.createStoragePeriod(storagePeriod));
					// 机构或问题
		archivalNo.getContent().add(factory.createInstitutions(institutions));
					// 类别号
		archivalNo.getContent().add(factory.createClassNo(classNo));
					// 室编案卷号
		archivalNo.getContent().add(factory.createRoomCaseNo(roomCaseNo));
					// 馆编案卷号
		archivalNo.getContent().add(factory.createTubeCaseNo(tubeCaseNo));
					// 室编件号
		archivalNo.getContent().add(factory.createRoomNo(roomNo));
					// 馆编件号
		archivalNo.getContent().add(factory.createTubeNo(tubeNo));
					// 页号
		archivalNo.getContent().add(factory.createPageNo(pageNo));
		recdEntity.setArchivalNo(archivalNo);
		
		String title = "题名";
		String patitle = "并列题名";
		String subtitle = "副题名";
		String titledesc = "说明题文字";
		
			// 内容描述
		ContentDesc contentDesc = factory.createContentDesc();
					// 题名
		contentDesc.setTitle("题名");
					// 并列题名
		contentDesc.setPaTitle("并列题名");
					// 副题名
		contentDesc.setSubTitle("副题名");
					// 说明题名文字
		contentDesc.setTitleDesc("说明题文字");
					// 主题词
		SubWord subword = factory.createSubWord();
		subword.setThesaurus("主体词");
		contentDesc.getSubWord().add(subword);
					// 关键词
		contentDesc.setKeyWord("关键词");
					// 人名
		contentDesc.setPeopleName("人名");
					// 摘要
		contentDesc.setSummary("摘要");
					// 分类号
		contentDesc.setCategoryNo("分类号");
					// 文件编号
		contentDesc.setFileNo("文件编号");
					// 责任者
		contentDesc.setResponsePeople("责任者");
					// 日期
		contentDesc.setDateE("日期");
					// 文种
		contentDesc.setRecdType("文种");
					// 紧急程度
		contentDesc.setUrgencyLev("紧急程度");
					// 主送
		contentDesc.setMainRecv("主送");
					// 抄送
		contentDesc.setCC("抄送");
					// 密级
		contentDesc.setDense("密级");
					// 保密期限
		contentDesc.setCondfidPeriod("保密期限");
		recdEntity.setContentDesc(contentDesc);
				// 形式特征
		FormalFeature formalFeature = factory.createFormalFeature();
					// 文件组合类型
		formalFeature.setRecdGroupType("文件组合类型");
					// 页数
		formalFeature.setPageNum(new BigInteger("20"));
					// 语种
		formalFeature.setLang("ZH");
					// 稿本
		formalFeature.setManuscript("稿本");
		recdEntity.setFormalFeature(formalFeature);
				// 存储位置
		StorageLocation storageLocation = factory.createStorageLocation();
					// 当前位置
		storageLocation.setCurrentLocation("当前位置");
					// 脱机载体编号
		storageLocation.getOfflineCarrierNo().add("脱机载体编号");
					// 脱机载体存址
		storageLocation.getOfflineCarrierPath().add("脱机载体地址");
					// 缩微号
		storageLocation.setMircoNo("微缩编号");
		recdEntity.setStorageLocation(storageLocation);
				// 权限管理
		PrivilegeManagement privilegeManagement = factory.createPrivilegeManagement();
					// 知识产权说明
		privilegeManagement.setIntePropRightDesc("知识产权说明");
					// 授权
		Authorization authorization = factory.createAuthorization();
						// 授权对象
		authorization.setAuthorizedObject("授权对象");
						// 授权行为
		authorization.setAuthorizedBehavior("授权行为");
		privilegeManagement.getAuthorization().add(authorization);
					// 控制标识
		privilegeManagement.setControlId("控制标识");
		recdEntity.setPrivilegeManagement(privilegeManagement);
				// 信息系统描述
		recdEntity.getInfoSysDesc().add("信息系统描述");
				// 附注
		recdEntity.getRemark().add("附注");
				// 文件数据
		RecdData recdData = factory.createRecdData();
		Document doc = factory.createDocument();
					//文档
						// 文档标识符
		doc.setDocumentId("文档标识符");
						// 文档序号
		doc.setRecdNo("文档序号");
						// 文档主从声明
		doc.setStateOnMainOrAttament("主从声明");
						// 题名
		doc.setTitle("题名");
						// 文档数据
		DocData docdata = factory.createDocData();
							// 编码
		Encode encode = factory.createEncode();
								// 电子属性
		ElecAttribute elecAttribute = factory.createElecAttribute();
									// 格式信息
		elecAttribute.setFormatInfo("格式信息");
									// 计算机文件名
		elecAttribute.setCompFileName("计算机文件名");
									// 计算机文件大小
		elecAttribute.setCompFileSize("计算机文件大小");
									// 文档创建程序
		elecAttribute.setDocCreateProg("文档创建程序");
		encode.setElecAttribute(elecAttribute);
								// 数字化属性
		DigitalAttribute digitalattr = factory.createDigitalAttribute();
									// 数字化对象形态
		digitalattr.setDigitalFormat("数字化对象形态");
									// 扫描分辨率
		digitalattr.setScanRatio("扫描分辨率");
									// 扫描色彩模式
		digitalattr.setScanClrMode("扫描色彩模式");
									// 图像压缩方案
		digitalattr.setScanCompressSchema("图像压缩方案");
		encode.setDigitalAttribute(digitalattr);
								// 编码描述
		encode.setEocodeDesc("编码描述");
								// 反编码关键字
		encode.setRenderKey("反编码关键字");
								// 编码数据
		EncodeData encodeData = factory.createEncodeData();
									// 编码数据ID
		encodeData.setCodedDataId("encode1");
									// 引用编码数据ID
		// encodeData.setRefCodedDataId("1");
		byte[] datas = new byte[10];
		datas[0] = '1';
		encodeData.setValue(datas);
		
		encode.setEncodeData(encodeData);
								// 编码ID
		encode.setEncodeId("encode1");
		docdata.getEncode().add(encode);
							// 文档数据ID
		docdata.setRecdDataId("文档数据ID");
		doc.getDocData().add(docdata);
		recdData.getDocument().add(doc);
		recdEntity.setRecdData(recdData);
		recdEntityBlock.setRecdEntity(recdEntity);
		
			// 文件实体关系
		RecdEntRelation recdEntRelation = factory.createRecdEntRelation();
				// 文件标识符
		recdEntRelation.setRecdId("文件标识符");
				// 被关联文件标识符
		recdEntRelation.setRelatedRecdId("被关联文件标识符");
				// 关系类型
		recdEntRelation.setRelationType("关系类型");
				// 关系
		recdEntRelation.setRelation("关系");
				// 关系描述
		recdEntRelation.setRelationDesc("关系描述");
		
		recdEntityBlock.getRecdEntRelation().add(recdEntRelation);
			
		*//**
		 * 业务实体块
		 *//*
		BusEntBlock busEntBlock = factory.createBusEntBlock();
			// 业务实体
		BusEnt busEnt = factory.createBusEnt();
				// 业务标识符
		busEnt.setBusEntId("业务标识符");
				// 机构人员标识符
		busEnt.setAgentEntId("机构人员标识符");
				// 文件标识符
		busEnt.setRecdId("文件标识符");
				// 业务状态
		busEnt.setBusState("业务状态");
				// 业务行为
		busEnt.setBusBehavior("业务行为");
				// 行为时间
		busEnt.setBehaviorTime("行为时间");
				// 行为依据
		busEnt.setBehaviorReason("行为依据");
				// 行为描述
		busEnt.setBehaviorDesc("行为描述");
		busEntBlock.getBusEnt().add(busEnt);
		
		*//**
		 * 机构人员实体块
		 *//*
		AgentEntBlock agentEntBlock = factory.createAgentEntBlock();
			// 机构人员实体
		AgentEnt agentEnt = factory.createAgentEnt();
				// 机构人员标识符
		agentEnt.setAgentEntId("机构人员标识符");
				// 机构人员类型
		agentEnt.setInstPeopleType("机构人员类型");
				// 机构人员名称
		agentEnt.setInstPeopleName("机构人员名称");
				// 组织机构代码
		agentEnt.setInstUnitCode("组织机构代码");
				// 个人职位
		agentEnt.setPeoplePosition("个人职位");
		agentEntBlock.getAgentEnt().add(agentEnt);
		
			// 机构人员实体关系
		AgentEntRelation agentEntRelation = factory.createAgentEntRelation();
				// 机构人员标识符
		agentEntRelation.setAgentEntId("机构人员标识符");
				// 被关联机构人员标识符
		agentEntRelation.setRelationAgentId("被关联机构人员标识符");
				// 关系类型
		agentEntRelation.setRelationAgentId("关系类型");
				// 关系
		agentEntRelation.setRelation("关系");
				// 关系描述
		agentEntRelation.setRelationDesc("关系描述");
		
		*//**
		 * 封包内容
		 *//*
		EncaContent encaContent = factory.createEncaContent();
			// 文件实体块
		encaContent.setRecdEntityBlock(recdEntityBlock);
			// 业务实体块
		encaContent.setBusEntBlock(busEntBlock);
			// 机构人员实体块
		encaContent.setAgentEntBlock(agentEntBlock);
		
		// 电子文件封装包
		ElecRecdsEncaPkg pkg =(ElecRecdsEncaPkg) factory.createElecRecdsEncaPkg();
		// 封装包格式描述
		JAXBElement<String> pkgtypedesc = factory.createEncaPkgTypeDesc("本EEP根据中华人民共和国档案行业标准DA/T 48-2009《基于XML的电子文件封装规范》生成");
		// 被签名对象
		SignedObj signedObj = factory.createSignedObj();
			// 封装包类型
		signedObj.setEncaPkgType(factory.createEncaPkgType("原始型").getValue());
			// 封装包类型描述
		signedObj.setEncaPkgTypeDesc(factory.createEncaPkgTypeDesc("本封装包包含电子文件数据及其元数据，原始封装，未经修改").getValue());
			// 封装包创建时间
		signedObj.setEncaPkgCreateTime(createtime);
			// 封装包创建单位
		signedObj.setEncaPkgCreator(creater);
			// 封装内容
		signedObj.setEncaContent(encaContent);
		// 组织对象
		JAXBElement<String> pkgdesc = factory.createEncaPkgFormatDesc(pkgtypedesc.getValue());
		pkg.setEncaPkgFormatDesc(pkgdesc.getValue());
		pkg.setVersion(calendar);
		pkg.setSignedObj(signedObj);
		String xml = JaxbUtil.convertToxml(pkg);
		System.out.println(xml);*/
	}
}
