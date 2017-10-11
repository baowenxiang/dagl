var grid;

$(function(){
	initMethod();
	initGrid("");
});


function initMethod(){
	//点击档案销毁按钮
	$('button.filedestroy').on("click",function(){
		//var tablename = $('[name="filetablename"]').val();
		
		var tablename = $('[name="tableselect"] option:selected').val();//选中的值
		
		var business = grid.getCheckedRecords();
		if(business.length==0){
			layer.alert("请选择销毁信息项！", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}

		var businessIds = [];
		for(var i=0;i<business.length;i++){
			businessIds.push(business[i].uuid);
		}
		
		var businessStr = businessIds.join();
		//layui弹出下一级处理人
		var options = {}
		options.url = Util.getRootPath() + "/w/fileidentify/identifyContent/nextstepView?tablename="+tablename+"&ids="+businessStr+"&flag=NEW";
		options.title = "下一步处理人";
		
    	window.parent.showModal(options);
		
		
	});
	
	$("button.query").on("click",function(){
		var tablename = $('[name="tableselect"] option:selected').val();//选中的值
		var options = {}
		options.url = Util.getRootPath() + "/w/fileidentify/identifyContent/queryView?tablename="+tablename;
		options.title = "题名查询";
		
    	window.parent.showModal(options);
	});
	
}

function initGrid(tablename){
	$("#tableIdentify").children().remove();
	
	
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/fileidentify/identifyContent/getIdentifyFiles",
	    ajaxLoad : true,
	    gridContainer : 'tableIdentify',
	    toolbarContainer : 'pagingIdentify',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    pageSizeLimit : [20,50,100,300],
	    columns : [
					{
						id:'dh',
						title : '<b>档号</b>',
						type:"string",
						columnClass:'text-center'
					},
		       		{
		       			id:'tm',
		       			title : '<b>题名</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'zrz',
		       			title : '<b>责任者</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'cwrq',
		       			title : '<b>成文日期</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'mj',
		       			title : '<b>密级</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       			
		       		},
		       		{
		       			id:'bgqx',
		       			title : '<b>保管期限</b>',
		       			type:"string",
		       			columnClass:'text-center',

		       		},
		       		{
		       			id:'isarchive',
		       			title : '<b>当前状态</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			isContentEditable: false,
						resolution:function(value, record, column, grid, dataNo, columnNo){
							if(record.isarchive == '4'){
								return "审批中";
							}else if(record.isarchive == '5'){
								return "已拒绝";
							}else{
								return "未鉴定";
							}
						}
		       		}
		       		]
	});
	refreshGrid(tablename);
}

function refreshGrid(tablename) {
	grid.parameters = getParameters(tablename);
	grid.load();
}
function getParameters(tablename) {
	return{
		tablename : tablename
		//tablename : $.trim($('[name="filetablename"]').val())
	}
}