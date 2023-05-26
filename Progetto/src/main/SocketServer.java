/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import java.net.*;

  
/**
 *
 * @author matxd
 */
public class SocketServer {
    
    public void SocketServer(){
        
    }
    
    
    
    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(9091);
        System.out.println("Server is running");
        
        try {
            while (true) {                
                Socket socket = listener.accept();
                try{
                    PrintWriter out= new PrintWriter(socket.getOutputStream(), true);
                    out.println("Ciao");
                }
                finally{
                    socket.close();
                }
            }
        }
        finally{
            listener.close();
        }
    }
}
