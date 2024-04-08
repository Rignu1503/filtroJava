package Database;

import java.util.List;

public interface CRUD {

    //Una interfaz solo lleva el nombre del metodo
    //Se pone object para que reciba elemntos de todo tipos
    public Object insert(Object obj);

    //LIStar elementos de la base de datos
    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);
}
