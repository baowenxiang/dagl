var grid;//档案信息
var preGrid;//预编译信息
$(function(){
	/*$(".btn-icon").on("mouseenter", function(){
        $this = $(this);
        $this.find('b').show();
     });
     
     $(".btn-icon").on("mouseleave", function(){
        $this = $(this);
        $this.find('b').hide();
     });*/
	$("button b").show();
	
	
	initGrid("");
	initMethod();
	
	initPreGrid("");
});

function initMethod(){
	//点击预编研按钮
	$("button.preCompile").on("click",function(){
		if(grid.getCheckedRecords().length === 0){
			layer.alert("请选择需要编研项！", {  skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		
		var datas = preGrid.exhibitDatas;;
		var gridDatas = grid.getCheckedRecords();//勾选的
		var preGridDatas = preGrid.exhibitDatas;//之前的
		var preGridIds = [];
		var flag = 0;
		for(var i = 0;i<preGridDatas.length;i++){
			preGridIds.push(preGridDatas[i].uuid);
		}
		
		for(var j = 0;j<gridDatas.length;j++){
			if(preGridIds.indexOf(gridDatas[j].uuid) == -1){
				(gridDatas[j])['bm'] = $.trim($('[name="filetablename"]').val());
				datas.push(gridDatas[j]);
			}else{
				flag++;
			}
		}
		
		if(flag === gridDatas.length){
			layer.alert("选择档案信息重复！", {  skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		/*//每条记录拼接表名
		for(var i = 0 ; i < datas.length;i++){
			
			(datas[i])['bm'] = $.trim($('[name="filetablename"]').val());
		}*/
		
		
		
		preGrid.originalDatas = datas;
		preGrid.baseDatas = datas;
		preGrid.exhibitDatas = datas;
		preGrid.reload();
	});
	
	//点击编研按钮
	$("button.compile").on("click",function(){
		var records = preGrid.getCheckedRecords();
		if(records.length === 0){
			layer.alert("请选择编研信息项！", {  skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		var options = {};
		options.url = Util.getRootPath() + "/w/compilation/compileMade/toCompileFormView";
		options.title = "编写编研成果";
    	window.parent.showModal(options);
	});
	
	//点击取消预编研
	$("button.cancel").on("click",function(){
		var preGridDataDel = [];
		var preGridDatas = preGrid.exhibitDatas;//预编研所有的
		var preGridCheck = preGrid.getCheckedRecords();//预编研勾选的
		var preGridCheckIds = [];
		for(var i = 0;i<preGridCheck.length;i++){
			preGridCheckIds.push(preGridCheck[i].uuid);
		}
		
		for(var j = 0;j<preGridDatas.length;j++){
			if(preGridCheckIds.indexOf(preGridDatas[j].uuid) == -1){
				preGridDataDel.push(preGridDatas[j]);
			}
		}
		
		preGrid.originalDatas = preGridDataDel;
		preGrid.baseDatas = preGridDataDel;
		preGrid.exhibitDatas = preGridDataDel;
		preGrid.reload();
		
	});
	
	
	$("button.query").on("click",function(){
		var tablename = $.trim($('[name="filetablename"]').val());//选中的值
		var options = {}
		options.url = Util.getRootPath() + "/w/compilation/queryView?tablename="+tablename;
		options.title = "题名查询";
		
    	window.parent.showModal(options);
	});
	$("#preCompileTableDiv,#fileInfoTableDiv").on("click", "img", function(){
		var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
		var fileid = $(this).attr("dagl-uuid");
		var filename = $(this).attr("title");
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
				}else{
					//word excel txt pdf
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

function initGrid(tablename){
	$("#fileInfoTableDiv").children().remove();
	
	
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/compilation/getFilesInfo",
	    ajaxLoad : true,
	    gridContainer : 'fileInfoTableDiv',
	    toolbarContainer : 'fileInfoPagingDiv',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    pageSizeLimit : [20,50,100,300],
	    tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
	    columns : [
		       		{
		       			id:'tm',
		       			title : '<b>题名</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		/*{
		       			id:'zrz',
		       			title : '<b>责任者</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'wh',
		       			title : '<b>文号</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'cwrq',
		       			title : '<b>成文日期</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			columnStyle:'width:130px',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'mj',
		       			title : '<b>密级</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       			
		       		},*/
		       		{
		       			id:'bgqx',
		       			title : '<b>保管期限</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}

		       		},{
		       			id:'uuid',
		       			title : '<b>挂接原文</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){

           				 var html = "";
           				 Util.ajaxJsonSync(
       						 Util.getRootPath()+"/w/example/flow/getYwgj",
       	                        {
       							 id : value
       	                        },
       	                        function(result){
       								if(result.success){
       									
       									var datas = result.datas;
       									if(datas.length>0){
           									for(var i = 0;i<datas.length;i++){
           										var wjlx = datas[i].wjlx;
           										var wjm = datas[i].wjm;
           										if(wjlx == 'pdf'){
           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/pdf.svg" />';
           										}else if(wjlx == 'doc' || wjlx == 'docx'){
           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/word.svg" />';
           										}else if(wjlx == 'xlsx' || wjlx == 'xls'){
           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/excel.svg" />';
           										}else if(Util.isAudio(wjlx)){
           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/audio.svg" />';
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
		       		}
		       		]
	});
	refreshGrid(tablename);
}

function refreshGrid(tablename) {
	//赋值
	$('[name="filetablename"]').val(tablename);
	
	grid.parameters = getParameters(tablename);
	grid.load();
}
function getParameters(tablename) {
	return{
		tablename : tablename
	}
}



function initPreGrid(datas){
	$("#preCompileTableDiv").children().remove();
	preGrid = $.fn.DtGrid.init({
		ajaxLoad : false,
	    gridContainer : 'preCompileTableDiv',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    datas:datas,
	    pageSizeLimit : [20,50,100,300],
	    tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
	    columns : [
		       		{
		       			id:'tm',
		       			title : '<b>题名</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		/*{
		       			id:'zrz',
		       			title : '<b>责任者</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'wh',
		       			title : '<b>文号</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'cwrq',
		       			title : '<b>成文日期</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'mj',
		       			title : '<b>密级</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       			
		       		},*/
		       		{
		       			id:'bgqx',
		       			title : '<b>保管期限</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}

		       		},
		       		{
		       			id:'uuid',
		       			title : '<b>挂接原文</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){

           				 var html = "";
           				 Util.ajaxJsonSync(
       						 Util.getRootPath()+"/w/example/flow/getYwgj",
       	                        {
       							 id : value
       	                        },
       	                        function(result){
       								if(result.success){
       									
       									var datas = result.datas;
       									if(datas.length>0){
           									for(var i = 0;i<datas.length;i++){
           										var wjlx = datas[i].wjlx;
           										var wjm = datas[i].wjm;
           										if(wjlx == 'pdf'){
           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/pdf.svg" />';
           										}else if(wjlx == 'doc' || wjlx == 'docx'){
           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/word.svg" />';
           										}else if(wjlx == 'xlsx' || wjlx == 'xls'){
           											html += '<img dagl-uuid="' + datas[i].id + '" title="'+wjm+'"  src="' + Util.getRootPath() + '/resources/img/icon_16/excel.svg" />';
           										}else if(Util.isAudio(wjlx)){
           											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" src="' + Util.getRootPath() + '/resources/img/icon_16/audio.svg" />';
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
		       			id:'bm',
		       			title : '<b>所属表名</b>',
		       			type:"string",
		       			columnClass:'text-center text-fixed',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		}
		       		]
	});
	preGrid.load();
	
	
}