/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author matxd
 */
public class GestoreClient implements Runnable {
    
    private final Socket clientSocket;
    private Database db;
    BufferedReader input;
    PrintWriter output;

    public GestoreClient(Socket clientSocket, Database db) {
        this.clientSocket = clientSocket;
        this.db= db;
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore" + "\033[0m");
        }
        
    }

    @Override
    public void run() {
        try{
            
            String richiesta;
            while ((richiesta=input.readLine()) !=null) {
                //Preleva i pezzi della richiesta
                String [] comando=richiesta.split("/");
               
                //Operazione di verifica esistenza utente
                if(comando[0].equals("e")){
                    String nickname= comando[1];
                    String password=comando[2];
                    int id=0;
                    if((id=db.getIdUtente(nickname,password))!=0)
                        output.println("OK/"+id);   
                    else
                        output.println("L'utente non esiste");
                }
                
                //Operazione di registrazione utente
                if(comando[0].equals("u")){
                    String nickname= comando[1];
                    String password=comando[2];
                    //Verifica se esiste già un utente con quel nickname
                    if(db.getIdUtente(nickname,password)==0){
                        int id= db.inserisciUtente(nickname, password);
                        output.println("OK/"+id); 
                    }
                    //Nickname già in uso
                    else
                        output.println("Nickname già in uso");
                }
                
            }
        }
        catch(SQLException s){
            output.println("Errore nella connessione al database");
        } 
        catch(IOException e) {
            output.println("Errore nella connessione al server");
        }
        
    }
    

}
