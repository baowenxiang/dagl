package cn.proem.suw.web.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.eep.entity.EepEntity;
import cn.proem.dagl.web.message.entity.Jdda;
import cn.proem.suw.web.common.dto.DtoArchives;
import cn.proem.suw.web.common.dto.DtoHeader;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.service.HeaderService;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class HeaderServiceImpl implements HeaderService {
	@Resource
	private GeneralDao generalDao;

	@Override
	public List<DtoHeader> getJdda() {
		String sql = "SELECT T.ZWM as \"dalx\", T.YWM as \"tablename\", T.UUID as \"uuid\", T.TM as \"tm\", T.DH as \"dh\", T.BGQX as \"bgqx\", T.CWRQ as \"cwrq\", T.ENDDAY as \"endday\", T.UUID as \"uuid\" FROM v_pdagl_bgqx T where not exists ( select * from pdagl_message_jdda where daid = uuid) and not exists (select * from PFM_FILE_IDENTIFY T2 where T2.JDNR = '销毁' and t2.del_flag = 0 and t2.daid = t.uuid ) ";
		List<Map<String, Object>> mls = generalDao.getObjectList(
				sql.toString(), 0, -1);
		List<DtoHeader> jdda = new ArrayList<DtoHeader>();
		for (Map<String, Object> ml : mls) {
			DtoHeader check = new DtoHeader();
			check.setDalx((String) ml.get("dalx"));
			check.setTm((String) ml.get("tm"));
			check.setDh((String) ml.get("dh"));
			check.setBgqx((String) ml.get("bgqx"));
			check.setCwrq((String) ml.get("cwrq"));
			check.setEndday((String) ml.get("endday"));
			check.setUuid((String) ml.get("uuid"));
			jdda.add(check);
		}
		return jdda;
	}
	
	//2017年8月22日
	@Override
	@Transactional
	@LogService(description ="保存档案ID")
	public void saveDaid(Jdda da) throws ServiceException {
    												//字段名		字段值	对象
		Jdda jdda = generalDao.findUniqueByProperty("daid", da.getDaid(), Jdda.class);
    	//如果存在相同daid，找到记录后更新时间
		if(jdda != null){
			//更新修改时间为最新时间
    		jdda.setUpdateTime(new Date());
    		//修改记录
    		generalDao.update(jdda);
    	//如果不存在相同daid，新建保存
    	}else{														
		generalDao.save(da);
    	}
    }	
    
	@Override
	public List<Jdda> jdda(String id) {
		QueryBuilder queryBuilder = new QueryBuilder();
		List<Jdda> daids;
		if(StringUtil.isEmpty(id)){
			daids  = new ArrayList<Jdda>();
		}else{
			queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0), ConditionType.EQ,FieldType.INTEGER, null));
			queryBuilder.addCondition(new QueryCondition("daid", id, ConditionType.EQ, null));
			daids = generalDao.queryByCriteria(Jdda.class, queryBuilder, null, 0, -1);
		}
		return daids;
	}
}

