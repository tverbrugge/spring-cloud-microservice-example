package application.clients;

import application.models.WxDailySummary;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
@FeignClient("wxdailysummary")
public interface WxDailySummaryClient {


    @RequestMapping(method = RequestMethod.GET, value = "/wxdailysummary")
    PagedResources<WxDailySummary> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/wxdailysummary/{id}")
    List<WxDailySummary> findById(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/wxdailysummary",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createWxDailySummary(@RequestBody WxDailySummary user);

    @RequestMapping(method = RequestMethod.GET, value = "/wxdailysummary/findByDate")
    WxDailySummary findByDate(@RequestParam("date") Date date);
}
