$(function(){
	listenSubmit();
});


function listenSubmit(){
	layui.use(['form','jquery'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
	    
	    var tablename;
		//监听提交
		form.on('submit(confirm)', function(data){
			var dataCollectId = $('[name="dataCollectId"]').val().split(",");
			Util.ajaxJsonSync(
              Util.getRootPath()+"/w/zlzl/oazlsj/dataCollectToPreArchive",
               {
					dataCollectIds:dataCollectId,
					tablename:tablename
				},
                function(result){
					if(result.success){
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						layer.msg('已全部录入',{time: 2000});
			            parent.layer.close(index);
			            
			            parent.mainIframe.refreshGrid();
					}
                }, 
                function (ex) {
                	layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
                }
         	);
			
			return false;
		});
		
		form.on('select(dataCollect)', function(data1){
			tablename = data1.value;
		});
	});
}