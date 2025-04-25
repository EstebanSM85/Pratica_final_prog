package practica;

import java.text.SimpleDateFormat;
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

    public void setFechaPedido(String fechaPedido)throws java.text.ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Formato de la fecha esperada
        dateFormat.setLenient(false); // Validación estricta para evitar fechas irreales
        try {
            Date fecha = dateFormat.parse(fechaPedido); // Intenta convertir la cadena a un objeto Date
            this.fechaPedido = fecha; // Asigna la fecha si es válida
            System.out.println("Fecha de pedido establecida correctamente: " + fechaPedido); // Confirmación de éxito
        } catch (Exception e) {
            System.err.println("Fecha inválida. No se realizó ningún cambio."); // Mensaje de error en caso de excepción
        }
    }

    // Método para calcular el total del pedido
    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {//Recorre la lista de productos
            if ((producto.getCaducidad().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24) <= 5) {
                total += producto.getPrecio() * 0.7; // Usa el precio con descuento si está en oferta
            } else {
                total += producto.getPrecio(); // Usa el precio normal
            }
        }
        return total;
    }


    // Método para mostrar detalles del pedido
    public String mostrarPedido() {
    	String productosNombres = ""; //Atributo donde voy ha hacer una cadena String con los productos
        for (Producto producto : productos) { // recorremos los productos
            productosNombres += "- " + producto.getNombre() + "\n"; // Concatenamos los detalles de cada producto en la lista
        }

        return "Código Pedido: " + codigoPedido +
               "\nCliente: " + cliente.getNombre() + " " + cliente.getApellidos() + //Muestra el nombre y apellidos en la misma linea
               "\nFecha Pedido: " + getFechaPedido() +
               "\nProductos: " +"\n"+ productosNombres+
               "\nTotal: " + calcularTotal() + "€";
    }

}