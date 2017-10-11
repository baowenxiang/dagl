//全局变量
var grid;
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
	
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initMethod();
	initGrid();
	layer.close(index);
});

function initMethod(){
	$("button.cancel").on("click",function(){
		var business = grid.getCheckedRecords();
		if(business.length==0){
			layer.alert("请选择撤销选项！", {skin: 'layui-layer-lan',closeBtn: 0});
			return;
		}
		
		var businessIds = [];
		for(var i=0;i<business.length;i++){
			businessIds.push(business[i].id);
		}
		
		
		layer.confirm('确定需要撤销所选'+business.length+'项', {
			  skin: 'layui-layer-lan',
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url : Util.getRootPath()+"/w/fileidentify/destroyHistory/cancelDestroy",
					type:'POST', //GET
					async:true,
					data:{
						ids:businessIds
					},
					dataType:'json',
					success:function(data,textStatus,jqXHR){
						if(data.success){
							layer.msg(data.msg,{time: 2000});
							refreshGrid();
						}
					}
				});
						
				
				
			}, function(){});
	});
	
	$("button.export").on("click",function(){
		var records = grid.getCheckedRecords();//记录
    	if (records.length==0) {
    		//打包导出
			location.href = Util.getRootPath() + "/w/fileidentify/destroyHistory/exportDestroyHistory/null";
		}else{
			var ids = [];
			for(var i = 0;i<records.length;i++){
				ids.push(records[i].id);
			}
			ids = ids.join(',');
			//打包导出
			location.href = Util.getRootPath() + "/w/fileidentify/destroyHistory/exportDestroyHistory/"+ids;
		}
	});
	
}




function initGrid(){
	
	grid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/fileidentify/destroyHistory/getDestroyTables",
	    ajaxLoad : true,
	    gridContainer : 'tableDestroyHistroy',
	    toolbarContainer : 'pagingDestroyHistroy',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 20,
	    check : true,
	    pageSizeLimit : [20,50,100,300],
	    columns : [
					{
						id:'dh',
						title : '<b>档号</b>',
						type:"string",
						columnClass:'text-center'
					},
		       		{
		       			id:'tm',
		       			title : '<b>题名</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'zrz',
		       			title : '<b>责任者</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'cwrq',
		       			title : '<b>成文日期</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'qzh',
		       			title : '<b>全宗号</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'ndh',
		       			title : '<b>年度号</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'wh',
		       			title : '<b>文号</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'mlh',
		       			title : '<b>目录号</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'ajh',
		       			title : '<b>案卷号</b>',
		       			type:"string",
		       			columnClass:'text-center'
		       		},
		       		{
		       			id:'bgqx',
		       			title : '<b>保管期限</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
		       				var bgqx; 
							Util.ajaxJsonSync(
								Util.getRootPath()+"/w/dicmanager/getDicValueByDno?dno=bgqx&dvno="+value,
								{
								},
								function(result){
									if(result.success){
										bgqx = result.data;
									}
								}
							)
							
							return bgqx;
		       			}
		       		}/*,
		       		{
						id:'daid',
						title : '<b>操作</b>',
						type:"string",
						hideType:'xs',
						columnClass:'text-center',
						isContentEditable: false,
						resolution:function(value, record, column, grid, dataNo, columnNo){
							html = '<a title="查看" class="btn btn-primary btn-xs "  data-id="'+record.daid+'">查看</a>';
							return html;
						}
		       		}*/
		       		]
	});
	refreshGrid();
}

function refreshGrid() {
	grid.parameters = getParameters();
	grid.load();
}
function getParameters() {
	return{
	}
}