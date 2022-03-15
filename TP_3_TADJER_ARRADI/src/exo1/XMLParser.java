package exo1;

/*
 * Created on 26 nov. 2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

public class XMLParser {

	public PrintWriter out = null;

	//Rem changer Package par ModelElement
	public void parse(String _fileXML)
		throws SAXException, ParserConfigurationException, IOException {

		// Charger le document
		FileInputStream _xml_input_file = new FileInputStream(_fileXML);

		parse(_xml_input_file);
	}

	public void parse(InputStream _xml_input_file)
		throws SAXException, ParserConfigurationException, IOException {

		// Output creation fil
		out = new PrintWriter( 
		new FileOutputStream("./output.html"));
		out.println("<html xmlns:fo=\"http://www.w3.org/1999/XSL/Format\"><head />");
		out.println("<body>");
		out.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><h1 align=\"left\">Domaines </h1>");

		// Instance parser builder
		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();

		// ignore comments in XML file
		_factory.setIgnoringComments(true);
		
		// create parser
		DocumentBuilder _builder = _factory.newDocumentBuilder();

		// Charge the document
		Document doc = _builder.parse(_xml_input_file);

		// Parse the document
		NodeList domainsList = doc.getElementsByTagName("domain");

		// Menu
		for (int i = 0; i < domainsList.getLength(); ++i) {
			Node domainNode = domainsList.item(i);

			if (domainNode.getNodeType() == Node.ELEMENT_NODE) {
				Element domainElement = (Element) domainNode;

				String title = domainElement.getElementsByTagName("title").item(0).getTextContent();

				out.println("<h2><a href=\"#" + title + "\">" + title + "</a></h2>");
			}
		}

		out.println("<hr>\n" +
				"<hr>");

		// Content
		for (int i = 0; i < domainsList.getLength(); ++i) {
			Node domainNode = domainsList.item(i);

			if (domainNode.getNodeType() == Node.ELEMENT_NODE) {
				Element domainElement = (Element) domainNode;

				String domainTitle = domainElement.getElementsByTagName("title").item(0).getTextContent();

				DomainTemplate(domainTitle);

				NodeList bib_refList = domainElement.getElementsByTagName("bib_ref");
				for (int y = 0; y < bib_refList.getLength(); ++y){
					Node bib_refNode = bib_refList.item(y);

					if (bib_refNode.getNodeType() == Node.ELEMENT_NODE) {
						Element bib_refElement = (Element) bib_refNode;

						String year = bib_refElement.getElementsByTagName("year").item(0).getTextContent();
						String bib_refTitle = bib_refElement.getElementsByTagName("title").item(0).getTextContent();
						String author = bib_refElement.getElementsByTagName("author").item(0).getTextContent();
						String weblink = bib_refElement.getElementsByTagName("weblink").item(0).getTextContent();

						out.println("<hr>\n" +
								"Annee  :" + year + "<br>\n" +
								"<h3>Titre :" + bib_refTitle + "</h3>\n" +
								"Auteur(s)  :" + author + "<br>\n" +
								"Lien : <a href=\""+ weblink + "\">" + weblink + "</a><br>");
					}
				}
			}
		}

		out.close();
		out.flush();
	}

	public void DomainTemplate(String name) {
		out.println("<table width=\"100%\" border=\"1\">");
		out.println("	<tr>");
		out.println("   	<td width=\"100%\" bgcolor=\"#C0C0C0\">");
		out.println(
				"			<h2><a name=\"" + name + "\">" + name + "</a></h2>");
		out.println("		</td>");
		out.println("	</tr>");
		out.println("</table>");
	}

}