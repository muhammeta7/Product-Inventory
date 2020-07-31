package com.prodinv.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFileValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER } )
@Retention(RetentionPolicy.RUNTIME)

public @interface ImageFileConstraint
{
    String message() default "Invalid file content type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
