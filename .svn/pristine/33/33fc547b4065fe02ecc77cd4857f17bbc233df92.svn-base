$(function() {
	var index = layer.load(1, {shade: [0.1,'#FFFFFF']});
	initDicValueTable();
//	initForm("kujda");
	initBtn();
	inputSynchronize();
//	selectSynchronize();
	layer.close(index);
});
//var type;

function initDicValueTable(){
	dicValueGrid=$.fn.DtGrid.init({
		loadURL : Util.getRootPath() + "/w/dicmanager/getDicValueList",
	    ajaxLoad : true,
	    gridContainer : 'dicValueTable',
	    toolbarContainer : 'pagingDicValue',
	    lang : 'zh-cn',
	    tools : '',
	    pageSize : 10,
	    check : false,
	    pageSizeLimit : false,
	    columns : [
		       		{
		       			id:'dvalue',
		       			title : '<b>档案类型</b>',
		       			type:"string",
		       			columnClass:'text-center',
		       			resolution:function(value, record, column, grid, dataNo, columnNo){
//		       				setFirstRule(record.id);
//		       				type = record.id;
			       			//存储record类，编辑时使用
		       				var html = ''; 	
		       				html += '<p data-id="' + record.id + '" data-tableName="' + record.dvno + '">' + record.dvalue + '</p>';
			       			
		       				return html;
		       			}
		       		}
		       		]
	});
	refreshDicValueGrid();
}

