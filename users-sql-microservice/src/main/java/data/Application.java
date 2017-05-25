package data;

import data.domain.nodes.User;
import data.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.util.Collections;


@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
//@EnableNeo4jRepositories
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@ComponentScan(basePackages = {"data.config"})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public Application() {
    }

    private static final boolean runAsWebService = false;

    public static void main(String[] args) {

        if (!runAsWebService) {
//            System.getProperties().setProperty("spring.profiles.active", "demo");
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

    @Bean
    @Profile("demo")
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            repository.save(new User());
            log.info("findAll");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }

        };
    }

    @Bean
    @Profile("microservice")
    public ResourceProcessor<Resource<User>> movieProcessor() {
        return new ResourceProcessor<Resource<User>>() {
            @Override
            public Resource<User> process(Resource<User> resource) {

                resource.add(new Link("/movie/movies", "movies"));
                return resource;
            }
        };
    }
}
