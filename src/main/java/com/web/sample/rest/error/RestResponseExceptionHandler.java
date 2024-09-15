package com.web.sample.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ErrorMessage> customerNotFoundException(CustomerNotFoundException customerNotFoundException,
                                                                 WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, customerNotFoundException.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FoodOutletNotFoundException.class})
    public ResponseEntity<ErrorMessage> foodOutletNotFoundException(FoodOutletNotFoundException foodOutletNotFoundException,
                                                                  WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, foodOutletNotFoundException.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
