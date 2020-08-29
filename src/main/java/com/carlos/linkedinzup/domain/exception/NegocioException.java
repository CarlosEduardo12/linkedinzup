package com.carlos.linkedinzup.domain.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message){
        super(message);
    }
}
