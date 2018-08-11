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
    UserMapper mapper = UserMapper.INSTANCE;

    public Optional<User> findByUserId(Long userId) {
        UserModel model = getSessionFactory().getCurrentSession().get(UserModel.class, userId);
        User.Builder builder = (new User.Builder()).withUserModel(model);

        return Optional.ofNullable(builder.build());
    }

    public Long save(User user) {
        UserModel model = mapper.mapDomainToModel(user);
        getSessionFactory().getCurrentSession().save(model);

        return model.getId();
    }

    public void update(User user) {
    }

    public void delete(Long userId) {
    }

    public List<User> findAllUsers() {
        return null;
    }
}
