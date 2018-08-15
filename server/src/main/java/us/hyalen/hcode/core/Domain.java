package us.hyalen.hcode.core;

import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Domain {
    @Setter
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
}
