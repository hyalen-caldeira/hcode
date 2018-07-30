package us.hyalen.hcode.core.session.v1;

import lombok.Setter;
import us.hyalen.hcode.core.Domain;

public class Session extends Domain {
    @Setter
    private static SessionDao sessionDao;
}
