package cn.proem.dagl.web.tj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.dagl.web.tj.dto.DesignDto;
import cn.proem.dagl.web.tj.dto.GCLdto;
import cn.proem.dagl.web.tj.dto.SeriesDto;
import cn.proem.dagl.web.tj.dto.CheckDto;
import cn.proem.dagl.web.tj.entity.GclOrder;
import cn.proem.dagl.web.tj.service.TJService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.service.CommonService;

@Controller
@RequestMapping(value = "/w/tj")
public class TJController extends BaseCtrlModel {
    @Autowired
    private TJService tjservice;
    @Autowired
    private CommonService commonService;
    
    /**
     * @Method: dainit 
     * @Description: 实体借阅初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/stjy")
    public ModelAndView stjyinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/stjy.vm");
        String company = this.getCompany(request);

        // 借阅时间
        List<CheckDto> jysjs = tjservice.designJYjysj(company);
        // 借阅效果
        List<CheckDto> jyxgs = tjservice.designJYjyxg(company);
        // 借阅目的
        List<CheckDto> jymds = tjservice.designJYjymd(company);
        
        modelAndView.addObject("jysjs", jysjs);
        modelAndView.addObject("jyxgs", jyxgs);
        modelAndView.addObject("jymds", jymds);
        
        return modelAndView;
    }
    
    /**
     * @Method: dainit 
     * @Description: 电子借阅初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/dzjy")
    public ModelAndView dzjyinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/dzjy.vm");
        String company = this.getCompany(request);
        // 借阅时间
        List<CheckDto> jysjs = tjservice.designDZJYjysj(company);
        // 借阅效果
        List<CheckDto> jyxgs = tjservice.designDZJYjyxg(company);
        // 借阅目的
        List<CheckDto> jymds = tjservice.designDZJYjymd(company);
        // 门类
        List<CheckDto> mls = tjservice.designDZJYMl(company);
        
        modelAndView.addObject("jysjs", jysjs);
        modelAndView.addObject("jyxgs", jyxgs);
        modelAndView.addObject("jymds", jymds);
        modelAndView.addObject("mls", mls);
        
        return modelAndView;
    }
    
    /**
     * @Method: jymdreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/dzjymd/report")
    @ResponseBody
    public DesignDto dzjymdreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="ml[]", required=false) List<String> ml,
                               @RequestParam(value="jymd[]", required=false) List<String> jymd) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjDZJymd(from, to, ml, jymd, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: jyxgreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/dzjyxg/report")
    @ResponseBody
    public DesignDto dzjyxgreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="ml[]", required=false) List<String> ml,
                               @RequestParam(value="jyxg[]", required=false) List<String> jyxg) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjDZJyxg(from, to, ml, jyxg, company);
       designDto.setReport(report);
       return designDto;
    }
    
    
    /**
     * @Method: jymdreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/jymd/report")
    @ResponseBody
    public DesignDto jymdreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="jymd[]", required=false) List<String> jymd) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjJymd(from, to, jymd, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: jyxgreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/jyxg/report")
    @ResponseBody
    public DesignDto jyxgreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="jyxg[]", required=false) List<String> jyxg) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjJyxg(from, to, jyxg, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: jdinit 
     * @Description: 鉴定页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/jd")
    public ModelAndView jdinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/jd.vm");
        String company = this.getCompany(request);
        List<CheckDto> mls = tjservice.designJDMl(company);
        List<CheckDto> nds = tjservice.designJDNd(company);
        List<CheckDto> nrs = tjservice.designJDNr(company);
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        modelAndView.addObject("nrs", nrs);
        return modelAndView;
    }
    
    /**
     * @Method: dainit 
     * @Description: 鉴定页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/bgqx")
    public ModelAndView bgqxinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/bgqx.vm");
        String company = this.getCompany(request);
        // 门类
        List<CheckDto> mls = tjservice.designDAMl(company);
        // 年度
        List<CheckDto> nds = tjservice.designDANd(company);
        // 保管期限
        List<CheckDto> bgqxs = tjservice.designDABgqx(company);
        // 密级
        List<CheckDto> mjs = tjservice.designDAMj(company);
        
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        modelAndView.addObject("bgqxs", bgqxs);
        modelAndView.addObject("mjs", mjs);
        
        return modelAndView;
    }
    
    /**
     * @Method: dainit 
     * @Description: 鉴定页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/ml")
    public ModelAndView mlinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/ml.vm");
        String company = this.getCompany(request);
        // 门类
        List<CheckDto> mls = tjservice.designDAMl(company);
        // 年度
        List<CheckDto> nds = tjservice.designDANd(company);
        // 保管期限
        List<CheckDto> bgqxs = tjservice.designDABgqx(company);
        // 密级
        List<CheckDto> mjs = tjservice.designDAMj(company);
        
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        modelAndView.addObject("bgqxs", bgqxs);
        modelAndView.addObject("mjs", mjs);
        
        return modelAndView;
    }
    
    /**
     * @Method: dainit 
     * @Description: 鉴定页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/mj")
    public ModelAndView mjinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/mj.vm");
        String company = this.getCompany(request);
        // 门类
        List<CheckDto> mls = tjservice.designDAMl(company);
        // 年度
        List<CheckDto> nds = tjservice.designDANd(company);
        // 保管期限
        List<CheckDto> bgqxs = tjservice.designDABgqx(company);
        // 密级
        List<CheckDto> mjs = tjservice.designDAMj(company);
        
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        modelAndView.addObject("bgqxs", bgqxs);
        modelAndView.addObject("mjs", mjs);
        
        return modelAndView;
    }
    
    /**
     * @Method: dainit 
     * @Description: 鉴定页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/nd")
    public ModelAndView ndinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/nd.vm");
        String company = this.getCompany(request);
        // 门类
        List<CheckDto> mls = tjservice.designDAMl(company);
        // 年度
        List<CheckDto> nds = tjservice.designDANd(company);
        // 保管期限
        List<CheckDto> bgqxs = tjservice.designDABgqx(company);
        // 密级
        List<CheckDto> mjs = tjservice.designDAMj(company);
        
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        modelAndView.addObject("bgqxs", bgqxs);
        modelAndView.addObject("mjs", mjs);
        
        return modelAndView;
    }
    
    /**
     * @Method: dareport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/da/report")
    @ResponseBody
    public DesignDto dareport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls,
                               @RequestParam(value="bgqxs[]", required=false) List<String> bgqxs,
                               @RequestParam(value="mjs[]", required=false) List<String> mjs) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjDa(from, to, mls, mjs, bgqxs, company);
       designDto.setReport(report);
       return designDto;
    }
    
    
    /**
     * @Method: damlreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/ml/report")
    @ResponseBody
    public DesignDto damlreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjDaMl(from, to, mls, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: damlreport 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/nd/report")
    @ResponseBody
    public DesignDto ndreport(HttpServletRequest request, 
                               @RequestParam(required=false) String from,
                               @RequestParam(required=false) String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls) throws ServiceException{
       DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       List<Map<String, Object>> report = tjservice.tjDaNd(from, to, mls, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: hkinit 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/init/hk")
    public ModelAndView hkinit(HttpServletRequest request) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/hk.vm");
        String company = this.getCompany(request);
        List<CheckDto> mls = tjservice.designHKMl(company);
        List<CheckDto> nds = tjservice.designHKNd(company);
        modelAndView.addObject("mls", mls);
        modelAndView.addObject("nds", nds);
        return modelAndView;
    }
    
    /**
     * @Method: hkinit 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/jd/report")
    @ResponseBody
    public DesignDto jdreport(HttpServletRequest request, 
                               @RequestParam String from,
                               @RequestParam String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls,
                               @RequestParam(value="hk", required=false) String hk) throws ServiceException{
        DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       if("hk_all".equals(hk)){
           hk = null;
       }
       List<Map<String, Object>> report = tjservice.tjJD(from, to, mls, hk, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: hkinit 
     * @Description: 划空页面初始化
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/hk/report")
    @ResponseBody
    public DesignDto hkreport(HttpServletRequest request, 
                               @RequestParam String from,
                               @RequestParam String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls,
                               @RequestParam(value="hk", required=false) String hk) throws ServiceException{
        DesignDto designDto = new DesignDto();
       String company = this.getCompany(request);
       if("hk_all".equals(hk)){
           hk = null;
       }
       List<Map<String, Object>> report = tjservice.tjHK(from, to, mls, hk, company);
       designDto.setReport(report);
       return designDto;
    }
    
    /**
     * @Method: init 
     * @Description: 室藏量初始化页面
     * @return
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request){
        ModelAndView modelAndView = this.createNormalView("/web/tj/gcl.vm");
        return modelAndView;
    }
    
    /**
     * @Method: order 
     * @Description: 保存修改后的排序
     * @param request
     * @param ord
     * @param type
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/order")
    public ModelAndView order(HttpServletRequest request, @RequestParam("ord[]") List<String> ord, @RequestParam("type") String type) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/gcl.vm");
        User user = this.getCurrentUser(request);
        String company = "";
        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
        if(userdepartment != null){
            company = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
            tjservice.tjGclSaveOrUpdate(ord, type, company);
        }
        return modelAndView;
    }
    
    /**
     * @Method: gcl 
     * @Description: 室藏量统计
     * @param request
     * @param type
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/gcl")
    @ResponseBody
    public GCLdto gcl(HttpServletRequest request, @RequestParam String type) throws ServiceException{
        GCLdto gcl = new GCLdto();
        User user = this.getCurrentUser(request);
        String department = "";
        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
        if(userdepartment != null){
            department = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
        }
        List<Map<String, Object>> gclwjlist = null;
        List<Map<String, Object>> gclajlist = null;
        List<Map<String, Object>> ndhlist = null;
        List<GclOrder> gclordlist = null;
        if("all".equals(type)){
            gclwjlist = tjservice.tjWjGcl(department);
            gclajlist = tjservice.tjAjGcl(department);
            ndhlist = tjservice.tjNdhArea(department);
        }else{
            gclwjlist = tjservice.tjWjGclA(department);
            gclajlist = tjservice.tjAjGclA(department);
            ndhlist = tjservice.tjNdhAreaA(department);
        }
        gclordlist = tjservice.tjGclFind(type, department);
        List<String> ord = new ArrayList<String>();
        for(GclOrder gclord : gclordlist){
            ord.add(gclord.getYwm());
        }
        if(gclwjlist != null && gclwjlist.size() > 0) gcl.setGclwj(gclwjlist);
        if(gclajlist != null && gclajlist.size() > 0) gcl.setGclaj(gclajlist);
        if(ndhlist != null && ndhlist.size() > 0) gcl.setNdhArea(ndhlist);
        if(ord != null && ndhlist.size() > 0) gcl.setOrd(ord);
        return gcl;
    }
    
    
    /**
     * @Method: init 
     * @Description: 自定义统计
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/design/report")
    @ResponseBody
    public DesignDto report(HttpServletRequest request, 
                               @RequestParam String from,
                               @RequestParam String to,
                               @RequestParam(value="mls[]", required=false) List<String> mls,
                               @RequestParam(value="bgqxs[]", required=false) List<String> bgqxs,
                               @RequestParam(value="mjs[]", required=false) List<String> mjs) throws ServiceException{
        DesignDto designDto = new DesignDto();
        String company = this.getCompany(request);
        
        if(company != null){
            List<Map<String, Object>> report = tjservice.designDalxReport(from, to, company, mls, bgqxs, mjs);
            List<SeriesDto> series = tjservice.designDalxPic(from, to, company, mls, bgqxs, mjs);
            
            designDto.setReport(report);
            designDto.setPic(series);
        }
        
        return designDto;
    }
    
    /**
     * @Method: init 
     * @Description: 自定义统计
     * @return
     * @throws ServiceException 
     */
    @RequestMapping(value = "/design")
    public ModelAndView design(HttpServletRequest request ) throws ServiceException{
        ModelAndView modelAndView = this.createNormalView("/web/tj/design.vm");
        User user = this.getCurrentUser(request);
        String company = "";
        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
        if(userdepartment != null){
            company = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
        }
        
        // 门类
        List<CheckDto> ml = tjservice.designMl(company);
        // 年度
        List<String> nd = tjservice.designNd(company);
        // 保管期限
        List<CheckDto> bgqx = tjservice.designBgqx(company);
        // 密级
        List<CheckDto> mj = tjservice.designMj(company);
        modelAndView.addObject("mls", ml);
        modelAndView.addObject("bgqxs", bgqx);
        modelAndView.addObject("mjs", mj);
        modelAndView.addObject("nds", nd);
        return modelAndView;
    }
    
