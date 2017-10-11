package cn.proem.dagl.web.oaservice.service;

import cn.proem.dagl.web.oaservice.entity.TGdwjObj;

public interface GdwjService {
	/**
	 *  往INFO_WSDA_GDWJ中添加数据
	 * @param gdwjObj
	 * @return Integer
	 */
	public Integer addGdwjObj(TGdwjObj gdwjObj,String id);
}
