import Controller.ClienteController;
import Controller.CompraController;
import Controller.ProductoController;
import Controller.TiendaController;
import Database.ConfigDB;
import Entity.Cliente;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. listar tienda
                    2. Prodcutos
                    3. Cliente
                    4. Comprar
                    """);
            switch (option){
                case "1":
                    TiendaController.getAll();
                    break;
                case"2":
                    option2 = JOptionPane.showInputDialog("""
                           1. listar producto
                           2. Agregar producto
                           3. Actualizar producto
                           4. eliminar producto
                           """);
                    switch (option2){
                        case "1":
                            ProductoController.getAll();
                            break;
                        case "2":
                            ProductoController.insert();
                            break;
                        case "3":
                            ProductoController.update();
                            break;
                        case "4":
                            ProductoController.delete();
                            break;

                    }
                    break;
                case "3":
                    option2 = JOptionPane.showInputDialog("""
                           1. listar Cliente
                           2. Agregar Cliente
                           3. Actualizar Cliente
                           4. eliminar Cliente
                           """);
                    switch (option2){
                        case "1":
                            ClienteController.getAll();
                            break;
                        case "2":
                            ClienteController.insert();
                            break;
                        case "3":
                            ClienteController.update();
                            break;
                        case "4":
                            ClienteController.delete();
                            break;


                    }
                    break;
                case "4":
                    option2 = JOptionPane.showInputDialog("""
                           1. listar Compras
                           2. Agregar Compras
                           3. Actualizar Compras
                           4. eliminar Compras
                           """);
                    switch (option2){
                        case "1":
                            CompraController.getAll();
                            break;
                        case "2":
                            CompraController.insert();
                            break;
                        case "3":
                            CompraController.update();
                            break;
                        case "4":
                            CompraController.delete();
                            break;


                    }
            }

        }while (!option.equals("5"));
    }
}