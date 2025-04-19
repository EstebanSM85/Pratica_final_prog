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
	private static int contadorCodigo = 1;

				
	//Constructor
	public Cliente(String nombre, String apellidos,String telefono, String direccion,Date fechaAlta, String historial){
		this.nombre=nombre.toLowerCase();
		this.apellidos=apellidos.toUpperCase();
		this.telefono=telefono;
		this.direccion=direccion;
		this.fechaAlta = new Date();
		this.historial ="";
		this.codigo = contadorCodigo;  //Aqui le asigno un codigo unico al crear el cliente
        contadorCodigo++;				// Me aseguro que sume uno cada vez que se cree uno

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
		
	public void setFechaAlta(Date fechaAlta) { 	// en este setter se pide que si esta nulo la fecha actual
		if (fechaAlta==null) {
			this.fechaAlta=new Date();
		}else {
			this.fechaAlta = fechaAlta;
		}
	} 
			
	public String getTelefono() {
		return telefono; 
	} 
			
	// Aquí compruebo  que el número introducido con el setter sea el correcto
	public void setTelefono(String telefono) {
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
	
	public String mostrar()	{
		return "Nombre: "+ getNombre()+
				"\nApellidos: "+getApellidos()+
				"\nTeléfono: "+getTelefono()+
				"\nFecha de alta: "+getFechaAlta()+
				"\nDirección: "+getDireccion()+
				"\nHistorial: "+getHistorial();	
	}
	
}
