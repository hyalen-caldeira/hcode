package us.hyalen.hcode.core.user.v1;

import lombok.Setter;
import us.hyalen.hcode.core.Domain;

public class User extends Domain {
    @Setter
    private static UserDao userDao;
}
