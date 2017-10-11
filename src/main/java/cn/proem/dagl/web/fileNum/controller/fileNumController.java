package cn.proem.dagl.web.fileNum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.annotation.LogService;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileNum.entity.FileNumRule;
import cn.proem.dagl.web.fileNum.service.FileNumService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.webservice.model.ResponseModel;
/**
 * @ClassName fileNumController
 * @Description 档号控制层
 * @author chenxiaofen
 * @date 2017年5月12日
 */
@Controller
@RequestMapping(value = "/w/fileNum")
public class fileNumController extends BaseCtrlModel {
	@Autowired
	private FileNumService fileNumService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private DicManagerService dicManagerService;
	/**
	 * @MethodName fileNumProducePage
	 * @Description 跳转档号生成页面
	 * @author chenxiaofen
	 * @date 2017年5月12日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/fileNumProducePage")
	public ModelAndView fileNumProducePage(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/filenum/fileNum.vm");
		return modelAndView;
	}
	/**
	 * @MethodName addColumn
	 * @Description 添加字段页面
	 * @author chenxiaofen
	 * @date 2017年5月19日
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addColumn")
	public ModelAndView addColumn(HttpServletRequest request) throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/filenum/addColumn.vm");
		return modelAndView;
	}
	/**
	 * @MethodName saveRules 
	 * @Description 保存档案号生成规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param request
	 * @param obj
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/saveRules")
	@ResponseBody
	@LogService(description = "保存档号生成规则")
    public ResponseModel saveRules(HttpServletRequest request,FileNumRule rule) throws ServiceException {
		ResponseModel responseModal = new ResponseModel();
		rule.setSerialNum(0);
		fileNumService.saveRules(rule);
		responseModal.setSuccess(true);
        return responseModal;
    }
	/**
	 * @MethodName getRule 
	 * @Description 获取档号规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param request
	 * @param rule
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getRule")
	@ResponseBody
	@LogService(description = "获取档号生成规则")
    public ResultModel<FileNumRule> getRule(HttpServletRequest request,String type) throws ServiceException {
		ResultModel<FileNumRule> reslutModel = new ResultModel<FileNumRule>();
		reslutModel.setDatas(fileNumService.getRule(type));
		reslutModel.setSuccess(true);
        return reslutModel;
    }
	/**
	 * @MethodName deleteRule
	 * @Description 删除档号规则
	 * @author chenxiaofen
	 * @date 2017年5月13日
	 * @param request
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteRule")
	@ResponseBody
	@LogService(description = "删除档号规则")
    public ResultModel<String> deleteRule(HttpServletRequest request,String id) throws ServiceException {
		ResultModel<String> reslutModel = new ResultModel<String>();
		fileNumService.deleteRule(id);
		reslutModel.setSuccess(true);
        return reslutModel;
    }
	/**
	 * @MethodName getDahByType
	 * @Description 
	 * @author chenxiaofen
	 * @date 2017年5月15日
	 * @param request
	 * @param type 相对应档案类型在字典表中的记录id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getDahByType")
	@ResponseBody
	@LogService(description = "查询完整档号")
    public ResultModel<String> getDahByType(HttpServletRequest request,String type) throws ServiceException {
		ResultModel<String> reslutModel = new ResultModel<String>();
		
		String dhRule = fileNumService.getDahByType(type);
		reslutModel.setData(dhRule);
		reslutModel.setSuccess(true);
        return reslutModel;
    }
	
	@RequestMapping(value = "/getTableField")
	@ResponseBody
	@LogService(description = "获取表字段")
    public ResultModel<BaseEntityInf> getTableField(HttpServletRequest request,String type) throws ServiceException {
		ResultModel<BaseEntityInf> reslutModel = new ResultModel<BaseEntityInf>();
		//获取表名
		String tableName = commonService.findById(type, DictionaryValue.class).getDvno();
		//查询该表所有字段
        List<BaseEntityInf> fieldlst = customArchiveService.getFields(tableName);
		reslutModel.setDatas(fieldlst);
		reslutModel.setSuccess(true);
        return reslutModel;
    }
	/**
	 * @MethodName getTableNameId
	 * @Description 根据表名查询对应id
	 * @author chenxiaofen
	 * @date 2017年6月8日
	 * @param request
	 * @param tableName
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getTableNameId")
	@ResponseBody
	@LogService(description = "查询表对应ID")
    public ResultModel<String> getTableNameId(HttpServletRequest request,String tableName) throws ServiceException {
		ResultModel<String> reslutModel = new ResultModel<String>();
		DictionaryValue dic = dicManagerService.getDicValByDnoAndDvno("DHSC",tableName);
		String id = "";
		if(StringUtil.isNotEmpty(dic)) {
			id = dic.getId();
		}else {
			reslutModel.setSuccess(false);
			reslutModel.setMsg("该表未配置");
		}
		reslutModel.setData(id);
        return reslutModel;
    }
	/**
	 * @MethodName getRuleEntity
	 * @Description 
	 * @author chenxiaofen
	 * @date 2017年6月8日
	 * @param request
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getRuleEntity")
	@ResponseBody
	@LogService(description = "获取档号规则对象集合")
    public ResultModel<FileNumRule> getRuleEntity(HttpServletRequest request,String type) throws ServiceException {
		ResultModel<FileNumRule> reslutModel = new ResultModel<FileNumRule>();
		List<FileNumRule> rule = fileNumService.getRule(type);
		reslutModel.setDatas(rule);
		reslutModel.setSuccess(true);
        return reslutModel;
    }
	/**
	 * 获得档案定义所有字段
	 * @param tablename 表名
	 * @return
	 */
//	private List<BaseEntityInf> getFields(String tablename){
//	    // 获得表的定义
//        String tabledefined = "pdagl_tablename";
//        Map<String, Object> conditions = new HashMap<String, Object>();
//        conditions.put("bm", tablename);
//        BaseEntityInf table = customArchiveService.getEntityByConditions(tabledefined, conditions);
//        // 获得表的所有字段并升序排序
//        conditions = new HashMap<String, Object>();
//        conditions.put("tableNameId", table.get("id"));
//        List<BaseEntityInf> fields = customArchiveService.getEntitiesByConditions("pdagl_tablefield", conditions, new DOrder("xsxh"));
//        return fields;
//	}
}
