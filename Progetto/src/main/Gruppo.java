/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author giovanni
 */
public class Gruppo extends Chat{
    private Utente amministratore;
    private String nome;
    
    public Gruppo(Utente nuovoAmministratore, String nuovoNome, int ID){
        super(ID);
        amministratore = nuovoAmministratore;
        nome = nuovoNome;
    }
    
    public String getAmministratore(){
        return amministratore.getNickname();
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setAmministratore(Utente u){
        amministratore = u;
    }
    
    public void setNome(String nuovoNome){
        nome= nuovoNome;
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
    
    
    public void aggiungiUtente(Utente amministratore, Utente nuovoUtente) throws NotAdministratorException{
        if(amministratore.getID()==this.amministratore.getID()){
            super.aggiungiUtente(nuovoUtente);
            //Aggiungi sul databse
        }
        else{
            throw new NotAdministratorException();
        }
    }
}
