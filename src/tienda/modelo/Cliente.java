package tienda.modelo;

import java.util.Date;//Se importa la clase para guardar la fecha

import tienda.negocio.TelefonoInvalidoException;

import java.io.Serializable; //Se importa para poder hacer la persistencia de datos
import java.text.SimpleDateFormat;//Se importa para dar formato a la fecha


/**
 * Representa un cliente dentro del sistema de gestión.
 * Implementa {@code Serializable} para la persistencia de datos.
 */


public class Cliente implements Serializable {
	// Atributos de cliente
	private String nombre;
	private String apellidos;
	private Date fechaAlta;
	private String telefono;
	private String direccion;
	private String historial;
	private int codigo;
	private static int contadorCodigo = 1;// Contador para asignación de códigos únicos



				
	/**
     * Constructor de la clase {@code Cliente}.
     *
     * @param nombre Nombre del cliente.
     * @param apellidos Apellidos del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param direccion Dirección del cliente.
     * @param fechaAlta Fecha de alta del cliente en el sistema.
     * @param historial Historial de compras o interacciones del cliente.
     * @throws TelefonoInvalidoException Si el número de teléfono es inválido.
     */


	public Cliente(String nombre, String apellidos,String telefono, String direccion,Date fechaAlta, String historial) throws TelefonoInvalidoException {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.fechaAlta = new Date(); //Asigna la fecha actual como fecha de alta
		this.historial ="";
		this.codigo = contadorCodigo;  //Asigna un codigo único al crear el cliente
        contadorCodigo++;				// Incrementa el contador
        
     	// validación de el número de telefono
        
        if (telefono.matches("[6789]\\d{8}")) { // El número debe empezar con 6, 7, 8, o 9 y tener 9 dígitos
            this.telefono = telefono;
        } else {
        	throw new TelefonoInvalidoException("El número de teléfono no es válido.");
        	
        }


	}
	
	// Métodos getter y setter con documentación Javadoc

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
	public String getNombre()	{
		return nombre;
	}
	
	/**
     * Establece el nombre del cliente.
     *
     * @param nombre Nuevo nombre del cliente.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
     * Obtiene el apelllido del cliente.
     *
     * @return apellidos del cliente.
     */
	public String getApellidos()	{
		return apellidos;
	}
	
	/**
     * Establece el aoellido del cliente.
     *
     * @param apellidos Nuevo apellidos del cliente.
     */
	public void setApellidos(String apellidos) {
		this.apellidos  = apellidos;
	}
	
	/**
     * Obtiene Fecha de alta del cliente.
     *
     * @return fechaAlta del cliente.
     */		
	public Date getFechaAlta() { 
		return fechaAlta; 
	} 
	
	/**
     * Establece una nueva fecha de alta.
     *
     * @param nuevaFecha Nueva fecha en formato "dd-MM-yyyy".
     * @throws java.text.ParseException Si la fecha introducida no es válida.
     */

	public void setFechaAlta(String nuevaFecha) throws java.text.ParseException {//Uso el throws porque el que captura la exception es el método que lo llama.
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");//Instancia el objeto dateFormat
	    dateFormat.setLenient(false); // Validación estricta para evitar fechas irreales

	   
	    if (nuevaFecha.matches("\\d{2}-\\d{2}-\\d{4}")) { // Valida si el String cumple con el formato "dd-MM-yyyy"
	        // Intenta convertir el String a un objeto Date usando parse(), que lanza ParseException si la fecha es inválida
	        Date fechaFormateada = dateFormat.parse(nuevaFecha); 
	        this.fechaAlta = fechaFormateada;// Asigna la fecha si es correcta
	        System.out.println("Fecha de alta establecida correctamente: " + nuevaFecha);//mensaje de confirmación
	    } else {
	    	System.err.print("Fecha invalida, introduce 'dd-mm-yyyy'."); 
	    	/* Mensaje de error si no es el formato correcto
	    	 * (si es el formato correcto pero fecha invalida lo 
	    	 * lanza el 'catch' del método que lo llama))*/
	    	
	    }
	}
			
	public String getTelefono() {
		return telefono; 
	} 
			
	public void setTelefono(String telefono) throws TelefonoInvalidoException  { 
		/* Aquí compruebo  que el número introducido con el setter sea el correcto,
		 * throws es el que captura la exception, es el método que lo llama.*/
		if (telefono.matches("[6789]\\d{8}+")){ // Como en el constructor el número debe empezar con 6, 7, 8, o 9 y tener 9 dígitos
			this.telefono = telefono;
		} else {
			System.err.println("El número de teléfono no es correcto, no se modifica");// Mensaje de error
		}
	}
			
	public String getDireccion() {
		return direccion;
	} 
			
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
			
	public String getHistorial() {
		return historial;
	} 
			
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

	public static void setContadorCodigo(int nuevoContador) {
	    contadorCodigo = nuevoContador; // Actualiza el contador estático
	}

	public static int getContadorCodigo() {
		return contadorCodigo;
	}
	
	/**
     * Muestra la información del cliente.
     *
     * @return Cadena con los detalles del cliente.
     */

	public String mostrar()	{ // Motramos los datos del cliente usando los getter
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //Se formatea la fecha para mostrar solo dd-mm-yyyy
	    String fechaFormateada = dateFormat.format(fechaAlta);
		
		return  "------ Información del Cliente ------"+
				"\nNombre: "+ getNombre()+
				"\nApellidos: "+getApellidos()+
				"\nTeléfono: "+getTelefono()+
				"\nDirección: "+getDireccion()+
				"\nFecha de alta: "+fechaFormateada+				
				"\nHistorial: "+getHistorial()+
				"\nCodigo: "+ getCodigo()+
				"\n------------------------------------";

	}

				

	
}
