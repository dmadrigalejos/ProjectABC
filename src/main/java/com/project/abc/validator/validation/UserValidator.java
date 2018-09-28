package com.project.abc.validator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.abc.user.User;
import com.project.abc.user.UserDAO;
import com.project.abc.utility.Messages;
import com.project.abc.validator.annotation.ValidUser;


public class UserValidator implements ConstraintValidator<ValidUser, User> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void initialize(ValidUser arg0) { }

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		boolean isValid = true;

		if (userDAO.isUsernameExists(user)) {
			context.buildConstraintViolationWithTemplate(Messages.User.FAIL_DUPLICATE_USERNAME)
					.addPropertyNode("userName").addConstraintViolation();

			isValid = false;
		}

		return isValid;
	}
}
