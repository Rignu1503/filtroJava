package Entity;

public class Compra {

    private int id_compra;

    private int id_cliente;

    private int id_producto;

    private String fecha_compra;

    private int cantidad;

    private Cliente objCliente;

    private Producto objProducto;


    public Compra() {
    }

    public Compra(int id_cliente, int id_producto, String fecha_compra, int cantidad, Cliente objCliente, Producto objProducto) {
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.fecha_compra = fecha_compra;
        this.cantidad = cantidad;
        this.objCliente = objCliente;
        this.objProducto = objProducto;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    @Override
    public String toString() {
        return

                "id_cliente=" + this.objCliente.getNombre() + '\n' +
                "id_producto=" + this.objProducto.getNombre() + '\n' +
                "fecha_compra='" + fecha_compra + '\n' +
                "cantidad=" + cantidad + '\n';

    }
}
