//存储附件的集合
var savFiles = [];
//存储被删掉的文件id
var removeFileIds = [];

	
$(function(){
	//加载一览页面原文文件
	initFileInfo();
	initFileBtn();
	initMethod();
	
	
	
});

function initFileInfo(){
	var detailId = $.trim($('[name="detailId"]').val());
	if(!Util.checkNull(detailId)){
		

		$.ajax({
			url : Util.getRootPath()+"/w/zlzl/zlsj/getDataInfoById",
			type:'POST', //GET
			async:false,
			data:{
				id:detailId
			},
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				//原文
				var files = data.data.files;
				var $fileDiv = $(".file-div");
				var html = '';
				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					
					var wjdx = Util.bytesToSize(file.wjdx);
					
					html += '<div>';
					html += '	<div>';
					html += '	<i class="fa fa-file"></i>';
					html += '	<a class="file" data-path="'+file.wjdz +'" data-id="'+file.id+'" title="'+file.wjm+'">'+file.wjm+'</a>';
					html += '	<span class="file-size">'+wjdx+'</span>';
					html += '	<span class="file-download" title="下载"><i class="fa fa-download fa-lg"></i></span>';
					html += '	<span class="file-remove"><i class="fa fa-close fa-lg"></i></span>';
					html += '	</div>';
					html += '</div>';
				}
				$fileDiv.append(html);
				
			},
			error:function(ex) {}
		});
	}
}


function initMethod(){
	//下载附件
	$(".file-div").on("click","span.file-download",function(){
		var path = $.trim($(this).prev().prev().attr("data-path"));
		var name = $.trim($(this).prev().prev().text());
		
		window.location.href = Util.getRootPath() + "/w/common/downLoadAttach?filePath="+path+"&fileName="+name;
	})
	
	
	
	//删除原文
	$(".file-div").on("click", ".file-remove", function () {
		var $this = $(this);
		var attaId = $this.prev().prev().prev().attr("data-id");
		layer.confirm('是否删除该原文？', {
			  skin: 'layui-layer-lan',
			  btn: ['删除','取消'] //按钮
			}, function(){
				if (attaId == null || attaId == undefined || $.trim(attaId) == "") {
					// 去除该文件
					var name = $this.prev().prev().prev().text();
					savFiles = savFiles.filter(function(file) {
						return file.name !== name;
					});
					
				} else {
					//将删除的文件id保存到removeFileInput中
					removeFileIds.push(attaId);
				}
				$this.parent().parent("div").remove();
				layer.msg('已删除',{time: 1000});
			}, function(){
				layer.msg('已取消',{time: 1000});
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