package tienda.negocio;

//Excepción personalizada de teléfono

public class TelefonoInvalidoException extends Exception { // El nombre personalizado  y extends RuntimeException porque es de quien hereda
    public TelefonoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
