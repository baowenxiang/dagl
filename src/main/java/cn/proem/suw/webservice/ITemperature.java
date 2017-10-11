package cn.proem.suw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import cn.proem.suw.webservice.model.ResponseModel;
import cn.proem.suw.webservice.model.Temperature;

@WebService
public interface ITemperature {

	@WebMethod
	ResponseModel save(@WebParam(name = "temperature")Temperature temperature);
}
