package cn.proem.suw.webservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.temperature.entity.DWsd;
import cn.proem.suw.web.temperature.service.DWsdService;
import cn.proem.suw.webservice.ITemperature;
import cn.proem.suw.webservice.model.ResponseModel;
import cn.proem.suw.webservice.model.Temperature;

@Service
public class ITemperatureImpl implements ITemperature{
    @Resource
    private DWsdService dWsdService;
    
    @Override
    @Transactional
    public ResponseModel save(Temperature temperature) {
        // 返回对象
        ResponseModel response = new ResponseModel();
        DWsd wsd = new DWsd();
        // 温湿度id
        wsd.setId(temperature.getId());
        // 记录日期
        wsd.setJlrq(temperature.getJlrq());
        // 时间
        wsd.setWsdsj(temperature.getWsdsj());
        // 记录人
        wsd.setJlr(temperature.getJlr());
        // 温度
        wsd.setWd(temperature.getWd());
        // 湿度
        wsd.setSd(temperature.getSd());
        
        try {
            dWsdService.save(wsd);
        } catch (ServiceException e) {
            response.setSuccess(false);
            response.setMsg(e.getMessage());
        } catch(Exception e){
            response.setSuccess(false);
            response.setMsg(e.getMessage());
        }
        return response;
    }

}
