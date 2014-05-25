package com.interzonedev.springmvcdemo.service.user;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * A non thread safe, non-concurrent implementation of CRUD services for "persisting" {@link User} instances. Uses a
 * {@code java.util.Map} for the persistence mechanism and is only persistant for the life of the JVM/container.
 */
@Named("userDaoInMemory")
public class UserDaoInMemory implements UserDao {

    private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    private final Map<Long, User> instances = new HashMap<Long, User>();

    private Long index = 0L;

    private boolean inited = false;

    public void init() {
        log.debug("init");

        createUser("Gern", "Blanston", true);
        createUser("Uncle", "Fester", false);
    }

    @Override
    public User getUserById(Long id) {
        log.debug("getUserById: id = " + id);

        checkInit();

        User user = instances.get(id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("getAllUsers");

        checkInit();

        List<User> all = Collections.unmodifiableList((List<User>) instances.values());
        return all;
    }

    @Override
    public User createUser(String firstName, String lastName, boolean admin) {
        log.debug("createUser: firstName = " + firstName + ", lastName = " + lastName + ", admin = " + admin);

        checkInit();

        Date now = new Date();

        User user = new User();
        user.setId(++index);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAdmin(admin);
        user.setTimeCreated(now);
        user.setTimeUpdated(now);

        instances.put(index, user);

        return user;
    }

    @Override
    public int updateUser(User user) {
        log.debug("updateUser: user = " + user);

        checkInit();

        Date now = new Date();

        User userToUpdate = getUserById(user.getId());

        int numResults = 0;

        if (null != userToUpdate) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setAdmin(user.isAdmin());
            userToUpdate.setTimeUpdated(now);

            numResults = 1;
        }

        return numResults;
    }

    @Override
    public int deleteUser(User user) {
        log.debug("deleteUser: user = " + user);

        checkInit();

        Long userToDeleteId = user.getId();

        User userToDelete = getUserById(userToDeleteId);

        int numResults = 0;

        if (null != userToDelete) {
            instances.remove(userToDeleteId);

            numResults = 1;
        }

        return numResults;
    }

    private void checkInit() {
        if (!inited) {
            inited = true;
            init();
        }
    }
}
