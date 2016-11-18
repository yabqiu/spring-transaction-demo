package cc.unmi;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class UserService {
    @Inject
    UserDao userDao;

    public void addUsers(User... users) {
        userDao.addUsers(users);

    }

    public void addUser(User user) {
        userDao.addUsers(user);
    }

    public void deleteById(int id) {

    }
}
