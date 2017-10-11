package cn.proem.dagl.web.filePreview.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.dagl.web.filePreview.dto.DtoPreviewFile;
import cn.proem.dagl.web.filePreview.service.AttachPreviewService;
import cn.proem.dagl.web.filePreview.service.UploadPreviewService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.constant.FileType;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.common.util.Doc2Html;
import cn.proem.suw.web.docu.entity.DocuAttachment;


@Controller
@RequestMapping(value = "/w/preview")
public class PreviewController extends BaseCtrlModel{
    @Autowired
    private UploadPreviewService uploadPreviewService;
    @Autowired
    private AttachPreviewService attachPreviewService;
    
    // 原文
    private Ywgj ywgj;
    // 附件
    private DocuAttachment attach;
    // 返回前台预览文件
    private DtoPreviewFile prefile;
    
    /**
     * 根据文件唯一ID获得文件并返回预览页面
     * @param fileid 文件唯一ID
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/video/{filename}/{fileid}")
    @ResponseBody
    @LogService(description = "根据文件唯一标识获取文件并预览")
    public ModelAndView video(@PathVariable("filename") String filename,
                              @PathVariable("fileid") String fileid) throws ServiceException {
        ModelAndView modelAndView = this.createNormalView("/web/preview/video.vm");
        modelAndView.addObject("filename", filename);
        modelAndView.addObject("filepath", Path.UPLOAD_CACHE_PATH + fileid + ".flv");
        return modelAndView;
    }

    
    @RequestMapping(value = "/viewAudio/{fileid}")
    @ResponseBody
    @LogService(description = "识别MP3文件")
    public ModelAndView viewAudio(@PathVariable("fileid") String fileid) {
	ModelAndView modelAndView = this.createNormalView("/web/preview/mp3.vm");
		 try {
			ywgj = uploadPreviewService.getFile(fileid);
			modelAndView.addObject("filename", ywgj.getWjm());
			String path = ywgj.getWjdz();
			String convertPath = path.substring(0, path.lastIndexOf("\\"))+"/"+path.substring(path.lastIndexOf("\\")+1);
			 modelAndView.addObject("filepath",convertPath);
			//File file = new File(this.getFilePath()+ywgj.getWjdz());
			//modelAndView.addObject("filepath",file.getAbsolutePath());
		 } catch (ServiceException e) {
			e.printStackTrace();
		 } catch(Exception ex){
			 ex.printStackTrace();
		 }
	     return modelAndView;
	}
    
    /**
     * 根据文件唯一ID获得文件并返回预览页面
     * @param fileid 文件唯一ID
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/{fileid}")
    @ResponseBody
    @LogService(description = "根据文件唯一标识获取文件并预览")
    public DtoPreviewFile init(HttpServletRequest request,
                               @PathVariable("fileid") String fileid) throws ServiceException {
    	
    	File yFile = null;
    	// 获得原文
        ywgj = uploadPreviewService.getFile(fileid);
       
        // 获得附件
        attach = attachPreviewService.getFile(fileid);
        
        
        String filePath = null;
        String fileAbsolutePath = null;
        String outPutFilePath = getFilePath() + Path.UPLOAD_CACHE_PATH;//输出目录    G:/apache-tomcat-8.0.14/webapps/uploadFile/cache/
        File fileTo = new File(outPutFilePath);
		if (!fileTo.exists()) {
			fileTo.mkdirs();
		}
        String newFileNameId = null;
        String newFileName = null;
        String newVideoName = null;
        if(ywgj !=null && attach != null){
            throw new ServiceException("附件与原文得文件唯一ID重复，请联系管理人员处理");
        }else if(ywgj != null){
        	 yFile = new File(this.getFilePath()+ywgj.getWjdz());//  G:/apache-tomcat-8.0.14/uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
             if(!yFile.exists()){
             	throw new ServiceException("文件不存在，或被删除");
             }
            // 绑定原文与资料主键
            prefile = new DtoPreviewFile();
            prefile.setName(ywgj.getWjm());//档案预览格式说明.txt
            prefile.setType(ywgj.getWjlx());//txt
            prefile.setRecord(ywgj.getZlsj());//档案uuid
            filePath = ywgj.getWjdz();//   /uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
            fileAbsolutePath = getFilePath()+filePath; //G:/apache-tomcat-8.0.14/uploadFile/ywgj/20170711\d86d2fd0-3e64-4745-9511-ee92d416af36.txt
            newFileName = ywgj.getWjm().substring(0, ywgj.getWjm().lastIndexOf("."))+".html"; //档案预览格式说明.html
            if(ywgj.getWjdz().lastIndexOf("\\") == -1){
                newFileNameId = yFile.getName().substring(0, yFile.getName().lastIndexOf("."));//\d86d2fd0-3e64-4745-9511-ee92d416af36
            }else{
                newFileNameId = ywgj.getWjdz().substring(ywgj.getWjdz().lastIndexOf("\\"), ywgj.getWjdz().lastIndexOf("."));//\d86d2fd0-3e64-4745-9511-ee92d416af36
            }
            //newVideoName = fileid + ".flv";
            File htmlFile = new File(outPutFilePath+newFileNameId+".html");
//            if(!htmlFile.exists()){
            	if(FileType.DOC.equals(ywgj.getWjlx()) || FileType.DOCX.equals(ywgj.getWjlx())){
            		if(!htmlFile.exists()){
            			    Doc2Html.convertTOfile(new File(fileAbsolutePath), outPutFilePath + "/" + newFileNameId+".html");
    						prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}else{
            			prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}
            		
            	}else if(FileType.XLS.equals(ywgj.getWjlx()) || FileType.XLSX.equals(ywgj.getWjlx())){
            		if(!htmlFile.exists()){
            		    Doc2Html.convertTOfile(new File(fileAbsolutePath), outPutFilePath + "/" + newFileNameId+".html");
    					prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}else{
            			prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}
            		
            	}else if(FileType.PDF.equals(ywgj.getWjlx())){
            		/*if(!htmlFile.exists()){
            			String filePath1 = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
                		String destDir = Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH.lastIndexOf("/"));//  /uploadFile/cache
                		DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH, filePath1 + filePath.replaceAll("/", "\\\\"), filePath1 + destDir.replaceAll("/", "\\\\"), newFileNameId+".html");
                		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}else{
            			prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".html");
            		}*/
            	
            	}else if(FileType.TXT.equals(ywgj.getWjlx())){
            		File file = new File(fileAbsolutePath);
            		try {
						InputStream in= new FileInputStream(file);
						 byte[] b = new byte[3];
				         in.read(b);
				         in.close();
				         if (b[0] == -17 && b[1] == -69 && b[2] == -65){
				        	 prefile.setCache(ywgj.getWjdz());
				         }else{
				        	 File txtfile = new File(outPutFilePath+newFileNameId+".txt");
			            		if(!txtfile.exists()){
			            			DataTransfer.txtConvert(fileAbsolutePath, outPutFilePath+newFileNameId+".txt");
			            		}
			            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".txt");
				         }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            		
            	}else if(FileType.isPIC(ywgj.getWjlx())){
            		prefile.setCache(ywgj.getWjdz());
            	}else if(FileType.TIF.equalsIgnoreCase(ywgj.getWjlx())){
            		File jpgfile = new File(outPutFilePath+newFileNameId+".jpg");
            		if(!jpgfile.exists()){
            			FileType.convertTifPic(fileAbsolutePath, outPutFilePath+newFileNameId+".jpg");
            		}
            	
            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".jpg");
            	}else if(FileType.isAudioOrVedio(ywgj.getWjlx())){
            		File flvFile = new File(outPutFilePath+newFileNameId+".flv");
            		if(!flvFile.exists()){
//            			String tagfile = request.getServletContext().getRealPath("/") + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/", "\\") + newFileNameId+".flv";
//                		String srcfile = request.getServletContext().getRealPath("/") + File.separator + ".." + ywgj.getWjdz().replace("/", "\\");
                		String srcfile1 = (this.getFilePath()+ywgj.getWjdz()).replace("/", "\\").substring(1).replace("\\\\", "\\");
                		String tagfile1 = (outPutFilePath+newFileNameId+".flv").replace("/", "\\").substring(1).replace("\\\\", "\\");
                		DataTransfer.processFfmpegOther(srcfile1,tagfile1);
            		}
            		prefile.setType("flv");
            		prefile.setCache(newFileNameId);
            	}else if(FileType.MP3.equalsIgnoreCase(ywgj.getWjlx())){
            		prefile.setType("mp3");
            	}else{
            		prefile.setType("others");
            		prefile.setCache(ywgj.getWjdz());
            	}
            	
            	

        }else {
        	yFile = new File(this.getFilePath()+attach.getFjnr());
            if(!yFile.exists()){
            	throw new ServiceException("文件不存在，或被删除");
            }
            // 绑定附件与目录主键
            prefile = new DtoPreviewFile();
            prefile.setName(attach.getYsjid());
            prefile.setType(attach.getFjhz());
            prefile.setRecord(attach.getFjmc());
            
            filePath = attach.getFjnr();
            fileAbsolutePath = getFilePath()+filePath;
            newFileName = attach.getFjmc().substring(0, attach.getFjmc().lastIndexOf("."))+".html";
            newFileNameId = attach.getFjnr().substring(attach.getFjnr().lastIndexOf("\\"), attach.getFjnr().lastIndexOf("."));
            newVideoName = fileid + ".flv";
            File htmlFile = new File(outPutFilePath+newFileName);
//            if(!htmlFile.exists()){
            	if(FileType.DOC.equals(attach.getFjhz()) || FileType.DOCX.equals(attach.getFjhz())){
            		if(!htmlFile.exists()){
            			try {
    						DataTransfer.convertWord2Html(fileAbsolutePath, outPutFilePath, newFileName);
    						//prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileName);
    					} catch (TransformerException e) {
    						e.printStackTrace();
    					} catch (IOException e) {
    						e.printStackTrace();
    					} catch (ParserConfigurationException e) {
    						e.printStackTrace();
    					}
            		}
            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileName);
            		
            	}else if(FileType.XLS.equals(attach.getFjhz()) || FileType.XLSX.equals(attach.getFjhz())){
            		if(!htmlFile.exists()){
            			try {
    						DataTransfer.convertExceltoHtml(fileAbsolutePath, outPutFilePath, newFileName);
    						
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
            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileName);
            		
            	}else if(FileType.PDF.equals(attach.getFjhz())){
            		/*if(!htmlFile.exists()){
            			String filePath1 = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
                		String destDir = Path.UPLOAD_CACHE_PATH.substring(0,Path.UPLOAD_CACHE_PATH.lastIndexOf("/"));
                		DataTransfer.pdf2html(filePath1 + Path.UPLOAD_PDF2HTML_FILE_PATH, filePath1 + filePath.replaceAll("/", "\\\\"), filePath1 + destDir.replaceAll("/", "\\\\"), newFileName);
                		
            		}
            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileName);*/
            	}else if(FileType.TXT.equals(attach.getFjhz())){
            		File file = new File(fileAbsolutePath);
            		try {
						InputStream in= new FileInputStream(file);
						 byte[] b = new byte[3];
				         in.read(b);
				         in.close();
				         if (b[0] != -17 || b[1] != -69 || b[2] != -65){
				        	 prefile.setCache(attach.getFjnr());
				         }else{
				        	 File txtfile = new File(outPutFilePath+newFileNameId+".txt");
			            		if(!txtfile.exists()){
			            			DataTransfer.txtConvert(fileAbsolutePath, outPutFilePath+newFileNameId+".txt");
			            		}
			            		prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileNameId+".txt");
				         }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		//prefile.setCache(attach.getFjnr());
            	}else if(FileType.isPIC(attach.getFjhz())){
            		prefile.setCache(attach.getFjnr());
            	}else if(FileType.TIF.equalsIgnoreCase(attach.getFjhz())){
            		File jpgfile = new File(outPutFilePath+fileid+".jpg");
            		if(!jpgfile.exists()){
            			FileType.convertTifPic(fileAbsolutePath, outPutFilePath+fileid+".jpg");
            		}
            	
            		prefile.setCache(Path.UPLOAD_CACHE_PATH+fileid+".jpg");
            	}
            	
            	else if(FileType.isAudioOrVedio(ywgj.getWjlx())){
            		File flvFile = new File(outPutFilePath+newVideoName);
            		if(!flvFile.exists()){
//            			String tagfile = request.getServletContext().getRealPath("/") + File.separator + ".." + Path.UPLOAD_CACHE_PATH.replace("/", "\\") + newVideoName;
//                		String srcfile = request.getServletContext().getRealPath("/") + File.separator + ".." + attach.getFjnr().replace("/", "\\");
                		String srcfile1 = (this.getFilePath()+attach.getFjnr()).replace("/", "\\").substring(1).replace("\\\\", "\\");
                		String tagfile1 = (outPutFilePath+newFileNameId+".flv").replace("/", "\\").substring(1).replace("\\\\", "\\");
                		DataTransfer.processFfmpegOther(srcfile1,tagfile1);
            		}
            		prefile.setType("flv");
            		prefile.setCache(fileid);
            		
            	}else if(FileType.MP3.equalsIgnoreCase(ywgj.getWjlx())){
            		prefile.setType("mp3");
            	}else{
            		prefile.setType("others");
            		prefile.setCache(ywgj.getWjdz());
            	}
//            }else{
//            	prefile.setCache(Path.UPLOAD_CACHE_PATH+newFileName);
//            }
        }
        
        
        return prefile;
    }
    
    @RequestMapping("/displayPDF/{fileid}")
    public void displayPDF(HttpServletResponse response,@PathVariable("fileid") String fileid) throws ServiceException{
    	
        try {
        	// 获得原文
            ywgj = uploadPreviewService.getFile(fileid);
            
        	
            File file = new File(this.getFilePath()+ywgj.getWjdz());
            if(!file.exists()){
            	throw new ServiceException("文件不存在，或被删除");
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;fileName="+ywgj.getWjm());
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
