package us.hyalen.hcode.core;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import us.hyalen.hcode.Application;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("test")
public abstract class TestH2 extends OverlayH2 {
    @Override
    public String inputSqlFilename() {
        return null;
    }

    @Override
    public String cleanupSqlFilename() {
        return null;
    }
}
