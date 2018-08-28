package us.hyalen.hcode.server.core.role.v1;

import lombok.Setter;
import us.hyalen.hcode.server.core.Domain;

public class Role extends Domain {
    @Setter
    private static RoleDao roleDao;
}
