package com.airfrance.userDemo;

public class UserException extends RuntimeException{
    private String message;
    public UserException(String message){
        super(message);
        this.message=message;
    }
    public UserException(){}
}
