layui.use(['form','jquery','laydate'], function(){
	var layer = layui.layer
    ,$ = layui.jquery
    ,form = layui.form()
    ,laydate = layui.laydate;
	
    form.on('submit(search)', function(data){
    	/*var tablename = data.field.fileSelect;
       var tm = data.field.tm;
       var dh = data.field.dh;
       var zrz = data.field.zrz;
       var cwrq_min = data.field.cwrq_min;*/
    	
    	/*
       if($.trim(data.field.fileSelect) == ""){
            layer.msg("请选择档案类型");
            return false;
       }
       */
       $('form').attr("action", "/dagl/w/condition/init");
    });
    
});