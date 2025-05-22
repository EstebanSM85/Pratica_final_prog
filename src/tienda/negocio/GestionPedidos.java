package tienda.negocio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tienda.modelo.Cliente;
import tienda.modelo.Pedido;
import tienda.modelo.Producto;
import tienda.persistencia.Conexion_Mejorada;

public class GestionPedidos implements Imprimir {
    private List<Pedido> pedidos; // Lista para almacenar objetos Pedido en una lista llamada pedidos

    public GestionPedidos() throws TelefonoInvalidoException, Exception {
        pedidos = new ArrayList<>();
        cargarPedidos();
    }

    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido); // Agrega el pedido a la lista en memoria

            String queryPedido = "INSERT INTO Pedidos (Codigo_Pedido, Cliente, Fecha_Pedido, Productos, Total) VALUES (?, ?, ?, ?, ?)";
            String queryProductos = "INSERT INTO pedidos_producto (Codigo_Pedido, producto_id) VALUES (?, ?)";

            try (Connection conexion = Conexion_Mejorada.conectar();
                 PreparedStatement psPedido = conexion.prepareStatement(queryPedido)) {

                psPedido.setInt(1, pedido.getCodigoPedido());
                psPedido.setString(2, pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellidos());
                psPedido.setTimestamp(3, new java.sql.Timestamp(pedido.getFechaPedido().getTime()));

                // Convertir la lista de productos a una cadena de texto
                String productosStr = pedido.getProductos().stream()
                    .map(Producto::getNombre)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

                psPedido.setString(4, productosStr);
                psPedido.setDouble(5, pedido.calcularTotal());

                psPedido.executeUpdate();

                // Guardar productos en la tabla de relación pedidos-productos
                try (PreparedStatement psProductos = conexion.prepareStatement(queryProductos)) {
                    for (Producto producto : pedido.getProductos()) {
                        psProductos.setInt(1, pedido.getCodigoPedido());
                        psProductos.setInt(2, producto.getCodigo());
                        psProductos.executeUpdate();
                    }
                }

                System.out.println("✅ Pedido agregado y guardado correctamente en la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌ Error al guardar pedido en la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("❌ El pedido no es válido.");
        }
    }

    public void listarPedidos() {
        String query = "SELECT * FROM Pedidos";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.err.println("❌ No hay pedidos registrados.");
                return;
            }

            while (rs.next()) {
                System.out.println("Código Pedido: " + rs.getInt("Codigo_Pedido"));
                System.out.println("Cliente: " + rs.getString("Cliente"));
                System.out.println("Fecha Pedido: " + rs.getTimestamp("Fecha_Pedido"));
                System.out.println("Productos: " + rs.getString("Productos"));
                System.out.println("Total: " + rs.getDouble("Total") + "€");
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar pedidos: " + e.getMessage());
        }
    }

    public Pedido buscarPedido(int codigo) throws TelefonoInvalidoException {
        String queryPedido = "SELECT * FROM Pedidos WHERE Codigo_Pedido = ?";
        String queryProductos = "SELECT pr.* FROM producto pr JOIN pedidos_producto pp ON pr.id = pp.producto_id WHERE pp.Codigo_Pedido = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement psPedido = conexion.prepareStatement(queryPedido)) {

            psPedido.setInt(1, codigo);
            try (ResultSet rsPedido = psPedido.executeQuery()) {
                if (rsPedido.next()) {
                    Cliente cliente = new Cliente(rsPedido.getString("Cliente"), "", "", "", rsPedido.getTimestamp("Fecha_Pedido"), "");
                    Date fechaPedido = rsPedido.getTimestamp("Fecha_Pedido");

                    List<Producto> productos = new ArrayList<>();
                    try (PreparedStatement psProductos = conexion.prepareStatement(queryProductos)) {
                        psProductos.setInt(1, codigo);
                        try (ResultSet rsProductos = psProductos.executeQuery()) {
                            while (rsProductos.next()) {
                                if ("comida".equalsIgnoreCase(rsProductos.getString("tipo"))) {
                                    productos.add(new Comida(
                                        rsProductos.getString("nombre"),
                                        rsProductos.getDouble("precio"),
                                        rsProductos.getDate("fecha_caducidad"),
                                        rsProductos.getDate("fecha_envase"),
                                        rsProductos.getBoolean("perecedero"),
                                        rsProductos.getFloat("calorias"),
                                        rsProductos.getBoolean("vegano")
                                    ));
                                } else {
                                    productos.add(new Bebida(
                                        rsProductos.getString("nombre"),
                                        rsProductos.getDouble("precio"),
                                        rsProductos.getDate("fecha_caducidad"),
                                        rsProductos.getDate("fecha_envase"),
                                        rsProductos.getBoolean("gaseoso"),
                                        rsProductos.getInt("medida"),
                                        rsProductos.getBoolean("lacteo")
                                    ));
                                }
                            }
                        }
                    }

                    return new Pedido(cliente, productos, fechaPedido);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar pedido: " + e.getMessage());
        }

        System.err.println("❌ Pedido no encontrado.");
        return null;
    }
    
    public void modificarPedido(int codigo, Date nuevaFecha) {
        String query = "UPDATE Pedidos SET Fecha_Pedido = ? WHERE Codigo_Pedido = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setTimestamp(1, new java.sql.Timestamp(nuevaFecha.getTime()));
            ps.setInt(2, codigo);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Pedido modificado correctamente.");
            } else {
                System.err.println("❌ No se encontró el pedido para modificar.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al modificar pedido: " + e.getMessage());
        }
    }

    public boolean eliminarPedido(int codigo) {
        String queryProductos = "DELETE FROM pedidos_producto WHERE Codigo_Pedido = ?";
        String queryPedido = "DELETE FROM Pedidos WHERE Codigo_Pedido = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement psProductos = conexion.prepareStatement(queryProductos);
             PreparedStatement psPedido = conexion.prepareStatement(queryPedido)) {

            psProductos.setInt(1, codigo);
            psProductos.executeUpdate();

            psPedido.setInt(1, codigo);
            int filasAfectadas = psPedido.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Pedido eliminado correctamente.");
                return true;
            } else {
                System.err.println("❌ No se ha eliminado el pedido.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }

	@Override
	public void imprimir() {
		if (pedidos.isEmpty()) {
	       System.out.println("\nNo hay pedidos para imprimir.");
	    } else {
	        System.out.println("\n--- TICKET DE PEDIDOS ---");
	        for (Pedido pedido : pedidos) {
	            System.out.println("Código Pedido: " + pedido.getCodigoPedido());
	            System.out.println("Cliente: " + pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellidos());
	            System.out.println("Fecha Pedido: " + pedido.getFechaPedido());
	            System.out.println("Productos:");
	            for (Producto producto : pedido.getProductos()) {
	            	double precioReal = producto.getPrecio(); // Precio original
	                long diferenciaDias = (producto.getCaducidad().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24); // Diferencia en días
	
	                if (diferenciaDias <= 5) { // Si el producto está en oferta
	                    precioReal *= 0.7; // Aplica el descuento del 30%
	                }
	
	                System.out.println("- " + producto.getNombre() + " (Precio: " + precioReal + "€)");
	            }
	            System.out.println("Total Pedido: " + pedido.calcularTotal() + "€");
	            System.out.println("-------------------------");
	        }
	    }			
    }
	
	public void guardarPedido(Pedido pedido) {
	    String queryPedido = "INSERT INTO Pedidos (Codigo_Pedido, Cliente, Fecha_Pedido, Productos, Total) VALUES (?, ?, ?, ?, ?)";
	    String queryProductos = "INSERT INTO pedidos_producto (Codigo_Pedido, producto_id) VALUES (?, ?)";

	    try (Connection conexion = Conexion_Mejorada.conectar();
	         PreparedStatement psPedido = conexion.prepareStatement(queryPedido)) {

	        psPedido.setInt(1, pedido.getCodigoPedido());
	        psPedido.setString(2, pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellidos());
	        psPedido.setTimestamp(3, new java.sql.Timestamp(pedido.getFechaPedido().getTime()));
	        
	        String productosStr = pedido.getProductos().stream()
	                .map(Producto::getNombre)
	                .reduce((a, b) -> a + ", " + b)
	                .orElse("");

	        psPedido.setString(4, productosStr);
	        psPedido.setDouble(5, pedido.calcularTotal());

	        psPedido.executeUpdate();

	        try (PreparedStatement psProductos = conexion.prepareStatement(queryProductos)) {
	            for (Producto producto : pedido.getProductos()) {
	                psProductos.setInt(1, pedido.getCodigoPedido());
	                psProductos.setInt(2, producto.getCodigo());
	                psProductos.executeUpdate();
	            }
	        }

	        System.out.println("✅ Pedido guardado correctamente.");
	    } catch (SQLException e) {
	        System.err.println("❌ Error al guardar pedido: " + e.getMessage());
	    }
	}
	
	public void cargarPedidos() throws TelefonoInvalidoException {
	    pedidos.clear(); // Limpiamos la lista antes de cargar los datos
	    String queryPedido = "SELECT p.*, c.telefono FROM Pedidos p JOIN Cliente c ON p.Cliente = c.nombre";
	    String queryProductos = "SELECT pr.* FROM producto pr JOIN pedidos_producto pp ON pr.id = pp.producto_id WHERE pp.Codigo_Pedido = ?";

	    try (Connection conexion = Conexion_Mejorada.conectar();
	         PreparedStatement psPedido = conexion.prepareStatement(queryPedido);
	         ResultSet rsPedido = psPedido.executeQuery()) {

	        while (rsPedido.next()) {
	            int codigoPedido = rsPedido.getInt("Codigo_Pedido");
	            String telefono = rsPedido.getString("telefono");

	            // Validamos que el teléfono cumple con el formato antes de crear el Cliente
	            if (telefono == null || !telefono.matches("[6789]\\d{8}")) {
	                System.err.println("⚠️ Teléfono inválido para el cliente: " + rsPedido.getString("Cliente"));
	                continue; // Saltamos este pedido si el teléfono no es válido
	            }

	            Cliente cliente = new Cliente(
	                rsPedido.getString("Cliente"), "", telefono, "", rsPedido.getTimestamp("Fecha_Pedido"), ""
	            );

	            Date fechaPedido = rsPedido.getTimestamp("Fecha_Pedido");

	            // Cargar productos asociados al pedido
	            List<Producto> productos = new ArrayList<>();
	            try (PreparedStatement psProductos = conexion.prepareStatement(queryProductos)) {
	                psProductos.setInt(1, codigoPedido);
	                try (ResultSet rsProductos = psProductos.executeQuery()) {
	                    while (rsProductos.next()) {
	                        productos.add(rsProductos.getString("tipo").equalsIgnoreCase("comida") ?
	                            new Comida(
	                                rsProductos.getString("nombre"),
	                                rsProductos.getDouble("precio"),
	                                rsProductos.getDate("fecha_caducidad"),
	                                rsProductos.getDate("fecha_envase"),
	                                rsProductos.getBoolean("perecedero"),
	                                rsProductos.getFloat("calorias"),
	                                rsProductos.getBoolean("vegano")
	                            ) :
	                            new Bebida(
	                                rsProductos.getString("nombre"),
	                                rsProductos.getDouble("precio"),
	                                rsProductos.getDate("fecha_caducidad"),
	                                rsProductos.getDate("fecha_envase"),
	                                rsProductos.getBoolean("gaseoso"),
	                                rsProductos.getInt("medida"),
	                                rsProductos.getBoolean("lacteo")
	                            ));
	                    }
	                }
	            }

	            pedidos.add(new Pedido(cliente, productos, fechaPedido));
	        }

	        System.out.println("✅ Pedidos cargados correctamente desde la BD.");
	    } catch (SQLException e) {
	        System.err.println("❌ Error al cargar pedidos desde la base de datos: " + e.getMessage());
	    }
	}

}