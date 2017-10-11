package cn.proem.suw.web.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.bpm.service.BpmQueryService;
import cn.proem.core.entity.User;
import cn.proem.core.model.DataGrid;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.service.ArchiveIndexService;
import cn.proem.suw.web.common.service.HeaderService;
import cn.proem.suw.web.common.dto.DtoArchives;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;

@Controller
@RequestMapping(value = "/notice")
public class ArchiveIndexController extends BaseCtrlModel {

	@Autowired
    private ArchiveIndexService ArchiveIndexService;
	@Autowired
    private CustomArchiveService customArchiveService;
	//1
	@Autowired
    private BpmQueryService bpmQueryService;
	@Autowired
    private HeaderService HeaderService;
	
	
	@RequestMapping(value = "/jdda")
  	@ResponseBody
    public ResultModel<DtoArchives> jdda(HttpServletRequest request) throws ServiceException {
  		ResultModel<DtoArchives> resultModel = new ResultModel<DtoArchives>();
  			
  		List<DtoArchives> archives = ArchiveIndexService.getArchive();
  		
  		resultModel.setDatas(archives);
  		return resultModel;
  	}
	
	/**
	 * @Description 
	 * @MethodName nextstepView
	 * @author bao
	 * @date 2017年5月21日
	 * @return ModelAndView
	 */
    @RequestMapping(value = "/nextstepView")
    public ModelAndView nextstepView(HttpServletRequest request,
    								@RequestParam(value="tablename") String tablename,
    								@RequestParam(value="ids")String ids,
    								@RequestParam(value="flag")String flag) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/detsroyNextstep.vm");
        //获得流程定义id
        String condition = " name = '档案销毁流程'";
        List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
        String processId = processes.get(0).get("ID");
        
        modelAndView.addObject("processId",processId);
        modelAndView.addObject("tablename", tablename);
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("flag", flag);
        
        return modelAndView;
	 }	
    
	@RequestMapping(value = "/toModifyRetentionView")
	public ModelAndView toModifyRetentionView(HttpServletRequest request,String tablename,String ids,String flag) throws ServiceException{
		ModelAndView modelAndView = this.createNormalView("/web/fileIdentify/modifyRetention.vm");
		
		String condition = " name = '修改保管期限流程'";
        List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
        String processId = processes.get(0).get("ID");
        
        modelAndView.addObject("processId",processId);
		modelAndView.addObject("tablename", tablename);
		modelAndView.addObject("ids", ids);
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/getJdtx")
  	@ResponseBody
  	public String getJdtx(HttpServletRequest request) {
		
		List<DtoArchives> archives = ArchiveIndexService.getArchive();
		
		return JSON.toJSONStringWithDateFormat(
				archives,
                "yyyy-MM-dd",
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
		
	}
	
	
	@RequestMapping(value = "/jdtx")
    public ModelAndView detroyToDoTaskView(HttpServletRequest request) throws ServiceException{
   	 User user = this.getCurrentUser(request);
        ModelAndView modelAndView = this.createNormalView("/web/common/jdtx.vm");
        
        //获得流程定义id
        String condition = " name = '档案销毁流程'";
        List<BaseEntityInf> processes = customArchiveService.getEntitiesByConditions("ptp_bpm_process", condition);
        String processId = processes.get(0).get("ID");
        
        modelAndView.addObject("processId", processId);
        return modelAndView;
	 }
    
    
	
}
