package com.prodinv.dtos;

import com.prodinv.exceptions.InvalidImageFileException;
import com.prodinv.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class RestExceptionHandler
{
    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(InvalidImageFileException.class)
    public ResponseEntity<?> handleInvalidImageFileException(InvalidImageFileException iife, HttpServletRequest request)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        iife.printStackTrace(pw);

        ErrorDetail error = new ErrorDetail("Invalid media type",
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                sw.toString());

        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException pnfe, HttpServletRequest request)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pnfe.printStackTrace(pw);

        ErrorDetail error = new ErrorDetail("Invalid product id",
                HttpStatus.NOT_FOUND.value(),
                sw.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
