package com.qcm;

import java.io.File;
import java.io.FileWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
 
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
 
public class XmlXslt {
	   public Document styleDocument(Document document, String stylesheet)
           throws Exception {
 
       // load the transformer using JAXP jaxp为XML处理的Java API
       TransformerFactory factory = TransformerFactory.newInstance();
       Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
 
       // now lets style the given document  进行转换
       DocumentSource source = new DocumentSource(document);
       DocumentResult result = new DocumentResult();
       transformer.transform(source, result);
 
       // return the transformed document
       Document transformedDoc = result.getDocument();
       return transformedDoc;
    }
	   
	   public static void main(String[] args) throws Exception {
		   SAXReader saxReader = new SAXReader();
           Document document = saxReader.read("cdcatalog.xml");
           XmlXslt xslt = new XmlXslt();
		   Document doc = xslt.styleDocument(document, "cdcatalog.xsl");
		   OutputFormat format = OutputFormat.createPrettyPrint();
		   XMLWriter output = new XMLWriter(new FileWriter(new File(
	                  "cdcatalog.html")),format);
	           output.write(doc);
	           output.close();
	}
	   
}











