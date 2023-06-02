/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
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
            System.err.println("Errore nella connessione al server");
        }
        
    }

    @Override
    public void run() {
        try{
            
            String comando;
            while ((comando=input.readLine()) !=null) {
                String [] estratto=comando.split("/");
               
                //Operazione di verifica esistenza utente
                if(estratto[0].equals("e")){
                    String nickname= estratto[1];
                    String password=estratto[2];
                    if(db.esisteUtente(nickname,password))
                        output.println("OK");   
                    else
                        output.println("NO");
                }
                
            }
        }
        catch(SQLException s){
            System.err.println("Errore nella connessione al database");
        } 
        catch(IOException e) {
            System.err.println("Errore nella connessione al server");
        }
        
    }
    

}