    private String getCompany(HttpServletRequest request) throws ServiceException{
        User user = this.getCurrentUser(request);
        String company = null;
        UserDepartment userdepartment = commonService.findUserDepartmentByUserId(user.getId());
        if(userdepartment != null){
            company = userdepartment.getDepartment() != null ? userdepartment.getDepartment().getId() : "";
        }
        return company;
    }


/**
 * @Method: dainit 
 * @Description: 鉴定页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/init/dzfj")
public ModelAndView dzfjinit(HttpServletRequest request) throws ServiceException{
    ModelAndView modelAndView = this.createNormalView("/web/tj/dzfj.vm");
    String company = this.getCompany(request);
    // 门类
    List<CheckDto> mls = tjservice.designDZFJMl(company);
    // 年度
    List<CheckDto> nds = tjservice.designDZFJNd(company);

    
    modelAndView.addObject("mls", mls);
    modelAndView.addObject("nds", nds);

    
    return modelAndView;
}

/**
 * @Method: damlreport 
 * @Description: 划空页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/dzfj/report")
@ResponseBody
public DesignDto dzfjreport(HttpServletRequest request, 
                           @RequestParam(required=false) String from,
                           @RequestParam(required=false) String to,
                           @RequestParam(value="mls[]", required=false) List<String> mls) throws ServiceException{
   DesignDto designDto = new DesignDto();
   String company = this.getCompany(request);
   List<Map<String, Object>> report = tjservice.tjDzfj(from, to, mls, company);
   designDto.setReport(report);
   return designDto;
}


/**
 * @Method: dainit 
 * @Description: 鉴定页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/init/gjfj")
public ModelAndView gjfjinit(HttpServletRequest request) throws ServiceException{
    ModelAndView modelAndView = this.createNormalView("/web/tj/gjfj.vm");
    String company = this.getCompany(request);
    // 门类
    List<CheckDto> mls = tjservice.designGJFJMl(company);
    // 年度
    List<CheckDto> nds = tjservice.designGJFJNd(company);
    
    modelAndView.addObject("mls", mls);
    modelAndView.addObject("nds", nds);
    
    return modelAndView;
}

/**
 * @Method: damlreport 
 * @Description: 划空页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/gjfj/report")
@ResponseBody
public DesignDto gjfjreport(HttpServletRequest request, 
                           @RequestParam(required=false) String from,
                           @RequestParam(required=false) String to,
                           @RequestParam(value="mls[]", required=false) List<String> mls) throws ServiceException{
   DesignDto designDto = new DesignDto();
   String company = this.getCompany(request);
   List<Map<String, Object>> report = tjservice.tjGJFJ(from, to, mls, company);
   designDto.setReport(report);
   return designDto;
}



/**
 * @Method: dainit 
 * @Description: 鉴定页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/init/dzwj")
public ModelAndView dzwjinit(HttpServletRequest request) throws ServiceException{
    ModelAndView modelAndView = this.createNormalView("/web/tj/dzwj.vm");
    String company = this.getCompany(request);
    // 门类
    List<CheckDto> mls = tjservice.designDZWJMl(company);
    // 年度
    List<CheckDto> nds = tjservice.designDZWJNd(company);
    
    modelAndView.addObject("mls", mls);
    modelAndView.addObject("nds", nds);

    return modelAndView;
}

/**
 * @Method: damlreport 
 * @Description: 划空页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/dzwj/report")
@ResponseBody
public DesignDto dzwjreport(HttpServletRequest request, 
                           @RequestParam(required=false) String from,
                           @RequestParam(required=false) String to,
                           @RequestParam(value="mls[]", required=false) List<String> mls) throws ServiceException{
   DesignDto designDto = new DesignDto();
   String company = this.getCompany(request);
   List<Map<String, Object>> report = tjservice.tjDZWJ(from, to, mls, company);
   designDto.setReport(report);
   return designDto;
}


/**
 * @Method: eepinit 
 * @Description: eep页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/init/eep")
public ModelAndView eepinit(HttpServletRequest request) throws ServiceException{
    ModelAndView modelAndView = this.createNormalView("/web/tj/eep.vm");
    String company = this.getCompany(request);
    // 门类
    List<CheckDto> mls = tjservice.designEEPMl(company);
    
    modelAndView.addObject("mls", mls);

    return modelAndView;
}

/**
 * @Method: eepreport 
 * @Description: eep页面初始化
 * @return
 * @throws ServiceException 
 */
@RequestMapping(value = "/eep/report")
@ResponseBody
public DesignDto eepreport(HttpServletRequest request, 
                           @RequestParam(value="mls[]",required=false) List<String> mls,
                           @RequestParam(value="fb" ,required=false) String state
                           ) throws ServiceException{
   DesignDto designDto = new DesignDto();
   String company = this.getCompany(request);
   List<Map<String, Object>> report = tjservice.tjEEP(mls, company, state);
   designDto.setReport(report);
   return designDto;
}

}
