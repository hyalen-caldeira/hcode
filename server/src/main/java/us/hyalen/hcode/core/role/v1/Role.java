package us.hyalen.hcode.core.role.v1;

import lombok.Setter;
import us.hyalen.hcode.core.Domain;

public class Role extends Domain {
    @Setter
    private static RoleDao roleDao;
}
