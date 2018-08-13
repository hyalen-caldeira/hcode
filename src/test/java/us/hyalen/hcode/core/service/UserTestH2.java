package us.hyalen.hcode.core.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.hcode.core.NotFoundException;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.User;
import us.hyalen.hcode.core.user.v1.UserMapper;
import us.hyalen.hcode.core.user.v1.UserResource;

import java.util.Optional;

import static org.junit.Assert.*;

// TODO, Constraint Violation Exception
@ActiveProfiles("test")
public class UserTestH2 extends TestH2 {
    private final Long EXIST_USER_ID = 1L;
    private final Long NON_EXIST_USER_ID = 999L;
    private final String FIRST_NAME = "Hyalen";
    private final String LAST_NAME = "Moreira";
    private final String EMAIL = "hyalen@hotmail.com";
    private final String LOGIN = "hyalen@hotmail.com";
    private final String PASSWORD = "123456";
    private final String FIRST_NAME_UPDATED = "Gabriela";
    private final String LAST_NAME_UPDATED = "Gontijo";

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
    public void when_AValidUserIdIsGiven_then_AValidUSerIsReturned() {
        Optional<User> user = User.findById(EXIST_USER_ID);
        assertTrue(user.isPresent());
    }

    @Test
    public void when_AInvalidUserIdIsGiven_then_NothingIsReturned() {
        Optional<User> user = User.findById(NON_EXIST_USER_ID);
        assertFalse(user.isPresent());
    }

    @Test (expected = NotFoundException.class)
    public void when_AInvalidUserIdIsGIVEN_then_NotFoundExceptionIsThrown() {
        User user = User.findById(NON_EXIST_USER_ID).orElseThrow(NotFoundException::new);
    }

    @Test
    public void supportsCreatingANewUser() {
        // GIVEN a valid UserResource
        UserResource resource = getValidUserResource();

        // WHEN a domain create method is called
        User user = new User.Builder().withUserResource(resource).build();
        user = user.create();

        // THEN, User has a correct data and userId generated;
        assertNotNull(user.getId());
        assertEquals(user.getFirstName(), FIRST_NAME);
        assertEquals(user.getLastName(), LAST_NAME);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getLogin(), LOGIN);
        assertEquals(user.getPassword(), PASSWORD);
    }

    @Test
    public void supportsUpdatingAnUser() {
        // GIVEN a valid userId
        long userId = EXIST_USER_ID;

        // WHEN update for a valid userId is made
        updateUser(userId);

        // THEN, resource update as expected
        User savedUser = User.findById(userId).orElseThrow(NotFoundException::new);
        UserResource resource = UserMapper.INSTANCE.mapDomainToResource(savedUser);

        assertEquals(FIRST_NAME_UPDATED, resource.firstName);
        assertEquals(LAST_NAME_UPDATED, resource.lastName);
    }

    @Test
    public void supportsDeletingAnUser() {
        // GIVEN a valid userId
        long userId = EXIST_USER_ID;

        // WHEN delete for a valid userId is made
        User user = User.findById(userId).orElseThrow(NotFoundException::new);
        user.delete();

        // THEN, user is deleted as expected
        Optional<User> userDeleted = User.findById(userId);
        assertFalse(userDeleted.isPresent());
    }


    private UserResource getValidUserResource() {
        UserResource resource = new UserResource();

        resource.setFirstName(FIRST_NAME);
        resource.setLastName(LAST_NAME);
        resource.setEmail(EMAIL);
        resource.setLogin(LOGIN);
        resource.setPassword(PASSWORD);

        return resource;
    }

    private void updateUser(Long id) {
        User user = User.findById(id).orElseThrow(NotFoundException::new);

        user.setFirstName(FIRST_NAME_UPDATED);
        user.setLastName(LAST_NAME_UPDATED);

        user.update();
    }
}
