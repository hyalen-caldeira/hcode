package us.hyalen.hcode.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TestDataConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Bean
    @Profile("test")
    public Properties hcodeHibernateProperties() {
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
        log.info("--------->>> INIT H2 HCODEDB, DATA SOURCE <<<---------");
        DataSource dataSource = new DataSource();

        // dataSource.setUrl("jdbc:h2:mem:hcodedb;init=runscript from 'classpath:schema-hcodedb.sql';db_close_on_exit=false");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setValidationQuery("SELECT 1 + 1");
        dataSource.setUrl("jdbc:h2:mem:hcodedb;init=runscript from 'classpath:sql/schema/hcodedb_schema.sql';mode=MySql;db_close_on_exit=false");
        // dataSource.setUrl("jdbc:h2:mem:hcodedb;mode=MySql;db_close_on_exit=false");
        dataSource.setMaxActive(2);

        return dataSource;
    }

    @Bean
    @Profile("test")
    @Primary
    public LocalSessionFactoryBean hcodeSessionFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        log.info("--------->>> INIT H2 HCODEDB, SESSION FACTORY <<<---------");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("us.hyalen.hcode.model");

        return localSessionFactoryBean;
    }

    @Bean
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("hcodeHibernateProperties") Properties properties,
            @Qualifier("hcodeDataSource") DataSource dataSource) {
        log.info("--------->>> INIT H2 HCODEDB, ENTITY MANAGER <<<---------");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("us.hyalen.hcode.model");
        em.setPersistenceUnitName("HCODEDBTEST");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);

        return em;
    }
}
