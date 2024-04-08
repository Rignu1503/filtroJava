package Controller;

import Entity.Producto;
import Entity.Tienda;
import Model.ProductoModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ProductoController {


    public static void update(){
        Object[] optionsProducto = Utils.listToArray(instanceModel().findAll());

        Producto objProducto = (Producto) JOptionPane.showInputDialog(null,
                "Seleccione un producto para eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsProducto,
                optionsProducto[0]
        );

        objProducto.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto"));
        objProducto.setPrecio(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo precio del producto")));

        objProducto.setId_tienda(objProducto.getObjTienda().getId_tienda());

        objProducto.setPrecio(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock del producto")));

        System.out.println(objProducto.toString());

        instanceModel().update(objProducto);

    }
    public static void delete(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Producto objProducto = (Producto) JOptionPane.showInputDialog(null,
                "Seleccione un producto para eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objProducto);
    }

    public static void getAll(){

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);




    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registro \n";

        for (Object temp: list){
            Producto objProducto = (Producto) temp;
            listString += objProducto.toString() + "\n";
        }
        return listString;
    }
    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
        String precio = JOptionPane.showInputDialog("Ingrese el precio del Producto");
        String stock = JOptionPane.showInputDialog( "Ingrese el stock del Producto");

        int stockCovertido = Integer.parseInt(stock);


        float precioConvertido = Float.parseFloat(precio);

        Object[] opcionesTienda = Utils.listToArray(TiendaController.instanceModel().findAll());

        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(null,
                "Seleccione una Tienda para agregar el producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesTienda,
                opcionesTienda[0]
        );

        instanceModel().insert(new Producto(nombre, precioConvertido, objTienda.getId_tienda(),  stockCovertido, objTienda));
    }

    public static ProductoModel instanceModel(){
        return new ProductoModel();
    }
}

