package application.clients;

import application.models.WxDailySummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by troy on 6/6/17.
 */
public class WxDailySummaryRestClient implements WxDailySummaryClient {

    private static final String DAILYSUMMARY_PATH = "/wxdailysummary";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    @Autowired
    private String uri;
    private RestTemplate restTemplate;

    protected RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    @Override
    public PagedResources<WxDailySummary> findAll() {
        return getRestTemplate().getForObject(uri + DAILYSUMMARY_PATH, PagedResources.class);
    }

    @Override
    public List<WxDailySummary> findById(String id) {
        return getRestTemplate().getForObject(uri + DAILYSUMMARY_PATH + "/" + id, List.class);
    }


    //    @RequestMapping(method = RequestMethod.GET, value = "/wxdailysummary/search/findByDate")
    //WxDailySummary findByDate(@RequestParam("date") @DateTimeFormat(style = "yyyy-mm-dd") Date date);

    @Override
    public void createWxDailySummary(WxDailySummary user) {

    }

    @Override
    public WxDailySummary findByDate(Date date) {
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("date", dateFormat.format(date));

        return getRestTemplate().getForObject(uri + DAILYSUMMARY_PATH + "/wxdailysummary/search/findByDate",
                WxDailySummary.class, dateMap);
    }
}
