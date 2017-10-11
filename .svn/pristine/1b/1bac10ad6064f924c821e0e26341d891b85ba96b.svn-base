/*按钮组插件*/
(function($) {

	//默认值
	var defaults = {
		name: "未定义名称的按钮",
		style: "btn-default",
		class: "",
		id: "",
		hide: false,
		icon: "",
		callback: {
			onClick: function(){}
		}
	};
	
	//扩展
	$.fn.btnBar = function(options){
		//返回对象
		return this.each(function(){
			bindBtn(options, this);
		});
	};
	
	//绑定按钮
	var bindBtn = function(options, obj) {
		var $btnbar = $("#"+$(obj).attr("id"));
		var html = '';
		for (var i = 0; i < options.length; i++) {
			var btn = options[i];
            if (btn.name != null) {
                html += '<a title="'+btn.name+'" ';
            } else {
                html += '<a title="'+defaults.name+'" ';
            }
			if (btn.style != null) {
				html += 'class="btn '+btn.style+' btn-sm';
			} else {
                html += 'class="btn '+defaults.style+' btn-sm';
			}
			if (btn.class != null) {
                html += '  '+btn.class+' ';
			}
            html += '" ';
			if (btn.id != null) {
				html += ' id="'+btn.id+'" ';
			}
            if (btn.hide) {
                html += ' style="display: none;" ';
            }

			html += '>';
            if (btn.icon != null) {
                html += ' '+btn.icon+' ';
            }
			if (btn.name != null) {
				html += ' '+btn.name+' ';
			}
			html += ' </a> ';
            if (btn.callback != null && btn.callback.onClick != null) {
                var onclick = btn.callback.onClick;
                if (btn.class != null) {
                	$btnbar.on("click", "."+btn.class, onclick);
				} else if (btn.id != null) {
					$btnbar.on("click", "#"+btn.id, onclick);
				}
			}
		}
		$btnbar.append(html);
	}
	
})(jQuery);


























