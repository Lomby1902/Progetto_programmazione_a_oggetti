/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author giovanni
 */
public abstract class Chat {
    private ArrayList<String> partecipanti;
    private ArrayList<Messaggio> messaggi;
    private String ID;
    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    
    public Chat(String ID){
        output= Main.getOutputStream();
        input = Main.getInputStream();
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
    
    public int aggiornaMessaggi(String type) throws IOException, ClassNotFoundException{
        //Serve per comunicare se sono arrivati nuovi messaggi
        int temp = 0;
        //serve per controllare se un messaggio è già presente nei messaggi salvati in locale in modo che non venga salvato lo stesso messaggio due volte
        boolean control = false;
        output.writeObject("a/" + ID + "/" + type);
        ArrayList<Messaggio> nuoviMessaggi = (ArrayList<Messaggio>) input.readObject();
        for(int i = 0; i < nuoviMessaggi.size(); i++){
            if (messaggi.isEmpty()){
                aggiungiMessaggio(nuoviMessaggi.get(i));
                temp++;
            }else{
                for(int j = 0; j < messaggi.size(); j++){
                    if (nuoviMessaggi.get(i).getTime().equals(messaggi.get(j).getTime())){
                        control = true;
                    }
                }
                if(control == false){
                    aggiungiMessaggio(nuoviMessaggi.get(i));
                    temp++;
                }
                control = false;
            }
            
            
        }
        return temp;
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
    
    public abstract void mostraPartecipanti();
    
    public abstract void MostraMessaggi();
}
