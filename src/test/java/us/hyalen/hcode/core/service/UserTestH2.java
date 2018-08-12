package us.hyalen.hcode.core.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.User;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
public class UserTestH2 extends TestH2 {
    private static final Long EXIST_USER_ID = 1L;
    private static final Long NON_EXIST_USER_ID = 999L;

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
    public void when_AValidUserIdIsGiven_then_OptionalTrueIsReturned() {
        Optional<User> user = User.findById(EXIST_USER_ID);
        assertTrue(user.isPresent());
    }

    @Test
    public void when_AInvalidUserIdIsGiven_then_OptionalFalseIsReturned() {
        Optional<User> user = User.findById(NON_EXIST_USER_ID);
        assertFalse(user.isPresent());
    }
}
