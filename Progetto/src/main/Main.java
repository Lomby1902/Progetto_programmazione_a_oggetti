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
import java.util.Arrays;
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
    
     
    
    public static void menuChats(){
        System.out.println("");
        System.out.println("\u001B[1m" + "Le tue chat");
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
                        for(int i = 0; i < risposta.size(); i++){
                                System.out.print("\033[1;32m" + "ID Chat: " + risposta.get(i)[0]);
                                System.out.print("   Utenti: " + risposta.get(i)[1] + ", " + risposta.get(i)[2] + "\033[0m");

                                System.out.println("");
                                System.out.println("");
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

                    ArrayList<String[]> risposta = (ArrayList <String[]>) oggettoRisposta;
                    
                    //Se esiste almeno una chat in cui si trova l'utente
                    if(risposta.size()>0){
                        for(int i=0;i<risposta.size();i++){
                                System.out.print("\033[1;32m"+"ID Gruppo: "+ risposta.get(i)[0]);
                                System.out.print("   Nome Gruppo: "+ risposta.get(i)[1]+  "\033[0m");
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
                
                Scanner tastiera = new Scanner(System.in);
                while (true){
                    System.out.println("Inserisci");
                    System.out.println("g) Per accedere ad un gruppo");
                    System.out.println("c) per accedere ad una chat privata");
                    System.out.println("0) per tornare indietro");
                    String type = tastiera.nextLine();
                    if (type.equals("0")){
                        return;
                    }
                    System.out.println("Inserisci l'ID corrispondente:");
                    String ID = tastiera.nextLine();
                    if(type.equals("g")){
                        System.out.println("Accedo al gruppo");
                        menuGruppo(ID);
                    }else{
                        System.out.println("Accedo alla chat privata");
                        menuChatPrivata(ID);
                    }                    
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
    
    
    
    public static void menuRimozione(Gruppo g){
        System.out.println("");
        System.out.println("Inserisci il nickname dell'utente da eliminare(0 per tornare indietro): ");
        Scanner tastiera= new Scanner(System.in);
        String nickname= tastiera.nextLine();
        if (nickname.equals("0"))
            return;
        try {
            g.eliminaPartecipante(nuovoUtente.getNickname(),nickname);
             System.out.println("\033[1;32m" + "Utente rimosso correttamente" + "\033[0m");
        
        } catch (NotAdministratorException n) {
            System.out.println("\033[1;31m"+n.getMessage()+ "\033[0m");
        } catch (IOException ex) {
           System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
        }
    }
    
    public static void menuAggiunta(Gruppo g){
        System.out.println("");
        System.out.println("Inserire il nickname dell'utente da aggiungere(0 per tornare indietro)");
        Scanner tastiera= new Scanner(System.in);
        String nickname= tastiera.nextLine();
        if (nickname.equals("0"))
            return;
        try{
            g.aggiungiPartecipante(nuovoUtente.getNickname(), nickname);
            System.out.println("\033[1;32m" + "Utente aggiunto correttamente" + "\033[0m");
        } catch (NotAdministratorException n){
            System.out.println("\033[1;31m"+n.getMessage()+ "\033[0m");
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
        }
    }
    
    public static void menuNome(Gruppo g){
        System.out.println("");
        System.out.println("Inserire il nuovo nome del gruppo (0 per tornare indietro)");
        Scanner tastiera= new Scanner(System.in);
        String nome = tastiera.nextLine();
        if (nome.equals("0"))
            return;
        try{
            g.setNome(nuovoUtente.getNickname(), nome);
            System.out.println("\033[1;32m" + "Nome aggiornato correttamente" + "\033[0m");
        } catch (NotAdministratorException n){
            System.out.println("\033[1;31m"+n.getMessage()+ "\033[0m");
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
        }
    }
    
    public static void menuGruppo(String ID){
        //Crea un gruppo a partire dal suo ID, andando a scaricare le informazioni dal server
        try {
        Gruppo gruppo = new Gruppo(ID);
        //Thread per scaricare i messaggi
        ChatListener listener = new ChatListener(gruppo);       
        Thread T = new Thread(listener);  
        T.start();
        String nomeGruppo = gruppo.getNome();
        System.out.println("Menu del gruppo " + nomeGruppo + ", inserisci uno dei seguenti comandi da tastiera o invia dei messaggi");
        System.out.println("@partecipanti - Stampa partecipanti");
        System.out.println("@nome - Modifica il nome del gruppo (AMMINISTRATORE)");
        System.out.println("@aggiungi - Aggiungi un utente al gruppo (AMMINISTRATORE)");
        System.out.println("@rimuovi - Rimuovi un utente dal gruppo (AMMINISTRATORE)");
        System.out.println("@elimina - Elimina il gruppo (AMMINISTRATORE)");
        System.out.println("@exit - Torna al menu del gruppo");
        Scanner tastiera = new Scanner(System.in);
        while(true){
            String text = tastiera.nextLine();           
            switch (text) {
                case "@exit":
                    return;
                case "@partecipanti":
                    gruppo.mostraPartecipanti();
                    break;
                case "@rimuovi":
                    menuRimozione(gruppo);
                    break;
                case "@aggiungi":
                    menuAggiunta(gruppo);
                    break;
                case "@elimina":
                    gruppo.elimina();
                    //Distrugge l'oggetto gruppo
                    gruppo=null;
                    break;
                case "@nome":
                    menuNome(gruppo);
                    break;
                case "@comandi":
                    nomeGruppo = gruppo.getNome();
                    System.out.println("Menu del gruppo " + nomeGruppo + ", inserisci uno dei seguenti comandi da tastiera o invia dei messaggi");
                    System.out.println("@partecipanti - Stampa partecipanti");
                    System.out.println("@nome - Modifica il nome del gruppo (AMMINISTRATORE)");
                    System.out.println("@aggiungi - Aggiungi un utente al gruppo (AMMINISTRATORE)");
                    System.out.println("@rimuovi - Rimuovi un utente dal gruppo (AMMINISTRATORE)");
                    System.out.println("@exit - Torna al menu delle chat");
                    System.out.println("@comandi - Stampa la lista dei comandi");
                    break;
                default:
                    Messaggio msg = new Messaggio(nuovoUtente.getNickname(), text);
                    break;
            }
        }
    }catch(IOException | ClassNotFoundException e){
          System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
          return;
    }
        
    }
    
    public static void menuChatPrivata(String ID){
            //Crea una chat a partire dal suo ID, andando a scaricare le informazioni dal server
        try {
            ChatPrivata cp = new ChatPrivata(ID);
             //Thread per scaricare i messaggi
            ChatListener listener = new ChatListener(cp);       
            Thread T = new Thread(listener);  
            T.start();
            while(true){
                System.out.println("Menu della chat " + ID + ", inserisci uno dei seguenti comandi da tastiera o invia dei messaggi");
                System.out.println("@partecipanti - Stampa partecipanti");
                System.out.println("@cancella - Cancella chat");
                System.out.println("@exit - Torna al menu della chat");

                Scanner tastiera = new Scanner(System.in);

                String text = tastiera.nextLine();
                Messaggio msg = new Messaggio(nuovoUtente.getNickname(), text);
                switch (text) {
                    case "@exit":
                        return;
                    case "@partecipanti":
                        cp.mostraPartecipanti();
                        break;
                    case "@elimina":
                        cp.elimina();
                        //Distrugge l'oggetto chat Privata
                        cp=null;
                        break;
                    default:
                        break;
                    }
            }
    }catch(IOException | ClassNotFoundException e){
          System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
          return;
    }
    }
     
    
    public static void menuCreaGruppo(){
        System.out.println("");
        System.out.println("Menu Creazione Gruppo");
        System.out.println("");
        System.out.println("Inserisci il nome del gruppo: (Inserire 0 per tornare indietro) :");
        Scanner tastiera = new Scanner(System.in);
        String nome = tastiera.nextLine();
        if (nome.equals("0"))
            return;
        System.out.println("Inserire i nickname dei partecipanti separati da una virgola");
        String partecipanti= tastiera.nextLine();
        //Rimuove eventuali spazi tra i nickname
        partecipanti=partecipanti.replaceAll("\\s+","");
        ArrayList<String> nicknamePartecipanti = new ArrayList<>(Arrays.asList( partecipanti.split(",")));
        //Aggiunge l'utente corrente
        nicknamePartecipanti.add(0,nuovoUtente.getNickname()); 
        try {
            //Indica al server l'operazione di creazione gruppo con amministratore l'utente attuale
            outputStream.writeObject("g/"+nome +"/"+nuovoUtente.getNickname());
            //Invia la lista dei partecipanti
            outputStream.writeObject(nicknamePartecipanti);
            
            //Risposta del server
            String risposta= (String) inputStream.readObject();
            //Preleva i pezzi della risposta
            String [] comando=risposta.split("/");
            //Se il gruppo è stato inserito
            if(comando[0].equals("OK")){
                //Prende l'id del gruppo creato
                String id= comando[1];
                Gruppo nuovoGruppo= new Gruppo(nuovoUtente, nome, id);
                System.out.println("\033[1;32m" + "Gruppo creato correttamente" + "\033[0m");  
                return;
             }
                //Messaggio di errore (Errore connessione o utente non esiste)
            else{
                System.out.println("\033[1;31m" + risposta + "\033[0m");
                System.out.println("");
                return;
            }
            
            
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        } catch (ClassNotFoundException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        }
    }
    
    
     public static void menuCreaChat(){
        System.out.println("");
        System.out.println("Menu Creazione Chat");
        System.out.println("");
        Scanner tastiera= new Scanner(System.in);
        System.out.println("Inserire il nickname dell'altro partecipante");    
        String partecipante= tastiera.nextLine();
        try {
            //Indica al server l'operazione di creazione chat con l'altro utente e l'utente attuale come membri
            outputStream.writeObject("c/"+nuovoUtente.getNickname() +"/"+partecipante);
            
            //Risposta del server
            String risposta= (String) inputStream.readObject();
            //Preleva i pezzi della risposta
            String [] comando=risposta.split("/");
            //Se il gruppo è stato inserito
            if(comando[0].equals("OK")){
                //Prende l'id del gruppo creato
                String id= comando[1];
                ChatPrivata Cp= new ChatPrivata(id);
                System.out.println("\033[1;32m" + "Chat creata correttamente" + "\033[0m");  
                return;
             }
                //Messaggio di errore (Errore connessione o utente non esiste)
            else{
                System.out.println("\033[1;31m" + risposta + "\033[0m");
                System.out.println("");
                return;
            }
            
            
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        } catch (ClassNotFoundException ex) {
            System.out.println("\033[1;31m"+ "Errore nella connessione al server" + "\033[0m");
            return;
        }
    }
    
    
    public static void menuUtente(){
        System.out.println("Benvenuto "+ nuovoUtente.getNickname());
        System.out.println("ID Utente: " + nuovoUtente.getID());
        System.out.println("");
         while (true) {            

            System.out.println("Che operazione vuoi eseguire?");
            System.out.println("");
            System.out.println("1) Gestisci le tue chat");
            System.out.println("2) Crea una nuova chat");
            System.out.println("3) Crea un nuovo gruppo");
            System.out.println("4) Indietro");
            System.out.println("");
            Scanner tastiera= new Scanner(System.in);
            switch (tastiera.nextInt()) {
                case 1:
                        menuChats();
                        break;
                case 2:
                        menuCreaChat();
                        break;
                case 3: 
                        menuCreaGruppo();
                        break;
                case 4:
                        return;
              
            }
        
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
    
    
    public static ObjectOutputStream getOutputStream(){
        return outputStream;
    }
    
    public static ObjectInputStream getInputStream(){
        return inputStream;
    }
    
}
