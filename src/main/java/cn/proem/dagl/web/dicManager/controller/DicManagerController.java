package cn.proem.dagl.web.dicManager.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.dicManager.dto.DtoDicValue;
import cn.proem.dagl.web.dicManager.entity.Dictionary;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping("/w/dicmanager")
public class DicManagerController extends BaseCtrlModel {
	
	@Autowired
	private DicManagerService dicManagerService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/init")
	public ModelAndView initMessagePage(){
		ModelAndView view = this.createNormalView("/web/dicManager/init.vm");
		
		return view;
		
	}
	
	/**
	 * 查询字典项列表
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionaryList")
	@ResponseBody
	@LogService(description = "查询字典项列表")
	public String getDictionaryList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<Dictionary> dataGrid = dicManagerService.getDictionaryDateGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 保存，修改消息
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateDictionary")
	@ResponseBody
	@LogService(description = "保存或修改字典项")
	public ResultModel<String> updateDictionary(HttpServletRequest request) throws ParseException{
		ResultModel<String> resultModel = new ResultModel<String>();
		Dictionary dictionary = new Dictionary();
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			dictionary.setId(id);
		}
		dictionary.setDelFlag(0);
		dictionary.setDno(request.getParameter("dno"));
		dictionary.setDname(request.getParameter("dname"));
		dictionary.setDdescr(request.getParameter("ddescr"));
		
		try {
			dicManagerService.saveOrUpdateDictionary(dictionary);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			resultModel.setSuccess(false);
			resultModel.setMsg("修改失败");
			e.printStackTrace();
		}
		return resultModel;
	}
	
	/**
	 * 获取字典项详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDictionaryById")
	@ResponseBody
	@LogService(description = "获取字典项详情")
	public ResultModel<Dictionary> getDictionaryById(String id){
		ResultModel<Dictionary> rm = new ResultModel<Dictionary>();
		Dictionary dictionary = commonService.findById(id, Dictionary.class);
		rm.setData(dictionary);
		return rm;
	}
	
	/**
	 * 删除字典项
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDictionary")
	@ResponseBody
	@LogService(description = "删除字典项")
	public ResultModel<String> deleteDictionaryById(String id){
		ResultModel<String> resultModel = new ResultModel<String>();
		if(StringUtil.isNotEmpty(id)){
			try {
				dicManagerService.deleteDictionaryById(id);
			} catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("删除失败");
				e.printStackTrace();
			}
		}
		
		return resultModel;
	}
	
	//-------------------------------------字典值----------------------------------------------------
	/**
	 * 查询字典值列表
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDicValueList")
	@ResponseBody
	@LogService(description = "查询字典值列表")
	public String getDicValueList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		Map<String, Object> map = query.getParameters();
		if(map!=null && map.size()>0){
			String id = (String)map.get("id");
			queryBuilder.addCondition(new QueryCondition("id",id,ConditionType.EQ,FieldType.STRING,"dictionary"));
		}
		
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<DictionaryValue> dataGrid = dicManagerService.getDictionaryValueDateGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 保存，修改字段值
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateDicValue")
	@ResponseBody
	@LogService(description = "保存或修改字典值")
	public ResultModel<String> updateDicValue(HttpServletRequest request) throws ParseException{
		ResultModel<String> resultModel = new ResultModel<String>();
		DictionaryValue dicValue = new DictionaryValue();
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			dicValue.setId(id);
		}
		dicValue.setDelFlag(0);
		dicValue.setDvalue(request.getParameter("dvalue"));
		dicValue.setDvno(request.getParameter("dvno"));
		dicValue.setXsxh(request.getParameter("xsxh"));
		String did = request.getParameter("did");
		Dictionary dictionary = dicManagerService.getDictionaryByDno(did);
		if(dictionary!=null){
			dicValue.setDictionary(dictionary);
			
			try {
				dicManagerService.saveOrUpdateDicValue(dicValue);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				resultModel.setSuccess(false);
				resultModel.setMsg("修改失败");
				e.printStackTrace();
			}
		}else{
			resultModel.setMsg("字典项编号有误");
		}
			
		
		return resultModel;
	}
	
	/**
	 * 获取字典项详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDicValueById")
	@ResponseBody
	@LogService(description = "获取字典值详情")
	public ResultModel<DictionaryValue> getDicValueById(String id){
		ResultModel<DictionaryValue> rm = new ResultModel<DictionaryValue>();
		DictionaryValue dicValue = commonService.findById(id, DictionaryValue.class);
		rm.setData(dicValue);
		return rm;
	}
	
	/**
	 * 删除字典值
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDicValue")
	@ResponseBody
	@LogService(description = "删除字典值")
	public ResultModel<String> deleteDicValueById(String id){
		ResultModel<String> resultModel = new ResultModel<String>();
		if(StringUtil.isNotEmpty(id)){
			try {
				dicManagerService.deleteDicValueById(id);
			} catch (ServiceException e) {
				resultModel.setSuccess(false);
				resultModel.setMsg("删除失败");
				e.printStackTrace();
			}
		}
		
		return resultModel;
	}
	
	
	/**
	 * 根据字典项编号获取字典值列表
	 * @return
	 */
	@RequestMapping("/getDicValueListByDno")
	@ResponseBody
	@LogService(description = "根据字典项编号获取字典值列表")
	public List<DtoDicValue> getDicValueList(String dno){
		List<DtoDicValue> dvList = new ArrayList<DtoDicValue>();
		List<DictionaryValue> list = dicManagerService.getDicValueList(dno);
		for(DictionaryValue dv:list){
			DtoDicValue ddv = new DtoDicValue();
			ddv.setDvno(dv.getDvno());
			ddv.setDvalue(dv.getDvalue());
			ddv.setXsxh(dv.getXsxh());
			dvList.add(ddv);
		}
		return dvList;
	}
	
	
	/**
	 * 获得字典标题值
	 * @param did
	 * @param val
	 * @return
	 */
	@RequestMapping("/getDicValueByDno")
	@ResponseBody
	@LogService(description = "获取字典标题值")
	public ResultModel<String> getDicTitle(String dno, String dvno){
		ResultModel<String> resultModel = new ResultModel<String>();
	    List<DictionaryValue> dictionaryValues = dicManagerService.getDicValueList(dno);
	    for(DictionaryValue dic : dictionaryValues){
	        // 获得字典内容
	        if(dic.getDvno().equals(dvno)){
	             resultModel.setData(dic.getDvalue());
	             return resultModel;
	        }
	    }
	    resultModel.setData("");
	    return resultModel;
	}

}
