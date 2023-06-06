/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.sql.*;
import java.util.ArrayList;
import main.Chat;
import main.ChatPrivata;


/**
 *
 * @author giovanni
 */
public class Database {
    static Connection databaseConnection;
    
    
    public Database() {
            try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto","root","");
            }
            catch(ClassNotFoundException cnfe){
               System.out.println("\033[1;31m"+ cnfe + "\033[0m");
            }
            catch(SQLException sqle){
               System.out.println("\033[1;31m"+ sqle + "\033[0m");
   
            }
    }
    
    //Verifica se esiste l'utente e ne restituisce l'id, altrimenti 0
    public int getIdUtente(String nickname, String password) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString ="SELECT * FROM Utenti WHERE Nickname = '"+ nickname + "' AND Password='" + password +"'" ;
        ResultSet result =statement.executeQuery(sqlString);
        int id=0;
        while(result.next()) {
           id=result.getInt("ID");
        }
        statement.close();
        if(id >0)
            return id;
        
        return 0;
    }
    
    
    
    
    /**
     * Inserisce la chat privata nel database e ne restituisce l'id
     * @param Utente1
     * @return
     * @throws SQLException 
     */
    public int inserisciChatPrivata(String Utente1) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce la chat privata nella tabella delle chat private inserendo già il creatore della chat
        String sqlString1 ="INSERT INTO ChatPrivate(ID,Utente1,Utente2) VALUES(null, '"+Utente1 +"',null) "; 
        statement.executeUpdate(sqlString1);
        //Prende l'utlimo ID inserito e lo restituisce
        String sqlString2 ="SELECT max(ID) FROM ChatPrivate ";
        ResultSet result =statement.executeQuery(sqlString2);
        int val=-1;
        while(result.next()){
                  val=result.getInt(1);
               }
        String sqlString3 ="CREATE TABLE Privata" + val +"messaggi (time datetime NOT NULL, Mittente varchar(255), Testo varchar(255)) "; 
        statement.executeUpdate(sqlString3);
        statement.close();
        return val;
    }
    
    
    //Inserisce un nuovo utente nel database e ne restituisce l'ID
    public int inserisciUtente(String nickname, String password) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce la chat privata nella tabella delle chat private inserendo già il creatore della chat
        String sqlString ="INSERT INTO Utenti(ID, Nickname, Password) VALUES(null, '" + nickname +"','"+ password +"')"; 
        statement.executeUpdate(sqlString);
        String sqlString2 ="SELECT * FROM Utenti ";
        ResultSet result =statement.executeQuery(sqlString2);
        int id=0;
        while(result.next()){
                  id=result.getInt("ID");
               }
        statement.close();
        return id;
    }
    
    
    //Restituisce un array contente le informazioni sui gruppo o sulle chat private in cui si trova l'utente
    public ArrayList<String []> mostra(String tabella, String Nickname) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        ArrayList<String []> ritorno = new ArrayList<>();
        if(tabella.equals("ChatPrivate")){
            String sqlString ="SELECT * FROM ChatPrivate WHERE Utente1='"+ Nickname +"' OR Utente2= '"+ Nickname+ "'";
            ResultSet result =statement.executeQuery(sqlString);
             while(result.next()){    
                  String chat []= new String[3];
                  chat[0]=(Integer.toString(result.getInt("ID")));
                  chat[1]= result.getString("Utente1");
                  chat[2]= result.getString("Utente2");
                  ritorno.add(chat);
               }
            statement.close();
        }
        
        return ritorno;
    } 
    
    
}
