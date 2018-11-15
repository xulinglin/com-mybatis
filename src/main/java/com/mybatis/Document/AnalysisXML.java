package com.mybatis.Document;

import java.io.FileNotFoundException;
import java.util.Map;

public interface AnalysisXML {
	
	//加载路径
	public String mapperLocations = "classpath:com/mybatis/Mapper/*Mapper.xml";
	//实体类包路径
	public String typeAliasesPackage = "com.mybatis.model";
	
	Map<String, Object> analysisXml() throws Exception;
}
