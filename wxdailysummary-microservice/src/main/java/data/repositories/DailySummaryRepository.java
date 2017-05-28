package data.repositories;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
@RepositoryRestResource(collectionResourceRel = "wxdailysummary", path = "wxdailysummary")
@Repository
public interface DailySummaryRepository extends PagingAndSortingRepository<DailySummary, Long> {

    List<DailySummary> findAll();

    @RequestMapping(value = "date", method = RequestMethod.GET)
    DailySummary findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date);

}
