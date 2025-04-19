package practica;

import java.util.Date;
public abstract class Producto {
	//Atributos
	private String nombre;
    private double precio;
    private Date caducidad; 
    private String estado;
    
    //Constructor
    public Producto(String nombre, double precio, Date caducidad, String estado) {
    	this.nombre=nombre;
    	this.precio=precio;
    	this.caducidad=caducidad;
    	this.estado=estado;
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
	
    
	//métodos abstractos
	public abstract Date obtener_caducidad(); 
	
	public abstract String detalle_producto();

}
