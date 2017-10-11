//上传原文模态框文件集合
var importFiles = [];


$(function(){
	initImportBtn();
	initMethod();
	
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
				html += '<div style="width:100%;">';
				html += '	<i class="fa fa-file"></i>';
				html += '	<a class="file">'+addFiles[i].name+'</a>';
				html += '	<span class="file-remove"><i class="fa fa-close"></i></span>';
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
					var name = $this.prev().text();
					importFiles = importFiles.filter(function(file) {
						return file.name !== name;
					});
					$this.parent("div").remove();
					layer.msg('已删除',{time: 1000});
				}, function(){
					layer.msg('已取消',{time: 1000});
				});
		});	
		
		//点击保存按钮
		$("#import").on("click",function(){
			var tablename = $.trim($('[name="tablename"]').val())
			if (importFiles.length > 0) {
				var fileData = new FormData($('mailForm')[0]);
				for (var i = 0, j = importFiles.length; i < j; ++i) {
					fileData.append('attachment[]', importFiles[i]);
				}
				
				fileData.append('tablename',tablename);
				fileData.append('isarchive','0');//预归档
				console.log(fileData);
				$.ajax({
			        async : false,
			        cache: false,
			        type: 'POST',
			        dataType : "json",
			        data: fileData,
					processData : false,
					contentType : false,
			        url: Util.getRootPath() + "/w/example/tools/importFileDef",
			        success: function(result){
			        	if(!result.success){
			        		var loading = layer.load(1, {shade: [0.1,'#FFFFFF']});
			        		layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
			        	}else{
			        		//layer.msg(result.msg,{time: 2000});
			        		var loading = layer.load(1, {shade: [0.1,'#FFFFFF']});
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
				
			}else{
				layer.alert("请输入需要导入的模版", {  skin: 'layui-layer-lan',closeBtn: 0});
				return;
			}
		});
		
	}
	

	
	
