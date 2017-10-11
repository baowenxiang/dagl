//存储头像图片的集合
var headPicFile = [];
//存储被删掉的图片id
var removeheadPicId = [];
//存储更新所选头像
var curFiles = [];
$(function(){
	initImgStyle();
	initContent();
	//初始化方法
	initMethod();
	//初始化头像file
//	initHeadPicBtn();
	initAttachment();
});
//初始化头像样式
function initImgStyle(){
//	var height = $(window).height();
//	$(".content-fixed").height(height - 38 - 30 + "px");//38: pdding-top  15: pdding-top  15: pdding-bottom
	//获取图片宽度，设置为高度
	$("#imghead").width($("#preview").width()).height($("#preview").width());
}
//头像上传处理
function initAttachment() {
	var attachment = document.getElementById("headPic");
	attachment.onchange = function () {
		//判断上传的文件是否为图片，若不是，做出提醒
		var files = attachment.files;
		for (var i = 0; i < files.length; i++) {
			var file = files[i];
			var filestr = file.name.split(".")[1].toUpperCase();
			if(filestr != "PNG" && filestr != "JPG" && filestr != "JPEG"){
				layer.alert('只能上传PNG、JPG、JPEG格式的图片！', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
				return;
			}
		}
		//对选中图片进行预览
		var objUrl = getObjectURL(this.files[0]) ;
		if (objUrl) {
			$("#imghead").attr("src", objUrl) ;
		}
	}
}


//建立一个可存取到该file的url
function getObjectURL(file) {
var url = null ; 
if (window.createObjectURL != undefined) { // basic
	url = window.createObjectURL(file) ;
} else if (window.URL != undefined) { // mozilla(firefox)
	url = window.URL.createObjectURL(file) ;
} else if (window.webkitURL != undefined) { // webkit or chrome
	url = window.webkitURL.createObjectURL(file) ;
}
return url ;
}


function initContent(){
	$.ajax({
		url : Util.getRootPath()+"/w/sysManage/getDtoUserDetail",
		type:'POST', //GET
		async:false,
		dataType:'json',
		data:{},
		success:function(data,textStatus,jqXHR){
			var data = data.data;
			//赋值
			$("#username").val(data.user.username);
			$('[name="userLogin"]').val(data.user.username);
			$("#name").val(data.user.name);
			$("#gender").val(data.user.gender);
			//$('[name="oldPassword"]').val(data.user.password);
			if(data.userDetail != null){
				if(data.userDetail.picPath == "") {
					
				}else {
					$("#imghead").attr("src",data.userDetail.picPath);
				}
				$("#telphone").val(data.userDetail.telphone);
				$("#appellation").val(data.userDetail.appellation);
				$("#createtime").val(data.userDetail.createTime);
				var $fileDiv = $(".headPic-file");
				var html = '';
					html += '<div class="col-sm-12">';
					html += '	<i class="fa fa-file"></i>';
					html += '	<a class="file" data-path="'+data.userDetail.picPath +'">'+data.userDetail.picName+'</a>';
					html += '	<span class="file-remove"><i class="fa fa-close"></i></span>';
					html += '</div>';
				$fileDiv.append(html);
				
			}
			$("#lastTime").val(data.lastTime);
			$("#loginNum").val(data.loginNum);
			
		},
		error:function(ex) {
			layer.alert("查询失败", {  skin: 'layui-layer-lan',closeBtn: 0});
		}
	});
}

//初始化方法
function initMethod(){
	//删除原文
	$(".headPic-file").on("click", ".file-remove", function () {
		var $this = $(this);
		var attaId = $this.prev().attr("data-id");
		layer.confirm('是否删除头像？', {
			  skin: 'layui-layer-lan',
			  btn: ['删除','取消'] //按钮
			}, function(){
				headPicFile = [];
				$this.parent("div").remove();
				layer.msg('已删除',{time: 1000});
			}, function(){
				layer.msg('已取消',{time: 1000});
			});
	});	
	
	$(".saveUserDetail").on("click",function(){
		//个人信息上传
		var attachment = document.getElementById("headPic");
		var files = attachment.files;
		if (files && files.length) {
			for (var i = 0; i < files.length; i++) {
				var file = files[i];
				var filestr = file.name.split(".")[1].toUpperCase();
				if(filestr != "PNG" && filestr != "JPG" && filestr != "JPEG"){
				} else {
					// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
					Array.prototype.push.apply(curFiles, files);
				}
			}
			
		}
		var fileData = new FormData($('userForm')[0]);
//		var fileData = new FormData($('.baseinfo')[0]);
		if (curFiles.length > 0) {
			for (var i = 0, j = curFiles.length; i < j; ++i) {
				fileData.append('attachment', curFiles[i]);
			}
		}else {
			fileData.append('attachment',null);
		}
		fileData.append("telphone", $.trim($("#telphone").val()));
		fileData.append("appellation", $.trim($("#appellation").val()));
		fileData.append("name", $.trim($("#name").val()));
		fileData.append("gender", $.trim($("#gender").val()));
		console.log(fileData);
		$.ajax({
	        async : false,
	        cache: false,
	        type: 'POST',
	        dataType : "json",
	        data: fileData,
			processData : false,
			contentType : false,
	        url: Util.getRootPath() + "/w/sysManage/saveUserDetail",
	        success: function(result){
	        	if(!result.success){
	        		layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
	        	}else{
	        		layer.msg(result.msg,{time: 1000});
	        	}
	        },
	        error: function(ex) {
	        	layer.alert("修改人员操作失败", {  skin: 'layui-layer-lan',closeBtn: 0});
	        }
		});

	});
	$(".passwordUpdate").on("click",function(){
		$(".modal").modal('show');
	});
	
	$(".updatePassword").on("click",function(){
		var oldPassword =  MD5($.trim($('[name="oldPassword"]').val()));
		var password =  MD5($.trim($('[name="password"]').val()));
		var confirmPass =  MD5($.trim($('[name="confirmPass"]').val()));
		$.ajax({
			url : Util.getRootPath() + "/w/sysManage/updatePassword",
			type:'POST', //GET
			async:true,
			data:{
				oldPassword:oldPassword,
				password:password
			},
			dataType:'json',
	        success: function(result){
	        	if(!result.success){
	        		layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
	        	}else{
	        		layer.msg(result.msg,{time: 1000});
	        		$(".modal").hide();
	        	}
	        },
	        error: function(ex) {
	        	layer.alert("修改密码操作失败", {  skin: 'layui-layer-lan',closeBtn: 0});
	        }
		});
	});
}

//function initHeadPicBtn(){
	//选择附件事件
//	var headPic = document.getElementById("headPic");
//	if (headPic != null) {
//		headPic.onchange = function () {
//			var length = $(".headPic-file").children("div").length;
//			if(length == 0){
//				var files = headPic.files;
//				if (files && files.length) {
//					// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
//					Array.prototype.push.apply(headPicFile, files);
//				}
//				var $fileDiv = $(".headPic-file");
//				var html = '';
//				for (var i = 0; i < files.length; i++) {
//					var file = files[i];
//					html += '<div class="col-sm-12">';
//					html += '	<i class="fa fa-file"></i>';
//					html += '	<a class="file" data-id="">'+file.name+'</a>';
//					html += '	<span class="file-remove"><i class="fa fa-close"></i></span>';
//					html += '</div>';
//					break;
//				}
//				$fileDiv.append(html);
//			}
//		}
//	}
	
	
//}