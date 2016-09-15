package com.sgannu.ivalidator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

@SuppressWarnings({"unchecked", "rawtypes"})
public class GenericValidator implements ConstraintValidator<Validate, Object> {

   private ValidationFacade validator;

    @Override
    public void initialize(Validate constraintAnnotation) {
        try {
            validator = constraintAnnotation.with().newInstance();
        } catch (Exception e) {
           throw new ValidationException(e); 
        }
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (target instanceof List) {
            for (Object obj : (List) target) {
                isValid = validator.isValid(obj, context) ? isValid : false;
            }
        } else {
            isValid = validator.isValid(target, context);
        }
        return isValid;
    }

}
