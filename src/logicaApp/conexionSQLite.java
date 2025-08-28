package logicaApp;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionSQLite {
private static final String URL = "jdbc:sqlite:C:/Users/chris/Documents/SQLite/turnosHospital.db";

        
        public static Connection conectar(){
            Connection conexion = null;
            try  {
            //Conexion base datos   
            conexion = DriverManager.getConnection(URL);
            System.out.println("conexion lista a SQLite");
        
        } catch (SQLException e) {
            System.out.println("Error al conectar " + e.getMessage());
            
        }
            return conexion;
    }  
        
}
