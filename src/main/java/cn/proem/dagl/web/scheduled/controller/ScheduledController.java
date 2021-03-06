package cn.proem.dagl.web.scheduled.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.core.model.Pager;
import cn.proem.dagl.web.oaservice.service.OagdService;
import cn.proem.dagl.web.scheduled.dto.Task;
import cn.proem.dagl.web.scheduled.dto.TaskList;
import cn.proem.dagl.web.scheduled.entity.ScheduledTask;
import cn.proem.dagl.web.scheduled.task.HttpTask;
import cn.proem.dagl.web.scheduled.task.OAThread;
import cn.proem.dagl.web.scheduled.task.impl.HttpTaskImpl;
import cn.proem.suw.util.PagerPropertyUtils;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.util.StringUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/w/schedule")
public class ScheduledController extends BaseCtrlModel{

    @Resource
    private GeneralDao generalDao;
    
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request)throws ServiceException {
        ModelAndView modelAndView = this.createNormalView("/web/schedule/schedule.vm");
        return modelAndView;
    }
    
    @RequestMapping(value = "/tasks")
    @ResponseBody
    @LogService(description = "分页获取定时任务列表")
    public String tasks(String dtGridPager, HttpServletRequest request) throws ServiceException {
        // 映射Pager对象
        Pager pager;
        try {
            pager = PagerPropertyUtils.copy(JSONObject.fromObject(dtGridPager));
        } catch (Exception e) {
           throw new ServiceException("PagerPropertyUtils复制对象错误");
        }
        // 映射为int型
        int pageSize = pager.getPageSize();
        int startRecord = pager.getStartRecord();
        int recordCount = pager.getRecordCount();
        int pageCount = pager.getPageCount();
        recordCount = generalDao.countByCriteria(ScheduledTask.class, new QueryBuilder());
        pager.setRecordCount(generalDao.countByCriteria(ScheduledTask.class, new QueryBuilder()));
        pageCount = recordCount/pageSize+(recordCount%pageSize>0?1:0);
        pager.setPageCount(pageCount);
        // 查询结果集放到信息中
        List<Map<String, Object>> dataList = generalDao.getObjectList("SELECT * FROM pdagl_scheduledtasks", startRecord, pageSize);
        pager.setExhibitDatas(dataList);
        // 设置查询成功
        pager.setIsSuccess(true);
        return JSON.toJSONStringWithDateFormat(pager,
                "yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
    }
    
    @RequestMapping(value = "/del/tasks")
    @ResponseBody
    @Transactional
    @LogService(description = "删除定时任务")
    public String deltask(@RequestBody TaskList tasks, HttpServletRequest request) throws ServiceException {
        for(Task task : tasks){
            generalDao.deleteById(task.getUuid(), ScheduledTask.class);
        }
        return "Success";
    }
    
    @RequestMapping(value = "/task", method=RequestMethod.POST)
    @ResponseBody
    @Transactional
    @LogService(description = "保存或修改定时任务")
    public String addtask(@RequestBody Task task, HttpServletRequest request) throws ServiceException {
        ScheduledTask schedule = null;
        User user = this.getCurrentUser(request);
        if(!"".equals(task.getUuid())){
            schedule = generalDao.findById(task.getUuid(), ScheduledTask.class);
            schedule.setUpdateTime(new Date());
            schedule.setUpdateId(user.getId());
        }else{
            schedule = new ScheduledTask();
            schedule.setCreateId(user.getId());
        }
        schedule.setTaskname(task.getTaskname());
        schedule.setState(task.getState());
        schedule.setExecutetime(task.getTasktime());
        schedule.setUrl(task.getUrl());
        generalDao.saveOrUpdate(schedule);
        return "Success";
    }
    
    @RequestMapping(value = "/task/edit", method=RequestMethod.GET)
    @ResponseBody
    @LogService(description = "定时任务的修改")
    public ModelAndView edittask(String id, HttpServletRequest request) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView("/web/schedule/task");
        if(id != null){
            ScheduledTask schedule = generalDao.findById(id, ScheduledTask.class);
            Task task = new Task();
            task.setUuid(id);
            task.setUrl(schedule.getUrl());
            task.setState(schedule.getState());
            task.setTaskname(schedule.getTaskname());
            task.setTasktime(schedule.getExecutetime());
            modelAndView.addObject("task", task);
        }
       
        return modelAndView;
    }
    
    @RequestMapping(value = "/task/start", method=RequestMethod.POST)
    @ResponseBody
    @LogService(description = "启动定时任务")
    public String startTask(@RequestBody Task task, HttpServletRequest request) throws Exception{
        if(task.getUuid() != null){
            ScheduledTask schedule = generalDao.findById(task.getUuid(), ScheduledTask.class);
            HttpTask createindex = new HttpTaskImpl(schedule.getUrl());
            return createindex.execute();
        }
        return "没有传入有效任务标识";
    }
    
    @RequestMapping(value = "/task/stop", method=RequestMethod.GET)
    @ResponseBody
    @LogService(description = "停止定时任务")
    public Task stopTask(){
        return null;
    }
    
    @RequestMapping(value = "/task/process", method=RequestMethod.GET)
    @ResponseBody
    @LogService(description = "查看任务进度")
    public Task processTask(){
        return null;
    }
    
}
