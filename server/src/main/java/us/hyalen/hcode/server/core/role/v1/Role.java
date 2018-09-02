package us.hyalen.hcode.server.core.role.v1;

import lombok.Getter;
import lombok.Setter;
import us.hyalen.hcode.client.core.role.v1.RoleResource;
import us.hyalen.hcode.server.core.Domain;
import us.hyalen.hcode.server.model.RoleModel;
import us.hyalen.hcode.server.model.RoleName;

import java.util.Optional;

@Getter
public class Role extends Domain {
    @Setter
    private static RoleDao roleDao;

    private Long id;
    private String name;

    // private Role() {}

    public static Optional<Role> findByName(RoleName roleName) {
        return roleDao.findByName(roleName);
    }

    public static class Builder {
        private Role role;

        public Builder() {
            this.role = new Role();
        }

        public Builder(Role role) {
            this.role = role;
        }

        public Builder withRoleResource(RoleResource resource) {
            return this;
        }

        public Builder withRoleModel(RoleModel model) {
            return this;
        }

        public Role build() {
            return role;
        }
    }
}
