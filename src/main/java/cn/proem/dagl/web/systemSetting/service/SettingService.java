package cn.proem.dagl.web.systemSetting.service;

import cn.proem.dagl.web.systemSetting.entity.SkinSetting;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 * @ClassName SettingService
 * @Description 皮肤设置服务层
 * @author chenxiaofen
 * @date 2017年5月9日
 */
public interface SettingService {
	/**
	 * @MethodName saveSkin
	 * @Description 保存皮肤设置
	 * @author chenxiaofen
	 * @date 2017年5月8日
	 * @param skinSetting
	 */
	public void saveSkin(SkinSetting skinSetting) throws ServiceException;
	/**
	 * @MethodName getSkin
	 * @Description 获取当前用户的皮肤设置信息(目前废弃了。。。)
	 * @author chenxiaofen
	 * @date 2017年5月8日
	 * @param user_id
	 * @return
	 * @throws ServiceException
	 */
	public SkinSetting getSkin(String user_id) throws ServiceException;
	/**
	 * @MethodName getSettingMsg
	 * @Description 获取设置信息
	 * @author chenxiaofen
	 * @date 2017年5月11日
	 * @return
	 * @throws ServiceException
	 */
	public SkinSetting getSettingMsg() throws ServiceException;
}
