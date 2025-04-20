package practica;

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
	
	@Override
    public boolean procesarPago() {
        System.out.println("Pago en efectivo registrado. Importe: " + getImporte() + "€");
        return true;
    }
}
