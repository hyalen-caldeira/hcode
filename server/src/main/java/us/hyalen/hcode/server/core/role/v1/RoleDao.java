package us.hyalen.hcode.server.core.role.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.server.core.BaseDao;
import us.hyalen.hcode.server.model.RoleModel;
import us.hyalen.hcode.server.model.RoleName;
import us.hyalen.hcode.server.repository.RoleRepository;

import java.util.Optional;

@Component("roleDao_v1")
@Transactional
public class RoleDao extends BaseDao {
    @Autowired
    RoleRepository repository;

    public Optional<Role> findByName(RoleName roleName) {
        Optional<RoleModel> model = repository.findByName(roleName);
        Role.Builder builder = (new Role.Builder()).withRoleModel(model.orElse(null));
        return Optional.ofNullable(builder == null ? null : builder.build());
    }
}
