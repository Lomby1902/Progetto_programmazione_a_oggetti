/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;
import java.util.ArrayList;


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
              /*
               Statement statement = databaseConnection.createStatement();
               String sqlString ="INSERT INTO Corsi (Nome) Values('Sensori') ";
               statement.executeUpdate(sqlString);
             
               while(rs.next()){
                   System.out.println(rs.getString(2));
               }
                */
    }catch(ClassNotFoundException cnfe){
        System.err.println(cnfe);
}
    catch(SQLException sqle){
        System.err.println(sqle);
    }
    }
    
    public static int inserisciChatPrivata() throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString1 ="INSERT INTO ChatPrivate(ID) VALUES(null) "; 
        statement.executeUpdate(sqlString1);
        System.out.println("Inserito");
        String sqlString2 ="SELECT max(ID) FROM ChatPrivate ";
        ResultSet result =statement.executeQuery(sqlString2);
        int val=-1;
        while(result.next()){
                  val=result.getInt(1);
               }
        return val;
    }
}
