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
    
    public Gruppo(Utente nuovoAmministratore, String nuovoNome, String ID){
        super(ID);
        
        amministratore = nuovoAmministratore.getNickname();
        nome = nuovoNome;
    }
    
    
    public Gruppo(String ID) throws IOException, ClassNotFoundException{
        super(ID);
        
          
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
    
    
    @Override
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
    
    
    public void elimina(String utente) throws NotAdministratorException, IOException{
        if(!(utente.equals(amministratore))){
            throw new NotAdministratorException();
        }else{
            output.writeObject("d/Gruppo/"+getID());
            System.out.println("\033[1;32m" +"Gruppo eliminato correttamente" +"\033[0m");
            System.out.println("");
        }               
    }
    
    public void MostraMessaggi(String Utente){
        try {
            int temp = aggiornaMessaggi("g");
            for (int i = getNumeroMessaggi() - temp; i < getNumeroMessaggi(); i++){
                  int lunghezzaMessaggio=getTestoMessaggio(i).length();
                if(getNicknameMittente(i).equals(Utente)){
                  
                    System.out.println("");
                    System.out.print("                      ");
                    for(int k=0;k<lunghezzaMessaggio+4;k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.print("                      ");
                    System.out.println(getTestoMessaggio(i));
                    System.out.print("                      ");
                    System.out.println("\u001B[1m" + getTimeMessaggio(i) + "\u001B[0m");
                    System.out.print("                      ");
                    for(int k=0;k<lunghezzaMessaggio+4;k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.println("");
                }else{
                    System.out.println("");
                    for(int k=0;k<lunghezzaMessaggio+4;k++)
                        System.out.print("*");
                    System.out.println("");
                    System.out.println("\u001B[1m" + getNicknameMittente(i) +"\u001B[0m");
                    System.out.println(getTestoMessaggio(i));
                    System.out.println("\u001B[1m" + getTimeMessaggio(i) + "\u001B[0m");
                    for(int k=0;k<lunghezzaMessaggio+4;k++)
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
        
        output.writeObject("w/Gruppo/"+getID());
        output.writeObject(nuovoMessaggio);
        
    }
    
    
   
}
