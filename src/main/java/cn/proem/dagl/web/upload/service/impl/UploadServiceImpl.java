package cn.proem.dagl.web.upload.service.impl;

import java.io.File;
import java.util.UUID;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.proem.dagl.web.upload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Override
	public String upload(HttpServletRequest request,MultipartFile atta, String pathname) throws Exception{
		String extName = atta.getOriginalFilename().substring(atta.getOriginalFilename().lastIndexOf("."));// 获得后缀.xlsx
		//文件的名称	abc.xlsx
		String filename = atta.getOriginalFilename();
		//文件对应的物理路径名称 4028b8815beac758015beac9127a0003.xlsx
		String realname = UUID.randomUUID().toString() + extName; 
		
		String realpath = request.getServletContext().getRealPath("/")+File.separator+ ".." + pathname.replace("/", File.separator) + realname;
		
		File fileTo = new File(realpath);
		if (!fileTo.exists()) {
			fileTo.mkdirs();
		}
		atta.transferTo(fileTo);
		return realname;
	}
	
	
	
}
