package cc.unmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Inject
    UserDao userDao;

    @Inject
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void doSomething() {
        try {
            userService.addUsers(new User(2, "xyz"), new User(3, ""));
        } finally {
            List<User> users = userDao.loadAll();
            System.out.println(users);
        }
    }
}
