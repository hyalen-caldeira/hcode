package us.hyalen.hcode.core;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import us.hyalen.hcode.Application;
import us.hyalen.hcode.core.user.v1.UserDao;

import java.net.URL;

@ContextConfiguration(classes = Application.class)
@WebAppConfiguration

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = Application.class)
//@DataJpaTest
//@SpringBootTest
public abstract class TestH2 { // extends OverlayH2 {
//    @Override
    public String inputSqlFilename() {
        return null;
    }

//    @Override
    public String cleanupSqlFilename() {
        return null;
    }

//    @Autowired
//    @Qualifier("hcodeSessionFactory")
//    private LocalSessionFactoryBean hcodeSessionFactory;

//    @Autowired
//    @Qualifier("hcodeTransactionManager")
//    private PlatformTransactionManager hcodeTransactionManager;

    @Autowired
    @Qualifier("userDao_v1")
    private UserDao userDao_v1;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Before
//    public void setupOverlay() {
//        if(inputSqlFilename() != null) {
//            overlaySql();
//            log.info("Test H2 --->>> SQL H2 OVERLAY APPLIED");
//        }
//    }
//
//    @After
//    public void cleanupOverlay() {
//        if(cleanupSqlFilename() != null) {
//            cleanupSql();
//            log.info("Test H2 --->>> SQL H2 CLEANUP APPLIED");
//        }
//    }
//
//    private void overlaySql() {
//        TransactionStatus transaction = hcodeTransactionManager.getTransaction(null);
//
//        hcodeSessionFactory.getCurrentSession()
//                .createQuery(readFromUrl(this.getClass().getClassLoader().getResource(inputSqlFilename())))
//                .executeUpdate();
//
//        hcodeTransactionManager.commit(transaction);
//    }
//
//    private void cleanupSql() {
//        TransactionStatus transaction = hcodeTransactionManager.getTransaction(null);
//
//        hcodeSessionFactory.getCurrentSession()
//                .createQuery(readFromUrl(this.getClass().getClassLoader().getResource(cleanupSqlFilename())))
//                .executeUpdate();
//
//        hcodeTransactionManager.commit(transaction);
//    }

    private String readFromUrl(URL url) {
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
