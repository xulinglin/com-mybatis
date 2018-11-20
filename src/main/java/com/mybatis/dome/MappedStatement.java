package com.mybatis.dome;


import java.util.Map;

public interface MappedStatement {
	
	//加载路径
	public String mapperLocations = "com/mybatis/mapper";
	//实体类包路径
//	public String typeAliasesPackage = "com.mybatis.model";
	
	Map<String, Map<String,AnalysisMapper>> analysisXml() throws Exception;
}