/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author giovanni
 */
public class ChatPrivata extends Chat{
    
    public ChatPrivata(){
        super();
    }
    
    
    
    @Override
    public void MostraMessaggi(){
        for (int i=0;i<getNumeroMessaggi();i++){
                System.out.println("\u001B[1m" + getTestoMessaggio(i) + "\u001B[0m");
                System.out.println(" ");
                System.out.println(" ");
        }
    }
    
     public void aggiungiPartecipanteChat(Utente nuovoUtente) throws LimitNumberException{
        if(getNumeroPartecipanti() ==2){
            throw new LimitNumberException();
        }
        else{
            aggiungiUtente(nuovoUtente);
            //Aggiungi utente nel database
        }
    }
}
