$(function(){
	getChildMenu(null);
	clickMenu();
	clickLi();
	$("#mainIframe").load(function(event){
		var location = $(this).attr("dagl-location");
		if(location){			
			$(this).contents().find(".dagl-location").html(location);
		}
	});
});
function setMenuHeight() {
	$(".leftMenu").attr("style","margin-top:88px");
}
//点击左侧列表时，header图片消失
function clickMenu() {
	$(".leftMenu").on("click",function() {
		//header图片隐藏
		parent.hidePic();
		//还原左侧菜单高度
		$(".leftMenu").removeAttr("style");
	});
}
//左侧li点击时添加添加选中样式
function clickLi() {
	$(".leftMenu").on("click","li",function(event) {
		$(this).siblings("li").removeClass("act");
		if($(this).find("ul").html() == undefined) {
			$(this).addClass("act");
		}
		$(this).siblings("li").find("li").removeClass("act").removeClass("active");
	});
}
function getChildMenu(code){
	$.ajax({
		 url:Util.getRootPath() + "/w/menu",
		 async:false,  
		 data:{
			 code:code
		 },
		 dataType:'json',
		 success:function(data,textStatus,jqXHR){
			  
			 var menu = data.menu_list[0];
			 var html = '';
			 for(var ix = 0; ix < menu.children.length; ix ++){
				 html = html + menus(menu.children[ix]);
			 }
			 $("ul.sidebar-menu").html(html);
		 },
		 error:function(xhr,textStatus){}
	});
	
			
}

function getChild(menu){
	return menu.children;
}
function isleaf(menu){
	if(menu.children.length){
		return false;
	}else{
		return true;
	}
}

function menus(data){
	if(isleaf(data)){
		//叶子节点
		var html = '';
		html += '<li dagl-location="' + data.name + '" onclick="setLocation(this);">';
		html += '	<a href="'+data.url+'" target="mainIframe">';
		html += '		<i class="fa '+data.icon+'"></i><span> '+data.name+'</span>'; 
		html += '	</a>';
		html +=	'</li>';
		return html;
	}
	else{
//		var html = '';
//		// 获得子节点
//		var childs = getChild(data);
//		for(var i=0;i<childs.length;i++){
//			html = html + menus(childs[i]);
//		}
//		html = "<ul class='treeview-menu'>" + html + '</ul>';
//		html = '<li dagl-location="' + data.name + '" class="treeview">'
//		     + '	<a href="'+data.url+'" target="mainIframe">'
//		     + '		<i class="fa fa-link"></i><span>'+data.name+'</span>'
//		     + '		<span class="pull-right-container">'
//		     + '			<i class="fa fa-angle-left pull-right"></i>'
//		     + '		</span>'
//		     + '	</a>'
//			 + html
//		     + '</li>';
//		 return html;
		 
		 var html = '';
		// 获得子节点
		var childs = getChild(data);
		for(var i=0;i<childs.length;i++){
			html = html + menus(childs[i]);
		}
		html = "<ul class='treeview-menu'>" + html + '</ul>';
		var htmlUl = '<li dagl-location="' + data.name + '" class="treeview">';
		htmlUl += '	<a href="'+data.url+'" target="mainIframe">';
		     if(data.icon == null || data.icon == "") {
		    	 htmlUl += '		<i class="fa fa-link"></i><span>'+data.name+'</span>';
		     }else {
		    	 htmlUl += '		<i class="fa ' + data.icon + '"></i><span>'+data.name+'</span>';
		     }
//			     + '		<i class="fa fa-link"></i><span>'+data.name+'</span>'
		     htmlUl += '		<span class="pull-right-container">';
		     htmlUl += '			<i class="fa fa-angle-left pull-right"></i>';
		     htmlUl += '		</span>';
		     htmlUl += '	</a>';
		     htmlUl += html;
		     htmlUl += '</li>';
		 return htmlUl;
	}
}

function getLocation(li){
	var locations = []
	locations.push(li.attr("dagl-location"));
	li.parents('li').each(function(index, element){
		locations.push($(element).attr("dagl-location"))
	});
	return locations;
}



function setLocation(obj){
	$this = $(obj);
	var locations = getLocation($this);
	var location = '<li class="fa fa-list"></li> ';
	var len = locations.length;
	if(len > 0){
		location = location + locations.pop();
		for(var i=1; i < len ; i++){
			if(i == (len-1)){
				location = location + " <i class='fa fa-angle-right'></li> <b>" +locations.pop() + "</b>";
			}else{
				location = location + " <i class='fa fa-angle-right'></li> " +locations.pop();
			}
		}
	}
	$("#mainIframe").attr("dagl-location", location);
}

