package cn.proem.suw.web.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.w3c.dom.Document;

import cn.proem.dagl.web.filePreview.controller.StreamGobbler;

public class DataTransfer {

	public static String FILE_DOC = "doc";
	public static String FILE_DOCX = "docx";
	public static String FILE_XLS = "xls";
	public static String FILE_XLSX = "xlsx";
	public static String FILE_TXT = "txt";
	public static String FILE_PDF = "pdf";
	public final static String ffmpegPath = ConfigReader.readFFMPEGPATH();
	/**
	 * 读取文件（建立索引用）
	 * 
	 * @param file
	 * @return
	 */
	public static String file2String(File file) {
		String content = "";
		// 获取文件后缀名
		String type = file.getName().substring(
				file.getName().lastIndexOf(".") + 1);
		if (FILE_DOC.equals(type)) {
			content += doc2String(file);
		} else if (FILE_XLS.equals(type)) {
			content += xls2String(file);
		} else if (FILE_TXT.equals(type)) {
			content += txt2String(file);
		} else if (FILE_PDF.equals(type)) {
			content += pdf2String(file);
		} else if (FILE_DOCX.equals(type)){
			content += docx2String(file);
		}

		return content;
	}
	
	/**
	 * 文件转为Html预览
	 */
	public static void file2Html(String filePath, String outPutFilePath,String newFileName){
		// 获取文件后缀名
		String type = filePath.substring(filePath.lastIndexOf(".")+1);
		if(FILE_DOC.equals(type) || FILE_DOCX.equals(type)){
			try {
				convertWord2Html(filePath,outPutFilePath,newFileName);
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
		}else if(FILE_XLS.equals(type) || FILE_XLSX.equals(type)){
			try {
				convertExceltoHtml(filePath,outPutFilePath,newFileName);
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
			
		}else if(FILE_PDF.equals(type)){
			
		}
		
		
		
		//return content;
		
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result = result + "\n" + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取doc文件内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String doc2String(File file) {
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			Range rang = doc.getRange();
			result += rang.text();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 读取docx文件
	 * @param file
	 * @return
	 */
	public static String docx2String(File file){
		String result = "";
		try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            result = extractor.getText();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}
	/**
	 * 读取xls文件内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String xls2String(File file) {
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			jxl.Workbook rwb = Workbook.getWorkbook(fis);
			Sheet[] sheet = rwb.getSheets();
			for (int i = 0; i < sheet.length; i++) {
				Sheet rs = rwb.getSheet(i);
				for (int j = 0; j < rs.getRows(); j++) {
					Cell[] cells = rs.getRow(j);
					for (int k = 0; k < cells.length; k++)
						sb.append(cells[k].getContents());
				}
			}
			fis.close();
			result += sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 读取xlsx文件内容
	 * @param file
	 * @return
	 */
	public static String xlsx2String(File file){
		String result="";
		try {
			FileInputStream fis = new FileInputStream(file);
			 XSSFWorkbook xwb = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取pdf内容
	 * 
	 * @param file
	 *            读取的文件对象
	 * @return 读取的文件内容
	 */
	public static String pdf2String(File file) {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(file);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * txt文件格式转化（utf-8）
	 * @param filePath
	 * @param outFilePath
	 */
	public static void txtConvert(String filePath,String outFilePath){
		
		  FileInputStream fis;
		  InputStreamReader isr;
		  try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			
			BufferedReader br = new BufferedReader(isr);
			 String fileContent = "";
			  String line = null;   
			
				while ((line = br.readLine()) != null) {   
					  fileContent += line;   
					  fileContent += "\r\n"; // 补上换行符   
				  }
			br.close();
			  FileOutputStream fos = new FileOutputStream(outFilePath);   
		      OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		      osw.write(fileContent);   
		      osw.flush();
		      osw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally{
			
		}

	}
	/**
	 * 
	 * @param content
	 * @param path
	 */
	public static void writeFile(String content, String path) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			if (!file.exists()) {

			}
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			bw.write(content);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie) {
			}
		}
	}

	/**
	 * 将word转换成html 支持 .doc and .docx
	 * 
	 * @param filePath
	 *            word文件名
	 * @param outPutFilePath
	 *            html存储路径
	 * @param newFileName
	 *            html名
	 * @throws TransformerException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void convertWord2Html(String filePath, String outPutFilePath,
			String newFileName) throws TransformerException, IOException,
			ParserConfigurationException {
		String substring = filePath.substring(filePath.lastIndexOf(".") + 1);
		ByteArrayOutputStream out = new ByteArrayOutputStream();	
		/**
		 * word2007和word2003的构建方式不同， 前者的构建方式是xml，后者的构建方式是dom树。
		 * 文件的后缀也不同，前者后缀为.docx，后者后缀为.doc 相应的，apache.poi提供了不同的实现类。
		 */
		if ("docx".equals(substring)) {
			// writeFile(new
			// String("<html><head>  <meta http-equiv=\"content-type\" content=\"text/html\" charset=\"utf-8\"/></head>对不起，.docx格式的word文档，暂时不能生成预览</html>".getBytes("utf-8")),
			// outPutFilePath+newFileName);

			// step 1 : load DOCX into XWPFDocument
			InputStream inputStream = new FileInputStream(new File(filePath));
			XWPFDocument document = new XWPFDocument(inputStream);

			// step 2 : prepare XHTML options
			final String imageUrl = "";

			XHTMLOptions options = XHTMLOptions.create();
			options.setExtractor(new FileImageExtractor(new File(outPutFilePath
					+ imageUrl)));
			options.setIgnoreStylesIfUnused(false);
			options.setFragment(true);
			options.URIResolver(new IURIResolver() {
				@Override 
				public String resolve(String uri) {
					return imageUrl + uri;
				}
			});

			// step 3 : convert XWPFDocument to XHTML
			XHTMLConverter.getInstance().convert(document, out, options);
		} else {
			HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(
					filePath));// WordToHtmlUtils.loadDoc(new
								// FileInputStream(inputFile));
			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
							.newDocument());
			wordToHtmlConverter.setPicturesManager(new PicturesManager() {
				@Override
				public String savePicture(byte[] content,
						PictureType pictureType, String suggestedName,
						float widthInches, float heightInches) {
					return suggestedName;
				}
			});
			wordToHtmlConverter.processDocument(wordDocument);
			// save pictures
			List pics = wordDocument.getPicturesTable().getAllPictures();
			if (pics != null) {
				for (int i = 0; i < pics.size(); i++) {
					Picture pic = (Picture) pics.get(i);
					System.out.println();
					try {
						pic.writeImageContent(new FileOutputStream(
								outPutFilePath + pic.suggestFullFileName()));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			Document htmlDocument = wordToHtmlConverter.getDocument();
			DOMSource domSource = new DOMSource(htmlDocument);
			StreamResult streamResult = new StreamResult(out);

			TransformerFactory tf = TransformerFactory.newInstance(); // 这个应该是转换成xml的
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
		}

		out.close();
		writeFile(new String(out.toByteArray()), outPutFilePath + newFileName);
	}
	
	/**
	 * 将excel转成html
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws InvalidFormatException
	 */
	public static void convertExceltoHtml(String filePath,String outPutFilePath,
			String newFileName) throws IOException,
			ParserConfigurationException, TransformerException,
			InvalidFormatException {
		HSSFWorkbook workBook = null;
		String content = null;
		StringWriter writer = null;
		File excelFile = new File(filePath);
		InputStream is = new FileInputStream(excelFile);
		;
		// 判断Excel文件是2003版还是2007版
		String suffix = filePath.substring(filePath.lastIndexOf("."));
		if (suffix.equals(".xlsx")) {
			// 将07版转化为03版
			Xssf2Hssf xlsx2xls = new Xssf2Hssf();
			XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(is);
			workBook = new HSSFWorkbook();
			xlsx2xls.transformXSSF(xSSFWorkbook, workBook);

		} else {
			workBook = new HSSFWorkbook(is);
		}
		//workBook = new HSSFWorkbook(is);
		try {
			ExcelToHtmlConverter converter = new ExcelToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
							.newDocument());
			converter.setOutputColumnHeaders(false);// 不显示列的表头
			converter.setOutputRowNumbers(false);// 不显示行的表头
			converter.processWorkbook(workBook);

			writer = new StringWriter();
			Transformer serializer = TransformerFactory.newInstance()
					.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(new DOMSource(converter.getDocument()),
					new StreamResult(writer));

			content = writer.toString();
			writer.close();
			writeFile(content, outPutFilePath + newFileName);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * pdf转成html
	 * @param exeFilePath
	 * @param pdfFile
	 * @param destDir
	 * @param htmlFileName
	 * @return
	 */
	public static boolean pdf2html(String exeFilePath, String pdfFile,
            String destDir, String htmlFileName) {
        if (!(exeFilePath != null && !"".equals(exeFilePath) && pdfFile != null
                && !"".equals(pdfFile) && htmlFileName != null && !""
                    .equals(htmlFileName))) {
            System.out.println("传递的参数有误！");
            return false;
        }
        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        command.append(exeFilePath).append(" ");
        if (destDir != null && !"".equals(destDir.trim()))// 生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \""))
                    .append(" ");
        command.append("--optimize-text 1 ");// 尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--zoom 1.4 ");
        command.append("--process-outline 0 ");// html中显示链接：0——false，1——true
        command.append("--font-format woff ");// 嵌入html中的字体后缀(default ttf)
                                                // ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");// 需要替换文件路径中的空格
        if (htmlFileName != null && !"".equals(htmlFileName.trim())) {
            command.append(htmlFileName);
            if (htmlFileName.indexOf(".html") == -1)
                command.append(".html");
        }
        try {
            System.out.println("Command：" + command.toString());
            Process p = rt.exec(command.toString());
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(),
                    "ERROR");
            // 开启屏幕标准错误流
            errorGobbler.start();
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(),
                    "STDOUT");
            // 开启屏幕标准输出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if (w == 0 && v == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	
//	public  static void main(String[] args){
		
//		try {
//			convert2Html("D://proem//apache-tomcat-8.0.14//webapps//uploadFile//ywgj//20170421//word2html.docx", 
//					"D://proem//apache-tomcat-8.0.14//webapps//uploadFile//ywgj//20170421//","html1.html");
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			convertExceltoHtml("D://proem//apache-tomcat-8.0.14//webapps//uploadFile//ywgj//20170421//excel2html.xlsx",
//					"D://proem//apache-tomcat-8.0.14//webapps//uploadFile//ywgj//20170421//cache//","html2.html");
//		} catch (InvalidFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	

	
	public static void main(String[] argus){
		
		String srcVideoPath = "D://proem//apache-tomcat-8.0.14//webapps//uploadFile//ywgj//20170513\\a0533fee-eb77-4643-b83b-a92e83c77a3e.mp4";
		
		String tarVideoPath = "D://videotest//a0533fee-eb77-4643-b83b-a92e83c77a3e.flv";
		
		processFfmpegOther(srcVideoPath,tarVideoPath);
		
	}
	
    /** 
     * ffmpeg将其他格式转换成FLV格式文件（未指定其他任何参数） 
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）   
     * @param srcVideoPath 视频文件(原) 
     * @param tarVideoPath 视频文件(新) 
     * @return 
     */  
    public static boolean processFfmpegOther(String srcVideoPath,String tarVideoPath) {    
        
  
        List<String> commend = new java.util.ArrayList<String>();    
           
//        String type =tarVideoPath.substring(tarVideoPath.lastIndexOf(".")+1, tarVideoPath.length());  
          
        commend.add(ffmpegPath);  
          
        commend.add( "-y");    
          
        commend.add( "-i");   
          
        commend.add(srcVideoPath);    
          
//         if(type.toUpperCase().equals("MP4")){  
//           commend.add( " -f h264 ");    
//         }else{  
//             
//         }  
           
         commend.add(tarVideoPath);  
          
        try {    
             ProcessBuilder builder = new ProcessBuilder();   
             builder.command(commend);  
             Process process = builder.start();   
             doWaitFor(process);    
             process.destroy();
             
             if (!checkfile(tarVideoPath)) {    
//                 logger.info(tarVideoPath + " is not exit! processFfmpegOther 转换不成功 !");
            	 System.out.println(tarVideoPath + " is not exit! processFfmpegOther 转换不成功 !");
                 return false;    
             } 
            System.out.println("success");
            return true;    
        } catch (Exception e) {    
//            logger.error("【" + srcVideoPath + "】processFfmpegOther 转换不成功 !"); 
        	 System.out.println("【" + srcVideoPath + "】processFfmpegOther 转换不成功 !");
            return false;    
        }    
    }
    
    
    /**
     * 检查文件是否存在
     * @param filePath
     * @return
     */
    public static boolean checkfile(String filePath){
    	
    	File file = new File(filePath);
    	if(file.exists()){
    		return true;
    		
    	}else{
    		return false;
    		
    	}
    }
    
    /** 
     * 等待进程处理 
     * @param p 
     * @return 
     */  
	 @SuppressWarnings("unused")  
	 public static int doWaitFor(Process p) {    
	        InputStream in = null;    
	        InputStream err = null;    
	        int exitValue = -1; // returned to caller when p is finished    
	        try {    
	            in = p.getInputStream();    
	            err = p.getErrorStream();    
	            boolean finished = false; // Set to true when p is finished    
	            while (!finished) {    
	                try {    
	                    while (in.available() > 0) {    
	                        Character c = new Character((char) in.read());    
	                    }    
	                    while (err.available() > 0) {    
	                        Character c = new Character((char) err.read());    
	                    }    
	                    exitValue = p.exitValue();    
	                    finished = true;    
	                } catch (IllegalThreadStateException e) {    
	                    Thread.currentThread();  
	                 Thread.sleep(500);    
	                }    
	            }    
	   
	        } catch (Exception e) {    
//	         logger.error("doWaitFor();: unexpected exception - "   
//	                    + e.getMessage());  
	        } finally {    
	            try {    
	                if (in != null) {    
	                    in.close();    
	                }    
	            } catch (IOException e) {    
//	                logger.error("等待进程处理错误");  
	            }    
	            if (err != null) {    
	                try {    
	                    err.close();    
	                } catch (IOException e) {    
//	                 logger.error("等待进程处理错误");    
	                }    
	            }    
	        }    
	        return exitValue;    
	    }    
}
