package tienda.negocio;

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
	
	public TreeMap<Double, Integer> calcularCambio() { // Se calcula el cambio a devolver
		TreeMap<Double, Integer> cambioDesglose = new TreeMap<>(); // Se usa treeMap para tener un orden
	    double[] valores = {200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
	    double restante = cambio;

	    for (double valor : valores) { //Recorre el array valores
	        int cantidad = (int) (restante / valor); // Calcula la cantidad de billete/moneda
	        if (cantidad > 0) {
	            cambioDesglose.put(valor, cantidad); //Añade el valor y cantidad al treeMap 
	            restante -= cantidad * valor; // Se resta para saber la diferencia
	            restante = Math.round(restante * 100.0) / 100.0; // Redondea para precisión.
	        }
	    }
	    for (Map.Entry<Double, Integer> entry : cambioDesglose.entrySet()) { //se usa Map.Entry para guardar los valores y keys de cambioDesglose
	        System.out.println(entry.getValue() + " --> " + entry.getKey() + "€"); //Se muestra el resutado
	    }

	    return cambioDesglose;
	}

	@Override
    public boolean procesarPago() { //sobreescribo el método de la superclase
        System.out.println("Pago en efectivo registrado. Importe: " + getImporte() + "€");
        if (cambio>0) { // se calcula el cambio y se muestra en caso de que sea mayor de 0
        	calcularCambio();
        	System.out.println("Pago realizado");//Mensaje de confirmación
        }else {
        	System.out.println("Entrega correcta, no se ha de devolver.");//Mensaje de confirmación
        }
        return true;
    }
}
