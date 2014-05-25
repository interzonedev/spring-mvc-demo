package com.interzonedev.springmvcdemo.service.user;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Named("userValidator")
public class UserValidator implements Validator {

    private boolean editing = false;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Don't bother to validate further if there are already errors.
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;

        if (editing && (null == user.getId())) {
            errors.reject("error.user.nullId");
        }

        if ((null != user.getId()) && (user.getId() < 1L)) {
            errors.reject("error.user.nonPositiveId");
        }

        if (StringUtils.isBlank(user.getFirstName())) {
            errors.reject("error.user.firstName");
        }

        if (StringUtils.isBlank(user.getLastName())) {
            errors.reject("error.user.lastName");
        }
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

}
