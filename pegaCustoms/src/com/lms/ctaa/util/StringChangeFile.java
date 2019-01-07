package com.lms.ctaa.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * XML字符串转成文件
 * @author guomingjia
 *
 */
public class StringChangeFile {

	 /**
     * 将字符串转换成XML文件
     */
    public static void StrChangeXML(StringBuffer str,File d){
    	PrintStream ps =null;
    	try{
    		ps= new PrintStream(d);
    		ps.print(str);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		ps.flush();
    		ps.close();
    	}
    }
}
