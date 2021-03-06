 var grid;
$(function(){
//	var index = layer.load(1, {shade: [1,'#FFFFFF']});
    initGrid();
    
    initMethod();
    
//    layer.close(index);
});
	//表格初始化
    function initGrid(){
    	var tablename = $('[name="tablename"]').val();
    	var auth_data = $.trim($('[name="auth_data"]').val());
    	var auth_look = $.trim($('[name="auth_look"]').val());
    	var auth_delete = $.trim($('[name="auth_delete"]').val());
    	var index = layer.load(1, {shade: [1,'#FFFFFF']});
    	$("#tableDataCollect").children().remove();
	    	$.ajax({
	             type: "POST",
				 async:false,
	             contentType: "application/json",
	             url: "/dagl/w/example/table/getHeader/" + tablename,
	             dataType: 'json',
	             success: function(result) {
	            	 var headers = result.datas;
	            	 
	            	 for(var i = 0;i<headers.length;i++){
	            		 headers[i].columnStyle='-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;';
	            		 headers[i].resolution = function(value, record, column, grid, dataNo, columnNo){
								return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
	            		 };
						
						
	            		 if(headers[i].id == "isarchive"){
	            			 headers[i].resolution = function(value, record, column, grid, dataNo, columnNo){
				       			if(value == 0){
				       				return "未处理";
				       				
				       			}else if(value == 1){
				       				return "审批中";
				       			}else if(value == 2){
				       				return "审批未通过";
				       			}else {
				       				return "审批通过";
				       			}
	            			 };
	            		 }
	            		 
	            	 } 
	            	 if(auth_data){
	            		 var header1 ={};
		            	 header1.id = "uuid";
		            	 header1.title = "挂接原文";
		            	 header1.type = "string";
		            	 header1.columnClass = "text-center";
		            	 header1.resolution = function(value, record, column, grid, dataNo, columnNo){
	            				 var html = "";
	            				 Util.ajaxJsonSync(
            						 Util.getRootPath()+"/w/example/flow/getYwgj",
            	                        {
            							 id : record.uuid
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
			       		headers.push(header1);
	            	 }
			        if($('[name="flag"]').val() === 'true'){
			        	var header2 = {};
			        	header2.id = "uuid";
			        	header2.title = "详情";
			        	header2.type = "string";
			        	header2.columnClass = "text-center";
			        	header2.resolution = function(value, record, column, grid, dataNo, columnNo){
			        		var html = ''; 	
			        		html += '	<a title="详情" class="btn btn-default btn-xs relative" data-id="'+record.uuid+'">详情</a>';
			        		return html;
			        	}	
			        	headers.push(header2); 
			        }
	            	//判断字段是原文挂接的处理
			        if(auth_look || auth_delete){
			        	 var header ={};
		            	 header.id = "uuid";
		            	 header.title = "操作";
		            	 header.type = "string";
		            	 header.columnClass = "text-center";
		            	 header.resolution = function(value, record, column, grid, dataNo, columnNo){
			       				var html = ''; 	
			       				html += '<div class="btnDiv">';
			       				if(auth_look){
			       					html += '	<a title="查看" class="btn btn-default btn-xs detail" data-id="'+record.uuid+'">查看</a>';
			       				}
			       				if(auth_delete){
			       					html += '<a title="删除" class="btn btn-danger btn-xs delete" data-id="'+record.uuid+'">删除</a>';
			       				}
			       				html += '</div>';
//			       				layer.close(index);
			       				return html;
			       		};
			       		headers.push(header);
			        }
		       		grid=$.fn.DtGrid.init({
		       			loadURL : Util.getRootPath() + "/w/example/pztable/getList/"+tablename,
		       			ajaxLoad : true,
                        lang : 'zh-cn',
                        exportFileName : '用户列表',
		       		    gridContainer : 'tableDataCollect',
		       		    toolbarContainer : 'pagingDataCollect',
		       		    check:true,
		       		    pageSize : 20,
		       		    pageSizeLimit : [20,50,100,300],
		       		    tools:'', 
		       		    tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
		       		    columns : headers		       			
		       		});
		       		layer.close(index);
	                 refreshGrid();
	             }
	    });
    } 
  
    //刷新表格
    function refreshGrid() {
    	grid.parameters = getParameters();
    	grid.load();
    }
    function getParameters() {
    	return{
    	}
    }

function initMethod(){
	//点击案卷级详情按钮
	$("#tableDataCollect").on("click","a.relative",function(){
		var tablename = $('[name="tablename"]').val();
		var relatablename = $('[name="relatablename"]').val();
		var id = $.trim($(this).attr("data-id"));
		var options = {}
		var url = Util.getRootPath() + "/w/example/table/relative/"+tablename+"/"+relatablename+"/"+id;
    	options.url = url;
    	options.title = "关联文件展示";
    	window.parent.showModal(options);
	});
	
	
	  //弹出模态框查看详情
    $("#tableDataCollect").on("click","a.detail",function(){
    	var tablename = $('[name="tablename"]').val();
    	var id = $.trim($(this).attr("data-id"));
    	var options = {}
    	var url = Util.getRootPath() + "/w/example/table/detail/"+tablename+"/"+id;
    	options.url = url;
    	options.title = "详情展示";
    	window.parent.showModal(options);
    });
    
    //点击删除内容
    $("#tableDataCollect").on("click","a.delete",function(){
    	var tablename = $('[name="tablename"]').val();
    	var id = $.trim($(this).attr("data-id"));
    	
    	layer.confirm('确定要删除档案吗?', {
		  skin: 'layui-layer-lan',
		  btn: ['确定','取消'] //按钮
		}, function(index){
			
	    	$.ajax({
	    		type: "GET",
	    		contentType: "application/json",
	    		url: Util.getRootPath()+"/w/example/table/delete/"+tablename+"/"+id,
	    		dataType:'json',
	    		success:function(result){
	    			
	    			if(result.success){
	    				layer.msg(result.msg,{time: 2000});
	    				layer.close(index);
	    				
	    				refreshGrid();
	    			}else{
	    				layer.alert(result.msg, {skin: 'layui-layer-lan',closeBtn: 0});
	    			}
	    		}
	    		
	    	});
		},function(index){
			 layer.close(index);
		});
    });
    //点击预览内容
    $("#tableDataCollect").on("click", "img", function(){
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

}
