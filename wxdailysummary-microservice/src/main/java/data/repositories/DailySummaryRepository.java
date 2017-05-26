package data.repositories;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
@RepositoryRestResource(collectionResourceRel = "wxdailysummary", path = "wxdailysummary")
@Repository
public interface DailySummaryRepository extends CrudRepository<DailySummary, Long> {

    List<DailySummary> findAll();

}
