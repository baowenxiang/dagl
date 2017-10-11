layui.use(['form','jquery'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
	    
		//监听提交
		form.on('submit(save)', function(data){
			  $.ajax({
		          url: Util.getRootPath() + "/w/fileuse/electronicLend/addJyxg",
		          type: "POST",
		          dataType: "json",
		          data: data.field,
		          async: false,
		          success: function(data) {
		            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		            if(data.success){
		            	layer.msg("保存成功");
		            	parent.layer.close(index);
		 	            parent.mainIframe.refreshGrid();
		            }else{
		            	layer.msg(data.msg);
		            }
		          },
		          error: function() {
		             layer.msg("保存请求失败，请稍后再试。");
		          }
		        });
		        return false;
		})
});


$(function(){
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
})