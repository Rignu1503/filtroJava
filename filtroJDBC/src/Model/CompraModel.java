package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Cliente;
import Entity.Compra;
import Entity.Producto;
import Entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();


        //2.covertir el objeto
        Compra objCompra = (Compra) obj;

        try {
            String sql = "INSERT INTO compra (id_cliente, id_producto, fecha_compra, cantidad) VALUES (?, ?, ?, ?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1, objCompra.getId_cliente());
            objPrepare.setInt(2, objCompra.getId_producto());
            objPrepare.setDate(3, Date.valueOf(objCompra.getFecha_compra()));
            objPrepare.setInt(4, objCompra.getCantidad());


            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Obtener el resulto con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un registro
            while (objRest.next()) {
                //id porque es el nombre de la columna o seria 1 por el indice
                objCompra.setId_compra(objRest.getInt(1));

            }

            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error >" + e.getMessage());
        }



        return objCompra;
    }

    @Override
    public List<Object> findAll() {
        //1. Creamos una lista para guardar lo que nos manda la base de datos
        List<Object> listaCita = new ArrayList<>();

        //2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query
            String sql =
                    "  SELECT * FROM compra \n" +
                            "  INNER JOIN cliente ON cliente.id_cliente = compra.id_cliente\n" +
                            "INNER JOIN producto ON producto.id_producto = compra.id_producto\n" +
                            "INNER JOIN tienda ON tienda.id_tienda  = producto.id_tienda";

            //4. Usamos el preparedStatament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutamos el query y tener el resultado // el executeQuery() es como el rayo en mysql
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras objResul tenga un siguiente elemento (Eso significa la expresión del while)
            while (objResult.next()) {

                //6.1 Creamos un nuevo Especialista
                Compra objCompra = new Compra();
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                objCompra.setId_compra(objResult.getInt("compra.id_compra"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));
                objCompra.setFecha_compra(objResult.getString("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));

                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getInt("producto.precio"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));


                //Llenamos las entidades
                objCompra.setObjCliente(objCliente);
                objCompra.setObjProducto(objProducto);
                objProducto.setObjTienda(objTienda);

                //6.3 Agregamos a los especialista a la lista

                listaCita.add(objCompra);


            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaCita;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto
        Compra objCompra = (Compra) obj;

        //3. Variable para saber si se actualizó no el especialista
        boolean idUpdate = false;
        try{
            //4. Sentencia sql
            String sql = "UPDATE compra SET id_cliente = ?, id_producto  = ? , fecha_compra  = ?, cantidad = ? WHERE id_compra = ?";

            //5. preparamos el statemt
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valores a los ?
            objPrepare.setInt(1, objCompra.getId_cliente());
            objPrepare.setInt(2, objCompra.getId_producto());
            objPrepare.setDate(3, Date.valueOf(objCompra.getFecha_compra()));
            objPrepare.setInt(4, objCompra.getCantidad());
            objPrepare.setInt(5, objCompra.getId_compra());

            //7. ejecutamos el query
            int rowAffected = objPrepare.executeUpdate();

            //si las columnas afectadas fueron maoyr a 0
            if (rowAffected > 0){
                idUpdate = true;
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente la compra");
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
        Compra objCita = (Compra) obj;

        //2. Creamos una variable para saber is fue eliminado
        boolean isDeleted = false;

        //3. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribimos la sentencia SQL
            String sql = "DELETE FROM compra where id_compra = ?;";

            //5. Preparamos el Statament
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //6. Asignamos valor al ?
            objPrepare.setInt(1, objCita.getId_compra());

            // 7. Ejecutamos el query
            //ExecuteUpdate nos dice cuantas filas fueron afectadas

            int totalAfecctedRows = objPrepare.executeUpdate();

            //8. codicional para saber si alguna fila fueron afectada significa que alguien fue eliminado
            if (totalAfecctedRows > 0) {
                //Reasignamos el valor
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se elimino la compra con exito con éxito");
            }else{
                JOptionPane.showMessageDialog(null, "No se puedo eliminar a ninguna compra");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
