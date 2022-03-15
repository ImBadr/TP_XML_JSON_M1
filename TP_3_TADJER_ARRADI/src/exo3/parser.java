package exo3;/*
 * Created on 26 nov. 2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class parser {
    public Document getDocument(String _fileXML) throws SAXException, IOException, ParserConfigurationException {
        FileInputStream _xml_input_file = new FileInputStream(_fileXML);
        DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
        _factory.setIgnoringComments(true);
        DocumentBuilder _builder = _factory.newDocumentBuilder();

        return _builder.parse(_xml_input_file);
    }

}