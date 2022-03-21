package exo3;/*
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    private PrintWriter out = null;

    public void parse(String _fileXML) throws SAXException, ParserConfigurationException, IOException {
        FileInputStream _xml_input_file = new FileInputStream(_fileXML);

        parse(_xml_input_file);
    }

    public void parse(InputStream _xml_input_file) throws SAXException, ParserConfigurationException, IOException {

        out = new PrintWriter(new FileOutputStream("./src/exo3/gender-sorted.xml"));

        out.print("""
            <?xml version="1.0"?>
            <!DOCTYPE list [
                    <!ELEMENT list (man | woman)*>
                    <!ELEMENT man (sons, daughters)>
                    <!ATTLIST man name CDATA #REQUIRED>
                    <!ELEMENT woman (sons, daughters)>
                    <!ATTLIST woman name CDATA #REQUIRED>
                    <!ELEMENT sons (man)*>
                    <!ELEMENT daughters (woman)*>
                    ]>""");

        DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
        _factory.setIgnoringComments(true);
        DocumentBuilder _builder = _factory.newDocumentBuilder();
        Document doc = _builder.parse(_xml_input_file);

        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("list");

        List<Node> personNodeList = new ArrayList<>();
        for (int i = 0; i < nodes.item(0).getChildNodes().getLength(); i++)
            personNodeList.add(nodes.item(0).getChildNodes().item(i));

        List<Person> persons = new ArrayList<>();
        for (Node node: personNodeList)
            if (node.getNodeName().equals("person"))
                persons.add(createPerson((Element) node));

        out.println("\n<list>");

        for (Person person : persons )
            buildXMLPerson(person);

        out.println("</list>");

        out.close();
        out.flush();
    }

    private Person createPerson(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        Gender gender = element.getAttribute("gender").equals("M") ? Gender.M : Gender.F;

        NodeList childrenNodes = element.getElementsByTagName("children");

        List<Node> childrenNodeList = new ArrayList<>();
        for (int i = 0; i < childrenNodes.item(0).getChildNodes().getLength(); i++)
            childrenNodeList.add(childrenNodes.item(0).getChildNodes().item(i));

        List<Person> children = new ArrayList<>();
        for (Node person: childrenNodeList)
                if (person.getNodeName().equals("person"))
                    children.add(createPerson((Element) person));

        return new Person(name, gender, children);
    }

    public void buildXMLPerson(Person person) {
        String gender = person.gender == Gender.M ? "man" : "woman";

        out.println("\t<" + gender + " name=\"" + person.name + "\">");

        out.println("\t\t<sons>");
        for (Person children : person.children )
            if (children.gender.equals(Gender.M))
                buildXMLPerson(children);
        out.println("\t\t</sons>");

        out.println("\t\t<daughters>");
        for (Person children : person.children )
            if (children.gender.equals(Gender.F))
                buildXMLPerson(children);
        out.println("\t\t</daughters>");

        out.println("\t</" + gender + ">");
    }
}