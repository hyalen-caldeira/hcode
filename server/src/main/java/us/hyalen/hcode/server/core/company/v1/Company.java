package us.hyalen.hcode.server.core.company.v1;

import lombok.Setter;
import us.hyalen.hcode.server.core.Domain;

public class Company extends Domain {
    @Setter
    private static CompanyDao companyDao;
}
