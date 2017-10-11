/**
 * 工具
 */
var Util = function() {
	return {
		/**
		 * 获取项目根路径
		 */
		getRootPath : function() {
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			var localhostPaht = curWwwPath.substring(0, pos);
			var projectName = pathName
					.substring(0, pathName.substr(1).indexOf('/') + 1);
			return (localhostPaht + projectName);
		},
		/**
		 * 页面输入验证【空判断】
		 * 
		 * @param data
		 *            输入值
		 * @returns returnFlag true:验证出错/false:验证无误
		 */
		checkNull : function(data) {
			var returnFlag = false;
			if (null == data || "" == data || data.length == 0) {
				returnFlag = true;
			}
			return returnFlag;
		},
		/**
		 * 从url中获取参数
		 */
		getQueryString : function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) {
				return unescape(r[2]);
			}
			return null;
		},
		/**
		 * 页面输入验证【判断日期是否在合理区间内】
		 * 
		 * @param firstData
		 *            开始日期
		 * @param secondData
		 *            结束日期
		 * @param targetData
		 *            比对日期
		 * @param action
		 *            比对方式【lt:< gt:> lteq:<= gteq:>= between】
		 * @returns returnFlag true:验证出错/false:验证无误
		 */
		checkDateInSection : function(firstData, secondData, targetData, action) {
			var firstDate = parseInt(Util.dateFormat(firstData,"yyyyMMdd"));
			var secondDate = null;
			if ("between" == action) {
				secondDate = parseInt(Util.dateFormat(secondData,"yyyyMMdd"));
			}
			var targetDate = parseInt(Util.dateFormat(targetData,"yyyyMMdd"));
			if ("lt" == action) {
				if (firstDate < targetDate) {
					return false;
				}
			} else if ("gt" == action) {
				if (firstDate > targetDate) {
					return false;
				}
			} else if ("lteq" == action) {
				if (firstDate < targetDate || firstDate == targetDate) {
					return false;
				}
			} else if ("gteq" == action) {
				if (firstDate > targetDate || firstDate == targetDate) {
					return false;
				}
			} else if ("between" == action) {
				if (firstDate < targetDate && targetDate < secondDate) {
					return false;
				}
			} else {
				return true;
			}
			return true;
		},
		/**
		 * 跨行
		 * @param index 第几个TD
		 * @param obj TR对象
		 * @param 开始行数
		 * @param 长度
		 */
		rowspanFn : function(index,obj,start,length) {
			var tempTD = null,count = 0;
			for(var i=start;i<length;i++) {
				if(tempTD == null) {
					tempTD = $(obj[i]).children("td:not([rowspan]):eq("+index+")");
				}
				if($(obj[i]).children("td:not([rowspan]):eq("+index+")").text() == tempTD.text()) {
					if(count > 0) {
						$(obj[i]).children("td:not([rowspan]):eq("+index+")").remove();
					}
					count ++;
					if(i < (length - 1)) {
						continue;
					}
				}
				tempTD.attr("rowspan",count);
				tempTD.css("vertical-align","middle");
				count = 1;
				tempTD = $(obj[i]).children("td:not([rowspan]):eq("+index+")");
			}
		},
		/**
		 * 日期格式转换
		 * 
		 * @param data 日期格式数据
		 * @returns 字符类型日期
		 */
		dateFormat : function(data,format){
			if(data) {
				var newDate = new Date(data);
				return newDate.pattern(format);
			}
			return "";
		},
		/**
		 * 添加一行
		 */
		AddTr : function($this) {
			var tableId = $this.parent().parent().parent().parent().attr("id");
			var trClass = $this.parent().parent().attr("class");
			var newTr = $this.parent().parent().clone();
			// newTr.find("input[type='hidden']").attr("value",Math.uuid());

			var row = $("." + trClass + " td").length;
			for (var i = 0; i < row; i++) {
				if (newTr.find("td:eq(" + i + ")").hasClass("datepicker")) {
					newTr.find("td:eq(" + i + ") input").removeClass().addClass("time")
							.removeAttr("id").prop("value", "");
				} else {
					newTr.find("td:eq(" + i + ") input").prop("value", "");
				}
			}
			newTr.find("td:eq(0) i").removeClass("clone-add").removeClass(
					"glyphicon-plus").addClass("glyphicon-minus clone-remove");
			$("#" + tableId + " tbody tr." + trClass + ":last").after(newTr);
			newTr.find("td:eq(0) i").off("click");
			newTr.find("td:eq(0) i").bind("click", function() {
				$(this).parent().parent().remove();
			});
		},
		createAdmissionPrint : function(strURL) {
			LODOP=getLodop();  
			LODOP.PRINT_INIT("打印准考证");
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
			LODOP.SET_PRINT_MODE("PRINT_DUPLEX",1);
			LODOP.ADD_PRINT_URL(0,0,"100%","100%",strURL);
			
			LODOP.ADD_PRINT_IMAGE(85,264,121,121,"<img src='"+Util.getRootPath()+"/n/sealPict' />");
			LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",4);
			LODOP.ADD_PRINT_IMAGE(145,564,121,121,"<img src='"+Util.getRootPath()+"/n/sealPict' />");
			LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",4);
		},
		createOneFormPage : function(strURL) {
			LODOP=getLodop();  
			LODOP.PRINT_INITA(0,0,522,333,"打印测试");
//			LODOP.SET_PRINT_STYLE("FontSize",18);
//			LODOP.SET_PRINT_STYLE("Bold",1);
			LODOP.SET_PRINT_PAGESIZE(1, 2250, 2100, "CreateCustomPage");
			//LODOP.ADD_PRINT_TEXT(50,231,260,39,"");
			LODOP.ADD_PRINT_URL(0,0,"100%","100%",strURL);
			//LODOP.ADD_PRINT_HTM(88,200,350,600,document.getElementById(objId).innerHTML);
		},
		checkIsInstall : function() {	 
			try{ 
			     var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
				if ((LODOP!=null)&&(typeof(LODOP.VERSION)!="undefined")){
					return true;
				}
			}catch(err){
	 		}
			return false;
		},
		/**
		 * 根据身份证号自动获取年龄
		 */
		getAgeFromIdCardNo : function(idCardNo){
			//获取年龄 
			var myDate = new Date(); 
			var month = myDate.getMonth() + 1; 
			var day = myDate.getDate(); 
			var age = myDate.getFullYear() - idCardNo.substring(6, 10) - 1; 
			if (idCardNo.substring(10, 12) < month 
					|| (idCardNo.substring(10, 12) == month && idCardNo.substring(12, 14) <= day)) { 
				age++; 
			} 
			
			return age;
		},
		/**
		 * 判断是否是时间格式
		 * @param str
		 * @returns {Boolean}
		 */
		isTime : function(str) {
			var a = str.match(/^\s*([01][0-9]|2[0-3])\:[0-5][0-9](\:[0-5][0-9]){0,1}\s*$/);
			    if (a == null) {
				return false;
			}
		    if (a[1]>24 || a[3]>60){
		    	return false;
		    }
		    return true;
		},
		/**
		 * 是否为正整数
		 */
		isPositiveNum : function (s){
			var re = /^[0-9]*[1-9][0-9]*$/ ; 
			return re.test(s);
		},
		/*封装ajax*/
		ajax : function(url,data,okfun,errorfun) {
			$.ajax({
				type:'post',
				url : url,
				data: data,
				dataType:"json",
				success:function(data, textStatus){
					if(okfun) {
						okfun(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if(errorfun) {
						errorfun(XMLHttpRequest, textStatus, errorThrown);
					}
				},
				complete : function (XMLHttpRequest, textStatus) {
					
				}
			});
		},
		ajaxSync : function(url,data,okfun,errorfun) {
			$.ajax({
				type:'post',
				url : url,
				data: data,
				dataType:"json",
				async : false,
				success:function(data, textStatus){
					if(okfun) {
						okfun(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if(errorfun) {
						errorfun(XMLHttpRequest, textStatus, errorThrown);
					}
				},
				complete : function (XMLHttpRequest, textStatus) {
					
				}
			});
		},
		ajaxJson : function(url,data,okfun,errorfun) {
			$.ajax({
				type:'post',
				url : url,
				data: JSON.stringify(data),
				dataType:"json",
				contentType : "application/json",
				success:function(data, textStatus){
					if(okfun) {
						okfun(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if(errorfun) {
						errorfun(XMLHttpRequest, textStatus, errorThrown);
					}
				},
				complete : function (XMLHttpRequest, textStatus) {
					
				}
			});
		},
		ajaxJsonSync : function(url,data,okfun,errorfun) {
			$.ajax({
				type:'post',
				url : url,
				data:JSON.stringify(data),
				dataType:"json",
				contentType : "application/json",
				async : false,
				success:function(data, textStatus){
					if(okfun) {
						okfun(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if(errorfun) {
						errorfun(XMLHttpRequest, textStatus, errorThrown);
					}
				},
				complete : function (XMLHttpRequest, textStatus) {
					
				}
			});
		},
		//*设置分页信息*//
		bindPage: function (page, $handler) {
			var showNum = 2;
			var pageNum = page.pageNum;
			var pageSize = page.pageSize;
			if (page == null || page.totalCount == 0) {
				$($handler).hide();
			} else {
				var pageHtml = '<ul class="pagination">';
				if (page.totalPage > 1) {//大于一页的情况
					//上一页, 第一页
					if (pageNum == 1) {
						pageHtml += '<li class="disabled"><a href="javascript:;"><i class="fa fa-angle-double-left"></i></a></li>';
						pageHtml += '<li class="disabled"><a href="javascript:;"><i class="fa fa-angle-left"></i></a></li>';
					} else {
						pageHtml += '<li><a href="javascript:;" pageNum="1" onclick="initPage(1, '+pageSize+');"><i class="fa fa-angle-double-left"></i></a></li>';
						pageHtml += '<li><a href="javascript:;" pageNum="1" onclick="initPage('+(pageNum-1)+', '+pageSize+');"><i class="fa fa-angle-left"></i></a></li>';
					}
					
					//中间页
					for (var i = 0; i < page.totalPage; i++) {
						pageHtml += '<li';
						if (pageNum == (i+1)) {
							pageHtml += ' class="active"';
						}
						pageHtml += '><a href="javascript:;" pageNum="'+(i+1)+'" onclick="initPage('+(i+1)+', '+pageSize+');">'+(i+1)+'</a></li>';
					}
					
					//下一页, 最后一页
					if (pageNum == page.totalPage) {
						pageHtml += '<li class="disabled"><a href="javascript:;"><i class="fa fa-angle-right"></i></a></li>';
						pageHtml += '<li class="disabled"><a href="javascript:;"><i class="fa fa-angle-double-right"></i></a></li>';
					} else {
						pageHtml += '<li><a href="javascript:;" pageNum="'+(pageNum+1)+'" onclick="initPage('+(pageNum+1)+', '+pageSize+');"><i class="fa fa-angle-right"></i></a></li>';
						pageHtml += '<li><a href="javascript:;" pageNum="'+page.totalPage+'" onclick="initPage('+(page.totalPage)+', '+pageSize+');"><i class="fa fa-angle-double-right"></i></a></li>';
					}
				}
				if (page.totalPage == 0 && page.totalCount > 0) {//只有一页的情况
					pageHtml += '<li class="active"><a href="javascript:;" pageNum="'+1+'">1</a></li>';
				}
				
				pageHtml += '</ul>';
				$($handler).children().remove();
				$($handler).append(pageHtml);
				
				//滚动到顶端
				$("html,body").animate({scrollTop:0}, 0);
			}
		},
		//转义
		convert : function(str){
			var result = "";
			if (str.length > 0) {
				var words = str.split("");
				for (var i = 0; i < words.length; i++) {
					var s = words[i];
					if (s == '"') {
						s = '&quot;'
					} else if (s == '<') {
						s = '&lt;'
					} else if (s == '<=') {
						s = '&le;'
					} else if (s == '>') {
						s = '&gt;'
					} else if (s == '>') {
						s = '&gt;'
					} else if (s == '>=') {
						s = '&ge;'
					}
					result += s;
				}
			}
			return result;
		},
		getUserHeadPath: function (userId){
			var headPath = null;
			Util.ajaxSync(
				Util.getRootPath() + "/w/common/getUserHeadPath",
				{
					id: userId
				},
				function(result) {
					if (result.success && result.data != null){
						headPath = result.data;
					}
				},function() {
					layer.alert('请求失败！');
				}
			);
			return headPath;
		},
		getIntercept : function () {
			var intercept = false;
			Util.ajaxSync(
					Util.getRootPath() + "/w/common/getIntercept",
					{},
					function (result) {
						if (result == "true") {
							intercept = true;
						}
						
					}, function(ex){}
				);
			return intercept;
		},
		setIntercept: function(intercept) {
			Util.ajaxSync(
				Util.getRootPath() + "/w/common/setIntercept",
				{intercept: intercept.toString()},
				function (result) {
					
				}, function(ex){}
			);
		},
		//判断是否为视频文件(asx，asf，mpg，wmv，3gp，mp4，mov，avi)
		isVedio: function(wjlx){
			
			if(wjlx.toLowerCase() == "asx" || wjlx.toLowerCase() == "asf" || wjlx.toLowerCase() == "mpg" 
				|| wjlx.toLowerCase() == "wmv" || wjlx.toLowerCase() == "3gp" 
				|| wjlx.toLowerCase() == "mp4" || wjlx.toLowerCase() == "mov" || wjlx.toLowerCase() == "avi" || wjlx.toLowerCase() == "flv"){
				
				return true;
			}else{
				return false;
			}
		},
		//判断是否为音频文件
		isAudio: function(wjlx){
			if(wjlx.toLowerCase() == "wav" || wjlx.toLowerCase() == "wma" || wjlx.toLowerCase() == "ogg" || wjlx.toLowerCase() == "cd" 
				|| wjlx.toLowerCase() == "asf" 
				|| wjlx.toLowerCase() == "mp3" || wjlx.toLowerCase() == "real" || wjlx.toLowerCase() == "vqf" || wjlx.toLowerCase() == "rm" || wjlx.toLowerCase() == "m4a"){
				
				return true;
			}else{
				return false;
			}
			
		},
		//判断是否为图片文件
		isPIC: function(wjlx){
			if(wjlx.toLowerCase() == "jpg" || wjlx.toLowerCase() == "png" || wjlx.toLowerCase() == "bmp" 
				|| wjlx.toLowerCase() == "jpeg" || wjlx.toLowerCase() == "gif" || wjlx.toLowerCase() == "tif" ){
				
				return true;
			}else{
				return false;
			}
			
		},
		bytesToSize:function(bytes) {  
		       if (bytes === 0) return '0 B';  
		  
		        var k = 1024;  
		  
		        sizes = ['B','KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];  
		  
		        i = Math.floor(Math.log(bytes) / Math.log(k));  
		  
		    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];   
		       //toPrecision(3) 后面保留一位小数，如1.0GB                                                                                                                  //return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];  
		}  
	};
}();