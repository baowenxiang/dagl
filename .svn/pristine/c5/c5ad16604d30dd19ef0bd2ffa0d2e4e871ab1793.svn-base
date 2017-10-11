
//存储附件的集合
var curFiles = [];
var ghGrid;
$(function(){
	
	initTable("");
	initMethod();

});

//初试化方法
function initMethod(){
	 //点击到处按钮
    $("button.export").on("click",function(){
    	var jyzt = $('[name="jyzt"]').val();
    	if(jyzt == '2'){
    		layer.alert("请选择已归还的数据导出!", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
    	}
    	
    	var records = ghGrid.getCheckedRecords();//记录
    	var pager = new Object();
		pager.parameters = ghGrid.parameters;
		var dtGridPager = JSON.stringify(pager);//筛选、排序条件
    	if (records.length==0) {
    		//打包导出
			location.href = Util.getRootPath() + "/w/fileBorrowing/exportSendBack/null/"+dtGridPager;
		}else{
			var ids = [];
			for(var i = 0;i<records.length;i++){
				ids.push(records[i].id);
			}
			ids = ids.join(',');
			//打包导出
			location.href = Util.getRootPath() + "/w/fileBorrowing/exportSendBack/"+ids+"/"+dtGridPager;
		}
    	
    });
	
	//续借
	$("button.continue").on("click",function(){
		var businesses = ghGrid.getCheckedRecords();
		
		if(businesses.length != 1){
			layer.alert("只能选择一条数据", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		if(businesses[0].jyzt == "1"){
			layer.alert("请选择未归还记录再做续借", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		var businessIds = [];
		for(var i=0;i<businesses.length;i++){
			businessIds.push(businesses[i].id);
		}
		var uuid = businessIds[0];
		//$("input").val("");会影响dtgrid
		$('[name="ujymd"]').val("");
		$('[name="unghsj"]').val("");
		$('[name="ubz"]').val("");
		$('[name="utm"]').val("");
		$('[name="ubm"]').val("");
		$('[name="ghxxId"]').val(uuid);
		//debugger;
		$('[name="jyzt"]').val(businesses[0].jyzt);
		$("#ghxxUpdate").modal("show");
		//根据资料收集id获得相应信息
		$.ajax({
			url : Util.getRootPath()+"/w/fileBorrowing/getJyxxById",
			type:'POST', //GET
			async:false,
			data:{
				"id":uuid
			},
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				//输入框赋值
				var dataInfo = data;
				$('[name="ujymd"]').val(dataInfo.data.jymd);
				$('[name="unghsj"]').val(dataInfo.data.nghsj);
				$('[name="ubz"]').val(dataInfo.data.bz);
				$('[name="utm"]').val(dataInfo.data.tm).attr("readonly","readonly");
				$('[name="ubm"]').val(dataInfo.data.bm).attr("readonly","readonly");
//				refreshGrid();
			},
			error:function(ex) {}
		});
		
	});
	
	//查询
	$("button.query").on("click",function(){
		var jyzt = $('[name="jyzt"] option:selected').val();//选中的值
		var options = {}
		options.url = Util.getRootPath() + "/w/fileBorrowing/ghxxQueryView?jyzt="+jyzt;
		options.title = "题名查询";
		
    	window.parent.showModal(options);
	});
	
	//点击查看事件
	$("#ghxxTable").on("click","a.looking",function(){
		
		var id = $(this).attr("data-id");
		var jyzt = $(this).attr("data-jyzt");
		//$('[name="messageId"]').val(id);
//		$("input").val("");
		//debugger;
		var input = $("#checkGhxx").find("input").val("");
//		$('[name="ghxxId"]').val(id);
//		$('[name="jyzt"]').val(jyzt);
		$("#checkGhxx").modal("show");
		//根据资料收集id获得相应信息
		$.ajax({
			url : Util.getRootPath()+"/w/fileBorrowing/getJyxxById",
			type:'POST', //GET
			async:false,
			data:{
				id:id
			},
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				//输入框赋值
				var dataInfo = data;
				$('[name="tm"]').val(dataInfo.data.tm).attr("readonly","readonly");
				$('[name="dh"]').val(dataInfo.data.dh).attr("readonly","readonly");
				$('[name="wh"]').val(dataInfo.data.wh).attr("readonly","readonly");
				$('[name="fh"]').val(dataInfo.data.fh).attr("readonly","readonly");
				$('[name="jymd"]').val(dataInfo.data.jymd).attr("readonly","readonly");
				$('[name="jyr"]').val(dataInfo.data.jyr).attr("readonly","readonly");
				$('[name="jbr"]').val(dataInfo.data.jbr).attr("readonly","readonly");
				$('[name="pzr"]').val(dataInfo.data.pzr).attr("readonly","readonly");
				$('[name="jyxg"]').val(dataInfo.data.jyxg).attr("readonly","readonly");
				$('[name="jgmc"]').val(dataInfo.data.jgmc).attr("readonly","readonly");
				$('[name="qwbs"]').val(dataInfo.data.qwbs).attr("readonly","readonly");
				$('[name="jgmc"]').val(dataInfo.data.jgmc).attr("readonly","readonly");
				$('[name="jysj"]').val(dataInfo.data.jysj).attr("readonly","readonly");
				$('[name="nghsj"]').val(dataInfo.data.nghsj).attr("readonly","readonly");
				$('[name="ghsj"]').val(dataInfo.data.ghsj).attr("readonly","readonly");
//				refreshGrid();
			},
			error:function(ex) {}
		});
	});

	//删除信息
	$("#ghxxTable").on("click","a.delete",function(){
		var id = $(this).attr("data-id");
		var jyzt = $(this).attr("data-jyzt");
		$.ajax({
			url : Util.getRootPath()+"/w/fileBorrowing/deleteJyxx",
			type:'POST', //GET
			async:true,
			data:{
				id:id
			},
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				if(data.success){
					layer.msg(data.msg,{time: 2000});
					refreshGrid(jyzt);
				}else{
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			},
			error:function(ex) {
				layer.alert("删除失败！", {  skin: 'layui-layer-lan',closeBtn: 0});
			}
		});
	});
	
	//保存更新信息
	$("button.dataUpdate1").on("click",function(){
		var id = $.trim($('[name="ghxxId"]').val());
		var jyzt = $.trim($('[name="jyzt"]').val());
		var jymd = $.trim($('[name="ujymd"]').val());
		var bz = $.trim($('[name="ubz"]').val());
		//前台校验
		var nghsj = $.trim($('[name="unghsj"]').val());
		if(Util.checkNull(nghsj)){
			layer.alert('拟归还时间不能为空', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}

		var data = {"id":id,"jymd":jymd,"bz":bz,"nghsj":nghsj};
		
		$.ajax({
			url : Util.getRootPath()+"/w/fileBorrowing/saveJyxx",
			type:'POST', 
			async:true,
			data:data,
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				if(data.success){
					layer.msg(data.msg,{time: 2000});
					$(".modal").modal("hide");
					refreshGrid(jyzt);
				}else{
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			},
			error:function(ex) {
				layer.alert("操作失败！", {  skin: 'layui-layer-lan',closeBtn: 0});
			}
		});
	});
}


function initTable(jyzt){
	$("#ghxxTable").children().remove();
	//$('[name="jyzt"]').val(jyzt);
	ghGrid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/fileBorrowing/getGhxxList",
	    ajaxLoad : true,
	    gridContainer : 'ghxxTable',
	    toolbarContainer : 'pagingGhxx',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    pageSizeLimit : [20,50,100,300],
	    tableStyle:'table-layout: fixed;word-break: break-all; word-wrap: break-word;',
	    columns : [
		       		{
		       			id:'jyzt',
		       			title : '<b>借阅状态</b>',
		       			type:"string",
		       			columnClass:'text-center',
			       		columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							if("1"==value){
		       					return '<span title="已归还" style="cursor: pointer;">已归还</span>';
		       				}else{
		       					return '<span title="未归还" style="cursor: pointer;">未归还</span>';
		       				}
						}
		       		},
		       		{
		       			id:'tm',
		       			title : '<b>题名</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       			
		       		},
		       		{
		       			id:'dh',
		       			title : '<b>档号</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'jymd',
		       			title : '<b>借阅目的</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'jyr',
		       			title : '<b>借阅人</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'jyxg',
		       			title : '<b>借阅效果</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'jysj',
		       			title : '<b>借阅时间</b>',
		       			type:"date",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'nghsj',
		       			title : '<b>拟归还时间</b>',
		       			type:"date",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'ghsj',
		       			title : '<b>归还时间</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'lrsj',
		       			title : '<b>录入时间</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			columnStyle:'-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100%;',
						resolution:function(value, record, column, grid, dataNo, columnNo){
							return '<span title="'+value+'" style="cursor: pointer;">'+value+'</span>';
						}
		       		},
		       		{
		       			id:'id',
		       			title : '<b>操作</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       			//存储record类，编辑时使用
		       				var html = ''; 	
		       				html += '<div class="btnDiv">';
		       				html += '	<a title="查看" class="btn btn-default btn-xs looking" data-id="'+value+'" data-jyzt="'+record.jyzt+'">查看</a>';
	       					html += '	<a title="删除" class="btn btn-primary btn-xs delete" data-id="'+value+'" data-jyzt="'+record.jyzt+'">删除</a>';
		       				html += '</div>';
			       			
		       				return html;
		       			}

		       		}
		       		]
	});
	refreshGrid(jyzt);
}

function refreshGrid(jyzt) {
	ghGrid.parameters = getParameters(jyzt);
	ghGrid.load();
}
function getParameters(jyzt) {
	return {
		jyzt:jyzt
	}
}

