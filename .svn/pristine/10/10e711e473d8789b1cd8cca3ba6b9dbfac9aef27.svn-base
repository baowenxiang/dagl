$(function() {
	initBtn();
});
function initBtn() {
	//关闭
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
}

layui.use(['form','jquery'], function(){
    var $ = layui.jquery,
    form = layui.form();
	//监听提交
	form.on('submit(saveDetail)', function(data){
		Util.ajaxJsonSync(
			Util.getRootPath()+"/w/temperature/saveOrUpdate",
			{
				dwsd: {
					jlrq: data.field.jlrq,
					tq:data.field.tq,
					wsdsj:data.field.wsdsj,
					jlr:data.field.jlr,
					wd:data.field.wd,
					sd:data.field.sd,
					cqcs:data.field.cqcs,
					bz:data.field.bz
				}
			},
			function(result){
				if(result.success){
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		            parent.layer.close(index);
		            parent.mainIframe.refreshGrid();
				}else {
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			}, function(ex){
                layer.alert("提交失败", {  skin: 'layui-layer-lan',closeBtn: 0});
            }
		)
		return false;
	});
});