package practica;

import java.util.Date;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        GestionCliente gestionClientes = new GestionCliente();
        GestionProducto gestionProductos = new GestionProducto();
        GestionPedidos gestionPedidos = new GestionPedidos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("====== Menú ======");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("4. Realizar Pago");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            System.out.println();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int opcionClientes;
                    do {
                    	System.out.println();
                        System.out.println("=== Gestión de Clientes ===");
                        System.out.println("1. Agregar Cliente");
                        System.out.println("2. Listar Clientes");
                        System.out.println("3. Buscar Cliente");
                        System.out.println("4. Borrar Cliente");
                        System.out.println("5. Regresar al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        opcionClientes = scanner.nextInt();

                        switch (opcionClientes) {
                            case 1:
                            	System.out.println();
                                System.out.print("Ingrese nombre: ");
                                String nombre = scanner.next();
                                System.out.print("Ingrese apellidos: ");
                                String apellidos = scanner.next();
                                System.out.print("Ingrese teléfono: ");
                                String telefono = scanner.next();
                                System.out.print("Ingrese dirección: ");
                                String direccion = scanner.next();
                                Cliente cliente = new Cliente(nombre, apellidos, telefono, direccion, new Date(), "");
                                gestionClientes.agregarCliente(cliente);
                                break;

                            case 2:
                            	System.out.println();
                                gestionClientes.listarClientes();
                                break;

                            case 3:
                            	System.out.println();
                                System.out.print("Ingrese el código del cliente a buscar: ");
                                int codigo = scanner.nextInt();
                                Cliente encontrado = gestionClientes.buscarCliente(codigo);
                                if (encontrado != null) {
                                    System.out.println(encontrado.mostrar());
                                }
                                break;
                                
                            case 4: // Se usa el método eliminarCliente para eliminar un cliente de la lista
                            	System.out.println();
                                System.out.print("Ingrese el código del cliente a borrar: ");
                                int codigoABorrar = scanner.nextInt(); // Se recoge el código proporcionado por el usuario
                                boolean resultado = gestionClientes.eliminarCliente(codigoABorrar); // Se llama al método eliminarCliente
                                if (resultado) { 
                                    System.out.println("El cliente ha sido eliminado exitosamente."); // Mensaje de éxito
                                } else {
                                    System.out.println(" "); //No muestro mensaje de error ya que los métodos llamados ya lo hacen
                                }
                                break;

                            case 5:
                            	System.out.println();
                                System.out.println("Regresando al Menú Principal...");
                                break;

                            default:
                                System.out.println("Opción no válida.");
                        }
                    } while (opcionClientes != 5);
                    break;

                case 2:
                    // Aquí irían las opciones para gestionar productos
                    System.out.println("Gestión de Productos...");
                    break;

                case 3:
                    // Aquí irían las opciones para gestionar pedidos
                    System.out.println("Gestión de Pedidos...");
                    break;

                case 4:
                    // Aquí iría la lógica para realizar pagos
                    System.out.println("Realizar Pago...");
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}