package practica;

import java.util.Date;
import java.util.Calendar;
public class Bebida extends Producto {

	//Atributos propios de la clase bebida
	private boolean gaseoso;
	private int medida;
	private boolean lacteo;
	private Date fecha_envase;
		
	//contructor propio de la clase Bebida
	public Bebida(String nombre, double precio, Date caducidad, String estado, boolean gaseoso, int medida, boolean lacteo, Date Fecha_envase) {
		super(nombre, precio, caducidad, estado); // Uso el constructor Abstract
	    this.gaseoso = gaseoso;
	    this.medida = medida;
	    this.lacteo = lacteo;
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
	
	public Date getFecha_envase() {
		return fecha_envase;
	}

	public void setFecha_envase(Date fecha_envase) {
		this.fecha_envase = fecha_envase;
	}


	@Override
	public Date obtener_caducidad() {
		Calendar tiempo = Calendar.getInstance(); //Instancio la clase Calendar para usarla
        tiempo.setTime(fecha_envase); // Introduzco la fecha de envasasado
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
		return "Nombre: " + getNombre() +    //Uso los getter para obtener los datos
		        "\nPrecio: " + getPrecio() +
		        "\nFecha de Caducidad: " +getCaducidad() +
		        "\nEstado: " + getEstado() +
		        "\nPerecedero: " + (isGaseoso() ? "Sí" : "No") + //Uso un el operador ? para que si es true ponga si y si es false ponga no
		        "\nCalorías: " + getMedida() +
		        "\nVegano: " + (isLacteo() ? "Sí" : "No") +
		        "\nFecha de Envase: " + getFecha_envase();
	}
	
}
