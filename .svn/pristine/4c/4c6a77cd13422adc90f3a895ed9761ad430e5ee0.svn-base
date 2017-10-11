$(function(){
	initButtonMethod();
});

function initButtonMethod(){
	
	 $("button b").show();
	 
	 // OA资料获取
	 $(".addOAdata").on('click',function() {
	   	 var options = {}
	    	 var url = Util.getRootPath() + "/w/OAService/OADateChoose";
	    	 options.url = url;
	    	 options.title = "OA对接起始日期";
	    	 window.parent.showModal(options);
	 });
	
	//点击资料整理按钮
	$("button.dataCollecting").on("click",function(){
		dataCollectIds = [];
		var records =  grid.getCheckedRecords();
		if(records.length==0){
			layer.alert("请勾选资料！", {  skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		//获得资料id的集合
		for(var i = 0;i<records.length;i++){
			if(records[i].delFlag=='1'){
				layer.alert("题名：<<" + records[i].tm +">> 的资料已归档, 请重新选择！", {  skin: 'layui-layer-lan',closeBtn: 0});
				return;
			}
			dataCollectIds.push(records[i].id);
		}
		var dataCollectId = dataCollectIds.join();
		var options = {}
		// OA接口数据
    	var url = Util.getRootPath() + "/w/zlzl/oazlsj/dataCollectView?dataCollectId="+dataCollectId;
    	options.url = url;
    	options.title = "资料整理";
    	window.parent.showModal(options);
	});
	
}