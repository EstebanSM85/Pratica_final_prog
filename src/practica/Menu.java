package practica;

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
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Aquí irían las opciones para gestionar clientes
                    System.out.println("Gestión de Clientes...");
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