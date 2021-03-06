//与档号相关的字段数组
var fieldArr = [];
//与档号相关字段的下标
var fieldIdx = [];
//档号数组
var dhArr = [];
//已有档号
var dh = $("input[name='dh']").attr("value");
//根据档号规则生成的档号
var dhRule = "";
//判断档号是否手动修改过的标识,0表示未修改过
var flag = 0;

//保存或保存并新增的标识,0表示保存,1表示保存并新增
var saveFlag = 0;
//卷内顺序号
var jnsxh = 0;
$(function(){
	initDetailMethod();
	listenSubmit();
	DhUtil.checkDhAndRule();
	saveAndAdd();
});
//判断是否启用了档号规则，若启用，则流水号+1
function addNum() {
	dhRule = "";
	for(var i = 0;i<dhArr.length;i++) {
		dhRule += dhArr[i];
	}
//	alert(dhRule+","+dh);
	if(dhRule == dh) {
		DhUtil.getDh();
	}
}
function saveAndAdd() {
	$(".saveAndAdd").on("click",function() {
		saveFlag = 1;
		listenSubmit();
	});
}
//form提交监听
function listenSubmit(){
	layui.use(['form','jquery','laydate'], function(){
	    var $ = layui.jquery,
	    form = layui.form();
	    var laydate = layui.laydate;
	    
	    var isAdd = false;
	    var isNext = false;
		//监听提交
		form.on('submit(saveOrUpdate)', function(data){
			var tablename = $.trim($('[name="tablename"]').val());
			var detailId = $.trim($('[name="detailId"]').val());
			var isProcessState = false;
			if(!Util.checkNull(detailId)){
				var businessId = tablename + ',' + detailId;
				$.ajax({
					url :  Util.getRootPath() + "/w/example/flow/checkFlowState",
					type:'POST', //GET
					async:false,
					data:{
						businessId:businessId
					},
					dataType:'json',
					success:function(data,textStatus,jqXHR){
						if(data.success){
							if(data.datas.length>0){
								isProcessState = true;
								layer.open({
									  type: 4,
									  content: ['当前记录已有流程记录', '.saveOrUpdate'] //数组第二项即吸附元素选择器或者DOM
								}); 
							}
						}
					},
					error:function(ex) {
						layer.alert("删除失败！", {  skin: 'layui-layer-lan',closeBtn: 0});
					}
				})
			}
			
			if(isProcessState){
				return false;
			}
			
			var loadIndex = layer.load(1, {shade: [0.1,'#FFFFFF']});
			var fileUUID;
	        //删除附件
	        if (removeFileIds.length > 0) {
	            Util.ajaxJsonSync(
	                Util.getRootPath() + "/w/ywgj/removeFiles",
	                {
	                    attaIds: removeFileIds
	                },
	                function(result){
	                    if(!result.success){
	                        layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
	                    }
	                }, function(ex){
	                    layer.alert("删除附件请求失败", {  skin: 'layui-layer-lan',closeBtn: 0});
	                }
	            );
	        }
	        
	        //资料收集修改,上传原文
	        if (savFiles.length > 0) {
	            var fileData = new FormData($('form')[0]);
	            for (var i = 0, j = savFiles.length; i < j; ++i) {
	                fileData.append('attachment[]', savFiles[i]);
	            }
	            //资料id
	            fileData.append('dataCollectId',$.trim($('[name="detailId"]').val()));
	            $.ajax({
	                async : true,
	                cache: false,
	                type: 'POST',
	                dataType : "json",
	                data:fileData,
	                processData : false,
	                contentType : false,
	                url: Util.getRootPath() + "/w/zlzl/zlsj/uploadFile",
	                success: function(result){
	                    if(!result.success){
	                        layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
	                    }else{
	                        fileUUID = result.data;
	                        $.ajax({
	              	          url: Util.getRootPath()+"/w/example/ygtable/saveOrUpdate/"+tablename+"/"+fileUUID,
	              	          type: "POST",
	              	          dataType: "json",
	              	          data: data.field,
	              	          async: true,
	              	          success: function(data) {
	              	        	  if(data.success){
	              	        		layer.close(loadIndex);
	              		            	layer.msg("保存成功");
	              		        	  addNum();
	              		        	  //保存后关闭弹窗
	              		        	  if(!isAdd && !isNext){
	              		        		  var idx = parent.layer.getFrameIndex(window.name); //获取窗口索引
	              		        		  layer.msg("保存成功");
	              		        		  parent.layer.close(idx);
	              		        	  }
	              		        	  //点击增加复选框
	              		        	  if(isAdd && !isNext){
	              		            		window.location.href = Util.getRootPath() + "/w/example/ygtable/adddetail/"+tablename;
	              		        	  }
	              		        	  //保存并新增，不关闭弹窗，并将此作为新增档案的模板
	              		        		  /*DhUtil.clearAlert();
	              		        		  //清空必要数据
	              		        		  fieldArr = [];
	              		        		  fieldIdx = [];
	              		        		  dhArr = [];
	              		        		  dh = $("input[name='dh']").attr("value");
	              		        		  dhRule = "";
	              		        		  flag = 0;
	              	
	              		        		  //保存或保存并新增的标识,0表示保存,1表示保存并新增
	              		        		  saveFlag = 0;
	              		        		  //卷内顺序号
	              		        		  jnsxh = 0;
	              		        		  DhUtil.checkDhAndRule();*/
	              		        		  
	              		        	  parent.mainIframe.refreshGrid();
	              	        	  }else{
	              	        		  layer.msg(data.msg);
	              		          }
	              	          },
	              	          error: function() {
	              	        	layer.close(loadIndex);
	              	             layer.msg("保存失败");
	              	          }
	              	        });
	                    }
	                },
	                error: function(ex) {
	                	layer.close(loadIndex);
	                    layer.alert("上传附件请求失败", {  skin: 'layui-layer-lan',closeBtn: 0});
	                }
	            });
	        }else{
	        //修改
	       
	        $.ajax({
	          url: Util.getRootPath()+"/w/example/ygtable/saveOrUpdate/"+tablename+"/"+fileUUID,
	          type: "POST",
	          dataType: "json",
	          data: data.field,
	          async: true,
	          success: function(data) {
	        	  if(data.success){
	        		  layer.close(loadIndex);
		            	layer.msg("保存成功");
		        	  addNum();
		        	  //保存后关闭弹窗
		        	  if(!isAdd && !isNext){
		        		  var idx = parent.layer.getFrameIndex(window.name); //获取窗口索引
		        		  layer.msg("保存成功");
		        		  parent.layer.close(idx);
		        	  }
		        	  //点击增加复选框
		        	  if(isAdd && !isNext){
		            		window.location.href = Util.getRootPath() + "/w/example/ygtable/adddetail/"+tablename;
		        	  }
		        	  //保存并新增，不关闭弹窗，并将此作为新增档案的模板
		        		  /*DhUtil.clearAlert();
		        		  //清空必要数据
		        		  fieldArr = [];
		        		  fieldIdx = [];
		        		  dhArr = [];
		        		  dh = $("input[name='dh']").attr("value");
		        		  dhRule = "";
		        		  flag = 0;
	
		        		  //保存或保存并新增的标识,0表示保存,1表示保存并新增
		        		  saveFlag = 0;
		        		  //卷内顺序号
		        		  jnsxh = 0;
		        		  DhUtil.checkDhAndRule();*/
		        		  
		        	  parent.mainIframe.refreshGrid();
	        	  }else{
	        		  layer.msg(data.msg);
		          }
	          },
	          error: function() {
	        	  layer.close(loadIndex);
	             layer.msg("保存失败");
	          }
	        });
	        }
	        return false;
		});
		
		form.on('checkbox(isAdd)', function(data){
			isAdd = data.elem.checked;
			
		}); 
		form.on('checkbox(isNext)', function(data){
			isNext= data.elem.checked;
		}); 
	});

}


function initDetailMethod(){
	//关闭
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	
}