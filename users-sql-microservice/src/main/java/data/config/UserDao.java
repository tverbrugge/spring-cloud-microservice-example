package data.config;

import data.domain.nodes.User;

import java.util.List;

/**
 * Created by troy on 5/24/17.
 */
public interface UserDao {
    public void insert(List<? extends User> users);
    List<User> loadAllUsers();
}
