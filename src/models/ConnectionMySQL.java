
package models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    
    private String database_name = "ElCriollo";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "1234";
    private String driver = "com.mysql.cj.jdbc.Driver";
    Connection conn = null;
    
    public Connection getConnection(){
        try{
            //obtener valor del driver
            Class.forName(driver);
            //obtener coneccion
            conn = DriverManager.getConnection(url+database_name, user, password);
            System.out.println("se conecto a bd" +database_name );
            
        }catch(ClassNotFoundException e){
            System.err.println("ha ocurrido un ClassNotFoundException " + e.getMessage());
        }catch(SQLException e){
            System.err.println("ha ocurrido un SQLException " + e.getMessage());
            System.out.println("no se conecto a bd" +database_name);

        }
        return conn;
    }
    public static void main(String[]args){
        
        ConnectionMySQL conexion=new ConnectionMySQL();
        conexion.getConnection();
    }
            
}