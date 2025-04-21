package practica;


import java.util.Date;//importo la clase para guardar la fecha

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
	public Cliente(String nombre, String apellidos,String telefono, String direccion,Date fechaAlta, String historial){
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
            System.err.println("El número de teléfono no es válido. Se asignará 'Desconocido'.");
            this.telefono = "Desconocido"; // Se le asigna esto para que no ponga null, en caso de no tener
        }


	}
	
	//hago los getter y setter para devolver e introducir los datos.
	public String getNombre()	{
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre.toLowerCase();
	}
	
	public String getApellidos()	{
		return apellidos;
	}
			
	public void setApellidos(String apellidos) {
		this.apellidos  = apellidos.toUpperCase();
	}
			
	public Date getFechaAlta() { 
		return fechaAlta; 
	} 
		
	public void setFechaAlta(Date fechaAlta) { 	// Aqui si la fecha de alta esta vacio, asignamos la fecha actual
		if (fechaAlta==null) {
			this.fechaAlta=new Date();
		}else {
			this.fechaAlta = fechaAlta;
		}
	} 
			
	public String getTelefono() {
		return telefono; 
	} 
			
	public void setTelefono(String telefono) { // Aquí compruebo  que el número introducido con el setter sea el correcto
		if (telefono.matches("[6789]\\d{8}+")){
			this.telefono = telefono;
		} else {
			System.out.println("El número de teléfono no es correcto");
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
		return  "------ Información del Cliente ------"+
				"\nNombre: "+ getNombre()+
				"\nApellidos: "+getApellidos()+
				"\nTeléfono: "+getTelefono()+
				"\nFecha de alta: "+getFechaAlta()+
				"\nDirección: "+getDireccion()+
				"\nHistorial: "+getHistorial()+
				"\nCodigo: "+ getCodigo()+
				"\n------------------------------------";

	}
	
}
