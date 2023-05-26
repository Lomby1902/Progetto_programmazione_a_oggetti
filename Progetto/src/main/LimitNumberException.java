/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author giovanni
 */
public class LimitNumberException extends Exception{
    public LimitNumberException(){
        super("In una chat privata non possono esserci più di 2 partecipanti");
    }
}
