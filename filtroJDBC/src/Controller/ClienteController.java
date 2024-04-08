package Controller;

import Entity.Cliente;
import Entity.Producto;
import Entity.Tienda;
import Model.ClienteModel;
import Model.TiendaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ClienteController {


    public static void update(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Cliente clienteSeleccionado = (Cliente) JOptionPane.showInputDialog(null,
                "Seleccione un cliente para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        clienteSeleccionado.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo cliente", clienteSeleccionado.getNombre()));
        clienteSeleccionado.setApellido(JOptionPane.showInputDialog(null, "Ingrese el apellido del nuevo cliente", clienteSeleccionado.getApellido()));
        clienteSeleccionado.setEmail(JOptionPane.showInputDialog(null, "Ingrese el apellido del nuevo cliente", clienteSeleccionado.getEmail()));

        instanceModel().update(clienteSeleccionado);


    }

    public static void delete(){

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(null,
                "Seleccione un cliente para eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objCliente);
    }
    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo cliente");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del nuevo cliente");
        String email = JOptionPane.showInputDialog("Ingrese el email del nuevo cliente");

        instanceModel().insert(new Cliente(nombre, apellido, email));
    }

    public static void getAll(){

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registro \n";

        for (Object temp: list){
            Cliente objCliente = (Cliente) temp;
            listString += objCliente.toString() + "\n";
        }
        return listString;
    }


    public static ClienteModel instanceModel(){
        return new ClienteModel();
    }
}
