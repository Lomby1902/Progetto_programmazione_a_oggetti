/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author giovanni
 */
public class Main {
    private static Socket s;
    private static Utente nuovoUtente;
   
    
    
    
    
        //Menu di login
    public static void menuLogin(){    
        Scanner tastiera= new Scanner(System.in);
        System.out.println("Inserisci il tuo nickname (Inserire 0 per tornare indietro):");
        String nickname = tastiera.next();
        if(nickname.equals("0"))
               return;
         System.out.println("Inserisci la password (Inserire 0 per tornare indietro):");
         String password = tastiera.next();
         if(password.equals("0"))
               return;
         try {
                PrintWriter output = new PrintWriter (s.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //Indica al thread del server che vuole verificare l'esistenza di un utente e invia i dati
                output.println("e/"+nickname+"/"+password); 
                //Risposta del server
                String risposta= input.readLine();
                //Preleva i pezzi della risposta
                String [] comando=risposta.split("/");
                //Se l'utente esiste
                if(comando[0].equals("OK")){
                    int id= Integer.parseInt(comando[1]);
                    nuovoUtente= new Utente(id,nickname,password);
                    System.out.println("Benvenuto "+ nuovoUtente.getNickname());
                    System.out.println("ID Utente: " + nuovoUtente.getID());
                    
                }
                //Messaggio di errore (Errore connessione o utente non esiste)
                else{
                    System.out.println("\033[1;31m" + risposta + "\033[0m");
                    System.out.println("");
                    return;
                }
             
        } 
        catch (IOException e) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        }
        
         
    }
    
    
   //Menu per registrazione utente
    public static void menuRegistrazione() {
        Scanner tastiera= new Scanner(System.in);
        System.out.println("Inserisci il tuo nickname (Inserire 0 per tornare indietro):");
        String nickname = tastiera.next();
        if(nickname.equals("0"))
               return;
         System.out.println("Inserisci la password (Inserire 0 per tornare indietro):");
         String password = tastiera.next();
         if(password.equals("0"))
               return;  
         try {
                PrintWriter output = new PrintWriter (s.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //Indica al thread del server che vuole registrare un utente
                output.println("u/"+nickname+"/"+password);
                //Risposta del server
                String risposta= input.readLine();
                 //Preleva i pezzi della risposta
                String [] comando=risposta.split("/");
                //Se l'utente non esiste
                if(comando[0].equals("OK")){
                    int id= Integer.parseInt(comando[1]);
                    nuovoUtente= new Utente(id,nickname,password);
                    System.out.println("Utente Registrato");
                    System.out.println("Benvenuto "+ nuovoUtente.getNickname());
                    System.out.println("ID Utente: " + nuovoUtente.getID());
                    
                }
                //Messaggio di errore (Errore connessione o nickname già in uso)
                else{
                    System.out.println("\033[1;31m"+ risposta + "\033[0m");
                    System.out.println("");
                    return;
                }
         }
         catch (IOException e) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        }
         
    }
    
    
    
    
    
    
    //Menu principale
    public static void menuprincipale(){
        while (true) {            

            System.out.println("Benvenuto su JavaChat");
            System.out.println("Che operazione vuoi eseguire?");
            System.out.println("");
            System.out.println("1) Login");
            System.out.println("2) Registrazione");
            System.out.println("3) Uscire");
            System.out.println("");
            Scanner tastiera= new Scanner(System.in);
            switch (tastiera.nextInt()) {
                case 1:
                        menuLogin();
                        break;
                case 2:
                        menuRegistrazione();
                        break;
                case 3: 
                        System.exit(0);
              
            }
        
        }
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String serverAddress = "localhost";
        int serverPort = 9091;
        try {
            s = new Socket(serverAddress, serverPort);
            menuprincipale();
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
        }
       
       
        
        
    
    }
    
}
