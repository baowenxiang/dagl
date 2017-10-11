
	var tu=function(){
			/*
				element 元素节点
				titleName 图表名称
				dataJSON  需要传入数组与字段名顺序一一对应 ex:[{字段名:对应的值},{字段名:对应的值}]
			 */
			var bintu=function(element,titleName,dataJSON){
				//清空元素内容
				$(element).empty();
				//清空饼图的内容
				bintu_temp.title.text='';
				bintu_temp.series[0].data.length=0;
				bintu_temp.legend.data.length=0;
				//给饼图设置值
				bintu_temp.title.text=titleName;
				var datas=[];
				for (var i = 0; i < dataJSON.length; i++) {
					$.each(dataJSON[i],function(ind,o){
						bintu_temp.legend.data.push(ind);
						var obj=
						{
							value:o, name:ind
						}
						bintu_temp.series[0].data.push(obj);
					})
				}
				var myCharts = echarts.init(element);
				myCharts.setOption(bintu_temp);	
			}
		return{
			bintu:function(element,titleName,dataJSON){
				bintu(element,titleName,dataJSON);
			}
		}

	}();
	
