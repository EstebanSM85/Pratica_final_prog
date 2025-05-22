package tienda.modelo;

import java.util.Scanner;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public abstract class Producto implements Serializable {
	//Atributos 
	private String nombre;
    private double precio;
    private Date caducidad; 
    private String estado;
    private Date fechaEnvase;
    
    //Constructor
    public Producto(String nombre, double precio, Date caducidad, String estado,Date fechaEnvase) {
    	this.nombre=nombre;
    	this.precio=precio;    	
    	this.caducidad=caducidad;
    	this.estado=estado;
    	this.fechaEnvase=fechaEnvase;
    }
    
    public Date getFechaEnvase() {
		return fechaEnvase;
	}

	public void setFechaEnvase(Date fechaEnvase) {
		this.fechaEnvase = fechaEnvase;
		this.setCaducidad(obtener_caducidad()); // Recalcula la caducidad automáticamente
	}

	//getter y setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getCaducidad() {
		return caducidad;
	}
	
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void modificarFechaEnvase(String nuevaFechaEnvase)/* throws java.text.ParseException*/ { // captura la excepcion quien llama al método
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false); // Validación estricta
	    try {
	        Date fechaEnvaseConvertida = dateFormat.parse(nuevaFechaEnvase); // Convierte la cadena en Date
	        this.setFechaEnvase(fechaEnvaseConvertida); // Asigna la nueva fecha
	        System.out.println("Fecha de envase modificada correctamente: " + nuevaFechaEnvase);
	    } catch (Exception e) {
	        /*throw*/ new java.text.ParseException("Fecha inválida. Intente nuevamente.", 0);
	    }
	}

	//métodos abstractos se inician en las clases que lo usen
	public abstract Date obtener_caducidad();
	
	public abstract String detalle_producto();
	
	public abstract void modificarAtributosEspecificos(Scanner scanner);


}
