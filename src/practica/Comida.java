package practica;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Comida extends Producto { //extends Producto porque hereda de esa clase
	
	//Atributos propios de la clase comida
	private boolean perecedero;
	private float calorias;
	private boolean vegano;
	
	 // Constructor
   public Comida(String nombre, double precio, Date caducidad,Date fechaEnvase, boolean perecedero, float calorias, boolean vegano ) {
        super(nombre, precio, caducidad, "Correcto",fechaEnvase); // Uso el constructor Abstract con la palabra reservada super, añado automaticamente correcto al estado
        this.perecedero = perecedero;
        this.calorias = calorias;
        this.vegano = vegano;
        this.setCaducidad(obtener_caducidad()); // Calculamos y asignamos la caducidad automáticamente
    } 
    
   //setter y getter
    public boolean isPerecedero() { 
		return perecedero;
	}
    
    public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}

	public float getCalorias() {
		return calorias;
	}
	
	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public boolean isVegano() {
		return vegano;
	}
	
	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}


	@Override
    public Date obtener_caducidad() {//Método para como dice obtener la fecha de caducidad según la opción que se elija
        
		Calendar tiempo = Calendar.getInstance(); //Instancio la clase Calendar para usarla
		
		if (perecedero) { // Si perecedero es true, devuelve la fecha de caducidad introducidad más 10 días 
            
            tiempo.setTime(getFechaEnvase()); // Introduzco la fecha de envasasado
            tiempo.add(Calendar.DAY_OF_YEAR, 10); // Sumo los 10 días
            
            return tiempo.getTime();// Devuelve la nueva fecha de caducidad como Date
        }
        else {// Si no es perecedero, devuelve la fecha de caducidad actual 
            return new Date(); 
        }
    }

	@Override
	public String detalle_producto() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	    long diferenciaDias = (getCaducidad().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24); // Diferencia en días

	    // Si el producto esta caducado
	    if (new Date().after(getCaducidad())) {
	        setEstado("CADUCADO"); // Cambia el estado a "CADUCADO"
	       
	    }

	    // Si está próximo a caducar (menos de 5 días)
	    if (diferenciaDias <= 5) {
	        double precioOferta = getPrecio() * 0.7; // Aplica el 30% de descuento
	        return "------ OFERTA ------\n" +
	               "Nombre: " + getNombre() +
	               "\nPrecio con Descuento: " + precioOferta +
	               "\nFecha de Caducidad: " + dateFormat.format(getCaducidad()) +
	               "\nEstado: " + getEstado() +
	               "\nPerecedero: " + (isPerecedero() ? "Sí" : "No") +
	               "\nCalorías: " + getCalorias() +
	               "\nVegano: " + (isVegano() ? "Sí" : "No") +
	               "\nFecha de Envase: " + dateFormat.format(getFechaEnvase()) +
	               "\n------------------------------------";
	    }

	    // Producto en estado normal
	    return "------ Información del Producto ------" +
	           "\nNombre: " + getNombre() +
	           "\nPrecio: " + getPrecio() +
	           "\nFecha de Caducidad: " + dateFormat.format(getCaducidad()) +
	           "\nEstado: " + getEstado() +
	           "\nPerecedero: " + (isPerecedero() ? "Sí" : "No") +
	           "\nCalorías: " + getCalorias() +
	           "\nVegano: " + (isVegano() ? "Sí" : "No") +
	           "\nFecha de Envase: " + dateFormat.format(getFechaEnvase()) +
	           "\n------------------------------------";
	}
	
	@Override
	public  void modificarAtributosEspecificos(Scanner scanner)throws java.util.InputMismatchException {
		/* Método que usamos para modificar los atributos propios de esta clase
		 * Uso el throws porque el que captura la exception es el método que lo llama.*/
		   System.out.print("¿Es perecedero? (true/false): ");
	        this.perecedero = scanner.nextBoolean();//volvemos a elegir la opción que consideremos

	        System.out.print("Ingrese calorías: ");
	        this.calorias = scanner.nextFloat();

	        System.out.print("¿Es vegano? (true/false): ");
	        this.vegano = scanner.nextBoolean();
	        
	        this.setCaducidad(obtener_caducidad()); // recalcula la fecha en base a los nuevos parámetros

	        System.out.println("Atributos específicos de comida modificados.");
	    
	}

    
 
}
