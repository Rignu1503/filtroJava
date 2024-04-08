package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Cliente;
import Entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();


        //2.covertir el objeto
        Cliente objCliente = (Cliente) obj;

        try {
            //3. Escribimos la snetencia sql
            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES (?, ?, ?)";

            /*
             * Preparamos el Statement
             * ademas de eso debemos agregar la propiedad RETURN_GENERATED_KEYS que haces que la sentencia sql los id por la base de datos
             * */

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignamos valores
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());


            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Obtener el resulto con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un registro
            while (objRest.next()) {
                //id porque es el nombre de la columna o seria 1 por el indice
                objCliente.setId_cliente(objRest.getInt(1));

            }

            JOptionPane.showMessageDialog(null, "Paciente agregado con éxito");


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCliente;

    }

    @Override
    public List<Object> findAll() {
        //1. Creamos una lista para guardar lo que nos manda la base de datos
        List<Object> listaCliente = new ArrayList<>();

        //2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query
            String sql = "SELECT * FROM cliente;";

            //4. Usamos el preparedStatament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutamos el query y tener el resultado // el executeQuery() es como el rayo en mysql
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras objResul tenga un siguiente elemento (Eso significa la expresión del while)
            while (objResult.next()) {

                //6.1 Creamos un nuevo Especialista
                Cliente objCliente = new Cliente();

                //6.2 Llenamos los elemento que están iterando
                //objTienda.getId_tienda(objResult.getInt("id_tienda"));
                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));
                //6.3 Agregamos a los especialista a la lista
                listaCliente.add(objCliente);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaCliente;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto
        Cliente objCliente = (Cliente) obj;

        //3. Variable para saber si se actualizó no el especialista
        boolean idUpdate = false;
        try{
            //4. Sentencia sql
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?  WHERE id_cliente = ?";

            //5. preparamos el statemt
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //6. Dar valores a los ?
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.setInt(4, objCliente.getId_cliente());
            //7. ejecutamos el query
            int rowAffected = objPrepare.executeUpdate();

            //si las columnas afectadas fueron maoyr a 0
            if (rowAffected > 0){
                idUpdate = true;
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente el Cliente");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. cerrar conexión
        ConfigDB.closeConnection();

        return idUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convertimos el objeto
        Cliente objCliente = (Cliente) obj;

        //2. Creamos una variable para saber is fue eliminado
        boolean isDeleted = false;

        //3. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribimos la sentencia SQL
            String sql = "DELETE FROM cliente where id_cliente = ?;";

            //5. Preparamos el Statament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //6. Asignamos valor al ?
            objPrepare.setInt(1, objCliente.getId_cliente());

            // 7. Ejecutamos el query
            //ExecuteUpdate nos dice cuantas filas fueron afectadas

            int totalAfecctedRows = objPrepare.executeUpdate();

            //8. codicional para saber si alguna fila fueron afectada significa que alguien fue eliminado
            if (totalAfecctedRows > 0) {
                //Reasignamos el valor
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se elimino el paciente con éxito");
            }else{
                JOptionPane.showMessageDialog(null, "No se puedo eliminar a ningún paciente");
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
