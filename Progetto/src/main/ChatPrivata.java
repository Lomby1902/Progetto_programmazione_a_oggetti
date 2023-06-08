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
public class ChatPrivata extends Chat{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public ChatPrivata(String ID) throws IOException, ClassNotFoundException{
        super(ID);
        output= Main.getOutputStream();
        input = Main.getInputStream();
        //Indica al server di voler ottenere informazioni sulla chat
        output.writeObject("i/ChatPrivata/"+ID);
            ArrayList<String> informazioni= (ArrayList<String>) input.readObject();
            //Aggiunge i due utenti scaricati dal database
            for(int i=0;i<informazioni.size();i++){
                aggiungiUtente(informazioni.get(i));
            }
    }
    
    
        @Override
        public void mostraPartecipanti(){
            System.out.println("");
            System.out.println("Membri della chat: ");
            System.out.println("");
            ArrayList<String> Partecipanti = getPartecipanti();
            for(int i=0;i<Partecipanti.size();i++){
                System.out.println("\033[1;32m" + Partecipanti.get(i) + "\033[0m");
                System.out.println("");
            }
        }
        
        public void elimina() throws IOException{
            output.writeObject("d/ChatPrivata/"+getID());
            System.out.println("\033[1;32m" +"Chat eliminata correttamente" +"\033[0m");
            System.out.println("");
        }
        
         
        
    @Override
    public void MostraMessaggi(){
        for (int i=0;i<getNumeroMessaggi();i++){
                System.out.println("\u001B[1m" + getTestoMessaggio(i) + "\u001B[0m");
                System.out.println(" ");
                System.out.println(" ");
        }
    }
  
}
