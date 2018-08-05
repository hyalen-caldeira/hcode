package us.hyalen.hcode.core;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.net.URL;

public abstract class OverlayH2 {
    abstract String inputSqlFilename();
    abstract String cleanupSqlFilename();

    @Autowired
    @Qualifier("mainSessionFactory")
    private SessionFactory mainSessionFactory;

    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager transactionManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setupOverlay() {
        if(inputSqlFilename() != null) {
            overlaySql();
            log.info("Test H2 --->>> SQL H2 OVERLAY APPLIED");
        }
    }

    @After
    public void cleanupOverlay() {
        if(cleanupSqlFilename() != null) {
            cleanupSql();
            log.info("Test H2 --->>> SQL H2 CLEANUP APPLIED");
        }
    }

    private void overlaySql() {
        TransactionStatus transaction = transactionManager.getTransaction(null);

        mainSessionFactory.getCurrentSession()
                .createQuery(readFromUrl(this.getClass().getClassLoader().getResource(inputSqlFilename())))
                .executeUpdate();

        transactionManager.commit(transaction);
    }

    private void cleanupSql() {
        TransactionStatus transaction = transactionManager.getTransaction(null);

        mainSessionFactory.getCurrentSession()
                .createQuery(readFromUrl(this.getClass().getClassLoader().getResource(cleanupSqlFilename())))
                .executeUpdate();

        transactionManager.commit(transaction);
    }

    private String readFromUrl(URL url) {
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
