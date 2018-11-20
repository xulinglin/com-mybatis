package com.mybatis.dome;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.mybatis.enums.SqlEnum;


public class IMappedStatement implements MappedStatement {

	private ClassLoader loader;
	private List<String> listXMLName;
	
	public Map<String, Map<String,AnalysisMapper>> analysisXml() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Map<String,AnalysisMapper>> map = new HashMap<>();
		if(this.loader == null){
            this.loader= ClassLoader.getSystemClassLoader();
        }
		if(this.listXMLName == null) {
			listXMLName = new ArrayList<>();
		}
		//通过包名文件绝对路径
        String mapperPath= ClassLoader.getSystemResource("").getPath()+mapperLocations.replace(".", "/");
        File file = new File(mapperPath);
        File[] tempList = file.listFiles();	
		for (File files : tempList) {
			if(files.isFile()) {
				//当前所有Mapper文件路径
				listXMLName.add(mapperPath+"/"+files.getName());
			}
		}
		
		//使用jdom解析
		for (String value : listXMLName) {
			SAXBuilder saxBuilder = new SAXBuilder();
			//创建一个输入流,加载xml文件
			InputStream in = new FileInputStream(value);
			Document document = saxBuilder.build(in);
			//获取根节点
			Element rootElement = document.getRootElement();
			//获取属性
			Map<String, AnalysisMapper> mapperMap = new HashMap<>();
			String namespace = rootElement.getAttribute("namespace").getValue();
			if(null == namespace) {
				throw new NullPointerException(value+" namespace is null");
			}
			//获取子节点
			List<Element> elements =  rootElement.getChildren();
			AnalysisMapper mapper = new AnalysisMapper();
			//空指针就不判断了
			for (Element element : elements) {
				
				String name = element.getName();
				String id = element.getAttributeValue("id");
				String parameterType = element.getAttributeValue("parameterType");
				String resultType = element.getAttributeValue("resultType");
				String sql = element.getTextNormalize();
				
				mapper.setSqlType(SqlEnum.valueOf(name));
				mapper.setId(id);
				mapper.setSql(sql);
				mapper.setParameterType(parameterType);
				mapper.setResultType(resultType);
				
				mapperMap.put(id, mapper);
			}
			map.put(namespace, mapperMap);
		}
		System.out.println(map);
		return map;
	}

}
