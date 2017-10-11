package cn.proem.suw.util;

import java.sql.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON-LIB 补丁，修复Date转换错误，应用其开放接口进行修复
 * @author 大连首闻科技有限公司
 * @version 1.0
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	// 处理数组的值
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理对象的值
	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理Date类型返回的Json数值
	private Object process(Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof Date) {
			return JSONObject.fromObject(new java.util.Date(((Date) value).getTime()));
		} else {
			return value.toString();
		}
	}
}