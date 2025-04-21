package practica;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        GestionCliente gestionClientes = new GestionCliente();
        GestionProducto gestionProductos = new GestionProducto();
        GestionPedidos gestionPedidos = new GestionPedidos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do { //Menú principal
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
                    do { // Submenú para gestionar a los clientes
                    	System.out.println();
                        System.out.println("=== Gestión de Clientes ===");
                        System.out.println("1. Agregar Cliente");
                        System.out.println("2. Listar Clientes");
                        System.out.println("3. Buscar Cliente");
                        System.out.println("4. Borrar Cliente");
                        System.out.println("5. Modificar cliente");
                        System.out.println("6. Regresar al Menú Principal");
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
                                    System.out.println(); //No muestro mensaje de error ya que los métodos llamados ya lo hacen
                                }
                                break;
                               
                            case 5: // Modificar cliente
                                System.out.println();
                                System.out.print("Ingrese el código del cliente a modificar: ");
                                int codigoModificar = scanner.nextInt(); // Recoge el código del cliente
                                Cliente clienteModificar = gestionClientes.buscarCliente(codigoModificar); // Busca el cliente por su código

                                if (clienteModificar != null) { // Si el cliente existe
                                	System.out.println();
                                    System.out.println("Cliente encontrado:");
                                    System.out.println();
                                    System.out.println(clienteModificar.mostrar()); // Muestra los datos actuales del cliente

                                    int opcionModificar; // Variable para el submenú
                                    do {
                                        // Submenú para modificar atributos del cliente
                                        System.out.println("\n=== Modificar Cliente ===");
                                        System.out.println("1. Modificar Nombre");
                                        System.out.println("2. Modificar Apellidos");
                                        System.out.println("3. Modificar Teléfono");
                                        System.out.println("4. Modificar Dirección");
                                        System.out.println("5. Modificar Fecha de Alta");
                                        System.out.println("6. Modificar Historial");
                                        System.out.println("7. Regresar");
                                        System.out.print("Seleccione una opción: ");
                                        opcionModificar = scanner.nextInt();

                                        switch (opcionModificar) {
                                            case 1: // Modificar nombre (case 1,2,3,4 y 6 funcionan igual solo cpongo comentario en el primero
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo nombre: ");
                                                String nuevoNombre = scanner.next(); //recoge el nombre que introduzca el usuario
                                                clienteModificar.setNombre(nuevoNombre);// El setter lo asigna
                                                System.out.println("Nombre modificado exitosamente."); // Mensaje de exito
                                                break;

                                            case 2: // Modificar apellidos
                                                System.out.println();
                                            	System.out.print("Ingrese los nuevos apellidos: ");
                                                String nuevosApellidos = scanner.next();
                                                clienteModificar.setApellidos(nuevosApellidos);
                                                System.out.println("Apellidos modificados exitosamente.");
                                                break;

                                            case 3: // Modificar teléfono
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo teléfono: ");
                                                String nuevoTelefono = scanner.next();
                                                clienteModificar.setTelefono(nuevoTelefono); // Validación en el setter de teléfono
                                                break;

                                            case 4: // Modificar dirección
                                                System.out.println();
                                            	System.out.print("Ingrese la nueva dirección: ");
                                                String nuevaDireccion = scanner.next();
                                                clienteModificar.setDireccion(nuevaDireccion);
                                                System.out.println("Dirección modificada exitosamente.");
                                                break;

                                            case 5: // Modificar fecha de alta, este caso es un poco diferente por eso lo detallo
                                                System.out.println();
                                            	System.out.print("Ingrese la nueva fecha de alta (dd-MM-yyyy): "); // Mensaje para pedir nueva fecha
                                                String nuevaFecha = scanner.next(); // Recoge la fecha como String
                                                try { //Por si lanza la excepción ParseException al hacer el cambio
                                                    clienteModificar.setFechaAlta(nuevaFecha); // Llama al método que puede lanzar ParseException
                                                } catch (ParseException e) { // Si lanza la excepción la capturamos
                                                    System.err.println("Fecha inválida. No se realizó ningún cambio.");//Mensaje de error
                                                }
                                                break;

                                            case 6: // Modificar historial
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo historial: ");
                                                String nuevoHistorial = scanner.next();
                                                clienteModificar.setHistorial(nuevoHistorial);
                                                System.out.println("Historial modificado exitosamente.");
                                                break;

                                            case 7: // Regresar
                                                System.out.println();
                                            	System.out.println("Regresando al menú de Gestión de Clientes...");
                                                break;

                                            default:
                                            	System.out.println();
                                                System.err.println("Opción no válida. Intente nuevamente.");
                                        }
                                    } while (opcionModificar != 7); // El submenú se ejecuta hasta que se elija la opción 7

                                } else {
                                	System.out.println();
                                    System.err.println("Cliente no encontrado con el código proporcionado."); // Mensaje de error si no existe el cliente
                                }
                                break;
                            case 6:
                            	System.out.println();
                                System.out.println("Regresando al Menú Principal...");
                                break;

                            default:
                            	System.out.println();
                                System.out.println("Opción no válida.");
                        }
                    } while (opcionClientes != 6
                    		);
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