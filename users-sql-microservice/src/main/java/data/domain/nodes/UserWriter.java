package data.domain.nodes;

import data.config.UserDao;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by troy on 5/24/17.
 */
public class UserWriter implements ItemWriter<User> {

    private final UserDao userDao;

    public UserWriter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void write(List<? extends User> list) throws Exception {
        userDao.insert(list);
    }
}
