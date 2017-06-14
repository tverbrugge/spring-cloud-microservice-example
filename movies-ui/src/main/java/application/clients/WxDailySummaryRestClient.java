package application.clients;

import application.models.WxDailySummary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by troy on 6/6/17.
 */
public class WxDailySummaryRestClient implements WxDailySummaryClient {

    private static final String DAILYSUMMARY_PATH = "/wxdailysummary";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    //    @Autowired
    private String uri = "http://wxdailysummary:8765";
//    private String uri = "http://127.0.0.1:8765";

    private RestTemplate restTemplate;
    private RestTemplate pagedRestTemplate;


    protected RestTemplate getPagedRestTemplate() {
        if(pagedRestTemplate == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new Jackson2HalModule());

            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
            converter.setObjectMapper(mapper);

            pagedRestTemplate = new RestTemplate(Arrays.asList(converter));
        }
        return pagedRestTemplate;

    }

    protected RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    @Override
    public PagedResources<WxDailySummary> findAll() {
        String url = uri + DAILYSUMMARY_PATH;
        ResponseEntity<PagedResources<WxDailySummary>> responseEntity = getPagedRestTemplate().exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<PagedResources<WxDailySummary>>() {
                });
        PagedResources<WxDailySummary> resources = responseEntity.getBody();
        return resources;
//        return getRestTemplate().getForObject(uri + DAILYSUMMARY_PATH, PagedResources.class);
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
