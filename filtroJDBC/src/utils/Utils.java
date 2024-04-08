package utils;

import java.util.List;

public class Utils {

    public static<T> T[] listToArray(List<T> list){

        //Crar un arrerglo de objets del tamaño de la lista
        T[] array = (T[]) new Object[list.size()];

        int i = 0;

        for(T iterador : list){
            array[i++] = iterador;
        }
        return array;
    }
}
