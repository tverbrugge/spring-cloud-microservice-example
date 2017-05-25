package data.repositories;

import data.domain.nodes.User;
// import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // TODO: Replace with @Param("name") when Spring Data Neo4j supports names vs. positional arguments
    List<User> findByLastName(String name);

    List<User> findAll();

}
