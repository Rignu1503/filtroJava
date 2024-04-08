package Controller;

import Entity.Tienda;
import Model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class TiendaController {

    public static void getAll(){

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registro";

        for (Object temp: list){
            Tienda objPaciente = (Tienda) temp;
            listString += objPaciente.toString() + "\n";
        }
        return listString;
    }


    public static TiendaModel instanceModel(){
        return new TiendaModel();
    }
}
