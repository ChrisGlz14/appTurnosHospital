package logicaApp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement; 
import java.util.Scanner;

public class mainParaTestearBD {

    // Instancia de conexión para usar en todos los metodos
    private static Connection conexion = null;

    public static void main(String[] args) {

        conexion = conexionSQLite.conectar();
        Scanner sc = new Scanner(System.in);

        System.out.println("Menu de opciones:");
        System.out.println("1. Agregar Medico");
        System.out.println("2. Agregar paciente");
        System.out.println("");
        System.out.println("4. Salir");
        System.out.print("Elija una opcion: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                agregarMedico();
                break;
            case 2:
                agregarPaciente();
                break;
            default:
                System.out.println("Opcion invalida ingrese nuevamente");
        }

        sc.close();
    }
    
    
//Metodo de agregar 
    public static void agregarMedico() {
        if (conexion != null) {
            String sql = "INSERT INTO medico(idMatricula, nombre, horaFichaje) VALUES(?,?,?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, 123);   // matricula
                ps.setString(2, "Dr. Gonzalez");  // nombre
                ps.setString(3, "2025-08-28 16:40"); // hora
                ps.executeUpdate();
                System.out.println("Medico insertado correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexion a la base de datos");
        }
    }

    // metodo test para agregar un paciente
    
    public static void agregarPaciente() //Creo la funcion
    {
      if(conexion != null) {//Ya tengo la conexion separada en una variable en el main, entonces la puedo usar
            String sql = "INSERT INTO paciente(idPaciente,horaLlegada,turno_ID,nombre_Paciente) VALUES(?,?,?,?)";
            try(PreparedStatement ps = conexion.prepareStatement(sql)){ //prepara y reutiliza sentencias SQL y se pone en el try para evitar poner el close
                //Relleno los datos para prueba
                ps.setInt(1, 2);
                ps.setString(2, "2025-08-15 09:25:00"); //hora llegada sera un DATETIME que devulve un timeString "YYYY-MM-DD HH:MM:SS"
                ps.setInt(3, 2);
                ps.setString(4,"Giordi");
                ps.executeUpdate();         //Ahora ejecutamos la llamada 
                System.out.println("Paciente insertado correctamente");
            } catch (SQLException e) {
                e.printStackTrace(); // es una excepcion mas detallada que muestra el nombre y la descripcion de la excepcion
            }
      } else {
          System.out.println("No hay conexion a la base de datos");
      }
    }  
}