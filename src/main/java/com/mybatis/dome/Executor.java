package com.mybatis.dome;

import java.util.Map;
/**
 * 
 * @author XiaoTian
 *
 */
public class Executor {
	
	private static Executor executor;
	
	private volatile static Map<String,Map<String,AnalysisMapper>> saveMap;
	
	private Executor(Map<String,Map<String,AnalysisMapper>> saveMap) {
		if(this.saveMap == null) {
			this.saveMap = saveMap;
		}
	}
	//判断是否为空 空进行扫描包获取XML文件内容
	public synchronized static Executor getExecutor() {
		if(executor == null) {
			MappedStatement analysisXML = new IMappedStatement();
			try {
				executor = new Executor(analysisXML.analysisXml());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executor;
	}
	
	public AnalysisMapper selectOne(String className,String name,Object... parameter){
		AnalysisMapper analysisMapper = saveMap.get(className).get(name);
		String sql = analysisMapper.getSql();
		ParameterHandler parameterHandler = new ParameterHandler();
		try {
			analysisMapper.setSql(parameterHandler.parameterExecutor(className, sql, name, parameter));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return analysisMapper;
	}
}
