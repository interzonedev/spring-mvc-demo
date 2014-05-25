package com.interzonedev.springmvcdemo.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ch.qos.logback.classic.Logger;

@Named("userDaoJdbc")
public class UserDaoJdbc extends JdbcDaoSupport implements UserDao {

    private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    @Inject
    public UserDaoJdbc(@Named("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public User getUserById(Long id) {
        log.debug("getUserById: id = " + id);

        String sql = "SELECT id, time_created, time_updated, first_name, last_name, admin FROM users WHERE id = ?";
        Object[] args = new Object[] { id };

        User user = (User) getJdbcTemplate().queryForObject(sql, new UserRowMapper(), args);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("getAllUsers");

        String sql = "SELECT id, time_created, time_updated, first_name, last_name, admin FROM users";

        List<User> users = getJdbcTemplate().query(sql, new UserRowMapper());

        return users;
    }

    @Override
    public User createUser(final String firstName, final String lastName, final boolean admin) {
        log.debug("createUser: firstName = " + firstName + ", lastName = " + lastName + ", admin = " + admin);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "INSERT INTO users (first_name, last_name, admin) VALUES (?, ?, ?)";

        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setBoolean(3, admin);
                return preparedStatement;
            }
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        User user = getUserById(id);

        return user;
    }

    @Override
    public int updateUser(final User user) {
        log.debug("updateUser: user = " + user);

        final String sql = "UPDATE users SET first_name = ?, last_name = ?, admin = ? WHERE id = ?";

        int numRows = getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setBoolean(3, user.isAdmin());
                preparedStatement.setLong(4, user.getId());
                return preparedStatement;
            }
        });

        return numRows;
    }

    @Override
    public int deleteUser(final User user) {
        log.debug("deleteUser: user = " + user);

        final String sql = "DELETE FROM users WHERE id = ?";

        int numRows = getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, user.getId());
                return preparedStatement;
            }
        });

        return numRows;
    }

}
