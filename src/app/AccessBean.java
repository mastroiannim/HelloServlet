package app;

import java.sql.*;

public class AccessBean {
  
    String parola;
    Connection connection;
    String databaseURL;

    String significato;

    public AccessBean(){
        try{	
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch(Exception e){
            System.out.println("ops.");
            e.printStackTrace();
        }
        databaseURL = "jdbc:ucanaccess://C:/Users/mastroiannim/Desktop/mydb.accdb";   
    }

    public void setParola(String p){
        parola = p;
    }

    public String getParole(){
        try {
            StringBuilder ulList = new StringBuilder();
            ulList.append("<select name=\"parole\">");
            connection = DriverManager.getConnection(databaseURL);
            Statement st = connection.createStatement(); 
            ResultSet result = st.executeQuery("SELECT * FROM Dizionario ");
            while (result.next()) {
                String p = result.getString("parola");
                ulList.append("<option value=\"");
                ulList.append(p);
                ulList.append("\">");
                ulList.append(p);
                ulList.append("</option>");
            }
            ulList.append("</select>");
            System.out.println("return ulList: " + ulList);
            return ulList.toString();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "ERR:parola non settata..";
        } 
    }

    public String getSignificato(){
        try {
            connection = DriverManager.getConnection(databaseURL);
            Statement st = connection.createStatement(); 
            String s = new StringBuilder()
            .append("SELECT * FROM Dizionario ")
                .append("WHERE parola='")
                .append(parola)
                .append("'")
            .toString();
            ResultSet result = st.executeQuery(s);
            while (result.next()) {
                significato = result.getString("descr");
            }
            System.out.println("return significato: " + significato);
            return significato;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "ERR:parola non settata..";
        } 
    }
    public static void main(String[] args) {
            
        try{	
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch(Exception e){
            System.out.println("ops.");
            e.printStackTrace();
        }
        String databaseURL = "jdbc:ucanaccess://C:/Users/mastroiannim/Desktop/mydb.accdb";
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            Statement st = connection.createStatement(); 
            //st.execute("CREATE TABLE example1 (id COUNTER PRIMARY KEY,descr text(400), number numeric(12,3), date0 datetime) ")  
            String s = new StringBuilder()
            .append("CREATE TABLE Dizionario (")
                .append("id COUNTER PRIMARY KEY,")
                .append("parola text(400),")
                .append("descr text(400),")
                .append("number numeric(12,3),")
                .append("date datetime")
            .append(")")
            .toString();
            st.execute(s);
            System.out.println("Tabella creata con successo");
        } catch (SQLException ex) {
            System.out.println("ops.");
            ex.printStackTrace();
        }
    }   

}