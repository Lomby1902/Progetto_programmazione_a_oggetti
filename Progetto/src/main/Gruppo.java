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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanni
 */
public class Gruppo extends Chat{
    private String amministratore;
    private String nome;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public Gruppo(Utente nuovoAmministratore, String nuovoNome, String ID){
        super(ID);
        output= Main.getOutputStream();
        input = Main.getInputStream();
        amministratore = nuovoAmministratore.getNickname();
        nome = nuovoNome;
    }
    
    
    public Gruppo(String ID) throws IOException, ClassNotFoundException{
        super(ID);
        
            output= Main.getOutputStream();
            input = Main.getInputStream();
            //Indica al server di voler ottenere informazioni sul gruppo
            output.writeObject("i/Gruppo/"+ID);
            ArrayList<String> informazioni= (ArrayList<String>) input.readObject();
            nome=informazioni.get(0);
            amministratore=informazioni.get(1);
            //Aggiunge i vari utenti scaricati dal database
            for(int i=2;i<informazioni.size();i++){
                aggiungiUtente(informazioni.get(i));
            }
        
    }
    
    
    public void eliminaPartecipante(String Utente,String nickname) throws NotAdministratorException, IOException{
     
            if(!(Utente.equals(amministratore)))
                throw new NotAdministratorException();
            else{
                if(!(getPartecipanti().contains(nickname))){
                    System.out.println("\033[1;31m" + "L'utente inserito non esiste oppure non fa parte del gruppo" + "\033[0m");
                }
                else{
                    //Indica l'operazione di manage e poi la rimozione nello specifico
                    output.writeObject("m/r/" + getID() + "/" + nickname);
                    super.eliminaPartecipante(nickname);
                }                
            }
    }
    
    public void aggiungiPartecipante(String utente, String nickname) throws NotAdministratorException, IOException{
        if(!(utente.equals(amministratore))){
            throw new NotAdministratorException();
        }else{
            if(getPartecipanti().contains(nickname)){
                System.out.println("\033[1;31m" + "L'utente e' gia' presente nel gruppo" + "\033[0m");                
            }
            else{
                //Indica l'operazione di merge e poi l'aggiunta nello specifico
                output.writeObject("m/a/" + getID() + "/" + nickname);
                super.aggiungiUtente(nickname);
            }
            
        }
    }
    
    public void InviaMessaggio(Messaggio msg){
        
    }
    
    
    
    public String getNome(){
        return nome;
    }
    
    
    public void setNome(String utente, String nuovoNome) throws NotAdministratorException, IOException{
        if(!(utente.equals(amministratore))){
            throw new NotAdministratorException();
        }else{
            if(this.nome.equals(nuovoNome)){
                System.out.println("\033[1;31m" + "Nome uguale all'attuale" + "\033[0m");
            } else{
                this.nome = nuovoNome;
                output.writeObject("m/n/" + getID() + "/" + nuovoNome);
            }
        }     
    }
    
    
    public void mostraPartecipanti(){
        System.out.println("");
        System.out.println("Membri del gruppo: ");
        System.out.println("");
        ArrayList<String> Partecipanti = getPartecipanti();
        for(int i=0;i<Partecipanti.size();i++){
            System.out.print("\033[1;32m" + Partecipanti.get(i) + "\033[0m");
            //Se l'utente è amministratore
            if(Partecipanti.get(i).equals(amministratore)){
                System.out.println("(Amministratore)");
            }
            else
                System.out.println("");
                System.out.println("");
        }
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
