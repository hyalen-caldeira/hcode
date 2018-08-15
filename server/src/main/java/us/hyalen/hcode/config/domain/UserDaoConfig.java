package us.hyalen.hcode.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.core.user.v1.User;
import us.hyalen.hcode.core.user.v1.UserDao;

import javax.annotation.PostConstruct;

@Component
public class UserDaoConfig {
    @Autowired
    @Qualifier("userDao_v1")
    private UserDao userDao_v1;

    @PostConstruct
    void injectDependencies() {
        User.setUserDao(userDao_v1);
    }
}
