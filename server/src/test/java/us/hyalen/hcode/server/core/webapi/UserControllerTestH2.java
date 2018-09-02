package us.hyalen.hcode.server.core.webapi;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import us.hyalen.hcode.server.core.ApiResponse;
import us.hyalen.hcode.server.core.TestH2;
import us.hyalen.hcode.client.core.user.v1.UserResource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


// TODO, include test for a set of roles
// TODO, test collection
@ActiveProfiles("test")
public class UserControllerTestH2 extends TestH2 {
    private final String BASE_URI = "/api/users/";

    private final String EXIST_USERNAME = "hmoreira";
    private final String NON_EXIST_USERNAME = "hfulano";

    private final String EXIST_FIRST_NAME = "Hyalen";
    private final String EXIST_LAST_NAME = "Moreira";
    private final String EXIST_EMAIL = "hyalen@gmail.com";

    private final String FIRST_NAME_CREATED = "Igor";
    private final String LAST_NAME_CREATED = "Caldeira";
    private final String EMAIL_CREATED = "igor@hotmail.com";
    private final String USERNAME_CREATED = "igor@hotmail.com";
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

    // TODO, fetching all users
    public void supportsFetchingAllUsers() {

    }

    @Test
    public void when_ANonExistUsernameIsGiven_then_ANotFoundStatusIsReturned() throws Exception {
        getRequest(NON_EXIST_USERNAME).andExpect(status().isNotFound());
    }

    @Test
    public void when_AGetWithCorrectUsernameIsPerformed_then_AValidResourceIsReturned() throws Exception {
        // GIVEN a valid username
        String username = EXIST_USERNAME;

        // WHEN a read request is made specifying a valid id parameter
        ResultActions result = getRequest(username);

        // THEN, success response is returned
        result.andExpect(status().isOk());

        // AND the User that we get back is as expected
        String json = result.andReturn().getResponse().getContentAsString();

        UserResource resource = objectMapper.readValue(json, UserResource.class);

        assertThat(resource.firstName, is(equalTo(EXIST_FIRST_NAME)));
        assertThat(resource.lastName, is(equalTo(EXIST_LAST_NAME)));
        assertThat(resource.email, is(equalTo(EXIST_EMAIL)));
    }

    @Test
    public void when_AGetWithANonExistUsernameIsPerformed_then_NotFoundIsReturned() throws Exception {
        // GIVEN a non exist username
        String username = NON_EXIST_USERNAME;

        // WHEN a read request is made specifying a non exist id parameter
        ResultActions result = getRequest(username);

        // THEN, not found response is returned
        result.andExpect(status().isNotFound());
    }

    @Test
    public void supportsCreatingANewUser() throws Exception {
        // GIVEN a valid user resource
        UserResource resource = getValidUserResource();

        // WHEN a create request is made
        ResultActions postResult = mockMvc.perform(post(BASE_URI)
                .content(objectMapper.writeValueAsString(resource))
                .contentType(UserResource.MEDIA_TYPE));

        // THEN, success response is returned
        postResult.andExpect(status().isCreated());

        // AND Location header has new resource URL
        String location = postResult.andReturn().getResponse().getHeader("Location");
        assertThat(location, notNullValue());

        // AND the response that we get back is as expected
        String json = postResult.andReturn().getResponse().getContentAsString();
        ApiResponse response = objectMapper.readValue(json, ApiResponse.class);
        assertThat(response.getSuccess(), is(true));
        assertThat(response.getMessage(), is(equalTo(ApiResponse.CREATED)));

        // AND new resource can be read using Location header
        ResultActions getResults = mockMvc.perform(get(location).accept(UserResource.MEDIA_TYPE));
        getResults.andExpect(status().isOk());
    }

    @Test
    public void supportsUpdatingAnUser() throws Exception {
        // GIVEN a valid username
        String username = EXIST_USERNAME;

        // WHEN update for username is made
        ResultActions result = updateUser(username);

        // THEN, success response is returned
        result.andExpect(status().isOk());

        // AND user is updated as expected
        UserResource updatedResource =
            objectMapper.readValue(getRequest(username).andReturn().getResponse().getContentAsString(), UserResource.class);

        assertThat(updatedResource.firstName, is(equalTo(FIRST_NAME_UPDATED)));
        assertThat(updatedResource.lastName, is(equalTo(LAST_NAME_UPDATED)));
    }

    @Test
    public void supportsDeletingAnUser() throws Exception {
        // GIVEN a valid username
        String username = EXIST_USERNAME;

        // WHEN a delete request is made for that id
        mockMvc.perform(delete(BASE_URI + username)).andExpect(status().isOk());
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

    private ResultActions getRequest(String username) throws Exception {
        return mockMvc.perform(get(BASE_URI + username).accept(UserResource.MEDIA_TYPE));
    }

    private ResultActions updateUser(String username) throws Exception {
        UserResource resource =
            objectMapper.readValue(getRequest(username).andReturn().getResponse().getContentAsString(), UserResource.class);

        resource.firstName = FIRST_NAME_UPDATED;
        resource.lastName = LAST_NAME_UPDATED;

        return mockMvc.perform(put(BASE_URI + username)
            .content(objectMapper.writeValueAsString(resource))
            .contentType(UserResource.MEDIA_TYPE));
    }
}
