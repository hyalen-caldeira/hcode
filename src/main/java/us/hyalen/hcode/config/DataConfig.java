package us.hyalen.hcode.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import us.hyalen.hcode.interceptor.EventLogInterceptor;

import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class DataConfig {
    @Autowired
    private Environment environment;

    @Bean
    @Profile("principal")
    public Properties hcodeHibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.jdbc.fetch_size", 500);

        // Allow System properties to overwrite this, so that in IDE we can set it to show SQL
        if (System.getProperty("hibernate.show_sql") != null) {
            properties.put("hibernate.format_sql", true);
            properties.put("hibernate.show_sql", true);
        } else {
            properties.put("hibernate.format_sql", true);
            properties.put("hibernate.show_sql", true);
        }

        properties.put("testWhileIdle", true);
        properties.put("validationQuery", "SELECT 1 + 1");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        // Without below property, ocdbSessionFactory.getCurrentSession() will raise "No CurrentSessionContext configured" exception
        properties.put("hibernate.current_session_context_class","org.springframework.orm.hibernate5.SpringSessionContext");

        return properties;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    @Profile("principal")
    public DataSource hcodeDataSource(@Value("${locale-alias.hcode}") String alias) {
        String server = environment.getProperty("db_ip." + alias);
        String dbName = environment.getProperty("db_name." + alias);
        Integer port = Integer.parseInt(environment.getProperty("db_port." + alias));

        DataSource dataSource = new DataSource();

        dataSource.setUrl("jdbc:mysql://" + server + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("root");
        dataSource.setPassword("Nicol#3113");

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager hcodeTransactionManager(@Qualifier("hcodeSessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    @Profile("principal")
    @Primary
    public LocalSessionFactoryBean hcodeSessionFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            EventLogInterceptor eventLogInterceptor,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("us.hyalen.hcode.model");
        localSessionFactoryBean.setEntityInterceptor(eventLogInterceptor);

        return localSessionFactoryBean;
    }

    @Bean
    @Profile("principal")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("us.hyalen.hcode.model");
        em.setPersistenceUnitName("HCODE");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);

        return em;
    }
}
