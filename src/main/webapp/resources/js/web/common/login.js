/**
 * Created by XMCC on 2017/1/23 0023.
 */
$(function() {
	regEvent();
});

function regEvent() {
	var isValidateCodeLogin = $("#isValidateCodeLogin").val();
	//刷新验证码
	$("#verifycode_img").on("click",function() {
		$(this).attr("src",$(this).attr("src") + "?t=" + new Date());
	});
	//表单提交事件
	$(".login-form").on("submit",function() {
		var username = $.trim($("input[name='username']").val());
		var password = $.trim($("input[name='password']").val());
		var verifycode = $("input[name='verifycode']").val();
		if($.trim(username).length == 0) {
			$(".error").html("请输入账号");
			return false;
		}
		if($.trim(password).length == 0) {
			$(".error").html("请输入密码");
			return false;
		}
		if(isValidateCodeLogin=="true"){
			if($.trim(verifycode).length == 0) {
				$(".error").html("请输入验证码");
				return false;
			}
		}
		$("input[name='password']").val(MD5(password));
		return true;
	});
}

function showLocaleTime(objD)   
{   
    var str,colorhead,colorfoot;   
    var yy = objD.getYear();   
        if(yy<1900) yy = yy+1900;   
    var MM = objD.getMonth()+1;   
        if(MM<10) MM = '0' + MM;   
    var dd = objD.getDate();   
        if(dd<10) dd = '0' + dd;   
    var hh = objD.getHours();   
        if(hh<10) hh = '0' + hh;   
    var mm = objD.getMinutes();   
        if(mm<10) mm = '0' + mm;   
    var ss = objD.getSeconds();   
        if(ss<10) ss = '0' + ss;   
    var ww = objD.getDay();   
        if (ww==0) ww="星期日";   
        if (ww==1) ww="星期一";   
        if (ww==2) ww="星期二";   
        if (ww==3) ww="星期三";   
        if (ww==4) ww="星期四";   
        if (ww==5) ww="星期五";   
        if (ww==6) ww="星期六";   
            str = hh + ":" + mm+ "<span class='miao'>:" + ss + "</span>";   
        return(str);   
}   
function showLocaleDay(objD)   
{   
    var str,colorhead,colorfoot;   
    var yy = objD.getYear();   
        if(yy<1900) yy = yy+1900;   
    var MM = objD.getMonth()+1;   
    var dd = objD.getDate();   
    var hh = objD.getHours();   
        if(hh<10) hh = '0' + hh;   
    var mm = objD.getMinutes();   
        if(mm<10) mm = '0' + mm;   
    var ss = objD.getSeconds();   
        if(ss<10) ss = '0' + ss;   
    var ww = objD.getDay();   
        if (ww==0) ww="星期日";   
        if (ww==1) ww="星期一";   
        if (ww==2) ww="星期二";   
        if (ww==3) ww="星期三";   
        if (ww==4) ww="星期四";   
        if (ww==5) ww="星期五";   
        if (ww==6) ww="星期六";   
            str = MM + "月" + dd + "日, " + ww;   
        return(str);   
} 













