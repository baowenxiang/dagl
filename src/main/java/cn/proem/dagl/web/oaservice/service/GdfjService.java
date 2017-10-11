package cn.proem.dagl.web.oaservice.service;

import cn.proem.dagl.web.oaservice.entity.TGdfjObj;

public interface GdfjService {
	/**
	 *  往INFO_WSDA_GDFJ中添加数据
	 * @param gdfjObj
	 * @return Integer
	 */
	public Integer addGdfjObj(TGdfjObj gdfjObj);
}
