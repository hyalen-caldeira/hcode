package us.hyalen.hcode.core.company.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.core.BaseDao;

@Component("companyDao_v1")
@Transactional
public class CompanyDao extends BaseDao {
}
