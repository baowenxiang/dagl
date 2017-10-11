//存储附件的集合
var transferedFileGrid;

$(function(){
	/*$(".btn-icon").on("mouseenter", function(){
        $this = $(this);
        $this.find('b').show();
     });
     
     $(".btn-icon").on("mouseleave", function(){
        $this = $(this);
        $this.find('b').hide();
     });*/
	$("button b").show();
	initTable();
	initMethod();
	
});



function initTable(){
	transferedFileGrid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/fileTransfered/getTransferedFileList",
	    ajaxLoad : true,
	    gridContainer : 'transferedFileControlTable',
	    toolbarContainer : 'pagingTransferedFileControl',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    pageSizeLimit : [20,50,100,300],
	    columns : [
					
						{
							id:'tm',
							title : '<b>批量移交标题</b>',
							type:"string",
							columnClass:'text-center'
						},
						{
							id:'userId',
							title : '<b>创建人</b>',
							type:"string",
							columnClass:'text-center',
							resolution:function(value, record, column, grid, dataNo, columnNo){
								var userName;
								Util.ajaxJsonSync(
									Util.getRootPath() + "/w/fileTransfered/getUserById",
									{
										userId:value
									},function(result){
										if (result.success) {
											userName = result.data.name;
										}
									});
										return userName;
							}
						},
						{
							id:'startTime',
							title : '<b>创建时间</b>',
							type:"string",
							columnClass:'text-center',
							resolution:function(value, record, column, grid, dataNo, columnNo){
								return value.substring(0,10);
							}
						},
						{
							id:'endTime',
							title : '<b>结束时间</b>',
							type:"string",
							columnClass:'text-center',
							resolution:function(value, record, column, grid, dataNo, columnNo){
								return value.substring(0,10);
							}
						},
						{
							id:'oldCompany',
							title : '<b>移交公司</b>',
							type:"string",
							columnClass:'text-center',
						},
						{
							id:'newCompany',
							title : '<b>接收公司</b>',
							type:"string",
							columnClass:'text-center',
						}
						]
	});
	refreshTransferedFileGrid();
}

function refreshTransferedFileGrid() {
	transferedFileGrid.parameters = getTransferedFileParameters();
	transferedFileGrid.load();
}
function getTransferedFileParameters() {
	
}

function initMethod(){
	//点击导出按钮
	$("button.export").on("click",function(){
		var records = transferedFileGrid.getCheckedRecords();//记录
		if (records.length==0) {
			//打包导出
			location.href = Util.getRootPath() + "/w/fileTransfered/exportTransferedInfo/null";
		}else{
			var ids = [];
			for(var i = 0;i<records.length;i++){
				ids.push(records[i].uuid);
			}
			ids = ids.join(',');
			//打包导出
			location.href = Util.getRootPath() + "/w/fileTransfered/exportTransferedInfo/"+ids;
		}
	});
}

