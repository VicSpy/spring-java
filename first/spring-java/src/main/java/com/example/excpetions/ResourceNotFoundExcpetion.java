package com.example.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcpetion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExcpetion(String ex) {
        super(ex);
    }
}
