layui.use(['form','jquery','laydate'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
	    var laydate = layui.laydate;
	    
	    
	    $(".closeup").on("click",function(){
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	        parent.layer.close(index);
		});
	    
		//监听提交
		form.on('submit(save)', function(data){
			$.ajax({
				url : Util.getRootPath()+"/w/fileBorrowing/saveJyxx",
				type:'POST', 
				async:true,
				data:data.field,
				dataType:'json',
				success:function(data,textStatus,jqXHR){
					if(data.success){
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						layer.msg(data.msg,{time: 2000});
						parent.layer.close(index);
						parent.mainIframe.refreshGrid("");
					}else{
						layer.alert(data.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
					}
				},
				error:function(ex) {
					layer.alert("保存请求失败！", {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			});
			
			return false;
		});
});