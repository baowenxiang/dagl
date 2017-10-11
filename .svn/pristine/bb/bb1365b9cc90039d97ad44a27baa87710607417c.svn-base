package cn.proem.dagl.web.message.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.proem.core.annotation.LogService;
import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.DataGrid;
import cn.proem.core.model.DataGridQuery;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.message.entity.Message;
import cn.proem.dagl.web.message.service.MessageService;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.service.CommonService;
import cn.proem.suw.web.common.util.StringUtil;

@Controller
@RequestMapping("/w/message")
public class MessageController extends BaseCtrlModel {
	
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/initMessagePage")
	public ModelAndView initMessagePage(){
		ModelAndView view = this.createNormalView("/web/message/initMessage.vm");
		
		return view;
		
	}
	
	
	/**
	 * @Description:初始化发件箱
	 * @author:bao
	 * @time:2017年6月27日 下午3:52:53
	 */
	@RequestMapping("/sendMessage/initView")
	public ModelAndView sendMessagePage(){
		ModelAndView view = this.createNormalView("/web/message/sendMessage.vm");
		
		return view;
		
	}
	
	
	/**
	 * @Description:初始化收件箱
	 * @author:bao
	 * @time:2017年6月27日 下午7:39:06
	 */
	@RequestMapping("/receiveMessage/initView")
	public ModelAndView receiveMessagePage(){
		ModelAndView view = this.createNormalView("/web/message/receiveMessage.vm");
		
		return view;
		
	}
	
	/**
	 * 
	 * @Description:增加发件箱跳转页面
	 * @author:bao
	 * @time:2017年6月27日 下午4:46:22
	 */
	@RequestMapping("/addSendMessageView")
	public ModelAndView addSendMessageView(){
		ModelAndView view = this.createNormalView("/web/message/addSendMessage.vm");
		List<User> users = messageService.findAllUsers();
		
		view.addObject("users", users);
		return view;
		
	}
	
