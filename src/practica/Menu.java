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
        String opcion;

        do { //Menú principal
            System.out.println("====== Menú ======");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("4. Realizar Pago");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            System.out.println();
            opcion = scanner.next();

            switch (opcion) {
                case "1":
                    String opcionClientes;
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
                        opcionClientes = scanner.next();

                        switch (opcionClientes) {
                            case "1":
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

                            case "2":
                            	System.out.println();
                                gestionClientes.listarClientes();
                                break;

                            case "3":
                            	System.out.println();
                                System.out.print("Ingrese el código del cliente a buscar: ");
                                int codigo = scanner.nextInt();
                                Cliente encontrado = gestionClientes.buscarCliente(codigo);
                                if (encontrado != null) {
                                    System.out.println(encontrado.mostrar());
                                }
                                break;
                                
                            case "4": // Se usa el método eliminarCliente para eliminar un cliente de la lista
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
                               
                            case "5": // Modificar cliente
                                System.out.println();
                                System.out.print("Ingrese el código del cliente a modificar: ");
                                int codigoModificar = scanner.nextInt(); // Recoge el código del cliente
                                Cliente clienteModificar = gestionClientes.buscarCliente(codigoModificar); // Busca el cliente por su código

                                if (clienteModificar != null) { // Si el cliente existe
                                	System.out.println();
                                    System.out.println("Cliente encontrado:");
                                    System.out.println();
                                    System.out.println(clienteModificar.mostrar()); // Muestra los datos actuales del cliente

                                    String opcionModificar; // Variable para el submenú
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
                                        opcionModificar = scanner.next();

                                        switch (opcionModificar) {
                                            case "1": // Modificar nombre (case 1,2,3,4 y 6 funcionan igual solo cpongo comentario en el primero
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo nombre: ");
                                                String nuevoNombre = scanner.next(); //recoge el nombre que introduzca el usuario
                                                clienteModificar.setNombre(nuevoNombre);// El setter lo asigna
                                                System.out.println("Nombre modificado exitosamente."); // Mensaje de exito
                                                break;

                                            case "2": // Modificar apellidos
                                                System.out.println();
                                            	System.out.print("Ingrese los nuevos apellidos: ");
                                                String nuevosApellidos = scanner.next();
                                                clienteModificar.setApellidos(nuevosApellidos);
                                                System.out.println("Apellidos modificados exitosamente.");
                                                break;

                                            case "3": // Modificar teléfono
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo teléfono: ");
                                                String nuevoTelefono = scanner.next();
                                                clienteModificar.setTelefono(nuevoTelefono); // Validación en el setter de teléfono
                                                break;

                                            case "4": // Modificar dirección
                                                System.out.println();
                                            	System.out.print("Ingrese la nueva dirección: ");
                                                String nuevaDireccion = scanner.next();
                                                clienteModificar.setDireccion(nuevaDireccion);
                                                System.out.println("Dirección modificada exitosamente.");
                                                break;

                                            case "5": // Modificar fecha de alta, este caso es un poco diferente por eso lo detallo
                                                System.out.println();
                                            	System.out.print("Ingrese la nueva fecha de alta (dd-MM-yyyy): "); // Mensaje para pedir nueva fecha
                                                String nuevaFecha = scanner.next(); // Recoge la fecha como String
                                                try { //Por si lanza la excepción ParseException al hacer el cambio
                                                    clienteModificar.setFechaAlta(nuevaFecha); // Llama al método que puede lanzar ParseException
                                                } catch (ParseException e) { // Si lanza la excepción la capturamos
                                                    System.err.println("Fecha inválida. No se realizó ningún cambio.");//Mensaje de error
                                                }
                                                break;

                                            case "6": // Modificar historial
                                                System.out.println();
                                            	System.out.print("Ingrese el nuevo historial: ");
                                                String nuevoHistorial = scanner.next();
                                                clienteModificar.setHistorial(nuevoHistorial);
                                                System.out.println("Historial modificado exitosamente.");
                                                break;

                                            case "7": // Regresar
                                                System.out.println();
                                            	System.out.println("Regresando al menú de Gestión de Clientes...");
                                                break;

                                            default:
                                            	System.out.println();
                                                System.err.println("Opción no válida. Intente nuevamente.");
                                        }
                                    } while (!opcionModificar.equals("7")); // El submenú se ejecuta hasta que se elija la opción 7

                                } else {
                                	System.out.println();
                                    System.err.println("Cliente no encontrado con el código proporcionado."); // Mensaje de error si no existe el cliente
                                }
                                break;
                            case "6":
                            	System.out.println();
                                System.out.println("Regresando al Menú Principal...");
                                break;

                            default:
                            	System.out.println();
                            	System.err.println("Opción no válida. Intente nuevamente.");
                        }
                    } while (!opcionClientes.equals("6"));
                    break;
                    

                case "2": 
                    String opcionProductos;
                    do { // Submenú para gestionar a los productos
                        System.out.println("\n=== Gestión de Productos ===");
                        System.out.println("1. Agregar Producto");
                        System.out.println("2. Listar Productos");
                        System.out.println("3. Buscar Producto");
                        System.out.println("4. Eliminar Producto");
                        System.out.println("5. Modificar Producto");
                        System.out.println("6. Regresar al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        opcionProductos = scanner.next();

                        switch (opcionProductos) {
                            case "1": // Agregar Producto con otro submenú
                            	String tipoProducto;
	                            do {	
                            		System.out.println("\nIngrese el tipo de producto: ");
	                            	System.out.println("1. Comida");
	                            	System.out.println("2. Bebida");
	                            	System.out.println("3. Regresar menú anterior");
	                            	System.out.print("Seleccione una opción: ");
	                            	tipoProducto = scanner.next();
	
	                            	switch (tipoProducto) {
	                            	    case "1": // Comida  
	                            	    	try {
		                            	        System.out.print("\nIngrese nombre: ");
		                            	        String nombreComida = scanner.next();                          	      
		                            	        System.out.print("Ingrese precio (0,00): ");
		                            	        double precioComida = scanner.nextDouble();                          	        
		                            	        System.out.print("Ingrese calorías (0): ");
		                            	        float calorias = scanner.nextFloat();
		                            	        System.out.print("¿Es perecedero? (true/false): ");
		                            	        boolean perecedero = scanner.nextBoolean();
		                            	        System.out.print("¿Es vegano? (true/false): ");
		                            	        boolean vegano = scanner.nextBoolean();
		                            	        System.out.print("Ingrese estado: ");
		                            	        String estadoComida = scanner.next();
		                            	        Date fechaEnvaseComida = new Date(); 
		
		                            	        Comida comida = new Comida(nombreComida, precioComida, null, estadoComida, perecedero, calorias, vegano, fechaEnvaseComida);
		                            	        gestionProductos.agregarProducto(comida);
	                            	    	}catch (java.util.InputMismatchException e) {
	                            	            System.err.println("Debe ingresar un valor válido!\nNúmeros si contiene decimales  separalo con ','\nEn caso de true o false, escribalo correctamente."); // Mensaje de error
	                            	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
	                            	        }
	
		                            	    break;
	
	                            	    case "2": // Bebida
	                            	    	try {
		                            	        System.out.print("\nIngrese nombre: ");
		                            	        String nombreBebida = scanner.next();
		                            	        System.out.print("Ingrese precio (0,00): ");
		                            	        double precioBebida = scanner.nextDouble();
		                            	        System.out.print("Ingrese medida (0 ml): ");
		                            	        int medida = scanner.nextInt();
		                            	        System.out.print("¿Es gaseoso? (true/false): ");
		                            	        boolean gaseoso = scanner.nextBoolean();
		                            	        System.out.print("¿Es lácteo? (true/false): ");
		                            	        boolean lacteo = scanner.nextBoolean();
		                            	        System.out.print("Ingrese estado: ");
		                            	        String estadoBebida = scanner.next();
		                            	        Date fechaEnvaseBebida = new Date(); // Puedes personalizar esta fecha
		
		                            	        Bebida bebida = new Bebida(nombreBebida, precioBebida, null, estadoBebida, gaseoso, medida, lacteo, fechaEnvaseBebida);
		                            	        gestionProductos.agregarProducto(bebida);
	                            	    	}catch (java.util.InputMismatchException e) {
	                            	            System.err.println("Debe ingresar un valor válido!\nNúmeros si contiene decimales  separalo con ','\nEn caso de true o false, escribalo correctamente."); // Mensaje de error
	                            	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
	                            	        }
	                            	        break;
	                            	    case "3": // Regresar al menú anterior
	                                        System.out.println("Regresando al menú anterior...");
	                                        break;

	                                    default:
	                                    	System.err.println("Opción no válida. Intente nuevamente.");
	                                }
	                            } while (!tipoProducto.equals("3")); // El ciclo se repite hasta que el usuario seleccione la opción 3 (Salir)
	                            break;


                            case "2": // Listar Productos
                                gestionProductos.listarProductos();
                                break;

                            case "3": // Buscar Producto
                                System.out.print("\nIngrese el nombre del producto a buscar: ");
                                String nombreBusqueda = scanner.next();
                                Producto productoEncontrado = gestionProductos.buscarProducto(nombreBusqueda);
                                if (productoEncontrado != null) {
                                    System.out.println("\nProducto encontrado:");
                                    System.out.println(productoEncontrado.detalle_producto());
                                }
                                break;

                            case "4": // Eliminar Producto
                                System.out.print("\nIngrese el nombre del producto a eliminar: ");
                                String nombreEliminar = scanner.next();
                                gestionProductos.eliminarProducto(nombreEliminar);
                                break;

                            case "5": // Modificar Producto
                                System.out.print("\nIngrese el nombre del producto a modificar: ");
                                String nombreModificar = scanner.next();
                                Producto productoModificar = gestionProductos.buscarProducto(nombreModificar);

                                if (productoModificar != null) {
                                    System.out.println("\nProducto encontrado:");
                                    System.out.println(productoModificar.detalle_producto());

                                    String opcionModificarProducto;
                                    do {
                                        System.out.println("\n=== Modificar Producto ===");
                                        System.out.println("1. Modificar Nombre");
                                        System.out.println("2. Modificar Precio");
                                        System.out.println("3. Modificar Estado");
                                        System.out.println("4. Modificar Atributos Específicos");
                                        System.out.println("5. Regresar");
                                        System.out.print("Seleccione una opción: ");
                                        opcionModificarProducto = scanner.next();

                                        switch (opcionModificarProducto) {
                                            case "1": // Modificar nombre
                                                System.out.print("\nIngrese el nuevo nombre: ");
                                                String nuevoNombre = scanner.next();
                                                productoModificar.setNombre(nuevoNombre);
                                                System.out.println("Nombre modificado exitosamente.");
                                                break;

                                            case "2": // Modificar precio
                                                System.out.print("\nIngrese el nuevo precio: ");
                                                double nuevoPrecio = scanner.nextDouble();
                                                productoModificar.setPrecio(nuevoPrecio);
                                                System.out.println("Precio modificado exitosamente.");
                                                break;

                                            case "3": // Modificar estado
                                                System.out.print("\nIngrese el nuevo estado: ");
                                                String nuevoEstado = scanner.next();
                                                productoModificar.setEstado(nuevoEstado);
                                                System.out.println("Estado modificado exitosamente.");
                                                break;
                                            
                                            case "4":
                                                productoModificar.modificarAtributosEspecificos(scanner);
                                                break;


                                            case "5": // Regresar
                                                System.out.println("\nRegresando...");
                                                break;

                                            default:
                                            	System.err.println("Opción no válida. Intente nuevamente.");
                                        }
                                    } while (!opcionModificarProducto.equals("5"));

                                } else {
                                    System.out.println("\nProducto no encontrado.");
                                }
                                break;

                            case "6": // Regresar al Menú Principal
                                System.out.println("\nRegresando al Menú Principal...");
                                break;

                            default:
                            	System.err.println("Opción no válida. Intente nuevamente.");
                        }
                    } while (!opcionProductos.equals("6"));
                    break;

                case "3":
                    // Aquí irían las opciones para gestionar pedidos
                    System.out.println("Gestión de Pedidos...");
                    break;

                case "4":
                    // Aquí iría la lógica para realizar pagos
                    System.out.println("Realizar Pago...");
                    break;

                case "5":
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                	System.err.println("Opción no válida. Intente nuevamente.");
            }

        } while (!opcion.equals("5"));

        scanner.close();
    }
}