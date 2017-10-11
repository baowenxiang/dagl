//存储附件的集合
var savFiles = [];

$(function(){
	listenSubmit();
	
	initMethod();
	//加载一览页面原文文件
	
	initFileBtn();
	
});

//监听提交
function listenSubmit(){
	layui.use(['form','jquery'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
	    
	    form.on('submit(save)', function(data){
	    	var tablename = data.field.tablename;
		    //资料收集修改,上传原文
	        if (savFiles.length > 0) {
	            var fileData = new FormData($('form')[0]);
	            for (var i = 0, j = savFiles.length; i < j; ++i) {
	                fileData.append('attachment[]', savFiles[i]);
	            }
	            //资料id
	            $.ajax({
	                async : true,
	                cache: false,
	                type: 'POST',
	                dataType : "json",
	                data:fileData,
	                processData : false,
	                contentType : false,
	                beforeSend:function(){
                        index1 = layer.load(1, {shade: [0.1,'#FFFFFF']});
                    },
	                url: Util.getRootPath()+"/w/example/table/batchHook/"+tablename,
	                success: function(result){
	                    if(!result.success){
	                    	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	                        layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
	                    }else{
	                    	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	                    	var datas = result.datas;
			        		var html = "";
			        		html += '<div style="padding: 20px;position: relative;">';
			        		datas.forEach(function(value,index,array){
			        			html += "<div>"+value+"</div>";
			        		})
			        		html += "</div>";
			        		parent.layer.open({
					              type: 1,
								  title:'导入详情',
					              area: ['700px', '530px'],
					              fixed: false, //不固定
					              maxmin: true,
					              content:html
					        });
			        		
			        		//关闭弹出框
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
			        		//刷新表格
							parent.mainIframe.refreshGrid();
	                    }
	                },
	                error: function(ex) {
	                    layer.alert("上传附件请求失败", {  skin: 'layui-layer-lan',closeBtn: 0});
	                }
	            });
	        }
	    
	    return false;
	 });
	});
}



function initFileBtn(){
	//选择附件事件
	var attachment = document.getElementById("attachment");
	if (attachment != null) {
		attachment.onchange = function() {
			
			var addFiles = attachment.files;
			if (addFiles && addFiles.length) {
				// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
				Array.prototype.push.apply(savFiles, addFiles);
			}
			var $fileDiv = $(".file-div");
			$(".file-none").remove();
			var html = '';
			for (var i = 0; i < addFiles.length; i++) {
				var wjdx = Util.bytesToSize(addFiles[i].size);
				
				html += '<div>';
				html += '	<div>';
				html += '	<i class="fa fa-file"></i>';
				html += '	<a class="file">'+addFiles[i].name+'</a>';
				html += '	<span class="file-size">'+wjdx+'</span>';
				html += '	<span class="file-download" style="display:none;" title="下载"><i class="fa fa-download"></i></span>';
				html += '	<span class="file-remove"><i class="fa fa-close fa-lg"></i></span>';
				html += '	</div>';
				html += '</div>';
				
			}
			$fileDiv.append(html);
		}
	}
}


function initMethod(){
	//关闭弹出层
	$("a.closeup").on("click",function(){
		 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		 parent.layer.close(index);
	})
	
	
	
	//删除原文
	$(".file-div").on("click", ".file-remove", function () {
		var $this = $(this);
		layer.confirm('是否删除该原文？', {
			  skin: 'layui-layer-lan',
			  btn: ['删除','取消'] //按钮
			}, function(){
					// 去除该文件
					var name = $this.prev().prev().prev().text();
					savFiles = savFiles.filter(function(file) {
						return file.name !== name;
					});
					
				$this.parent().parent("div").remove();
				layer.msg('已删除',{time: 1000});
			}, function(){
				layer.msg('已取消',{time: 1000});
			});
	});
}
