package us.hyalen.hcode.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.core.company.v1.Company;
import us.hyalen.hcode.core.company.v1.CompanyDao;

import javax.annotation.PostConstruct;

@Component
public class CompanyDaoConfig {
    @Autowired
    @Qualifier("companyDao_v1")
    private CompanyDao companyDao_v1;

    @PostConstruct
    void injectDependencies() {
        Company.setCompanyDao(companyDao_v1);
    }
}
