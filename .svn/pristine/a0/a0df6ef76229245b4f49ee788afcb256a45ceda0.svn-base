$(function() {
	//logo区点击跳转首页
	$(".logo").on("click",function() {
		// window.location.href = Util.getRootPath() + "/w/index";
		$("#mainIframe").attr("dagl-location", "<i class='fa fa-home'></i> 首页");
		$("#mainIframe").attr("src",Util.getRootPath() + "/w/welcome");  
	});
});

//加载头部平台名称以及缩略名
function setPlateMsg(msg) {
	$("header .logo-mini").text(msg.mini);
	$("header .logo-lg").text(msg.lg);
}
//加载皮肤图片的设定
function setSkinPic(skinPath) {
//	var path = skinPath.split("bgpic")[1];
//    path.substring(1, path.length);
//	$("header").attr("style","background:url(" + "/uploadFile/bgpic/" + path + ")");
	$(".normal").attr("style","background:url(" + skinPath + ")");
}
function setHeaderPic(headerPic) {
	var width = $(window).width();
	$(".normal").attr("style","top:88px");
	var $logoPic = $(".logoPic");
	$logoPic.removeAttr("style");
	var html = "";
	html += '<img src="' + headerPic + '" style="width:' + width + 'px;height:88px">';
	$logoPic.append(html);
}
//隐藏图片
function hideHeaderPic() {
	$(".normal").removeAttr("style");
	$(".logoPic").attr("style","display:none");
}
