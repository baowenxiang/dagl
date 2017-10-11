package cn.proem.suw.web.common.constant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

/**
 * 系统中附件与上传文件中存在的文件类型
 * @ClassName FileType
 * @Description 文件类型
 * @author Tcc
 * @date 2017/5/9
 */
public final class FileType {
    public static final String M4V = "m4v";
    public static final String M4A = "m4a";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String PDF = "pdf";
    public static final String TXT = "txt";
    public static final String TIF = "tif";
    public static final String MP3 = "mp3";
    
    /**
     * 判断是否为图片
     * @param picType
     * @return
     */
    public static boolean isPIC(String picType){
    	if("jpg".equalsIgnoreCase(picType) || "png".equalsIgnoreCase(picType) || "bmp".equalsIgnoreCase(picType)
    			|| "gif".equalsIgnoreCase(picType)){
    		return true;
    	}
    	return false;
    }
    /**
     * 判断是否为音，视屏
     * @param picType
     * @return
     */
    public static boolean isAudioOrVedio(String fileType){
    	if("asx".equalsIgnoreCase(fileType) || "asf".equalsIgnoreCase(fileType) || "mpg".equalsIgnoreCase(fileType)
    			|| "mp4".equalsIgnoreCase(fileType)|| "mov".equalsIgnoreCase(fileType)|| "wmv".equalsIgnoreCase(fileType)
    			|| "3gp".equalsIgnoreCase(fileType)|| "avi".equalsIgnoreCase(fileType)|| "flv".equalsIgnoreCase(fileType)
    			|| "wav".equalsIgnoreCase(fileType)|| "wma".equalsIgnoreCase(fileType)|| "ogg".equalsIgnoreCase(fileType)
    			|| "cd".equalsIgnoreCase(fileType)|| "real".equalsIgnoreCase(fileType)
    			|| "vqf".equalsIgnoreCase(fileType) ||"rm".equalsIgnoreCase(fileType)||"m4a".equalsIgnoreCase(fileType)){
    		return true;
    	}
    	return false;
    }
    
    /**
     * tif格式图片转jpg
     * @param filePath
     * @param outFilePath
     */
    public static void convertTifPic(String filePath,String outFilePath){
    	
    	 /* tif转换到jpg格式 */  
//        String input2 = "d:/img/a.tif";  
//        String output2 = "d:/img/a.jpg";  
        RenderedOp src2 = JAI.create("fileload", filePath);  
        OutputStream os2;
		try {
			os2 = new FileOutputStream(outFilePath);
			JPEGEncodeParam param2 = new JPEGEncodeParam();  
	        //指定格式类型，jpg 属于 JPEG 类型  
	        ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2);  
	        enc2.encode(src2);  
	        os2.close();
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
        
    }
}
