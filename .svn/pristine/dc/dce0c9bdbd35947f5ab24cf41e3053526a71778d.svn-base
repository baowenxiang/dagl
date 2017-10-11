package cn.proem.dagl.web.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.bpm.entity.Deployment;
import cn.proem.bpm.entity.Handler;
import cn.proem.bpm.entity.HandlerGroup;
import cn.proem.bpm.entity.HandlerType;
import cn.proem.bpm.entity.ProcTask;
import cn.proem.bpm.entity.ProcUnit;
import cn.proem.bpm.model.NextStep;
import cn.proem.bpm.model.Task;
import cn.proem.bpm.model.TaskHandler;
import cn.proem.bpm.service.BpmQueryService;
import cn.proem.bpm.service.ProcessService;
import cn.proem.core.dao.GeneralDao;
import cn.proem.core.entity.User;
import cn.proem.core.entity.UserDepartment;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.flow.service.FlowService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.tools.util.CommonFile;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class FlowServiceImpl implements FlowService{
	@Autowired
	private GeneralDao generalDao;
	@Autowired
	private ProcessService processService;
	@Autowired
	private BpmQueryService bpmQueryService;
	@Autowired
	private CommonService commonService;

	@Override
	public List<Ywgj> getFileBydataId(String id) {
		QueryBuilder queryBuilder = new QueryBuilder();
		List<Ywgj> ywgjs;
		if(StringUtil.isEmpty(id)){
			ywgjs  = new ArrayList<Ywgj>();
		}else{
			queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0), ConditionType.EQ,FieldType.INTEGER, null));
			queryBuilder.addCondition(new QueryCondition("zlsj", id, ConditionType.EQ, null));
			ywgjs = generalDao.queryByCriteria(Ywgj.class, queryBuilder, new Order[]{new Order("xsxh", OrderType.ASC)}, 0, -1);
		}
		
		
		
		
		return ywgjs;
	}

	@Override
	public List<CommonFile> getFilesByDataId(String dataId) {
		List<CommonFile> commonFiles = new ArrayList<CommonFile>();
		
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(!StringUtil.isEmpty(dataId)){
			queryBuilder.addCondition(new QueryCondition("zlsj", dataId, ConditionType.EQ, null));
			queryBuilder.addCondition(new QueryCondition("delFlag", Integer.valueOf(0), ConditionType.EQ,FieldType.INTEGER, null));
			List<Ywgj> ywgjs = generalDao.queryByCriteria(Ywgj.class, queryBuilder, null, 0, -1);
			for(Ywgj ywgj : ywgjs){
				commonFiles.add(new CommonFile(ywgj.getWjdz(), ywgj.getWjm()));
			}
		}
		return commonFiles;
	}
	
	@Override
	public DataGrid<User> getNextHandlerList(QueryBuilder queryBuilder, String processId, User user, int nowPage, int pageSize) {
		DataGrid<User> handlerDtGrid = new DataGrid<User>();
		//获取所有节点
		List<ProcUnit> allUnits = bpmQueryService.getAllUnits(processId);
		HandlerGroup group = null;
		
		group = allUnits.get(1).getGroup();
		
		//获取所有的处理对象
		List<Handler> handlers = group.getHandlers();
		if (handlers != null && handlers.size() > 0) {
			//循环获取该处理对象中的所有人员
			List<User> us = new ArrayList<User>();
			for (Handler handler : handlers) {
				Set<String> targets = handler.getTargets();
				us = this.convertHandlerToUser(handler.getType(), targets);
			}
			//赋值
			handlerDtGrid.setExhibitDatas(us);
			handlerDtGrid.setRecordCount(us.size());
		}
		return handlerDtGrid;
	}
	
	@Override
	public DataGrid<User> getNextHandlerList(QueryBuilder queryBuilder,
			String processId, String businessId, User user, String operate, int nowpage,
			int pageSize) {
		//获取部署ID
		Deployment deployment = bpmQueryService.getMaxVersionDeployment(processId);
		String deploymentId = deployment.getId();
		DataGrid<Task> tasks = bpmQueryService.queryTask(user.getId(), processId, 0, -1);
		//任务
		Task task = null;
		if (tasks != null && tasks.getRecordCount() > 0) {
			for (Task t : tasks.getExhibitDatas()) {
				if (t.getBusinessId().equals(businessId)) {
					task = t;
					break;
				}
			}
		}
		//找到下一步
		List<NextStep> nextSteps = bpmQueryService.getNextSteps(task.getId());
		NextStep nextStep = null;
		if (nextSteps != null && nextSteps.size() > 0) {
			if (nextSteps.size() == 1) {
				nextStep = nextSteps.get(0);
			} else {
				for (NextStep step : nextSteps) {
					if (step.getOperate().equals(operate) && StringUtil.isNotEmpty(step.getExpression())) {
						
						nextStep = step;
						break;
					}
				}
			}
		}
		//赋值
		DataGrid<User> handlerDtGrid = new DataGrid<User>();
		handlerDtGrid.setNowPage(nowpage);
		handlerDtGrid.setPageSize(pageSize);
		//获取所有处理对象
		List<TaskHandler> nodeHandlers = bpmQueryService.getNodeHandlers(deploymentId, task.getProcessInstanceId(), nextStep.getKey());
		if (nodeHandlers != null && nodeHandlers.size() > 0) {
			//循环获取该处理对象中的所有人员
			List<User> us = new ArrayList<User>();
			for (TaskHandler taskHandler : nodeHandlers) {
				Set<String> targets = taskHandler.getTargets();
				us = this.convertHandlerToUser(taskHandler.getType(), targets);
				//转为UserDepartment对象
			}
			//赋值
			handlerDtGrid.setExhibitDatas(us);
			handlerDtGrid.setRecordCount(us.size());
		}
		return handlerDtGrid;
	}
	
	/**
	 * @MethodName convertHandlerToUserDept
	 * @Description 根据操作类型和集合id， 获取所有的用户
	 * @author Pan Jilong
	 * @date 2017年3月3日
	 * @param handType
	 * @param targets
	 * @return
	 */
	private List<User> convertHandlerToUser(HandlerType handType, Set<String> targets) {
		List<User> uds = new ArrayList<User>();
		switch (handType) {
			case ROLE :
				//根据角色，查找人员
				for (String rid : targets) {
					List<User> us = commonService.findUsersByRoleId(rid);
					uds.addAll(us);
				}
				break;
			case USER :
				//获取人员
				for (String uid : targets) {
					uds.add(generalDao.findById(uid, User.class));
				}
				break;
			case DUTY :
				//根据职务，查找人员
				for (String dutyid : targets) {
					List<User> us = commonService.findUsersByDutyId(dutyid);
					uds.addAll(us);
				}
				break;
			case CREATEER :
				//获取人员
				for (String uid : targets) {
					uds.add(generalDao.findById(uid, User.class));
				}
				break;
			case DEPARTMENT :
				//根据部门，查找人员（下属所有部门的人员都需要查找到）
				for (String did : targets) {
					List<User> us = commonService.findUsersByDeptId(did);
					uds.addAll(us);
				}
				break;
		}
		return uds;
	}

	@Override
	public List<ProcTask> checkFlowState(String businessId) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.addCondition(new QueryCondition("businessId", businessId, ConditionType.EQ, FieldType.STRING));
		List<ProcTask> procTasks = generalDao.queryByCriteria(ProcTask.class, queryBuilder, null, 0, -1);
		return procTasks;
	}
	
}
