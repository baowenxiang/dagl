package cn.proem.dagl.web.eep.service;

import java.util.List;

import cn.proem.dagl.web.eep.entity.EepEntity;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.eep.entity.BusEnt;
import cn.proem.eep.entity.DocData;
import cn.proem.eep.util.EEPUtil;
import cn.proem.suw.web.common.exception.ServiceException;

public interface EEPService {
    public List<DocData> getDocs(EEPUtil util, String uuid) throws ServiceException;
    public BaseEntityInf getArchiveDetail(String archivetype, String uuid) throws ServiceException;
    public String getProp(String prop);
    
    public void saveDaid(EepEntity da) throws ServiceException;
    
	public List<EepEntity> getFileBydataId(String id);
    
	public List<BusEnt> getWDetail(EEPUtil util, String id);
    
    
}
