/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanni
 */
public class Main {
    private static Database db;
    
    
    
    
    //Menu principale
    public static int menu1(){
        System.out.println("Che operazione vuoi eseguire?");
        System.out.println("");
        System.out.println("1) Login");
        System.out.println("2) Registrazione");
        System.out.println("3) Uscire");
        System.out.println("");
        Scanner tastiera= new Scanner(System.in);
        return tastiera.nextInt();
    }

    
    //Menu di login
    public static Utente menuLogin(){
       
        Scanner tastiera= new Scanner(System.in);
        System.out.println("Inserisci il tuo nickname (Inserire 0 per tornare indietro):");
        String nickname = tastiera.next();
        if(nickname.equals("0"))
               return null;
         System.out.println("Inserisci la password (Inserire 0 per tornare indietro):");
         String password = tastiera.next();
         if(password.equals("0"))
               return null;
         try {
                if(Database.esisteUtente(nickname,password)){
                   return new Utente(nickname, password);
                }
                else{
                    System.out.println("L'utente non esiste");
                    return null;
                }
        } 
        catch (SQLException ex) {
            System.out.println("Errore nella connessione al Database");
            return null;
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        db= new Database();
        Utente nuovoUtente;
        System.out.println("Benvenuto su JavaChat");
        switch (menu1()) {
            case 1:
                    nuovoUtente= menuLogin();
                    System.out.println("Benvenuto "+ nuovoUtente.getNickname());
                break;
            default:
                throw new AssertionError();
        }

        Utente giovanni = new Utente("Giovanni", "Password");
        ChatPrivata cp=null;
        
        //L'utente crea una chat privata
        try {
            cp = giovanni.creaChatPrivata();
            System.out.println("Chat creata correttamente");
        } catch (SQLException ex) {
            System.out.println("Errore nella creazione della Chat");
        }
       
        /*
        Utente matteo = new Utente("Matteo", "Password");
        giovanni.aggiungiUtenteChat(matteo,cp);
       
        Messaggio nuovoMessaggio = new Messaggio(giovanni,"Ciao come stai ?");
        giovanni.scriviMessaggioChat(nuovoMessaggio, cp);
        Messaggio secondoMessaggio= new Messaggio(matteo, "Tutto bene, tu ?");
        matteo.scriviMessaggioChat(secondoMessaggio, cp);
        cp.MostraMessaggi();
        cp.MostraPartecipanti();
*/
    }
    
}
