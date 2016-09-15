package com.sgannu.ivalidator;

import java.util.ArrayDeque;
import java.util.Queue;

import com.sgannu.ivalidator.ValidationAction.ValidationType;


public class Validates {
    private final Queue<ValidationAction> actions;

    Validates(Queue<ValidationAction> actions) {
        this.actions = actions;
    }

    public Queue<ValidationAction> getActions() {
        return actions;
    }

    public static Validates map() {
        return new Validates(new ArrayDeque<ValidationAction>());
    }

    public Validates empty(String message) {
        addAction(ValidationType.EMPTY, message, null);
        return this;
    }

    public Validates invalid(String message) {
        addAction(ValidationType.INVALID, message, null);
        return this;
    }

    public Validates invalidLength(String message) {
        addAction(ValidationType.INVALID_LENGTH, message, null);
        return this;
    }

    public Validates invalidFormat(String message) {
        addAction(ValidationType.INVALID_FORMAT, message, null);
        return this;
    }

    public Validates notFound(String message) {
        addAction(ValidationType.NOT_FOUND, message, null);
        return this;
    }
    public Validates incompatible(String message) {
        addAction(ValidationType.INCOMPATIBLE, message, null);
        return this;
    }
    
    public Validates empty(String message, String errorNode) {
        addAction(ValidationType.EMPTY, message, errorNode);
        return this;
    }

    public Validates invalid(String message, String errorNode) {
        addAction(ValidationType.INVALID, message, errorNode);
        return this;
    }

    public Validates invalidLength(String message, String errorNode) {
        addAction(ValidationType.INVALID_LENGTH, message, errorNode);
        return this;
    }

    public Validates invalidFormat(String message, String errorNode) {
        addAction(ValidationType.INVALID_FORMAT, message, errorNode);
        return this;
    }

    public Validates notFound(String message, String errorNode) {
        addAction(ValidationType.NOT_FOUND, message, errorNode);
        return this;
    }
    public Validates incompatible(String message, String errorNode) {
        addAction(ValidationType.INCOMPATIBLE, message, errorNode);
        return this;
    }
    
    
    public void addAction(ValidationType validationType, String message, String errorNode) {
        this.actions.add(new ValidationAction(validationType, message, errorNode));
    }
}