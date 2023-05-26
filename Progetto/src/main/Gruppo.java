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
    
    public Gruppo(Utente nuovoAmministratore, String nuovoNome){
        super();
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
    
}
