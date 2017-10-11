package cn.proem.dagl.web.filePreview.controller;

import java.io.File;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.dagl.web.filePreview.service.FilePreviewService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.docu.entity.DocuAttachment;
/**
 * @ClassName FilePreviewController
 * @Description 文件预览控制层 
 * @author chenxiaofen
 * @date 2017年5月2日
 */
@Controller
@RequestMapping(value = "/w/filePreview")
public class FilePreviewController extends BaseCtrlModel {
	@Autowired
	private FilePreviewService filePreviewService;
	/**
	 * @MethodName init
	 * @Description 进入表格展示页面
	 * @author chenxiaofen
	 * @date 2017年5月3日
	 * @param request
	 * @param tableName
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/init/{tableName}")
	public ModelAndView init(HttpServletRequest request,@PathVariable("tableName") String tableName) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/file/filepreview.vm");
		modelAndView.addObject("tableName", tableName);	
		return modelAndView;
	}
	/**
	 * @MethodName getFile
	 * @Description 获取预览文件的地址
	 * @author chenxiaofen
	 * @date 2017年5月2日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws ServerException 
	 */
	@RequestMapping(value = "/getFile/{id}")
	@ResponseBody
	@LogService(description = "获取预览文件的地址")
	public DocuAttachment getFile(HttpServletRequest request,@PathVariable("id") String id) throws ServiceException {
		return filePreviewService.getFile(id);
	}
	/**
	 * @MethodName 
	 * @Description 
	 * @author chenxiaofen
	 * @date 2017年5月2日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getPdf/{id}")
	@ResponseBody
	@LogService(description = "PDF文件预览")
	public String getPdf(HttpServletRequest request,@PathVariable("id") String id) throws ServiceException {
		String result = "";
		String pdfPath = filePreviewService.getFile(id).getFjnr();
		String destDir = pdfPath.substring(0,pdfPath.lastIndexOf("/"));
		String filepath = this.getFilePath() + pdfPath.split("[.]")[0] + ".html";
		File htmlFile = new File(filepath);
		//判断文件是否存在，不存在则生成对应html文件
		if(!htmlFile.exists()){
			String filePath = (this.getFilePath().substring(1,this.getFilePath().length()-1)).replaceAll("/", "\\\\");
			pdf2html(filePath + Path.UPLOAD_PDF2HTML_FILE_PATH,filePath + pdfPath.replaceAll("/", "\\\\"),filePath + destDir.replaceAll("/", "\\\\"),filepath.substring(filepath.lastIndexOf("/")+1));
			result = pdfPath.substring(0,pdfPath.lastIndexOf("/")+1);
			result += filepath.substring(filepath.lastIndexOf("/"));
		}else {
			//文件存在，直接返回对应html路径
			result = pdfPath.split("[.]")[0] + ".html";
		}
		
		return result;
	}
	
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

}
