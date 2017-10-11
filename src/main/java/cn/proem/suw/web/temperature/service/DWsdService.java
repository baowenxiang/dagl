package cn.proem.suw.web.temperature.service;

import java.util.List;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.temperature.entity.DWsd;

public interface DWsdService {
    
    public List<DWsd> wsdsByPage(Integer startRecord, Integer pageSize);
    
    public void save(DWsd wsd) throws ServiceException;
    
}
