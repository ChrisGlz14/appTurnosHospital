package logicaApp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement; 
import java.util.Scanner;

public class mainParaTestearBD {

    // Variable de conexión para usar en todos los métodos
    private static Connection conexion = null;

    public static void main(String[] args) {

        conexion = conexionSQLite.conectar();
        Scanner sc = new Scanner(System.in);

        System.out.println("Menu de opciones:");
        System.out.println("1. Agregar paciente");
        System.out.println("2. Mostrar pacientes");
        System.out.println("3. Buscar paciente");
        System.out.println("4. Salir");
        System.out.print("Elija una opcion: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                agregarPaciente();
                break;
            default:
                System.out.println("Opcion invalida ingrese nuevamente");
        }

        sc.close();
    }
//Metodo de agregar 
    public static void agregarPaciente() {
        if (conexion != null) {
            String sql = "INSERT INTO medico(idMatricula, nombre, horaFichaje) VALUES(?,?,?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, 123);   // matricula
                ps.setString(2, "Dr. Gonzalez");  // nombre
                ps.setString(3, "2025-08-28 16:40"); // hora
                ps.executeUpdate();
                System.out.println("Médico insertado correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión a la base de datos.");
        }
    }

    // metodo test para agregar un paciente
    
    
    
    
}