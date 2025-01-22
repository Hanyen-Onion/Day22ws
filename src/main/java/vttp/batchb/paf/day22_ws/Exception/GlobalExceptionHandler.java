package vttp.batchb.paf.day22_ws.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vttp.batchb.paf.day22_ws.models.Exceptions.ApiError;
import vttp.batchb.paf.day22_ws.models.Exceptions.ResourceNotFoundException;

import static vttp.batchb.paf.day22_ws.Time.*;

import java.sql.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        ApiError apiError = new ApiError(404, ex.getMessage(), new Date(now()), "");

        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }
    

}
