package cn.proem.dagl.web.tools.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import cn.proem.dagl.core.model.Condition;
import cn.proem.dagl.core.model.Sort;

/**
 * 
 * @author Administrator
 *
 */
public class QueryUtils {

	/**
	 * 获取快速查询的条件SQL
	 * @param params 快速查询参数
	 * @param arguments 参数值列表
	 * @return 条件SQL
	 * @throws Exception
	 */
	public static String getFastQuerySql(Map<String, Object> params, List<Object> arguments) throws Exception {
//		如果传递的条件参数为空则返回空字符串
		if(params==null){
			return "";
		}
//		定义条件SQL
		StringBuffer conditionSql = new StringBuffer();
//		遍历参数，拼接SQL
		for(String key : params.keySet()){
			if("".equals(MapUtils.getString(params, key, "").trim()))
				continue;
			if(key.indexOf("_")!=-1){
				String field = key.substring(key.indexOf("_")+1, key.length());
//				equal
				if(key.startsWith("eq_")){
					conditionSql.append(" and ").append(field).append(" = ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
//				not equal
				if(key.startsWith("ne_")){
					conditionSql.append(" and ").append(field).append(" != ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
//				like
				if(key.startsWith("lk_")){
					conditionSql.append(" and ").append(field).append(" like ? ");
					arguments.add("%"+MapUtils.getString(params, key)+"%");
					continue;
				}
//				right like
				if(key.startsWith("rl_")){
					conditionSql.append(" and ").append(field).append(" like ? ");
					arguments.add("%"+MapUtils.getString(params, key));
					continue;
				}
//				left like
				if(key.startsWith("ll_")){
					conditionSql.append(" and ").append(field).append(" like ? ");
					arguments.add(MapUtils.getString(params, key)+"%");
					continue;
				}
//				is null
				if(key.startsWith("in_")){
					conditionSql.append(" and ").append(field).append(" is null ");
					continue;
				}
//				is not null
				if(key.startsWith("inn_")){
					conditionSql.append(" and ").append(field).append(" is not null ");
					continue;
				}
//				great then
				if(key.startsWith("gt_")){
					conditionSql.append(" and ").append(field).append(" > ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
//				great then and equal
				if(key.startsWith("ge_")){
					conditionSql.append(" and ").append(field).append(" >= ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
//				less then
				if(key.startsWith("lt_")){
					conditionSql.append(" and ").append(field).append(" < ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
//				less then and equal
				if(key.startsWith("le_")){
					conditionSql.append(" and ").append(field).append(" <= ? ");
					arguments.add(MapUtils.getString(params, key));
					continue;
				}
			}
		}
//		返回条件SQL
		return conditionSql.toString();
	}
	
	/**
	 * 获取高级查询的条件SQL
	 * @param advanceQueryConditions 查询条件列表
	 * @param arguments 参数值列表
	 * @return 条件SQL
	 * @throws Exception
	 */
	public static String getAdvanceQueryConditionSql(List<Condition> advanceQueryConditions, List<Object> arguments){
//		定义条件SQL
		StringBuffer conditionSql = new StringBuffer();
		if(advanceQueryConditions!=null&&advanceQueryConditions.size()>0){
//			加入前置的and参数
			conditionSql.append(" and ");
			for(Condition advanceQueryCondition : advanceQueryConditions){
//				获取参数：leftParentheses-左括号 field-字段名 condition-条件 value-值 rightParentheses-右括号 logic-逻辑符号
				String leftParentheses = advanceQueryCondition.getLeftParentheses();
				String field = advanceQueryCondition.getField();
				String condition = advanceQueryCondition.getCondition();
				String value = advanceQueryCondition.getValue();
				String rightParentheses = advanceQueryCondition.getRightParentheses();
				String logic = advanceQueryCondition.getLogic();
//				拼接SQL
				getSingleAdvanceQueryConditionSql(conditionSql, leftParentheses, field, condition, value, rightParentheses, logic, arguments);
			}
		}
//		返回条件SQL
		return conditionSql.toString();
	}
	
	/**
     * 获取高级查询的条件SQL
     * @param advanceQueryConditions 查询条件列表
     * @param arguments 参数值列表
     * @return 条件SQL
     * @throws Exception
     */
    public static String getAdvanceQueryConditionSql(List<Condition> advanceQueryConditions){
//      定义条件SQL
        StringBuffer conditionSql = new StringBuffer();
        if(advanceQueryConditions!=null&&advanceQueryConditions.size()>0){
//          加入前置的and参数
            conditionSql.append(" and (");
            for(Condition advanceQueryCondition : advanceQueryConditions){
//              获取参数：leftParentheses-左括号 field-字段名 condition-条件 value-值 rightParentheses-右括号 logic-逻辑符号
                String leftParentheses = advanceQueryCondition.getLeftParentheses();
                String field = advanceQueryCondition.getField();
                String condition = advanceQueryCondition.getCondition();
                String value = advanceQueryCondition.getValue();
                String rightParentheses = advanceQueryCondition.getRightParentheses();
                String logic = advanceQueryCondition.getLogic();
//              拼接SQL
                getSingleAdvanceQueryConditionSql(conditionSql, leftParentheses, field, condition, value, rightParentheses, logic);
            }
            conditionSql.append(" ) ");
        }
//      返回条件SQL
        return conditionSql.toString();
    }
	
	/**
     * 拼接单条的高级查询SQL
     * @param conditionSql 条件SQL
     * @param leftParentheses 左括号 0-( 1-(( 2-((( 3-(((( 4-(((((
     * @param field 字段信息
     * @param condition 条件 0-= 1-!= 2-like 3-start with 4-end with 5-> 6->= 7-< 8-<= 9-is null 10-is not null
     * @param value 值
     * @param rightParentheses 右括号 0-) 1-)) 2-))) 3-)))) 4-)))))
     * @param logic 逻辑符号 0-and 1-or
     * @param arguments 参数值列表
     */
    private static void getSingleAdvanceQueryConditionSql(StringBuffer conditionSql, String leftParentheses, 
            String field, String condition, String value, String rightParentheses, String logic){
//      获取左括号内容、右括号内容、逻辑符号内容
        logic = getConditionLogicContent(logic);
//      根据条件类型拼接SQL
        conditionSql.append(" ").append(leftParentheses).append(" ");
        if("0".equals(condition)){
            conditionSql.append(" ").append(field).append(" = '").append(value).append("' ");
        }else if("1".equals(condition)){
            conditionSql.append(" ").append(field).append(" != '").append(value).append("' ");
        }else if("2".equals(condition)){
            conditionSql.append(" ").append(field).append(" like '").append("%"+value+"%").append("' ");
        }else if("3".equals(condition)){
            conditionSql.append(" ").append(field).append(" like '").append(value+"%").append("' ");
        }else if("4".equals(condition)){
            conditionSql.append(" ").append(field).append(" like '").append("%"+value).append("' ");
        }else if("5".equals(condition)){
            conditionSql.append(" ").append(field).append(" > '").append(value).append("' ");
        }else if("6".equals(condition)){
            conditionSql.append(" ").append(field).append(" >= '").append(value).append("' ");
        }else if("7".equals(condition)){
            conditionSql.append(" ").append(field).append(" < '").append(value).append("' ");
        }else if("8".equals(condition)){
            conditionSql.append(" ").append(field).append(" <= '").append(value).append("' ");
        }else if("9".equals(condition)){
            conditionSql.append(" ").append(field).append(" is null ");
        }else if("10".equals(condition)){
            conditionSql.append(" ").append(field).append(" is not null ");
        }
        conditionSql.append(" ").append(rightParentheses).append(" ");
        conditionSql.append(" ").append(logic).append(" ");
    }
	
	/**
	 * 拼接单条的高级查询SQL
	 * @param conditionSql 条件SQL
	 * @param leftParentheses 左括号 0-( 1-(( 2-((( 3-(((( 4-(((((
	 * @param field 字段信息
	 * @param condition 条件 0-= 1-!= 2-like 3-start with 4-end with 5-> 6->= 7-< 8-<= 9-is null 10-is not null
	 * @param value 值
	 * @param rightParentheses 右括号 0-) 1-)) 2-))) 3-)))) 4-)))))
	 * @param logic 逻辑符号 0-and 1-or
	 * @param arguments 参数值列表
	 */
	private static void getSingleAdvanceQueryConditionSql(StringBuffer conditionSql, String leftParentheses, 
			String field, String condition, String value, String rightParentheses, String logic, List<Object> arguments){
//		获取左括号内容、右括号内容、逻辑符号内容
		logic = getConditionLogicContent(logic);
//		根据条件类型拼接SQL
		conditionSql.append(" ").append(leftParentheses).append(" ");
		if("0".equals(condition)){
			conditionSql.append(" ").append(field).append(" = ? ");
			arguments.add(value);
		}else if("1".equals(condition)){
			conditionSql.append(" ").append(field).append(" != ? ");
			arguments.add(value);
		}else if("2".equals(condition)){
			conditionSql.append(" ").append(field).append(" like ? ");
			arguments.add("%"+value+"%");
		}else if("3".equals(condition)){
			conditionSql.append(" ").append(field).append(" like ? ");
			arguments.add(value+"%");
		}else if("4".equals(condition)){
			conditionSql.append(" ").append(field).append(" like ? ");
			arguments.add("%"+value);
		}else if("5".equals(condition)){
			conditionSql.append(" ").append(field).append(" > ? ");
			arguments.add(value);
		}else if("6".equals(condition)){
			conditionSql.append(" ").append(field).append(" >= ? ");
			arguments.add(value);
		}else if("7".equals(condition)){
			conditionSql.append(" ").append(field).append(" < ? ");
			arguments.add(value);
		}else if("8".equals(condition)){
			conditionSql.append(" ").append(field).append(" <= ? ");
			arguments.add(value);
		}else if("9".equals(condition)){
			conditionSql.append(" ").append(field).append(" is null ");
		}else if("10".equals(condition)){
			conditionSql.append(" ").append(field).append(" is not null ");
		}
		conditionSql.append(" ").append(rightParentheses).append(" ");
		conditionSql.append(" ").append(logic).append(" ");
	}
	
	/**
	 * 获取逻辑内容
	 * @param logic 逻辑码值
	 * @return 逻辑内容
	 */
	private static String getConditionLogicContent(String logic){
		String content = "";
		if("0".equals(logic)){
			content = "and";
		}else if("1".equals(logic)){
			content = "or";
		}
		return content;
	}
	
	/**
	 * 获取高级查询的排序SQL
	 * @param advanceQuerySorts 排序列表
	 * @return 条件SQL
	 * @throws Exception
	 */
	public static String getAdvanceQuerySortSql(List<Sort> advanceQuerySorts){
//		定义条件SQL
		StringBuffer sortSql = new StringBuffer();
		if(advanceQuerySorts!=null&&advanceQuerySorts.size()>0){
//			加入前置的and参数
			sortSql.append(" order by ");
			for(Sort advanceQuerySort : advanceQuerySorts){
//				获取参数：field-字段名 logic-排序逻辑
				String field = advanceQuerySort.getField();
				String logic = advanceQuerySort.getLogic();
//				拼接SQL
				getSingleAdvanceQuerySortSql(sortSql, field, logic);
			}
			sortSql.delete(sortSql.lastIndexOf(","), sortSql.length());
		}
//		返回条件SQL
		return sortSql.toString();
	}
	
	/**
	 * 拼接单条的高级排序SQL
	 * @param sortSql 排序SQL
	 * @param field 字段信息
	 * @param logic 逻辑符号 0-asc 1-desc
	 */
	private static void getSingleAdvanceQuerySortSql(StringBuffer sortSql, String field, String logic){
//		获取左括号内容、右括号内容、逻辑符号内容
		logic = getSortLogicContent(logic);
//		根据条件类型拼接SQL
		sortSql.append(" ").append(field).append(" ").append(logic).append(",  ");
	}
	
	/**
	 * 获取排序逻辑内容
	 * @param logic 逻辑码值
	 * @return 逻辑内容
	 */
	private static String getSortLogicContent(String logic){
		String content = "";
		if("0".equals(logic)){
			content = "asc";
		}else if("1".equals(logic)){
			content = "desc";
		}
		return content;
	}
	
}