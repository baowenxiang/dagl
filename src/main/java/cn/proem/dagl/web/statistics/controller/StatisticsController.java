package cn.proem.dagl.web.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.proem.core.annotation.LogService;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.statistics.service.StatisticsService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;

@Controller
@RequestMapping(value = "/w/example/statistics")
public class StatisticsController extends BaseCtrlModel{
	@Autowired
	private DicManagerService dicService;
	@Autowired
	private CustomArchiveService customArchiveService;
	@Autowired
	private StatisticsService statisticsService;
	/**
	 * 入口
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/start")
	public ModelAndView init(HttpServletRequest request)
			throws ServiceException {
		ModelAndView modelAndView = this.createNormalView("/web/statistics/statistics.vm");
		// 取得字典项目
		java.util.List<DictionaryValue> tablename = dicService
				.getDicValueList("DALX");
		System.out.println(JSON.toJSON(tablename));
		modelAndView.addObject("tablename", tablename);
		return modelAndView;
	}
	
	/**
	 * 通过表名获取字段名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getdalx/{tablename}")
	@LogService(description = "通过表名获取字段名")
	public Map<String,String> getDalx(@PathVariable("tablename") String tablename)
			throws ServiceException {
		 // 获得档案定义字段
		java.util.List<BaseEntityInf> dazd= customArchiveService.getFields(tablename);
		Map<String,String> map=new HashMap<String,String>(0);
		for (BaseEntityInf b : dazd) {
			map.put((String)b.get("zdywm"), (String)b.get("zdzwm"));
		}
		return map;
	}
	
	/**
	 * 获取图标类型
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "/tbType")
	@LogService(description = "获取图标类型")
	public Map<String,String> getTbType(HttpServletRequest request)
			throws ServiceException {
		// 取得字典项目 
		List<DictionaryValue> zd=dicService.getDicValueList("txlb");
		Map<String,String> map=new HashMap<String,String>(0);
		for (DictionaryValue d : zd) {
			map.put(d.getDvno(), d.getDvalue());
		}
		return map;
		 
	}
	
	@ResponseBody
	@RequestMapping(value = "/createCharts")
	@LogService(description = "创建图表")
	public List<Map<String, String>> createCharts(String tablename,String cols){
		System.out.println(JSON.toJSON(statisticsService.findParams(tablename, cols)));
		return statisticsService.findParams(tablename, cols);
	}
}
