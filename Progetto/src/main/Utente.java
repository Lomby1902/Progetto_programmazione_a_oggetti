/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import server.Database;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class Utente {
    private int ID;
    private String nickname;
    private String password;
    private boolean stato;

    public Utente(int ID, String nickname, String password) {
        this.ID= ID;
        this.nickname = nickname;
        this.password = password;
    }

    public Utente(int ID, String nickname) {
        this.ID= ID;
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
    public void aggiungiUtenteChat(Utente nuovoUtente, ChatPrivata cp ){
         try {
             if(cp.getNumeroPartecipanti()==2)
                 throw new LimitNumberException();
             cp.aggiungiUtente(nuovoUtente);
             //aggiungi utente nel database
         } catch (LimitNumberException e) {
             System.out.println("\033[1;31m"+e.getMessage()+ "\033[0m");
         }
    }
    
    /**
     * Questo metodo scrive un messaggio m nella chat privata cp
     * @param m
     * @param cp 
     */
    public void scriviMessaggioChat(Messaggio m, ChatPrivata cp){
        cp.aggiungiMessaggio(m);
         //Inserisci il messaggio sul database
    }
    
    
     public void aggiungiUtenteGruppo(Utente nuovoUtente, Gruppo g ){
        g.aggiungiUtente(nuovoUtente);
        //aggiungi utente nel database
         
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
