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
    private ArrayList<String> partecipanti;
    private ArrayList<Messaggio> messaggi;
    private int ID;
    
    public Chat(int ID){
        partecipanti = new ArrayList<Utente>(2);
        messaggi = new ArrayList<Messaggio> (10);
        this.ID = ID;
    }
    
    public void aggiungiUtente(String nuovoUtente){
        partecipanti.add(nuovoUtente);
    }
    
    public void aggiungiMessaggio(Messaggio nuovoMessaggio){
        messaggi.add(nuovoMessaggio);
    }
    
    
    public void MostraPartecipanti(){
        for (int i = 0; i < partecipanti.size(); i++){
                System.out.println(partecipanti.get(i).getNickname());
        }
    }
    
    public int getNumeroPartecipanti(){
        return partecipanti.size();
    }
    
    public int getNumeroMessaggi(){
        return messaggi.size();
    }
    
    public String getNicknameMittente(int indice){
        return messaggi.get(indice).getMittente();
    }
    
    public String getTestoMessaggio(int indice){
        return messaggi.get(indice).getTesto();
    }
    
    public int getID(){
        return ID;
    }
    
    private void setID(int ID){
        this.ID = ID;
    }
    public abstract void MostraMessaggi();
}
