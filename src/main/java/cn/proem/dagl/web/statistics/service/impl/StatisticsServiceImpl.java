package cn.proem.dagl.web.statistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.statistics.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private GeneralDao generalDao;
	/**
	 * 用来展示图片SQL
	 */
	@Override
	public List<Map<String, String>> findParams(String tablename,String cols) {
		//定义sql
		StringBuilder sql=new StringBuilder();
		List<Map<String, String>> listMap=new ArrayList<Map<String, String>>(0);
		sql.append("select nvl(").append(cols);
		sql.append(",'暂无数据') zd ,count(").append(cols).append(") colCount");
		sql.append(" from ").append(tablename);
		sql.append(" where 1=1 AND ISARCHIVE = '3' ");
		sql.append(" group by ").append(cols);
		List<Map<String, Object>> cnt = generalDao.getObjectList(sql.toString(),0, 100);
//		//获取字段的基本信息
//		List<DictionaryValue> dv=dms.getDicValueList(tablename);
		for (int i = 0; i < cnt.size(); i++) {
			Map<String,Object> staMap=cnt.get(i);
			Map<String,String> mapval=new HashMap<String,String>(0);
			//保存字段名
			String zd="";
			//将Map里的key 循环
			for (String keys : staMap.keySet()) {
				//判断是否是zd
				if("ZD".equals(keys)){
					zd=(String) staMap.get(keys);
				}else{
					//添加到map里
					mapval.put(zd, staMap.get(keys).toString());
				}
			}
			listMap.add(mapval);
		}
		return listMap;
	}

}
