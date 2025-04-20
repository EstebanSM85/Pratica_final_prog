package practica;

public class Tarjeta extends PasarelaPago {
	//Atributos propios
    private String numeroTarjeta;
    private String titular;
    private String fechaExpiracion;
    private int codigoSeguridad;

    //Constructor
    public Tarjeta(double importe, String numeroTarjeta, String titular, String fechaExpiracion, int codigoSeguridad) {
        super(importe); //uso el constructor de la superclase
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }
    
    //getter y setter
    public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public int getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	
	//Métodos para comprobar las tarjetas
	public boolean validarFormato() { // Verifica si la tarjeta tiene un formato válido
	       return numeroTarjeta.matches("\\d{4} \\d{6} \\d{5}") || numeroTarjeta.matches("\\d{4} \\d{4} \\d{4} \\d{4}");
	    }
	
	
	 public String identificarTarjeta() { // Identifico el tipo de tarjeta según el numero
	        if (numeroTarjeta.startsWith("3") && numeroTarjeta.matches("\\d{4} \\d{6} \\d{5}")) {
	            return "American Express";
	        } else if (numeroTarjeta.startsWith("4") && numeroTarjeta.matches("\\d{4} \\d{4} \\d{4} \\d{4}")) {
	            return "VISA";
	        } else if (numeroTarjeta.startsWith("5") && numeroTarjeta.matches("\\d{4} \\d{4} \\d{4} \\d{4}")) {
	            return "MasterCard";
	        } else {
	            return "No válida";
	        }
	    }


	@Override
    public boolean procesarPago() { //sobreescribo el método de la superclase
        System.out.println("Procesando pago con tarjeta de crédito...");
        
        if (!validarFormato()) { //Valido el formato si no es valido imprimo mensaje.
            System.out.println("Número de tarjeta inválido.");
            return false;
        }
     
        String tipoTarjeta = identificarTarjeta(); //identifico el tipo de tarjeta
        if (!tipoTarjeta.equals("No válida")) {
            System.out.println("La tarjeta es " + tipoTarjeta + ", tarjeta aceptada.");
            System.out.println("Pago aprobado. Importe: " + getImporte() + "€");
            return true;
        } else {
            System.out.println("Esta tarjeta no está admitida.");
            return false;
        }

    }
}
