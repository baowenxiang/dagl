package cn.proem.suw.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import cn.proem.suw.web.docu.entity.DocWDetail;
import cn.proem.suw.web.docu.entity.DocuDetail;
import cn.proem.suw.webservice.model.CxfFileWrapper;
import cn.proem.suw.webservice.model.ResponseModel;

@WebService
public interface ExportData {

	@WebMethod
	ResponseModel exportDocuDetails(@WebParam(name = "docuDetails")List<DocuDetail> docuDetails);
	
	@WebMethod
	ResponseModel exportAttachments(@WebParam(name = "file") CxfFileWrapper file);
	
	@WebMethod
	ResponseModel exportDocwDetials(@WebParam(name = "workflows") List<DocWDetail> workflows);
}
