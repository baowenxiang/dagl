package cn.proem.dagl.web.systemSetting.entity;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import cn.proem.suw.web.common.exception.ServiceException;
/**
 * @ClassName ZipUtil
 * @Description 文件夹备份工具类
 * @author chenxiaofen
 * @date 2017年5月16日
 */
public class ZipUtil {
	private File zipFile;
	
    public ZipUtil(String finalFile) {    
        zipFile = new File(finalFile);    
    }    
        
    /**
	 * @MethodName compressExe
	 * @Description 压缩操作
	 * @author chenxiaofen
	 * @date 2017年5月15日
	 * @param srcPathName
	 */
    public void compressExe(String srcPathName) {    
        File srcdir = new File(srcPathName);    
        if (!srcdir.exists()){  
            throw new RuntimeException(srcPathName + "不存在！");    
        }   
            
        Project prj = new Project();    
        Zip zip = new Zip();    
        zip.setProject(prj);    
        zip.setDestFile(zipFile);    
        FileSet fileSet = new FileSet();    
        fileSet.setProject(prj);    
        fileSet.setDir(srcdir);    
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");    
        //fileSet.setExcludes(...); //排除哪些文件或文件夹    
        zip.addFileset(fileSet);    
        zip.execute();    
    }
}
