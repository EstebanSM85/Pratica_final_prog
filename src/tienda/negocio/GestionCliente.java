package tienda.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tienda.modelo.Cliente;
import tienda.persistencia.Conexion_Mejorada;

public class GestionCliente {
    private static final Logger LOGGER = Logger.getLogger(GestionCliente.class.getName());
    private List<Cliente> clientes;

    public GestionCliente() throws Exception {
        this.clientes = new ArrayList<>();
        cargarClientes(); // Al iniciar, cargamos clientes desde la base de datos
    }

    // 🔹 Agregar un cliente a la base de datos
    public void agregarCliente(Cliente cliente) {
        String query = "INSERT INTO cliente (nombre, apellidos, telefono, direccion, fecha_alta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setDate(5, new java.sql.Date(cliente.getFechaAlta().getTime()));

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Cliente agregado: " + cliente.getNombre());
            } else {
                System.err.println("❌ No se pudo agregar el cliente.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar cliente", e);
        }
    }

    // 🔹 Listar todos los clientes desde la base de datos
    public void listarClientes() throws TelefonoInvalidoException {
        String query = "SELECT * FROM cliente";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.err.println("❌ No hay clientes registrados.");
                return;
            }

            while (rs.next()) {
                // ✅ Ahora recuperamos correctamente el código de la BD
                int codigoBD = rs.getInt("Codigo");

                Cliente cliente = new Cliente(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_alta"),
                    ""
                );

                cliente.setCodigo(codigoBD); // 🔹 Asigna el código correcto
                clientes.add(cliente);

                // ✅ Muestra el código al imprimir los clientes
                System.out.println("Código: " + cliente.getCodigo());
                System.out.println(cliente.mostrar());
                System.out.println(" ");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar clientes", e);
        }
    }

    // 🔹 Buscar un cliente por su código en la base de datos
    public Cliente buscarCliente(int codigo) throws TelefonoInvalidoException {
        String query = "SELECT * FROM cliente WHERE Codigo = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getDate("fecha_alta"),
                            ""
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar cliente", e);
        }

        System.err.println("❌ Cliente no encontrado");
        return null;
    }

    // 🔹 Eliminar un cliente de la base de datos
    public boolean eliminarCliente(int codigo) {
        String query = "DELETE FROM cliente WHERE Codigo = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setInt(1, codigo);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Cliente eliminado: " + codigo);
                return true;
            } else {
                System.err.println("❌ No se ha eliminado.");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar cliente", e);
            return false;
        }
    }

    // 🔹 Cargar clientes desde la base de datos al iniciar la aplicación
    public void cargarClientes() throws TelefonoInvalidoException {
        clientes.clear(); // Limpiar la lista antes de cargar datos
        String query = "SELECT * FROM cliente";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_alta"),
                    ""
                );
                cliente.setCodigo(rs.getInt("Codigo")); // Aseguramos que el código sea el de la BD
                clientes.add(cliente);
            }

            System.out.println("✅ Clientes cargados correctamente desde la BD.");
        } catch (SQLException e) {
            System.err.println("❌ Error al cargar clientes desde la base de datos: " + e.getMessage());
        }
    }
}