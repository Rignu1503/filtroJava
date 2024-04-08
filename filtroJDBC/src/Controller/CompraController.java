package Controller;

import Entity.Cliente;
import Entity.Compra;
import Entity.Producto;
import Model.CompraModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CompraController {


    public static void update(){

        Object[] optionsCompra = Utils.listToArray(instanceModel().findAll());

        Compra objCompra = (Compra) JOptionPane.showInputDialog(null,
                "Seleccione una compra para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCompra,
                optionsCompra[0]
        );

        Object[] opcionesClientes = Utils.listToArray(ClienteController.instanceModel().findAll());
        Object[] opcionesProductos = Utils.listToArray(ProductoController.instanceModel().findAll());

        objCompra.setObjCliente((Cliente)JOptionPane.showInputDialog(null,
                "Seleccione un cliente al que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesClientes,
                opcionesClientes[0]

        ));

        objCompra.setId_cliente(objCompra.getObjCliente().getId_cliente());

        objCompra.setObjProducto((Producto)JOptionPane.showInputDialog(null,
                "Seleccione el producto actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesProductos,
                opcionesProductos[0]

        ) );

        objCompra.setId_producto(objCompra.getObjProducto().getId_prodcuto());

        objCompra.setFecha_compra(JOptionPane.showInputDialog("Ingrese la nueva fecha de la compra YYYY-MM-DD"));
        objCompra.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad")));

        instanceModel().update(objCompra);


    }



    public static void delete(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Compra objCompra = (Compra) JOptionPane.showInputDialog(null,
                "Seleccione una compra para eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objCompra);

    }

    public static void getAll(){

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registro \n";

        for (Object temp: list){
            Compra objCompra = (Compra) temp;
            listString += objCompra.toString() + "\n";
        }
        return listString;
    }




    public static void insert(){
        Object[] optionesCliente = Utils.listToArray(ClienteController.instanceModel().findAll());
        Object[] optionesProducto = Utils.listToArray(ProductoController.instanceModel().findAll());

        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(null,
                "Seleccione un cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionesCliente,
                optionesCliente[0]
        );


        Producto objProducto = (Producto) JOptionPane.showInputDialog(null,
                "Seleccione un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionesProducto,
                optionesProducto[0]
        );

        String fechaCompra = JOptionPane.showInputDialog("Ingrese la fecha de la cita YYYY-MM-DD");
        String cantidad = JOptionPane.showInputDialog("Ingrese la cantidad de producto");

        int cantidadConvertido =  Integer.parseInt(cantidad);

        if (objProducto.getStock() > cantidadConvertido){

            instanceModel().insert(new Compra(objCliente.getId_cliente(), objProducto.getId_prodcuto(), fechaCompra, cantidadConvertido, objCliente, objProducto));

            String productoComprado = objProducto.getNombre();
            int Precio = (int) objProducto.getPrecio();

            int precioTotal = (int) (Precio + (Precio * 0.19));

            JOptionPane.showMessageDialog(null,"__-Factura-___\n \n" +
                    "Producto: "+ productoComprado + "       precio"+Precio +"\n"+
                    "\n Informacion de la tienda \n" +
                    "\n Nombre: " + objProducto.getObjTienda().getNombre() + "       "+
                    "Ubicacion: " + objProducto.getObjTienda().getUbicacion() +
                    "\n Informacion del cliente \n" +
                    "Nombre completo" + objCliente.getNombre()+ " " + objCliente.getApellido()+
                    "     " + "email" + objCliente.getEmail() + "\n" +
                    "Precio Total" + precioTotal

            );

        }else{

            JOptionPane.showMessageDialog(null, "No hay suficiente stock");
        }
    }

    public static CompraModel instanceModel(){
        return new CompraModel();
    }

}
