package Entity;

public class Cliente {

    private int id_cliente;
    private String nombre;
    private String apellido;
    private String email;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "id_cliente=" + id_cliente + '\n' +
                ", nombre='" + nombre + '\n' +
                ", apellido='" + apellido + '\n' +
                ", email='" + email + '\n';

    }
}
