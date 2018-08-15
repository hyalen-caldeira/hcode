package us.hyalen.hcode.core.session.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.core.BaseDao;

@Component("sessionDao_v1")
@Transactional
public class SessionDao extends BaseDao {
}
