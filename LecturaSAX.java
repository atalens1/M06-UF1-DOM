import java.io.*;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class LecturaSAX {
    public static void main (String [] args)
           throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
/* A continuación se crea objeto procesador XML - XMLReader -. Durante la creación de este objeto se puede producir una 
excepción SAXException. */
//   XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        SAXParser parser = saxpf.newSAXParser();
        XMLReader procesadorXML = parser.getXMLReader();
/* A continuación, mediante setContentHandler establecemos que la clase que gestiona los eventos provocados por la 
lectura del XML será GestionContenido */
        GestionContenido gestor = new GestionContenido();
        procesadorXML.setContentHandler(gestor);
/* Por último, se define el fichero que se va leer mediante InputSource y se procesa el documento XML mediante el 
método parse() de XMLReader */
        InputSource fileXML = new InputSource ("empleats.xml");
        procesadorXML.parse(fileXML);
   }
}
//Clases e interfaces de SAX
/* GestionContenido es la clase que implementa los métodos necesarios para crear nuestro parser de XML. Es decir, 
definimos los métodos que serán llamados al provocarse los eventos comentados anteriormente: startDocument, 
startElement, characters, etc. Si quisieramos tratar más eventos definiríamos el método asociado en esta clase. */
    class GestionContenido extends DefaultHandler {
        public GestionContenido(){
        super();
        }

        public void startDocument(){
        System.out.println("Comienzo del documento XML");
        }

        public void endDocument(){
        System.out.println("Final del documento XML");
        }

        public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
        System.out.printf("\tPrincipio Elemento: %s %n", nombre);
        }

        public void endElement (String uri, String nombre, String nombreC){
        System.out.printf("\tFin Elemento: %s %n",nombre);
        }

        public void characters(char[] ch, int inicio, int longitud) throws SAXException {
            String car = new String (ch, inicio, longitud);
            car = car.replaceAll("[\t\n]","");
            System.out.printf("\tCaracteres: %s %n", car);
        }
}
