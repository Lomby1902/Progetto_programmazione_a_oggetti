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
    
    public ChatPrivata(String ID){
        super(ID);
    }
    
    
    
    @Override
    public void MostraMessaggi(){
        for (int i=0;i<getNumeroMessaggi();i++){
                System.out.println("\u001B[1m" + getTestoMessaggio(i) + "\u001B[0m");
                System.out.println(" ");
                System.out.println(" ");
        }
    }
  
}
