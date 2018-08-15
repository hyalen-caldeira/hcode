package us.hyalen.hcode.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.core.Domain;

import javax.annotation.PostConstruct;
import javax.validation.ValidatorFactory;

@Component
public class DomainConfig {
    @Autowired
    private ValidatorFactory validatorFactory;

    @PostConstruct
    void injectDependencies() {
        Domain.setValidator(validatorFactory.getValidator());
    }
}