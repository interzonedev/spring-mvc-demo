package com.interzonedev.springmvcdemo.service.user;

import java.util.List;

public interface UserService {
	public User getUserById(Long id);

	public List<User> getAllUsers();

	public User createUser(String firstName, String lastName, boolean admin);

	public void updateUser(User user);

	public void deleteUser(User user);
}
