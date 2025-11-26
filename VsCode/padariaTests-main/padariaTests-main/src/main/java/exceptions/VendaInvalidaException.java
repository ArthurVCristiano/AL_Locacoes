package exceptions;

public class VendaInvalidaException extends RuntimeException {
    public VendaInvalidaException(String message) {
        super(message);
    }
}
