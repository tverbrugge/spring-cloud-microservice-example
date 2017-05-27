package data.config;

import data.domain.nodes.DailySummary;
import data.domain.nodes.DailySummaryWriter;
import data.domain.nodes.User;
import data.domain.nodes.UserWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
    DailySummaryCSVReader dailySummaryCSVReader;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    UserDao userRepository;

    @Autowired
    DailySummaryDao dailySummaryRepository;

//    @Autowired
//    public UserDaoImpl customerDao;

    @Bean(name = "readUsersJob")
    public Job readUsersJob(FlatFileItemReader<User> flatFileItemReader) {
        return jobBuilderFactory.get("readUsersJob").incrementer(new RunIdIncrementer()).flow(readUsersJobStep1(flatFileItemReader)).end().build();
    }

    @Bean
    public Step readUsersJobStep1(FlatFileItemReader<User> flatFileItemReader) {
        return stepBuilderFactory.get("readUsersJobStep1").<User, User>chunk(2).reader(flatFileItemReader).writer(
                new UserWriter(userRepository)).build();
    }

    @Bean(name = "readWxDataJob")
    public Job readWxDataJob() {
        return jobBuilderFactory.get("readWxDataJob").incrementer(new RunIdIncrementer()).flow(readWxDataStep1()).end().build();
    }

    @Bean
    public Step readWxDataStep1() {
        return stepBuilderFactory.get("readWxDataStep1").<DailySummary, DailySummary>chunk(2).reader(dailySummaryCSVReader).writer(
                new DailySummaryWriter(dailySummaryRepository)).build();
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
