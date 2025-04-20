package practica;

import java.util.Date;
import java.util.List;

public class Pedido {
    // Atributos de la clase Pedido
    private Cliente cliente;
    private List<Producto> productos;
    private Date fechaPedido;
    private int codigoPedido; // Contador único para pedidos
    private static int contadorCodigoPedido = 1; 

    // Constructor
    public Pedido(Cliente cliente, List<Producto> productos, Date fechaPedido) {
        this.cliente = cliente;
        this.productos = productos;
        this.fechaPedido = (fechaPedido != null) ? fechaPedido : new Date();
        this.codigoPedido = contadorCodigoPedido; // Código único para el pedido
        contadorCodigoPedido++; // Incrementar el contador para el próximo pedido
    }

    // Getters y Setters
    public int getCodigoPedido() {
        return codigoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = (fechaPedido != null) ? fechaPedido : new Date(); //si la fechaPedido esta vacio, asigno la fecha actual
    }

    // Método para calcular el total del pedido
    public double calcularTotal() {
        double total = 0;// se inicia a 0 para poder usarlo
        for (Producto producto : productos) { // recorre la lista productos
            total += producto.getPrecio(); // A total le suma el precio del producto 
        }
        return total; // cuando acaba devuelve la suma total
    }

    // Método para mostrar detalles del pedido
    public String mostrarPedido() {
        return "Código Pedido: " + codigoPedido +
               "\nCliente: " + cliente.getNombre() + " " + cliente.getApellidos() + //Muestra el nombre y apellidos en la misma linea
               "\nFecha Pedido: " + getFechaPedido() +
               "\nProductos: " + getProductos() +
               "\nTotal: " + calcularTotal() + "€";
    }

}