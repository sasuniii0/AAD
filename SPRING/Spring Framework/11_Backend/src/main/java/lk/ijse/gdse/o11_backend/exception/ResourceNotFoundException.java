package lk.ijse.gdse.o11_backend.exception;

// custom exception classes
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
