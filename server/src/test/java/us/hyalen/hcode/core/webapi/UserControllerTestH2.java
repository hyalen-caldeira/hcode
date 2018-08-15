package us.hyalen.hcode.core.webapi;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.UserResource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

// TODO, include roles
@ActiveProfiles("test")
public class UserControllerTestH2 extends TestH2 {
    private final long EXIST_USER_ID = 1L;
    private final long NON_EXIST_USER_ID = 999L;

    private final String EXIST_FIRST_NAME = "Hyalen";
    private final String EXIST_LAST_NAME = "Moreira";
    private final String EXIST_EMAIL = "hyalen@gmail.com";

    private final String FIRST_NAME_CREATED = "Igor";
    private final String LAST_NAME_CREATED = "Caldeira";
    private final String EMAIL_CREATED = "igor@hotmail.com";
    private final String LOGIN_CREATED = "igor@hotmail.com";
    private final String PASSWORD_CREATED = "123456";

    private final String FIRST_NAME_UPDATED = "Gabriela";
    private final String LAST_NAME_UPDATED = "Gontijo";

    @Override
    public String inputSqlFilename() {
        return "sql/user/v1/user_h2.sql";
    }

    @Override
    public String cleanupSqlFilename() {
        return "sql/user/v1/user_cleanup_h2.sql";
    }

    @Test
    public void when_ANonExistUserIdIsGiven_then_ANotFoundStatusIsReturned() throws Exception {
        mockMvc.perform(get("/users/" + NON_EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_AValidUserIdIsGiven_then_AnOkStatusIsReturned() throws Exception {
        mockMvc.perform(get("/users/" + EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void when_AGetWithCorrectUserIdIsPerformed_then_AValidResourceIsReturned() throws Exception {
        // GIVEN a valid userId
        long userId = EXIST_USER_ID;

        // WHEN a read request is made specifying a valid id parameter
        ResultActions result = mockMvc.perform(get("/users/" + EXIST_USER_ID).accept(UserResource.MEDIA_TYPE));

        // THEN, success response is returned
        result.andExpect(status().isOk());

        // AND the User that we get back is as expected
        String json = result.andReturn().getResponse().getContentAsString();

        UserResource resource = objectMapper.readValue(json, UserResource.class);

        assertEquals(resource.getFirstName(), EXIST_FIRST_NAME);
        assertEquals(resource.getLastName(), EXIST_LAST_NAME);
        assertEquals(resource.getEmail(), EXIST_EMAIL);
    }

    @Test
    public void when_AGetWithANonExistUserIdIsPerformed_then_ANotFoundIsReturned() throws Exception {
        // GIVEN a non exist userId
        long userId = NON_EXIST_USER_ID;

        // WHEN a read request is made specifying a non exist id parameter
        ResultActions result = mockMvc.perform(get("/users/" + NON_EXIST_USER_ID).accept(UserResource.MEDIA_TYPE));

        // THEN, not found response is returned
        result.andExpect(status().isNotFound());
    }

    @Test
    public void supportsCreatingANewUser() throws Exception {
        // GIVEN a valid user resource
        UserResource resource = getValidUserResource();

        // WHEN a create request is made
        ResultActions postResult = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(resource))
                .contentType(UserResource.MEDIA_TYPE));

        // THEN, success response is returned
        postResult.andExpect(status().isOk());

        // AND Location header has new resource URL
        String location = postResult.andReturn().getResponse().getHeader("Location");
        assertNotNull(location);

        // AND new resource can be read using Location header
        // result = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + NON_EXIST_USER_ID).accept(UserResource.MEDIA_TYPE));
        ResultActions getResults = mockMvc.perform(get(location).accept(UserResource.MEDIA_TYPE));
        getResults.andExpect(status().isOk());

    }

    private UserResource getValidUserResource() {
        UserResource resource = new UserResource();

        resource.setFirstName(FIRST_NAME_CREATED);
        resource.setLastName(LAST_NAME_CREATED);
        resource.setEmail(EMAIL_CREATED);
        resource.setLogin(LOGIN_CREATED);
        resource.setPassword(PASSWORD_CREATED);

        return resource;
    }
}
