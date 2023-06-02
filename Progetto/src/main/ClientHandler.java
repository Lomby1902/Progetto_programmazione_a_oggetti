/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
/**
 *
 * @author matxd
 */
public class ClientHandler implements Runnable {
    
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            String comando;
            while ((comando=input.readLine()) !=null) {
                String [] estratto=comando.split("/");
               
                //Operazione di verifica esistenza utente
                if(estratto[0].equals("e")){
                    String nickname= estratto[1];
                    String password=estratto[2];
                    if(Database.esisteUtente(nickname,password))
                        output.println("OK");   
                    else
                        output.println("NO");
                }
                
            }
        }
        catch(SQLException s){
            System.out.println("Errore nella connessione al database");
        } 
        catch(IOException e) {
            System.out.println("Errore nella connessione al server");
        }
        
    }
    

}
