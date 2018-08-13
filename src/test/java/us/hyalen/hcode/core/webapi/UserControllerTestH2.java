package us.hyalen.hcode.core.webapi;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.UserResource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@ActiveProfiles("test")
public class UserControllerTestH2 extends TestH2 {
    private final long EXIST_USER_ID = 1L;
    private final long NON_EXIST_USER_ID = 999L;
    private final String FIRST_NAME = "Hyalen";
    private final String LAST_NAME = "Moreira";
    private final String EMAIL = "hyalen@gmail.com";

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
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + NON_EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_AValidUserIdIsGiven_then_AnOkStatusIsReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void when_AGetWithCorrectUserIdIsPerformed_then_AValidResourceIsReturned() throws Exception {
        // GIVEN a valid userId
        long userId = EXIST_USER_ID;

        // WHEN a read request is made specifying a valid id parameter
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + EXIST_USER_ID).accept(UserResource.MEDIA_TYPE));

        // THEN, success response is returned
        result.andExpect(status().isOk());

        // AND the User that we get back is as expected
        String json = result.andReturn().getResponse().getContentAsString();

        UserResource resource = objectMapper.readValue(json, UserResource.class);

        assertEquals(resource.getFirstName(), FIRST_NAME);
        assertEquals(resource.getLastName(), LAST_NAME);
        assertEquals(resource.getEmail(), EMAIL);
    }
}
