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
    private String ID;
    
    public Chat(String ID){
        partecipanti = new ArrayList<String>(2);
        messaggi = new ArrayList<Messaggio> (10);
        this.ID = ID;
    }
    
    public void aggiungiUtente(String nuovoUtente){
        partecipanti.add(nuovoUtente);
    }
    
    public void aggiungiMessaggio(Messaggio nuovoMessaggio){
        messaggi.add(nuovoMessaggio);
    }
    
    public ArrayList<String> getPartecipanti(){
        return partecipanti;
    }
    
    public void eliminaPartecipante(String nickname){
        for(int i=0;i<partecipanti.size();i++){
            if(partecipanti.get(i).equals(nickname)){
                partecipanti.remove(i);
                break;
            }
        }
    }
    
    
    public int getNumeroPartecipanti(){
        return partecipanti.size();
    }
    
    public int getNumeroMessaggi(){
        return messaggi.size();
    }
    
    public String getID(){
        return ID;
    }
    public String getNicknameMittente(int indice){
        return messaggi.get(indice).getMittente();
    }
    
    public String getTestoMessaggio(int indice){
        return messaggi.get(indice).getTesto();
    }
    
    public abstract void MostraMessaggi();
}
