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
    
    public void start(){
        try{
            ServerSocket listener = new ServerSocket(9090);
            System.out.println("Server is running");
            while(true){
                Socket socket = listener.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Multithread
                Thread clientThread = new Thread(new ClientHandler(socket));
                clientThread.start();
                output.close();
                input.close();
                socket.close();
            }
        }catch(IOException e) {
                e.printStackTrace();
            }
    }
    
    public static void main(String[] args) throws IOException{
        SocketServer server = new SocketServer();
        server.start();
    }
}
