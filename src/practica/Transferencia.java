package practica;

public class Transferencia extends PasarelaPago {
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    
    
    //Constructor
    public Transferencia(double importe, String numeroCuentaOrigen, String numeroCuentaDestino) {
        super(importe); //El constructor de la superclase
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
    }
    
    //getter y setter
    public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	
	//Métodos para validar las cuentas
    public boolean validarCuentas() {
        // Verifica que los números de cuenta sean válidos
        return numeroCuentaOrigen.matches("\\d{20}") && numeroCuentaDestino.matches("\\d{20}");
    }

	@Override
    public boolean procesarPago() { //sobreescribo el método de la superclase
        System.out.println("Procesando transferencia bancaria...");
        
        if (validarCuentas()) { // Verifica si las cuentas tienen un formato válido usando el metodo 
            System.out.println("Transferencia desde la cuenta: " + numeroCuentaOrigen);
            System.out.println("Transferencia hacia la cuenta: " + numeroCuentaDestino);
            System.out.println("Transferencia exitosa. Importe: " + getImporte() + "€"); //Mensajes de confirmación
            return true;
        } else {
            System.out.println("Transferencia fallida. Las cuentas no son válidas."); //Mensaje de error
            return false;
        }

    }
}
