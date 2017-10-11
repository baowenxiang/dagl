package cn.proem.dagl.web.systemSetting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.systemSetting.entity.SkinSetting;
import cn.proem.dagl.web.systemSetting.service.SettingService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.StringUtil;
@Service
public class SettingServiceImpl implements SettingService {
	@Autowired
	private GeneralDao generalDao;

	@Override
	@Transactional
	@LogService(description = "保存或修改系统设置")
	public void saveSkin(SkinSetting skinSetting) throws ServiceException {
		if(StringUtil.isEmpty(skinSetting)) {
			throw new ServiceException("数据不能为空");
		}
		if(StringUtil.isEmpty(skinSetting.getId())) {
			generalDao.save(skinSetting);
		}else {
			SkinSetting setting = generalDao.findById(skinSetting.getId(), SkinSetting.class);
			if(StringUtil.isNotEmpty(skinSetting.getSkinClass())) {
				setting.setSkinClass(skinSetting.getSkinClass());
			}
			if(StringUtil.isNotEmpty(skinSetting.getPlateLgName())) {
				setting.setPlateLgName(skinSetting.getPlateLgName());
			}
			if(StringUtil.isNotEmpty(skinSetting.getPlateMiniName())) {
				setting.setPlateMiniName(skinSetting.getPlateMiniName());
			}
			if(StringUtil.isNotEmpty(skinSetting.getBgPicPath())) {
				setting.setBgPicPath(skinSetting.getBgPicPath());
			}
			if(StringUtil.isNotEmpty(skinSetting.getHeaderPicPath())) {
				setting.setHeaderPicPath(skinSetting.getHeaderPicPath());
			}
			if(StringUtil.isNotEmpty(skinSetting.getHeaderPicPath())) {
				setting.setHeaderPicPath(skinSetting.getHeaderPicPath());
			}
			if(StringUtil.isNotEmpty(skinSetting.getSkinPicPath())) {
				setting.setSkinPicPath(skinSetting.getSkinPicPath());
			}
			generalDao.update(setting);
		}
	}

	@Override
	public SkinSetting getSkin(String user_id) throws ServiceException {
		
		return generalDao.findUniqueByProperty("userId", user_id, SkinSetting.class);
	}

	@Override
	public SkinSetting getSettingMsg() throws ServiceException {
		List<SkinSetting> list = generalDao.queryByCriteria(SkinSetting.class, new QueryBuilder(), null, 0, -1);
		SkinSetting setting = new SkinSetting();
		if(list != null && list.size() > 0) {
			setting = list.get(0);
		}
		return setting;
	}
}
