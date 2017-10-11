package cn.proem.dagl.web.sysManage.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.dagl.web.sysManage.dto.DtoUserDetail;
import cn.proem.dagl.web.sysManage.entity.UserDetail;
import cn.proem.dagl.web.sysManage.service.PersonalInfoService;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;
/**
 * 个人信息控制层
 * @author bao
 *
 */
@Controller
@RequestMapping(value = "/w/sysManage")
public class PersonalInfoController extends BaseCtrlModel {
	@Autowired
	private CommonService commonService;
	@Autowired
	private PersonalInfoService personalInfoService;
	
	/**
	 * @Description 个人信息初始化页面
	 * @MethodName initView
	 * @author bao
	 * @date 2017年4月20日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/personalInfoView")
	public ModelAndView initView(HttpServletRequest request) {
		ModelAndView modelAndView = this.createNormalView("/web/sysManage/personalInfo.vm");
		
		
		return modelAndView;
	}
	
	/**
	 * @Description 获得登录用户详情
	 * @MethodName getDtoUserDetail
	 * @author bao
	 * @date 2017年4月24日
	 * @param request
	 * @return ResultModel<DtoUserDetail>
	 */
	@RequestMapping(value = "/getDtoUserDetail")
	@ResponseBody
	@LogService(description = "获取登录用户详情")
	public ResultModel<DtoUserDetail> getDtoUserDetail(HttpServletRequest request){
		ResultModel<DtoUserDetail> resultModel = new ResultModel<DtoUserDetail>();
		User user = this.getCurrentUser(request);
		
		DtoUserDetail dtoUserDetail = new DtoUserDetail();
		dtoUserDetail.setUser(user);
		dtoUserDetail.setUserDetail(personalInfoService.getUserDetailByUser(user));
		dtoUserDetail.setLoginNum(personalInfoService.getLoginNum(user));
		dtoUserDetail.setLastTime(personalInfoService.getLastTime(user));
		
		resultModel.setData(dtoUserDetail);
		return resultModel;
		
	}
	
	
	@RequestMapping(value = "/saveUserDetail")
	@ResponseBody
	@LogService(description = "保存人员信息")
	public ResultModel<String> saveUserDetail(HttpServletRequest request, 
				@RequestParam(value="attachment",required = false) MultipartFile attachment,
				@RequestParam("telphone") String telphone,
				@RequestParam("appellation") String appellation,
				@RequestParam("name") String name,
				@RequestParam("gender") String gender) {
		User user = this.getCurrentUser(request);
		ResultModel<String> resultModel = new ResultModel<String>();
		
		try {
			UserDetail userDetail = personalInfoService.getUserDetailByUser(user);
			if(userDetail==null){
				userDetail = new UserDetail();
			}
			userDetail.setTelphone(telphone);
			userDetail.setAppellation(appellation);
			userDetail.setUser(user);
			if (attachment != null) {
					if (!attachment.isEmpty() && (attachment != null && !"".equals(attachment.getOriginalFilename()))) {
						String extName = attachment.getOriginalFilename().substring(
								attachment.getOriginalFilename().lastIndexOf("."));// 获得后缀
						String filePath = UUID.randomUUID().toString() + extName;
						String fileName = attachment.getOriginalFilename();
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						String path = simpleDateFormat.format(new Date());
						
//						String realpath = this.getFilePath() + Path.UPLOAD_YWGJ_FILE_PATH + path +File.separator + filePath;
						String realpath = Path.UPLOAD_YWGJ_FILE_PATH + path +File.separator + filePath;
						File fileTo = new File(this.getFilePath() + realpath);
						if (!fileTo.exists()) {
							fileTo.mkdirs();
						}
						attachment.transferTo(fileTo);
						
						userDetail.setPicPath(realpath);
						userDetail.setPicName(fileName);
					}
				
				}else{
					if(StringUtil.isEmpty(userDetail.getPicName())) {
						userDetail.setPicName("");
					}
					if(StringUtil.isEmpty(userDetail.getPicPath())) {
						userDetail.setPicPath("");
					}
				}
			personalInfoService.saveOrUpdatUserDetail(userDetail);
			user.setName(name);
			user.setGender(Integer.valueOf(gender));
			personalInfoService.updateUser(user);
			
		}catch (ServiceException e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		} catch (Exception e) {
			resultModel.setSuccess(false);
			resultModel.setMsg(e.getMessage());
		}
		
		return resultModel;
	}
	
	@RequestMapping(value = "/updatePassword")
	@ResponseBody
	@LogService(description = "修改密码")
	public ResultModel<String> updatePassword(HttpServletRequest request,String oldPassword,String password){
		ResultModel<String> resultModel = new ResultModel<String>();
		User user = this.getCurrentUser(request);
		if(!oldPassword.equals(user.getPassword())){
			resultModel.setSuccess(false);
			resultModel.setMsg("原始密码输入有误");
			return resultModel;
		}else{
			user.setPassword(password);
			try {
				personalInfoService.updateUser(user);
			} catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			} catch (Exception e) {
				resultModel.setSuccess(false);
				resultModel.setMsg(e.getMessage());
			}
		}
		
		return resultModel;
	}
}

	




