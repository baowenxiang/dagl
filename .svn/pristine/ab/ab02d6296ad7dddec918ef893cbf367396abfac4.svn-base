package cn.proem.dagl.web.preArchive.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.archives.entity.DField;
import cn.proem.dagl.web.archives.service.CustomArchiveService;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.dagl.web.oaservice.entity.TGdwjObj;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.preArchive.service.OAfjService;
import cn.proem.dagl.web.preArchive.service.ZlsjOAService;
import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;

/**
 * @ClassName ZlsjOAController
 * @Description OA资料预归档
 * @author poole
 * @date 2017年8月22日
 */
@Controller
@RequestMapping(value = "/w/zlzl/oazlsj")

public class ZlsjOAController extends BaseCtrlModel {
    
	@Autowired
	private ZlsjOAService zlsjOAService;
    @Autowired
    private OAfjService oafjService;
    @Autowired
    private DicManagerService dicManagerService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CustomArchiveService customArchiveService;
	
    /**
     * @Method: initView 
     * @Description: OA显示列表初始化
     * @param request
     * @return
     */
	@RequestMapping(value = "/initView0")
	public ModelAndView initView0(HttpServletRequest request) {
		ModelAndView modelAndView = this.createNormalView("/web/zlzl/zlsjoa/dataCollect0.vm");
		return modelAndView;
	}
	
	/**
     * @Method: initView 
     * @Description: OA显示列表初始化
     * @param request
     * @return
     */
    @RequestMapping(value = "/initView1")
    public ModelAndView initView1(HttpServletRequest request) {
        ModelAndView modelAndView = this.createNormalView("/web/zlzl/zlsjoa/dataCollect1.vm");
        return modelAndView;
    }
	
	/**
	 * @Method: dataCollectView 
	 * @Description: 资料整理页面
	 * @param request
	 * @param dataCollectId
	 * @return
	 */
    @RequestMapping(value = "/dataCollectView")
    public ModelAndView dataCollectView(HttpServletRequest request,String dataCollectId) {
        ModelAndView modelAndView = this.createNormalView("/web/zlzl/zlsjoa/dataCollectView.vm");
        modelAndView.addObject("dataCollectId", dataCollectId);
        return modelAndView;
    }
    
    /**
     * @Description 获得档案类型
     * @MethodName getFileTypeByDicno
     * @param request
     * @param dno
     * @return ResultModel<DictionaryValue>
     */
    @RequestMapping(value = "/getFileTypeByDicno")
    @ResponseBody
    public ResultModel<DictionaryValue> getFileTypeByDicno(HttpServletRequest request,String dno){
        ResultModel<DictionaryValue> resultModel = new ResultModel<DictionaryValue>();
        List<DictionaryValue> dictionaryValues = dicManagerService.getDicValueList(dno);
        resultModel.setDatas(dictionaryValues);
        return resultModel;
    }
    
	/**
	 * @Method: getDataList 
	 * @Description: 获得OA目录列表
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getZlsjList0")
	@ResponseBody
	public String getDataListFLH0(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		queryBuilder.addCondition(new QueryCondition("flh", "0", ConditionType.EQ, FieldType.STRING, null));
		return JSON.toJSONStringWithDateFormat(zlsjOAService.getZlsjOADataGrid(
				queryBuilder, query.getNowPage(), query.getPageSize()),
				"yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
     * @Method: getDataList 
     * @Description: 获得OA目录列表
     * @param dtGridPager
     * @param request
     * @return
     */
    @RequestMapping(value = "/getZlsjList1")
    @ResponseBody
    public String getDataListFLH1(String dtGridPager, HttpServletRequest request){
        DataGridQuery query = parseToQuery(dtGridPager == null ? "" : dtGridPager);
        QueryBuilder queryBuilder = query.generateQueryBuilder();
        queryBuilder.addCondition(new QueryCondition("flh", "1", ConditionType.EQ, FieldType.STRING, null));
        return JSON.toJSONStringWithDateFormat(zlsjOAService.getZlsjOADataGrid(
                queryBuilder, query.getNowPage(), query.getPageSize()),
                "yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
    }
	
	/**
     * @Description 资料转换成预归档
     * @MethodName dataCollectToPreArchive
     * @author bao
     * @date 2017年5月10日
     * @param request
     * @param obj
     * @return ResultModel<String>
     */
    @RequestMapping(value = "/dataCollectToPreArchive")
    @ResponseBody
    public ResultModel<String> dataCollectToPreArchive(HttpServletRequest request, @RequestBody Map<String, Object> obj){
        ResultModel<String> resultModel = new ResultModel<String>();
        User user = this.getCurrentUser(request);
        
        try {
            @SuppressWarnings("unchecked")
            List<String> dataCollectIds = (List<String>)obj.get("dataCollectIds");
            String tablename = (String)obj.get("tablename");
            
            for(String dataId : dataCollectIds){
                TGdwjObj zlsj = commonService.findById(dataId, TGdwjObj.class);
                
                BaseEntityInf entity = customArchiveService.getEntity(tablename);
                
                List<BaseEntityInf> fields = customArchiveService.getFields(tablename);
                 // UUID 资料id
                entity.set(DField.UUID, zlsj.getId());
                //设置集团号
                entity.set(DField.NOWCAMP, getDepartment(user));
                //设置新建时间
                entity.set(DField.CREATETIME, nowTime());
                //设置默认的归档状态
                entity.set(DField.CREATEUSER, user.getId());
                //设置默认删除标识
                entity.set(DField.DELFLAG, "0");
                //设置默认的归档状态
                entity.set(DField.ISARCHIVE,"0");
                
                for(BaseEntityInf field : fields){
                    if("TM".equalsIgnoreCase((String) field.get("zdywm"))){
                        entity.set("TM", zlsj.getTm());
                    }
                    if("ZRZ".equalsIgnoreCase((String) field.get("zdywm"))){
                        entity.set("ZRZ", zlsj.getZrz());
                    }
                    if("CWRQ".equalsIgnoreCase((String) field.get("zdywm"))){
                        entity.set("CWRQ", zlsj.getCwrq());
                    }
                    if("WH".equalsIgnoreCase((String) field.get("zdywm"))){
                        entity.set("WH", zlsj.getWh());
                    }
                    if("FLH".equalsIgnoreCase((String) field.get("zdywm"))){
                        entity.set("FLH", zlsj.getFlh());
                    }
                }
                customArchiveService.save(entity);
                zlsjOAService.deleteZlsj(dataId);
            }
        
        }catch (ServiceException e) {
            resultModel.setSuccess(false);
            resultModel.setMsg(e.getMessage());
        } catch (Exception e) {
            resultModel.setSuccess(false);
            resultModel.setMsg("保存失败");
        }
        return resultModel;
    }
	
	/**
	 * @Method: getYwgj 
	 * @Description: 获得OA数据挂接原文
	 * @param request
	 * @param obj
	 * @return
	 * @throws ServiceException
	 */
    @RequestMapping(value = "/Oafj")
    @ResponseBody
    public ResultModel<Ywgj> getYwgj(HttpServletRequest request,@RequestBody Map<String, String> obj) throws ServiceException {
        ResultModel<Ywgj> resultModel = new ResultModel<Ywgj>();
        List<Ywgj> ywgjs = oafjService.getFileBydataId(obj.get("id"));
        resultModel.setDatas(ywgjs);
        return resultModel;
    }
    
    private String getDepartment(User user) throws ServiceException{
        // 用户部门
        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
        if(userdepartment != null){
            // 部门
            String department = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
            return department;
        }
        return "";
    }
    
    private String nowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
}

	




