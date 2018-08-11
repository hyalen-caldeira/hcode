package us.hyalen.hcode.core.role.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.dao.BaseDao;

@Component("roleDao_v1")
@Transactional
public class RoleDao extends BaseDao {
}
