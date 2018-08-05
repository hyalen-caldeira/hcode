package us.hyalen.hcode.core.user.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.dao.BaseDao;

import java.util.List;
import java.util.Optional;


@Component("userDao_v1")
@Transactional
public class UserDao extends BaseDao {
    public Optional<User> findByUserId(Long userId) {
        return null;
    }

    public void save(User user) {
    }

    public void update(User user) {
    }

    public void delete(Long userId) {
    }

    public List<User> findAllUsers() {
        return null;
    }
}
