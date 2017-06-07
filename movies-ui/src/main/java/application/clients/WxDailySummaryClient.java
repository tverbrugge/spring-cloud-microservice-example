package application.clients;

import application.models.WxDailySummary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
public interface WxDailySummaryClient {


    PagedResources<WxDailySummary> findAll();

    List<WxDailySummary> findById(@RequestParam("id") String id);

    void createWxDailySummary(@RequestBody WxDailySummary user);

    WxDailySummary findByDate(@RequestParam("date") @DateTimeFormat(style = "yyyy-mm-dd") Date date);
}
