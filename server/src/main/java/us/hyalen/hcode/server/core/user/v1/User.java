package us.hyalen.hcode.server.core.user.v1;

import lombok.Getter;
import lombok.Setter;
import us.hyalen.hcode.client.core.user.v1.UserResource;
import us.hyalen.hcode.server.core.Domain;
import us.hyalen.hcode.client.core.role.v1.RoleResource;
import us.hyalen.hcode.server.core.role.v1.Role;
import us.hyalen.hcode.server.model.RoleModel;
import us.hyalen.hcode.server.model.UserModel;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
public class User extends Domain {
    @Setter
    private static UserDao userDao;
    private static UserMapper mapper = UserMapper.INSTANCE;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;

    private User() {}

    public static boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    public static boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    public static Optional<User> findById(Long id) {
        return userDao.findByUserId(id);
    }

    public static Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public static Optional<User> findByUsernameOrEmail(String username, String email) {
        return userDao.findByUsernameOrEmail(username, email);
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
            return mapper.mapResourceToDomain(resource);
            // mapper.mapResourceToDomain(resource, user);
            // return this;
        }

        public Builder withUserModel(UserModel model) {
            if (model == null)
                return null;

            return mapper.toDomainBuilder(model);
            // mapper.mapModelToDomain(model, user);
            // return this;
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

        public Builder withUsername(String username) {
            user.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

//        public Builder withRoles(Set<RoleModel> models) {
//            if (models != null && models.size() > 0) {
//                user.roles = new HashSet<>();
//
//                models.forEach (model -> {
//                    user.roles.add(mapper.toRole(model));
//                });
//            }
//
//            return this;
//        }

        public Builder withRoles(Set<Role> roles) {
            user.roles = roles;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
