package com.example.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectIsNullExcpetion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectIsNullExcpetion() {
        super("It is not allowed to persisted a null object");
    }

    public ObjectIsNullExcpetion(String ex) {
        super(ex);
    }
}
