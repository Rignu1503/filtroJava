package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Variable que va a contener el estado de la conexión
    static Connection objConnection = null;

    //MEtodo para abrir la conexion entre Java y la base de datos

    public static Connection openConnection(){
        try {
            // class,forname permite obtener o implementar el drive
            Class.forName("com.mysql.cj.jdbc.Driver");

            //cremaos variables con nuestras credenciales de la base de datos
            String url = "jdbc:mysql://localhost:3306/filtro";
            String user = "root";
            String password = "Rig0bert0.";

            //Establecemos la conexion
            objConnection = DriverManager.getConnection(url,user,password);
            System.out.println("La conexion a la base de datos correctamente");


            //Error de drive
        }catch (ClassNotFoundException e){
            System.out.println("Error >> Drive no Instalado");

            //Error con la base de datos
        }catch (SQLException e){
            System.out.println("Error >> No se puedo establecer una conexion con la DDBB");
        }

        return objConnection;
    }

    public static void closeConnection(){
        try {
            //SI hay una conexion activa la cerramos
            if (objConnection != null) objConnection.close();
            //Si no envie el error
        }catch (SQLException e){
            System.out.println("ERROR:  " + e.getMessage());
        }
    }

}
