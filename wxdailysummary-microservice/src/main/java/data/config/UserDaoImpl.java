package data.config;

import data.config.UserDao;
import data.domain.nodes.User;
import data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by troy on 5/24/17.
 */
@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource dataSource;
    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insert(List<? extends User> users) {
        userRepository.save(users);
    }

    @Override
    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }
}
