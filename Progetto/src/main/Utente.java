/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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

    public ChatPrivata creaChatPrivata(){
        ChatPrivata nuovaChatPrivata = new ChatPrivata();
        try {
            nuovaChatPrivata.aggiungiPartecipanteChat(this);
        } catch (LimitNumberException e) {
            System.out.println(e.getMessage());
        }
        return nuovaChatPrivata;
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
    
    
    
}
