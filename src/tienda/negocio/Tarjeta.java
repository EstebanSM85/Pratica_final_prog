package tienda.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarjeta extends PasarelaPago {
	//Atributos propios
    private String numeroTarjeta;
    private String titular;
    private String fechaCaducidad;
    private int codigoSeguridad;

    //Constructor
    public Tarjeta(double importe, String numeroTarjeta, String titular, String fechaCaducidad, int codigoSeguridad) {
        super(importe); //uso el constructor de la superclase
     // Validación del formato del número de tarjeta
        if (!numeroTarjeta.matches("\\d{16}")) {
            throw new TarjetaInvalidaException("Formato de número de tarjeta inválido. Debe cumplir con: 1234 5678 9123 4567 o 3782 822463 10005.");
        }
        
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.codigoSeguridad = codigoSeguridad;
        
     

        
     // Validación de la fecha de caducidad
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            dateFormat.setLenient(false); // Validación estricta
            Date fechaExpiracionDate = dateFormat.parse(fechaCaducidad); // Convierte la fecha de caducidad a un objeto Date
            Date fechaActual = new Date(); // Obtiene la fecha actual

            if (fechaExpiracionDate.before(fechaActual)) { // Comprueba si la tarjeta está vencida comparando si es antes de fecha actual
                throw new TarjetaInvalidaException("La tarjeta está caducada. No se puede guardar la tarjeta."); // Lanza excepción si la tarjeta está caducada
            }
            this.fechaCaducidad = fechaCaducidad; // Asigna la fecha si es válida
        } catch (Exception e) {
            throw new TarjetaInvalidaException("Formato de fecha de caducidad inválido. No se puede guardar la tarjeta."); // Lanza excepción si el formato es incorrecto
        }
    }

    
    
    //getter y setter
    public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
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

	public void setFechaCaducidad(String fechaCaducidad) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy"); // Formato de mes/año
	        dateFormat.setLenient(false); // Validación estricta
	        Date fechaExpiracionDate = dateFormat.parse(fechaCaducidad); // Convierte la fecha a un objeto Date
	        Date fechaActual = new Date(); // Obtiene la fecha actual

	        if (fechaExpiracionDate.before(fechaActual)) { // Comprueba si la tarjeta está caducada
	            throw new IllegalArgumentException("La tarjeta está caducada. No se puede asignar la fecha de caducidad.");
	        }
	        this.fechaCaducidad = fechaCaducidad; // Asigna la fecha si es válida
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Formato de fecha de caducidad inválida. Use el formato MM/yy."); // Manejo de errores
	    }
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	
	//Métodos para comprobar las tarjetas
	public boolean validarFormato() { // Verifica si la tarjeta tiene un formato válido
	       return numeroTarjeta.matches("\\d{16}");
	    }
	
	
	 public String identificarTarjeta() { // Identifico el tipo de tarjeta según el numero
	        if (numeroTarjeta.startsWith("3")) {
	            return "American Express";
	        } else if (numeroTarjeta.startsWith("4")) {
	            return "VISA";
	        } else if (numeroTarjeta.startsWith("5")) {
	            return "MasterCard";
	        } else {
	            return "No válida";
	        }
	    }


	@Override
    public boolean procesarPago() { //sobreescribo el método de la superclase
        System.out.println("Procesando pago con tarjeta de crédito...");
     
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
