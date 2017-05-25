package data.config;

import com.mysql.jdbc.Driver;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

/**
 * Created by troy on 5/19/17.
 */
@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Database {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Driver.class.getName());
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("");


        return  driverManagerDataSource;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }


}
