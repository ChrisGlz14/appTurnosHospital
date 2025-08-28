
package logicaApp;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement; 

public class mainParaTestearBD {

 
    public static void main(String[] args) {
    Connection conexion = conexionSQLite.conectar();
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
}