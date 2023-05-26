/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.time.Instant;
/**
 *
 * @author matxd
 */
public class Messaggio {
    private Instant time;
    private Utente mittente;
    private String testo;
    
    public Messaggio(Utente mittente, String testo) {
        this.time = Instant.now();
        this.mittente = mittente;
        this.testo = testo;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Utente getMittente() {
        return mittente;
    }

    public void setMittente(Utente mittente) {
        this.mittente = mittente;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    
    
}
