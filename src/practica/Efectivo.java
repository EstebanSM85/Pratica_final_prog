package practica;

import java.util.Map;
import java.util.TreeMap;

public class Efectivo extends PasarelaPago {
	//Atributos propios
	 private double entrega;
	 private double cambio;
	
	 //Constructor
    public Efectivo(double importe,double entrega) {
        super(importe); //uso el constructor de la superclase
        this.entrega=entrega;
        this.cambio=entrega-importe;    
    }
   
    //setter y getter
    public double getEntrega() {
		return entrega;
	}

	public double getCambio() {
		return cambio;
	}
	
	public void setEntrega(double entrega) {
		this.entrega = entrega;
		this.cambio = entrega-this.getImporte();
	}
	
	public TreeMap<Double, Integer> calcularCambio() {
		TreeMap<Double, Integer> cambioDesglose = new TreeMap<>();
	    double[] valores = {200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
	    double restante = cambio;

	    for (double valor : valores) {
	        int cantidad = (int) (restante / valor);
	        if (cantidad > 0) {
	            cambioDesglose.put(valor, cantidad);
	            restante -= cantidad * valor;
	            restante = Math.round(restante * 100.0) / 100.0; // Redondeo para precisión.
	        }
	    }
	    for (Map.Entry<Double, Integer> entry : cambioDesglose.entrySet()) {
	        System.out.println(entry.getValue() + " --> " + entry.getKey() + "€");
	    }

	    return cambioDesglose;
	}

	@Override
    public boolean procesarPago() {
        System.out.println("Pago en efectivo registrado. Importe: " + getImporte() + "€");
        if (cambio>0) {
        	calcularCambio();	
        }else {
        	System.out.println("Entrega correcta, no se ha de devolver.");
        }
        return true;
    }
}
