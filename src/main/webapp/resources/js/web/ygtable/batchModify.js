var type = $.trim($('[name="dhDicId"]').val());
$(function(){
	initMethod();
	initDh();
	
	listenSubmit();
});

function initMethod(){
	//关闭
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	
}


function listenSubmit(){
	layui.use(['form','jquery','laydate'], function(){
		var laydate = layui.laydate;
	    var $ = layui.jquery,
	    form = layui.form();
	    
	    //选择预归档档案uuid集合
	    var uuids = [];
		var files = parent.mainIframe.grid.getCheckedRecords();
		for(var i = 0;i<files.length;i++){
			uuids.push(files[i].uuid);
		}
		

	    //监听提交
		form.on('submit(update)', function(data){
			var fields = {};
			fields['uuids'] = uuids;//修改的uuid数组
			
			
			var flag = false;
		    var daglfixfields = $('[name^="dagl_fix_"]');//是否修改
		    
		    var autofields = new Array();
		    daglfixfields.each(function(){
		    	if(data.field[$(this).attr('name')]){//修改按钮是否勾选
		    		flag = true;
		    		var fieldname = $($(this).parent().find('[name]')[0]).attr('name');//需要修改的input框name e.g dh
		    		fields[fieldname] = data.field[fieldname];//自增字段
		    		
		    		//自增字段
		    		var idx = $(this).attr('name').substring(9); //数字
		    		var autoname = "dagl_auto_" + idx;
		    		if(data.field[autoname]){
		    			autofields.push(fieldname);
		    		}
		    	}
		    });
		    if(!flag){
		    	layer.alert("请选择一种字段修改！", {skin: 'layui-layer-lan',closeBtn: 0});
				return false;
		    }
		    
		    fields['autofields'] = autofields; //自增字段集合
		    var tablename = $.trim($('[name="tablename"]').val());
			$.ajax({
		          url:Util.getRootPath()+"/w/example/ygtable/batchModify/"+tablename,
		          type: "POST",
		          dataType: "json",
		          data: fields,
		          async: false,
		          success: function(data) {
		        	  if(data.success){
			        	  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		        		  layer.msg("修改成功");
		  	              parent.layer.close(index);
		  	              
		  	              parent.mainIframe.refreshGrid();
		        	  }else{
		        		  layer.alert(data.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
		        	  }
		          },
		          error: function() {
		             layer.msg("修改请求失败");
		          }
			   });	
			
			return false;
		});
	});
}



function initDh(){
	
	//获取档案的档号规则
	Util.ajaxSync(
		Util.getRootPath() + "/w/fileNum/getRule",
		{type:type},
		function(result) {
			if (result.success && result.datas != null && result.datas.length > 0){
				result = result.datas;
				number = result.length;
				//清空规则设置表单
				$(".dahForm").empty();
				var html = '';
				html += '<input name="type" value="' + result[0].type + '" class="hidden">';
				html += '<div class="form-group dahPreview container-fluid">';
				html += '	<label class="col-xs-3 control-label" style="padding-top:0px;color:red;" >当前档号格式:</label>';
		    	html += '	<div class="col-xs-9 prePContent">';
		    	html += '	</div>';
				html += '</div>';
				$(".dahForm").append(html);
				
				
				for(var i = 0; i<result.length;i++) {
					initRule(result[i]);
				}
			}
		},function() {
			layer.alert('请求失败！');
		}
	);

}


function initRule(data) {
	//动态添加规则
	var preP = '';
	var time = new Date();
	if(data.title == "text") {
		preP += '		<span class="preP colum' + data.num + '">' + data.value + '</span>';
	}else if(data.title == "year") {
		preP += '		<span class="preP colum' + data.num + '">' + time.getFullYear() + '</span>';
	}else if(data.title == "month") {
		if(time.getMonth() < 9) {
			preP += '		<span class="preP colum' + data.num + '">0' + (time.getMonth() + 1) + '</span>';
		}else {
			preP += '		<span class="preP colum' + data.num + '">' + time.getMonth() + 1 + '</span>';
		}
	}else if(data.title == "date") {
		if(time.getDate() < 10) {
			preP += '		<span class="preP colum' + data.num + '">0' + time.getDate() + '</span>';
		}else {
			preP += '		<span class="preP colum' + data.num + '">' + time.getDate() + '</span>';
		}
	}else if(data.title == "number") {
		var str = setNum(data.value);
		preP += '		<span class="preP colum' + data.num + '">' + str +'1</span>';
	}else {
		//发送请求获取所有表字段
		Util.ajaxSync(
			Util.getRootPath() + "/w/fileNum/getTableField",
			{type:type},
			function(result) {
				if(result.datas != null) {
					result = result.datas;
					for(var i = 0; i < result.length;i++ ) {
						
						if(data.title == result[i].vals.ZDYWM) {
							preP += '		<span class="preP colum' + data.num + '" data-type="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</span>';
						}
					}
				}
			}
		);
	}
	//设置下拉选对应值选中
	$(".dahPreview").find(".prePContent").append(preP);
}
