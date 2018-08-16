package us.hyalen.hcode.server.repository;

import us.hyalen.hcode.server.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByLoginOrEmail(String login, String email);

    List<UserModel> findByIdIn(List<Long> userIds);

    Optional<UserModel> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}