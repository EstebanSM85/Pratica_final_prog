package tienda.negocio;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import tienda.modelo.Producto;

public class GestionProducto {
    // Lista para almacenar objetos Producto en una lista llamada productos
    private List<Producto> productos;

    // Constructor para inicializar la lista uso un ArrayList porque necesito que sea dinamica
    public GestionProducto() {
        productos = new ArrayList<>();
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        if (producto != null) { //si producto esta correcto lo añado a la lista 'producto'
            productos.add(producto);
            System.out.println("\nProducto agregado: " + producto.getNombre()); //Mensaje de confirmación
        } else {
            System.err.println("\nEl producto no es válido."); //Mensaje de error
        }
    }

    // Método para listar todos los productos
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.err.println("\nNo hay productos registrados.");
        } else {
            System.out.println("\nLista de productos:"); //Muestro la lista de los productos
            for (Producto producto : productos) { // Recorro la lista
                System.out.println(producto.detalle_producto()); // Imprimo los detalles de cada uno 
                System.out.println(); // dejo espacio entre cada uno para que se vean
            }
        }
    }
    
      

    // Método para buscar un producto por nombre
    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) { // Hago la comparacion de esta manera evito que haga caso de si se escribe con mayusculas o no
                return producto;
            }
        }
        System.err.println("\nProducto no disponible"); // Mensaje si no lo encuentra
        return null; // Si no se encuentra el producto
    }

    // Método para eliminar un producto por nombre
    public boolean eliminarProducto(String nombre) {
        Producto EliminarProducto = buscarProducto(nombre); //uso el método buscarProducto para buscarlo y asignarlo a la variable
        if (EliminarProducto != null) { //si la variable no esta vacia lo elimino
            productos.remove(EliminarProducto); // Con .remove borro un objetode la lista(el asignado como eliminarProducto)
            System.out.println("\nProducto eliminado: " + nombre); //Mensaje de confirmación
            return true;
        } else {
            System.err.println("\nProducto no encontrado: " + nombre);// Mensaje de error
            return false;
        }
    }
    
    public void guardarProductos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.txt"))) {
            for (Producto producto : productos) {
                out.writeObject(producto); // Serializamos cada objeto Producto
            }
            System.out.println("Productos guardados.");
        } catch (IOException e) {
            System.err.println("Error al guardar los productos: " + e.getMessage());
        }
    }
    
    public void cargarProductos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.txt"))) {
            Producto producto;
            while (true) {
                producto = (Producto) in.readObject(); // Deserializamos cada objeto Producto
                productos.add(producto); // Lo añadimos a la lista
            }
        } catch (EOFException e) {
            // Se espera esta excepción para terminar de leer el archivo
            System.out.println("Productos cargados desde el archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los productos: " + e.getMessage());
        }
    }
}
