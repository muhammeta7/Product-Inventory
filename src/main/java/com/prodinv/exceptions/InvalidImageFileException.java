package com.prodinv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class InvalidImageFileException extends RuntimeException
{
    public InvalidImageFileException() {}

    public InvalidImageFileException(String message)
    {
        super(message);
    }

    public InvalidImageFileException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
