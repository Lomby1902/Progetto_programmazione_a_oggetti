/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanni
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db= new Database();
        Utente giovanni = new Utente("Giovanni", "Password");
        ChatPrivata cp=null;
        try {
            cp = giovanni.creaChatPrivata();
        } catch (SQLException ex) {
            System.out.println("Errore nella creazione della Chat");
        }
       
        /*
        Utente matteo = new Utente("Matteo", "Password");
        giovanni.aggiungiUtenteChat(matteo,cp);
       
        Messaggio nuovoMessaggio = new Messaggio(giovanni,"Ciao come stai ?");
        giovanni.scriviMessaggioChat(nuovoMessaggio, cp);
        Messaggio secondoMessaggio= new Messaggio(matteo, "Tutto bene, tu ?");
        matteo.scriviMessaggioChat(secondoMessaggio, cp);
        cp.MostraMessaggi();
        cp.MostraPartecipanti();
*/
    }
    
}
