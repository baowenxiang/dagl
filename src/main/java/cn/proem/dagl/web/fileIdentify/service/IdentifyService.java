package cn.proem.dagl.web.fileIdentify.service;

import java.util.List;

import cn.proem.dagl.web.archives.entity.DTableName;
import cn.proem.dagl.web.fileIdentify.entity.FileIdentify;
import cn.proem.dagl.web.fileIdentify.entity.IdentifyContent;
import cn.proem.suw.web.common.exception.ServiceException;


public interface IdentifyService {
	
	/**
	 * @Description 获得所有档案表信息
	 * @MethodName getAllDtablename
	 * @author bao
	 * @date 2017年5月19日
	 * @return List<DTableName>
	 */
	public List<DTableName> getAllDtablename();
	
	/**
	 * @Description 保存档案鉴定信息
	 * @MethodName saveFileIdentify
	 * @author bao
	 * @date 2017年5月21日
	 * @param fileIdentify
	 * @return
	 * @throws ServiceException String
	 */
	public String saveFileIdentify(FileIdentify fileIdentify) throws ServiceException;
	
	/**
	 * @Description 保存鉴定内容
	 * @MethodName saveIdentifyContent
	 * @author bao
	 * @date 2017年5月21日
	 * @param identifyContent
	 * @return
	 * @throws ServiceException String
	 */
	public String saveIdentifyContent(IdentifyContent identifyContent) throws ServiceException;
}
