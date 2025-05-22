package tienda.persistencia;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion_Mejorada {
	// Logger para gestionar mensajes de error e información
	private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());

	// Configuración de parametros de acceso a base de datos
	private static final String NOMBRE_BD = "tienda"; // Nombre de la base de datos
	private static final String UBICACION = "localhost"; // Dirección del servidor de BD
	private static final String PUERTO = "3306"; // Puerto del servidor MySQL
	private static final String USUARIO = "root"; // Usuario de la BD
	private static final String CLAVE = "p2ssWord"; // Contraseña de la BD

	// URL de conexión a MySQL con configuración adicional
	private static final String URL = "jdbc:mysql://" + UBICACION + ":" + PUERTO + "/" + NOMBRE_BD
			+ "?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

	// Constructor privado para evitar que se creen instancias de esta clase
	private Conexion_Mejorada() {
	}

	/**
	 * Establece una conexión con la base de datos MySQL y la retorna.
	 * 
	 * @return Connection si la conexión es exitosa, de lo contrario retorna null.
	 */
	public static Connection conectar() {
		Connection conexion = null;
		try {
			// Uso de Properties para establecer la configuración de la conexión
			Properties props = new Properties();
			props.put("user", USUARIO); // Usuario de la BD
			props.put("password", CLAVE); // Contraseña de la BD
			props.put("useSSL", "false"); // Desactiva SSL (opcional dependiendo de la configuración de MySQL)
			/*
			 * Cuando configuramos una conexión a bbdd y utilizamos un objeto properties
			 * Evitar advertencias: algunos controladores como MYSQL emiten advertencias
			 * Conexión local o fase de desarrollo: no necesito una conexión cifrada
			 * Compatibilidad:algunas versiones del driver (JDBC) lo necesitan para que
			 * funcione
			 */

			// Establece la conexión a la base de datos
			conexion = DriverManager.getConnection(URL, props);
			LOGGER.info("Conexión establecida con la base de datos " + NOMBRE_BD);
		} catch (SQLException e) {
			// Captura errores de conexión y los registra en el log
			LOGGER.log(Level.SEVERE, "Error en la conexión a la base de datos", e);
		}
		return conexion; // Retorna la conexión (puede ser null si falló)
	}
}

