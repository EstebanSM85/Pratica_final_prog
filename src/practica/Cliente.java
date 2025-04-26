package practica;

import java.util.Date;//Se importa la clase para guardar la fecha
import java.text.SimpleDateFormat;//Se importa para dar formato a la fecha

public class Cliente {
	// Aqui pongo los atributos
	private String nombre;
	private String apellidos;
	private Date fechaAlta;
	private String telefono;
	private String direccion;
	private String historial;
	private int codigo;
	private static int contadorCodigo = 1;// lo inicio a 1 para que el primer numero asignado sea 1

				
	//Constructor
	public Cliente(String nombre, String apellidos,String telefono, String direccion,Date fechaAlta, String historial) throws TelefonoInvalidoException {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.fechaAlta = new Date(); //Asigno la fecha actual como fecha de alta
		this.historial ="";
		this.codigo = contadorCodigo;  //Aqui le asigno un codigo único al crear el cliente
        contadorCodigo++;				// Me aseguro que sume uno cada vez que se cree uno
        
     	// Se comprueba el numero de telegono que sea correcto
        
        if (telefono.matches("[6789]\\d{8}")) { // El número debe empezar con 6, 7, 8, o 9 y tener 9 dígitos
            this.telefono = telefono;
        } else {
        	throw new TelefonoInvalidoException("El número de teléfono no es válido.");
        	
        }


	}
	
	//hago los getter y setter para devolver e introducir los datos.
	public String getNombre()	{
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos()	{
		return apellidos;
	}
			
	public void setApellidos(String apellidos) {
		this.apellidos  = apellidos;
	}
			
	public Date getFechaAlta() { 
		return fechaAlta; 
	} 
		
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
