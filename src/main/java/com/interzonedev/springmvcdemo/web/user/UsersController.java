package com.interzonedev.springmvcdemo.web.user;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.springmvcdemo.service.user.User;
import com.interzonedev.springmvcdemo.service.user.UserService;
import com.interzonedev.springmvcdemo.web.DemoController;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends DemoController {

	@Inject
	@Named("userService")
	private UserService userService;

	@Inject
	@Named("userFormValidator")
	private UserFormValidator userFormValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		log.debug("getAllUsers");

		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);

		return "users/viewAllUsers";
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public String getUser(@PathVariable("id") Long id, Model model) {
		log.debug("getUser");

		User user = getUserById(id);

		model.addAttribute("user", user);

		String view = "users/viewUser";

		return view;
	}

	@RequestMapping(method = RequestMethod.GET, value = "new")
	public String displayNewForm(Model model) {
		log.debug("displayNewForm");

		model.addAttribute("userForm", new UserForm());

		return "users/userForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@Valid UserForm userForm, BindingResult result) {
		log.debug("createUser");

		ValidationUtils.invokeValidator(userFormValidator, userForm, result);

		if (result.hasErrors()) {
			log.debug("Form has errors");
			return "users/userForm";
		}

		String firstName = userForm.getFirstName().trim();
		String lastName = userForm.getLastName().trim();
		boolean admin = userForm.isAdmin();

		User user = userService.createUser(firstName, lastName, admin);

		return "redirect:/users/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}/edit")
	public String displayEditForm(@PathVariable("id") Long id, Model model) {
		log.debug("displayEditForm");

		User user = getUserById(id);

		UserForm userForm = new UserForm();
		userForm.setId(user.getId());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setAdmin(user.isAdmin());
		userForm.setEdit(true);

		model.addAttribute("userForm", userForm);

		return "users/userForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "{id}", params = "_method=put")
	public String updateUser(@PathVariable("id") Long id, Model model, @Valid UserForm userForm, BindingResult result) {
		log.debug("updateUser");

		ValidationUtils.invokeValidator(userFormValidator, userForm, result);

		if (result.hasErrors()) {
			log.debug("Form has errors");
			return "users/userForm";
		}

		User user = getUserById(id);
		user.setFirstName(userForm.getFirstName().trim());
		user.setLastName(userForm.getLastName().trim());
		user.setAdmin(userForm.isAdmin());

		userService.updateUser(user);

		return "redirect:/users/" + id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}", params = "_method=delete")
	public String deleteUser(@PathVariable("id") Long id) {
		log.debug("deleteUser");

		User user = getUserById(id);

		userService.deleteUser(user);

		return "redirect:/users/";
	}

	private User getUserById(Long id) {
		User user = userService.getUserById(id);

		if (null == user) {
			throw new RuntimeException("error.resource.not.found");
		}

		return user;
	}
}
