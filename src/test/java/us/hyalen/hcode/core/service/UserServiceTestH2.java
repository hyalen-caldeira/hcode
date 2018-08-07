package us.hyalen.hcode.core.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import us.hyalen.hcode.Application;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.User;

import static org.junit.Assert.*;

@ActiveProfiles("test")
public class UserServiceTestH2 extends TestH2 {
//    @TestConfiguration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public User.Builder user() {
//            return new User.Builder();
//        }
//    }
//
//    @Autowired
//    private User.Builder user;

    @Override
    public String inputSqlFilename() {
        return "sql/user/v1/UserTestH2.sql";
    }

    @Override
    public String cleanupSqlFilename() {
        return "sql/user/v1/UserTestH2Cleanup.sql";
    }

    @Test
    public void testing() {
        assertNull(null);
    }
}
