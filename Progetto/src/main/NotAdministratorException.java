/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author giovanni
 */
public class NotAdministratorException extends Exception{
    
    public NotAdministratorException(){
        super("Non sei l'amministratore di questo gruppo");
    }
}
