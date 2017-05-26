package data.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by troy on 5/22/17.
 */
@Profile("microservice")
@Configuration
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
public class EurekaClientConfiguration {
}
