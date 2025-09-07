package logicaApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logicaApp.conexionSQLite;
import interfazGrafica.ventanaLogin;
import javax.naming.spi.DirStateFactory;
import interfazGrafica.InterfazMedico;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class metodosDB {

   
    public static void agregarMedico(String[] args) {
    Connection conexion = conexionSQLite.conectar();
    
    
    // metodo INSERT (AGREGAR)
    //INSERT INTO medico(idMatricula,nombre,horaFichaje)VALUES(44784,'Dr Leonor','2023-05-08 15:25'); Para agregar 1 medico, seguir esta estructura

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
    //Metodo SELECT (para login)
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
    
    //Metodo SELECT (para pacientes)
    
    public static DefaultTableModel cargaPacientes(){
        
       DefaultTableModel tablaPacientes = new DefaultTableModel();

       tablaPacientes.addColumn("idPaciente");
       tablaPacientes.addColumn("hora Llegada");
       tablaPacientes.addColumn("Turno");
       tablaPacientes.addColumn("Nombre");
       
        try (Connection conexionDB = conexionSQLite.conectar()) 
        {
          
            
            String sql = "select * from paciente";
            PreparedStatement ps = conexionDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            
            while(rs.next()) {
                Object[] fila ={
                    rs.getInt("idPaciente"),
                    rs.getString("horaLlegada"),
                    rs.getInt("turno_ID"),
                    rs.getString("nombre_Paciente")
                };
                tablaPacientes.addRow(fila);
            }
            
            
            
            
            
       } catch (SQLException e) {
             e.printStackTrace();
        } 
        
        JTable tabla = new JTable(tablaPacientes);
        JScrollPane scroll = new JScrollPane(tabla);
        return tablaPacientes;
        
    }
}