package us.hyalen.hcode.repository;

import us.hyalen.hcode.model.RoleModel;
import us.hyalen.hcode.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(RoleName roleName);
}