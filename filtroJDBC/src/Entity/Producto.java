package Entity;

public class Producto {

    private int id_prodcuto;

    private String nombre;

    private float precio;

    private int id_tienda;

    private int stock;
    private Tienda objTienda;


    public Producto() {
    }

    public Producto(String nombre, float precio, int id_tienda, int stock, Tienda objTienda) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_tienda = id_tienda;
        this.objTienda = objTienda;

    }

    public int getId_prodcuto() {
        return id_prodcuto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId_prodcuto(int id_prodcuto) {
        this.id_prodcuto = id_prodcuto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    @Override
    public String toString() {
        return
                "nombre: " + nombre + '\n' +
                "precio: " + precio + '\n' +
                "Stock: " + stock + '\n' +
                "objTienda: " + this.objTienda.getNombre();    }
}
