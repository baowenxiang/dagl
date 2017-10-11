/**
 * 档号规则工具
 */
var DhUtil = function() {
	return {
		/**
		 * 校验当前档号是否是根据档号规则生成
		 */
		checkDhAndRule : function() {
			//根据规则生成的档号
			var tableName = $("input[name='tablename']").val();
			//表id
			var tableId = getTableId(tableName);
			if(tableId){
				dhRule = getDhRule(tableId);
				dh = $("input[name='dh']").attr("value");
				//0表示已有档号是根据档号规则生成的
				var eqFlag = 0;
				for(var i = 0;i < fieldArr.length;i++) {
					var val = $("input[name='" + fieldArr[i] + "']").attr("value");
//					alert(val + "," + dh + "," + dh.indexOf(val));
					if(dh.indexOf(val) == -1 || val == "") {
						eqFlag++;
					}
				}
//				dh = $("input[name='dh']").attr("value");
				if(dh == "") {
					$("input[name='dh']").attr("value",dhRule);
					eqFlag = 0;
				}
				if(eqFlag == 0) {
					dhChange();
				}
			}else{
				console.log("请添加系统字典档案类型(生成档号)【%s】定义。", tableName);
			}
			
		},
		/**
		 * 清空表单
		 */
		clearAlert : function() {
			//清空form表单
			$(':input','.form') 
			.not(':button, :submit, :reset, :hidden') 
			.attr("value","")
			.removeAttr('checked') 
			.removeAttr('selected');
			//清空附件
			$(".file-div").empty();
		},
		/**
		 * 将当前档案的部分数据作为新档案的模板
		 */
		initNewForm : function() {
			//档号
			$("input[name='dh']").val($("input[name='dh']").attr("value"));
			//卷内顺序号 案卷号
			if($("input[name='jnsxh']").html() == undefined) {
				$("input[name='ajh']").val($("input[name='ajh']").attr("value"));
			}else {
				$("input[name='jnsxh']").val($("input[name='jnsxh']").attr("value"));
			}
		}
	,
		getDh : function() {
			var tableName = $("input[name='tablename']").val();
			var tableId = getTableId(tableName);
			if(tableId){
				Util.ajaxSync(
				        Util.getRootPath() + "/w/fileNum/getDahByType",
				        {
				        	type: tableId
				        },
				        function(result){
				        	
				        }, function(ex){
				            console.log("请检查【%s】的档号规则是否配置。", tableId);
				        }
				    );
			}else{
				console.log("请添加系统字典档案类型(生成档号)【%s】定义。", tableName);
			};
		}
	};
}();

function getDh(tableId) {
	Util.ajaxSync(
        Util.getRootPath() + "/w/fileNum/getDahByType",
        {
        	type: tableId
        },
        function(result){
        	
        }, function(ex){
        	console.log("【%s】获取档案规则字段失败。", tableId);
        }
    );
}
//判断是否手动修改过档号
function dhChange() {
	$("input[name='dh']").change(function() {
		flag = 1;
	});
	if(flag == 0) {
		initDhRule();
	}
}
//监听输入框值的改变
function inputListen() {
	$("input").change(function() {
		eachField($(this).attr("name"));
	});
}
//档号生成或修改
function initDhRule() {
	var tableName = $("input[name='tablename']").val();
	//表id
	var tableId = getTableId(tableName);
	//根据规则生成的档号
//	dhRule = getDhRule(tableId);
	//当前档案已有的档号
	dh = $("input[name='dh']").val();
	
	inputListen();
	
}

//遍历与档号相关的字段数组
function eachField(fieldStr) {
	for(var i = 0;i<fieldArr.length;i++) {
		var str = fieldArr[i];
		var index = fieldIdx[i];
		if(fieldStr == str) {
			dhArr[index - 1] = $("input[name='" + str + "']").val();
			setDh();
		}
	}
}
//设置档号
function setDh() {
	dh = "";
	for(var j = 0;j<dhArr.length;j++) {
		dh += dhArr[j];
	}
	$("input[name='dh']").attr("value",dh);
}
//查询档号
function getDhRule(tableId) {
	//根据规则生成的档号
	var dhRule = "";
	Util.ajaxSync(
	        Util.getRootPath() + "/w/fileNum/getRuleEntity",
	        {
	        	type: tableId
	        },
	        function(result){
	            if(result.success){
//	            	dh = result.data;
	            	result = result.datas;
	            	for(var i = 0;i<result.length;i++) {
	            		var str = result[i].value;
	            		//判断字符串是否包含%
	            		if(str.indexOf("%") != -1 ) {
	            			str = str.substring(1,str.length-1);
	            			var date = new Date();
	            			if(str == "year") {
	            				//获取当前年份
	            				dhRule += date.getFullYear();
	            				dhArr.push(date.getFullYear());
	            			}else if(str == "month") {
	            				//获取当前月份
	            				var month = date.getMonth()+1;
	            				if(month < 10) {
	            					dhRule += "0" + month;
	            					dhArr.push("0" + month);
	            				}else {
	            					dhRule += month;
	            					dhArr.push(month);
	            				}
	            			}else if(str == "date") {
	            				//获取当前日期
	            				var date = date.getDate();
	            				if(date < 10) {
	            					dhRule += "0" + date;
	            					dhArr.push("0" + date);
	            				}else {
	            					dhRule += date;
	            					dhArr.push(date);
	            				}
//	            				dhRule += date.getDate();
	            			}else {
	            				dhRule += $("input[name='" + str + "']").val();
	            				fieldArr.push(str);
	            				fieldIdx.push(result[i].num);
	            				dhArr.push($("input[name='" + str + "']").val());
//	            				changeFieldVal(str);
	            			}
	            		}else if(result[i].title == "number") {	//判断是否为流水号
	        				var rnum = result[i].serialNum;
	        				rnum ++;
	        				dhRule += (rnum/Math.pow(10,str)).toFixed(str).substr(2);
//	        				fieldIdx.push(result[i].num);
	        				dhArr.push((rnum/Math.pow(10,str)).toFixed(str).substr(2));
	        				var jnsxhLabel = $("input[name='jnsxh']");
	        				var ajhLabel = $("input[name='ajh']");
//	        				getNum();
	        				if(jnsxhLabel.html() != undefined && jnsxhLabel.attr("value") == "") {
	        					jnsxhLabel.attr("value",(rnum/Math.pow(10,str)).toFixed(str).substr(2));
	        				}else if(jnsxhLabel.html() == undefined && ajhLabel.attr("value") == "") {
	        					$("input[name='ajh']").attr("value",(rnum/Math.pow(10,str)).toFixed(str).substr(2));
	        				}
	        			}else {
	            			dhRule += str;
//	            			fieldIdx.push(result[i].num);
	        				dhArr.push(str);
	            		}
	            	}
	            }
//	            getDh(tableId);
	        }, function(ex){
	        	console.log("【%s】没有配置档号规则对象。", tableId);
	        }
	    );
	return dhRule;
}
//根据表名，获取该表对应id
function getTableId(tableName) {
	var tableId;
	//根据表名获取id，根据id查询该档案的档号
	Util.ajaxSync(
        Util.getRootPath() + "/w/fileNum/getTableNameId",
        {
        	tableName: tableName
        },
        function(result){
            if(result.success){
            	//表id
            	tableId = result.data;
            }
        }, function(ex){
            layer.alert(result.msg, {  skin: 'layui-layer-lan',closeBtn: 0});
        }
    );
	return tableId;
}