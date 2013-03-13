package com.interzonedev.springmvcdemo.service.user;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

import ch.qos.logback.classic.Logger;

@Named("userService")
public class UserServiceImpl implements UserService {

	private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Inject
	@Named("userDaoJdbc")
	private UserDao userDao;

	@Inject
	@Named("userValidator")
	private UserValidator userValidator;

	@Inject
	@Named("jsr303Validator")
	private Validator jsr303Validator;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserById(Long id) {
		log.debug("getUserById: id = " + id);

		Assert.notNull(id, "getUserById: The id must be set");
		Assert.isTrue(id > 0, "getUserById: The id must be positive");

		User user = null;

		try {
			user = userDao.getUserById(id);
		} catch (EmptyResultDataAccessException e) {
			log.warn("getUserById: Could not retrieve the user with id = " + id);
		}

		return user;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getAllUsers() {
		log.debug("getAllUsers");

		List<User> allUsers = userDao.getAllUsers();

		return allUsers;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User createUser(String firstName, String lastName, boolean admin) {
		log.debug("createUser: firstName = " + firstName + ", lastName = " + lastName + ", admin = " + admin);

		Assert.hasText(firstName, "createUser: The first name must be set");
		Assert.hasText(lastName, "createUser: The last name must be set");

		User user = userDao.createUser(firstName, lastName, admin);
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User user) {
		log.debug("updateUser: user = " + user);

		Assert.notNull(user, "updateUser: The user must be set");

		validateUserWithJsr303Validator(user, true);

		int numResults = userDao.updateUser(user);

		if (0 == numResults) {
			String errorMessage = "updateUser: No user updated with id = " + user.getId();
			log.error(errorMessage);
			throw new EmptyResultDataAccessException(errorMessage, 1);
		}

		if (numResults > 1) {
			throw new DataIntegrityViolationException("updateUser: More than one user was updated for id = "
					+ user.getId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(User user) {
		log.debug("deleteUser: user = " + user);

		Assert.notNull(user, "deleteUser: The user must be set");
		Assert.notNull(user.getId(), "deleteUser: The user id must be set");
		Assert.isTrue(user.getId() > 0, "deleteUser: The user id must be positive");

		int numResults = userDao.deleteUser(user);

		if (0 == numResults) {
			String errorMessage = "deleteUser: No user deleted with id = " + user.getId();
			log.error(errorMessage);
			throw new EmptyResultDataAccessException(errorMessage, 1);
		}

		if (numResults > 1) {
			throw new DataIntegrityViolationException("deleteUser: More than one user was deleted for id = "
					+ user.getId());
		}
	}

	private void validateUserWithJsr303Validator(User user, boolean editing) {
		Set<ConstraintViolation<User>> errors = jsr303Validator.validate(user);

		if (!errors.isEmpty()) {
			throw new InvalidUserException();
		}

	}

	@SuppressWarnings("unused")
	private void validateUserWithSpringValidator(User user, boolean editing) {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(user, "user");

		userValidator.setEditing(editing);
		ValidationUtils.invokeValidator(userValidator, user, result);

		if (result.hasErrors()) {
			throw new InvalidUserException();
		}
	}
}
