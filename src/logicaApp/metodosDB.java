package logicaApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logicaApp.conexionSQLite;
import interfazGrafica.ventanaLogin;
import javax.naming.spi.DirStateFactory;

public class metodosDB {

   
    public static void agregarMedico(String[] args) {
    Connection conexion = conexionSQLite.conectar();
    
    
    // metodo INSERT (AGREGAR)
        if(conexion != null){
            String sql = "INSERT INTO medico(idMatricula, nombre, horaFichaje) VALUES(?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, 123487);                 // idMatricula
            ps.setString(2, "Dra. Aixa");        // nombre
            ps.setString(3, "2025-08-28 16:40"); // horaFichaje
            ps.executeUpdate();
            System.out.println("Médico insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
       
  }
    //Metodo SELECT (Consulta)
    public static boolean loginMatricula(int matricula){
       Connection conexion = conexionSQLite.conectar();
       String sql = "SELECT * FROM medico WHERE idMatricula = ?";
       
        try(PreparedStatement ps = conexion.prepareStatement(sql)) 
        {
            ps.setInt(1, matricula);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                System.out.println("Login Exitoso, Ingreso dr:" + rs.getString("nombre"));
                return true;
            } else {
                System.out.println("Login fallido, Intente Nuevamente");
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
   }
}