package cn.proem.suw.web.common.service;

import java.util.List;
import java.util.Map;

import cn.proem.bpm.entity.Process;
import cn.proem.bpm.model.Task;
import cn.proem.core.model.DataGrid;
import cn.proem.suw.web.common.dto.DtoStatis;
import cn.proem.suw.web.common.dto.DtoTask;
import cn.proem.suw.web.temperature.entity.DWsd;

public interface IndexService {
    /**
     * 获得流程对应的待处理任务
     * @param processInstanceId
     * @return
     */
    DataGrid<Task> queryTask(String userid, String processId);
    /**
     * 获得流程实例
     * @param processInstanceId
     * @return
     */
    DtoTask queryProcTask(String processInstanceId);
    /**
     * 得到流程类型
     * @return
     */
    List<Process> findProcessValue();
    
    /**
     * 得到所有类型的待处理流程
     * @param userid
     * @return
     */
    List<DtoTask> queryAllTask(String userid);
    
    /**
     * 统计各个档案的数据
     * @return
     */
    List<DtoStatis> queryArchiveNumStatic();
    
    /**
     * @MethodName queryTemperatureStatic
     * @Description 统计温湿度数据
     * @author chenxiaofen
     * @date 2017年6月6日
     * @return
     */
    List<DWsd> queryTemperatureStatic();
    /**
     * @MethodName getTotalYears
     * @Description 获取温湿度记录表中所覆盖的年份
     * @author chenxiaofen
     * @date 2017年6月6日
     * @return
     */
    public List<Map<String, Object>> getTotalYears();
}
