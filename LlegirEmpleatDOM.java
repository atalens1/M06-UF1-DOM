import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LlegirEmpleatDOM {
    public static void main (String[] args) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File ("empleats.xml"));
                Element arrel = document.getDocumentElement();
                System.out.printf ("element arrel : %s %n", arrel.getNodeName());

                NodeList empleats = document.getElementsByTagName("empleat");
                
                System.out.printf ("Nodes a recòrrer: %d %n", empleats.getLength());
        
                for (int i = 0; i < empleats.getLength(); i++) {
                    Node emple = empleats.item(i);
                    if (emple.getNodeType() == Node.ELEMENT_NODE){
                        
                        Element element = (Element) emple;

                        System.out.printf("Id = %s %n",element.getAttribute("id"));
                        
                        System.out.printf(" * nom empleat = %s %n", 
                        element.getElementsByTagName("nomEmp").item(0).getTextContent());
        
                        System.out.printf(" * departament = %s %n", 
                        element.getElementsByTagName("dep").item(0).getTextContent());
        
                        System.out.printf(" * salari = %s %n",
                        element.getElementsByTagName("salari").item(0).getTextContent());
                    }
                }
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }
}
