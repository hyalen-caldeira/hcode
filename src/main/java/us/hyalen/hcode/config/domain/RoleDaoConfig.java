package us.hyalen.hcode.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.core.role.v1.Role;
import us.hyalen.hcode.core.role.v1.RoleDao;

import javax.annotation.PostConstruct;

@Component
public class RoleDaoConfig {
    @Autowired
    @Qualifier("roleDao_v1")
    private RoleDao roleDao_v1;

    @PostConstruct
    void injectDependencies() {
        Role.setRoleDao(roleDao_v1);
    }
}
