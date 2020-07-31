package com.prodinv.validation;

import com.prodinv.models.ImageFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ImageFileConstraint, ImageFile>
{
    @Override
    public void initialize(ImageFileConstraint imageFile) {}

    @Override
    public boolean isValid(ImageFile contactField, ConstraintValidatorContext ctx)
    {
        if(contactField == null)
        {
            return true;
        }
        else
        {
            return contactField != null && contactField.getType().contains("image/");
        }
    }
}
