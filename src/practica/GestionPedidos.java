package practica;

import java.util.ArrayList;
import java.util.List;

public class GestionPedidos {
    private List<Pedido> pedidos; // Lista para almacenar objetos Pedido en una lista llamada pedidos

    public GestionPedidos() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {  //si el pedido esta correcto lo añado a la lista 'pedidos'
            pedidos.add(pedido);
            System.out.println("Pedido agregado: Código " + pedido.getCodigoPedido()); //Mensaje de confirmación
        } else {
            System.out.println("El pedido no es válido."); //Mensaje de error
        }
    }

    public void listarPedidos() { //Muestro todos los pedidos, si no Muestro el mensaje.
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("Lista de pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido.mostrarPedido()); //Muestro cada pedido y pongo una separaración para que se vea más claro
                System.out.println(" ");
            }
        }
    }

    public Pedido buscarPedido(int codigo) { //Cada pedido tiene su codigo unico asignado al crearlo
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigoPedido() == codigo) { // cuando el codigo proporcionado coincide, devuelve pedido
                return pedido;
            }
        }
        return null; // Si no se encuentra el pedido
    }

    public boolean eliminarPedido(int codigo) {
        Pedido eliminarPedido = buscarPedido(codigo);//uso el método buscarPedido para buscarlo y asignarlo a la variable
        if (eliminarPedido != null) {//si la variable no esta vacia lo elimino
            pedidos.remove(eliminarPedido);// Con .remove borro un objeto de la lista(el asignado como eliminarPedido)
            System.out.println("Pedido eliminado: Código " + codigo); //Mensaje de confirmación
            return true;
        } else {
            System.out.println("Pedido no encontrado: Código " + codigo);
            return false; // Mensaje de error
        }
    }
}