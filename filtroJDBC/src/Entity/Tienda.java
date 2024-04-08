package Entity;

public class Tienda {

    private int id_tienda;
    private String nombre;
    private String ubicacion;

    public Tienda() {
    }

    public Tienda(int id_tienda, String nombre, String ubicacion) {
        this.id_tienda = id_tienda;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return
                "id_tienda=" + id_tienda +  '\n' +
                ", nombre='" + nombre +  '\n' +
                ", apellido='" + ubicacion + '\n';

    }
}
