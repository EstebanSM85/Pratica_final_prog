package practica;

import java.util.ArrayList;
import java.util.List;

public class GestionProducto {
    // Lista para almacenar productos
    private List<Producto> productos;

    // Constructor para inicializar la lista
    public GestionProducto() {
        productos = new ArrayList<>();
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
            System.out.println("Producto agregado: " + producto.getNombre());
        } else {
            System.out.println("El producto no es válido.");
        }
    }

    // Método para listar todos los productos
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : productos) {
                System.out.println(producto.detalle_producto());
                System.out.println("-----------------------");
            }
        }
    }

    // Método para buscar un producto por nombre
    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto
    }

    // Método para eliminar un producto por nombre
    public boolean eliminarProducto(String nombre) {
        Producto EliminarProducto = buscarProducto(nombre);
        if (EliminarProducto != null) {
            productos.remove(EliminarProducto);
            System.out.println("Producto eliminado: " + nombre);
            return true;
        } else {
            System.out.println("Producto no encontrado: " + nombre);
            return false;
        }
    }
}
