package com.interzonedev.springmvcdemo.service.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAdmin(rs.getBoolean("admin"));
        user.setTimeCreated(rs.getTimestamp("time_created"));
        user.setTimeUpdated(rs.getTimestamp("time_updated"));

        return user;
    }

}