function setFirstRule() {
//	alert($(".typeForm tr:eq(0)").find("p").attr("data-id"));
//	alert(id);
	initForm(id);
}
function refreshDicValueGrid() {
	dicValueGrid.parameters = getDicValueParameters();
	dicValueGrid.load();
}
function getDicValueParameters() {
//	var id = $('[name="dictionaryId"]').val();
	return {"id":"402881085c8b496e015c8b8808fc0003"};
}
//
var i = 2;
var number = 6;
//档案类型
var type;
//按钮点击事件
var addType;
function initBtn() {
	
//	$(".test").on("click",function() {
////		window.location.href = Util.getRootPath()+"/w/OAService/getOaDataByDate?startDate=2017-06-01 23:59:59&endDate=2017-06-02 23:59:59";
//		window.location.href = Util.getRootPath()+"/w/fileNum/getDahByType?type=402885215c09bc95015c09c7d0ce0002";
//	});
//	var $dahPreview = $(".dahPreview");
//	var $preContent = $(".preContent");
//	var $prePContent = $(".prePContent");
	//弹框提交按钮点击
	$(".add").on('click',function() {
//		alert($('radio[name="column"]:checked').val());
		var addType = $("form").find("input[type=radio]:checked").attr("value");
		if(addType.length == 0) {
			layer.alert('请选择一个类型', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			return;
		}
		var html = '';
		//格式预览
		var preHtml = "";
		//档号预览
		var prePHtml = '';
//		alert(parent.mainIframe.number);
		parent.mainIframe.number = parent.mainIframe.number + 1;
		if(addType == "text") {
			html += '<div class="form-group colum">';
			html += '	<label class="col-sm-2 control-label" for="username">自定义字符串</label>';
			html += '	<div class="col-sm-6">';
			html += '		<input name="colum' + parent.mainIframe.number + '" class="form-control">';
			html += '	</div>';
			html += '	<div class="col-sm-4">';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
			html += '	</div>';
			html += '</div>';
//			$(".dahPreview").before(html);
			preHtml += '<div class="headDiv colum' + parent.mainIframe.number + '">';
			preHtml += '	<p class="title">自定义字符串</p>';
			preHtml += '	<p class="cont">无</p>';
			preHtml += '</div>';
//			$(".preContent").append(preHtml);
			
			
			prePHtml += '		<span class="preP colum' + parent.mainIframe.number + '"></span>';
//			$(".prePContent").append(prePHtml);
		}
		if(addType == "tableField") {
//			<div class="layui-form-item">
			html += '<div class="form-group layui-form-item colum">';
			html += '	<label class="col-sm-2 control-label" for="username">表字段</label>';
			html += '	<div class="col-sm-6 layui-input-block">';
			html += '		<select class="form-control" name="colum' + parent.mainIframe.number + '" onchange="selectOnchang(this)">';
			preHtml += '<div class="headDiv colum' + parent.mainIframe.number + '">';
//			preHtml += '	<p class="title">表字段</p>';
//			alert(parent.mainIframe.type);
			//发送请求获取所有表字段
			Util.ajaxSync(
				Util.getRootPath() + "/w/fileNum/getTableField",
				{type:parent.mainIframe.type},
				function(result) {
					if(result.datas != null) {
						result = result.datas;
						for(var i = 0; i < result.length;i++ ) {
							if(i == 0) {
								html += '			<option selected value="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</option>';
								preHtml += '	<p class="title" data-flag="1" data-id="' + result[i].vals.ID + '">表字段</p>';
								preHtml += '	<p class="cont" data-type="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</p>';
								prePHtml += '		<span class="preP colum' + parent.mainIframe.number + '" data-type="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</span>';
							}else {
								html += '			<option value="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</option>';	
							}
						}
					}
				},function() {
					layer.alert('请求失败！');
				}
			);
			
//			html += '		<input name="colum' + number + '" class="form-control">';
//			html += '			<option value="qzh">全宗号</option>';
//			html += '			<option value="jh">件号</option>';
//			html += '			<option value="mlh">目录号</option>';
			html += '		</select>';
			html += '	</div>';
			html += '	<div class="col-sm-4">';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
			html += '	</div>';
			html += '</div>';
//			$(".dahPreview").before(html);
//			preHtml += '<div class="headDiv colum' + parent.mainIframe.number + '">';
//			preHtml += '	<p class="title">表字段</p>';
//			preHtml += '	<p class="cont">无</p>';
			preHtml += '</div>';
//			$(".preContent").append(preHtml);
			
			
//			prePHtml += '		<span class="preP colum' + parent.mainIframe.number + '"></span>';
//			$(".prePContent").append(prePHtml);
		}
		if(addType == "number") {
			html += '<div class="form-group colum">';
			html += '	<label class="col-sm-2 control-label" for="username">流水号位数</label>';
			html += '	<div class="col-sm-6">';
			html += '		<input name="colum' + parent.mainIframe.number + '" class="form-control number">';
			html += '	</div>';
			html += '	<div class="col-sm-4">';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
			html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
			html += '	</div>';
			html += '</div>';
//			$(".dahPreview").before(html);
			preHtml += '<div class="headDiv colum' + parent.mainIframe.number + '">';
			preHtml += '	<p class="title">流水号位数</p>';
			preHtml += '	<p class="cont">无</p>';
			preHtml += '</div>';
//			$(".preContent").append(preHtml);
			
			
			prePHtml += '		<span class="preP colum' + parent.mainIframe.number + '"></span>';
//			$(".prePContent").append(prePHtml);
		}
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
		//向父页面添加字段
		parent.mainIframe.$(".dahPreview").before(html);
		parent.mainIframe.$(".preContent").append(preHtml);
		parent.mainIframe.$(".prePContent").append(prePHtml);
		//关闭弹框，并向页面添加字段
//		add(html,preHtml,prePHtml);
	});
	//关闭弹框
	$(".closeup").on("click",function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	//模态框提交按钮
//	$(".saveDalx").on("click",function() {
//		var name = $("[name='daName']").val();
//		var type = $("[name='daType']").val();
//		var html = '';
//		html += '<div class="form-group">';
//		html += '	<div class="col-sm-12">';
//		html += '		<p name="' + type + '" class="form-control" data-type="kjda">' + name + '</p>';
//		html += '	</div>';
//		html += '</div>';
//		$(".typeForm").append(html);
////		$('name="daName"').val("");
////		$('name="daType"').val("");
//	});
	//模态框关闭时清空
//	$('#dalx').on('hidden.bs.modal', function () {
//		$('[name="daName"]').val("");
//		$('[name="daType"]').val("");
//	});
	//左侧档案列表点击事件
	$("#dicValueTable").on("click","p",function() {
		$("#dicValueTable").find("tbody").find("tr").removeClass("selectDa");
		//设置当前点击行选中
		$(this).parent().parent().addClass("selectDa");	
		var id = $(this).attr("data-id");
		type = id;
		initForm(id);
	});
	//上移按钮
	$(".dahForm").on("click",".up",function() {
		//表单区域移动
		var formGroup = 'form-group';
		$formGroup = $(this).parents('.'+ formGroup);
		if($formGroup.prev('.'+formGroup).html() != undefined){ 
			var obj = $formGroup.clone(true); 
			$formGroup.prev().before(obj); 
			$formGroup.remove();
			
			//预览区域移动
//			var clazz = $formGroup.find("input").attr("name");
//			var formGroup = 'headDiv';
			var $preP;
			if($formGroup.find("input").attr("name") == undefined) {
				$preDiv = $(".preContent").find('.'+ $formGroup.find("select").attr("name"));
				
				$preP = $(".prePContent").find('.'+ $formGroup.find("select").attr("name"));
			}else {
				$preDiv = $(".preContent").find('.'+ $formGroup.find("input").attr("name"));
				$preP = $(".prePContent").find('.'+ $formGroup.find("input").attr("name"));
			}
			var obj = $preDiv.clone(true); 
			$preDiv.prev().before(obj); 
			$preDiv.remove();
			
			
//			var $preP = $(".prePContent").find('.'+ $formGroup.find("input").attr("name"));
			var obj = $preP.clone(true); 
			$preP.prev().before(obj); 
			$preP.remove();
		}else{ 
			layer.alert('亲，现在已是最上的哦，不能再上移了...');
		}
	});
	//下移按钮
	$(".dahForm").on("click",".down",function() {
		var formGroup = 'form-group';
		$formGroup = $(this).parents('.'+ formGroup);
		if($formGroup.next('.'+formGroup).html() != undefined && $formGroup.next('.'+formGroup).attr("class") != "form-group dahPreview"){ 
			var obj = $(this).parents('.'+formGroup).clone(true); 
			$formGroup.next().after(obj);
			$formGroup.remove(); 
			
			//预览区域移动
			$preDiv = $(".preContent").find('.'+ $formGroup.find("input").attr("name"));
			var obj = $preDiv.clone(true); 
			$preDiv.next().after(obj); 
			$preDiv.remove();
			
			var $preP = $(".prePContent").find('.'+ $formGroup.find("input").attr("name"));
			var obj = $preP.clone(true); 
			$preP.prev().before(obj); 
			$preP.remove();
		}else{ 
			layer.alert('亲，现在已是最下的哦，不能再下移了...'); 
		}
	});
	//新增年月日按钮
	$(".addDate").on("click",function() {
		number ++;
		var text;
		var calzz;
		var prePHtml = '';
		var date = new Date();
		
		//判断年月日字段是否存在
		if($(".dahForm").find(".year").html() == undefined) {
			text = "年份";
			clazz = "year";
			prePHtml += '		<span class="preP colum' + number + '">' + date.getFullYear() + '</span>';
		}else if($(".dahForm").find(".month").html() == undefined) {
			text = "月份";
			clazz = "month";
			if(date.getMonth() < 9) {
				prePHtml += '		<span class="preP colum' + number + '">0' + (date.getMonth() + 1) + '</span>';
			}else {
				prePHtml += '		<span class="preP colum' + number + '">' + (date.getMonth() + 1) + '</span>';
			}
		}else if($(".dahForm").find(".date").html() == undefined) {
			text = "日期";
			clazz = "date";
			
			if(date.getDate() < 9) {
				prePHtml += '		<span class="preP colum' + number + '">0' + date.getDate() + '</span>';
			}else {
				prePHtml += '		<span class="preP colum' + number + '">' + date.getDate() + '</span>';
			}
		}else {
			layer.alert('年月日均已存在。。。'); 
		}
		$(".prePContent").append(prePHtml);
		var html = '';
		html += '<div class="form-group colum ' + clazz + '">';
		html += '	<label class="col-sm-2 control-label" for="username">' + text + '</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + number + '" class="form-control" value="当前' + text + '" readonly>';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		$(".dahPreview").before(html);
		
		
		var preHtml = "";
		preHtml += '<div class="headDiv colum' + number + '">';
		preHtml += '	<p class="title">' + text + '</p>';
		preHtml += '	<p class="cont">当前' + text + '</p>';
		preHtml += '</div>';
		$(".preContent").append(preHtml);
		
		
	});
	//新增字段按钮
	$(".addColum").on("click",function() {
		//弹出框
		var options = {}
    	var url = Util.getRootPath() + "/w/fileNum/addColumn";
    	options.url = url;
    	options.title = "添加字段";
    	window.parent.showModal(options);
	});
	//删除按钮
	$(".dahForm").on("click",".remove",function() {
		//删除预览区域该字段
//		var id = $("." + $(this).parent().parent().find("input").attr("name")).find(".title").attr("data-id");
		var id;
		var flag = 0;
		if($(this).parent().parent().find("input").attr("name") == undefined) {
			
			flag = $("." + $(this).parent().parent().find("select").attr("name")).find(".title").attr("data-flag");
			if(flag == undefined || flag == "undefined") {
				flag = 0;
			}
			id = $("." + $(this).parent().parent().find("select").attr("name")).find(".title").attr("data-id");
			$("." + $(this).parent().parent().find("select").attr("name")).remove();
		}else {
			id = $("." + $(this).parent().parent().find("input").attr("name")).find(".title").attr("data-id");
			$("." + $(this).parent().parent().find("input").attr("name")).remove();
			
		}
		
//		$("." + $(this).parent().parent().find("input").attr("name")).remove();
		//删除编辑区域当前字段
//		$(this).parent().parent().remove();
		if(id != undefined && flag == 0) {
			//删除数据库中数据
			Util.ajaxSync(
				Util.getRootPath() + "/w/fileNum/deleteRule",
				{id:id},
				function(result) {
					
				},function() {
					layer.alert('请求失败！');
				}
			);
		}
		$(this).parent().parent().remove();
//		initI --;
	});
	//点击提交按钮，提交数据
	$(".saveDah").on("click",function() {
		//档案类型
		var type = $(".dahForm").find("[name='type']").attr("value");
		if (type.length == 0) {
			  layer.alert('请选择一个档案类型', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
			  return;
		}
		//序号
		var num = 0;
		//字段数组
//		var columArr = [];
		var i = 0;
		$(".dahPreview").find(".headDiv").each(function() {
//			alert($(this).find(".title").attr("data-id"));
			var colum = {};
			num ++;
			colum.num = num;
			colum.id = $(this).find(".title").attr("data-id");
			var title = $(this).find(".title").text();
			//非空校验
//			if (type.length == 0) {
//				  layer.alert('您还未选择一个档案类型', {skin: 'layui-layer-lan',closeBtn: 0,anim: 5});
//				  return;
//			}
			
			
			
			colum.type = type;
			if(title == "年份") {
				colum.title = "year";
				colum.value = "%year%"
			}else if(title == "月份") {
				colum.title = "month";
				colum.value = "%month%";
			}else if(title == "日期") {
				colum.title = "date";
				colum.value = "%date%"
			}else if(title == "流水号位数") {
				colum.title = "number";
				var text = $(this).find(".cont").text();
				colum.value = text;
			}else if(title == "自定义字符串") {
				colum.title = "text";
				var text = $(this).find(".cont").text();
				colum.value = text;
			}else {
				colum.title = $(this).find(".cont").attr("data-type");
//				colum.value = "%special%";
				colum.value = "%" + $(this).find(".cont").attr("data-type") + "%";
			}
			
			Util.ajaxSync(
				Util.getRootPath() + "/w/fileNum/saveRules",
				colum,
				function(result) {
					if (result.success){
						i ++;
					}
				},function() {
					layer.alert('请求失败！');
				}
			);
		});
		if(i > 0){
			var id = $("input[name='type']").attr("value");
			initForm(id);
			layer.alert('保存成功');
		}
//		alert(JSON.stringify(columArr));
	});
}
//初始化表单
function initForm(type){
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
//				initI = 0;
				var html = '';
				html += '<input name="type" value="' + result[0].type + '" class="hidden">';
				html += '<div class="form-group dahPreview">';
				html += '	<label class="col-sm-2 control-label" for="telphone">格式预览</label>';
				html += '	<div class="col-sm-10 preContent">';
//					html += '		<div class="headDiv colum1">';
//					html += '			<p class="title">字符串1</p>';
//					html += '			<p class="cont"></p>';
				html += '	</div>';
				html += '	<label class="col-sm-2 control-label" for="telphone">档号预览</label>';
		    	html += '	<div class="col-sm-10 prePContent">';
//		    	html += '		<span class="preP colum1"><span>';
//		    	html += '		<span class="preP colum1"><span>';
//		    	html += '		<span class="preP colum1"><span>';
//		    	html += '		<span class="preP colum1"><span>';
//		    	html += '		<span class="preP colum1"><span>';
//		    	html += '		<span class="preP colum1"><span>';
		    	html += '	</div>';
				html += '</div>';
				$(".dahForm").append(html);
				
				
				for(var i = 0; i<result.length;i++) {
					initRule(result[i]);
				}
			}else {
				//还原原始表单
				$(".dahForm").empty();
				var html = '';
				html += '<input name="type" value="' + type + '" class="hidden">';
				html += '<div class="form-group colum">';
				html += '	<label class="col-sm-2 control-label" for="username">自定义字符串</label>';
		    	html += '	<div class="col-sm-6">';
		        html += '		<input name="colum1" class="form-control">';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
			
		    	html += '<div class="form-group colum">';
		    	html += '	<label class="col-sm-2 control-label" for="telphone">自定义字符串</label>';
		    	html += '	<div class="col-sm-6">';
		    	html += '		<input name="colum2" class="form-control">';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
		    	html += '<div class="form-group colum year">';
		    	html += '	<label class="col-sm-2 control-label" for="username">年份</label>';
		    	html += '	<div class="col-sm-6">';
		    	html += '		<input name="colum3" class="form-control" value="当前年份" readonly>';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
		    	html += '<div class="form-group colum month">';
		    	html += '	<label class="col-sm-2 control-label" for="username">月份</label>';
		    	html += '	<div class="col-sm-6">';
		    	html += '		<input name="colum4" class="form-control" value="当前月份" readonly>';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
		    	html += '<div class="form-group colum date">';
		    	html += '	<label class="col-sm-2 control-label" for="username">日期</label>';
		    	html += '	<div class="col-sm-6">';
		    	html += '		<input name="colum5" class="form-control" value="当前日期" readonly>';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
		    	html += '<div class="form-group colum">';
		    	html += '	<label class="col-sm-2 control-label" for="telphone">流水号位数</label>';
		    	html += '	<div class="col-sm-6">';
		    	html += '		<input name="colum6" class="form-control number">';
		    	html += '	</div>';
		    	html += '	<div class="col-sm-4">';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		    	html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		    	html += '	</div>';
		    	html += '</div>';
		    
		    	html += '<div class="form-group dahPreview">';
		    	html += '	<label class="col-sm-2 control-label" for="telphone">格式预览</label>';
		    	html += '	<div class="col-sm-10 preContent">';
		    	html += '		<div class="headDiv colum1">';
		    	html += '			<p class="title">自定义字符串</p>';
		    	html += '			<p class="cont">无</p>';
		    	html += '		</div>'
			    	
		    	html += '		<div class="headDiv colum2">';
		    	html += '			<p class="title">自定义字符串</p>';
		    	html += '			<p class="cont">无</p>';
		    	html += '		</div>';
			    	
		    	html += '		<div class="headDiv colum3">';
		    	html += '			<p class="title">年份</p>';
		    	html += '			<p class="cont">当前年份</p>';
		    	html += '		</div>';
		    	html += '		<div class="headDiv colum4">';
		    	html += '			<p class="title">月份</p>';
		    	html += '			<p class="cont">当前月份</p>';
		    	html += '		</div>';
		    	html += '		<div class="headDiv colum5">';
		    	html += '			<p class="title">日期</p>';
		    	html += '			<p class="cont">当前日期</p>';
		    	html += '		</div>';
			    	
		    	html += '		<div class="headDiv colum6">';
		    	html += '			<p class="title">流水号位数</p>';
		    	html += '			<p class="cont">无</p>';
		    	html += '		</div>';
		    	html += '	</div>';
		    	html += '	<label class="col-sm-2 control-label" for="telphone">档号预览</label>';
		    	html += '	<div class="col-sm-10 prePContent">';
		    	html += '		<span class="preP colum1"></span>';
		    	html += '		<span class="preP colum2"></span>';
		    	var date = new Date();
		    	
		    	html += '		<span class="preP colum3">' + date.getFullYear() + '</span>';
		    	if(date.getMonth() < 9) {
		    		html += '		<span class="preP colum4">0' + (date.getMonth() + 1) + '</span>';
		    	}else {
		    		html += '		<span class="preP colum4">' + (date.getMonth() + 1) + '</span>';
		    	}
		    	if(date.getDate() < 9) {
		    		html += '		<span class="preP colum5">0' + date.getDate() + '</span>';
		    	}else {
		    		html += '		<span class="preP colum5">' + date.getDate() + '</span>';
		    	}
		    	html += '		<span class="preP colum6"></span>';
		    	html += '	</div>';
		    	
		    	
		    	html += '</div>';
		    	$(".dahForm").append(html);
			}
		},function() {
			layer.alert('请求失败！');
		}
	);
}
//动态拼接页面，展示相应档案的档号规则
//var initI = 0;
function initRule(data) {
	//动态添加规则
	var html = '';
	//预览区域
	var preHtml = '';
	preHtml += '<div class="headDiv colum' + data.num + '">';
	var preP = '';
	var time = new Date();
	if(data.title == "text") {
//		initI ++;
		html += '<div class="form-group colum">';
		html += '	<label class="col-sm-2 control-label" for="username">自定义字符串</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + data.num + '" class="form-control" value="' + data.value + '">';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		
		preHtml += '	<p class="title" data-id="' + data.id + '">自定义字符串</p>';
		preHtml += '	<p class="cont">' + data.value +'</p>';
		
		preP += '		<span class="preP colum' + data.num + '">' + data.value + '</span>';
	}else if(data.title == "year") {
		html += '<div class="form-group colum year">';
		html += '	<label class="col-sm-2 control-label" for="username">年份</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + data.num + '" class="form-control" value="当前年份" readonly>';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		preHtml += '	<p class="title" data-id="' + data.id + '">年份</p>';
		preHtml += '	<p class="cont">当前年份</p>';
		
		preP += '		<span class="preP colum' + data.num + '">' + time.getFullYear() + '</span>';
	}else if(data.title == "month") {
		html += '<div class="form-group colum month">';
		html += '	<label class="col-sm-2 control-label" for="username">月份</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + data.num + '" class="form-control" value="当前月份" readonly>';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		preHtml += '	<p class="title" data-id="' + data.id + '">月份</p>';
		preHtml += '	<p class="cont">当前月份</p>';
		if(time.getMonth() < 9) {
			preP += '		<span class="preP colum' + data.num + '">0' + (time.getMonth() + 1) + '</span>';
		}else {
			preP += '		<span class="preP colum' + data.num + '">' + time.getMonth() + 1 + '</span>';
		}
	}else if(data.title == "date") {
		html += '<div class="form-group colum date">';
		html += '	<label class="col-sm-2 control-label" for="username">日期</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + data.num + '" class="form-control" value="当前日期" readonly>';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		preHtml += '	<p class="title" data-id="' + data.id + '">日期</p>';
		preHtml += '	<p class="cont">当前日期</p>';
		if(time.getDate() < 10) {
			preP += '		<span class="preP colum' + data.num + '">0' + time.getDate() + '</span>';
		}else {
			preP += '		<span class="preP colum' + data.num + '">' + time.getDate() + '</span>';
		}
	}else if(data.title == "number") {
		html += '<div class="form-group colum">';
		html += '	<label class="col-sm-2 control-label" for="username">流水号位数</label>';
		html += '	<div class="col-sm-6">';
		html += '		<input name="colum' + data.num + '" class="form-control number" value=" ' + data.value + '">';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		preHtml += '	<p class="title" data-id="' + data.id + '">流水号位数</p>';
		preHtml += '	<p class="cont">' + data.value +'</p>';
		
		var str = setNum(data.value);
		preP += '		<span class="preP colum' + data.num + '">' + str +'1</span>';
	}else {
		html += '<div class="form-group layui-form-item colum">';
		html += '	<label class="col-sm-2 control-label" for="username">表字段</label>';
		html += '	<div class="col-sm-6 layui-input-block">';
		html += '		<select class="form-control" name="colum' + data.num + '" onchange="selectOnchang(this)">';
		//发送请求获取所有表字段
		Util.ajaxSync(
			Util.getRootPath() + "/w/fileNum/getTableField",
			{type:parent.mainIframe.type},
			function(result) {
				if(result.datas != null) {
					result = result.datas;
					for(var i = 0; i < result.length;i++ ) {
						
						if(data.title == result[i].vals.ZDYWM) {
							html += '			<option value="' + result[i].vals.ZDYWM + '" selected>' + result[i].vals.ZDZWM + '</option>';
							preHtml += '	<p class="title" data-id="' + data.id + '">表字段</p>';
							preHtml += '	<p class="cont" data-type="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</p>';
							preP += '		<span class="preP colum' + data.num + '" data-type="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</span>';
						}else {
							html += '			<option value="' + result[i].vals.ZDYWM + '">' + result[i].vals.ZDZWM + '</option>';
//							preHtml += '	<p class="title">表字段</p>';
//							preHtml += '	<p class="cont">' + data.value + '</p>';
//							preP += '		<span class="preP colum' + data.num + '" data-type="' + data.title + '">' + data.value + '</span>';
						}
					}
				}
			},function() {
				layer.alert('请求失败！');
			}
		);
		//设置下拉选对应值选中
		
		
		
		html += '		</select>';
		html += '	</div>';
		html += '	<div class="col-sm-4">';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-minus remove"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-up up"></span>';
		html += '		<span type="button" class="spanBtns glyphicon glyphicon-arrow-down down"></span>';
		html += '	</div>';
		html += '</div>';
		
		
		
//		preP += '		<span class="preP colum' + data.num + '" data-type="' + data.title + '">' + data.value + '</span>';
//		$(".prePContent").append(prePHtml);
	}
//	preHtml += '	<p class="cont">' + data.value +'</p>';
	preHtml += '</div>'
	$(".dahForm").find(".preContent").append(preHtml);
	$(".dahForm").find(".prePContent").append(preP);
	$(".dahForm").find(".dahPreview").before(html);
}
//字段输入框失去焦点时，将值同步到预览区域
function inputSynchronize() {
//	$(".dahForm").find(".form-control").blur(function() {
	$(".dahForm").on('blur',"input",function() {
		if($(this).val().trim() != "") {
			$(".dahForm").find("." + $(this).attr("name")).find(".cont").text($(this).val().trim());
			if($(this).attr("class") == "form-control number") {
				$(".dahForm").find(".prePContent").find("." + $(this).attr("name")).text(setNum($(this).val().trim()) + "1");
			}else {
				$(".dahForm").find(".prePContent").find("." + $(this).attr("name")).text($(this).val().trim());
			}
		}
	});
//	$(".dahForm").find("select").change(function() {
//		alert(0);
//	});
//	var form = layui.form();
//	form.select({
//	  change: function(val){
//	    alert(val)
//	  }
//	})
}
function selectOnchang(data) {
	var clazz = $(data).attr("name");
	var title = $(data).find("option:selected").val();
	var text = $(data).find("option:selected").text();
	//预览区域设值
	$(".dahForm").find("." + clazz).find(".cont").text(text).attr("data-type",title);
	$(".dahForm").find(".prePContent").find("." + clazz).text(text).attr("data-type",title);
	
}
//流水号的预览
function setNum(n) {
	var str = '';
	for(var i = 1;i<n;i++) {
		str += "0";
	}
	return str;
}
