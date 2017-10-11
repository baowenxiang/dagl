package cn.proem.dagl.web.eep.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.eep.entity.EepEntity;
import cn.proem.dagl.web.eep.service.EEPService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.eep.entity.BusEnt;
import cn.proem.eep.entity.DocData;
import cn.proem.eep.entity.ElecAttribute;
import cn.proem.eep.entity.Encode;
import cn.proem.eep.entity.EncodeData;
import cn.proem.eep.util.Base64Util;
import cn.proem.eep.util.EEPUtil;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.docu.entity.DocWDetail;

@Service
public class EEPServiceImpl implements EEPService {
    
    @Autowired
    private DicManagerService dicService;
    
    @Autowired
    private GeneralDao generalDao;

    @Autowired
    private CustomArchiveService customArchiveService;
    /**
     * @Method: getDocs 
     * @Description: 活动档案文件数据
     * @param uuid 档案标识符
     * @return 
     * @throws ServiceException 
     */
    @Override
    public List<DocData> getDocs(EEPUtil util, String uuid) throws ServiceException {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("zlsj", uuid, ConditionType.EQ, FieldType.STRING));
        queryBuilder.addCondition(new QueryCondition("delFlag", 0, ConditionType.EQ, FieldType.INTEGER));
        List<Ywgj> ywgjs = generalDao.queryByCriteria(Ywgj.class, queryBuilder, null, 0, -1);
        List<DocData> datas = new ArrayList<DocData>(); 
        List<Encode> encodes = new ArrayList<Encode>();
        for(Ywgj ywgj : ywgjs){
            ElecAttribute elec = util.createElecAttribute(ywgj.getWjm(), String.valueOf(ywgj.getWjdx()));
            String result;
            String filepath = null;
            try {
                filepath = ConfigReader.readAppHome() + ywgj.getWjdz();
                System.out.println("开始对文件 '" + filepath + "'进行编码..." );
                result = Base64Util.encoder2(filepath);
                System.out.println("文件 '" + filepath + "'编码成功。" );
            } catch (Exception e) {
                System.out.println("文件 '" + filepath + "'编码失败。" );
               throw new ServiceException(e.getMessage());
            }
            EncodeData data = util.createEncodeData(uuid, null, result.getBytes());
            encodes.add(util.createEncode(elec, EEPUtil.CODE_DESC, "base64-" + (ywgj.getWjlx()==null?"default":ywgj.getWjlx()), data));
        }
        datas.add(util.creatDocData(uuid, encodes));
        return datas;
    }

    @Override
    public BaseEntityInf getArchiveDetail(String archivetype, String uuid) throws ServiceException {
        // 获得所有数据
        return customArchiveService.getEntity(archivetype, uuid);
    }

    @Override
    public String getProp(String prop) {
        // 取得字典项目
        List<DictionaryValue> dicts = dicService.getDicValueList("eep");
        for(int i =0 ; i < dicts.size(); i++){
            if(prop.equals(dicts.get(i).getDvno())) return dicts.get(i).getDvalue();
        }
        return null;
    }
    
    @Override
	@Transactional
	@LogService(description ="保存档案ID")
	public void saveDaid(EepEntity da) throws ServiceException {
    															//字段名		字段值	对象
		EepEntity eepEntity = generalDao.findUniqueByProperty("daid", da.getDaid(), EepEntity.class);
    	//如果存在相同daid，找到记录后更新时间
		if(eepEntity != null){
			//更新修改时间为最新时间
    		eepEntity.setUpdateTime(new Date());
    		//修改记录
    		generalDao.update(eepEntity);
    	//如果不存在相同daid，新建保存
    	}else{														
		generalDao.save(da);
    	}
    }	
    
	@Override
	public List<EepEntity> getFileBydataId(String id) {
		QueryBuilder queryBuilder = new QueryBuilder();
		List<EepEntity> daids;
		if(StringUtil.isEmpty(id)){
			daids  = new ArrayList<EepEntity>();
		}else{
			queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0), ConditionType.EQ,FieldType.INTEGER, null));
			queryBuilder.addCondition(new QueryCondition("daid", id, ConditionType.EQ, null));
			daids = generalDao.queryByCriteria(EepEntity.class, queryBuilder, null, 0, -1);
		}
		return daids;
	}
	
	
    @Override
    public List<BusEnt> getWDetail(EEPUtil util, String id) {
    	List<BusEnt> busents = new ArrayList<BusEnt>();
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("ysjid", id, ConditionType.EQ, FieldType.STRING));
        queryBuilder.addCondition(new QueryCondition("delFlag", 0, ConditionType.EQ, FieldType.INTEGER));
        List<DocWDetail> DocWDetails = generalDao.queryByCriteria(DocWDetail.class, queryBuilder, null, 0, -1);
        for(DocWDetail DocWDetail : DocWDetails){
        	String ywbsf = DocWDetail.getYwbsf();
        	String jgrybsf = DocWDetail.getJgrymc();
        	String wjbsf = DocWDetail.getWjbsf();
        	String ywzt = DocWDetail.getYwzt();
        	String ywxw = DocWDetail.getYwxw();
        	String xwyj = DocWDetail.getXwyj();
        	String xwsj = DocWDetail.getXwsj();
        	String xwms = DocWDetail.getXwms();
        	busents.add(util.createBusEnt(ywbsf, jgrybsf, wjbsf, ywzt, ywxw, xwsj, xwyj, xwms));
        }
        
        return busents;
    }
    
}
