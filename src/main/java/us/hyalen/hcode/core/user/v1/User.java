package us.hyalen.hcode.core.user.v1;

import lombok.Getter;
import lombok.Setter;
import us.hyalen.hcode.core.Domain;
import us.hyalen.hcode.model.UserModel;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class User extends Domain {
    @Setter
    private static UserDao userDao;

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;

    private User() {}

    public static Optional<User> findByUserId(Long userId) {
        return userDao.findByUserId(userId);
    }

    // TODO Confirm the List<User>
    public static List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void save() {
        validate();
        userDao.save(this);
    }

    public void update() {
        validate();
        userDao.update(this);
    }

    public void delete(Long userId) {
        userDao.delete(userId);
    }

    public static class Builder {
        private User user;
        private UserMapper userMapper = UserMapper.INSTANCE;

        public Builder() {
            this.user = new User();
        }

        public Builder(User user) {
            this.user = user;
        }

        public Builder fromResource(UserResource userResource) {
            return userMapper.mapResourceToDomainBuilder(userResource);
        }

        public Builder fromModel(UserModel userModel) {
            return userMapper.mapModelToDomainBuilder(userModel);
        }

        public Builder withUserId(Long userId) {
            user.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withLogin(String login) {
            user.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
