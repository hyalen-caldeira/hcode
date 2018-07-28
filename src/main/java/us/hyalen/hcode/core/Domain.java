package us.hyalen.hcode.core;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

abstract public class Domain {
    protected static Validator validator;
    protected List<ValidationError> validationErrors = new ArrayList<>();

    protected boolean addValidationError(String name, String message) {
        return validationErrors.add(new ValidationError(name, message));
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void validate() {
        Set<ConstraintViolation<Domain>> constraintViolations = validator.validate(this);
        constraintViolations.forEach(error ->
                validationErrors.add(new ValidationError(error.getPropertyPath().toString(), error.getMessage()))
        );

        if (validationErrors.size() > 0) {
            throw new InvalidFieldException(getClass().getSimpleName() + " has validation errors", validationErrors);
        }
    }

    public static void setValidator(Validator validator) {
        Domain.validator = validator;
    }
}
