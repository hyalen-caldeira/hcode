package us.hyalen.hcode.core.user.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.dao.BaseDao;
import us.hyalen.hcode.model.UserModel;

import java.util.List;
import java.util.Optional;


@Component("userDao_v1")
@Transactional
public class UserDao extends BaseDao {
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
}
