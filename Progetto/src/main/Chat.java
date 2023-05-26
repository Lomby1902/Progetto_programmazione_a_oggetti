/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author giovanni
 */
public abstract class Chat {
    private ArrayList<Utente> partecipanti;
    private ArrayList<Messaggio> messaggi;
    
    public Chat(){
        partecipanti= new ArrayList<Utente>(2);
        messaggi = new ArrayList<Messaggio> (10);
    }
    
    
    public Chat(Utente nuovoUtente, Messaggio nuovoMessaggio) {
        this.partecipanti.add(nuovoUtente);
        this.messaggi.add(nuovoMessaggio);
    }
    
    
    public void MostraPartecipanti(){
        for (int i=0;i<partecipanti.size();i++){
                System.out.println(partecipanti.get(i).getNickname());
        }
    }
    
    public void MostraMessaggi(){
        for (int i=0;i<messaggi.size();i++){
                System.out.println(messaggi.get(i).getMittente().getNickname() + " : ");
                System.out.println("\u001B[1m" + messaggi.get(i).getTesto() + "\u001B[0m");
                System.out.println(" ");
                System.out.println(" ");
        }
    }
}
