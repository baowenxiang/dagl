$(function(){
	initButtonMethod();
});

function initButtonMethod(){
	/*$(".btn-icon").on("mouseenter", function(){
        $this = $(this);
        $this.find('b').show();
     });
     
     $(".btn-icon").on("mouseleave", function(){
        $this = $(this);
        $this.find('b').hide();
     });*/
	 $("button b").show();
	//点击下载模版
	$("button.downloadTemplet").on("click",function(){
		var tablename = $.trim($('[name="tablename"]').val());
		location.href = Util.getRootPath() + "/w/example/tools/downloadTempletDef/"+tablename;
		
	});
	
	//点击导入按钮弹出框
	$("button.importTemplet").on("click",function(){
		var tablename = $('[name="tablename"]').val();
    	var options = {}
    	var url = Util.getRootPath() + "/w/zlzl/zlsj/importView/"+tablename;
    	options.url = url;
    	options.title = "导入模版";
    	window.parent.showModal(options);
	});
	
	//增加记录
	$("button.addRecord").on("click",function(){
		var tablename = $('[name="tablename"]').val();
    	var options = {}
    	var url = Util.getRootPath() + "/w/zlzl/zlsj/adddetail/"+tablename;
    	options.url = url;
    	options.title = "增加记录";
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
			dataCollectIds.push(records[i].id);
		}
		var dataCollectId = dataCollectIds.join();
		var options = {}
    	var url = Util.getRootPath() + "/w/zlzl/zlsj/dataCollectView?dataCollectId="+dataCollectId;
    	options.url = url;
    	options.title = "资料整理";
    	window.parent.showModal(options);
	});
	
}