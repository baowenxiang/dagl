$(function() {
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initBtn();
	layer.close(index);
});
function initBtn() {
	//关闭
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	//提交
	$(".sub").on("click",function() {
		var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
		var startDate = $.trim($('[name="startDate"]').val());
		var endDate = $.trim($('[name="endDate"]').val());
		if(startDate.length == 0) {
			layer.alert('请选择起始日期', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}
		if(startDate.length == 0) {
			layer.alert('请选择结束日期', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}
		Util.ajaxSync(
			Util.getRootPath()+"/w/OAService/getOaDataByDate",
                {
				startDate : startDate,
				endDate : endDate
                },
                function(result){
					if(result.success){
						layer.close(index);
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				        parent.layer.close(index);
				        parent.mainIframe.refreshGrid();
//						layer.alert("OA接口数据对接成功", {  skin: 'layui-layer-lan',closeBtn: 0});
					}
					if(result.data){
						$.ajax({
                            url : Util.getRootPath()+"/w/OAService/process?threadid=" + result.data,
                            type:'GET', 
                            dataType:'json',
                            success:function(data){
                                console.log("OAdata "+ result.data + "执行成功");
                            },
                            error:function(error){
                                console.log("OAdata" + result.data + "执行失败");
                                console.log(error);
                            }
                       });
					}
                }
		 )
	});
//	layui.use(['form','jquery'], function(){
//	    var $ = layui.jquery,
//	    form = layui.form();
//		//监听提交
//		form.on('submit(sub)', function(data){
//			var startDate = $.trim($('[name="startDate"]').val());
//			var endDate = $.trim($('[name="endDate"]').val());
//			alert(startDate + "," + endDate);
//		});
//	});
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}