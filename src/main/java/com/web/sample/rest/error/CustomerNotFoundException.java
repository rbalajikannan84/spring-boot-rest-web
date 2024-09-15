package com.web.sample.rest.error;

public class CustomerNotFoundException extends Exception
{
    public CustomerNotFoundException()
    {
        super();
    }

    public CustomerNotFoundException(String message){
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public CustomerNotFoundException(Throwable cause){
        super(cause);
    }
}
