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

        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        SAXParser parser = saxpf.newSAXParser();
        XMLReader procesadorXML = parser.getXMLReader();

/*Es podria utilitzar defaultHandler, però millor utilitzar la subclasse ja que aquesta
  pot incorporar altres mètodes o modificar els que incorpora per defecte*/        
        GestioContingut gestor = new GestioContingut();
        procesadorXML.setContentHandler(gestor);

        InputSource fileXML = new InputSource ("empleats.xml");
        procesadorXML.parse(fileXML);
   }
}
/*Classes i interfícies de SAX
 GestionContingut és la classe que implementa els mètodes necessaris per crear el nostre parser de XML. És a dir, 
definim els mètodes que seran cridats en provocar-se els esdeveniments comentats anteriorment: startDocument, 
startElement, characters, etc. Si volguéssim tractar més esdeveniments definiríem el mètode associat en aquesta classe. */
  
    class GestioContingut extends DefaultHandler {
        public GestioContingut(){
        super();
        }

//Anunciem l'inici del document
        public void startDocument(){
        System.out.println("Inici del document XML");
        }

//Anunciem el final del document
        public void endDocument(){
        System.out.println("Final del document XML");
        }

//Comencem a tractar els elements
        public void startElement (String uri, String nom, String nomC, Attributes atts) {
            for (int i = 0; i < atts.getLength(); i++) {
                String nomAtribut = atts.getQName(i); // Nom de l'atribut (si l'element en té)
                String valorAtribut = atts.getValue(i);   // Valor d'aquest atribut
                System.out.printf("\t\tAtribut: %s, Valor: %s %n", nomAtribut, valorAtribut);
            }
            System.out.printf("\tPrincipi Element: %s %n", nomC);
        }

//Final de tractament dels elements
        public void characters(char[] ch, int inicio, int longitud) throws SAXException {
            String car = new String (ch, inicio, longitud).trim();
            if (!car.isEmpty()) {
                System.out.printf("\tCaracters: %s %n", car);
            }
        }

        public void endElement (String uri, String nom, String nomC){
            System.out.printf("\tFinal d'element: %s %n",nomC);
        }
}
