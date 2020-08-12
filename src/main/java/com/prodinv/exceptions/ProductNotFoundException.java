package com.prodinv.exceptions;

import com.prodinv.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException() { }

    public ProductNotFoundException(String message)
    {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
