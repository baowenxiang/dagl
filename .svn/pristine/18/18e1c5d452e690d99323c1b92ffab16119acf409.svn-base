$(function(){
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initGrid();
	refreshGrid();
	
	initMethod();
	layer.close(index);
});


//初始化表格
function initGrid(){
	grid = $.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/notice/getJdtx",
	    ajaxLoad : true,
	    loadAll: true,
	    gridContainer : 'tableDiv',
	    toolbarContainer : 'pagingDiv',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : false,
	    pageSizeLimit : [20,50,100,300],
		columns : [
					{
						id:'dalx',
						title : '<b>档案类型</b>',
						type:"string",
						columnClass:'text-center'
					},
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
		       			id:'bgqx',
		       			title : '<b>保管期限</b>',
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
						id:'id',
						title : '<b>操作</b>',
						type:"string",
						hideType:'xs',
						columnClass:'text-center',
						isContentEditable: false,
						resolution:function(value, record, column, grid, dataNo, columnNo){
						var btn = "";
						btn += '<a title="档案销毁" class="btn btn-danger btn-xs daxh" uuid="'+record.uuid+'"  tablename="'+record.tableName+'">档案销毁</a>';
						btn += '<a title="修改期限" class="btn btn-primary btn-xs xgqx" uuid="'+record.uuid+'" tablename="'+record.tableName+'">修改期限</a>';
						return btn;
				}
			},
		]
	});
}


/**
 * 刷新列表数据
 */
function refreshGrid() {
	grid.parameters = getParameters();
	grid.load();
}
/**
 * 获取查询参数
 */
function getParameters() {
	return {
	};
}

function initMethod(){
	$("#tableDiv").on("click",".daxh",function(){
		var uuid = $(this).attr("uuid");
		var tablename = $(this).attr("tablename");//选中的值

		var businessIds = [];
		businessIds.push(uuid);
		var businessStr = businessIds.join();
		//layui弹出下一级处理人
		var options = {}
		options.url = Util.getRootPath() + "/notice/nextstepView?tablename="+tablename+"&ids="+businessStr+"&flag=NEW";
		options.title = "下一步处理人";
		window.parent.showModal(options);
	});
	
	$("#tableDiv").on("click",".xgqx",function(){
		var uuid = $(this).attr("uuid");
		var tablename = $(this).attr("tablename");//选中的值
		var businessIds = [];
		businessIds.push(uuid);
		var businessStr = businessIds.join();
		var options = {}
		options.url = Util.getRootPath() + "/notice/toModifyRetentionView?tablename="+tablename+"&ids="+businessStr+"&flag=NEW";
		options.title = "下一步处理人";
		window.parent.showModal(options);
	});
	
	$("#tableDiv").on("click", "img", function(){
		var fileid = $(this).attr("dagl-uuid");
		var filename = $(this).attr("title");
		$.ajax({
            type: "GET",
            contentType: "application/json",
            url: Util.getRootPath() + "/w/preview/" + fileid,
            dataType: 'json',
            async: false,	
            success: function(result) {
            	var option = {};
            	option.title = filename;
            		option.url = result.cache;
            		window.parent.showModal(option);
            },
		});
	});
}

