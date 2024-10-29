import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*; 
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CrearEmpleatDOM {

    public static void main (String args[]) throws IOException {
        
        File fichero = new File ("EmpleatRAF.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        
        int id, dep, posicio=0;
        
        Double salari;
        
        char nomEmp[] = new char[20], aux;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument (null,"empleats", null);
            document.setXmlVersion("1.0");
       
            for ( ; ;){
                file.seek(posicio);
                id = file.readInt();
                
                for (int i = 0; i < nomEmp.length ; i++) {
                    aux =file.readChar();
                    nomEmp[i] = aux;
                }
        
                String nomEmps = new String (nomEmp);
        
                dep = file.readInt();
        
                salari = file.readDouble();
        
                if (id>0) {
        
                    Element arrel = document.createElement ("empleat");
                    arrel.setAttribute("id",Integer.toString(id));
                    document.getDocumentElement().appendChild(arrel);

                    CrearElement ("nomEmp",nomEmps.trim(), arrel, document);
                    CrearElement ("dep", Integer.toString(dep), arrel, document);
                    CrearElement ("salari", Double.toString(salari),arrel, document);
                }
        
                posicio = posicio + 56;
        
                if (file.getFilePointer() == file.length()) break;
        
            }
        
            Source source = new DOMSource (document);
            Result result = new StreamResult (new FileWriter("empleats.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "5");
            transformer.transform (source, result);
        
        } catch (Exception e ) { System.err.println ("Error: " + e);}
            file.close();
        }
        
        public static void CrearElement (String dadaEmpleat, String valor, Element arrel, Document document) {
            Element elem = document.createElement (dadaEmpleat);
            Text text = document.createTextNode(valor);
            arrel.appendChild (elem);
            elem.appendChild (text);
        }
}
