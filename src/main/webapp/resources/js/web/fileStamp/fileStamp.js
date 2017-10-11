layui.use(['form', 'element', 'jquery'], function(){
        var form = layui.form();
        var element = layui.element();
        var $ = layui.jquery;
        
        uuids = new Array;
        var records = parent.mainIframe.grid.getCheckedRecords(); 
        records.forEach(function(value,index,array){
            uuids.push(value.uuid);
        });
        
        
		form.on('submit(confirm)', function(data){
			if(Util.checkNull(data.field.dhzlx)){
				layer.alert("请选择档号章类型", {  skin: 'layui-layer-lan',closeBtn: 0});
				return false;
			}else if(Util.checkNull(data.field.zlid)){
				console.log(data.field.zlid);
				layer.alert("请选择盖章文件", {  skin: 'layui-layer-lan',closeBtn: 0});
				return false;
			}
			$.ajax({
				type: 'POST',
				url : Util.getRootPath() + "/w/fileStamp/doFileStamp",
				async:false,
				data:{
				    tablename : data.field.tablename,
				    dhzlx:data.field.dhzlx,		
				    zlid:data.field.zlid,
				    uuids : uuids,
				    wz : data.field.wz
				},
				traditional:true,
				dataType:'json',
				success:function(result){
				    if(result.success){
			    		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	        		    layer.msg(result.msg);
	        		    parent.layer.close(index);
	        		    parent.mainIframe.refreshGrid();
				    }else{
				        layer.msg(result.msg);
				    }
				},
				error:function(error){
				    layer.msg("");
				}
	        });
			
			
			return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
        
});