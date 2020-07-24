package com.prodinv.dtos;

import com.prodinv.exceptions.InvalidImageFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class RestExceptionHandler
{
    @Autowired
    MessageSource messageSource;

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
}
