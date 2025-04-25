package practica;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Bebida extends Producto {

	//Atributos propios de la clase bebida
	private boolean gaseoso;
	private int medida;
	private boolean lacteo;
		
	//contructor propio de la clase Bebida
	public Bebida(String nombre, double precio, Date caducidad,Date fechaEnvase, boolean gaseoso, int medida, boolean lacteo) {
		super(nombre, precio, caducidad, "Correcto",fechaEnvase); // Uso el constructor Abstract,añado automaticamente correcto al estado
	    this.gaseoso = gaseoso;
	    this.medida = medida;
	    this.lacteo = lacteo;
	    this.setCaducidad(obtener_caducidad()); // Calculamos y asignamos la caducidad automáticamente
	}
	
	
	//getter y setter
	public boolean isGaseoso() {
		return gaseoso;
	}
	
	public void setGaseoso(boolean gaseoso) {
		this.gaseoso = gaseoso;
	}


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
	

	//Métodos
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

	@Override
	public String detalle_producto() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    long diferenciaDias = (getCaducidad().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24); // Diferencia en días

	    // Si el producto ya está caducado
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
	
	@Override
    public void modificarAtributosEspecificos(Scanner scanner) throws java.util.InputMismatchException{ 
		/* Método que usamos para modificar los atributos propios de esta clase
		Uso el throws porque el que captura la exception es el método que lo llama.*/
        System.out.print("¿Es gaseoso? (true/false): ");
        this.gaseoso = scanner.nextBoolean(); //volvemos a elegir la opción que consideremos

        System.out.print("Ingrese medida (ml): ");
        this.medida = scanner.nextInt();

        System.out.print("¿Es lácteo? (true/false): ");
        this.lacteo = scanner.nextBoolean();
        
        this.setCaducidad(obtener_caducidad()); // recalcula la fecha en base a los nuevos parámetros


        System.out.println("Atributos específicos de bebida modificados.");
    }

	
}
