package us.hyalen.hcode.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import us.hyalen.hcode.Application;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("test")
public abstract class TestH2 extends OverlayH2 {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public String inputSqlFilename() {
        return null;
    }

    @Override
    public String cleanupSqlFilename() {
        return null;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
