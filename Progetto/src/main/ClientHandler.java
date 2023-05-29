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
public class ClientHandler implements Runnable {
    
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            output.println("Ciao");
            output.close();
            input.close();
            clientSocket.close();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    

}
