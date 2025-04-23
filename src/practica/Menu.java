package practica;
/*Se podria mejorar en varios aspectos, como por ejemplo, hay algunos try que se podría mejorar el codigo y quitarlos,
 * se podría hacer que en los pedidos en vez de cambiar todos los productos puedas quitarlos o añadirlos individualmente,
 * que cuando añadas productos puedas seleccionar una cantidad de los mismos en vez de de uno en uno,
 * todo esto es más falta de tiempo que ideas*/


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                        System.out.println("\n=== Gestión de Clientes ===");
                        System.out.println("1. Agregar Cliente");
                        System.out.println("2. Listar Clientes");
                        System.out.println("3. Buscar Cliente");
                        System.out.println("4. Borrar Cliente");
                        System.out.println("5. Modificar cliente");
                        System.out.println("6. Regresar al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        opcionClientes = scanner.next();

                        switch (opcionClientes) {
                            case "1": //Se agrega un nuevo cliente
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

                            case "2": //Se muestra una lista de los clientes guardados usando el método .listarClientes
                            	System.out.println();
                                gestionClientes.listarClientes();
                                break;

                            case "3": // Se busca un cliente con el codigo único proporcionado al darle de alta
                            	    boolean entradaValida = false; // Bandera para controlar la validez de la entrada
                            	    int codigo = 0;

                            	    while (!entradaValida) {
                            	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                            	            System.out.print("\nIngrese el código del cliente a buscar: ");
                            	            codigo = scanner.nextInt(); // Intenta leer el código como entero
                            	            entradaValida = true; // Si llega aquí, la entrada es válida
                            	        } catch (java.util.InputMismatchException e) {
                            	            System.err.println("Debe ingresar un número válido.");
                            	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                            	        }
                            	    }

                                Cliente encontrado = gestionClientes.buscarCliente(codigo); //usamos el método buscarCliente para buscarlo
                                if (encontrado != null) {
                                    System.out.println(encontrado.mostrar());//usamos el método mostrar para mostrarlo
                                }else {
                                    System.err.println("Cliente no encontrado con el código proporcionado."); //Mensaje de error si no lo encuentra
                                }
                                break;
                                
                            case "4": // Se usa el método eliminarCliente para eliminar un cliente de la lista
                            	 boolean entradaValidaEliminar = false; // Bandera para controlar la validez de la entrada
                         	    int codigoABorrar =0;

                         	    while (!entradaValidaEliminar) {
                         	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                         	            System.out.print("\nIngrese el código del cliente a buscar: ");
                         	            codigoABorrar = scanner.nextInt(); // Intenta leer el código como entero
                         	            entradaValidaEliminar = true; // Si llega aquí, la entrada es válida
                         	        } catch (java.util.InputMismatchException e) {
                         	            System.err.println("Debe ingresar un número válido.");
                         	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                         	        }
                         	    }
                                boolean resultado = gestionClientes.eliminarCliente(codigoABorrar); // Se llama al método eliminarCliente
                                if (resultado) { 
                                    System.out.println("El cliente ha sido eliminado exitosamente."); // Mensaje de éxito
                                } else {
                                    System.out.println(); //No muestro mensaje de error ya que los métodos llamados ya lo hacen
                                }
                                break;
                               
                            case "5": // Modificar cliente
                            	boolean entradaValidaModificar = false; // Bandera para controlar la validez de la entrada
                         	    int codigoAModificar = 0;

                         	    while (!entradaValidaModificar) {
                         	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                         	            System.out.print("\nIngrese el código del cliente a buscar: ");
                         	            codigoAModificar = scanner.nextInt(); // Intenta leer el código como entero
                         	            entradaValidaModificar = true; // Si llega aquí, la entrada es válida
                         	        } catch (java.util.InputMismatchException e) {
                         	            System.err.println("Debe ingresar un número válido.");
                         	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                         	        }
                         	    }
                                // Recoge el código del cliente
                                Cliente clienteModificar = gestionClientes.buscarCliente(codigoAModificar); // Busca el cliente por su código

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
                                            case "1": // Modificar nombre (case 1,2,3,4 y 6 funcionan igual solo pongo comentario en el primero
                      
                                            	System.out.print("\nIngrese el nuevo nombre: ");
                                                String nuevoNombre = scanner.next(); //recoge el nombre que introduzca el usuario
                                                clienteModificar.setNombre(nuevoNombre);// El setter lo asigna
                                                System.out.println("Nombre modificado exitosamente."); // Mensaje de exito
                                                break;

                                            case "2": // Modificar apellidos
                                               
                                            	System.out.print("\nIngrese los nuevos apellidos: ");
                                                String nuevosApellidos = scanner.next();
                                                clienteModificar.setApellidos(nuevosApellidos);
                                                System.out.println("Apellidos modificados exitosamente.");
                                                break;

                                            case "3": // Modificar teléfono
                                                
                                            	System.out.print("\nIngrese el nuevo teléfono: ");
                                                String nuevoTelefono = scanner.next();
                                                clienteModificar.setTelefono(nuevoTelefono); // Validación en el setter de teléfono
                                                break;

                                            case "4": // Modificar dirección
                                                
                                            	System.out.print("\nIngrese la nueva dirección: ");
                                                String nuevaDireccion = scanner.next();
                                                clienteModificar.setDireccion(nuevaDireccion);
                                                System.out.println("Dirección modificada exitosamente.");
                                                break;

                                            case "5": // Modificar fecha de alta, este caso es un poco diferente por eso lo detallo
                                                
                                            	System.out.print("\nIngrese la nueva fecha de alta (dd-MM-yyyy): "); // Mensaje para pedir nueva fecha
                                                String nuevaFecha = scanner.next(); // Recoge la fecha como String
                                                try { //Por si lanza la excepción ParseException al hacer el cambio
                                                    clienteModificar.setFechaAlta(nuevaFecha); // Llama al método que puede lanzar ParseException
                                                } catch (ParseException e) { // Si lanza la excepción la capturamos
                                                    System.err.println("Fecha inválida. No se realizó ningún cambio.");//Mensaje de error
                                                }
                                                break;

                                            case "6": // Modificar historial
                                                
                                            	System.out.print("\nIngrese el nuevo historial: ");
                                                String nuevoHistorial = scanner.next();
                                                clienteModificar.setHistorial(nuevoHistorial);
                                                System.out.println("Historial modificado exitosamente.");
                                                break;

                                            case "7": // Regresar
                                               
                                            	System.out.println("\nRegresando al menú de Gestión de Clientes...");
                                                break;

                                            default:
                                            	
                                                System.err.println("\nOpción no válida. Intente nuevamente.");
                                        }
                                    } while (!opcionModificar.equals("7")); // El submenú se ejecuta hasta que se elija la opción 7

                                } else {
                                	
                                    System.err.println("\nCliente no encontrado con el código proporcionado."); // Mensaje de error si no existe el cliente
                                }
                                break;
                            case "6":
                            
                                System.out.println("\nRegresando al Menú Principal...");
                                break;

                            default:
                            	
                            	System.err.println("\nOpción no válida. Intente nuevamente.");
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
	                            	    		/*Uso el bloque try para controlar 'InputMismatchException'
	                            	    		aunque es una excepción que hereda de RuntimeException,
	                            	    		por lo tanto podria cambiar el codigo para no usarlo*/
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
		                            	        Date fechaEnvaseComida = new Date(); // asigna la fecha actual como  de envase(Se puede modificar para introducirla manual)
		
		                            	        Comida comida = new Comida(nombreComida, precioComida, null, perecedero, calorias, vegano, fechaEnvaseComida);
		                            	        //La fecha de caducidad es null porque dentro del constructor se establece segun los valores introducidos
		                            	        gestionProductos.agregarProducto(comida); // se usa el método para agregar el producto
	                            	    	}catch (java.util.InputMismatchException e) { // aqui capturo la excepción y muestro el mensaje
	                            	            System.err.println("Debe ingresar un valor válido!\nNúmeros si contiene decimales  separalo con ','\nEn caso de true o false, escribalo correctamente."); // Mensaje de error
	                            	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada si no a veces hace cosas 'raras'
	                            	        }
	
		                            	    break;
	
	                            	    case "2": // Bebida
	                            	    	try { //Copia casi exacta del case 1 variando los atributos para Bebida, por eso no comento más
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
		                            	        Date fechaEnvaseBebida = new Date(); // Puedes personalizar esta fecha
		
		                            	        Bebida bebida = new Bebida(nombreBebida, precioBebida, null, gaseoso, medida, lacteo, fechaEnvaseBebida);
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
                                gestionProductos.listarProductos();// se muestran los productos que tenemos guardados
                                break;

                            case "3": // Buscar Producto
                                System.out.print("\nIngrese el nombre del producto a buscar: ");
                                String nombreBusqueda = scanner.next(); 
                                //Busca por nombre, esto me parece mejor hacerlo por codigo tambien, pero por variarlo un poco con respecto a cliente
                                Producto productoEncontrado = gestionProductos.buscarProducto(nombreBusqueda);
                                if (productoEncontrado != null) {
                                    System.out.println("\nProducto encontrado:");
                                    System.out.println(productoEncontrado.detalle_producto());
                                }//No pongo mensaje de error porque dentro del método si no lo encuentra ya nos lo devuelve
                                break;

                            case "4": // Eliminar Producto
                                System.out.print("\nIngrese el nombre del producto a eliminar: ");
                                String nombreEliminar = scanner.next();
                                // busca por nombre,dentro del método usam el método buscar, si lo encuentra lo elimina
                                gestionProductos.eliminarProducto(nombreEliminar);
                                break;

                            case "5": // Modificar Producto
                                System.out.print("\nIngrese el nombre del producto a modificar: ");
                                String nombreModificar = scanner.next();
                                Producto productoModificar = gestionProductos.buscarProducto(nombreModificar);// primero busca el producto

                                if (productoModificar != null) {
                                    System.out.println("\nProducto encontrado:");
                                    System.out.println(productoModificar.detalle_producto());//Lo muestra para ver los detalles a modificar

                                    String opcionModificarProducto;
                                    do { //Submenú con las opciones
                                        System.out.println("\n=== Modificar Producto ===");
                                        System.out.println("1. Modificar Nombre");
                                        System.out.println("2. Modificar Precio");
                                        System.out.println("3. Modificar Estado");
                                        System.out.println("4. Modificar Atributos Específicos");
                                        System.out.println("5. Regresar");
                                        System.out.print("Seleccione una opción: ");
                                        opcionModificarProducto = scanner.next();

                                        switch (opcionModificarProducto) {
                                            case "1": // Modificar nombre, case 1,2,3 son los mismos para las 2 clases, funcionan igual
                                                System.out.print("\nIngrese el nuevo nombre: ");
                                                String nuevoNombre = scanner.next(); //recoje la entrada del usuario
                                                productoModificar.setNombre(nuevoNombre); //Asigna el nombre
                                                System.out.println("Nombre modificado exitosamente."); // Mensaje de confirmación
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
                                            
                                            case "4"://Modifica los atributos especificos, usando el método abstracto que esta en cada una de las clases 
                                            	try { //Como puede lanzar la excepción InputMismatchException la captura(se podria hacer para no usarlo)
                                                productoModificar.modificarAtributosEspecificos(scanner);
                                            }catch (java.util.InputMismatchException e) {
	                            	            System.err.println("Opción no válida. Intente nuevamente."); // Mensaje de error
	                            	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
	                            	        }
                                                break;


                                            case "5": // Regresar
                                                System.out.println("\nRegresando...");
                                                break;

                                            default: // si no pulsas la opción correspondiente
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
                	String opcionPedidos;
                	do { // Submenú para gestionar los pedidos
                        System.out.println();
                        System.out.println("=== Gestión de Pedidos ===");
                        System.out.println("1. Agregar Pedido");
                        System.out.println("2. Listar Pedidos");
                        System.out.println("3. Buscar Pedido");
                        System.out.println("4. Eliminar Pedido");
                        System.out.println("5. Modificar Pedido");
                        System.out.println("6. Regresar al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        opcionPedidos = scanner.next();
                        switch (opcionPedidos) {
                        case "1": // Agregar un nuevo pedido
                           
                            System.out.print("\nIngrese el código del cliente para el pedido: ");
                            int codigoCliente = scanner.nextInt();
                            Cliente clientePedido = gestionClientes.buscarCliente(codigoCliente); // Busca al cliente y lo asigna a clientePedido

                            if (clientePedido != null) { //si no esta vacio cream una lista de Producto
                                List<Producto> productosPedido = new ArrayList<>(); // Instancia la lista y la llama productosPedido
                                String agregarProductos;
                                do {
                                    System.out.println("Ingrese el nombre del producto para añadir al pedido: ");
                                    String nombreProducto = scanner.next(); //recoge la entrada del usuario
                                    Producto productoPedido = gestionProductos.buscarProducto(nombreProducto); //Busca la opción
                                    // si no lo encuentra el método buscar lanzara un mensaje de error

                                    if (productoPedido != null) { //Si la variable tiene un valor, lo añade
                                        productosPedido.add(productoPedido);
                                        System.out.println("Producto añadido al pedido.");//Mensaje de confirmación
                                    }//No pongo mensaje de error ya que el método buscar si no encuentra ya muestra uno

                                    System.out.print("¿Desea agregar otro producto? (sí/no): ");
                                    agregarProductos = scanner.next();//recoje la entrada
                                    
                                    while (!agregarProductos.equalsIgnoreCase("si") &&
                                           !agregarProductos.equalsIgnoreCase("sí") &&
                                           !agregarProductos.equalsIgnoreCase("no")) {
                                        System.err.println("Entrada no válida. Por favor, ingrese 'sí' o 'no'.");
                                        System.out.print("¿Desea agregar otro producto? (sí/no): ");
                                        agregarProductos = scanner.next(); // Recoge la entrada nuevamente solo si es inválida
                                    }

                                } while (agregarProductos.equalsIgnoreCase("si")||agregarProductos.equalsIgnoreCase("sí"));//Repite mientras sea si o sí  

                                Pedido nuevoPedido = new Pedido(clientePedido, productosPedido, new Date()); //Instancia un nuevo pedido
                                gestionPedidos.agregarPedido(nuevoPedido);//Agrega el pedido a la lista 
                            } else {
                                System.err.println("No se puede crear el pedido.");//Mensaje de error si no encuentra al cliente
                            }
                            break;
                            
                        case "2": // Listar todos los pedidos
                            System.out.println();
                            gestionPedidos.listarPedidos();//usa el método para listar los pedidos
                            break;
                            
                        case "3": // Buscar un pedido por código
                        	boolean pedidoValido = false; // Bandera para controlar la validez de la entrada
                     	    int codigoPedido =0;

                     	    while (!pedidoValido) {
                     	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                     	            System.out.print("\nIngrese el código del pedido a buscar: ");
                     	            codigoPedido = scanner.nextInt(); // Intenta leer el código como entero
                     	            pedidoValido = true; // Si llega aquí, la entrada es válida
                     	        } catch (java.util.InputMismatchException e) {
                     	            System.err.println("Debe ingresar un número válido.");
                     	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                     	        }
                     	    }
                           
                            Pedido pedidoEncontrado = gestionPedidos.buscarPedido(codigoPedido);
                            // Busca el pedido utilizando el método buscarPedido de la clase gestionPedidos
                            
                            if (pedidoEncontrado != null) {// Si el pedido es encontrado
                                System.out.println(pedidoEncontrado.mostrarPedido()); //Lo muestra
                            } else {
                                System.err.println("Pedido no encontrado con el código proporcionado.");//Mensaje de error
                            }
                            break;
                            
                        case "4": // Eliminar un pedido por código
                        	boolean pedidoValidoEliminar = false; // Bandera para controlar la validez de la entrada
                     	    int pedidoABorrar =0;

                     	    while (!pedidoValidoEliminar) {
                     	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                     	            System.out.print("\nIngrese el código del pedido a eliminar: ");
                     	            pedidoABorrar = scanner.nextInt(); // Intenta leer el código como entero
                     	            pedidoValidoEliminar = true; // Si llega aquí, la entrada es válida
                     	        } catch (java.util.InputMismatchException e) {
                     	            System.err.println("Debe ingresar un número válido.");
                     	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                     	        }
                     	    }
                          
                            boolean resultadoEliminar = gestionPedidos.eliminarPedido(pedidoABorrar);
                            /* Llama al método eliminarPedido de la clase GestionPedidos,
                            dentro usa el método buscar y lo elimina si lo encuentra*/
                            

                            if (resultadoEliminar) {//Si es true
                                System.out.println("Pedido eliminado exitosamente.");//Mensaje de confirmación
                            } else {
                                System.err.println("No se pudo eliminar el pedido.");//Si es false mensaje de error
                            }
                            break;
                            
                            
                        case "5": // Modificar un pedido
                        	boolean pedidoValidoModificar = false; // Bandera para controlar la validez de la entrada
                     	    int pedidoAModificar =0;

                     	    while (!pedidoValidoModificar) {
                     	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                     	            System.out.print("\nIngrese el código del pedido a modificar2: ");
                     	           pedidoAModificar = scanner.nextInt(); // Intenta leer el código como entero
                     	           pedidoValidoModificar = true; // Si llega aquí, la entrada es válida
                     	        } catch (java.util.InputMismatchException e) {
                     	            System.err.println("Debe ingresar un número válido.");
                     	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                     	        }
                     	    }
                            
                            Pedido pedidoModificar = gestionPedidos.buscarPedido(pedidoAModificar);
                            // Busca el pedido en la lista utilizando el método buscarPedido de la clase gestionPedidos
                            

                            if (pedidoModificar != null) {// Si lo encuentra

                                System.out.println("\nPedido encontrado:");
                                System.out.println(pedidoModificar.mostrarPedido());//Lo muestra

                                String opcionModificarPedido;
                                do {/// Muestra el submenú de opciones para modificar un pedido
                                    System.out.println("\n=== Modificar Pedido ===");
                                    System.out.println("1. Modificar Cliente");
                                    System.out.println("2. Modificar Productos");
                                    System.out.println("3. Modificar Fecha de Pedido");
                                    System.out.println("4. Regresar");
                                    System.out.print("Seleccione una opción: ");
                                    opcionModificarPedido = scanner.next();

                                    switch (opcionModificarPedido) {
                                        case "1": // Modificar cliente
                                        	boolean cambioClientePedido = false; // Bandera para controlar la validez de la entrada
                                     	    int nuevoCodigoCliente = 0;

                                     	    while (!cambioClientePedido) {
                                     	        try { //Si se introduce un string tenemos la excepcion InputMismatchException
                                     	            System.out.print("\nIngrese el código del cliente a buscar: ");
                                     	           nuevoCodigoCliente = scanner.nextInt(); // Intenta leer el código como entero
                                     	          cambioClientePedido = true; // Si llega aquí, la entrada es válida
                                     	        } catch (java.util.InputMismatchException e) {
                                     	            System.err.println("Debe ingresar un número válido.");
                                     	            scanner.nextLine(); // Limpia el buffer del scanner para evitar fallos en la próxima entrada
                                     	        }
                                     	    }
                                            
                                            Cliente nuevoCliente = gestionClientes.buscarCliente(nuevoCodigoCliente);

                                            if (nuevoCliente != null) { // Si lo encuentra

                                                pedidoModificar.setCliente(nuevoCliente); //Asigna la nueva variable
                                                System.out.println("Cliente modificado exitosamente.");//Mensaje de confirmación
                                            } else {
                                                System.err.println("Cliente no encontrado.");//Mensaje de  error
                                            }
                                            break;

                                        case "2": // Modificar productos
                                            List<Producto> nuevosProductos = new ArrayList<>();
                                            String modificarProductos;
                                            do {
                                                System.out.print("Ingrese el nombre del nuevo producto: ");
                                                String nombreNuevoProducto = scanner.next();
                                                Producto nuevoProducto = gestionProductos.buscarProducto(nombreNuevoProducto);

                                                if (nuevoProducto != null) {
                                                    nuevosProductos.add(nuevoProducto);
                                                    System.out.println("Producto añadido.");
                                                } else {
                                                    System.err.println("Producto no encontrado.");
                                                }

                                                do { //Este do-wile tiene la misma funcion que en case 1 agregar pedido 
                                                    System.out.print("¿Desea agregar otro producto? (sí/no): ");
                                                    modificarProductos = scanner.next(); // Captura la respuesta del usuario

                                                    if (!modificarProductos.equalsIgnoreCase("si") && // Valida si la entrada no es "si", "sí" o "no"
                                                        !modificarProductos.equalsIgnoreCase("sí") &&
                                                        !modificarProductos.equalsIgnoreCase("no")) {
                                                        System.err.println("Entrada no válida. Por favor, ingrese 'sí' o 'no'."); // Mensaje de error
                                                    }
                                                } while (!modificarProductos.equalsIgnoreCase("si") && // Repite hasta que la entrada sea válida
                                                         !modificarProductos.equalsIgnoreCase("sí") &&
                                                         !modificarProductos.equalsIgnoreCase("no"));

                                            } while (modificarProductos.equalsIgnoreCase("si") || modificarProductos.equalsIgnoreCase("sí")); // Continúa si el usuario pulsa 'sí' o 'si'


                                            pedidoModificar.setProductos(nuevosProductos);
                                            System.out.println("Productos modificados exitosamente.");
                                            break;

                                        case "3": // Modificar fecha de pedido
                                            System.out.print("\nIngrese la nueva fecha del pedido (dd-MM-yyyy): ");
                                            String nuevaFechaPedido = scanner.next(); // Captura la fecha ingresada por el usuario como cadena

                                            try { //Por si se mete una fecha invalida
                                                pedidoModificar.setFechaPedido(nuevaFechaPedido); // Llama al método que puede lanzar ParseException
                                                System.out.println("Fecha de pedido modificada exitosamente."); 
                                            } catch (ParseException e) { // Si lanza la excepción la capturamos
                                                System.err.println("La fecha proporcionada no es válida. Intente nuevamente.");
                                            }
                                            break; 

                                        case "4": // Regresar
                                            System.out.println("\nRegresando al menú de Gestión de Pedidos...");
                                            break;

                                        default:
                                            System.err.println("Opción no válida. Intente nuevamente.");
                                    }
                                } while (!opcionModificarPedido.equals("4"));
                            } else {
                                System.err.println("Pedido no encontrado con el código proporcionado.");
                            }
                            break;
                            
                        case "6": // Regresar al menú principal
                            System.out.println("\nRegresando al Menú Principal...");
                            break;
                            
                        default:
                            System.err.println("\nOpción no válida. Intente nuevamente.");//Mensaje de error si no es correcta la opción
                        }
                        
                    }while (!opcionPedidos.equals("6"));
                    break;

                case "4": // Procesar el pago del pedido

                    // Solicita el código del pedido a pagar
                    System.out.print("\nIngrese el código del pedido que desea pagar: ");
                    int codigoPedidoPago = scanner.nextInt(); // Captura el código del pedido
                    Pedido pedidoPago = gestionPedidos.buscarPedido(codigoPedidoPago); // Busca el pedido por código

                    if (pedidoPago != null) { // Si el pedido existe
                        System.out.println("\nPedido encontrado:");
                        System.out.println(pedidoPago.mostrarPedido()); // Muestra los detalles del pedido

                        String opcionPago; // Variable para seleccionar la forma de pago
                        do {
                            System.out.println("\n=== Métodos de Pago ===");
                            System.out.println("1. Tarjeta de Crédito/Débito");
                            System.out.println("2. Transferencia Bancaria");
                            System.out.println("3. Pago en Efectivo");
                            System.out.println("4. Regresar");
                            System.out.print("Seleccione una opción: ");
                            opcionPago = scanner.next(); // Captura la opción seleccionada por el usuario

                            switch (opcionPago) {// Pago con tarjeta de crédito/débito

                                case "1": // Tarjeta
                                    System.out.println("\n--- Pago con Tarjeta ---"); //Se introducen los datos de la tarjeta
                                    System.out.print("Ingrese el número de la tarjeta(16 dígitos sin espacios): ");
                                    String numeroTarjeta = scanner.next();
                                    System.out.print("Ingrese el titular de la tarjeta: ");
                                    String titular = scanner.next();
                                    System.out.print("Ingrese la fecha de caducidad (MM/AA): "); //Se verifica dentro del constructor
                                    String fechaCaducidad = scanner.next();
                                    System.out.print("Ingrese el código de seguridad: ");
                                    int codigoSeguridad = scanner.nextInt();

                                    try {
                                        Tarjeta pagoTarjeta = new Tarjeta(pedidoPago.calcularTotal(), numeroTarjeta, titular, fechaCaducidad, codigoSeguridad);
                                        if (pagoTarjeta.procesarPago()) {
                                            System.out.println("El pago con tarjeta se realizó exitosamente.");
                                        }
                                    } catch (TarjetaInvalidaException e) {
                                        System.err.println("Error: " + e.getMessage());
                                    }
                                    break;


                                case "2": // Transferencia
                                    System.out.println("\n--- Pago por Transferencia ---"); // Se introducen los datos de las cuentas
                                    System.out.print("Ingrese la cuenta origen(20 dígitos, sin espacios): ");
                                    String cuentaOrigen = scanner.next(); // Captura la cuenta de origen
                                    System.out.print("Ingrese la cuenta destino(20 dígitos, sin espacios): ");
                                    String cuentaDestino = scanner.next(); // Captura la cuenta de destino

                                    try {
                                        // Intenta crear el objeto Transferencia
                                        Transferencia pagoTransferencia = new Transferencia(pedidoPago.calcularTotal(), cuentaOrigen, cuentaDestino);

                                        // Procesa el pago si las cuentas son válidas
                                        if (pagoTransferencia.procesarPago()) {
                                            System.out.println("El pago por transferencia se realizó exitosamente."); // Mensaje de confirmación
                                        } 

                                    } catch (CuentaInvalidaException e) {
                                        // Captura la excepción lanzada por el constructor y muestra un mensaje de error
                                        System.err.println("Error al crear la transferencia: " + e.getMessage());
                                    }
                                    break;

                                case "3": // Efectivo
                                    System.out.println("\n--- Pago en Efectivo ---");
                                    System.out.print("Ingrese la cantidad entregada: ");
                                    double entrega = scanner.nextDouble();

                                    if (entrega >= pedidoPago.calcularTotal()) { // Verifica que la entrega sea suficiente
                                        Efectivo pagoEfectivo = new Efectivo(pedidoPago.calcularTotal(), entrega);
                                        pagoEfectivo.procesarPago(); // Procesa el pago en efectivo y calcula el cambio
                                    } else {
                                        System.err.println("La cantidad entregada es insuficiente para realizar el pago.");
                                    }
                                    break;

                                case "4": // Regresar
                                    System.out.println("\nRegresando al menú anterior...");
                                    break;

                                default:
                                    System.err.println("Opción no válida. Intente nuevamente.");
                            }
                        } while (!opcionPago.equals("4")); // Se repite hasta que el usuario pulse 4
                    } else { // Si el pedido no existe
                        System.err.println("Pedido no encontrado con el código proporcionado.");//Mensaje de error
                    }
                    break;

                case "5": //Cierra el programa
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                	System.err.println("Opción no válida. Intente nuevamente."); //mensaje de error
            }

        } while (!opcion.equals("5"));// Se repite hasta que el usuario pulse 5

        scanner.close();
    }
}