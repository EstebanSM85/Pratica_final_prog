package practica;

import java.util.ArrayList;
import java.util.List;
	
public class GestionCliente {
	 // Lista para almacenar objetos Cliente en una lista llamada clientes
	private List<Cliente> clientes;
	
	// Constructor para inicializar la lista uso un ArrayList porque necesito que sea dinamica 
	public GestionCliente() {	     
		this.clientes = new ArrayList<>();
	}
	
	
	// Método para añadir un cliente   
	public void agregarCliente(Cliente cliente) {
		if (cliente != null) { //si el cliente esta correcto lo añado a la lista 'clientes'
			clientes.add(cliente);
			System.out.println("Cliente agregado: " + cliente.getNombre()); //Mensaje de confirmación
	    } else {
	        System.out.println("El cliente no es válido."); //Mensaje de error
	    }
	}

	// Método para listar todos los clientes
	public void listarClientes() {
		if (clientes.isEmpty()) {  //Muestro todos los clientes, si no hay saco el mensaje.
	        System.err.println("No hay clientes registrados.");//mensaje de error
	    } else {
	    	for (Cliente cliente : clientes) { //uso el for each  porque queda mas limpio 
	             System.out.println(cliente.mostrar());//Muestro cada cliente y pongo una separaración para que se vea mas claro
	             System.out.println(" ");
	        }
	    }
	}

	// Método para buscar un cliente por su codigo
	public Cliente buscarCliente(int  codigo) { //Cada cliente tiene su codigo unico asignado al crearlo
	     for (Cliente cliente : clientes) {  //recorro el ArrayList
	         if (cliente.getCodigo()==codigo) { // cuando el codigo proporcionado coincide, devuelve cliente
	             return cliente;
	         }
	     }	System.err.println("Cliente no encontrado"); // Mensaje si no lo encuentra
	     	return null; 
	 }
	
	public boolean eliminarCliente(int codigo) {
        Cliente eliminarCliente = buscarCliente(codigo); //uso el método buscarProducto para buscarlo y asignarlo a la variable
        if (eliminarCliente != null) { //si la variable no esta vacia lo elimino
            clientes.remove(eliminarCliente); // Con .remove borro un objetode la lista(el asignado como eliminarProducto)
            System.out.println("Cliente eliminado: " + codigo); //Mensaje de confirmación
            return true;
        } else {
            System.err.println("No se ha eliminado!");// Mensaje de error
            return false;
        }
    }
	
}
