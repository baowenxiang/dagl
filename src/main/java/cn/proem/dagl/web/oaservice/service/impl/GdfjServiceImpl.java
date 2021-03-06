package cn.proem.dagl.web.oaservice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.oaservice.entity.InfoGdfj;
import cn.proem.dagl.web.oaservice.entity.TGdfjObj;
import cn.proem.dagl.web.oaservice.service.GdfjService;

@Service
public class GdfjServiceImpl implements GdfjService {

	@Resource
	private GeneralDao generalDao;
	@Override
	public Integer addGdfjObj(TGdfjObj gdfjObj) {
		InfoGdfj info = new InfoGdfj();
		info.setFjhz(gdfjObj.getFjhz());
		info.setFjmc(gdfjObj.getFjmc());
		info.setFjnr(gdfjObj.getFjnr());

		info.setDaId(gdfjObj.getId());
		info.setYwurl(gdfjObj.getYwurl());
		info.setDelflag(0);;
		info.setIsArchive(3);
		

		String id = generalDao.save(gdfjObj);
		info.setId(id);
		generalDao.save(info);
		return 0;
	}
}
