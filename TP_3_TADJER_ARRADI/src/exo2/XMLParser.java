package exo2;
/*
 * Created on 26 nov. 2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLParser {

	public Document getDocument(String _fileXML) throws SAXException, IOException, ParserConfigurationException {
		FileInputStream _xml_input_file = new FileInputStream(_fileXML);
		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
		_factory.setIgnoringComments(true);
		DocumentBuilder _builder = _factory.newDocumentBuilder();

		return _builder.parse(_xml_input_file);
	}

	public String Display(String _fileXML, String nodeName) throws SAXException, ParserConfigurationException, IOException  {
		Document doc = getDocument(_fileXML);
		StringBuilder stringBuilder = new StringBuilder();

		NodeList elementsList = doc.getElementsByTagName(nodeName);

		for (int i = 0; i < elementsList.getLength(); ++i) {
			Element element = (Element) elementsList.item(i);
			stringBuilder.append(element.getTextContent());
		}

		return stringBuilder.toString();
	}

	public String Delete(String _fileXML, String nodeName) throws SAXException, ParserConfigurationException, IOException {
		Document doc = getDocument(_fileXML);

		NodeList elements = doc.getElementsByTagName(nodeName);
		if (elements.getLength() > 0) {
			for (int i = 0; i < elements.getLength(); ++i) {
				Element element = (Element) elements.item(i);
				doc.getElementsByTagName("E-mail").item(0).removeChild(element);
			}
			saveXMLContent(doc, _fileXML);
			return nodeName + " node, deleted with success";
		}

		return "Invalid nodeName";
	}

	private static void saveXMLContent(Document document, String xmlFile) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(xmlFile));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}