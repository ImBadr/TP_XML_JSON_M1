package exo2;/*
 * Created on 26 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Salim
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class Main {

	public static void main(String[] args) {
		try {
			XMLParser parser  = new XMLParser();
			String filename = "./src/exo2/gender.xml";

			MenuDisplay(filename, parser);
		}
		catch (Exception e ){
			e.printStackTrace();
		}
		
	}

	private static void MenuDisplay(String filename, XMLParser parser) throws ParserConfigurationException, IOException, SAXException {
		System.out.println("Hello, please select an option :");
		System.out.println("[1] Display an element");
		System.out.println("[2] Delete an element");

		Scanner scanner = new Scanner(System.in);

		int choice = scanner.nextInt();

		switch (choice) {
			case 1 -> {
				System.out.println("Please enter a node name :");
				while (scanner.hasNext()) {
					String nodeName = scanner.nextLine();
					System.out.println(parser.Display(filename, nodeName));
					if ( !nodeName.isEmpty() ) break;
				}
				MenuDisplay(filename, parser);
			}
			case 2 -> {
				System.out.println("Please enter a node to delete :");
				while (scanner.hasNext()) {
					String nodeName = scanner.nextLine();
					System.out.println(parser.Delete(filename, nodeName));
					if ( !nodeName.isEmpty() ) break;
				}
				MenuDisplay(filename, parser);
			}
			default -> {
				System.out.println("Invalid Choice, please retry with valid choice");
				MenuDisplay(filename, parser);
			}
		}


	}
}
