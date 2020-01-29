package app;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Database.FileFormat;

public class AccessBean {
  
    String parola;
    Connection connection;
    static String dbPath;
    static String databaseURL;
    String[] elenco;

    String significato;

    public AccessBean(String databaseURL){
        
    }

    public AccessBean(){
        try{	
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch(Exception e){
            System.out.println("ops.");
            e.printStackTrace();
        }  
        String tomcatBase = System.getProperty("catalina.base");
        File dbFile = new File(tomcatBase + "/webapps/WebContent/WEB-INF/database.accdb");
        dbPath = dbFile.getAbsolutePath().toString();
        databaseURL = "jdbc:ucanaccess://" + dbPath;
        if(!dbFile.exists()){
            try (Database db = DatabaseBuilder.create(FileFormat.V2010, new File(dbPath))) {
                System.out.println("The database file has been created.");
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
            createDataBase();
            popolateDataBase();
        }
    }

    public String getDbFilePath(){
        return dbPath;
    }

    public void setParola(String p){
        parola = p;
    }

    public String getParola(){
        return parola;
    }

    public String[] getElenco(){
        String[] ret = {""};
        try {
            StringBuilder ulList = new StringBuilder();
            ulList.append("<select name=\"parola\">");
            connection = DriverManager.getConnection(databaseURL);
            int conta=0;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM Dizionario");
            while (resultSet.next()) {
                conta = resultSet.getInt(1);
            }

            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery("SELECT * FROM Dizionario ");

            ret = new String[conta];
            int i = 0;
            while(rs.next()) {
                String p = rs.getString("parola");
                ret[i]= p;
                i++;
            }
            
            return ret;

          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public String getParole(){
        try {
            StringBuilder ulList = new StringBuilder();
            ulList.append("<select name=\"parola\">");
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
            System.out.println("databaseURL");
            System.out.println(databaseURL);
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

    public static void aggiungiParola(String parola, String significato){
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            Statement st = connection.createStatement(); 
            String s = "INSERT INTO Dizionario (parola, descr) VALUES ('" + parola + "','" + significato + "');";
            st.execute(s);
            System.out.println("Record inseriti con successo");
        } catch (SQLException ex) {
            System.out.println("ops. popolateDataBase");
            ex.printStackTrace();
        }    
    }

    private static void createDataBase(){
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
            System.out.println("ops. createDataBase");
            ex.printStackTrace();
        }
    }

    private static void popolateDataBase(){
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            Statement st = connection.createStatement(); 
            String s = "INSERT INTO Dizionario (parola, descr) VALUES ('gatto','miao');";
            st.execute(s);
            System.out.println("Record inseriti con successo");
        } catch (SQLException ex) {
            System.out.println("ops. popolateDataBase");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
            
        try{	
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch(Exception e){
            System.out.println("ops1.");
            e.printStackTrace();
        }

        dbPath = new File("WebContent/WEB-INF/database.accdb").getAbsolutePath().toString();
        databaseURL = "jdbc:ucanaccess://" + dbPath;

        try (Database db = DatabaseBuilder.create(FileFormat.V2010, new File(dbPath))) {
            System.out.println("The database file has been created.");
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }

        createDataBase();
        popolateDataBase();

        System.out.println(new AccessBean(databaseURL).getElenco()[0]);
    }   

}