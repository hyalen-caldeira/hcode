package us.hyalen.hcode.core.user.v1;

import lombok.Getter;
import lombok.Setter;
import us.hyalen.hcode.core.Domain;
import us.hyalen.hcode.core.role.v1.RoleResource;
import us.hyalen.hcode.model.RoleModel;
import us.hyalen.hcode.model.UserModel;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class User extends Domain {
    @Setter
    private static UserDao userDao;
    private static UserMapper mapper = UserMapper.INSTANCE;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Set<RoleResource> roles;

    private User() {}

    public static Optional<User> findById(Long id) {
        return userDao.findByUserId(id);
    }

    public static List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User create() {
        validate();
        return userDao.create(mapper.mapDomainToModel(this));
    }

    public void update() {
        validate();
        userDao.update(mapper.mapDomainToModel(this));
    }

    public void delete() {
        userDao.delete(mapper.mapDomainToModel(this));
    }

    public static class Builder {
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder(User user) {
            this.user = user;
        }

        public Builder withUserResource(UserResource resource) {
            mapper.mapResourceToDomain(resource, user);
            return this;
        }

        public Builder withUserModel(UserModel model) {
            if (model == null)
                return null;

            mapper.mapModelToDomain(model, user);
            return this;
        }

        public Builder withId(Long id) {
            user.id = id;
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

        public Builder withRoles(Set<RoleModel> models) {
            if (models.size() > 0) {
                user.roles = new HashSet<>();

                models.forEach (model -> {
                    user.roles.add(mapper.toRole(model));
                });
            }

            return this;
        }

        public User build() {
            return user;
        }
    }
}
