$(function(){
	//下拉框值
	var selval;
	//字段复选框的值
	var paramsval=[];
	//字段复选框的中文值
	var paramsname=[];
	//所选图片
	var typeval=[];
	/*隐藏显示*/
	var jump=function(o){
		/*将lang拆分数组*/
		var obj=o.attr("lang").split(' ');
		var show="$('."+obj[0]+"').show();"
		var hide="$('."+obj[1]+"').hide();"
		eval(show);
		eval(hide);	
	}
	
	/*选择框下一步按钮点击事件*/
	$(".charts_biaoSel button.next").bind("click",function(){
		var obj=$(this);
		selval=$("select.dab option:selected").val();
		$.ajax({
			url:Util.getRootPath()+"/w/example/statistics/getdalx/"+selval,
			type:'POST',
			success:function(res){
				createtab(res,$("select.dab option:selected").text(),$(".charts_params .charts_table"),"zd_params")
				jump(obj);
			}
		})
	});
	
	/*
	 * 拼接复选框 
	 */
	var createtab=function(res,tabTitle,tabObj,tabname){
		tabObj.empty();
		var arr=Object.keys(res);
		var str="<table class='table'><tr><th colspan='4'>"+tabTitle+"</th>";
		var i=0;
		$.each(res,function(ind,obj){
			for(;i<arr.length;){
				if(i%4==0){
					str+="</tr><tr>"
				}
				str+="<td><label class='checkbox-inline'>"+
                        "<input type='checkbox' name='"+tabname+"' id='zd_params' value="+ind
                        +"><span>"+obj+"</span></label></td>";
				i++;
				return;
			}
			
		});
		str+="</tr></table>";
		tabObj.append(str);
	}
	
	/*复选框按钮点击事件*/
	$(".charts_params button.next").bind("click",function(){
		var obj=$(this);
		if(validate("zd_params")){
			$.ajax({
				url:Util.getRootPath()+"/w/example/statistics/tbType",
				type:'POST',
				success:function(res){
					createtab(res,'图标展示类型',$(".charts_type .charts_table"),"tu_type");
					jump(obj);
				}
			});
		}
	});

	/*复选框按钮点击事件*/
	$(".charts_type button.next").bind("click",function(){
		$(".chart_tabs").empty();
		$(".chart_content").empty();
		if(validate("tu_type")){
			console.log(selval+"	"+paramsval+"	"+ typeval)
			/*循环字段个数*/
			$.each(paramsval,function(i,o){
				//清空字符串
				var str="";
				$.ajax({
					url:Util.getRootPath()+"/w/example/statistics/createCharts",
					type:'POST',
					data:{"tablename":selval,"cols":o},
					success:function(res){
						//拼接table
						str="<div class='chart tab-pane' id='"+o+"' style='position: relative; height: 300px;'><div class='charts_table col-xs-4'><strong style='font-size:18px;'>"+paramsname[i]+"</strong><div class='row'><div class='col-xs-12'><table class='table table-hover table-bordered table-striped'>"
						+"<tr><th>字段名</th><th>数量</th></tr>"
						$.each(res,function(ind,obj){
							$.each(obj,function(index,object){
								str+="<tr><td>"+index+"</td><td>"+object+"</td></tr>";
							});
						})
						str+="</table></div></div></div>";
						//图表存放地方
						str+="<div class='row'><div class='col-xs-4 col-xs-offset-1'><div class='charts_tuli"+i+"' style='width:400px;height:300px;'></div></div>";
						//拼写tabs选项卡
						var strtabs="<li><a href=#"+o+" data-toggle='tab'>"+paramsname[i]+"</a></li>";
						$(".chart_tabs").append(strtabs);
						$(".chart_content").append(str);
						//默认第一个选择
						$(".chart_tabs li:first").addClass("active");
						$(".chart_content>div:first").addClass("active");
						//加载图表
						tu.bintu($(".charts_tuli"+i)[0],paramsname[i],res);
					}
				})
			});
			jump($(this));
		}
	});
	
	/*返回上一页事件*/
	$(".btn_zu").children("button.return").bind("click",function(){
		jump($(this));
				
	});
	
	/*验证复选框不能为空*/
	var validate = function(name){
		var num=$("input[name='"+name+"']:checked");
		if(num.size()===0){
			layer.msg('选项不能为空');
			return false;
		}
		if(name==='zd_params'){
			 paramsval=[];
			 paramsname=[];
			 num.each(function() {
			 	paramsval.push($(this).val());
			 	paramsname.push($(this).next().html())
			 });
		 }else{
			 typeval=[];
		 	 num.each(function() {
			 	typeval.push($(this).val());
			 });
		 }
		return true;
	}
});
	
