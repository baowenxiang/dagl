package cn.proem.dagl.web.preArchive.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.dagl.web.preArchive.service.YwgjService;
import cn.proem.dagl.web.preArchive.service.ZlsjService;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.common.model.ResultModel;

/**
 * 原文挂接控制层
 * @author bao
 *
 */
@Controller
@RequestMapping(value = "/w/ywgj")
public class YwgjController extends BaseCtrlModel {
	@Autowired
	private YwgjService ywgjService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ZlsjService zlsjService;
	/**
	 * @Description 上传原文
	 * @MethodName uploadFile
	 * @author bao
	 * @date 2017年4月20日
	 * @param request
	 * @param attachments
	 * @return ResultModel<String>
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	@LogService(description = "上传原文")
	public ResultModel<String> uploadFile(HttpServletRequest request,@RequestParam("attachment[]") MultipartFile[] attachments){
		ResultModel<String> resultModel = new ResultModel<String>();
		List<String> msg = new ArrayList<String>();
		try {
			if (attachments != null) {
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				Zlsj zlsj = new Zlsj();
				zlsj.setDrsj(simpleDateFormat1.format(new Date()));
				zlsj.setDrz(this.getCurrentUser(request).getName());
				zlsj.setCreateTime(new Date());
				zlsj.setDelFlag(0);
				try {
					String zlsjId = zlsjService.saveAttachment(zlsj);
				} catch (ServiceException e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				} catch (Exception e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				}
				for(int i=0;i<attachments.length;i++){
					MultipartFile atta = attachments[i];
					Ywgj ywgj = new Ywgj();
					if (!atta.isEmpty() && (atta != null && !"".equals(atta.getOriginalFilename()))) {
						String extName = atta.getOriginalFilename().substring(
								atta.getOriginalFilename().lastIndexOf("."));// 获得后缀
						String fileType = atta.getOriginalFilename().substring(
								atta.getOriginalFilename().lastIndexOf(".")+1);// 获得后缀
						String filePath = UUID.randomUUID().toString() + extName;
						String fileName = atta.getOriginalFilename();
						
						msg.add(fileName);
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						
						String path = simpleDateFormat.format(new Date());
						
						String realpath = this.getFilePath() + Path.UPLOAD_YWGJ_FILE_PATH + path +File.separator + filePath;
						File fileTo = new File(realpath);
						if (!fileTo.exists()) {
							fileTo.mkdirs();
						}
						atta.transferTo(fileTo);
						
						ywgj.setXsxh(i+1);
						ywgj.setWjdz(Path.UPLOAD_YWGJ_FILE_PATH+path+File.separator+ filePath);
						ywgj.setScrq(new Date());
						ywgj.setScz(getCurrentUser(request).getId());
						ywgj.setWjlx(fileType);
						ywgj.setWjdx(atta.getSize());
						ywgj.setWjm(fileName);
						ywgj.setZlsj(zlsj.getId());
						ywgj.setCreateTime(new Date());
						ywgj.setDelFlag(0);
						
						try {
							ywgjService.saveAttachment(ywgj);
						} catch (ServiceException e) {
							resultModel.setSuccess(false);
							resultModel.setMsg(e.getMessage());
						} catch (Exception e) {
							resultModel.setSuccess(false);
							resultModel.setMsg(e.getMessage());
						}
					}
				}
			}
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg("附件上传失败");
		}
		resultModel.setMsg("资料"+StringUtils.join(msg.toArray(),",")+"上传成功");
		return resultModel;
	}
	
	/**
	 * @Description 逻辑删除原文
	 * @MethodName deleteFiles
	 * @author bao
	 * @date 2017年4月22日
	 * @param obj
	 * @return ResultModel<String>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/removeFiles")
	@ResponseBody
	@LogService(description = "逻辑删除原文")
	public ResultModel<String> deleteFiles(@RequestBody Map<String, Object> obj) {
		ResultModel<String> resultModel = new ResultModel<String>();
		List<String> attaIds = (List<String>)obj.get("attaIds");
		if (attaIds != null && attaIds.size() > 0) {
			for (String id : attaIds) {
				try {
					ywgjService.removeAttachment(id);
				} catch (ServiceException e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				} catch (Exception e) {
					resultModel.setSuccess(false);
					resultModel.setMsg(e.getMessage());
				}
			}
		}
		return resultModel;
	}
	
	
	
	
	
}

	




