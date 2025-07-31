package lk.ijse.gdse.o13_auth_bn.exception;

import lk.ijse.gdse.o13_auth_bn.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        return new ResponseEntity<>(new ApiResponse(500, "Internal Server Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
