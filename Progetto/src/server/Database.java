/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.sql.*;
import java.util.ArrayList;
import main.Chat;
import main.ChatPrivata;
import main.Messaggio;
import java.time.Instant;


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
               System.out.println("\033[1;31m" + cnfe + "\033[0m");
            }
            catch(SQLException sqle){
               System.out.println("\033[1;31m" + sqle + "\033[0m");
   
            }
    }
    
    
    
    
    
    //Restituisce 0 se non trova il nickname
    public int getIdUtente(String nickname) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString = "SELECT * FROM Utenti WHERE Nickname = '" + nickname + "'";
        ResultSet result = statement.executeQuery(sqlString);
        int id = 0;
        while(result.next()) {
           id = result.getInt("ID");
        }
        if(id > 0) {
           return id;
        }
       
        return 0;
    }
    
    
    
    
    
    //Restituisce l'id di un utente registrato, altrimenti 0
    public int login(String nickname, String password) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString = "SELECT * FROM Utenti WHERE Nickname = '" + nickname + "' AND Password='" + password + "'" ;
        ResultSet result = statement.executeQuery(sqlString);
        int id = 0;
        while(result.next()) {
           id = result.getInt("ID");
        }
        statement.close();
        if(id > 0)
            return id;
        
        return 0;
    }
    
    
    //Inserisce un gruppo nel database e ne restituisce l'id
    public int inserisciGruppo(String Nome,String Amministratore, ArrayList<String> partecipanti) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce la chat privata nella tabella delle chat private inserendo già il creatore della chat
        String sqlString1 = "INSERT INTO Gruppi(ID,Nome,Amministratore) VALUES(null, '" + Nome + "','" + Amministratore + "')"; 
        statement.executeUpdate(sqlString1);
        //Prende l'utlimo ID inserito e lo restituisce
        String sqlString2 = "SELECT max(ID) FROM Gruppi ";
        ResultSet result = statement.executeQuery(sqlString2);
        int id = 0;
        while(result.next()){
                  id = result.getInt(1);
               }
        String sqlString3 = "CREATE TABLE Gruppo" + id + "Utenti (Nickname varchar(255), ID int(11)) "; 
        statement.executeUpdate(sqlString3);
        for(int i = 0; i < partecipanti.size(); i++){
            int idUtente = getIdUtente(partecipanti.get(i));
            statement.executeUpdate( "INSERT INTO Gruppo" + id + "Utenti(Nickname,ID) VALUES('" + partecipanti.get(i) + "','" + idUtente + "')");
            
        }
        String sqlString4 = "CREATE TABLE Gruppo" + id + "Messaggi (time datetime NOT NULL, Mittente varchar(255), Testo varchar(255))";
        statement.executeUpdate(sqlString4);
        statement.close();
        return id;
    }
    
    //Restituisce le informazioni del gruppo o sulla chat
    public ArrayList<String> getInfo(String id, String tabella) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        ArrayList<String> informazioni= new ArrayList<>();
       
            //Se sta richiedendo informazioni su un gruppo
            if(tabella.equals("Gruppo")){
            Statement statement2 = databaseConnection.createStatement();
            String sqlString = "SELECT * FROM Gruppi WHERE ID = '" + id + "'";
            ResultSet result = statement.executeQuery(sqlString);
            while(result.next()) {
               //La prima informazione è il nome del gruppo
               informazioni.add(result.getString("Nome"));
               //La seconda informazione è l'id dell'amministratore
               informazioni.add(result.getString("Amministratore"));
               //Le altre informazioni sono i nickaname dei partecipanti   
               String sqlString2 = "SELECT * FROM Gruppo"+id+"Utenti";
               ResultSet result2 = statement2.executeQuery(sqlString2);
               while(result2.next()){
                   informazioni.add(result2.getString("Nickname"));
               }

            }
            statement2.close();
            
        }
            else if(tabella.equals("ChatPrivata")){
                String sqlString = "SELECT * FROM ChatPrivate WHERE ID = '" + id + "'";
                ResultSet result = statement.executeQuery(sqlString);
                while(result.next()) {
                    informazioni.add(result.getString("Utente1"));
                    informazioni.add(result.getString("Utente2"));
                    
                }
            }
        
        statement.close();
        return informazioni;
    }
    
    
    
    
    
    
    
    //Rimuove un utente dal gruppo
    public void rimuoviUtente(String idGruppo, String nickname) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString = "DELETE FROM Gruppo"+idGruppo+"Utenti WHERE Nickname='"+ nickname+"'";
        statement.executeUpdate(sqlString);
        
    }
    
    //Aggiunge un utente dal gruppo
    public void aggiungiUtente(String idGruppo, String nickname) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        int idUtente = getIdUtente(nickname);
        String sqlString = "INSERT INTO Gruppo" + idGruppo + "Utenti(Nickname,ID) VALUES('" + nickname + "','" + idUtente + "')";
        statement.executeUpdate(sqlString);
    }
    
    //Modifica nome del gruppo
    public void modificaNome(String idGruppo, String nuovoNome) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString = "UPDATE Gruppi SET Nome = '" + nuovoNome + "' WHERE ID = '" + idGruppo + "'";
        statement.executeUpdate(sqlString);
        
    }
    
    
    
    
    
    
    
    
    /**
     * Inserisce la chat privata nel database e ne restituisce l'id
     * @param Utente1
     * @return
     * @throws SQLException 
     */
    public int inserisciChatPrivata(String Utente1, String Utente2) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce la chat privata nella tabella delle chat private inserendo già il creatore della chat
        String sqlString1 = "INSERT INTO ChatPrivate(ID,Utente1,Utente2) VALUES(null, '" + Utente1 + "','" + Utente2 + "')"; 
        statement.executeUpdate(sqlString1);
        //Prende l'utlimo ID inserito e lo restituisce
        String sqlString2 = "SELECT max(ID) FROM ChatPrivate ";
        ResultSet result = statement.executeQuery(sqlString2);
        int id = 0;
        while(result.next()){
                  id = result.getInt(1);
               }
        String sqlString3 = "CREATE TABLE Privata" + id + "Messaggi (time datetime NOT NULL, Mittente varchar(255), Testo varchar(255)) "; 
        statement.executeUpdate(sqlString3);
        statement.close();
        return id;
    }
    
    
    //Inserisce un nuovo utente nel database e ne restituisce l'ID
    public int inserisciUtente(String nickname, String password) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce l'utente
        String sqlString = "INSERT INTO Utenti(ID, Nickname, Password) VALUES(null, '" + nickname + "','" + password + "')"; 
        statement.executeUpdate(sqlString);
        String sqlString2 = "SELECT max(ID) FROM Utenti ";
        ResultSet result = statement.executeQuery(sqlString2);
        int id = 0;
        while(result.next()){
                  id = result.getInt(1);
               }
        statement.close();
        return id;
    }
    
    
    //Restituisce un array contente le informazioni sui gruppo o sulle chat private in cui si trova l'utente
    public ArrayList<String []> mostra(String tabella, String Nickname) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        ArrayList<String []> ritorno = new ArrayList<>();
        //Se la richiesta è di mostrare le chat
        if(tabella.equals("ChatPrivate")){
            String sqlString = "SELECT * FROM ChatPrivate WHERE Utente1='" + Nickname + "' OR Utente2= '" + Nickname + "'";
            ResultSet result = statement.executeQuery(sqlString);
            while(result.next()){    
                  String chat [] = new String[3];
                  chat[0] =(Integer.toString(result.getInt("ID")));
                  chat[1] = result.getString("Utente1");
                  chat[2] = result.getString("Utente2");
                  ritorno.add(chat);
            }
           
        }
        //Se la richiesta è di mostrare i gruppi
        else if(tabella.equals("Gruppi")){
            String sqlString = "SELECT * FROM Gruppi";
            Statement statement2 = databaseConnection.createStatement();
            ResultSet result = statement.executeQuery(sqlString);
             while(result.next()){    
                  int id = result.getInt("ID");
                  String nome = result.getString("Nome");
                  String sqlString2 = "SELECT * FROM Gruppo" + id + "Utenti WHERE Nickname='" + Nickname + "'";
                  ResultSet result2 = statement2.executeQuery(sqlString2);
                  if(result2.next()){
                    String gruppo[] = new String[2];
                    gruppo[0] = (Integer.toString(id));
                    gruppo[1] = nome;
                    ritorno.add(gruppo);
                  }

               }
           
        }
            
            
        
        return ritorno;
    } 
    
    
    public void elimina(String tabella, String ID) throws SQLException{
       Statement statement = databaseConnection.createStatement();
       //Se deve eliminare una chat Privata
       if(tabella.equals("ChatPrivata")){
        String sqlString="DELETE FROM ChatPrivate WHERE ID="+ ID;
        statement.executeUpdate(sqlString);
        String sqlString2= "DROP TABLE Privata"+ID+"Messaggi";
        statement.executeUpdate(sqlString2);
       }
       
       //Se deve eliminare un gruppo
       else if(tabella.equals("Gruppo")){
        String sqlString="DELETE FROM Gruppi WHERE ID="+ ID;
        statement.executeUpdate(sqlString);
        String sqlString2= "DROP TABLE Gruppo"+ID+"Utenti";
        statement.executeUpdate(sqlString2);
        String sqlString3= "DROP TABLE Gruppo"+ID+"Messaggi";
        statement.executeUpdate(sqlString3);
       }
       
       statement.close();
    }
    
    public ArrayList<Messaggio> aggiornaMessaggi(String ID, String type) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        ArrayList<Messaggio> ritorno = new ArrayList<>();
        if(type.equals("g")){
            String sqlString = "SELECT * FROM Gruppo" + ID + "Messaggi";
            ResultSet result = statement.executeQuery(sqlString);
            while(result.next()){
                Instant tempo = result.getTimestamp("time").toInstant();
                String mittente = result.getString("Mittente");
                String testo = result.getString("Testo");
                Messaggio msg = new Messaggio(tempo, mittente, testo);
                ritorno.add(msg);
            }
        }
        
        else if(type.equals("c")){
            String sqlString = "SELECT * FROM Privata" + ID + "Messaggi";
            ResultSet result = statement.executeQuery(sqlString);
            while(result.next()){
                Instant tempo = result.getTimestamp("time").toInstant();
                String mittente = result.getString("Mittente");
                String testo = result.getString("Testo");
                Messaggio msg = new Messaggio(tempo, mittente, testo);
                ritorno.add(msg);
            }
        }
        return ritorno;
    }
    
}
