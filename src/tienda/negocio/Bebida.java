package tienda.negocio;

import java.util.Date;
import java.util.Scanner;

import tienda.modelo.Producto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa una bebida dentro del sistema de productos.
 * Extiende la clase {@code Producto} y agrega atributos específicos como
 * gaseoso, medida y si es lácteo.
 */

public class Bebida extends Producto {

	// Indica si la bebida es gaseosa.
	private boolean gaseoso;
	// Medida de la bebida en mililitros.
	private int medida;
	// Indica si la bebida contiene lácteos.
	private boolean lacteo;
		
	 /**
     * Constructor de la clase {@code Bebida}.
     * 
     * @param nombre Nombre de la bebida.
     * @param precio Precio del producto.
     * @param caducidad Fecha de caducidad.
     * @param fechaEnvase Fecha en la que fue envasada.
     * @param gaseoso {@code true} si la bebida es gaseosa, {@code false} en caso contrario.
     * @param medida Medida de la bebida en mililitros.
     * @param lacteo {@code true} si la bebida contiene lácteos, {@code false} en caso contrario.
     */
	public Bebida(String nombre, double precio, Date caducidad,Date fechaEnvase, boolean gaseoso, int medida, boolean lacteo) {
		super(nombre, precio, caducidad, "Correcto",fechaEnvase); // Uso el constructor Abstract,añado automaticamente correcto al estado
	    this.gaseoso = gaseoso;
	    this.medida = medida;
	    this.lacteo = lacteo;
	    this.setCaducidad(obtener_caducidad()); // Calculamos y asignamos la caducidad automáticamente
	}
	
	
	/**
     * Obtiene si la bebida es gaseosa.
     * 
     * @return {@code true} si es gaseosa, {@code false} en caso contrario.
     */
	public boolean isGaseoso() {
		return gaseoso;
	}
	
	/*
    * Establece si la bebida es gaseosa.
    * 
    * @param gaseoso {@code true} para establecer como gaseosa, {@code false} en caso contrario.
    */
	public void setGaseoso(boolean gaseoso) {
		this.gaseoso = gaseoso;
	}
	
	// Métodos getter y setter para medida y lacteo...
	public int getMedida() {
		return medida;
	}
	
	public void setMedida(int medida) {
		this.medida = medida;
	}


	public boolean isLacteo() {
		return lacteo;
	}
	
	public void setLacteo(boolean lacteo) {
		this.lacteo = lacteo;
	}
	

	/**
     * Calcula automáticamente la fecha de caducidad de la bebida dependiendo de si es lácteo o no.
     * Si es lácteo, caduca en 10 días desde la fecha de envasado.
     * Si no es lácteo, caduca en 20 días desde la fecha de envasado.
     * 
     * @return La fecha de caducidad calculada.
     */

	@Override
	public Date obtener_caducidad() { //Método para como dice obtener la fecha de caducidad según la opción que se elija
		Calendar tiempo = Calendar.getInstance(); //Instancio la clase Calendar para usarla
        tiempo.setTime(getFechaEnvase()); // Introduzco la fecha de envasasado
		  if (lacteo) { // Si lacteo es true, devuelve la fecha de caducidad introducidad más 10 días 
	            tiempo.add(Calendar.DAY_OF_YEAR, 10); // Sumo los 10 días
	            return tiempo.getTime();// Devuelve la nueva fecha de caducidad como Date
	        }
	        else {  // Si no es lacteo, devuelve la fecha de caducidad introducidad más 20 días 
	            tiempo.add(Calendar.DAY_OF_YEAR, 20); // Sumo los 20 días
	            return tiempo.getTime();// Devuelve la nueva fecha de caducidad como Date
	        }

	}
	
	/**
     * Muestra los detalles del producto, incluyendo su estado de caducidad y posibles descuentos.
     * 
     * @return Cadena con la información del producto.
     */

	@Override
	public String detalle_producto() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    long diferenciaDias = (getCaducidad().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24); // Diferencia en días

	    // Si el producto ya está caducado
	    if (new Date().after(getCaducidad())) {
	        setEstado("CADUCADO"); // Cambia el estado a "CADUCADO"
	        return null;
	    }

	    // Si está próximo a caducar (menos de 5 días)
	    if (diferenciaDias <= 5) {
	        double precioOferta = getPrecio() * 0.7; // Aplica el 30% de descuento
	        return "------ OFERTA ------\n" +
	               "Nombre: " + getNombre() +
	               "\nPrecio con Descuento: " + precioOferta +
	               "\nFecha de Caducidad: " + dateFormat.format(getCaducidad()) +
	               "\nEstado: " + getEstado() +
	               "\nGaseoso: " + (isGaseoso() ? "Sí" : "No") +
	               "\nMedida: " + getMedida() + "ml" +
	               "\nLácteo: " + (isLacteo() ? "Sí" : "No") +
	               "\nFecha de Envase: " + dateFormat.format(getFechaEnvase()) +
	               "\n------------------------------------";
	    }

	    // Producto en estado normal
	    return "------ Información del Producto ------" +
	           "\nNombre: " + getNombre() +
	           "\nPrecio: " + getPrecio() +
	           "\nFecha de Caducidad: " + dateFormat.format(getCaducidad()) +
	           "\nEstado: " + getEstado() +
	           "\nGaseoso: " + (isGaseoso() ? "Sí" : "No") +
	           "\nMedida: " + getMedida() + "ml" +
	           "\nLácteo: " + (isLacteo() ? "Sí" : "No") +
	           "\nFecha de Envase: " + dateFormat.format(getFechaEnvase()) +
	           "\n------------------------------------";
	}
	
	/**
     * Modifica los atributos específicos de la bebida a través de la entrada del usuario.
     * 
     * @param scanner Objeto {@code Scanner} para recibir entrada del usuario.
     * @throws java.util.InputMismatchException Si la entrada del usuario es inválida.
     */
	@Override
    public void modificarAtributosEspecificos(Scanner scanner) throws java.util.InputMismatchException{ 
		/* Método que usamos para modificar los atributos propios de esta clase
		Uso el throws porque el que captura la exception es el método que lo llama.*/
        System.out.print("¿Es gaseoso? (true/false): ");
        this.gaseoso = scanner.nextBoolean(); 

        System.out.print("Ingrese medida (ml): ");
        this.medida = scanner.nextInt();

        System.out.print("¿Es lácteo? (true/false): ");
        this.lacteo = scanner.nextBoolean();
        
        this.setCaducidad(obtener_caducidad()); // recalcula la fecha en base a los nuevos parámetros


        System.out.println("Atributos específicos de bebida modificados.");
    }

	
}
