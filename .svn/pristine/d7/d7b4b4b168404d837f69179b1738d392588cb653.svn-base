package cn.proem.dagl.web.fileStamp.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asprise.util.tiff.TIFFReader;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileStamp.dto.PdfFileStamp;
import cn.proem.dagl.web.fileStamp.dto.PicFileStamp;
import cn.proem.dagl.web.fileStamp.dto.TifFileStamp;
import cn.proem.dagl.web.fileStamp.service.FileStampService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.service.IDService;
import cn.proem.dagl.web.preArchive.service.ZlsjService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.stamp.PDFWriter;
import cn.proem.stamp.PicTifWriter;
import cn.proem.stamp.PicWriter;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.util.ConfigReader;

@Controller
@RequestMapping(value = "/w/fileStamp")
public class FileStampController extends BaseCtrlModel {
	@Autowired
	private FileStampService fileStampService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private ZlsjService zlsjService;
	@Autowired
	private DicManagerService dicManagerService;
	@Autowired
	private IDService idService;
	
	private final int padding = 10;
	
	
	@RequestMapping(value = "/toFileStampView/{tableName}")
	public ModelAndView toFileStampView(HttpServletRequest request,@PathVariable("tableName") String tableName) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/fileStamp/fileStamp.vm");
		modelAndView.addObject("tablename", tableName);	
		return modelAndView;
	}
	
	@RequestMapping(value = "/doFileStamp")
    @ResponseBody
    public ResultModel<String> doFileStamp(HttpServletRequest request){
		ResultModel<String> resultModel = new ResultModel<String>();
		// 前台传入数据
		// 表名
		String tablename = request.getParameter("tablename");
		// 档号章类型
		String dhzlx = request.getParameter("dhzlx");
		// 档案ID   单独数组用【】    getParameterVlaues
		String[] uuids = request.getParameterValues("uuids");
		// 文件ID
		String zlid = request.getParameter("zlid");
		// 盖章位置
		String wz = request.getParameter("wz");
		// 数据处理,根据档号章类型获得配置条件
		List<DictionaryValue> dictionaryValues = dicManagerService.getDicValueList(dhzlx);
		// 获得原文,调用IDService服务，服务getYwgj  传入上文获得的资料ID
		Ywgj ywgj = idService.getYwgj(zlid);
			// 盖章
			// 根据获得的原文，判断文件类型


			String wjlx = ywgj.getWjlx();	//从原文挂接得到文件类型
			String wjdz = ywgj.getWjdz();	//从原文挂接得到文件地址
			try{
			    // 文件为pdf
				if("pdf".equalsIgnoreCase(wjlx)){	
					float horizonPosition = 10;			//水平位置
					float verticalPosition = 10;		//垂直位置
					float width = 0;
					float height = 0;
					float rownums = 0;
					float colnums = 0;
					for(DictionaryValue dic : dictionaryValues){
						if("width".equals(dic.getDvno())){
							width = Integer.valueOf(dic.getDvalue());
						}else if("height".equals(dic.getDvno())){
							height = Integer.valueOf(dic.getDvalue());
						}else if("rownums".equals(dic.getDvno())){
							rownums = Integer.valueOf(dic.getDvalue());
						}else if("colnums".equals(dic.getDvno())){
							colnums = Integer.valueOf(dic.getDvalue());
						}
					}
					
			        PdfReader reader = new PdfReader(ConfigReader.readAppHome() + wjdz);
			        Rectangle mediabox = reader.getPageSize(1);
			        float widths = mediabox.getRight();
			        float heights = mediabox.getTop();
					
					
					//上左
					if("topleft".equals(wz)){
		           		
		           		//上中
		           		}else if("topmiddle".equals(wz)){
		           			horizonPosition = (widths/2) - (width * colnums)/2;
		           		
		           		//上右	
		           		}else if("topright".equals(wz)){
		           			horizonPosition = widths - (colnums * width) - 10;
		           		
		           		//下左
		           		}else if("downleft".equals(wz)){
		           			verticalPosition = heights - (rownums * height) - 10;
		           			
		           		//下中	
		           		}else if("downmiddle".equals(wz)){
		           			horizonPosition = (widths/2) - (width * colnums)/2;
		           			verticalPosition = heights - (rownums * height) - 10;

		           		//下右
		           		}else if("downright".equals(wz)){
		           			horizonPosition = widths - (colnums * width) - 10;
		           			verticalPosition = heights - (rownums * height) - 10;

		           		}
					this.getPdfStamp(dictionaryValues, tablename, uuids[0], ywgj, horizonPosition, verticalPosition, dhzlx);
				// 文件为JPG	
				}else if("jpg".equalsIgnoreCase(wjlx)){	
					int horizonPosition = 10;		//水平位置
					int verticalPosition = 10;		//垂直位置
					int width = 0;
					int height = 0;
					int rownums = 0;
					int colnums = 0;
					for(DictionaryValue dic : dictionaryValues){
						if("width".equals(dic.getDvno())){
							width = Integer.valueOf(dic.getDvalue());
						}else if("height".equals(dic.getDvno())){
							height = Integer.valueOf(dic.getDvalue());
						}else if("rownums".equals(dic.getDvno())){
							rownums = Integer.valueOf(dic.getDvalue());
						}else if("colnums".equals(dic.getDvno())){
							colnums = Integer.valueOf(dic.getDvalue());
						}
					}
					BufferedImage bufferedImage = ImageIO.read(new File(ConfigReader.readAppHome() + wjdz));   
					int widths = bufferedImage.getWidth();   
					int heights = bufferedImage.getHeight(); 
							//上左
			           		if("topleft".equals(wz)){
			           		
			           		//上中
			           		}else if("topmiddle".equals(wz)){
			           			horizonPosition = (widths/2) - (width * colnums)/2;
			           		
			           		//上右	
			           		}else if("topright".equals(wz)){
			           			horizonPosition = widths - (colnums * width) - 10;
			           		
			           		//下左
			           		}else if("downleft".equals(wz)){
			           			verticalPosition = heights - (rownums * height) - 10;
			           			
			           		//下中	
			           		}else if("downmiddle".equals(wz)){
			           			horizonPosition = (widths/2) - (width * colnums)/2;
			           			verticalPosition = heights - (rownums * height) - 10;

			           		//下右
			           		}else if("downright".equals(wz)){
			           			horizonPosition = widths - (colnums * width) - 10;
			           			verticalPosition = heights - (rownums * height) - 10;

			           		}
					this.getPicStamp(dictionaryValues, tablename, uuids[0], ywgj, horizonPosition, verticalPosition, dhzlx);
				// 文件为tif	
				}else if("tif".equalsIgnoreCase(wjlx)){	
					int horizonPosition = 10;		//水平位置
					int verticalPosition = 10;		//垂直位置
					int width = 0;
					int height = 0;
					int rownums = 0;
					int colnums = 0;
					for(DictionaryValue dic : dictionaryValues){
						if("width".equals(dic.getDvno())){
							width = Integer.valueOf(dic.getDvalue());
						}else if("height".equals(dic.getDvno())){
							height = Integer.valueOf(dic.getDvalue());
						}else if("rownums".equals(dic.getDvno())){
							rownums = Integer.valueOf(dic.getDvalue());
						}else if("colnums".equals(dic.getDvno())){
							colnums = Integer.valueOf(dic.getDvalue());
						}
					}
				//	BufferedImage bufferedImage = ImageIO.read(new File(ConfigReader.readAppHome() + wjdz));
					File _file = new File(ConfigReader.readAppHome() + wjdz);
					TIFFReader reader = new TIFFReader(_file);
					RenderedImage image = reader.getPage(0);
//					    ColorModel cm = bufferedImage.getColorModel();
					    int widths = image.getWidth();
					    int heights = image.getHeight();
					    
					
							//上左
			           		if("topleft".equals(wz)){
			           		
			           		//上中
			           		}else if("topmiddle".equals(wz)){
			           			horizonPosition = (widths/2) - (width * colnums)/2;
			           		
			           		//上右	
			           		}else if("topright".equals(wz)){
			           			horizonPosition = widths - (colnums * width) - 10;
			           		
			           		//下左
			           		}else if("downleft".equals(wz)){
			           			verticalPosition = heights - (rownums * height) - 10;
			           			
			           		//下中	
			           		}else if("downmiddle".equals(wz)){
			           			horizonPosition = (widths/2) - (width * colnums)/2;
			           			verticalPosition = heights - (rownums * height) - 10;

			           		//下右
			           		}else if("downright".equals(wz)){
			           			horizonPosition = widths - (colnums * width) - 10;
			           			verticalPosition = heights - (rownums * height) - 10;

			           		}
					this.getTifStamp(dictionaryValues, tablename, uuids[0], ywgj, horizonPosition, verticalPosition, dhzlx);
				}
					
			
			//自定义错误类型
			}catch (ServiceException e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
					e.printStackTrace();
			// 返回错误信息   错误父类
			} catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("文件被使用或者不存在");
			}	           
		return resultModel;        
	}
	
	
	public BaseColor getBaseColor(String color){
		BaseColor baseColor;
		switch (color){
		case "0" : 
			baseColor = BaseColor.WHITE;
			break;
		case "1" : 
			baseColor =  BaseColor.LIGHT_GRAY;
			break;
		case "2" : 
			baseColor =  BaseColor.GRAY;
			break;
		case "3" : 
			baseColor =  BaseColor.DARK_GRAY;
			break;
		case "4" : 
			baseColor =  BaseColor.RED;
			break;
		case "5" : 
			baseColor =  BaseColor.PINK;
			break;
		case "6" : 
			baseColor =  BaseColor.ORANGE;
			break;
		case "7" : 
			baseColor =  BaseColor.YELLOW;
			break;
		case "8" : 
			baseColor = BaseColor.GREEN;
			break;
		case "9" : 
			baseColor = BaseColor.MAGENTA;
			break;
		case "10" : 
			baseColor = BaseColor.CYAN;
			break;
		case "11" : 
			baseColor = BaseColor.BLUE;
			break;
		default : 
			baseColor = BaseColor.BLACK;
			break;
		}
		
		return baseColor;
	}
	
	
	public Color getColor(String color){
		Color baseColor;
		switch (color){
		case "0" : 
			baseColor = Color.WHITE;
			break;
		case "1" : 
			baseColor =  Color.LIGHT_GRAY;
			break;
		case "2" : 
			baseColor =  Color.GRAY;
			break;
		case "3" : 
			baseColor =  Color.DARK_GRAY;
			break;
		case "4" : 
			baseColor =  Color.RED;
			break;
		case "5" : 
			baseColor =  Color.PINK;
			break;
		case "6" : 
			baseColor =  Color.ORANGE;
			break;
		case "7" : 
			baseColor =  Color.YELLOW;
			break;
		case "8" : 
			baseColor = Color.GREEN;
			break;
		case "9" : 
			baseColor = Color.MAGENTA;
			break;
		case "10" : 
			baseColor = Color.CYAN;
			break;
		case "11" : 
			baseColor = Color.BLUE;
			break;
		default : 
			baseColor = Color.BLACK;
			break;
		}
		
		return baseColor;
	}
	
	
  private String getValue(Object object){
	  String value1 = (object != null?object.toString():"");
	  return value1;
  }

  private String getZwm(String bgqx){
	  //通过字典得到BGQX的列表
	  List<DictionaryValue> dics = dicManagerService.getDicValueList("bgqx");
	  //循环列表  ，比较YWM是否对应，返回ZWM
	  for(DictionaryValue dic : dics){
		  //获得字典YWM
		  String ywm = dic.getDvno();
		  //获得字典ZWM
		  String zwm = dic.getDvalue();
		  if(ywm.equals(bgqx)){
			  return zwm;
		  }
	  }
	  return "";
  }
  
  private void getPdfStamp(List<DictionaryValue> dictionaryValues,String tablename,String uuid,Ywgj ywgj,float horizonPosition,float verticalPosition,String dhzlx) throws DocumentException, IOException, ServiceException{
		  PdfFileStamp pdfFileStamp = new PdfFileStamp();
		  
	  	  pdfFileStamp.setInputPath(this.getFilePath() + ywgj.getWjdz());//设置输入地址
		  String stampPath = ywgj.getWjdz().substring(0,ywgj.getWjdz().lastIndexOf(".")-36);
		
		  String realStampPath = stampPath+UUID.randomUUID().toString()+".pdf";
		  pdfFileStamp.setOutputPath(this.getFilePath()+realStampPath);//设置输出地址
		  pdfFileStamp.setHorizonPosition(horizonPosition);
		  pdfFileStamp.setVerticalPosition(verticalPosition);
		  for(DictionaryValue dic : dictionaryValues){
	        	if("x_position".equals(dic.getDvno())){
//	        		pdfFileStamp.setHorizonPosition(Float.valueOf(dic.getDvalue()));
	        	}else if("y_position".equals(dic.getDvno())){
//	        		pdfFileStamp.setVerticalPosition(Float.valueOf(dic.getDvalue()));
	        	}else if("width".equals(dic.getDvno())){
	        		pdfFileStamp.setWidth(Float.valueOf(dic.getDvalue()));
	        	}else if("height".equals(dic.getDvno())){
	        		pdfFileStamp.setHeight(Float.valueOf(dic.getDvalue()));
	        	}else if("rownums".equals(dic.getDvno())){
	        		pdfFileStamp.setRownums(Float.valueOf(dic.getDvalue()));
	        	}else if("colnums".equals(dic.getDvno())){
	        		pdfFileStamp.setColnums(Float.valueOf(dic.getDvalue()));
	        	}else if("lineSize".equals(dic.getDvno())){
	        		pdfFileStamp.setLineSize(Float.valueOf(dic.getDvalue()));
	        	}else if("fontSize".equals(dic.getDvno())){
	        		pdfFileStamp.setFontSize(Float.valueOf(dic.getDvalue()));
	        	}else if("lineColor".equals(dic.getDvno())){
	        		String lineColorDic = dic.getDvalue();
	        		pdfFileStamp.setLineColor(getBaseColor(lineColorDic));
	        	}else if("fontColor".equals(dic.getDvno())){
	        		String fontColorDic = dic.getDvalue();
	        		pdfFileStamp.setFontColor(getBaseColor(fontColorDic));
	        	}
	        }
		//获得对应单个档案
    	BaseEntityInf entity = customArchiveService.getEntity(tablename, uuid);
    	 //判断档案类型
        if("dhz_ywyj".equalsIgnoreCase(dhzlx)){
        	String[][] fonts = { 
        			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh"))}, 
        			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx")), getValue(entity.get("ztsl"))}
        	};
        	//转换  YWM=>ZWM
        	fonts[1][1] = getZwm(fonts[1][1]);
        	pdfFileStamp.setFonts(fonts);//设置内容
        }else if("dhz_jj".equalsIgnoreCase(dhzlx)){
        	String[][] fonts = { 
        			{"档号","序号"}, 
        			{ getValue(entity.get("dh")), getValue(entity.get("xh"))}
        	};
        	pdfFileStamp.setFonts(fonts);//设置内容
        }else if("dhz_bgz".equalsIgnoreCase(dhzlx)){
        	String[][] fonts = { 
        			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh")), " "}, 
        			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx"))," ", getValue(entity.get("ztsl"))}
        	};
        	fonts[1][1] = getZwm(fonts[1][1]);
        	pdfFileStamp.setFonts(fonts);//设置内容
        	}
        
        PDFWriter.drawTable(pdfFileStamp.getInputPath(),
			        		pdfFileStamp.getOutputPath(),
			        		pdfFileStamp.getHorizonPosition(),
			        		pdfFileStamp.getVerticalPosition(),
			        		pdfFileStamp.getWidth(),
			        		pdfFileStamp.getHeight(),
			        		pdfFileStamp.getRownums(),
			        		pdfFileStamp.getColnums(), 
			        		pdfFileStamp.getFonts(),
			        		pdfFileStamp.getLineSize(),
			        		pdfFileStamp.getFontSize(),
			        		pdfFileStamp.getLineColor(),
			        		pdfFileStamp.getFontColor()
			        		);

        File realStamp = new File(this.getFilePath()+realStampPath);
        Long size = realStamp.length();

        fileStampService.doFileStamp(size, ywgj.getId(), realStampPath);
  }
  
  private void getPicStamp(List<DictionaryValue> dictionaryValues,String tablename,String uuid,Ywgj ywgj,int horizonPosition,int verticalPosition,String dhzlx) throws DocumentException, IOException, ServiceException{
	  PicFileStamp picFileStamp = new PicFileStamp();
	  
	  picFileStamp.setInputPath(this.getFilePath() + ywgj.getWjdz());//设置输入地址
	  String stampPath = ywgj.getWjdz().substring(0,ywgj.getWjdz().lastIndexOf(".")-36);
	
	  String realStampPath = stampPath+UUID.randomUUID().toString()+".jpg";
	  picFileStamp.setOutputPath(this.getFilePath()+realStampPath);//设置输出地址
	  picFileStamp.setHorizonPosition(horizonPosition);
	  picFileStamp.setVerticalPosition(verticalPosition);
	  for(DictionaryValue dic : dictionaryValues){
        	if("x_position".equals(dic.getDvno())){
        		// picFileStamp.setHorizonPosition(Integer.valueOf(dic.getDvalue()));
        	}else if("y_position".equals(dic.getDvno())){
        		// picFileStamp.setVerticalPosition(Integer.valueOf(dic.getDvalue()));
        	}else if("width".equals(dic.getDvno())){
        		picFileStamp.setWidth(Integer.valueOf(dic.getDvalue()));
        	}else if("height".equals(dic.getDvno())){
        		picFileStamp.setHeight(Integer.valueOf(dic.getDvalue()));
        	}else if("rownums".equals(dic.getDvno())){
        		picFileStamp.setRownums(Integer.valueOf(dic.getDvalue()));
        	}else if("colnums".equals(dic.getDvno())){
        		picFileStamp.setColnums(Integer.valueOf(dic.getDvalue()));
        	}else if("lineSize".equals(dic.getDvno())){
        		picFileStamp.setLineSize(Integer.valueOf(dic.getDvalue()));
        	}else if("fontSize".equals(dic.getDvno())){
        		picFileStamp.setFontSize(Integer.valueOf(dic.getDvalue()));
        	}else if("lineColor".equals(dic.getDvno())){
        		String lineColorDic = dic.getDvalue();
        		picFileStamp.setLineColor(getColor(lineColorDic));
        	}else if("fontColor".equals(dic.getDvno())){
        		String fontColorDic = dic.getDvalue();
        		picFileStamp.setFontColor(getColor(fontColorDic));
        	}
        }
	//获得对应单个档案
	BaseEntityInf entity = customArchiveService.getEntity(tablename, uuid);
	List<DictionaryValue> dics = dicManagerService.getDicValueList("bgqx");
	 //判断档案类型

    if("dhz_ywyj".equalsIgnoreCase(dhzlx)){
    	
    	String[][] fonts = { 
    			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh"))}, 
    			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx")), getValue(entity.get("ztsl"))}
    	};
    	fonts[1][1] = getZwm(fonts[1][1]);
    	picFileStamp.setFonts(fonts);//设置内容
    }else if("dhz_jj".equalsIgnoreCase(dhzlx)){
    	String[][] fonts = { 
    			{"档号","序号"}, 
    			{ getValue(entity.get("dh")), getValue(entity.get("xh"))}
    	};
    	picFileStamp.setFonts(fonts);//设置内容
    }else if("dhz_bgz".equalsIgnoreCase(dhzlx)){
    	String[][] fonts = { 
    			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh")), " "}, 
    			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx")), " ", getValue(entity.get("ztsl"))}
    	};
    	fonts[1][1] = getZwm(fonts[1][1]);
    	picFileStamp.setFonts(fonts);//设置内容
    	}

    PicWriter.drawTable(picFileStamp.getInputPath(),
    			picFileStamp.getOutputPath(),
    			picFileStamp.getHorizonPosition(),
    			picFileStamp.getVerticalPosition(),
    			picFileStamp.getWidth(),
    			picFileStamp.getHeight(),
    			picFileStamp.getRownums(),
    			picFileStamp.getColnums(), 
    			picFileStamp.getFonts(),
    			picFileStamp.getLineSize(),
    			picFileStamp.getFontSize(),
    			picFileStamp.getLineColor(),
    			picFileStamp.getFontColor()
		        		);

    File realStamp = new File(this.getFilePath()+realStampPath);
    Long size = realStamp.length();

    fileStampService.doFileStamp(size, ywgj.getId(), realStampPath);
}
  
  private void getTifStamp(List<DictionaryValue> dictionaryValues,String tablename,String uuid,Ywgj ywgj,int horizonPosition,int verticalPosition,String dhzlx) throws DocumentException, IOException, ServiceException{
	  TifFileStamp tifFileStamp = new TifFileStamp();
	  
	  tifFileStamp.setInputPath(this.getFilePath() + ywgj.getWjdz());//设置输入地址
	  String stampPath = ywgj.getWjdz().substring(0,ywgj.getWjdz().lastIndexOf(".")-36);
	
	  String realStampPath = stampPath+UUID.randomUUID().toString()+".tif";
	  tifFileStamp.setOutputPath(this.getFilePath()+realStampPath);//设置输出地址
	  tifFileStamp.setHorizonPosition(horizonPosition);
	  tifFileStamp.setVerticalPosition(verticalPosition);
	  for(DictionaryValue dic : dictionaryValues){
        	if("x_position".equals(dic.getDvno())){
        		// tifFileStamp.setHorizonPosition(Integer.valueOf(dic.getDvalue()));
        	}else if("y_position".equals(dic.getDvno())){
        		// tifFileStamp.setVerticalPosition(Integer.valueOf(dic.getDvalue()));
        	}else if("width".equals(dic.getDvno())){
        		tifFileStamp.setWidth(Integer.valueOf(dic.getDvalue()));
        	}else if("height".equals(dic.getDvno())){
        		tifFileStamp.setHeight(Integer.valueOf(dic.getDvalue()));
        	}else if("rownums".equals(dic.getDvno())){
        		tifFileStamp.setRownums(Integer.valueOf(dic.getDvalue()));
        	}else if("colnums".equals(dic.getDvno())){
        		tifFileStamp.setColnums(Integer.valueOf(dic.getDvalue()));
        	}else if("lineSize".equals(dic.getDvno())){
        		tifFileStamp.setLineSize(Integer.valueOf(dic.getDvalue()));
        	}else if("fontSize".equals(dic.getDvno())){
        		tifFileStamp.setFontSize(Integer.valueOf(dic.getDvalue()));
        	}else if("lineColor".equals(dic.getDvno())){
        		String lineColorDic = dic.getDvalue();
        		tifFileStamp.setLineColor(getColor(lineColorDic));
        	}else if("fontColor".equals(dic.getDvno())){
        		String fontColorDic = dic.getDvalue();
        		tifFileStamp.setFontColor(getColor(fontColorDic));
        	}
        }
	//获得对应单个档案
	BaseEntityInf entity = customArchiveService.getEntity(tablename, uuid);
	 //判断档案类型
    if("dhz_ywyj".equalsIgnoreCase(dhzlx)){
    	String[][] fonts = { 
    			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh"))}, 
    			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx")), getValue(entity.get("ztsl"))}
    	};
    	fonts[1][1] = getZwm(fonts[1][1]);
    	tifFileStamp.setFonts(fonts);//设置内容
    }else if("dhz_jj".equalsIgnoreCase(dhzlx)){
    	String[][] fonts = { 
    			{"档号","序号"}, 
    			{ getValue(entity.get("dh")), getValue(entity.get("xh"))}
    	};
    	tifFileStamp.setFonts(fonts);//设置内容
    }else if("dhz_bgz".equalsIgnoreCase(dhzlx)){
    	String[][] fonts = { 
    			{ getValue(entity.get("qzh")), getValue(entity.get("gdnd")), getValue(entity.get("jh")), " "}, 
    			{ getValue(entity.get("jgwtdh")), getValue(entity.get("bgqx")), " ", getValue(entity.get("ztsl"))}
    	};
    	fonts[1][1] = getZwm(fonts[1][1]);
    	tifFileStamp.setFonts(fonts);//设置内容
    	}

    PicTifWriter.drawTable(tifFileStamp.getInputPath(),
    		tifFileStamp.getOutputPath(),
    		tifFileStamp.getHorizonPosition(),
    		tifFileStamp.getVerticalPosition(),
    		tifFileStamp.getWidth(),
    		tifFileStamp.getHeight(),
    		tifFileStamp.getRownums(),
    		tifFileStamp.getColnums(), 
    		tifFileStamp.getFonts(),
    		tifFileStamp.getLineSize(),
    		tifFileStamp.getFontSize(),
    		tifFileStamp.getLineColor(),
    		tifFileStamp.getFontColor()
		        		);

    File realStamp = new File(this.getFilePath()+realStampPath);
    Long size = realStamp.length();

    fileStampService.doFileStamp(size, ywgj.getId(), realStampPath);
}
  
}


