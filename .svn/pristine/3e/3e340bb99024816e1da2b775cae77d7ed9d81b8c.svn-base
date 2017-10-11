package cn.proem.suw.web.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.dagl.web.eep.entity.EepEntity;
import cn.proem.dagl.web.fileIdentify.dto.DtoFileBase;
import cn.proem.dagl.web.message.entity.Jdda;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.service.HeaderService;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.dto.DtoHeader;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;

@Controller
@RequestMapping(value = "/header")
public class HeaderController extends BaseCtrlModel {

	@Autowired
    private HeaderService HeaderService;
	
	@RequestMapping(value = "/message")
  	@ResponseBody
    public ResultModel<DtoHeader> jdda(HttpServletRequest request) throws ServiceException {
  		ResultModel<DtoHeader> resultModel = new ResultModel<DtoHeader>();
  		
  		List<DtoHeader> jdda = HeaderService.getJdda();
  		resultModel.setDatas(jdda);
  		return resultModel;
  	}
	//2017年8月22日 
	@RequestMapping(value = "/getJdda")
	@ResponseBody
	@LogService(description = "获取档案数据")
	public String jdda(String uuid, HttpServletRequest request)throws ServiceException {
		Jdda da = new Jdda();
		da.setDaid(uuid);
		HeaderService.saveDaid(da);
		
		return "success";
	}
}
