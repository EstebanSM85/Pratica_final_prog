package tienda.negocio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tienda.modelo.Producto;
import tienda.persistencia.Conexion_Mejorada;

public class GestionProducto {
    // Lista para almacenar objetos Producto en una lista llamada productos
    private List<Producto> productos;

    // Constructor para inicializar la lista uso un ArrayList porque necesito que sea dinamica
    public GestionProducto() {
        productos = new ArrayList<>();
        cargarProductos();
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        String query = "INSERT INTO producto (nombre, precio, fecha_caducidad, estado, fecha_envase, tipo, perecedero, calorias, vegano, gaseoso, medida, lacteo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setDate(3, new java.sql.Date(producto.getCaducidad().getTime()));
            ps.setString(4, producto.getEstado());
            ps.setDate(5, new java.sql.Date(producto.getFechaEnvase().getTime()));
            ps.setString(6, producto instanceof Comida ? "comida" : "bebida");

            if (producto instanceof Comida) {
                Comida comida = (Comida) producto;
                ps.setBoolean(7, comida.isPerecedero());
                ps.setDouble(8, comida.getCalorias());
                ps.setBoolean(9, comida.isVegano());
                ps.setNull(10, java.sql.Types.BOOLEAN);
                ps.setNull(11, java.sql.Types.VARCHAR);
                ps.setNull(12, java.sql.Types.BOOLEAN);
            } else if (producto instanceof Bebida) {
                Bebida bebida = (Bebida) producto;
                ps.setNull(7, java.sql.Types.BOOLEAN);
                ps.setNull(8, java.sql.Types.DECIMAL);
                ps.setNull(9, java.sql.Types.BOOLEAN);
                ps.setBoolean(10, bebida.isGaseoso());
                ps.setInt(11, bebida.getMedida());
                ps.setBoolean(12, bebida.isLacteo());
            }

            ps.executeUpdate();
            System.out.println("✅ Producto agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }

    // Método para listar todos los productos
    public void listarProductos() {
        String query = "SELECT * FROM producto";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.err.println("❌ No hay productos registrados.");
                return;
            }

            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Fecha de caducidad: " + rs.getDate("fecha_caducidad"));
                System.out.println("Estado: " + rs.getString("estado"));
                System.out.println("Fecha de envase: " + rs.getDate("fecha_envase"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar productos: " + e.getMessage());
        }
    }
    
      

    // Método para buscar un producto por nombre
    public Producto buscarProducto(String nombre) {
        String query = "SELECT * FROM producto WHERE nombre = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");

                    // 📌 Si el producto es comida, creamos una instancia de `Comida`
                    if ("comida".equalsIgnoreCase(tipo)) {
                        return new Comida(
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getDate("fecha_caducidad"),
                            rs.getDate("fecha_envase"),
                            rs.getBoolean("perecedero"),
                            rs.getFloat("calorias"),
                            rs.getBoolean("vegano")
                        );
                    } 
                    // 📌 Si el producto es bebida, creamos una instancia de `Bebida`
                    else if ("bebida".equalsIgnoreCase(tipo)) {
                        return new Bebida(
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getDate("fecha_caducidad"),
                            rs.getDate("fecha_envase"),
                            rs.getBoolean("gaseoso"),
                            rs.getInt("medida"),
                            rs.getBoolean("lacteo")
                        );
                    } else {
                        System.err.println("❌ Tipo de producto desconocido.");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar producto: " + e.getMessage());
        }

        System.err.println("❌ Producto no encontrado.");
        return null;
    }

    // Método para eliminar un producto por nombre
    public boolean eliminarProducto(String nombre) {
        String query = "DELETE FROM producto WHERE nombre = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, nombre);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Producto eliminado correctamente.");
                return true;
            } else {
                System.err.println("❌ No se ha eliminado el producto.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    public void modificarProducto(String nombre, double nuevoPrecio, String nuevoEstado) {
        String query = "UPDATE producto SET precio = ?, estado = ? WHERE nombre = ?";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setDouble(1, nuevoPrecio);
            ps.setString(2, nuevoEstado);
            ps.setString(3, nombre);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Producto modificado correctamente.");
            } else {
                System.err.println("❌ No se encontró el producto para modificar.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al modificar producto: " + e.getMessage());
        }
    }
    
   
    
    public void cargarProductos() {
        productos.clear(); // Limpiamos la lista antes de cargar los datos
        String query = "SELECT * FROM producto";

        try (Connection conexion = Conexion_Mejorada.conectar();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");

                // 📌 Si el producto es Comida, lo instanciamos correctamente
                if ("comida".equalsIgnoreCase(tipo)) {
                    Comida comida = new Comida(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDate("fecha_caducidad"),
                        rs.getDate("fecha_envase"),
                        rs.getBoolean("perecedero"),
                        rs.getFloat("calorias"),
                        rs.getBoolean("vegano")
                    );
                    productos.add(comida);
                } 
                // 📌 Si el producto es Bebida, lo instanciamos correctamente
                else if ("bebida".equalsIgnoreCase(tipo)) {
                    Bebida bebida = new Bebida(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDate("fecha_caducidad"),
                        rs.getDate("fecha_envase"),
                        rs.getBoolean("gaseoso"),
                        rs.getInt("medida"),
                        rs.getBoolean("lacteo")
                    );
                    productos.add(bebida);
                } else {
                    System.err.println("❌ Tipo de producto desconocido en la BD.");
                }
            }

            System.out.println("✅ Productos cargados correctamente desde la BD.");
        } catch (SQLException e) {
            System.err.println("❌ Error al cargar productos desde la base de datos: " + e.getMessage());
        }
    }
}
