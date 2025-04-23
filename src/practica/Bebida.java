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
	private Date fechaEnvaseBebida;
		
	//contructor propio de la clase Bebida
	public Bebida(String nombre, double precio, Date caducidad, boolean gaseoso, int medida, boolean lacteo, Date fechaEnvaseBebida) {
		super(nombre, precio, caducidad, "Correcto"); // Uso el constructor Abstract
	    this.gaseoso = gaseoso;
	    this.medida = medida;
	    this.lacteo = lacteo;
	    this.fechaEnvaseBebida = fechaEnvaseBebida;
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
	
	public Date getFechaEnvase() {
		return fechaEnvaseBebida;
	}

	public void setFechaEnvase(Date fecha_envase) {
		this.fechaEnvaseBebida = fecha_envase;
	}

	//Métodos
	@Override
	public Date obtener_caducidad() { //Método para como dice obtener la fecha de caducidad según la opción que se elija
		Calendar tiempo = Calendar.getInstance(); //Instancio la clase Calendar para usarla
        tiempo.setTime(fechaEnvaseBebida); // Introduzco la fecha de envasasado
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
	public String detalle_producto() { // Método que muestra los detalles de los productos
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //Se formatea la fecha para mostrar solo dd-mm-yyyy
	    String nuevaFechaEnvase = dateFormat.format(fechaEnvaseBebida);
	    String nuevaFechaCaducidad = dateFormat.format(getCaducidad()); // Formateo de caducidad
	    
		return "------ Información del Producto ------"+
				"Nombre: " + getNombre() +    //Uso los getter para obtener los datos
		        "\nPrecio: " + getPrecio() +
		        "\nFecha de Caducidad: " +nuevaFechaCaducidad +
		        "\nEstado: " + getEstado() +
		        "\nGaseoso: " + (isGaseoso() ? "Sí" : "No") + //Uso un el operador ? para que si es true ponga si y si es false ponga no
		        "\nMedida: " + getMedida() +
		        "\nLacteo: " + (isLacteo() ? "Sí" : "No") +
		        "\nFecha de Envase: " + nuevaFechaEnvase+
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
