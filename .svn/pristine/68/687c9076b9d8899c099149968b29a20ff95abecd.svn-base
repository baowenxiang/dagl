$(function(){
	console.log(parent.mainIframe.preGrid.getCheckedRecords());
	
	listenSubmit();
	
});

//监听提交
function listenSubmit(){
	layui.use(['form','jquery'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
		//监听提交
		form.on('submit(confirm)', function(data){
			Util.ajaxJsonSync(
					Util.getRootPath() + "/w/compilation/saveCompileForm",
					{
						tm : data.field.tm,
						bz : data.field.bz,
						records : parent.mainIframe.preGrid.getCheckedRecords()
					},
					function(result){
						if (result.success) {
							
							var preGridDataDel = [];
							var preGridDatas = parent.mainIframe.preGrid.exhibitDatas;//预编研所有的
							var preGridCheck = parent.mainIframe.preGrid.getCheckedRecords();//预编研勾选的
							var preGridCheckIds = [];
							for(var i = 0;i<preGridCheck.length;i++){
								preGridCheckIds.push(preGridCheck[i].uuid);
							}
							
							for(var j = 0;j<preGridDatas.length;j++){
								if(preGridCheckIds.indexOf(preGridDatas[j].uuid) == -1){
									preGridDataDel.push(preGridDatas[j]);
								}
							}
							
							parent.mainIframe.preGrid.originalDatas = preGridDataDel;
							parent.mainIframe.preGrid.baseDatas = preGridDataDel;
							parent.mainIframe.preGrid.exhibitDatas = preGridDataDel;
							parent.mainIframe.preGrid.reload();
							
							
							
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				            parent.layer.close(index);
						} 
					}, function(ex){
						layer.alert("保存流程请求失败", {  skin: 'layui-layer-lan',closeBtn: 0});
					}
				);
			
			
			return false;
		});
	});
}