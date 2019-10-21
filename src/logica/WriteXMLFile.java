/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/**
 *
 * @author usuario
 */
public class WriteXMLFile {
    private DocumentBuilderFactory docFactory ;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Element child;
    private Element root;
    private Element paisActual;
    public WriteXMLFile () {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            root = doc.createElement("Paises");
            doc.appendChild(root);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void agregarPaiz () {
        paisActual = doc.createElement("País");
        root.appendChild(paisActual);
    }
    
    public void agregarCaracteristica(String etiqueta,String contenido){
        child = doc.createElement(etiqueta);
        child.appendChild(doc.createTextNode(contenido));
        paisActual.appendChild(child);
    }
    
    public boolean escribirDocumento () {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("archivo.xml"));
            transformer.transform(source, result);
            return true;
        } catch (TransformerException ex) {
            return false;
        }
	
    }
    
}
