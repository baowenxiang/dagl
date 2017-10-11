package cn.proem.dagl.web.condition.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.core.model.Pager;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
//import cn.proem.dagl.web.condition.service.ConditionSearchService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.fileControl.service.FileControlService;
import cn.proem.dagl.web.flow.service.FlowService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.table.dto.Header;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.util.StringUtil;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;

/**
 * 数据中心控制层
 * @author bao
 *
 */
@Controller
@RequestMapping(value = "/w/condition")
public class ConditionSearchController extends BaseCtrlModel{
		@Autowired
		private FileControlService fileControlService;
        @Autowired
        private DicManagerService dicService;
        @Autowired
		private CustomArchiveService customArchiveService;
        @Autowired
		private FlowService flowService;
       // @Autowired
		//private ConditionSearchService conditionSearchService;
		/**
		 * @Description 去导入中心界面
		 * @MethodName toImportCenterView
		 * @author bao
		 * @date 2017年5月10日
		 * @param request
		 * @return
		 * @throws ServiceException ModelAndView
		 */
	 	@RequestMapping(value = "/conditions")
		public ModelAndView toImportCenterView(HttpServletRequest request) throws ServiceException {
			ModelAndView modelAndView = this.createNormalView("/web/condition/conditions.vm");
			
			return modelAndView;
		}
	 	
	 	/**
	     * @Description 进入表格展示页面
	     * @MethodName init
	     * @author tangcc
	     * @date 2017/4/24
	     * @param request
	 	 * @param cwrq 
	     * @return ModelAndView
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/init")
	    public ModelAndView init(HttpServletRequest request)throws ServiceException {
	        ModelAndView modelAndView = this.createNormalView("/web/condition/list.vm");
	        String dh = request.getParameter("dh");
	        String tm = request.getParameter("tm");
	        String cwrq_min = request.getParameter("cwrq_min");
	        String cwrq_max = request.getParameter("cwrq_max");
	        String zrz = request.getParameter("zrz");
	        String dalx = request.getParameter("fileSelect");
	        modelAndView.addObject("tm", tm);
	        modelAndView.addObject("dh", dh);
	        modelAndView.addObject("cwrq_min", cwrq_min);
	        modelAndView.addObject("cwrq_max", cwrq_max);
	        modelAndView.addObject("zrz", zrz);
	        modelAndView.addObject("dalx", dalx);
	        return modelAndView;
	    }
	    
	    /**
	     * @Description 表格赋值
	     * @MethodName getHeader
	     * @author tangcc
	     * @date 2017/4/24
	     * @param request
	     * @return ModelAndView
	     */
	    @ResponseBody
	    @RequestMapping(value = "/getHeader/{tablename}")
	    @LogService(description = "获取表格头部信息")
	    public ResultModel<Header> getHeader(HttpServletRequest request,@PathVariable("tablename") String tablename)throws ServiceException {
	        ResultModel<Header> resultModel = new ResultModel<Header>();
	        
	        // 获得档案定义字段
	        List<BaseEntityInf> fields = customArchiveService.getFields(tablename);

	        List<Header> headers = this.getHeaders(fields);;

	        resultModel.setDatas(headers);
	        
	        return resultModel;
	    
	    }
	    
	    /**
	     * @Description 表格赋值
	     * @MethodName getList
	     * @author tangcc
	     * @date 2017/4/24
	     * @param request
	     * @return ModelAndView
	     */
	    @RequestMapping(value = "/getList", method = RequestMethod.POST)
	    @ResponseBody
	    @LogService(description = "表格赋值")
	    public String getList(String dtGridPager, HttpServletRequest request)throws ServiceException {
	        User user = this.getCurrentUser(request);
	        Pager dataGridQuery = parseToPager(dtGridPager == null ? "" : dtGridPager);
	        Map<String,Object> parameters = new HashMap<String, Object>();
	        Map<String, Object> conditions = dataGridQuery.getParameters();
	        QueryBuilder queryBuilder = new QueryBuilder();
	        queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
	        queryBuilder.addCondition(new QueryCondition("mj","0",ConditionType.EQ,FieldType.STRING,null));
	        queryBuilder.addCondition(new QueryCondition("bm",conditions.get("dalx"),ConditionType.EQ,FieldType.STRING,null));
	        if(StringUtil.isNotEmpty(conditions.get("tm"))){
	        	queryBuilder.addCondition(new QueryCondition("tm",conditions.get("tm"),ConditionType.LIKE,FieldType.STRING,null));
	        }
	        if(StringUtil.isNotEmpty(conditions.get("dh"))){
	        	queryBuilder.addCondition(new QueryCondition("dh",conditions.get("dh"),ConditionType.LIKE,FieldType.STRING,null));
	        }
	        if(StringUtil.isNotEmpty(conditions.get("zrz"))){
	        	queryBuilder.addCondition(new QueryCondition("zrz",conditions.get("zrz"),ConditionType.LIKE,FieldType.STRING,null));
	        }
	        if(StringUtil.isNotEmpty(conditions.get("cwrq_min"))){
	        	queryBuilder.addCondition(new QueryCondition("cwrq",conditions.get("cwrq_min"),ConditionType.GE,FieldType.DATE,null));
	        	
	        }
	        if(StringUtil.isNotEmpty(conditions.get("cwrq_max"))){
	        	queryBuilder.addCondition(new QueryCondition("cwrq",conditions.get("cwrq_max"),ConditionType.LE,FieldType.DATE,null));
	        	
	        }
	        
	        
	        return JSON.toJSONStringWithDateFormat(fileControlService.getOpenedFileDataGrid(queryBuilder, dataGridQuery.getNowPage(), dataGridQuery.getPageSize()),
	                "yyyy-MM-dd HH:mm:ss",
	                SerializerFeature.WriteDateUseDateFormat,
	                SerializerFeature.DisableCircularReferenceDetect);
	    }
	    
