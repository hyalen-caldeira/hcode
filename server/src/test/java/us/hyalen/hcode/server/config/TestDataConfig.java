package us.hyalen.hcode.server.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import java.util.Properties;

// TODO, Get parameters from property file
@EnableTransactionManagement
@Configuration
public class TestDataConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Bean
    @Profile("test")
    public Properties hcodeHibernateProperties() {
        log.info("--------->>> TestDataConfig, SETTING HIBERNATE PROPERTIES");
        Properties properties = new Properties();

        properties.put("hibernate.jdbc.fetch_size", 500);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.connection.url", "jdbc:h2:mem:hcodedb");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.validationQuery", "SELECT 1 + 1");
        properties.put("hibernate.hbm2ddl.auto", "create");

        return properties;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    @Profile("test")
    public DataSource hcodeDataSource() {
        log.info("--------->>> TestDataConfig, SETTING DATA SOURCE");
        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setValidationQuery("SELECT 1 + 1");
        dataSource.setUrl("jdbc:h2:mem:hcodedb;init=runscript from 'classpath:sql/schema/hcodedb_schema.sql';mode=MySql;db_close_on_exit=false");
        dataSource.setMaxActive(2);

        return dataSource;
    }

    @Bean
    @Profile("test")
    @Primary
    public LocalSessionFactoryBean hcodeSessionFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        log.info("--------->>> TestDataConfig, SETTING SESSION FACTORY");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("us.hyalen.hcode.server.model");

        return localSessionFactoryBean;
    }

    @Bean
    @Profile({"test"})
    public HibernateTransactionManager hcodeTransactionManager(@Qualifier("hcodeSessionFactory") SessionFactory sessionFactory) {
        log.info("--------->>> DataConfig, SETTING TRANSACTION MANAGER");
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        log.info("--------->>> TestDataConfig, SETTING ENTITY MANAGER FACTORY");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("us.hyalen.hcode.server.model");
        em.setPersistenceUnitName("HCODEDBTEST");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);

        return em;
    }
}
