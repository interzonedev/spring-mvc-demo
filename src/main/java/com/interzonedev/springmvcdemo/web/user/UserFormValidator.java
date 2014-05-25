package com.interzonedev.springmvcdemo.web.user;

import javax.inject.Named;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Named("userFormValidator")
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Don't bother to validate further if there are already errors.
        if (errors.hasErrors()) {
            return;
        }

        UserForm userForm = (UserForm) target;

        // Check that the first and last names are not the same.
        if (userForm.getFirstName().trim().equals(userForm.getLastName().trim())) {
            errors.reject("error.userForm.sameNames");
        }
    }
}
