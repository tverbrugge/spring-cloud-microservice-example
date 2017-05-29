package data;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;
import data.repositories.DailySummaryRepository;
import data.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.util.Collections;


@SpringBootApplication()
//@EnableDiscoveryClient
//@EnableZuulProxy
//@EnableHystrix
@ComponentScan(basePackages = {"data.config"})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public Application() {
    }

    private static final boolean runAsWebService = true;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @javax.annotation.Resource(name = "readWxDataJob")
    Job readWxDataJob;


    public static void main(String[] args) {

        if (!runAsWebService) {
            System.getProperties().setProperty("spring.profiles.active", "demo");
        }

        SpringApplication app = new SpringApplication();
        app.setWebEnvironment(runAsWebService);
        app.setSources(Collections.singleton(Application.class));

        ConfigurableApplicationContext ctx = app.run(args);
        if (runAsWebService) {
            RepositoryRestConfiguration restConfiguration = ctx.getBean("config", RepositoryRestConfiguration.class);
            restConfiguration.exposeIdsFor(User.class);
        }
    }

//    @Bean
//    @Profile("!demo")
    public CommandLineRunner readInDataFromCSVFiles(DailySummaryRepository dailySummaryRepository) {
        return (args) -> {
            insertDailySummariesFromCSVFileJob();
            log.info("running findAll");
            log.info("Current count = " + dailySummaryRepository.count());
            for (DailySummary dailySummary : dailySummaryRepository.findAll()) {
//                log.info(dailySummary.toString());
            }

        };
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterInit() {
        log.info("ApplicationReadyEvent");
        log.info("Current count = " + dailySummaryRepository.count());
    }

    private DailySummaryRepository dailySummaryRepository;

    @Bean
    @Profile("!demo")
    public CommandLineRunner checkCSVFileData(DailySummaryRepository dailySummaryRepository) {
        return (args) -> {
            this.dailySummaryRepository = dailySummaryRepository;
            log.info("running findAll");
            log.info("Current count = " + dailySummaryRepository.count());
//            for (DailySummary dailySummary : dailySummaryRepository.findAll()) {
////                log.info(dailySummary.toString());
//            }

        };
    }


    @Bean
    @Profile("demo")
    public CommandLineRunner demo(DailySummaryRepository dailySummaryRepository) {
        return (args) -> {
            insertDailySummariesFromCSVFileJob();
            log.info("running findAll");
            log.info("Current count = " + dailySummaryRepository.count());
            for (DailySummary dailySummary : dailySummaryRepository.findAll()) {
                log.info(dailySummary.toString());
            }

        };
    }

    private void insertDailySummariesFromCSVFileJob() {
        try {
            jobLauncher.run(readWxDataJob, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ResourceProcessor<Resource<User>> movieProcessor() {
        return new ResourceProcessor<Resource<User>>() {
            @Override
            public Resource<User> process(Resource<User> resource) {

                resource.add(new Link("/wx/wxdata", "wx"));
                return resource;
            }
        };
    }

}
