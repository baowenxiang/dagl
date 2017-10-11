
$(function(){
	$("input.add").on("click",function(){
			var field = $.trim($('[name="field"]').val());
			var condition = $.trim($('[name="condition"]').val());
			var content = $.trim($('[name="content"]').val());
			var relation = $.trim($('[name="relation"]:checked').val());
			
			
			var html  = '';
            	html += '<a class="list-group-item screenItem">';
            	html += '	<span>';
            	html += '		<span class="relation" data="'+relation+'">' + relation + '&emsp;</span>';
            	html += '		<span class="field" data="'+field+'">' + field + '&emsp;</span>';
            	html += '		<span class="condition" data="'+condition+'">' + condition + '&emsp;</span>';
            	html += '		<span class="content" data="'+content+'">' + content + '&emsp;</span>';
            	html += '	</span>';
            	html += '	<button  class="layui-btn layui-btn-small" onclick="removeScreen(this)"><span class="glyphicon glyphicon-remove"></span></button>';
            	html += '</a>';
            	$(".screenGrop").append(html);
	});
	
	
	$("input.query").on("click",function(){
		//获得所有条件
		var info = [];
		for(var i=0;i<$("a.screenItem").length;i++){
			var $item = $("a.screenItem").eq(i);
			var obj = {};
			obj.relation = $item.find(".relation").attr("data");//与或关系
			obj.field = $item.find(".field").attr("data");//表字段
			obj.condition = $item.find(".condition").attr("data");//条件
			obj.content = $item.find(".content").attr("data");//内容
			info.push(obj);
		}
		
		
	});
	
});





function removeScreen(data){
	$(data).parents('.screenItem').remove(); 
}