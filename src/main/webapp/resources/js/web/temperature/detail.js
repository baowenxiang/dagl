$(function() {
	getDetail();
	initBtn();
});
function initBtn() {
	//关闭
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
}
function getDetail() {
	var id = $("input[name='id']").val();
	Util.ajaxSync(
		Util.getRootPath()+"/w/temperature/detail",
		{
			id : id
		},
		function(result){
			if(result.success){
				result = result.data;
				$("input[name='jlrq']").val(result.jlrq);
				$("input[name='tq']").val(result.tq);
				$("input[name='wsdsj']").val(result.wsdsj);
				$("input[name='jlr']").val(result.jlr);
				$("input[name='wd']").val(result.wd);
				$("input[name='sd']").val(result.sd);
				$("input[name='cqcs']").val(result.cqcs);
				$("input[name='bz']").val(result.bz);
			}
		}
	)
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
					id : data.field.id,
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