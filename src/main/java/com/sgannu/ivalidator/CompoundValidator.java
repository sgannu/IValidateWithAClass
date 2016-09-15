package com.sgannu.ivalidator;

import java.util.ArrayDeque;
import java.util.Queue;

import javax.validation.ConstraintValidatorContext;

public abstract class CompoundValidator<T> implements ValidationFacade<T> {

    private Queue<ValidationFacade<T>> validators;
    private boolean returnOnSingleError = false;

    protected CompoundValidator() {
        validators = new ArrayDeque<>();
    }

    @Override
    public final boolean isValid(T target, ConstraintValidatorContext context) {
        boolean isValid = true;
        for (ValidationFacade<T> validator : validators) {
            isValid = validator.isValid(target, context) ? isValid : false;
            if (returnOnSingleError && !isValid) {
                break;
            }
        }
        return isValid;
    }

    public final void addValidator(ValidationFacade<T> validator) {
        validators.add(validator);
    }

    public final void returnOnSingleError(boolean returnOnSingleError) {
        this.returnOnSingleError = returnOnSingleError;
    }
}
