package com.example.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsopportedMathException extends RuntimeException {

    public UnsopportedMathException(String ex) {
        super(ex);
    }

    private static final long serialVersionUID = 1L;
}
