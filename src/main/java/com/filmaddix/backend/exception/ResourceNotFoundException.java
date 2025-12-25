package com.filmaddix.backend.exception;

public class ResourceNotFoundException extends RuntimeException{
     public ResourceNotFoundException(String message){
        super(message);
     }
}

