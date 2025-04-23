package practica;

public class CuentaInvalidaException extends RuntimeException {
    public CuentaInvalidaException(String mensaje) {
        super(mensaje);
    }
}