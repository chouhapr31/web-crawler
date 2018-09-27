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

public class SiteMapper {
	public static final String xmlFilePath = "C:\\Windows\\Temp\\sitemap.xml";
	private Document document = null;
	private Element root = null;
	private DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	private TransformerFactory transformerFactory = TransformerFactory.newInstance();

	public SiteMapper() {
		try {
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();

			root = document.createElement("web-crawling");
			document.appendChild(root);

		} catch (ParserConfigurationException tfe) {
		}
	}

	public void map(String input) {

		Element url = document.createElement("url");
		url.appendChild(document.createTextNode(input));
		root.appendChild(url);
	}

	public void eMap(String input) {

		Element url = document.createElement("e-url");
		url.appendChild(document.createTextNode(input));
		root.appendChild(url);
	}

	public void staticMap(String source, String input, String url) {
		NodeList nodes = null;
		if (url.equalsIgnoreCase(source)) {
			nodes = document.getElementsByTagName("url");
		} else {
			nodes = document.getElementsByTagName("i-url");
		}
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			String text = element.getTextContent();
			if (text != null && !text.trim().isEmpty() && text.lastIndexOf("https://") > 1)
				text = text.substring(0, text.indexOf("https://", 10));

			if (text.equalsIgnoreCase(source)) {
				Element urlImg = document.createElement("static");
				urlImg.appendChild(document.createTextNode(input));
				element.appendChild(urlImg);
			}
		}

	}

	public void mapToChild(String source, String input) {
		NodeList nodes = document.getElementsByTagName("url");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			String text = element.getTextContent();
			if (text != null && !text.trim().isEmpty() && text.lastIndexOf("https://") > 1)
				text = text.substring(0, text.indexOf("https://", 10));

			if (text.equalsIgnoreCase(source)) {
				Element url = document.createElement("i-url");
				url.appendChild(document.createTextNode(input));
				element.appendChild(url);

			}
		}

	}

	public void transform() {
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (TransformerException tfe) {
		}
	}
}
