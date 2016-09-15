package com.sgannu.ivalidator;

public class ValidationAction {
    public enum ValidationType {
        EMPTY, INVALID_FORMAT, INVALID_LENGTH, NOT_FOUND, INVALID, VALID, INCOMPATIBLE
    };

    private ValidationType validationType;
    private String message;
    private String errorNode;

    ValidationAction(ValidationType validationType, String message) {
        this.validationType = validationType;
        this.message = message;
    }
    
    ValidationAction(ValidationType validationType, String message, String errorNode) {
        this.validationType = validationType;
        this.message = message;
        this.errorNode = errorNode;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorNode() {
        return errorNode;
    }
}