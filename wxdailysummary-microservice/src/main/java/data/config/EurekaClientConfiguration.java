package data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by troy on 5/22/17.
 */
@Profile("!demo")
@Configuration
//@EnableDiscoveryClient
//@EnableZuulProxy
//@EnableHystrix
public class EurekaClientConfiguration {
}
