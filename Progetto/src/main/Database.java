/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;


/**
 *
 * @author giovanni
 */
public class Database {
    public static void main(String[] args) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
               Connection databaseConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/prova","root","");
               Statement statement = databaseConnection.createStatement();
               String sqlString ="INSERT INTO Corsi (Nome) Values('Sensori') ";
               statement.executeUpdate(sqlString);
               /*
             
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
}
