package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Producto;
import Entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();


        //2.covertir el objeto
        Producto objProducto = (Producto) obj;

        try {
            String sql = "INSERT INTO producto (nombre, precio, id_tienda, stock) VALUES (?, ?, ?, ?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setFloat(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getId_tienda());
            objPrepare.setInt(4, objProducto.getStock());



            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Obtener el resulto con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un registro
            while (objRest.next()) {
                //id porque es el nombre de la columna o seria 1 por el indice
                objProducto.setId_prodcuto(objRest.getInt(1));

            }

            JOptionPane.showMessageDialog(null, "Producto insertado correctamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error >" + e.getMessage());
        }



        return objProducto;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaProducto = new ArrayList<>();

        //2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query
            String sql = " SELECT * FROM producto \n" +
                    " left JOIN tienda ON producto.id_tienda = tienda.id_tienda;\n";

            //4. Usamos el preparedStatament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutamos el query y tener el resultado // el executeQuery() es como el rayo en mysql
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras objResul tenga un siguiente elemento (Eso significa la expresión del while)
            while (objResult.next()) {

                //6.1 Creamos un nuevo Especialista
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                //6.2 Llenamos los elemento que están iterando
                objProducto.setId_prodcuto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getFloat("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));


                //Como el la entidad medico llamamos a la entidadespecialista debemos llenar tambien esa entidad
                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));
                //Lenamos la entidad Especialidad
                objProducto.setObjTienda(objTienda);
                //6.3 Agregamos a los especialista a la lista

                listaProducto.add(objProducto);


            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaProducto;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto
        Producto objProducto = (Producto) obj;

        //3. Variable para saber si se actualizó no el especialista
        boolean idUpdate = false;
        try{
            //4. Sentencia sql

            String sql = "UPDATE producto SET nombre = ?, precio = ?, id_tienda = ?, stock = ? WHERE = id_producto = ?;";

            //5. preparamos el statemt
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valores a los ?
            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setFloat(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());
            objPrepare.setInt(5, objProducto.getId_prodcuto());


            //7. ejecutamos el query
            int rowAffected = objPrepare.executeUpdate();

            //si las columnas afectadas fueron maoyr a 0
            if (rowAffected > 0){
                idUpdate = true;
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente el producto");
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
        Producto objProducto = (Producto) obj;

        //2. Creamos una variable para saber is fue eliminado
        boolean isDeleted = false;

        //3. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribimos la sentencia SQL
            String sql = "DELETE FROM producto where id_producto = ?;";

            //5. Preparamos el Statament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //6. Asignamos valor al ?
            objPrepare.setInt(1, objProducto.getId_prodcuto());

            // 7. Ejecutamos el query
            //ExecuteUpdate nos dice cuantas filas fueron afectadas

            int totalAfecctedRows = objPrepare.executeUpdate();

            //8. codicional para saber si alguna fila fueron afectada significa que alguien fue eliminado
            if (totalAfecctedRows > 0) {
                //Reasignamos el valor
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se elimino el producto con éxito");
            }else{
                JOptionPane.showMessageDialog(null, "No se puedo producto a ningún medico");
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
