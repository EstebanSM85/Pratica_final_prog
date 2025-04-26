package practica;

//Excepción personalizada de Cuenta

public class CuentaInvalidaException extends RuntimeException { // El nombre personalizado  y extends RuntimeException porque es de quien hereda
    public CuentaInvalidaException(String mensaje) {
        super(mensaje);
    }
}