	    /**
	     * 获得记录
	     * @param fields 
	     * @param records
	     * @return
	     */
	    private List<HashMap<String, Object>> getDetails(List<BaseEntityInf> fields, List<BaseEntityInf> records ){
	        List<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();
	        for (int i = 0; i < records.size(); i++) {
	            BaseEntityInf record = records.get(i);
	            HashMap<String, Object> detail = new HashMap<String, Object>();
	            for (BaseEntityInf field : fields) {
	                // 列标题编辑
	                String id = field.get("zdywm");
	                // 判断列是否是字典值
	                String did = field.get("did");
	                if(did == null || "".equals(did)){
	                    // 列值编辑
	                    if(record.get(id)!=null){
	                        detail.put(id, record.get(id));
	                    }else{
	                        detail.put(id, "");
	                    }
	                }else{
	                    if("4".equals(field.get("zdlx"))){
	                        String vals = record.get(id);
	                        if(vals!=null){
	                            String rets[] = vals.split(",");
	                            vals = "";
	                            vals = this.getDicTitle(did, rets[0]);
	                            for(int j=1;j<rets.length;j++){
	                                vals += "," + this.getDicTitle(did, rets[j]);
	                            }
	                            detail.put(id, vals);
	                        }else{
	                            detail.put(id,"");
	                        }
	                    }else{
	                        detail.put(id, this.getDicTitle(did, (String) record.get(id)));
	                    }
	                }
	                
	            }
	            // 获得uuid
	            String uuid = record.get("uuid");
	            detail.put("uuid", uuid);
	            detail.put("id", record.get("id"));
	            // 获得原文
	            String dataid = record.get("dataid");
	            detail.put("dataid", dataid);
	            // 获得状态
	            String  isarchive = record.get("isarchive");
	            detail.put("isarchive", isarchive);
	            details.add(detail);
	        }
	        return details;
	    }

	    /**
	     * 获得记录标题
	     * @param fields
	     * @return
	     */
	    private List<Header> getHeaders(List<BaseEntityInf> fields){
	        List<Header> headers = new ArrayList<Header>();
	        //uuid
	        Header header = new Header();
	        header.setHide(true);
	        header.setId("uuid");
	        header.setTitle("uuid");
	        headers.add(header);
	        
	        // 列标题编辑
	        for (BaseEntityInf field : fields) {
	            header = new Header();
	            header.setTitle((String) field.get("zdzwm"));
	            header.setId((String) field.get("zdywm"));
	            header.setType("string");
	            header.setColumnClass("text-center");
	            // 是否信息显示列判断
	            if("0".equals((String)field.get("sfgyxxx"))){
	                header.setHide(true);
	            }else{
	                header.setHide(false);
	            }
	            headers.add(header);
	        }
	      
	       /* //原文
	        header = new Header();
	        header.setHide(false);
	        header.setId("dataid");
	        header.setTitle("挂接原文");
	        header.setType("string");
	        header.setColumnClass("text-center");
	        headers.add(header);*/
	        
	        //状态值
	        header = new Header();
	        header.setHide(true);
	        header.setId("isarchive");
	        header.setTitle("状态");
	        header.setType("string");
	        header.setColumnClass("text-center");
	        headers.add(header);
	        return headers;
	    }
	    
	    /**
	     * 获得字典标题值
	     * @param did
	     * @param val
	     * @return
	     */
	    private String getDicTitle(String did, String val){
	        List<DictionaryValue> dictionaryValues = dicService
	                .getDicValueList(did);
	        for(DictionaryValue dic : dictionaryValues){
	            // 获得字典内容
	            if(dic.getDvno().equals(val)){
	                return dic.getDvalue();
	            }
	        }
	        return null;
	    }
	    /**
	     * 将字符串转换为查询对象
	     * 
	     * @param dtGridPager
	     *            查询字符串
	     * @return DataGridQuery
	     */
	    private Pager parseToPager(String dtGridPager)
	    {
	        return JSON.toJavaObject(JSON.parseObject(dtGridPager), Pager.class);
	    }	
	    
	 	@RequestMapping(value = "/getYwgj")
	  	@ResponseBody
	  	@LogService(description = "根据唯一标识ID获取原文信息")
	    public ResultModel<Ywgj> getYwgj(HttpServletRequest request,@RequestBody Map<String, String> obj) throws ServiceException {
	  		ResultModel<Ywgj> resultModel = new ResultModel<Ywgj>();
	  			
	  		List<Ywgj> ywgjs = flowService.getFileBydataId(obj.get("id"));
	  		
	  		resultModel.setDatas(ywgjs);
	  		return resultModel;
	  	}
	 
//	 	@RequestMapping(value = "/getYwgj")
//	  	@ResponseBody
//	  	@LogService(description = "根据唯一标识ID获取原文信息")
//	    public ResultModel<Ywgj> getYwgj(HttpServletRequest request,@RequestBody Map<String, String> obj) throws ServiceException {
//	  		ResultModel<Ywgj> resultModel = new ResultModel<Ywgj>();
//	  			
//	  		List<Ywgj> ywgjs = conditionSearchService.getFileBydataId(obj.get("id"));
//	  		
//	  		resultModel.setDatas(ywgjs);
//	  		return resultModel;
//	  	}
}
