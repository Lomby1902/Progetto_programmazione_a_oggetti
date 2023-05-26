/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author giovanni
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Utente giovanni = new Utente("Giovanni", "Password");
       ChatPrivata cp = giovanni.creaChatPrivata();
       Utente matteo = new Utente("Matteo", "Password");
        
       try {
            cp.aggiungiPartecipanteChat(matteo);
        } catch (LimitNumberException e) {
            System.out.println(e.getMessage());
        }
       
        Messaggio nuovoMessaggio = new Messaggio(giovanni,"Ciao come stai ?");
        cp.aggiungiMessaggioChat(nuovoMessaggio);
        Messaggio secondoMessaggio= new Messaggio(matteo, "Tutto bene, tu ?");
        cp.aggiungiMessaggioChat(secondoMessaggio);
        cp.MostraMessaggi();
    }
    
}
