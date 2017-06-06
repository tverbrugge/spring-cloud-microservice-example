package application;

import application.clients.WxDailySummaryClient;
import application.clients.WxDailySummaryRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by troy on 6/6/17.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public WxDailySummaryClient getWxDailySummaryClient() {
        return new WxDailySummaryRestClient();
    }

}
