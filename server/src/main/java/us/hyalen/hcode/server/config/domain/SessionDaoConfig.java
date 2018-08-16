package us.hyalen.hcode.server.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.server.core.session.v1.Session;
import us.hyalen.hcode.server.core.session.v1.SessionDao;

import javax.annotation.PostConstruct;

@Component
public class SessionDaoConfig {
    @Autowired
    @Qualifier("sessionDao_v1")
    private SessionDao sessionDao_v1;

    @PostConstruct
    void injectDependencies() {
        Session.setSessionDao(sessionDao_v1);
    }
}
