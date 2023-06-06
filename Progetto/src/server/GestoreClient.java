/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




public class GestoreClient implements Runnable {
    
    private final Socket clientSocket;
    private Database db;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public GestoreClient(Socket clientSocket, Database db) {
        this.clientSocket = clientSocket;
        this.db= db;
        try {
          
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore" + "\033[0m");
        }
        
    }

    @Override
    public void run() {
        try{
            
            String richiesta;
            while ((richiesta=(String)inputStream.readObject()) !=null) {
                //Preleva i pezzi della richiesta
                String [] comando=richiesta.split("/");
               
                //Operazione di verifica esistenza utente
                if(comando[0].equals("e")){
                    String nickname= comando[1];
                    String password=comando[2];
                    int id=0;
                    if((id=db.getIdUtente(nickname,password))!=0)
                        outputStream.writeObject("OK/"+id);   
                    else
                       outputStream.writeObject("Username e/o password errati");
                }
                
                //Operazione di registrazione utente
                if(comando[0].equals("u")){
                    String nickname= comando[1];
                    String password=comando[2];
                    //Verifica se esiste già un utente con quel nickname
                    if(db.getIdUtente(nickname,password)==0){
                        int id= db.inserisciUtente(nickname, password);
                        outputStream.writeObject("OK/"+id); 
                    }
                    //Nickname già in uso
                    else
                        outputStream.writeObject("Nickname già in uso");
                }
                
                 //Operazione di visualizzazione chat o gruppi
                if(comando[0].equals("s")){
                    //tabella può essere chat private o gruppi
                    String tabella= comando[1];
                    String nickname=comando[2];
                    //Verifica se il numero di chat o gruppi è maggiore di 0
                    ArrayList<String []> arr= db.mostra(tabella, nickname);
                    if(arr.size()!=0){ 
                       outputStream.writeObject(arr);
                         
                    }
                   //Nessun risultato
                    else
                        outputStream.writeObject("Nessun Risultato");
                }
                  
            }
        }
        catch(SQLException | ClassNotFoundException | IOException e ){
            try {
                outputStream.writeUTF("Errore");
            } catch (IOException ex) {
             System.err.println("Errore nell'invio al client");
            }
        } 
       
        
        
    }
    

}
