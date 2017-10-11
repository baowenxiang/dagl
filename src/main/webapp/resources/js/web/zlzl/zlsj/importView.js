//上传原文模态框文件集合
var importFiles = [];


$(function(){
	initImportBtn();
	initMethod();
	
});



layui.use(['form','jquery'], function(){
    var $ = layui.jquery,
    form = layui.form();
});


function initImportBtn(){
	//选择附件事件
	var importAttachment = document.getElementById("importAttachment");
	if (importAttachment != null) {
		importAttachment.onchange = function() {
			var addFiles = importAttachment.files;
			if (addFiles && addFiles.length) {
				// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
				Array.prototype.push.apply(importFiles, addFiles);
			}
			var $fileDiv = $(".add-file-div");
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
		$("a#close").on("click",function(){
			 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			 parent.layer.close(index);
		})
		
		
		//增加按钮中删除原文
		$(".add-file-div").on("click", ".file-remove", function () {
			var $this = $(this);
			layer.confirm('是否删除该附件？', {
				  skin: 'layui-layer-lan',
				  btn: ['删除','取消'] //按钮
				}, function(){
					// 去除该文件
					var name = $this.prev().prev().prev().text();
					importFiles = importFiles.filter(function(file) {
						return file.name !== name;
					});
					$this.parent().parent("div").remove();
					layer.msg('已删除',{time: 1000});
				}, function(){
					layer.msg('已取消',{time: 1000});
				});
		});	
		
		//点击保存按钮
		$("#import").on("click",function(){
			var tablename = $.trim($('[name="tablename"]').val());
			if (importFiles.length > 0) {
				var fileData = new FormData($('mailForm')[0]);
    			for (var i = 0, j = importFiles.length; i < j; ++i) {
    				fileData.append('attachment[]', importFiles[i]);
    			}
				fileData.append('tablename',tablename);
					$.ajax({
            	        async : false,
            	        cache: false,
            	        type: 'POST',
            	        dataType : "json",
            	        data: fileData,
            			processData : false,
            			contentType : false,
            	        url: Util.getRootPath() + "/w/example/tools/importTemplet1",
            	        success: function(result){
            	        	if(!result.success){
            	        		layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
            	        	}else{
            	        		layer.msg(result.msg,{time: 2000});
            	        		//$("#importTempletModal").modal("hide");
            	        		//refreshGrid();
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
    		}else{
    			layer.alert("请选择模版", {  skin: 'layui-layer-lan',closeBtn: 0});
    			return;
    		}	
		});
		
		
		
		
	}
	

	
	
