package ta.market.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean sessionFactory() {
        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
        sessionFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        sessionFactory.setJpaDialect(new HibernateJpaDialect());
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"ta.market"});
        sessionFactory.setJpaProperties(hibernateProperties());
        return sessionFactory;
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:data/task");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    protected Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.hbm2ddl.import_files", "populate.sql");
        return properties;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
