$(function() {
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initBtn();
	initTable();
//	window.location.href = Util.getRootPath()+"/w/setting/timeBack";
	layer.close(index);
});
//初始化按钮点击事件
function initBtn() {
	//新增备份按钮点击事件，打开模态框
	$(".add").on("click",function(){
		$("#dataCollectUpdate").modal("show");
	});
	//删除按钮点击事件
	$("#tableDataCollect").on("click","a.delete",function(){
		var id = $(this).attr("data-id");
		$.ajax({
			url : Util.getRootPath()+"/w/setting/deleteBackupRecordById",
			type:'POST', //GET
			async:true,
			data:{
				id:id
			},
			dataType:'json',
			success:function(data,textStatus,jqXHR){
				if(data.success){
					layer.msg(data.msg,{time: 2000});
					refreshGrid();
				}else{
					layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
				}
			},
			error:function(ex) {
				layer.alert("删除失败！", {  skin: 'layui-layer-lan',closeBtn: 0});
			}
		});
	});
	//备份按钮点击事件
	$(".backup").on('click',function() {
		var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
		//获取文件名以及备注
		var fileName = $("[name='filename']").val();
		var content = $("[name='content']").val();
//		window.location.href = Util.getRootPath()+"/w/setting/backup?fileName=" + fileName + "&content=" + content;
		
		Util.ajax(
			Util.getRootPath()+"/w/setting/backup",
                {
				fileName : fileName,
				content : content
                },
                function(result){
					if(result.success){
						layer.close(index);
						refreshGrid();
					}
                }
		 )
	});
}
//初始化列表
function initTable(){
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/setting/getBackUpList",
	    ajaxLoad : true,
	    gridContainer : 'tableDataCollect',
	    toolbarContainer : 'pagingDataCollect',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : false,
	    pageSizeLimit : [20,50,100,300],
	    columns : [
		       		{
		       			id:'createTime',
		       			title : '<b>备份时间</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'fileName',
		       			title : '<b>文件名</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'path',
		       			title : '<b>备份路径</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'userName',
		       			title : '<b>备份人员</b>',
		       			type:"string",
		       			columnClass:'text-center'
//		       			resolution:function(value, record, column, grid, dataNo, columnNo){
//		       				return value.name;
//		       			}
		       		},
		       		{
		       			id:'content',
		       			title : '<b>备注</b>',
		       			type:"string",
		       			columnClass:'text-center'
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
//		       				html += '	<a title="查看" class="btn btn-default btn-xs looking" data-id="'+value+'">查看</a>';
//	       					html += '	<a title="修改" class="btn btn-primary btn-xs update" data-id="'+value+'">修改</a>';
	       					html += '	<a title="删除" class="btn btn-primary btn-xs delete" data-id="'+value+'">删除</a>';
		       				html += '</div>';
			       			
		       				return html;
		       			}

		       		}
		       		]
	});
	refreshGrid();
}

function refreshGrid() {
	grid.parameters = getParameters();
	grid.load();
}
function getParameters() {
	
}