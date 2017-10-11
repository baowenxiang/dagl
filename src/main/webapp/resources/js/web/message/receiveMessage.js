var grid;
$(function(){
	initGrid();
	
	refreshGrid();
	
});

function initGrid(){
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/message/getReceiveMessageList",
	    ajaxLoad : true,
	    gridContainer : 'tableReceiveMessage',
	    toolbarContainer : 'pagingReceiveMessage',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : false,
	    pageSizeLimit : [20,50,100,300],
	    columns : [
		       		{
		       			id:'sendUser',
		       			title : '<b>发送者姓名</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				
		       				return value.name;
		       			}
		       		},
		       		{
		       			id:'receUser',
		       			title : '<b>发送者姓名</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				
		       				return value.name;
		       			}
		       		},
		       		{
		       			id:'sendTime',
		       			title : '<b>消息发送时间</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				var date = new Date(value);
		       			    return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日";
			       		}
		       		},
		       		{
		       			id:'isRead',
		       			title : '<b>是否已读</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				if("isRead_0"==value){
		       					return "未读";
		       				}else{
		       					return "已读";
		       				}
			       		
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
		       				html += '	<a title="查看" class="btn btn-default btn-xs looking" data-id="'+value+'">查看</a>';
		       				html += '</div>';
			       			
		       				return html;
		       			}

		       		}
		       		]
	});
}

function refreshGrid() {
	grid.parameters = getParameters();
	grid.load();
}
function getParameters() {
	
}


//点击查看
$("#tableReceiveMessage").on("click",".looking",function(){
	var id = $.trim($(this).attr("data-id"));
	window.parent.layer.open({
        type: 2,
		title:"查看消息",
        area: ['500px', '300px'],
        fixed: false, //不固定
        maxmin: true,
        content: Util.getRootPath() + "/w/message/lookReceiveMessageView?id="+id,
      });
})


