package com.web.sample.rest.error;

public class FoodOutletNotFoundException extends Exception
{
    public FoodOutletNotFoundException()
    {
        super();
    }

    public FoodOutletNotFoundException(String message){
        super(message);
    }

    public FoodOutletNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public FoodOutletNotFoundException(Throwable cause){
        super(cause);
    }
}
