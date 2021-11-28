package com.pluralsight.repository;

import com.pluralsight.repository.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.pluralsight.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //earlier jdbc template was not private

    @Override
    public User createUser(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement pS = con.prepareStatement(
                        "INSERT INTO users (firstname, lastname, age, email) " +
                                "           VALUES (?, ?, ?, ?)", new String[]{"id"});
                pS.setString(1, user.getFirstName());
                pS.setString(2, user.getLastName());
                pS.setInt(3, user.getAge());
                pS.setString(4, user.getEmail());
                return pS;
            }
        }, keyHolder);

        Number id = keyHolder.getKey();

        return getUser(id.intValue());
    }

    public User getUser(Integer id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?",
                new UserRowMapper(), id);
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET firstname = ?, lastname = ?, age = ?," +
                "email = ? WHERE id = ?", user.getFirstName(), user.getLastName(),
                user.getAge(), user.getEmail(), user.getId());
        return user;
    }

    @Override
    public void batch(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("UPDATE users SET localization = ? WHERE id = ?", pairs);
    }

    @Override
    public void deleteUser(Integer id) {
        NamedParameterJdbcTemplate namedTemplate =
                                new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        namedTemplate.update("DELETE FROM users WHERE id = :id", paramMap);
    }

    @Override
    public void updateUsers(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("UPDATE user SET localization = ? WHERE id = ?", pairs);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        return users;
    }

}
