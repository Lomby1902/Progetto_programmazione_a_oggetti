/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    
    
    public static void menuUtente(){
        System.out.println("Benvenuto "+ nuovoUtente.getNickname());
        System.out.println("ID Utente: " + nuovoUtente.getID());
        System.out.println("");
        System.out.println("\u001B[1m"+"Le tue chat");
        System.out.println("");
        
        Object oggettoRisposta;
        try {
            
                
                //Indica al thread del server che vuole mostrare le chat private dell'utente
                outputStream.writeObject("s/ChatPrivate"+"/"+ nuovoUtente.getNickname()); 
                //Risposta del server
                oggettoRisposta= inputStream.readObject();
                if(!(oggettoRisposta instanceof String)){
                    
                    ArrayList<String []> risposta= (ArrayList < String[] >)oggettoRisposta;
                    
                    //Se esiste almeno una chat in cui si trova l'utente
                    if(risposta.size()>0){
                        for(int i=0;i<risposta.size();i++){
                                System.out.print("ID Chat: "+ risposta.get(i)[0]);
                                System.out.print("   Utenti: "+ risposta.get(i)[1] + ", "+ risposta.get(i)[2]);
                                System.out.println("");
                                System.out.println("");
                        }  
                    }
                }
                //Messaggio di errore
                else{
                    String risposta = (String) (oggettoRisposta);
                    System.out.println("\033[1;31m" + risposta + "\033[0m");
                    System.out.println("");
                   
                }
             
                System.out.println("\u001B[1m"+"I tuoi Gruppi");
                System.out.println("");
                
                //Indica al thread del server che vuole mostrare i gruppi dell'Utente
                outputStream.writeObject("s/Gruppi"+"/"+ nuovoUtente.getNickname()); 
                //Risposta del server
                oggettoRisposta= inputStream.readObject();
                if(!(oggettoRisposta instanceof String)){
                    
                    ArrayList<String []> risposta= (ArrayList < String[] >)oggettoRisposta;
                    
                    //Se esiste almeno una chat in cui si trova l'utente
                    if(risposta.size()>0){
                        for(int i=0;i<risposta.size();i++){
                                System.out.print("ID Gruppo: "+ risposta.get(i)[0]);
                                System.out.print("   Nome Gruppo: "+ risposta.get(i)[1]);
                                System.out.println("");
                                System.out.println("");
                        }  
                    }
                }
                //Messaggio di errore
                else{
                    String risposta = (String) (oggettoRisposta);
                    System.out.println("\033[1;31m" + risposta + "\033[0m");
                    System.out.println("");
                    return;
                }
                
                
        } 
        catch (IOException e) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        } catch (ClassNotFoundException ex) {
             System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
                 
                //Indica al thread del server che vuole verificare l'esistenza di un utente e invia i dati
                outputStream.writeObject("e/"+nickname+"/"+password); 
                //Risposta del server
                String risposta= (String) inputStream.readObject();
                //Preleva i pezzi della risposta
                String [] comando=risposta.split("/");
                //Se l'utente esiste
                if(comando[0].equals("OK")){
                    int id= Integer.parseInt(comando[1]);
                    nuovoUtente= new Utente(id,nickname,password);
                    menuUtente();
                    
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
        } catch (ClassNotFoundException ex) {
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
           
                //Indica al thread del server che vuole registrare un utente
                outputStream.writeObject("u/"+nickname+"/"+password);
                //Risposta del server
                String risposta= (String)inputStream.readObject();
                 //Preleva i pezzi della risposta
                String [] comando=risposta.split("/");
                //Se l'utente non esiste
                if(comando[0].equals("OK")){
                    int id= Integer.parseInt(comando[1]);
                    nuovoUtente= new Utente(id,nickname,password);
                    System.out.println("Utente Registrato");
                    menuUtente();
                    
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
        } catch (ClassNotFoundException ex) {
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
            outputStream = new ObjectOutputStream(s.getOutputStream());
            inputStream = new ObjectInputStream(s.getInputStream());
            menuprincipale();
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
        }
       
       
        
        
    
    }
    
}
