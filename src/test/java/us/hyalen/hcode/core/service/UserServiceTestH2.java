package us.hyalen.hcode.core.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.hcode.core.NotFoundException;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.User;

import static org.junit.Assert.assertNull;

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
        return "sql/user/v1/user_h2.sql";
    }

    @Override
    public String cleanupSqlFilename() {
        return "sql/user/v1/user_cleanup_h2.sql";
    }

    @Test
    public void testing() {
        User user = User.findById(1L).orElseThrow(NotFoundException::new);
        assertNull(null);
    }
}
