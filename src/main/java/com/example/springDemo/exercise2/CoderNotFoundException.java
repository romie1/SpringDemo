package com.example.springDemo.exercise2;

public class CoderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    CoderNotFoundException(Long id) {
        super("Coder " + id + " not found");
    }
}