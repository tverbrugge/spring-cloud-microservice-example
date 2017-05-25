package data.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by troy on 5/19/17.
 */
public class JobConfig {

    @Bean(name="jobRepository")
    public JobRepositoryFactoryBean getJobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
    {
        JobRepositoryFactoryBean jobRepository = new JobRepositoryFactoryBean();
        jobRepository.setDatabaseType("mysql");
        jobRepository.setDataSource(dataSource);
        jobRepository.setTransactionManager(transactionManager);

        return jobRepository;
    }

    /*
    public MapJobRepositoryFactoryBean getJobRepository()
    {

    }
     */

    @Bean(name="jobLauncher")
    public JobLauncher getJobLauncher(JobRepository jobRepository) {

        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;


    }

}
