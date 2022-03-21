package exo3;/*
 * Created on 26 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


/**
 * @author Salim
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println("Begin");
			XMLParser parser = new XMLParser();

			String filename = "./src/exo3/gender.xml";

			parser.parse(filename);
			System.out.println("End");
		}
		catch (Exception e ){
			e.printStackTrace();
		}
	}
}
