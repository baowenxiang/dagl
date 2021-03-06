var grid;

$(function(){
	 $("button b").show();
    initGrid();
    initMethod();
});

function initGrid(){
	 grid=$.fn.DtGrid.init({
         loadURL : Util.getRootPath() + "/w/condition/getList",
         ajaxLoad : true,
         lang : 'zh-cn',
         exportFileName : '用户列表',
         gridContainer : 'tableDataCollect',
         toolbarContainer : 'pagingDataCollect',
         pageSize : 20,
         pageSizeLimit : [20,50,100,300],
         tools:'', 
         tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
         columns : [
				{
					id:'dh',
					title : '<b>档号</b>',
					type:"string",
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
					columnClass:'text-center',
					columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
					resolution:function(value, record, column, grid, dataNo, columnNo){
						return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
					}
				
				},
				{
					id:'cwrq',
					title : '<b>成文日期</b>',
					type:"string",
					columnClass:'text-center',
					columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
					resolution:function(value, record, column, grid, dataNo, columnNo){
						return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
					}
				
				},
				{
					id:'zrz',
					title : '<b>责任者</b>',
					type:"string",
					columnClass:'text-center',
					columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
					resolution:function(value, record, column, grid, dataNo, columnNo){
						return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
					}
				
				},
				{
					id:'zrz',
					title : '<b>档案类型</b>',
					type:"string",
					columnClass:'text-center',
					columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
					resolution:function(value, record, column, grid, dataNo, columnNo){
						return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
					}	
				},
				{
	       			id:'id',
	       			title : '<b>挂接原文</b>',
	       			type:"string",
	       			columnClass:'text-center',
	       			resolution:function(value, record, column, grid, dataNo, columnNo){

       				 var html = "";
       				 Util.ajaxJsonSync(
   						 Util.getRootPath()+"/w/condition/getYwgj",
   	                        {
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
			]                       
     });
	 refreshGrid();
}

//刷新表格
function refreshGrid() {
	grid.parameters = getParameters();
	grid.load();
}
function getParameters() {
    return{
    	tm : $.trim($("[name='tm']").val()),
    	dh : $.trim($("[name='dh']").val()),
    	zrz : $.trim($("[name='zrz']").val()),
    	cwrq_min : $.trim($("[name='cwrq_min']").val()),
    	cwrq_max : $.trim($("[name='cwrq_max']").val()),
    	dalx : $.trim($("[name='dalx']").val())
    }
}


function initMethod(){
	$("button.back").on("click",function(){
		history.go(-1);
	});
	
	$("#tableDataCollect").on("click", "img", function(){
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
					 layer.open({
			              type: 2,
						  title:options.title,
			              area: ['700px', '530px'],
			              fixed: false, //不固定
			              maxmin: true,
			              content: Util.getRootPath() + "/w/preview/video/" + filename + "/" + result.cache,
			           });
				}else if(result.type == 'mp3'){
					layer.open({
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
					layer.open({
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
					//图片 word excel txt pdf
					layer.close(index);
					layer.open({
		                type: 2,
		  			  	title:filename,
		                area: ['700px', '530px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: result.cache,
					   });
				}
			},
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	layer.close(index);
	        	layer.msg("文件不存在或被删除");
	        }
		});
	});
}
