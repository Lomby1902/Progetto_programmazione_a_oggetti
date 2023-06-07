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
    
    Gruppo gruppo;
    ChatPrivata chatPrivata;
    
    public ChatListener(Object temp){
        if((temp instanceof Gruppo)){
            gruppo = (Gruppo) temp;
        }else{
            chatPrivata = (ChatPrivata) temp;
        }
    } 

    @Override
    public void run() {
        if (gruppo != null){
            while(true){
                gruppo.MostraMessaggi();
            }
        }else{
            while(true){
                chatPrivata.MostraMessaggi();
            }
        }
    }
    
    
}
