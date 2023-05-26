/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;

/**
 *
 * @author matxd
 */
public class Utente {
    private int ID;
    private String nickname;
    private String password;
    private boolean stato;

    public Utente(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    

    public static void main(String[] args) throws IOException{
        String serverAddress = "localhost";
        int serverPort = 9090;
        Socket s = new Socket(serverAddress, serverPort);
        PrintWriter output = new PrintWriter (s.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg = "";
        msg = input.readLine();
        System.out.println(msg);
        output.close();
        input.close();
        s.close();
    }
    
    
}
