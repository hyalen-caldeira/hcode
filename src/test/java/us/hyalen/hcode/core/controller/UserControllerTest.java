package us.hyalen.hcode.core.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import us.hyalen.hcode.core.TestH2;
import us.hyalen.hcode.core.user.v1.UserResource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
public class UserControllerTest extends TestH2 {
    private static final String EXIST_USER_ID = "/1";
    private static final String NON_EXIST_USER_ID = "/999";

    private MockMvc mockMvc;

    @Override
    public String inputSqlFilename() {
        return "sql/user/v1/user_h2.sql";
    }

    @Override
    public String cleanupSqlFilename() {
        return "sql/user/v1/user_cleanup_h2.sql";
    }

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void when_ANonExistUserIdIsGiven_then_ANotFoundStatusIsReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users" + NON_EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_AValidUserIdIsGiven_then_AnOkStatusIsReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users" + EXIST_USER_ID).accept(UserResource.MEDIA_TYPE))
                .andExpect(status().isOk());
    }
}
