$(function(){
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initGrid();
	refreshGrid();
	
	initMethod();
	layer.close(index);
});


//初始化表格
function initGrid(){
	var processId = $.trim($('[name="processId"]').val());
	grid = $.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/fileuse/electronicLend/getToDoTasks?processId="+processId,
	    ajaxLoad : true,
	    gridContainer : 'tableDiv',
	    toolbarContainer : 'pagingDiv',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : false,
	    pageSizeLimit : [20,50,100,300],
	    tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
		columns : [
			
			{
				id:'jyzt',
				title : '<b>借阅状态</b>',
				type:"string",
				hideType:'xs',
				columnClass:'text-center',
				columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
				resolution:function(value, record, column, grid, dataNo, columnNo){
					return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
				}
			},
			{
				id:'jyr',
				title : '<b>借阅人</b>',
				type:"string",
				hideType:'xs',
				columnClass:'text-center',
				columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
				resolution:function(value, record, column, grid, dataNo, columnNo){
					return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
				}
			},
			{
				id:'jymd',
				title : '<b>借阅目地</b>',
				type:"string",
				hideType:'xs',
				columnClass:'text-center',
				columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
				resolution:function(value, record, column, grid, dataNo, columnNo){
					return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
				}
			},
			{
				id:'tm',
				title : '<b>题名</b>',
				type:"string",
				hideType:'xs',
				columnClass:'text-center',
				columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
				resolution:function(value, record, column, grid, dataNo, columnNo){
					return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
				}
			},
			{
				id:'bz',
				title : '<b>备注</b>',
				type:"string",
				hideType:'xs',
				columnClass:'text-center',
				columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
				resolution:function(value, record, column, grid, dataNo, columnNo){
					return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
				}
			},
			{
				id:'daid',
				title : '<b>挂接原文</b>',
				type:"string",
				columnClass:'text-center',
				resolution:function(value, record, column, grid, dataNo, columnNo){

   				 var html = "";
   				 Util.ajaxJsonSync(
						 Util.getRootPath()+"/w/example/flow/getYwgjByFileId",
	                        {
							 tablename:record.bm,
							 id : record.daid
	                        },
	                        function(result){
								if(result.success){
									
									var datas = result.datas;
									if(datas.length>0){
   									for(var i = 0;i<datas.length;i++){
										var wjlx = datas[i].wjlx;
										var wjm = datas[i].wjm;
										if(wjlx == 'pdf'){
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/pdf.svg" />';
										}else if(wjlx == 'doc' || wjlx == 'docx'){
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/word.svg" />';
										}else if(wjlx == 'xlsx' || wjlx == 'xls'){
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/excel.svg" />';
										}else if(Util.isAudio(wjlx)){
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/音频.svg" />';
										}else if(Util.isVedio(wjlx)){
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/视频.svg" />';
										}else if(Util.isPIC(wjlx)){
   											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/pic.svg" />';
										}else if(wjlx == 'txt'){
   											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/txt.svg" />';

   										}
										else{
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/文件.svg" />';
										}
									}
									}
								}
	                        }
   				 )
   				 return html;
   			 
				}
				
				
				
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
					if(record.nodeName !== '部门领导审批'){
						btn += '<a title="移交上级" class="btn btn-primary btn-xs toLeader" data-id="'+record.id+'" data-business-id="'+record.businessId+'">移交上级</a>';
					}
					btn += '<a title="同意" class="btn btn-primary btn-xs approve" data-id="'+record.id+'"  data-business-id="'+record.businessId+'">同意</a>';
					btn += '<a title="拒绝" class="btn btn-primary btn-xs reject" data-id="'+record.id+'" data-business-id="'+record.businessId+'">拒绝</a>';
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
	$("#tableDiv").on("click",".toLeader",function(){
		var tablename = $('[name="tablename"]').val();
		var businessIds = [];
		businessIds.push($.trim($(this).attr("data-id")));
		var url = Util.getRootPath() + "/w/fileuse/electronicLend/nextstepView?tablename="+tablename+"&ids="+businessIds+"&flag=HANDLE";
		
		var options = {}
		options.url = url;
    	options.title = "下一步处理人";
    	window.parent.showModal(options);
		
		
		
	});
	
	
	
	$("#tableDiv").on("click",".approve",function(){
		
		var handParam = {};
		//流程定义Id
		handParam.processId = $.trim($('[name="processId"]').val());
		//业务ID
		handParam.businessId = $(this).attr('data-business-id');
		
		handParam.operate = 'APPROVE';
		
		//处理流程
		Util.ajaxJsonSync(
			Util.getRootPath() + "/w/fileuse/electronicLend/handProcess",
			{	
				handParam: handParam,
			},
			function(result){
				if (result.success) {
					layer.msg(result.msg,{time: 2000});
					 refreshGrid();
				} else {
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			}, function (ex) {
				layer.alert("流程处理失败", {  skin: 'layui-layer-lan',closeBtn: 0});
			}
		);
	});
	
	$("#tableDiv").on("click",".reject",function(){
		var handParam = {};
		//流程定义Id
		handParam.processId = $.trim($('[name="processId"]').val());
		//业务ID
		handParam.businessId = $(this).attr('data-business-id');
		
		handParam.operate = 'REJECT';
		
		//处理流程
		Util.ajaxJsonSync(
			Util.getRootPath() + "/w/fileuse/electronicLend/handProcess",
			{	
				handParam: handParam,
			},
			function(result){
				if (result.success) {
					layer.msg(result.msg,{time: 2000});
					 refreshGrid();
				} else {
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			}, function (ex) {
				layer.alert("流程处理失败", {  skin: 'layui-layer-lan',closeBtn: 0});
			}
		);
	});
	
	
	
	//点击预览内容
    $("#tableDiv").on("click", "img", function(){
    	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
    	var fileid = $(this).attr("dagl-uuid");
    	var filename = $(this).attr("title");
    	console.log(filename);
    	$.ajax({
            type: "GET",
            url: Util.getRootPath() + "/w/preview/" + fileid,
            dataType: 'json',
            async: true,	
            success: function(result) {
				
				var option = {};
				option.title = filename;
				console.log(result);
				
				layer.close(index);
				//视频格式转成flv
				if(result.type == 'flv'){
					layer.close(index);
					option.url = Util.getRootPath() + "/w/preview/video/" + filename + "/" + result.cache;
					window.parent.showModal(option);
				}else if(result.type == 'mp3'){
					parent.layer.open({
		                type: 2,
		  			  	title:filename,
		                area: ['450px', '200px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: Util.getRootPath() + "/w/preview/viewAudio/" + fileid,
		              });
				}else if(result.type == 'others'){
					layer.confirm('其他格式的文件不能预览,是否需要下载?', {
						  skin: 'layui-layer-lan',
						  btn: ['是','否'] //按钮
					}, function(index){
						layer.close(index);
						window.location.href = Util.getRootPath() + "/w/common/downLoadAttach?filePath="+result.cache+"&fileName="+result.name;
					},function(index){
						 layer.close(index);
					});
					
				}else if(result.type == 'pdf'){
					parent.layer.open({
			              type: 2,
						  title:filename,
			              area: ['700px', '530px'],
			              fixed: false, //不固定
			              maxmin: true,
			              content: Util.getRootPath()+"/resources/plugins/pdfJs/generic/web/viewer.html?file="+Util.getRootPath()+"/w/preview/displayPDF/"+fileid,
			        });
				}else if(Util.isPIC(result.type)){
					//图片 
					window.open(result.cache);
				}else {
					// word excel txt pdf
					layer.close(index);
					option.url = result.cache;
					window.parent.showModal(option);
				}
			},
            error:function(XMLHttpRequest, textStatus, errorThrown){
            	layer.close(index);
            	layer.msg("文件不存在或被删除");
            }
    	});
    });
	
}

