/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author matxd
 */
public class Messaggio implements Serializable{
    private Instant time;
    private String mittente;
    private String testo;
    
    public Messaggio(String nickname, String testo) {
        this.time = Instant.now();
        this.mittente = nickname;
        this.testo = testo;
    }
    
    public Messaggio(Instant time, String nickname, String testo) {
        this.time = time;
        this.mittente = nickname;
        this.testo = testo;
    }

    public String getTime() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
        return formatter.format(time);
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String nickname) {
        this.mittente = nickname;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    
    
}
