package cn.proem.suw.web.common.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.bpm.entity.ProcTask;
import cn.proem.bpm.entity.Process;
import cn.proem.bpm.model.Task;
import cn.proem.bpm.service.BpmQueryService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.dicManager.entity.DictionaryValue;
import cn.proem.dagl.web.dicManager.service.DicManagerService;
import cn.proem.suw.web.common.dto.DtoStatis;
import cn.proem.suw.web.common.dto.DtoTask;
import cn.proem.suw.web.common.service.IndexService;
import cn.proem.suw.web.temperature.entity.DWsd;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private GeneralDao generalDao;
    @Autowired
    private BpmQueryService bpmQueryService;
    @Autowired
    private DicManagerService dicService;

    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
    
    // 档案类型字典名
    private static final String DALX = "DALX";
    private static final String STATIS_CNT = "SELECT COUNT(1) AS CNT FROM %s WHERE 1=1 %s";
    private static final String ARCHIVECONDS = " AND ISARCHIVE = '3' AND DELFLAG='0'";

    @Override
    public List<Process> findProcessValue() {
        QueryBuilder queryBuilder = new QueryBuilder();
        List<Process> processes = null;
        processes = generalDao.queryByCriteria(Process.class, queryBuilder, null, 0, -1);
        return processes;
    }

    @Override
    public DtoTask queryProcTask(String processInstanceId) {
        
        DtoTask task = new DtoTask();
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("processInstanceId",processInstanceId, ConditionType.EQ, FieldType.STRING, null));
        List<ProcTask> protasks = null;
        protasks = generalDao.queryByCriteria(ProcTask.class, queryBuilder, null, 0, -1);
        if(protasks.size() == 1){
            // 流程名
            task.setTaskname(protasks.get(0).getTaskName());
            User user = this.getUser(protasks.get(0).getCreateUser());
            // 发起人姓名
            if(user != null){
                task.setUsername(user.getName());
            }else{
                task.setUsername("");
            }
            // 创建时间
            task.setCreateTime(dateFormater.format(protasks.get(0).getCreateTime()));
        }
        return task;
    }

    @Override
    public DataGrid<Task> queryTask(String userid, String processId) {
        return bpmQueryService.queryTask(userid, processId, 0, -1);
    }
    
    @Override
    public List<DtoTask> queryAllTask(String userid){
        List<DtoTask> tasks = new ArrayList<DtoTask>();
        List<Process> processes = findProcessValue();
        for(Process process: processes){
        	String processName = process.getName();
            System.out.println(process.getName());
                // 待处理流程任务
                DataGrid<Task> tmptasks = queryTask(userid, process.getId());
                for(Task task: tmptasks.getExhibitDatas()){
                    DtoTask dtoTask = queryProcTask(task.getProcessInstanceId());
                    dtoTask.setProcess(processName);
                    if("预归档流程".equals(processName.trim())){
                    	dtoTask.setUrl("/dagl/w/example/flow/toDoTaskView");
                    }else if("档案划控流程".equals(processName.trim())){
                    	dtoTask.setUrl("/dagl/w/dahk/controlToDoTaskView");
                    }else if("档案移交流程".equals(processName.trim())){
                    	dtoTask.setUrl("/dagl/w/fileTransfer/transferToDoTaskView");
                    }else if("电子借阅流程".equals(processName.trim())){
                    	dtoTask.setUrl("/dagl/w/fileuse/electronicLend/toDoTaskView");
                    }else if("修改保管期限流程".equals(processName.trim())){
                    	dtoTask.setUrl("/dagl/w/fileidentify/identifyInfo/modifyRetentionToDoTaskView");
                    }else{
                    	dtoTask.setUrl("/dagl/w/fileidentify/identifyContent/detroyToDoTaskView");
                    }
                    
                    
                    tasks.add(dtoTask);
                }
            }
        if(tasks.size()>10){
        	tasks = tasks.subList(0, 10);
        }
        return tasks;
    }
    
    /**
     * 根据用户ID获得用户对象
     * @param userid
     * @return
     */
    private User getUser(String userid){
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addCondition(new QueryCondition("id",userid, ConditionType.EQ, FieldType.STRING, null));
        List<User> users= generalDao.queryByCriteria(User.class, queryBuilder, null, 0, -1);
        if(users.size() == 1){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<DtoStatis> queryArchiveNumStatic() {
        // 获得所有档案
        List<DictionaryValue> dictionaryValues = dicService.getDicValueList(DALX);
        // 统计档案数量
        List<DtoStatis> statises = new ArrayList<DtoStatis>();
        for (DictionaryValue dicVal : dictionaryValues) {
            DtoStatis statis = new DtoStatis();
            
            Map<String, Object> cnt = generalDao.getObject(String.format(STATIS_CNT, dicVal.getDvno(), ARCHIVECONDS), new HashMap<String, Object>());
            statis.setNum(((BigDecimal)cnt.get("CNT")).toString());
            statis.setName(dicVal.getDvalue());
            statises.add(statis);
        }
        return statises;
    }

	@Override
	public List<DWsd> queryTemperatureStatic() {
		//获取所有温湿度记录
		List<DWsd> dwsdList = generalDao.queryByCriteria(
				DWsd.class, 
				new QueryBuilder(), 
				new Order[]{new Order("jlrq", OrderType.ASC)}, 
				0, -1);
		return dwsdList;
	}
	@Override
	public List<Map<String, Object>> getTotalYears() {
		String sql = "SELECT DISTINCT to_char(JLRQ,'yyyy') AS years FROM man_dabg_wsd";
		List<Map<String,Object>> list = generalDao.getObjectList(sql, 0, -1, new Object[]{});
		return list;
	}
	
	
	public String getFilePath() {
		String classesPath = this.getClass().getClassLoader().getResource("").getPath().replaceAll("%20", " ");  
        String tempdir;
        String classPath[] = classesPath.split("webapps");
        tempdir = classPath[0];
        tempdir += "webapps/";
        tempdir += File.separator;
        return tempdir;
	}
}
