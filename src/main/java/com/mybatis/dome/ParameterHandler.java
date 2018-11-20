package com.mybatis.dome;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class ParameterHandler {
	
	public String parameterExecutor(String className,String sql,String name,Object... parameter) throws ClassNotFoundException {
		//获取全类名
		Class<?> clazz = Class.forName(className);
    	Method[] methods = clazz.getMethods();
    	Map<String, Object> map = new HashMap<>();
    	for (Method method : methods) {
    		if(!method.getName().equals(name)) { continue; }
    		Parameter[] parameters= method.getParameters();
    		int length = parameters.length;
    		if(length == 0) { break; }
    		if(length == 1) {
    			Param param = parameters[0].getAnnotation(Param.class);
    			if(null != param) {
    				map.put(param.value(), parameter[0]);
    				break;
    			}
    			map.put(parameters[0].getName(), parameter[0]);
    			break;
    		}
    		for (int i = 0; i < parameters.length; i++) {
    			Param param = parameters[i].getAnnotation(Param.class);
    			if(null != param) {
    				map.put(param.value(), parameter[i]);
    			}
			}
		}
		return parameterBinding(sql,map);
	}
	/**
	 * 参数绑定
	 * @return
	 */
	@SuppressWarnings("unused")
	private String parameterBinding(String sql,Map<String,Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) { 
			  String key = "#{"+entry.getKey()+"}";
			  String value = null == entry.getValue() ? null : "'"+String.valueOf(entry.getValue())+"'";
			  sql = sql.replace(key,value);
		}
		return sql;
	}
}
