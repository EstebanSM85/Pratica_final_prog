package practica;

import java.util.Date;
import java.util.Calendar;
public class Comida extends Producto {
	
	//Atributos
	private boolean perecedero;
	private float calorias;
	private boolean vegano;
	private Date fecha_envase;
	
	 // Constructor
    public Comida(String nombre, double precio, Date caducidad, String estado, boolean perecedero, float calorias, boolean vegano, Date fecha_envase) {
        super(nombre, precio, caducidad, estado); 
        this.perecedero = perecedero;
        this.calorias = calorias;
        this.vegano = vegano;
        this.fecha_envase = fecha_envase;
    }
    
    @Override
    public String obtener_caducidad() {
    	Date caducidad;
    	Calendar tiempo = Calendar.getInstance();
    	tiempo.setTime(fecha_envase);
    	tiempo.add(Calendar.DAY_OF_YEAR, 10);

    	if (perecedero) {
    		tiempo.setTime(fecha_envase);
    		caducidad=tiempo;
    		return Date.format(caducidad);
    	}else {
    	return caducidad;
    	}
    }
   


}
