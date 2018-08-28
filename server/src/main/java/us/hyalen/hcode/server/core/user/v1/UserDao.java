package us.hyalen.hcode.server.core.user.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.server.core.BaseDao;
import us.hyalen.hcode.server.model.UserModel;
import us.hyalen.hcode.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Component("userDao_v1")
@Transactional
public class UserDao extends BaseDao {
    @Autowired
    UserRepository repository;

    public Optional<User> findByUserId(Long userId) {
        UserModel model = getSessionFactory().getCurrentSession().get(UserModel.class, userId);
        User.Builder builder = (new User.Builder()).withUserModel(model);
        return Optional.ofNullable(builder == null ? null : builder.build());
    }

    public User create(UserModel model) {
        getSessionFactory().getCurrentSession().save(model);
        return new User.Builder().withUserModel(model).build();
    }

    public void update(UserModel model) {
        getSessionFactory().getCurrentSession().merge(model);
    }

    public void delete(UserModel model) {
        getSessionFactory().getCurrentSession().delete(model);
    }

    public List<User> findAllUsers() {
        return null;
    }

    // --------- Repositories
    public Optional<User> findByUsername(String username) {
        Optional<UserModel> model = repository.findByUsername(username);
        User.Builder builder = (new User.Builder()).withUserModel(model.orElse(null));
        return Optional.ofNullable(builder == null ? null : builder.build());
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
