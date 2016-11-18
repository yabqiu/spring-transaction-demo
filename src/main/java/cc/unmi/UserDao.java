package cc.unmi;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Stream;

@Named
//@Transactional
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Transactional
    public void addUsers(User... users) {
        Stream.of(users).forEach(user ->
                jdbcTemplate.update("insert into users(id, name) values(?, ?)", user.getId(), user.getName()));
    }

    public List<User> loadAll() {
        return jdbcTemplate.query("select id, name from users", new BeanPropertyRowMapper<>(User.class));
    }
}
