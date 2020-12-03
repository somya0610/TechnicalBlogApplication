package technicalblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    /*The class ‘LocalContainerEntityManagerFactoryBean’ provided by spring will generate the
     EntityManagerFactory object instance by taking the input information of the persistence.xml file.*/
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        factoryBean.setDataSource(dataSource());
        factoryBean.afterPropertiesSet(); //allows to validate the overall configuration
                                // and initialize the instance of EntityManagerFactory with all the properties set.
        return factoryBean.getObject();
    }

    /*The DriverManagerDatasource is a class provided by spring to make a connection with the database
     based on the information provided. DriverManagerDataSource class acts as the primary mediator
      between the application and the drivers of the application you want to connect with.*/
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/technicalblog");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        return ds;
    }
}
