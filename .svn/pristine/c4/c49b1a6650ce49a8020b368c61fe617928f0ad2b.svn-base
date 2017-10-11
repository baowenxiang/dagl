var grid;

$(function(){
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initMethod();
	initGrid("");
	layer.close(index);
//	selectFileTableName();
});


//function selectFileTableName(){
//	//$("#tableSelect option:selected").val();
//	$("#tableSelect").change(function(){
//		$('[name="filetablename"]').val($("#tableSelect option:selected").val());
//		refreshGrid();
//	})
//	//$('[name="filetablename"]').val($("#tableSelect option:selected").val());
//}

function initMethod(){
	//点击档案公开按钮
	$('button.open').on("click",function(){
//		var tablename = $('[name="filetablename"]').val();
		var tablename = $('[name="tableselect"] option:selected').val();//选中的值
		var business = grid.getCheckedRecords();
		if(business.length==0){
			layer.alert("请选公开信息项！", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}

		var businessIds = [];
		for(var i=0;i<business.length;i++){
			businessIds.push(business[i].uuid);
		}
		
		var businessStr = businessIds.join();
		//layui弹出下一级处理人
		var options = {}
		options.url = Util.getRootPath() + "/w/dahk/nextstepView?tablename="+tablename+"&ids="+businessStr+"&flag=NEW";
//		options.data = {
//			tablename : tablename,
//			ids : businessStr,
//			flag:'NEW'
//		};
		options.title = "下一步处理人";
		window.parent.showModal(options);
		
		
	});
	
	$("button.query").on("click",function(){
		var tablename = $('[name="tableselect"] option:selected').val();//选中的值
		var options = {}
		options.url = Util.getRootPath() + "/w/dahk/queryView?tablename="+tablename;
		options.title = "题名查询";
		
    	window.parent.showModal(options);
	});
	
}

function initGrid(tablename){
	$("#tableFileControl").children().remove();
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/dahk/getControlFiles",
	    ajaxLoad : true,
	    gridContainer : 'tableFileControl',
	    toolbarContainer : 'pagingFileControl',
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
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'hkkz',
		       			title : '<b>当前状态</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			isContentEditable: false,
						resolution:function(value, record, column, grid, dataNo, columnNo){
							if(record.hkkz == '0'){
								return "审批中";
							}else if(record.hkkz == '2'){
								return "已拒绝";
							}else{
								return "未鉴定";
							}
						}
		       		}
//		       		{
//		       			id:'id',
//		       			title : '<b>挂接原文</b>',
//		       			type:"string",
//		       			columnClass:'text-center',
//		       			resolution:function(value, record, column, grid, dataNo, columnNo){
//
//           				 var html = "";
//           				 Util.ajaxJsonSync(
//       						 Util.getRootPath()+"/w/example/flow/getYwgj",
//       	                        {
//       							 id : value
//       	                        },
//       	                        function(result){
//       								if(result.success){
//       									
//       									var datas = result.datas;
//       									if(datas.length>0){
//           									for(var i = 0;i<datas.length;i++){
//           										var wjlx = datas[i].wjlx;
//           										var wjm = datas[i].wjm;
//           										if(wjlx == 'pdf'){
//           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/pdf.svg" />';
//           										}else if(wjlx == 'doc' || wjlx == 'docx'){
//           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/word.svg" />';
//           										}else if(wjlx == 'xlsx' || wjlx == 'xls'){
//           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/excel.svg" />';
//           										}else if(Util.isAudio(wjlx)){
//           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/音频.svg" />';
//           										}else if(Util.isVedio(wjlx)){
//           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/视频.svg" />';
//           										}else{
//           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/文件.svg" />';
//           										}
//           									}
//       									}
//       								}
//       	                        }
//           				 )
//           				 return html;
//           			 
//		       			}
//		       		},
//		       		{
//		       			id:'id',
//		       			title : '<b>操作</b>',
//		       			type:"string",
//		       			columnClass:'text-center',
//		       			resolution:function(value, record, column, grid, dataNo, columnNo){
//		       			//存储record类，编辑时使用
//		       				var html = ''; 	
//		       				html += '<div class="btnDiv">';
//		       				html += '	<a title="查看" class="btn btn-default btn-xs looking" data-id="'+value+'">查看</a>';
//	       					html += '	<a title="修改" class="btn btn-primary btn-xs update" data-id="'+value+'">修改</a>';
//	       					html += '	<a title="删除" class="btn btn-primary btn-xs delete" data-id="'+value+'">删除</a>';
//		       				html += '</div>';
//			       			
//		       				return html;
//		       			}
//
//		       		}
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
//		tablename : $.trim($('[name="filetablename"]').val())
	}
}