package com.sgannu.ivalidator;

import javax.validation.ConstraintValidatorContext;

public interface ValidationFacade<T> {

    boolean isValid(T target, ConstraintValidatorContext context);
    
}
