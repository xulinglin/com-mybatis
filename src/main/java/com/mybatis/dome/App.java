package com.mybatis.dome;

import com.mybatis.Document.AnalysisXML;
import com.mybatis.Document.IAnalysisXML;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnalysisXML analysisXML = new IAnalysisXML();
    	try {
			analysisXML.analysisXml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
