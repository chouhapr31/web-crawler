package co.uk.prudential.demo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Test2 {

	public static void main(String argv[]) {

		String document = "-<url>\r\n" + 
				"https://www.prudential.co.uk/\r\n" + 
				"<i-url>https://www.prudential.co.uk/contacts/media-enquiries#mmenu</i-url>";
		System.out.println(document);
//		NodeList nodes = document.getElementsByTagName("url");

	}

}
