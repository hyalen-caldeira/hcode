package us.hyalen.hcode.server.core.session.v1;

import lombok.Setter;
import us.hyalen.hcode.server.core.Domain;

public class Session extends Domain {
    @Setter
    private static SessionDao sessionDao;
}
