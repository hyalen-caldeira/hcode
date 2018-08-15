package us.hyalen.hcode.core.company.v1;

import lombok.Setter;
import us.hyalen.hcode.core.Domain;

public class Company extends Domain {
    @Setter
    private static CompanyDao companyDao;
}
