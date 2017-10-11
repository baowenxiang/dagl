package cn.proem.dagl.web.oaservice.service.impl;

import cn.proem.dagl.web.oaservice.service.OaService;



//@Service
public class OaServiceImpl implements OaService {
//	@Override
//	public String[] getEdocByAccount_client(String key,String startDate,String endDate){
//		String result = null;
//		String[] resultArr = null;
//		try {
//			EndpointReference targetEPR = new EndpointReference("http://221.226.17.202:88/seeyon/services/transferService?wsdl");
//			RPCServiceClient serviceClient;
//			serviceClient = new RPCServiceClient();
//			Options options = serviceClient.getOptions();
//			options.setTo(targetEPR);
//			//QName opAdd =new QName("http://impl.service.transfer.plugin.v3x.seeyon.com", "getEdocByAccount");
//			QName opAdd =new QName("http://impl.service.transfer.apps.seeyon.com", "getEdocByAccount");
//			Class[] returnTypes = new Class[] {String.class};
//			Object[] opAddArgs = new Object[] {key,startDate,endDate};
//			Object[] response = serviceClient.invokeBlocking(opAdd, opAddArgs, returnTypes);
//			result = (String)response[0];
//			resultArr = result.split(";;");
//			System.out.println(result);
//		} catch (AxisFault e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return resultArr;
//
//	}


	/**
	 * func:根据公文ID与单位密匙获取指定公文数据
	 * @param key
	 * @param id
	 * @param dir
	 * @return
	 */
//	public void exportEdocDate_client(String key,String id,String dir){
//		
//		EndpointReference targetEPR = new EndpointReference("http://221.226.17.202:88/seeyon/services/transferService?wsdl");
//		RPCServiceClient serviceClient;
//		try {
//			serviceClient = new RPCServiceClient();
//			Options options = serviceClient.getOptions();
//			options.setTo(targetEPR);
//			//QName opAdd =new QName("http://impl.service.transfer.plugin.v3x.seeyon.com", "exportEdocDate");
//			QName opAdd =new QName("http://impl.service.transfer.apps.seeyon.com", "exportEdocDate");
//			Class[] returnTypes = new Class[] {String.class};
//			Object[] opAddArgs = new Object[] {key,id};
//			Object[] response = serviceClient.invokeBlocking(opAdd, opAddArgs, returnTypes);
//			String result = (String)response[0];
////			System.out.println(result);
//			try  {   
//				byte [] buff=new byte[]{};  
//				FileOutputStream output=new FileOutputStream(dir+id+".xml");  
//				buff=result.getBytes("UTF-8");      
//				output.write(buff, 0, buff.length);  
//				output.flush();
//				output.close();
//			} catch (Exception e)   {   
//				e.printStackTrace();  
//			}
//		} catch (AxisFault e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	 
//	}

	/**
	 * func:根据公文ID获取指定公文文单的HTML格式
	 * @param id
	 */
//	public void exportOfflineEdocModel_client(String id){
//		try {
//			EndpointReference targetEPR = new EndpointReference("http://221.226.17.202:88/seeyon/services/transferService?wsdl");
//			RPCServiceClient serviceClient;
//			serviceClient = new RPCServiceClient();
//			Options options = serviceClient.getOptions();
//			options.setTo(targetEPR);
//			//QName opAdd =new QName("http://impl.service.transfer.plugin.v3x.seeyon.com", "exportOfflineEdocModel");
//			QName opAdd =new QName("http://impl.service.transfer.apps.seeyon.com", "exportOfflineEdocModel");
//			Class[] returnTypes = new Class[] {String.class};
//			Object[] opAddArgs = new Object[] {id};//公文id
//			Object[] response = serviceClient.invokeBlocking(opAdd, opAddArgs, returnTypes);
//			String result = (String)response[0];
//			System.out.println("exportOfflineEdocModel = "+result);
//			try  {   
//				byte [] buff=new byte[]{};  
//				FileOutputStream output=new FileOutputStream("D:\\公文文单_"+id+".html");  
//				buff=result.getBytes("UTF-8");      
//				output.write(buff, 0, buff.length);  
//				output.flush();
//				output.close();
//			} catch (Exception e)   {   
//				e.printStackTrace();  
//			}
//		} catch (AxisFault e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}


