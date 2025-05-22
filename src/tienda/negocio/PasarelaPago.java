package tienda.negocio;

public abstract class PasarelaPago {
	//atributo
    private double importe;
    
    //Constructor
    public PasarelaPago(double importe) {
        this.importe = importe;
    }
    
    //setter y getter

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    //método para usar en las clases
    public abstract boolean procesarPago();
}
