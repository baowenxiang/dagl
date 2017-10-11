package cn.proem.suw.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.collections.MapUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.proem.dagl.core.model.Column;
import cn.proem.dagl.core.model.Pager;



/**
 * 导出工具类
 * @author 大连首闻科技有限公司
 * @since 2014-8-22 16:25:30
 */
public class DtGridUtils {
	
	/**
	 * DTGrid的查询方法
	 * @param sql 查询SQL
	 * @param pager 传递的Pager参数对象
	 * @return 包含查询结果集的Pager
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void queryForDTGrid(JdbcTemplate jt, String sql, Pager pager, HttpServletRequest request, HttpServletResponse response, Object...args) throws Exception {
//		定义参数
		List<Object> arguments = new ArrayList<Object>();
		for(int i=0; i<args.length; i++){
			arguments.add(args[i]);
		}
//		获取快速查询条件SQL、高级查询条件SQL、排序SQL
		String fastQuerySql = QueryUtils.getFastQuerySql(pager.getFastQueryParameters(), arguments);
		String advanceQueryConditionSql = QueryUtils.getAdvanceQueryConditionSql(pager.getAdvanceQueryConditions(), arguments);
		String advanceQuerySortSql = QueryUtils.getAdvanceQuerySortSql(pager.getAdvanceQuerySorts());
		try{
//			处理导出
			if(pager.getIsExport()){
//				如果是全部导出数据
				if(pager.getExportAllData()){
//					查询结果集放到信息中
					StringBuffer resultSql = new StringBuffer();
					resultSql.append("select * from (").append(sql).append(") t where 1=1 ").append(fastQuerySql).append(advanceQueryConditionSql).append(advanceQuerySortSql);
					pager.setExportDatas(jt.queryForList(resultSql.toString(), arguments.toArray()));
				}
				ExportUtils.export(request, response, pager);
				return;
			}
//			映射为int型
			int pageSize = pager.getPageSize();
			int startRecord = pager.getStartRecord();
			int recordCount = pager.getRecordCount();
			int pageCount = pager.getPageCount();
//			获取总记录条数、总页数可能没有
			StringBuffer countSql = new StringBuffer();
			countSql.append("select count(*) from (").append(sql).append(") t where 1=1 ").append(fastQuerySql).append(advanceQueryConditionSql);
			recordCount = jt.queryForInt(countSql.toString(), arguments.toArray());
			pager.setRecordCount(recordCount);
			pageCount = recordCount/pageSize+(recordCount%pageSize>0?1:0);
			pager.setPageCount(pageCount);
//			查询结果集放到信息中
			StringBuffer resultSql = new StringBuffer();
			resultSql.append("select * from (").append(sql).append(") t where 1=1 ").append(fastQuerySql).append(advanceQueryConditionSql).append(advanceQuerySortSql).append(" limit ").append(startRecord).append(", ").append(pageSize);
			List<Map<String, Object>> dataList = jt.queryForList(resultSql.toString(), arguments.toArray());
			pager.setExhibitDatas(dataList);
//			设置查询成功
			pager.setIsSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
//			设置查询失败
			pager.setIsSuccess(false);
		}
//		json-lib 日期补丁
		JsonValueProcessor jsonProcessor = new DateJsonValueProcessor();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, jsonProcessor);
//		转换为JSON数据格式
		JSONObject pagerJSON = JSONObject.fromObject(pager, jsonConfig);
//		回写数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(pagerJSON.toString());
	}
	
	/**
	 * 格式化日期
	 * @param column
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String formatContent(Column column, String content){
		try{
//			处理码表
			if(column.getCodeTable()!=null){
				if(column.getCodeTable().containsKey(content)){
					return MapUtils.getString(column.getCodeTable(), content);
				}
			}
//			处理日期、数字的默认情况
			if("date".equalsIgnoreCase(column.getType())&&column.getFormat()!=null&&!"".equals(column.getFormat())){
				if(column.getOtype()!=null&&!"".equals(column.getOtype())){
					if("time_stamp_s".equals(column.getOtype())){
						SimpleDateFormat sdf = new SimpleDateFormat(column.getFormat());
						Date date = new Date(Integer.parseInt(content)*1000);
						return sdf.format(date);
					}else if("time_stamp_ms".equals(column.getOtype())){
						SimpleDateFormat sdf = new SimpleDateFormat(column.getFormat());
						Date date = new Date(Integer.parseInt(content));
						return sdf.format(date);
					}else if("string".equals(column.getOtype())){
						if(column.getOformat()!=null&&!"".equals(column.getOformat())){
							SimpleDateFormat osdf = new SimpleDateFormat(column.getOformat());
							SimpleDateFormat sdf = new SimpleDateFormat(column.getFormat());
							Date date = osdf.parse(content);
							return sdf.format(date);
						}
					}
				}
			}else if("number".equalsIgnoreCase(column.getType())&&!"".equals(column.getFormat())){
				DecimalFormat df = new DecimalFormat(column.getFormat());
				content = df.format(Double.parseDouble(content));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}

}