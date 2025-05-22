package tienda.negocio;

//Excepción personalizada del Importe

public class ImporteInsuficienteException extends Exception { // El nombre personalizado  y extends RuntimeException porque es de quien hereda
 public ImporteInsuficienteException(String mensaje) {
     super(mensaje);
 }
}