package cn.proem.suw.web.common.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.suw.web.common.dto.DtoArchives;
import cn.proem.suw.web.common.service.ArchiveIndexService;

@Service
public class ArchiveIndexServiceImpl implements ArchiveIndexService {
	@Resource
	private GeneralDao generalDao;

	@Override
	public List<DtoArchives> getArchive() {
		String sql = "SELECT T.ZWM as \"dalx\", T.YWM as \"tablename\", T.UUID as \"uuid\", T.TM as \"tm\", T.DH as \"dh\", T.BGQX as \"bgqx\", T.CWRQ as \"cwrq\", T.ENDDAY as \"endday\", T.UUID as \"uuid\" FROM v_pdagl_bgqx T where not exists ( select * from pdagl_message_jdda where daid = uuid) and not exists (select * from PFM_FILE_IDENTIFY T2 where T2.JDNR = '销毁' and t2.del_flag = 0 and t2.daid = t.uuid ) ";
		List<Map<String, Object>> mls = generalDao.getObjectList(
				sql.toString(), 0, -1);
		List<DtoArchives> archives = new ArrayList<DtoArchives>();
		Format format = new SimpleDateFormat("yyyyMMdd");
		String nowtime = format.format(new Date());
		for (Map<String, Object> ml : mls) {
			if(ml.get("endday") == null || ( nowtime.compareTo((String) ml.get("endday")) < 0) ){
				continue;
			}
			DtoArchives check = new DtoArchives();
			check.setDalx((String) ml.get("dalx"));
			check.setTm((String) ml.get("tm"));
			check.setDh((String) ml.get("dh"));
			check.setBgqx((String) ml.get("bgqx"));
			check.setCwrq((String) ml.get("cwrq"));
			check.setEndday((String) ml.get("endday"));
			check.setTableName((String) ml.get("tablename"));
			check.setUuid((String) ml.get("uuid"));
			archives.add(check);
		}
		return archives;
	}
	
}

