package cn.proem.dagl.web.systemSetting.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.proem.core.annotation.LogService;
import cn.proem.dagl.web.systemSetting.entity.BackUp;
import cn.proem.dagl.web.systemSetting.entity.ZipUtil;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.util.ConfigReader;

@Controller
@RequestMapping(value = "/w/backUpTimer")
public class BackUpController extends BaseCtrlModel {
	@ResponseBody
    @RequestMapping(value = "/fileBackUp")
	@LogService(description = "文件夹备份")
	public ResultModel<String> fileBackUp(){
		ResultModel<String> resultModel = new ResultModel<String>();
		System.out.println("文件夹备份开始======");
		String time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		File file = new File(Path.FILE_BACKUP_PATH);
        if(!file.exists()){
        	file.mkdir();
        }
		ZipUtil zca = new ZipUtil(Path.FILE_BACKUP_PATH + time + ".zip");
		zca.compressExe(this.getFilePath() + "\\uploadFile");
        return resultModel;
	}
	@ResponseBody
    @RequestMapping(value = "/dbBackUp")
	@LogService(description = "数据库备份")
	public void dbBackUp(){
		System.out.println("数据库备份开始======");
		String path = backupTimer();
		BackUp backup = new BackUp();
		backup.setFileName(path);
		backup.setPath(path);
		backup.setUserName("系统");
		backup.setContent("系统备份");
//		try {
//			backUpService.saveBackUp(backup);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
		
        
	}
	public static String backupTimer() {
		String path = "";
        try {
			
            Runtime rt = Runtime.getRuntime();
            
            //读取config.properties文件，获取ip以及数据库用户名密码
            String ip = ConfigReader.readIP();
            String userName = ConfigReader.readUserName();
            String password = ConfigReader.readPassword();
            String severName = ConfigReader.readSeverName();
            
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //调用Oracle的命令
            Process child = rt
    				.exec("exp " + userName + "/" + password + "@" + ip + "/" + severName + " file=" + Path.BACKUP_PATH + time + ".dmp");
            path = Path.BACKUP_PATH + time + ".dmp";
            //调用Oracle安装目录的命令(本地)
//            Process child = rt
//    				.exec("exp " + "DROA" + "/" + "123456" + "@" + "orcl" + " file=" + Path.BACKUP_PATH + "/" + time + ".dmp");
            final InputStream is1 = child.getInputStream();  
            new Thread(new Runnable() {  
                public void run() {  
                    BufferedReader br = new BufferedReader(new InputStreamReader(is1));  
                    String info;  
                    try {  
                        while ((info=br.readLine()) != null){  
                        System.out.println("info: "+info);   
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start(); // 启动单独的线程来清空process.getInputStream()的缓冲区  
            InputStream is2 = child.getErrorStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));  
            // 保存输出结果  
            StringBuilder buf = new StringBuilder();  
            String line = null;  
            int i=0;  
            while ((line = br2.readLine()) != null){  
	            // 循环等待ffmpeg进程结束  
            	System.out.println("info: " +line);  
	            buf.append(line);  
            }  
            try {  
            	if(buf.toString().contains("ORA-")&&buf.toString().contains("EXP-")){  
            		System.err.println("备份失败！");  
            		child.destroy();  
            	}else{  
            		i=child.waitFor();   
            		System.out.println("over status: "+i);  
            	}  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("备份结束...");
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return path;
    }
}
