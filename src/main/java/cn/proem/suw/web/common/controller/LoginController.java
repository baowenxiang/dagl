package cn.proem.suw.web.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.service.LoginService;
import cn.proem.dagl.web.systemSetting.entity.SkinSetting;
import cn.proem.dagl.web.systemSetting.service.SettingService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.StringUtil;

/**
 * @ClassName LoginController
 * @Description 网页版登录
 * @author Pan Jilong
 * @date 2016年12月28日
 */
@Controller
@RequestMapping(value = "/w")
public class LoginController extends BaseCtrlModel {

	@Resource
    private LoginService loginService;
	@Autowired
	private SettingService settingService;
	
	static Map<String, Integer> LOGIN_FAIL_MAP = null;
	
	@RequestMapping(value = "")
	public ModelAndView entry1(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/w/login");
	}
	@RequestMapping(value = "/")
	public ModelAndView entry2(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/w/login");
	}
	
	/**
	 * @MethodName login
	 * @Description 跳转登录页
	 * @author Pan Jilong
	 * @date 2017年1月22日
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		request.getSession().invalidate();
		ModelAndView view = new ModelAndView("/web/common/login");
		try {
			//从设置表中读取公司名
			SkinSetting setting = settingService.getSettingMsg();
			if(StringUtil.isNotEmpty(setting)){
				if(StringUtil.isNotEmpty(setting.getPlateLgName())) {
					view.addObject("systemName",setting.getPlateLgName());
					view.addObject("inBrowserName",setting.getPlateLgName());
				}else {
					view.addObject("systemName", ConfigReader.readSystemName());
					view.addObject("inBrowserName", ConfigReader.readInBrowserName());
				}
				if(StringUtil.isNotEmpty(setting.getBgPicPath())) {
					view.addObject("bgPath",setting.getBgPicPath());
				}
			}else {
				//从config.properties读取公司名称
				view.addObject("systemName", ConfigReader.readSystemName());
				view.addObject("inBrowserName", ConfigReader.readInBrowserName());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * @MethodName checklogin
	 * @Description 检查登录, 成功则跳转首页面
	 * @author Pan Jilong
	 * @date 2017年2月3日
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginValidate(HttpServletRequest request) {
		String errMsg = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isValidateCodeLogin = request.getParameter("isValidateCodeLogin");
        // 前台验证码
        String verifycode = request.getParameter("verifycode");
        int result = loginService.validateUser(username, password);// 登录验证
        // 非授权异常，登录失败，验证码加1。
        if (StringUtil.isNotEmpty(isValidateCodeLogin) && isValidateCodeLogin.equals("true")) {// 验证码登录
            if (verifycode == null || 
            		!verifycode.toUpperCase().equals(request.getSession().getAttribute(SAFE_CODE_NAME))) {
                errMsg = "验证码输入错误";
                request.getSession().removeAttribute(SAFE_CODE_NAME);// 清除验证码
            } else {
                request.getSession().removeAttribute(SAFE_CODE_NAME);// 清除验证码
                if (result == 1) {
                    request.getSession().removeAttribute(PROFILE_NAME);
                    request.getSession().setAttribute(PROFILE_NAME, username);
                    request.getSession().setAttribute("INTERCEPT", "false");	//菜单是否可以跳转
                    // 这里一定要注意先后顺序，先设值，再添加日志，并且变量名必须用平台指定的变量名
                    loginService.addLog(request);
                    isValidateCodeLogin(username, false, true);
                    return new ModelAndView("redirect:/w/index");
                } else {
                    request.setAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
                    errMsg = "用户名或密码错误";
                }
            }
        } else {
            if (result == 1) {
                request.getSession().removeAttribute(PROFILE_NAME);
                request.getSession().setAttribute(PROFILE_NAME, username);
                request.getSession().setAttribute("INTERCEPT", "false");	//菜单是否可以跳转
                // 这里一定要注意先后顺序，先设值，再添加日志，并且变量名必须用平台指定的变量名
                loginService.addLog(request);
                isValidateCodeLogin(username, false, true);
                return new ModelAndView("redirect:/w/index");
            } else {
                request.setAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
                errMsg = "用户名或密码错误";
            }
        }
        request.setAttribute("error_msg", errMsg);
        return login(request);
	}
	
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean)
    {
        if (LOGIN_FAIL_MAP == null)
        {
            LOGIN_FAIL_MAP = new HashMap<String, Integer>();
        }
        Integer loginFailNum = LOGIN_FAIL_MAP.get(useruame);
        if (loginFailNum == null)
        {
            loginFailNum = 0;
        }
        if (isFail)
        {
            loginFailNum++;
            LOGIN_FAIL_MAP.put(useruame, loginFailNum);
        }
        if (clean)
        {
            LOGIN_FAIL_MAP.remove(useruame);
        }
        return loginFailNum >= 3;
    }
	
	/**
	 * 登出操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/w/login");	
		request.getSession().removeAttribute(PROFILE_NAME);
		request.getSession().invalidate();	
		return view;
	}

}
