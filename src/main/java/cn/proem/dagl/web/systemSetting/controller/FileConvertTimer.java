package cn.proem.dagl.web.systemSetting.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;





import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.proem.dagl.web.filePreview.service.AttachPreviewService;
import cn.proem.dagl.web.filePreview.service.UploadPreviewService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.constant.FileType;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.docu.entity.DocuAttachment;
@Controller
@RequestMapping("/w/fileConvert")
public class FileConvertTimer extends BaseCtrlModel {
	
	@Autowired
    private UploadPreviewService uploadPreviewService;
    @Autowired
    private AttachPreviewService attachPreviewService;
    
//	Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式：
//	Seconds Minutes Hours DayofMonth Month DayofWeek Year或 
//	Seconds Minutes Hours DayofMonth Month DayofWeek
//  @Scheduled(cron="0 */10 * * * ?")
    @RequestMapping("/fileConvert")
    public void fileConvert(){
    	
    	String newFileNameId = null;
    	String fileType = null;
    	File fileConverted = null;
    	File file = null;
    	String outPutFilePath = getFilePath() + Path.UPLOAD_CACHE_PATH;
    	//原文挂接
    	List<Ywgj> wygjList = uploadPreviewService.getYwgjFileList();
    	if(wygjList.size()>0 && wygjList != null){
    		for(Ywgj ywgj : wygjList){
    			file = new File(this.getFilePath()+ywgj.getWjdz());
    			//判断文件是否存在
    			if(file.exists()){
    				newFileNameId = ywgj.getWjdz().substring(ywgj.getWjdz().lastIndexOf("\\"), ywgj.getWjdz().lastIndexOf("."));
        			fileType = ywgj.getWjlx();
        			//word
        			if(FileType.DOC.equalsIgnoreCase(fileType) || FileType.DOCX.equals(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					try {
    							DataTransfer.convertWord2Html(this.getFilePath()+ywgj.getWjdz(), outPutFilePath, newFileNameId+".html");
    						} catch (TransformerException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
        				}
        			}
        			//excel
        			else if(FileType.XLS.equalsIgnoreCase(fileType) || FileType.XLSX.equals(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					try {
    							DataTransfer.convertExceltoHtml(this.getFilePath()+ywgj.getWjdz(), outPutFilePath, newFileNameId+".html");
    						} catch (InvalidFormatException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (TransformerException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
        				}
        			}
        			//PFD
        			else if(FileType.PDF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					String filePath1 = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
                    		String destDir = Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH.lastIndexOf("/"));
                    		DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH, filePath1 + ywgj.getWjdz().replaceAll("/", "\\\\"), filePath1 + destDir.replaceAll("/", "\\\\"), newFileNameId+".html");
        				}
        			}
        			//tet
        			else if(FileType.TXT.equalsIgnoreCase(fileType)){
        				fileConverted = new File(this.getFilePath()+ywgj.getWjdz());
                		try {
    						InputStream in= new FileInputStream(fileConverted);
    						 byte[] b = new byte[3];
    				         in.read(b);
    				         in.close();
    				         if (b[0] != -17 || b[1] != -69 || b[2] != -65){
    				 
    				        	 fileConverted = new File(outPutFilePath+newFileNameId+".txt");
    			            		if(!fileConverted.exists()){
    			            			DataTransfer.txtConvert(this.getFilePath()+ywgj.getWjdz(), outPutFilePath+newFileNameId+".txt");
    			            		}
    				         }
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
        			}
        			//pic
        			else if(FileType.TIF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".jpg");
                		if(!fileConverted.exists()){
                			FileType.convertTifPic(this.getFilePath()+ywgj.getWjdz(), outPutFilePath+newFileNameId+".jpg");
                		}
        			}
        			else if(FileType.isPIC(fileType)){
                		
                		}
        			//流媒体
        			else if(FileType.isAudioOrVedio(ywgj.getWjlx())){
        				fileConverted = new File(outPutFilePath+newFileNameId+".flv");
                		if(!fileConverted.exists()){
//                			String tagfile = request.getServletContext().getRealPath("/") + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/", "\\") + newFileNameId+".flv";
//                    		String srcfile = request.getServletContext().getRealPath("/") + File.separator + ".." + ywgj.getWjdz().replace("/", "\\");
                			String srcfile = (this.getFilePath()+ywgj.getWjdz()).replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		String tagfile = (outPutFilePath+newFileNameId+".flv").replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		DataTransfer.processFfmpegOther(srcfile,tagfile);
        			}
        			//
        		}
    			}
    			
    	}
      } //管养接口
    	List<DocuAttachment> doccList = attachPreviewService.getDocuAttachmentList();
    	if(doccList.size()>0 && doccList != null){
    		for(DocuAttachment docu : doccList){
    			file  = new File(this.getFilePath()+docu.getFjnr());
    			if(file.exists()){
    				newFileNameId = docu.getFjnr().substring(docu.getFjnr().lastIndexOf("\\"), docu.getFjnr().lastIndexOf("."));
        			fileType = docu.getFjhz();
        			//word
        			if(FileType.DOC.equalsIgnoreCase(fileType) || FileType.DOCX.equals(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					try {
    							DataTransfer.convertWord2Html(this.getFilePath()+docu.getFjnr(), outPutFilePath, newFileNameId+".html");
    						} catch (TransformerException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
        				}
        			}
        			//excel
        			else if(FileType.XLS.equalsIgnoreCase(fileType) || FileType.XLSX.equals(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					try {
    							DataTransfer.convertExceltoHtml(this.getFilePath()+docu.getFjnr(), outPutFilePath, newFileNameId+".html");
    						} catch (InvalidFormatException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (TransformerException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
        				}
        			}
        			//PFD
        			else if(FileType.PDF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					String filePath1 = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
                    		String destDir = Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH.lastIndexOf("/"));
                    		DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH, filePath1 + docu.getFjnr().replaceAll("/", "\\\\"), filePath1 + destDir.replaceAll("/", "\\\\"), newFileNameId+".html");
        				}
        			}
        			//txt
        			else if(FileType.TXT.equalsIgnoreCase(fileType)){
        				fileConverted = new File(this.getFilePath()+docu.getFjnr());
                		try {
    						InputStream in= new FileInputStream(fileConverted);
    						 byte[] b = new byte[3];
    				         in.read(b);
    				         in.close();
    				         if (b[0] != -17 || b[1] != -69 || b[2] != -65){
    				 
    				        	 fileConverted = new File(outPutFilePath+newFileNameId+".txt");
    			            		if(!fileConverted.exists()){
    			            			DataTransfer.txtConvert(this.getFilePath()+docu.getFjnr(), outPutFilePath+newFileNameId+".txt");
    			            		}
    				         }
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
        			}
        			//pic
        			else if(FileType.TIF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".jpg");
                		if(!fileConverted.exists()){
                			FileType.convertTifPic(this.getFilePath()+docu.getFjnr(), outPutFilePath+newFileNameId+".jpg");
                		}
        			}
        			else if(FileType.isPIC(fileType)){
                		
            		}
        			//流媒体
        			else if(FileType.isAudioOrVedio(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".flv");
                		if(!fileConverted.exists()){
//                			String tagfile = request.getServletContext().getRealPath("/") + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/", "\\") + newFileNameId+".flv";
//                    		String srcfile = request.getServletContext().getRealPath("/") + File.separator + ".." + docu.getFjnr().replace("/", "\\");
                			String srcfile = (this.getFilePath()+docu.getFjnr()).replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		String tagfile = (outPutFilePath+newFileNameId+".flv").replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		DataTransfer.processFfmpegOther(srcfile,tagfile);
        			}
        			//
        		}
    			}
    			
    		}
    	}
   }
}
