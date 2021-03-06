var nextstepUserGrid;

$(function() {
	initNextstepUser();
	refreshNextstepUserGrid();
	
	initMethod();
});


//获取人员列表
function initNextstepUser() {
	nextstepUserGrid = $.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/example/flow/getNextstepUsers",
	    ajaxLoad : true,
	    gridContainer : 'nextstepUserTableDiv',
	    toolbarContainer : 'nextstepUserPagingDiv',
	    check:true,
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 50],
		columns : [
			{
				id:'name',
				title : '<b>姓名</b>',
				type:"string",
				columnClass:'text-center',
			},
			{
				id:'username',
				title : '<b>用户名</b>',
				type:"string",
				columnClass:'text-center',
			},
		]
	});	
}


/**
 * 刷新列表数据
 */
function refreshNextstepUserGrid() {
	nextstepUserGrid.parameters = getNextstepUserParameters();
	nextstepUserGrid.load();
}
/**
 * 获取查询参数
 */
function getNextstepUserParameters() {
	var processId = $.trim($("[name='processId']").val());
	if($('[name="flag"]') === 'NEW'){
		return {
			processId : processId,
			flag:$.trim($('[name="flag"]').val()),
			
		};
		
	}else{
		return {
			processId : processId,
			flag:$.trim($('[name="flag"]').val()),
			businessId:$('[name="tablename"]').val()+","+$('[name="ids"]').val()
		};
	}
}


function initMethod(){
	//关闭
	$("button.closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	//执行流程
	$("button.btn_hand").on("click",function(){
		var nextUsers = nextstepUserGrid.getCheckedRecords();
		if(nextUsers.length==0){
			layer.alert("请勾选下一节点的处理人！", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		
		var tablename = $.trim($('[name="tablename"]').val());
		var flag = $.trim($('[name="flag"]').val())
			//下一步的处理人数组
			var checkUsers = nextstepUserGrid.getCheckedRecords();
			//获取勾选的人员id数组
			var users = [];
			for(var i=0;i<checkUsers.length;i++){
				users.push(checkUsers[i].id);
			}
			
			var nextHandlers = users;
			
		if(flag == "NEW"){
			
			//流程启动参数
			var startParam = {};
			//流程定义ID
			startParam.processId = $.trim($("[name='processId']").val());
			
			//获得所勾选的业务id数组
			var idArr = $('[name="ids"]').val().split(',');
			var businessIds = [];
			for(var i = 0;i<idArr.length;i++){
				var businessId = tablename+','+idArr[i];
				//业务对象ID
				businessIds.push(businessId);
			
			}		
				
				//启动流程
				Util.ajaxJsonSync(
					Util.getRootPath() + "/w/fileuse/electronicLend/startProcess",
					{	
						startParam:startParam,
						nextHandlers: nextHandlers,
						businessIds:businessIds
					},
					function(result){
						if (result.success) {
							layer.msg(result.msg,{time: 2000});
							
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
							
							parent.mainIframe.refreshGrid();
						} else {
							layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
						}
					}, function (ex) {
						layer.alert("流程发起失败", {  skin: 'layui-layer-lan',closeBtn: 0});
					}
				);
				
				
		}else if(flag == "HANDLE"){
			
			var handParam = {};
			//流程定义Id
			handParam.processId = $.trim($('[name="processId"]').val());
			//业务ID
			handParam.businessId = tablename+","+$('[name="ids"]').val();
			
			handParam.operate = 'APPROVE';
			
			handParam.expression = 'toLeader';
			//处理流程
			Util.ajaxJsonSync(
					Util.getRootPath() + "/w/fileuse/electronicLend/handProcess",
					{	
						handParam: handParam,
						nextHandlers: nextHandlers
					},
					function(result){
						if (result.success) {
							layer.msg(result.msg,{time: 2000});
							
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
							
							parent.mainIframe.refreshGrid();
						} else {
							layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
						}
					}, function (ex) {
						layer.alert("流程处理失败", {  skin: 'layui-layer-lan',closeBtn: 0});
					}
				);
		}
		
		
		
			
	})
}
