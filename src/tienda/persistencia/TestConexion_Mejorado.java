package tienda.persistencia;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConexion_Mejorado {

	// Consultas SQL definidas como constantes
    private static final String SELECT_SQL = "SELECT * FROM USUARIO";
    private static final String INSERT_SQL = "INSERT INTO usuario (usuario, clave) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE usuario SET usuario = ?, clave = ? WHERE idusuario = ?";

    /**
     * Realiza una consulta SELECT sobre la tabla usuario.
     * Recupera todos los registros y los imprime en la consola.
     */
    public static void consultaSelect() {
		try (Connection cn = Conexion_Mejorada.conectar();
				PreparedStatement ps = cn.prepareStatement(SELECT_SQL);
				ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
				int idUsuario = rs.getInt(1);
				String usuario = rs.getString(2);
				String clave = rs.getString(3);

				// Imprime solo los datos sin detalles adicionales
				System.out.println(idUsuario + " | " + usuario + " | " + clave);
            }

        } catch (SQLException e) {
			System.err.println("Error al ejecutar consulta SELECT: " + e.getMessage());
        }
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * Utiliza un PreparedStatement para prevenir inyección SQL.
     * 
     * @param usuario Nombre del usuario a insertar
     * @param clave Clave del usuario a insertar
     */
    public static void consultaInsert(String usuario, String clave) {
		try (Connection cn = Conexion_Mejorada.conectar();
             PreparedStatement ps = cn.prepareStatement(INSERT_SQL)) {

			ps.setString(1, usuario);
			ps.setString(2, clave);

			ps.executeUpdate();
			// Imprime solo el mensaje sin detalles adicionales
			System.out.println("Usuario insertado correctamente en la base de datos");

        } catch (SQLException e) {
			System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un usuario por su ID.
     * Usa PreparedStatement para mejorar la seguridad y evitar SQL Injection.
     * 
     * @param idUsuario ID del usuario a actualizar
     * @param usuario Nuevo nombre de usuario
     * @param clave Nueva clave del usuario
     */
    public static void consultaUpdate(int idUsuario, String usuario, String clave) {
		try (Connection cn = Conexion_Mejorada.conectar();
             PreparedStatement ps = cn.prepareStatement(UPDATE_SQL)) {

			ps.setString(1, usuario);
			ps.setString(2, clave);
			ps.setInt(3, idUsuario);

			int resultado = ps.executeUpdate();

            if (resultado > 0) {
				System.out.println("Usuario actualizado correctamente");
            } else {
				System.out.println("No se encontró el usuario a actualizar");
            }

        } catch (SQLException e) {
			System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Descomentar la función que se quiera probar
		// Recupera y muestra todos los usuarios en la base de datos
		consultaSelect();
		// Inserta un nuevo usuario
		
		// consultaInsert("Alejandro", "qwerty");
		// "Victor" y clave "999"
		// consultaUpdate(3, "Javier", "987"); // Actualiza el usuario con ID 3
    }
}
