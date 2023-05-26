/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;

public class Utente {
    private int ID;
    private String nickname;
    private String password;
    private boolean stato;

    public Utente(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    
    /**
    * Questo metodo crea una chat privata ed 
    * inserisce già l'utente che la crea.
    * Restituisce l'oggetto ChatPrivata
    */
    public ChatPrivata creaChatPrivata(){
        ChatPrivata nuovaChatPrivata = new ChatPrivata();
        nuovaChatPrivata.aggiungiUtente(this);
        return nuovaChatPrivata;
    }
    
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
             System.out.println(e.getMessage());
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
    

     
    
    /*
    public static void main(String[] args) throws IOException{
        String serverAddress = "localhost";
        int serverPort = 9091;
        Socket s = new Socket(serverAddress, serverPort);
        PrintWriter output = new PrintWriter (s.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg = "";
        msg = input.readLine();
        System.out.println(msg);
        output.close();
        input.close();
        s.close();
    }
    */
    
}
