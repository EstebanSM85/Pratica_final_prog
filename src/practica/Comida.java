package practica;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Comida extends Producto {
	
	//Atributos de la clase comida
	private boolean perecedero;
	private float calorias;
	private boolean vegano;
	private Date fecha_envase;
	
	 // Constructor
   public Comida(String nombre, double precio, Date caducidad, String estado, boolean perecedero, float calorias, boolean vegano, Date fecha_envase) {
        super(nombre, precio, caducidad, estado); // Uso el constructor Abstract
        this.perecedero = perecedero;
        this.calorias = calorias;
        this.vegano = vegano;
        this.fecha_envase = fecha_envase;
    } 
    
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

	public Date getFecha_envase() {
		return fecha_envase;
	}

	public void setFecha_envase(Date fecha_envase) {
		this.fecha_envase = fecha_envase;
	}

	@Override
    public Date obtener_caducidad() {
        if (perecedero) { // Si perecedero es true
            Calendar tiempo = Calendar.getInstance();
            tiempo.setTime(fecha_envase); // Introduzco la fecha de envasasado
            tiempo.add(Calendar.DAY_OF_YEAR, 10); // Sumo los 10 días
            
            return tiempo.getTime();// Devuelve la nueva fecha de caducidad como Date
        }
        else {// Si no es perecedero, devuelve la fecha de caducidad introducidad 
            return getCaducidad(); 
        }
    }

	@Override
	public String detalle_producto() {
		return "Nombre: " + getNombre() +    //Uso los getter para obtener los datos
		        "\nPrecio: " + getPrecio() +
		        "\nFecha de Caducidad: " +getCaducidad() +
		        "\nEstado: " + getEstado() +
		        "\nPerecedero: " + (isPerecedero() ? "Sí" : "No") + //Uso un el operador ? para que si es tur ponga si y si es false ponga no
		        "\nCalorías: " + getCalorias() +
		        "\nVegano: " + (isVegano() ? "Sí" : "No") +
		        "\nFecha de Envase: " + getFecha_envase();

	}

    
 
}
