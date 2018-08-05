package us.hyalen.hcode.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class DataConfig {
    @Bean
    @Primary
    public HibernateTransactionManager transactionManager(@Qualifier("mainSessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    /*
    @ConfigurationProperties(prefix = "datasource")
    @Bean
    @Primary
    @Profile({"principal", "IntegrationTest"})
    public DataSource ocdbDataSource(@Value("${locale-alias.ocdb}") String alias) {
        // emergency brakes
        if(environment.getProperty("db_ip." + alias).matches(".*sj2.*")) {
            for (String profileName : environment.getActiveProfiles()) {
                if(profileName.matches(".*Test.*")) {
                    throw new RuntimeException("\n\n\n> > > Attempt to run tests with production database detected, execution halted!!! < < <\n\n");
                }
            }
        }
        return createDataSource(alias);
    }

    @ConfigurationProperties(prefix = "datasource")
    @Bean
    @Profile({"principal", "IntegrationTest"})
    public DataSource metaDataSource(@Value("${locale-alias.meta}") String alias) {
            return createDataSource(alias);
    }

    private DataSource createDataSource(@Value("${meta.locale-alias}") String alias) {
        String server = environment.getProperty("db_ip." + alias);
        String dbName = environment.getProperty("db_name." + alias);
        Integer port = Integer.parseInt(environment.getProperty("db_port." + alias));
        String connectDetails = environment.getProperty("db_type." + alias);
        DBAuthInfo authInfo = new DBAuthInfo(connectDetails);
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:oracle:thin:@" + server + ":" + port + ":" + dbName);
        dataSource.setUsername(authInfo.getLoginName());
        dataSource.setPassword(authInfo.getPassword());

        return dataSource;
    }

    @Bean
    @Profile({"principal", "IntegrationTest"})
    public Properties ocdbHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.jdbc.fetch_size", 500);
        // Allow System properties to overwrite this, so that in IDE we can set it to show SQL
        if (System.getProperty("hibernate.show_sql") != null) {
            properties.put("hibernate.format_sql", true);
            properties.put("hibernate.show_sql", true);
        } else {
            properties.put("hibernate.format_sql", false);
            properties.put("hibernate.show_sql", false);
        }
        properties.put("testWhileIdle", true);
        properties.put("validationQuery", "SELECT 1 FROM DUAL");

        properties.put("hibernate.dialect", "com.conversantmedia.core.OracleDialect");
        // Without below property, ocdbSessionFactory.getCurrentSession() will raise "No CurrentSessionContext configured" exception
        properties.put("hibernate.current_session_context_class","org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

    @Bean
    @Profile({"principal", "IntegrationTest"})
    @Primary
    public LocalSessionFactoryBean ocdbSessionFactory(@Qualifier("ocdbHibernateProperties") Properties properties,
                                                      EventLogInterceptor eventLogInterceptor,
                                                      @Qualifier("ocdbDataSource") DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("com.conversantmedia.pubby.entity", "com.conversantmedia.core.model.ocdb");
        localSessionFactoryBean.setEntityInterceptor(eventLogInterceptor);
        return localSessionFactoryBean;
    }

    @Bean
    @Primary
    public HibernateTransactionManager transactionManager(@Qualifier("ocdbSessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    @Profile({"principal", "IntegrationTest"})
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("ocdbHibernateProperties") Properties properties,
                                                                           @Qualifier("ocdbDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.conversantmedia.core.model.ocdb");
        em.setPersistenceUnitName("OCDB");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);

        return em;
    }
     */
}
