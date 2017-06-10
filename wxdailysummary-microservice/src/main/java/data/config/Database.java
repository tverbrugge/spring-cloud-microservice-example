package data.config;

import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by troy on 5/19/17.
 */
// @Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
public class Database {

//    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder/*.addScript("classpath:sql/schema.sql")*/.
                setType(EmbeddedDatabaseType.H2).build();
//        return null;
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName(Driver.class.getName());
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("");
//
//
//        return driverManagerDataSource;
    }

//    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, PlatformTransactionManager transactionManager) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        sessionFactory.setHibernateProperties(new UsersDataSourceProperties());

        return sessionFactory;
    }

//    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

 //   @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        return new Properties() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {

                setProperty("hibernate.hbm2ddl.auto", "create");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            }
        };
    }

}
