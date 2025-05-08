package com.project1.SBR_demo.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
         super(message);
    }
}
