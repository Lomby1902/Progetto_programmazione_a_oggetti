/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanni
 */
public class ChatPrivata extends Chat{
   
    
    public ChatPrivata(String ID) throws IOException, ClassNotFoundException{
        super(ID);
        //Indica al server di voler ottenere informazioni sulla chat
        output.writeObject("i/ChatPrivata/" + ID);
            ArrayList<String> informazioni = (ArrayList<String>) input.readObject();
            //Aggiunge i due utenti scaricati dal database
            for(int i = 0; i < informazioni.size(); i++){
                aggiungiUtente(informazioni.get(i));
            }
    }
    
        @Override
        public void mostraPartecipanti(){
            System.out.println("");
            System.out.println("Membri della chat: ");
            System.out.println("");
            ArrayList<String> Partecipanti = getPartecipanti();
            for(int i = 0; i < Partecipanti.size(); i++){
                System.out.println("\033[1;32m" + Partecipanti.get(i) + "\033[0m");
                System.out.println("");
            }
        }
        
        public void elimina() throws IOException{
            output.writeObject("d/ChatPrivata/" + getID());
            System.out.println("\033[1;32m" + "Chat eliminata correttamente" +"\033[0m");
            System.out.println("");
        }
    
   public void MostraMessaggi(String Utente){
        try {
            int temp = aggiornaMessaggi("c");
            for (int i = getNumeroMessaggi() - temp; i < getNumeroMessaggi(); i++){
                  int lunghezzaMessaggio=getTestoMessaggio(i).length();
                if(getNicknameMittente(i).equals(Utente)){
                    System.out.println("");
                    System.out.print("                      ");
                    for(int k = 0; k < lunghezzaMessaggio + 4; k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.print("                      ");
                    System.out.println(getTestoMessaggio(i));
                    System.out.print("                      ");
                    System.out.println("\u001B[1m" + getTimeMessaggio(i) + "\u001B[0m");
                    System.out.print("                      ");
                    for(int k = 0; k < lunghezzaMessaggio + 4; k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.println("");
                }else{
                    System.out.println("");
                    for(int k = 0; k < lunghezzaMessaggio + 4; k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.println(getTestoMessaggio(i));
                    System.out.println("\u001B[1m" + getTimeMessaggio(i) + "\u001B[0m");
                    for(int k = 0; k < lunghezzaMessaggio + 4; k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.println("");
                }
            }
        } catch (IOException ex) {
            System.out.println("\033[1;31m"+ "Errore" + "\033[0m");
        } catch (ClassNotFoundException ex) {
             System.out.println("\033[1;31m"+ "Errore" + "\033[0m");
        }
    }
 
    public void inviaMessaggio(Messaggio nuovoMessaggio) throws IOException {
        output.writeObject("w/ChatPrivata/" + getID());
        output.writeObject(nuovoMessaggio);
    }
}
