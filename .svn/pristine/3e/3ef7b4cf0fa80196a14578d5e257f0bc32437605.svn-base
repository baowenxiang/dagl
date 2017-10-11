var grid;

$(function(){
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initGrid("");
	initMethod();
	layer.close(index);
//	selectFileTableName();
});
//form.on('submit(confirm)', function(data){
//	if(Util.checkNull(data.field.dalx)){
//		layer.alert("请选择档案类型", {  skin: 'layui-layer-lan',closeBtn: 0});
//		return false;
//	}
//	$.ajax({
//		type: 'POST',
//		url : Util.getRootPath() + "/w/fileStamp/doFileStamp",
//		async:false,
//		data:{
//		    tablename : data.field.tablename,
//		    dalx:data.field.dalx,		
//		    uuids : uuids,
//		},
//		traditional:true,
//		dataType:'json',
//		success:function(result){
//		    if(result.success){
//	    		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//    		    layer.msg(result.msg);
//    		    parent.layer.close(index);
//    		    parent.mainIframe.refreshGrid();
//		    }else{
//		        layer.msg(result.msg);
//		    }
//		},
//		error:function(error){
//		    layer.msg("");
//		}
//    });
//	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
//});
	function initMethod(){
		//点击预览内容
		$("#tableFileControl").on("click", "img", function(){
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
					}else{
						layer.close(index);
						// word excel txt pdf
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
	$("button.query").on("click",function(){
		var tablename = $('[name="tableselect"] option:selected').val();//选中的值
		var options = {}
		options.url = Util.getRootPath() + "/w/example/eep/PackagingQueryView?tablename="+tablename;
		options.title = "题名查询";
		
    	window.parent.showModal(options);
	});
	


function initGrid(tablename){
	var tablename = $('[name="tablename"]').val();
	$("#tableFileControl").children().remove();
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/example/eep/getPackaging",
	    ajaxLoad : true,
	    gridContainer : 'tableFileControl',
	    toolbarContainer : 'pagingFileControl',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : false,
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
						id:'cwrq',
						title : '<b>成文日期</b>',
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
		       			id:'uuid',
		       			title : '<b>下载</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
	       				 var html = "";
	       				 Util.ajaxJsonSync(
	   						 Util.getRootPath()+"/w/example/eep/getYwgj",
	   	                        {
	   							 id : record.uuid
	   	                        },
	   	                        function(result){
	   								if(result.success){
	   									var datas = result.datas;
	   									console.log(datas);
	   									if(datas.length>0){
	       									for(var i = 0;i<datas.length;i++){
	       										var wjlx = "eep";
	       										var wjm = "DH";
	       										console.log(datas);
	       										html += '<a href="/dagl/w/example/eep/download?filename=' + datas[i].fileName + '" >' + datas[i].fileName + '</a>';
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
//下载多个文档
function downloadFile(url) 
{   
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = url;   
        elemIF.style.display = "none";  
        elemIF.target="_blank"; 
        document.body.appendChild(elemIF);   
    }catch(e){ 
    
    } 
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