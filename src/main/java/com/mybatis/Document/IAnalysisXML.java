package com.mybatis.Document;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class IAnalysisXML implements AnalysisXML {

	public Map<String, Object> analysisXml() throws Exception {
		// TODO Auto-generated method stub
		if(!mapperLocations.endsWith(".xml")) {
			throw new FileNotFoundException("No .xml ending file");
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("books.xml");
		return null;
	}

}
