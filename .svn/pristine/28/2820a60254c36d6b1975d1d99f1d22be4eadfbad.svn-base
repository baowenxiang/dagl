//存储登录页面背景图
var curFiles = [];
//存储首页header图片
var headerCurFiles = [];
//存储皮肤图片
var skinCurFiles = [];
//当前登录人id
var userId = parent.userId;
$(function() {
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initColorBlock();
	setSettingMsg();
	initBgPic();
	initSetting();
	initStyle();
	//初始化登录用户的皮肤设置
//	var personalSkin = parent.personalSkin;
	$("#skinClass").attr("data-class",parent.personalSkin);
	$("#skinClass").attr("data-id",parent.personelSkinId);
	layer.close(index);
});
var $defaultBlock;	//用户设置的皮肤颜色
//设置页面加载当前已经设置的数据，而不是数据留白
function setSettingMsg() {
	//设置默认值
	Util.ajax(
		Util.getRootPath() + "/w/setting/getSettingMsg",
		{},
		function(result){
			result = result.data;
			if(result != null) {
				//设置平台名称
				$("input[name='plateName']").val(result.plateLgName);
				$("input[name='miniName']").val(result.plateMiniName);
				//标记背景图片
				if(result.bgPicPath != null && result.bgPicPath != "") {
					$("#bgPic").attr("src",result.bgPicPath);
				}
				//设置Headerlogo图片
				if(result.headerPicPath != null && result.headerPicPath != "") {
					$("#imghead").attr("src",result.headerPicPath);
				}
				//标记设置的皮肤或默认的皮肤
				if(result.skinClass != null && result.skinClass != "") {
					$(".colorDiv").each(function() {
						if($(this).attr("id") == result.skinClass) {
							$defaultBlock = $(this);
							$defaultBlock.addClass("select");
							$defaultBlock.find("p").removeClass("hideSelectIcon");
						}
					});
				}else {
					$("#skin-blue-light").addClass("select").find("p").removeClass("hideSelectIcon");
				}
			}
		},function(ex){}
	);
}
//系统设置
function initSetting() {
	//皮肤块点击预览皮肤设置样式
	$(".singleDiv").on("click",".colorDiv",function() {
		var skinClass = $(this).attr("id");
		window.localStorage.setItem(userId, skinClass);
		
		//初始化设置皮肤后的样式
		$("body").addClass(skinClass);
		parent.initBodyStyle(userId);
		
		var $selectBlock = $(this);	//点击选中预览的颜色块 
		//设置当前颜色块选中
		$(".colorDiv").each(function() {
			$(this).find("p").addClass("hideSelectIcon");
		});
		$selectBlock.find("p").removeClass("hideSelectIcon");
		//弹出询问框
		layer.confirm('确当修改皮肤？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				//确定修改，保存该用户的皮肤设置
				Util.ajaxJsonSync(
					Util.getRootPath() + "/w/setting/saveSettingMsg",
					{
						setting:{
							id : $("#skinClass").attr("data-id"),
							skinClass : skinClass
						}
					},
					function(result){
						if(result.success) {
							$("#skinClass").attr("data-class",skinClass);
							layer.msg('保存成功', {icon: 1});
							$defaultBlock = $selectBlock;
						}
					},function(ex){}
				);
			}, function(){
				//调用父页面方法，取消设置，还原皮肤设置
				parent.returnBodyStyle($("#skinClass").attr("data-class"));
				//取消选中
				$defaultBlock.find("p").removeClass("hideSelectIcon");
				//还原用户设置的皮肤颜色
				$selectBlock.find("p").addClass("hideSelectIcon");
			});
	});
	
	//平台名称设置提交
	$(".savePlateMsg").on("click",function() {
		var plateLgName = $("input[name='plateName']").val();
		var plateMiniName = $("input[name='miniName']").val();
		//非空校验
		if (plateLgName.length == 0) {
			  layer.alert('请输入平台名称', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			  return;
		}
		if (plateMiniName.length == 0) {
			  layer.alert('请输入平台缩略名', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			  return;
		}
		if (plateMiniName.length > 2) {
			  layer.alert('缩略名请不要超过两个字符', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			  return;
		}
		var msg = {};
		msg.lg = plateLgName;
		msg.mini = plateMiniName;
		//保存首页平台名称
		Util.ajaxJsonSync(
			Util.getRootPath() + "/w/setting/saveSettingMsg",
			{
				setting:{
					id : $("#skinClass").attr("data-id"),
					plateMiniName : msg.mini,
					plateLgName : msg.lg
				}
			},
			function(result){
				if(result.success) {
					layer.msg('保存成功', {icon: 1});
					//保存成功，调用父页面方法设置首页平台名称
					parent.initPlateMsg(msg);
				}
			},function(ex){}
		);
		
	});
	initAttachment(document.getElementById("bgPicFile"));
	initAttachment(document.getElementById("headerPicFile"));
	//皮肤图片
//	initAttachment(document.getElementById("skinPicFile"));
	//系统图片上传
	$(".savePic").on("click",function() {
		var attachment = document.getElementById("bgPicFile");
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
		var fileData = new FormData($('.picForm')[0]);
		if (curFiles.length > 0) {
			fileData.append('bgAttachment', curFiles[0]);
		}else {
//			fileData.append('bgAttachment',null);
			layer.alert('您未选择背景图片', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}
		
//		var headerAttachment = document.getElementById("headerPicFile");
//		var files = headerAttachment.files;
//		if (files && files.length) {
//			for (var i = 0; i < files.length; i++) {
//				var file = files[i];
//				var filestr = file.name.split(".")[1].toUpperCase();
//				if(filestr != "PNG" && filestr != "JPG" && filestr != "JPEG"){
//				} else {
//					// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
//					Array.prototype.push.apply(headerCurFiles, files);
//				}
//			}
//			
//		}
//		if (headerCurFiles.length > 0) {
//			fileData.append('headerAttachment', headerCurFiles[0]);
//		}else {
////			fileData.append('headerAttachment',null);
//			layer.alert('您未选择HeaderLogo图片', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
//			return;
//		}
		
		$.ajax({
			async : false,
	        cache: false,
	        type: 'POST',
	        dataType : "json",
	        data: fileData,
			processData : false,
			contentType : false,
	        url: Util.getRootPath() + "/w/setting/saveSystemPic",
	        success: function(result){
				if(result != null || result != "") {
					//result是两张图片的地址，逗号分割
					layer.msg('保存成功', {icon: 1});
				}
			}
		});
	});
	$(".saveHeadPic").on("click",function() {
		var fileData = new FormData($('.picForm')[0]);
		
		var headerAttachment = document.getElementById("headerPicFile");
		var files = headerAttachment.files;
		if (files && files.length) {
			for (var i = 0; i < files.length; i++) {
				var file = files[i];
				var filestr = file.name.split(".")[1].toUpperCase();
				if(filestr != "PNG" && filestr != "JPG" && filestr != "JPEG"){
				} else {
					// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
					Array.prototype.push.apply(headerCurFiles, files);
				}
			}
			
		}
		if (headerCurFiles.length > 0) {
			fileData.append('headerAttachment', headerCurFiles[0]);
		}else {
//			fileData.append('headerAttachment',null);
			layer.alert('您未选择HeaderLogo图片', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}
		
		$.ajax({
			async : false,
	        cache: false,
	        type: 'POST',
	        dataType : "json",
	        data: fileData,
			processData : false,
			contentType : false,
	        url: Util.getRootPath() + "/w/setting/saveSystemPic",
	        success: function(result){
				if(result != null || result != "") {
					//result是两张图片的地址，逗号分割
					layer.msg('保存成功', {icon: 1});
				}
			}
		});
	});
	//皮肤图片上传(功能暂时取消,待定)
//	$(".skinPicBtn").on("click",function() {
//		var skinAttachment = document.getElementById("skinPicFile");
//		var files = skinAttachment.files;
//		if (files && files.length) {
//			for (var i = 0; i < files.length; i++) {
//				var file = files[i];
//				var filestr = file.name.split(".")[1].toUpperCase();
//				if(filestr != "PNG" && filestr != "JPG" && filestr != "JPEG"){
//				} else {
//					// 原始FileList对象不可更改，所以将其赋予curFiles提供接下来的修改
//					Array.prototype.push.apply(skinCurFiles, files);
//				}
//			}
//			
//		}
//		var fileData = new FormData($('.skinForm')[0]);
//		if (skinCurFiles.length > 0) {
//			fileData.append('skinAttachment', skinCurFiles[0]);
//		}else {
//			fileData.append('skinAttachment',null);
//		}
//		
//		$.ajax({
//			async : false,
//	        cache: false,
//	        type: 'POST',
//	        dataType : "json",
//	        data: fileData,
//			processData : false,
//			contentType : false,
//	        url: Util.getRootPath() + "/w/setting/saveSystemPic",
//	        success: function(result){
//				if(result != null || result != "") {
//					parent.setSkinPic(result);
//					layer.msg('保存成功', {icon: 1});
//				}
//			}
//		});
//	});
}
function initAttachment(attach) {
//	var attachment = document.getElementById("headPic");
	attach.onchange = function () {
		//判断上传的文件是否为图片，若不是，做出提醒
		var files = attach.files;
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
			$(attach).next().find("img").attr("src",objUrl);
		}
	}
}


//建立一个可存取到文件的地址
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
//初始化默认背景图大小
function initBgPic() {
	var windowWidth = $(window).width();
	//设置背景图宽
	$("#bgPic").width(windowWidth / 5 + "px");
	//设置headerlogo宽高
	$("#imghead").width(windowWidth / 2 + "px").height(windowWidth / 30 + "px");
	//皮肤图片
	$("#skinPic").width(windowWidth / 5 + "px");
}
//初始化页面整体样式
function initStyle() {
	//点击ul时打开或关闭对应的内容区域
//	$("ul").on("click","p",function() {
//		//点击，展开或关闭对应区域
//		var $nextDiv = $(this).parent().parent().next();
//		if($nextDiv.attr("style") == "display:none"){
//			$nextDiv.removeAttr("style");
//		}else {
//			$nextDiv.attr("style","display:none");
//		}
//	});
}
//设置颜色快的大小
function initColorBlock() {
	var width = $(".InnerDiv").width();
	$(".colorDiv").width(width / 5 + "px");
	$(".colorDiv").height(width / 8 + "px");
	$(".colorDiv").find("p").attr("style","height:" + width / 8 + "px;line-height:" + width / 8 + "px");
}