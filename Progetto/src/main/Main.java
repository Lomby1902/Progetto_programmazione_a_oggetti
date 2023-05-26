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
        giovanni.aggiungiUtenteChat(matteo,cp);
       
        Messaggio nuovoMessaggio = new Messaggio(giovanni,"Ciao come stai ?");
        giovanni.scriviMessaggioChat(nuovoMessaggio, cp);
        Messaggio secondoMessaggio= new Messaggio(matteo, "Tutto bene, tu ?");
        matteo.scriviMessaggioChat(secondoMessaggio, cp);
        cp.MostraMessaggi();
        cp.MostraPartecipanti();
    }
    
}
