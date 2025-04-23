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
	private Date fechaEnvase;
	
	 // Constructor
   public Comida(String nombre, double precio, Date caducidad, boolean perecedero, float calorias, boolean vegano, Date fechaEnvase) {
        super(nombre, precio, caducidad, "Correcto"); // Uso el constructor Abstract con la palabra reservada super
        this.perecedero = perecedero;
        this.calorias = calorias;
        this.vegano = vegano;
        this.fechaEnvase = fechaEnvase;
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

	public Date getFechaEnvase() {
		return fechaEnvase;
	}

	public void setFechaEnvase(Date fecha_envase) {
		this.fechaEnvase = fecha_envase;
	}

	@Override
    public Date obtener_caducidad() {//Método para como dice obtener la fecha de caducidad según la opción que se elija
        if (perecedero) { // Si perecedero es true, devuelve la fecha de caducidad introducidad más 10 días 
            Calendar tiempo = Calendar.getInstance(); //Instancio la clase Calendar para usarla
            tiempo.setTime(fechaEnvase); // Introduzco la fecha de envasasado
            tiempo.add(Calendar.DAY_OF_YEAR, 10); // Sumo los 10 días
            
            return tiempo.getTime();// Devuelve la nueva fecha de caducidad como Date
        }
        else {// Si no es perecedero, devuelve la fecha de caducidad actual 
            return new Date(); 
        }
    }

	@Override
	public String detalle_producto() {// Método que muestra los detalles de los productos
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //Se formatea la fecha para mostrar solo dd-mm-yyyy
	    String nuevaFechaEnvase = dateFormat.format(fechaEnvase);
	    String nuevaFechaCaducidad = dateFormat.format(getCaducidad()); // Formateo de caducidad


		return "------ Información del Producto ------"+
				"\nNombre: " + getNombre() +    //Uso los getter para obtener los datos
		        "\nPrecio: " + getPrecio() +
		        "\nFecha de Caducidad: " +nuevaFechaCaducidad +
		        "\nEstado: " + getEstado() +
		        "\nPerecedero: " + (isPerecedero() ? "Sí" : "No") + //Uso un el operador ? para que si es true ponga si y si es false ponga no
		        "\nCalorías: " + getCalorias() +
		        "\nVegano: " + (isVegano() ? "Sí" : "No") +
		        "\nFecha de Envase: " + nuevaFechaEnvase+
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
