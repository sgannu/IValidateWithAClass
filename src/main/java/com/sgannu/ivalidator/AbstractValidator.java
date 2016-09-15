package com.sgannu.ivalidator;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;


public abstract class AbstractValidator<T> implements ValidationFacade<T> {

    private Validates validates;

    protected AbstractValidator(Validates validates) {
        this.validates = validates;
    }

    public final boolean isValid(T target, ConstraintValidatorContext context) {
        for (ValidationAction action : validates.getActions()) {
            if (validate(target, action)) {
                return reject(action, context);
            }
        }
        return true;
    }
    
    protected boolean invalidLength(T target) {
        return false;
    }

    protected boolean invalid(T target) {
        return false;
    }

    protected boolean empty(T target) {
        return StringUtils.isEmpty(String.valueOf(target));
    }

    protected boolean invalidFormat(T target) {
        return false;
    }

    protected boolean notFound(T target) {
        return false;
    }

    protected boolean incompatible(T target) {
        return false;
    }
    

    private boolean validate(T target, ValidationAction action) {
        boolean isError = false;
        switch (action.getValidationType()) {
            case EMPTY:
                isError = empty(target);
                break;
            case INVALID_FORMAT:
                isError = invalidFormat(target);
                break;
            case INVALID:
                isError = invalid(target);
                break;
            case INVALID_LENGTH:
                isError = invalidLength(target);
                break;
            case NOT_FOUND:
                isError = notFound(target);
                break;
            case INCOMPATIBLE:
                isError = incompatible(target);
                break;
            default:
                break;
        }
        return isError;
    }

    private boolean reject(ValidationAction action, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(action.getErrorNode()) ? rejectWithError(action.getMessage(), context) : rejectWithErrorOnSubNode(action.getMessage(), action.getErrorNode(), context);
    }

    private boolean rejectWithError(String errorMessage, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        return false;
    }

    private boolean rejectWithErrorOnSubNode(String errorMessage, String subNode, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addNode(subNode).addConstraintViolation();
        return false;
    }

}
