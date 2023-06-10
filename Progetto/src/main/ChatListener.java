/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author matxd
 */
public class ChatListener implements Runnable{
    
    private Gruppo gruppo;
    private ChatPrivata chatPrivata;
    private String Utente;
    private  boolean exit = false;
    
    public ChatListener(Object temp, String utente){
        if((temp instanceof Gruppo)){
            gruppo = (Gruppo) temp;
            Utente=utente;
            chatPrivata=null;
        }else{
            gruppo=null;
            chatPrivata = (ChatPrivata) temp;
            Utente=utente;
        }
    } 

     public void stop() {
         exit = true;
    }
    
    @Override
    public void run() {
        while(true){
            while(!exit){
                if (gruppo != null){     
                        gruppo.MostraMessaggi(Utente);                
                }else{        
                        chatPrivata.MostraMessaggi(Utente);
                }
          }
        }
        
    }
    
    
}
