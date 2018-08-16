package us.hyalen.hcode.server.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.server.core.Domain;

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