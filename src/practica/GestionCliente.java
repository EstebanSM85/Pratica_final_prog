package practica;

import java.util.ArrayList;
import java.util.List;
	
public class GestionCliente {
	 // Lista para almacenar objetos Cliente
	private List<Cliente> clientes;
	
	// Constructor para inicializar la lista   
	public GestionCliente() {	     
		this.clientes = new ArrayList<>();
	}
	
	
	// Método para añadir un cliente   
	public void agregarCliente(Cliente cliente) {
		if (cliente != null) {
			clientes.add(cliente);
	    } else {
	        System.out.println("El cliente no es válido.");
	    }
	}

	// Método para listar todos los clientes
	public void listarClientes() {
		if (clientes.isEmpty()) {
	        System.out.println("No hay clientes registrados.");
	    } else {
	    	for (Cliente cliente : clientes) {
	             System.out.println(cliente.mostrar());
	             System.out.println("---------------");
	        }
	    }
	}

	// Método para buscar un cliente por su codigo
	public Cliente buscarCliente(int  codigo) {
	     for (Cliente cliente : clientes) {
	         if (cliente.getCodigo()==codigo) {
	             return cliente;
	         }
	     } return null; // Si no lo encuentra
	 }
	
}
