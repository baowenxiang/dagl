;(function(){
	this.bintu=function(){
		var opt = {
			    title : {
			        text: '某站点用户访问来源',
			        x:'center'
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        /*data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']*/
			        data:[]//字段名
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                /*{value:数量, name="字段名"}
			                {value:335, name:'直接访问'},
			                {value:310, name:'邮件营销'},
			                {value:234, name:'联盟广告'},
			                {value:135, name:'视频广告'},
			                {value:1548, name:'搜索引擎'}*/
			            ]
			        }
			    ]
			};
		return{
			initBT:function(){
				opt;
			}
		}
	}();
	bintu.initBT();
})(window);
