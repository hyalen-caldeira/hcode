package us.hyalen.hcode.server.core.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.hcode.server.core.NotFoundException;
import us.hyalen.hcode.server.core.TestH2;
import us.hyalen.hcode.server.core.user.v1.User;
import us.hyalen.hcode.server.core.user.v1.UserMapper;
import us.hyalen.hcode.client.core.user.v1.UserResource;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

// TODO, Constraint Violation Exception
// TODO, include roles
// TODO, test collection
@ActiveProfiles("test")
public class UserTestH2 extends TestH2 {
    private final Long EXIST_USER_ID = 1L;
    private final Long NON_EXIST_USER_ID = 999L;

    private final String EXIST_FIRST_NAME = "Hyalen";
    private final String EXIST_LAST_NAME = "Moreira";
    private final String EXIST_EMAIL = "hyalen@gmail.com";
    private final String EXIST_USERNAME = "hmoreira";

    private final String FIRST_NAME_CREATED = "Igor";
    private final String LAST_NAME_CREATED = "Caldeira";
    private final String EMAIL_CREATED = "igor@hotmail.com";
    private final String USERNAME_CREATED = "icaldeira";
    private final String PASSWORD_CREATED = "123456";

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

    // TODO, fetching all users
    public void supportsFetchingAllUsers() {

    }

    @Test
    public void when_AValidUserIdIsGiven_then_AValidUSerIsReturned() {
        User user = User.findById(EXIST_USER_ID).orElseThrow(NotFoundException::new);

        assertThat(user.getFirstName(), is(equalTo(EXIST_FIRST_NAME)));
        assertThat(user.getLastName(), is(equalTo(EXIST_LAST_NAME)));
        assertThat(user.getEmail(), is(equalTo(EXIST_EMAIL)));
    }

    @Test
    public void when_AValidUsernameIsGiven_then_AValidUSerIsReturned() {
        User user = User.findByUsername(EXIST_USERNAME).orElseThrow(NotFoundException::new);

        assertThat(user.getFirstName(), is(equalTo(EXIST_FIRST_NAME)));
        assertThat(user.getLastName(), is(equalTo(EXIST_LAST_NAME)));
        assertThat(user.getEmail(), is(equalTo(EXIST_EMAIL)));
    }

    @Test (expected = NotFoundException.class)
    public void when_AnNonExistUsernameIsGiven_then_NotFoundExceptionIsThrown() {
        User.findById(NON_EXIST_USER_ID).orElseThrow(NotFoundException::new);
    }

    @Test
    public void when_AnInvalidUserIdIsGiven_then_NothingIsReturned() {
        Optional<User> user = User.findById(NON_EXIST_USER_ID);

        assertThat(user.isPresent(), is(false));
    }

    @Test (expected = NotFoundException.class)
    public void when_AInvalidUserIdIsGiven_then_NotFoundExceptionIsThrown() {
        User.findById(NON_EXIST_USER_ID).orElseThrow(NotFoundException::new);
    }

    @Test
    public void supportsCreatingANewUser() {
        // GIVEN a valid UserResource
        UserResource resource = getValidUserResource();

        // WHEN a domain create method is called
        User user = new User.Builder().withUserResource(resource).build();
        user = user.create();

        // THEN, User has a correct data and userId generated;
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getFirstName(), is(equalTo(FIRST_NAME_CREATED)));
        assertThat(user.getLastName(), is(equalTo(LAST_NAME_CREATED)));
        assertThat(user.getEmail(), is(equalTo(EMAIL_CREATED)));
        assertThat(user.getUsername(), is(equalTo(USERNAME_CREATED)));
        assertThat(user.getPassword(), is(equalTo(PASSWORD_CREATED)));
    }

    @Test
    public void supportsUpdatingAnUser() {
        // GIVEN a valid userId
        long userId = EXIST_USER_ID;

        // WHEN update for a valid userId is made
        updateUser(userId);

        // THEN, resource is updated as expected
        User savedUser = User.findById(userId).orElseThrow(NotFoundException::new);
        UserResource resource = UserMapper.INSTANCE.mapDomainToResource(savedUser);

        assertThat(resource.firstName, is(equalTo(FIRST_NAME_UPDATED)));
        assertThat(resource.lastName, is(equalTo(LAST_NAME_UPDATED)));
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

        assertThat(userDeleted.isPresent(), is(false));
    }

    @Test
    public void when_AValidEmailIsGiven_then_ExistsByEmailReturnsTrue() {
        assertTrue(User.existsByEmail(EXIST_EMAIL));
    }

    @Test
    public void when_AValidUsernameIsGiven_then_ExistsByUsernameReturnsTrue() {
        assertTrue(User.existsByUsername(EXIST_USERNAME));
    }

    private UserResource getValidUserResource() {
        UserResource resource = new UserResource();

        resource.firstName = FIRST_NAME_CREATED;
        resource.lastName = LAST_NAME_CREATED;
        resource.email = EMAIL_CREATED;
        resource.username = USERNAME_CREATED;
        resource.password = PASSWORD_CREATED;

        return resource;
    }

    private void updateUser(Long id) {
        User user = User.findById(id).orElseThrow(NotFoundException::new);

        user = new User.Builder(user).withFirstName(FIRST_NAME_UPDATED).withLastName(LAST_NAME_UPDATED).build();

        user.update();
    }
}
