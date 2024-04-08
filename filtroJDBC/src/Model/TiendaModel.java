package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Cliente;
import Entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public List<Object> findAll() {
        //1. Creamos una lista para guardar lo que nos manda la base de datos
        List<Object> listaTienda = new ArrayList<>();

        //2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query
            String sql = "SELECT * FROM tienda;";

            //4. Usamos el preparedStatament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutamos el query y tener el resultado // el executeQuery() es como el rayo en mysql
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras objResul tenga un siguiente elemento (Eso significa la expresión del while)
            while (objResult.next()) {

                //6.1 Creamos un nuevo Especialista
                Tienda objTienda = new Tienda();

                //6.2 Llenamos los elemento que están iterando
                //objTienda.getId_tienda(objResult.getInt("id_tienda"));
                objTienda.setId_tienda(objResult.getInt("id_tienda"));
                objTienda.setNombre(objResult.getString("nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                //6.3 Agregamos a los especialista a la lista
                listaTienda.add(objTienda);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaTienda;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
