//存储附件的集合
var openedFileGrid;

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
	
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initMethod();
	
	initTable();
	layer.close(index);
	
});

//初试化方法
function initMethod(){
	
	$("button.back").on("click",function(){
		var business = openedFileGrid.getCheckedRecords();
		if(business.length==0){
			layer.alert("请选择撤销选项！", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		var businessIds = [];
		for(var i=0;i<business.length;i++){
			businessIds.push(business[i].id);
		}
		
		
		layer.confirm('确定需要撤销所选'+business.length+'项', {
			  skin: 'layui-layer-lan',
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url : Util.getRootPath()+"/w/ykfda/cancelOpenedFile",
					type:'POST', //GET
					async:true,
					data:{
						ids:businessIds
					},
					dataType:'json',
					success:function(data,textStatus,jqXHR){
						if(data.success){
							layer.msg(data.msg,{time: 2000});
							refreshOpenedFileGrid();
						}
					}
				});
						
				
				
			}, function(){});
	});
	
	$("button.export").on("click",function(){
		
		var records = openedFileGrid.getCheckedRecords();//记录
		if (records.length==0) {
			//打包导出
			location.href = Util.getRootPath() + "/w/ykfda/exportOpenedFileInfo/null";
		}else{
			var ids = [];
			for(var i = 0;i<records.length;i++){
				ids.push(records[i].id);
			}
			ids = ids.join(',');
			//打包导出
			location.href = Util.getRootPath() + "/w/ykfda/exportOpenedFileInfo/"+ids;
		}
	});
	
}


function initTable(){
		var ywgj ={};
		ywgj.id = "daid";
		ywgj.title = "挂接原文";
		ywgj.type = "string";
		ywgj.columnClass = "text-center";
		ywgj.headerStyle = 'width:6%';
		ywgj.resolution = function(value, record, column, grid, dataNo, columnNo){
				 var html = "";
				 Util.ajaxJsonSync(
					 Util.getRootPath()+"/w/example/flow/getYwgj",
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
											html += '<img dagl-uuid="' + datas[i].id  + '" title="'+wjm+'" onclick="" src="' + Util.getRootPath() + '/resources/img/icon_16/pdf.svg" />';
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
		 };
	openedFileGrid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/ykfda/getOpenedFileList",
	    ajaxLoad : true,
	    gridContainer : 'openedFileControlTable',
	    toolbarContainer : 'pagingOpenedFileControl',
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
							id:'qzh',
							title : '<b>全宗号</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'ndh',
							title : '<b>年度号</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'wh',
							title : '<b>文号</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'mlh',
							title : '<b>目录号</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'ajh',
							title : '<b>案卷号</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'bgqx',
							title : '<b>保管期限</b>',
							type:"string",
							columnClass:'text-center',
							resolution:function(value, record, column, grid, dataNo, columnNo){
			       				var bgqx; 
								Util.ajaxJsonSync(
									Util.getRootPath()+"/w/dicmanager/getDicValueByDno?dno=bgqx&dvno="+value,
									{
									},
									function(result){
										if(result.success){
											bgqx = result.data;
										}
									}
								)
								
								return bgqx;
			       			}
					
						},
						ywgj
						]
	});
	refreshOpenedFileGrid();
}

//点击预览内容
$("#openedFileControlTable").on("click", "img", function(){
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
				//word excel txt pdf
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

function refreshOpenedFileGrid() {
	openedFileGrid.parameters = getOpenedFileParameters();
	openedFileGrid.load();
}
function getOpenedFileParameters() {
	
}

