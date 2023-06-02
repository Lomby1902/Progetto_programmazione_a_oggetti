/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import main.Database;

  
/**
 *
 * @author matxd
 */
public class Server {
    
    public void Server(){
        
    }
    
    
    
    public static void main(String[] args) throws IOException{
        Database db= new Database();
        ServerSocket listener = new ServerSocket(9091);
        System.out.println("Server is running");
        
        try {
            while (true) {                
                Socket socket = listener.accept();
                GestoreClient client = new GestoreClient(socket, db);
                Thread T1 = new Thread(client);
                T1.start();
            }
        }finally{
            listener.close();
        }
        
    }
}
