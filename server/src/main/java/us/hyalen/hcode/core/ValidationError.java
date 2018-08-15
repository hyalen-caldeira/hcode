package us.hyalen.hcode.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ValidationError {

    public String name;
    public String message;

    public ValidationError(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public ValidationError() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
