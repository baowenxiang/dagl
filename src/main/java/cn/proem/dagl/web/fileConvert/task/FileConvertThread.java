package cn.proem.dagl.web.fileConvert.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import cn.proem.dagl.web.filePreview.service.AttachPreviewService;
import cn.proem.dagl.web.filePreview.service.UploadPreviewService;
import cn.proem.dagl.web.fuzzyQuery.service.FuzzyQueryService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.constant.FileType;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.docu.entity.DocuAttachment;

public class FileConvertThread extends Thread {
 	private  UploadPreviewService uploadPreviewService;
 	private AttachPreviewService attachPreviewService;
	
 	
 	public void setUploadPreviewService(UploadPreviewService uploadPreviewService) {
        this.uploadPreviewService = uploadPreviewService;
    }
 	
 	public void setAttachPreviewService(AttachPreviewService attachPreviewService) {
        this.attachPreviewService = attachPreviewService;
    }
	//获取项目根目录
	public String getFilePath() {
			String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
	        String tempdir;
	        String classPath[] = classesPath.split("webapps");
	        tempdir = classPath[0];
	        tempdir += "webapps/";
	        tempdir += File.separator;
	        return tempdir;
	}

	@Override
	public void run(){

    	
    	String newFileNameId = null;
    	String fileType = null;
    	File fileConverted = null;
    	File file = null;
    	String outPutFilePath = getFilePath() + Path.UPLOAD_CACHE_PATH;
    	  File fileTo = new File(outPutFilePath);
  		if (!fileTo.exists()) {
  			fileTo.mkdirs();
  		}
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
    							e.printStackTrace();
    						} catch (IOException e) {
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
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
    							e.printStackTrace();
    						} catch (IOException e) {
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							e.printStackTrace();
    						} catch (TransformerException e) {
    							e.printStackTrace();
    						}
        				}
        			}
        			//PDF
        			else if(FileType.PDF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".html");
        				if(!fileConverted.exists()){
        					String filePath1 = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
                    		String destDir = Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH.lastIndexOf("/"));
                    		DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH, filePath1 + ywgj.getWjdz().replaceAll("/", "\\\\"), filePath1 + destDir.replaceAll("/", "\\\\"), newFileNameId+".html");
        				}
        			}
        			//txt
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
    						e.printStackTrace();
    					}
        			}
        			//TIF
        			else if(FileType.TIF.equalsIgnoreCase(fileType)){
        				fileConverted = new File(outPutFilePath+newFileNameId+".jpg");
                		if(!fileConverted.exists()){
                			FileType.convertTifPic(this.getFilePath()+ywgj.getWjdz(), outPutFilePath+newFileNameId+".jpg");
                		}
        			}
        			//视频
        			else if(FileType.isAudioOrVedio(ywgj.getWjlx())){
        				fileConverted = new File(outPutFilePath+newFileNameId+".flv");
                		if(!fileConverted.exists()){
//                			String tagfile = request.getServletContext().getRealPath("/") + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/", "\\") + newFileNameId+".flv";
//                    		String srcfile = request.getServletContext().getRealPath("/") + File.separator + ".." + ywgj.getWjdz().replace("/", "\\");
                			String srcfile = (this.getFilePath()+ywgj.getWjdz()).replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		String tagfile = (outPutFilePath+newFileNameId+".flv").replace("/", "\\").substring(1).replace("\\\\", "\\");
                    		DataTransfer.processFfmpegOther(srcfile,tagfile);
        			}else{
        				continue;
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
    							e.printStackTrace();
    						} catch (IOException e) {
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
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
    							e.printStackTrace();
    						} catch (IOException e) {
    							e.printStackTrace();
    						} catch (ParserConfigurationException e) {
    							e.printStackTrace();
    						} catch (TransformerException e) {
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
        			else{
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
