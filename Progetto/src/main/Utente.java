/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utente {
    private int ID;
    private String nickname;
    private String password;
    private boolean stato;
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    public Utente(int ID, String nickname, String password) {
        this.ID = ID;
        this.nickname = nickname;
        this.password = password;
        outputStream= Main.getOutputStream();
        inputStream= Main.getInputStream();
    }

    public Utente(int ID, String nickname) {
        this.ID = ID;
        this.nickname = nickname;
    }
    
    /**
    * Questo metodo crea una chat privata ed 
    * inserisce già l'utente che la crea.
    * Restituisce l'oggetto ChatPrivata
    */
   
    
    /**
     * Questo metodo aggiunge un nuovo utente alla chat privata cp
     * @param nuovoUtente
     * @param cp 
     * 
     */
    /*
   
    
    
    
    
    /**
     * Questo metodo scrive un messaggio m nella chat privata cp
     * @param m
     * @param cp 
     */
    public void scriviMessaggioChat(Messaggio m, ChatPrivata cp){
        cp.aggiungiMessaggio(m);
         //Inserisci il messaggio sul database
    }
    
   public void creaGruppo(String nome,ArrayList<String> nicknamePartecipanti){
       try {
            //Indica al server l'operazione di creazione gruppo con amministratore l'utente attuale
            outputStream.writeObject("g/"+nome +"/"+getNickname());
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
                Gruppo nuovoGruppo= new Gruppo(this, nome, id);
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
    
   public void creaChat(String partecipante){
       try {
            //Indica al server l'operazione di creazione chat con l'altro utente e l'utente attuale come membri
            outputStream.writeObject("c/"+getNickname() +"/"+partecipante);
            
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
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    

     
    
   
    
}
