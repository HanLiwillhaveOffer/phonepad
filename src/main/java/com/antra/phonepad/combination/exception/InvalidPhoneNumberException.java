package com.antra.phonepad.combination.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public String getErrorMessage() {
        return errorMessage;
    }

    private String errorMessage;

    public InvalidPhoneNumberException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public InvalidPhoneNumberException(){

    }
}
