/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

  
/**
 *
 * @author matxd
 */
public class Server {
   
   private static String indirizzo;
   private static int porta;
   
    
    public static void leggiConfigurazione(){
        try {
            DocumentBuilderFactory dbf=  DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = dbf.newDocumentBuilder();
            Document doc= dB.parse(new File("configurazione.xml"));
            
            Element root= doc.getDocumentElement();
            root.normalize();
            NodeList children= root.getChildNodes();
            for(int i=0;i<children.getLength();i++){
                if(children.item(i).getNodeType()== Element.ELEMENT_NODE){
                    if(children.item(i).getNodeName().equals("indirizzo")){
                        indirizzo=children.item(i).getTextContent();
                    }
                    if(children.item(i).getNodeName().equals("porta"))
                        porta=Integer.parseInt(children.item(i).getTextContent());
                }   
            }
        } catch (ParserConfigurationException ex) {
            System.out.println("Errore");
        } catch (SAXException ex) {
             System.out.println("Errore");
        } catch (IOException ex) {
            System.out.println("Errore");
       }
    }
    
    
    
    public static void main(String[] args) throws IOException{  
    
        leggiConfigurazione();
        Database db = new Database();
        ServerSocket listener = new ServerSocket(porta);
        System.out.println("Server Partito alla porta "+ porta);
        
        try {
            while (true) {                
                Socket socket = listener.accept();
                System.out.println("Client Connesso");
                GestoreClient client = new GestoreClient(socket, db);
                Thread T1 = new Thread(client);
                T1.start();
            }
        }finally{
            listener.close();
        }
        
    }
}
