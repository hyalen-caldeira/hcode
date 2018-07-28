package us.hyalen.hcode.core;

import java.util.Collections;
import java.util.List;

public class InvalidFieldException extends RuntimeException {
    private List<ValidationError> validationErrors;

    public InvalidFieldException() {
        super();
    }

    public InvalidFieldException(String message, List<ValidationError> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public InvalidFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldException(String message, Throwable cause, List<ValidationError> validationErrors) {
        this(message, cause);
        this.validationErrors = validationErrors;
    }

    public InvalidFieldException(String message, ValidationError validationError) {
        this(message, Collections.singletonList(validationError));
    }

    public List<ValidationError> getValidationErrors() { return validationErrors; }
}