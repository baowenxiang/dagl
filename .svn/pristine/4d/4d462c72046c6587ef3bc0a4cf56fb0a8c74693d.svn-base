package cn.proem.suw.web.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.proem.core.entity.SystemCode;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.workflow.entity.Attachment;

/**
 * @ClassName CommonController
 * @Description 公共控制器
 * @author Pan Jilong
 * @date 2017年2月28日
 */
@Controller
@RequestMapping(value = "/w/common")
public class CommonController extends BaseCtrlModel {
	@Autowired
	CommonService commonService;

	/**
	 * 根据实际路径下载附件
	 * @param filePath
	 * @return 
	 * @return 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/downLoadAttach")
	public void downLoadAttach(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws IOException {
			String realpath=this.getFilePath()+filePath;	
			InputStream fin = null;  
			ServletOutputStream out1 = null;  
	       		try {  
	       			File file = new File(realpath); 
	        	    fin = new FileInputStream(file);              
	        	    response.setCharacterEncoding("utf-8");
	        	    String extName = fileName.substring(fileName.lastIndexOf(".")+1);
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
	        	          response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));  
	        	      } else {
	        	         // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
	        	            response.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  
	        	      }   
	        	        out1 = response.getOutputStream();  
	        	        byte[] buffer2 = new byte[512];  // 缓冲区  
	        	        int bytesToRead = -1;  
	        	        // 通过循环将读入的文件的内容输出到浏览器中  
	        	         while((bytesToRead = fin.read(buffer2)) != -1) {  
	        	               out1.write(buffer2, 0, bytesToRead);  
	        	            }  
	        	  } finally {  
	        		  if(fin != null) fin.close();  
	        	      if(out1 != null) out1.close();  
	        	  }  
	 }
	
	/**
	 * @MethodName batchDownLoad
	 * @Description 附件打包下载
	 * @author Shen Hongyu
	 * @date 2017年3月13日
	 * @param request
	 * @param resp
	 * @param ids
	 * @param processTitle
	 * @throws IOException
	 * @throws ParseException
	 * @throws ServletException
	 */
	@RequestMapping("batchDownLoad")
	public void batchDownLoad(HttpServletRequest request, HttpServletResponse resp,String ids,String processTitle)throws IOException, ParseException,ServletException{
		String[] idArr=ids.split(",");
		List<Attachment> fileList=new ArrayList<>();
		for(int i=0;i<idArr.length;i++){
		   Attachment fileObj = commonService.findById(idArr[i], Attachment.class);
		   fileList.add(fileObj);
		}
		//生成的ZIP文件名  
		Calendar cld=Calendar.getInstance();
		int millisecond=cld.get(Calendar.MILLISECOND);
        String tmpFileName = processTitle+"-"+millisecond+".zip"; 
        //在服务器端创建打包下载的临时文件
    	String strZipPath= this.getFilePath()+Path.UPLOAD_FLOW_FILE_PATH+ tmpFileName ;
    	File file = new File(strZipPath);
        //循环获得需要打包的文件		            
         List<File> files=new ArrayList<File>();
         for(int i=0;i<fileList.size();i++){
        	 String realPath=this.getFilePath()+ fileList.get(i).getPath();
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
        			 ZipEntry entry = new ZipEntry(fileList.get(i).getName());
        			 toClient.putNextEntry(entry);
        			 final int MAX_BYTE = 10 * 1024 *1024;    //最大的流为10M
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
        		 throw new ServletException("文件不存在！");
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
        	            resp.setCharacterEncoding("utf-8");
        	            //获得zip文件的后缀
        	            String extName = tmpFileName.substring(tmpFileName.lastIndexOf(".")+1);
        	            resp.setContentType("application/"+extName);  
        	            
        	            boolean isMSIE = false;
        	            String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
        	            String userAgent = request.getHeader("User-Agent");
        	            for (String signal : IEBrowserSignals) {
        	                if (userAgent.contains(signal))
        	                	isMSIE = true;
        	            }
        	            if (isMSIE) {
        	                // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
        		            resp.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(tmpFileName, "UTF-8"));  
        	            } else {
        	            	// 设置浏览器以下载的方式处理该文件默认名为resume.doc  
        		            resp.addHeader("Content-Disposition", "attachment;filename="+ new String(tmpFileName.getBytes("UTF-8"),"ISO8859-1"));  
        	            }
        	            
        	              
        	            out1 = resp.getOutputStream();  
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
	}

	/**
	 * @MethodName setJump
	 * @Description 设置页面是否跳转
	 * @author Pan Jilong
	 * @date 2017年3月17日
	 * @return
	 */
	@RequestMapping(value ="/setIntercept")
	@ResponseBody
	public String setIntercept(String intercept, HttpServletRequest request) {
		request.getSession().setAttribute("INTERCEPT", intercept);
		return "success";
	}
	/**
	 * @MethodName getJump
	 * @Description 获取页面是否跳转
	 * @author Pan Jilong
	 * @date 2017年3月17日
	 * @param jump
	 * @param request
	 */
	@RequestMapping(value ="/getIntercept")
	@ResponseBody
	public String getIntercept(HttpServletRequest request) {
//		return (String)request.getSession().getAttribute("INTERCEPT");
		return "false";
	}
	
	/**
	 * @MethodName getDictionary
	 * @Description 获取字典值
	 * @author Pan Jilong
	 * @date 2017年3月21日
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getDictionary")
	@ResponseBody
	public ResultModel<String> getDictionary(HttpServletRequest request, String id) {
		ResultModel<String> resultModel = new ResultModel<String>();
		SystemCode systemCode = commonService.findById(id, SystemCode.class);
		if (systemCode ==null) {
			resultModel.setSuccess(false);
			return resultModel;
		}
		resultModel.setData(systemCode.getLabel());
		return resultModel;
	}
	
}