	/**
	 * func:根据公文ID获取指定公文文单的图片格式(包括收发文办文单、拟稿纸)
	 * @param key
	 * @param id
	 * @param type
	 */
//	public void transferDownloadImgService_client(String key,String id,String type,String dir){
//		StringBuffer parameters = new StringBuffer(); 
//		parameters.append("summaryId="+id);//公文或表单流程id
//		parameters.append("&type="+type);//公文 edoc 表单col
//		URL preUrl = null;
//		URLConnection uc = null;
//		try {
//	    		preUrl = new URL("http://221.226.17.202:88/seeyon/services/transferDownloadImgService");
//	    		String s = parameters.toString();
//	    		uc = preUrl.openConnection();
//	    		uc.setDoOutput(true);
//	    		uc.setUseCaches(false);
//	    		uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//	    		HttpURLConnection hc = (HttpURLConnection) uc;
//	    		hc.setRequestMethod("POST");
//	    		OutputStream os = hc.getOutputStream();
//	    		DataOutputStream dos = new DataOutputStream(os);
//	    		dos.writeBytes(s);
//	    		dos.flush();
//	    		dos.close();
//	    		FileOutputStream file = new FileOutputStream(dir+id+".jpg");
//	    		InputStream is = hc.getInputStream();
//	    		int ch;
//	    		while ((ch = is.read()) != -1) {
//	        		file.write(ch);
//	    		}
//	    	if (is != null) is.close();
//	    	System.out.println("获取文件完成");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * func:根据文件ID下载指定公文的正文及附件
	 * @param id
	 */
//	public void transferDownloadService_client(String key,String fileId,String fileDir,String fileName,String fileSuffix){
//		StringBuffer parameters = new StringBuffer(); 
//		parameters.append("fileId="+fileId);
//		URL preUrl = null;
//		URLConnection uc = null;
//		try {
//	    		preUrl = new URL("http://221.226.17.202:88/seeyon/services/transferDownloadService");
//	    		String s = parameters.toString();
//	    		uc = preUrl.openConnection();
//	    		uc.setDoOutput(true);
//	    		uc.setUseCaches(false);
//	    		uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//	    		HttpURLConnection hc = (HttpURLConnection) uc;
//	    		hc.setRequestMethod("POST");
//	    		OutputStream os = hc.getOutputStream();
//	    		DataOutputStream dos = new DataOutputStream(os);
//	    		dos.writeBytes(s);
//	    		dos.flush();
//	    		dos.close();
//	    		FileOutputStream file = new FileOutputStream(fileDir+fileName+"."+fileSuffix);
//	    		InputStream is = hc.getInputStream();
//	    		int ch;
//	    		while ((ch = is.read()) != -1) {
//	        		file.write(ch);
//	    		}
//	    	if (is != null) is.close();
//	    	System.out.println("获取文件完成");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}


//	public String timeConv(Date date,String str) {
//		
//		//初始化
//		String dateString = null;
//		SimpleDateFormat formatter = null;
//		
//		//根据输入参数选择对应的转换格式
//		if(str.equals("yyyy-MM-dd")){
//			formatter = new SimpleDateFormat("yyyy-MM-dd");
//		}else if(str.equals("yyyy-MM-dd HH:mm:ss")){
//			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		}
//		dateString = formatter.format(date);
//		return dateString;
//	}
	
//	public Map readXML(String gwDir,String ywDir,String id)
//	    {
//			System.out.println("ID号为："+id+"的公文XML解析开始....");
//			//--初始化
//		 	OAGwObj gwObj = new OAGwObj();
//	    	List<OAFjObj> fjObjList = new ArrayList<OAFjObj>();
//	    	StringBuffer gwObjStr = new StringBuffer();
//	    	StringBuffer fileIds = new StringBuffer();
//	    	StringBuffer fileSuffixs = new StringBuffer();
//	    	StringBuffer fileNames = new StringBuffer();
//	    	String[] gwObjArr = null;
//	    	String[] fileIdsArr = null;
//	    	String[] fileSuffixsArr = null;
//	    	String[] fileNamesArr = null;
//	    	Map map = new HashMap();
//	    	
//	    	try {
//	            SAXReader reader = new SAXReader();
//	            Document doc = reader.read(new File(gwDir+id+".xml"));
//	            
//	            //--1.解析XML
//	            Element root = doc.getRootElement();
//	            //--1.1第一层
//	            for(Iterator it=root.elementIterator();it.hasNext();){    
//	                Element element = (Element) it.next();
//	                Attribute attribute=element.attribute("propertyname"); 
//	                String text = attribute.getText();
//	                System.out.println(text);
////	                if(text.equals("docCreateTime")){
////	                	System.out.println("===="+element.getText());
////	                	
////	                	if(element.getText()==null||element.getText()==""){
////							gwObjStr.append(" ");
////						}else{
////							gwObjStr.append(element.getText());
////						}
////						gwObjStr.append(";;");
////	                }
//	                
//	                //--1.2第二层
//	                for(Iterator it2=element.elementIterator();it2.hasNext();){ 
//	                	
//	                	Element element2 = (Element) it2.next();
//	                	Attribute attribute2=element2.attribute("type"); 
//	                	if(attribute2!=null){
//	                		String text2 = attribute2.getText();
//	                		
//	                		//--1.3第三层
//	            			for(Iterator it3=element2.elementIterator();it3.hasNext();){
//	            				
//	            				Element element3 = (Element) it3.next();
//	            				Attribute attribute3=element3.attribute("propertyname"); 
//	            				Attribute fileId=element3.attribute("value");
//	            				
//	            				String text3 = attribute3.getText();
//	            				
//	            				if(text2.equals("DocumentFormExport")){
//	            					
//	            					//--1.4获取正文字段信息
//	            					if(text3.equals("value")){
//	            						if(element3.getText()==null||element3.getText()==""){
//	            							gwObjStr.append(" ");
//	            						}else{
//	            							gwObjStr.append(element3.getText());
//	            						}
//	            						gwObjStr.append(";;");
//	            					}
//	            				}else if(text2.equals("AttachmentExport")){
//	            					
//	            					//--1.5获取附件字段信息（ID、后缀名、文件名）
//	            					if(text3.equals("id")){
//	            						if(fileId.getText()==null||fileId.getText()==""){
//	            							fileIds.append(" ");
//	            						}else{
//	            							fileIds.append(fileId.getText());
//	            						}
//	            						fileIds.append(";;");
//	            					}
//	            					if(text3.equals("filesuffix")){
//	            						if(element3.getText()==null||element3.getText()==""){
//	            							fileSuffixs.append(" ");
//	            						}else{
//	            							fileSuffixs.append(element3.getText());
//	            						}
//	            						fileSuffixs.append(";;");
//
//	            					}
//	            					if(text3.equals("fileName")){
//	            						if(element3.getText()==null||element3.getText()==""){
//	            							fileNames.append(" ");
//	            						}else{
//	            							fileNames.append(element3.getText());
//	            						}
//	            						fileNames.append(";;");
//	            					}
//	            					
//	            				}
//	            			}
//	                		
//	                	}
//	                }
//	            }
//	            
//	            //--2.将公文字段信息存入实体
//	            gwObjArr = (gwObjStr.toString()).split(";;");
//	            gwObj.setFormId(gwObjArr[0]);
//	            gwObj.setTm(gwObjArr[1]);
//	            gwObj.setFlh(gwObjArr[3]);
//	            gwObj.setWh(gwObjArr[5]);
//	            if(gwObjArr[7].equals(" ")||gwObjArr[7].equals("")){
//	            	gwObj.setMj(null);
//	            }else{
//	            	gwObj.setMj(gwObjArr[7]);
//	            }
////	            gwObj.setZrz(gwObjArr[14]+";"+gwObjArr[11]);
//	            
//	            //1：收文，0：发文
//	            if(gwObjArr[3].equals("1")&&(gwObjArr.length>21)){
//	            	gwObj.setZrz(gwObjArr[14]);
//	            	gwObj.setCwrq(gwObjArr[21]);
//	            }else if(gwObjArr[3].equals("1")&&(gwObjArr.length==21)){
//	            	gwObj.setZrz(" ");
//	            	gwObj.setCwrq(" ");
//	            }else if(gwObjArr[3].equals("0")){
//	            	gwObj.setZrz(gwObjArr[14]);
//	            	gwObj.setCwrq(gwObjArr[16]);
//	            }
//	            
//	            gwObj.setZtc(gwObjArr[19]);
//	            
//	            //--3.将附件字段信息存入实体
//	            fileIdsArr = (fileIds.toString()).split(";;");
//	            fileSuffixsArr = (fileSuffixs.toString()).split(";;");
//	            fileNamesArr = (fileNames.toString()).split(";;");
//	            for(int i=0;i<fileIdsArr.length;i++){
//	            	OAFjObj fjObj = new OAFjObj();
//	            	fjObj.setFileDir(ywDir);
//	            	fjObj.setFileId(fileIdsArr[i]);
//	            	fjObj.setFileSuffix(fileSuffixsArr[i]);
//	            	fjObj.setFileName(fileNamesArr[i]);
//	            	fjObjList.add(fjObj);
//	            	
//	            }
//	            map.put("gw", gwObj);
//	            map.put("fj", fjObjList);
//	            
////	            //--4.归档附件获取
////	            for(OAFjObj fj:fjObjList){
////	            	transferDownloadService_client(key,fj.getFileId(),ywDir,fj.getFileName(),fj.getFileSuffix());
////	            }
//	            
//	        } catch (DocumentException | MalformedURLException e) {
//	            e.printStackTrace();
//	        }
//	        
//	        System.out.println("ID号为："+id+"的公文XML解析完成！");
//			return map;
//	    }
	
//	public static void main(String[] args){
//		OaServiceImpl se = new OaServiceImpl();
//		se.exportEdocDate_client("", "", "");
//		se.exportEdocDate_client("123","","");
//		System.out.print("111");
//	}
}
