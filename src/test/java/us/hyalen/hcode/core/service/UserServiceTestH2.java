package us.hyalen.hcode.core.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.hcode.core.TestH2;

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
