/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author giovanni
 */
public class Gruppo extends Chat{
    private String amministratore;
    private String nome;
    
    public Gruppo(Utente nuovoAmministratore, String nuovoNome, String ID){
        super(ID);
        amministratore = nuovoAmministratore.getNickname();
        nome = nuovoNome;
    }
    
    
    public Gruppo(String ID){
        super(ID);
        try{
            ObjectOutputStream output= Main.getOutputStream();
            ObjectInputStream input = Main.getInputStream();
            output.writeObject("m/Gruppo/"+ID);
            ArrayList<String> informazioni= (ArrayList<String>) input.readObject();
            nome=informazioni.get(0);
            amministratore=informazioni.get(1);
            //Aggiunge i vari utenti scaricati dal database
            for(int i=2;i<informazioni.size();i++){
                aggiungiUtente(informazioni.get(i));
            }
    }catch(IOException | ClassNotFoundException e){
        System.err.println("Errore nella connessione al gruppo");
    }
        
    }
    
    
    public void InviaMessaggio(Messaggio msg){
        
    }
    
    
    
    public String getNome(){
        return nome;
    }
    
   
    
    public void setNome(String nuovoNome){
        nome = nuovoNome;
    }
    
    @Override
    public void MostraMessaggi(){
        for (int i=0;i<getNumeroMessaggi();i++){
                System.out.println(getNicknameMittente(i) + " : ");
                System.out.println("\u001B[1m" + getTestoMessaggio(i) + "\u001B[0m");
                System.out.println(" ");
                System.out.println(" ");
        }
    }
    
    
   
}
