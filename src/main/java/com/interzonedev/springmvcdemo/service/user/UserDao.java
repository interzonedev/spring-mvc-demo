package com.interzonedev.springmvcdemo.service.user;

import java.util.List;

public interface UserDao {
	public User getUserById(Long id);

	public List<User> getAllUsers();

	public User createUser(String firstName, String lastName, boolean admin);

	public int updateUser(User user);

	public int deleteUser(User user);
}