	/**
	 * 
	 * @Description:查看发件箱跳转页面
	 * @author:bao
	 * @throws ServiceException 
	 * @time:2017年6月27日 下午4:46:22
	 */
	@RequestMapping("/lookSendMessageView")
	public ModelAndView lookSendMessageView(String id) throws ServiceException{
		ModelAndView view = this.createNormalView("/web/message/lookSendMessage.vm");
		
		Message message = commonService.findById(id, Message.class);
		
		view.addObject("content", message.getContent());
		return view;
		
	}
	
	
	/**
	 * 
	 * @Description:查看收件箱跳转页面
	 * @author:bao
	 * @time:2017年6月27日 下午8:12:16
	 */
	@RequestMapping("/lookReceiveMessageView")
	public ModelAndView lookReceiveMessageView(String id) throws ServiceException{
		ModelAndView view = this.createNormalView("/web/message/lookReceiveMessage.vm");
		
		Message message = commonService.findById(id, Message.class);
		if(message!=null){
			if(!"isRead_1".equals(message.getIsRead())){
				message.setIsRead("isRead_1");;
				messageService.saveOrUpdateMessage(message);
			}
		}
		
		view.addObject("content", message.getContent());
		return view;
		
	}
	
	
	@RequestMapping("/getSendMessageList")
	@ResponseBody
	@LogService(description = "查询消息列表")
	public String getSendMessageList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		String userId = this.getCurrentUser(request).getId();
		queryBuilder.addCondition(new QueryCondition("id",userId,ConditionType.EQ,FieldType.STRING,"sendUser"));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<Message> dataGrid = messageService.getMessageDateGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, 
				"yyyy-MM-dd HH:mm:ss", 
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect
		);
	}
	
	@RequestMapping("/getReceiveMessageList")
	@ResponseBody
	@LogService(description = "查询消息列表")
	public String getReceiveMessageList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		String userId = this.getCurrentUser(request).getId();
		queryBuilder.addCondition(new QueryCondition("id",userId,ConditionType.EQ,FieldType.STRING,"receUser"));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<Message> dataGrid = messageService.getMessageDateGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, 
				"yyyy-MM-dd HH:mm:ss", 
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect
		);
	}
	
	/**
	 * 查询message列表
	 * @param dtGridPager
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMessageList")
	@ResponseBody
	@LogService(description = "查询消息列表")
	public String getMessageList(String dtGridPager, HttpServletRequest request){
		DataGridQuery query = this.parseToQuery(dtGridPager == null ? "" : dtGridPager);
		QueryBuilder queryBuilder = query.generateQueryBuilder();
		User receUser = this.getCurrentUser(request);
		String receUser_id = receUser.getId();
		queryBuilder.addCondition(new QueryCondition("id",receUser_id,ConditionType.EQ,FieldType.STRING,"receUser"));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		DataGrid<Message> dataGrid = messageService.getMessageDateGrid(queryBuilder,query.getNowPage(),query.getPageSize());
		
		return JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	/**
	 * 保存，修改消息
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/addSendMesage")
	@ResponseBody
	@LogService(description = "保存发件箱数据")
	public ResultModel<String> addSendMesage(HttpServletRequest request) throws ParseException{
		ResultModel<String> resultModel = new ResultModel<String>();
			try {
			Message message = new Message();
			
			message.setSendTime(new Date());
			
			User receiveUser = commonService.findById(request.getParameter("receiveUser"), User.class);
			message.setReceUser(receiveUser);
			message.setSendUser(this.getCurrentUser(request));
			
			message.setIsRead("isRead_0");
			message.setContent(request.getParameter("content"));
			
		
			messageService.saveOrUpdateMessage(message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			resultModel.setSuccess(false);
			resultModel.setMsg("保存失败");
			e.printStackTrace();
		}
		return resultModel;
	}
	
	
	
	
	/**
	 * 获取message详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMessageById")
	@ResponseBody
	@LogService(description = "获取消息详情")
	public ResultModel<Message> getMessageById(String id){
		ResultModel<Message> rm = new ResultModel<Message>();
		Message message = commonService.findById(id, Message.class);
		message.setIsRead("isRead_1");
		try {
			messageService.saveOrUpdateMessage(message);
			rm.setData(message);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		
		return rm;
	}
	/**
	 * 删除信息byId
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	@LogService(description = "根据唯一标识ID删除消息")
	public ResultModel<String> deleteMessageById(String id){
		ResultModel<String> resultModel = new ResultModel<String>();
		if(StringUtil.isNotEmpty(id)){
			messageService.deleteMessageById(id);
			resultModel.setSuccess(true);
			resultModel.setMsg("删除成功");
		}else{
			resultModel.setSuccess(false);
			resultModel.setMsg("删除失败");
		}
		
		return resultModel;
	}
	/**
	 * 保存，修改消息
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	@LogService(description = "保存或修改消息")
	public ResultModel<String> updateMessage(HttpServletRequest request) throws ParseException{
		ResultModel<String> resultModel = new ResultModel<String>();
		Message message = new Message();
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			message.setId(id);
		}
		
		message.setType(request.getParameter("type"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		message.setSendTime(sdf.parse(request.getParameter("sendTime")));
		User receUser = messageService.getUserByName(request.getParameter("sendName"));
		message.setReceUser(receUser);
		message.setContent(request.getParameter("content"));
		User sendUser = this.getCurrentUser(request);
		message.setSendUser(sendUser);
		message.setIsRead("isRead_0");
		
		try {
			messageService.saveOrUpdateMessage(message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			resultModel.setSuccess(false);
			resultModel.setMsg("修改失败");
			e.printStackTrace();
		}
		return resultModel;
	}
	/**
	 * 获取未读消息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUnReadMessageList")
	@ResponseBody
	@LogService(description = "获取未读消息")
	public List<Message> getUnreadMessageList(HttpServletRequest request){
		QueryBuilder queryBuilder = new QueryBuilder();
		User receUser = this.getCurrentUser(request);
		String receUser_id = receUser.getId();
		queryBuilder.addCondition(new QueryCondition("id",receUser_id,ConditionType.EQ,FieldType.STRING,"receUser"));
		queryBuilder.addCondition(new QueryCondition("delFlag",Integer.parseInt("0"),ConditionType.EQ,FieldType.INTEGER,null));
		queryBuilder.addCondition(new QueryCondition("isRead","isRead_0",ConditionType.EQ,FieldType.STRING,null));
		List<Message> list = messageService.getUnreadMessageList(queryBuilder);
		
		return list;
		
	}
	
	
}
