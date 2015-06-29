package com.qcm;

import java.io.File;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
 
public class XmlHandler {
    public static void main(String[] args) {
       SAXReader saxReader = new SAXReader();
       File file = new File("output.xml");
       try {
           // 添加一个ElementHandler实例。
           saxReader.addHandler("/students/student", new StudentHandler()); //根据名称找到某个子节点 得到处理器
           saxReader.read(file);
 
       } catch (DocumentException e) {
           System.out.println(e.getMessage());
       }
    }
 
    /**
     * 定义StudentHandler处理器类，对<student>元素进行处理。
     */
    private static class StudentHandler implements ElementHandler {
       public void onStart(ElementPath path) {
           Element elt = path.getCurrent();
           System.out.println("Found student: " + elt.attributeValue("sn"));
           // 添加对子元素<name>的处理器。
           path.addHandler("age", new NameHandler());
       }
 
       public void onEnd(ElementPath path) {
           // 移除对子元素<name>的处理器。
           path.removeHandler("age");
       }

	
    }
 
    /**
     * 定义NameHandler处理器类，对<student>的<name>子元素进行处理。
     */
    private static class NameHandler implements ElementHandler {
       public void onStart(ElementPath path) {
           System.out.println("path : " + path.getPath());
       }
 
       public void onEnd(ElementPath path) {
           Element elt = path.getCurrent();
           // 输出<name>元素的名字和它的文本内容。
           System.out.println(elt.getName() + " : " + elt.getText());
       }

	
    }
}