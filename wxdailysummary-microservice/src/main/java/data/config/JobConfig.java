package data.config;

import data.domain.nodes.User;
import data.domain.nodes.UserWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by troy on 5/19/17.
 */
@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    UserDao userRepository;


//    @Autowired
//    public UserDaoImpl customerDao;

    @Bean
    public Job job(FlatFileItemReader<User> flatFileItemReader) {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).flow(step1(flatFileItemReader)).end().build();
    }

    @Bean
    public Step step1(FlatFileItemReader<User> flatFileItemReader) {
        return stepBuilderFactory.get("step1").<User, User>chunk(2).reader(flatFileItemReader).writer(
                new UserWriter(userRepository)).build();
    }

//    @Bean(name = "jobRepository")
//    public JobRepositoryFactoryBean getJobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) {
//        JobRepositoryFactoryBean jobRepository = new JobRepositoryFactoryBean();
//        jobRepository.setDatabaseType("mysql");
//        jobRepository.setDataSource(dataSource);
//        jobRepository.setTransactionManager(transactionManager);
//
//        return jobRepository;
//    }

    /*
    public MapJobRepositoryFactoryBean getJobRepository()
    {

    }
     */

//    @Bean(name = "jobLauncher")
//    public JobLauncher getJobLauncher(JobRepository jobRepository) {
//
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        return jobLauncher;
//
//
//    }

}
