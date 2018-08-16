package us.hyalen.hcode.server.repository;

import us.hyalen.hcode.server.model.RoleModel;
import us.hyalen.hcode.server.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(RoleName roleName);
}