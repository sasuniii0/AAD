package lk.ijse.gdse.o11_backend.exception;

import lk.ijse.gdse.o11_backend.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//// This class is used to handle global exceptions in the application.
/// Exception handling
@RestControllerAdvice
public class GlobalExceptionHandler {
    // class ek denn one exception eka throw krnn one wena
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGenericException(Exception e) {
        // Log the exception or return a custom error response
       return new ResponseEntity(new APIResponse(500,e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity(new APIResponse(404, e.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyFoundException.class)
    public ResponseEntity<APIResponse> handleResourceAlreadyFoundException(ResourceAlreadyFoundException e) {
        return new ResponseEntity(new APIResponse(409, e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourseEmptyException.class)
    public ResponseEntity<APIResponse> handleResourceEmptyException(ResourseEmptyException e) {
        return new ResponseEntity(new APIResponse(204, e.getMessage(), null), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity(new APIResponse(400, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity(new APIResponse(500, "Null pointer exception occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String > erroes = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            erroes.put(error.getField(), error.getDefaultMessage());
        });
        String errorMessage = erroes.toString();
        return new ResponseEntity(new APIResponse(400, errorMessage, null), HttpStatus.BAD_REQUEST);
    }
}
