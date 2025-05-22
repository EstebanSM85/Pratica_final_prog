package tienda.negocio;

//Excepción personalizada de Tarjeta 

public class TarjetaInvalidaException extends RuntimeException { // El nombre personalizado  y extends RuntimeException porque es de quien hereda
    public TarjetaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
