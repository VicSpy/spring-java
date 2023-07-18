package com.example.excpetions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.excpetions.ExcpetionResponse;
import com.example.excpetions.UnsopportedMathException;

@ControllerAdvice
@RestController
public class CustomizedExcpetion extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExcpetionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExcpetionResponse excpetionResponse = new ExcpetionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(excpetionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsopportedMathException.class)
    public final ResponseEntity<ExcpetionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
        ExcpetionResponse excpetionResponse = new ExcpetionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(excpetionResponse, HttpStatus.BAD_REQUEST);
    }
}
