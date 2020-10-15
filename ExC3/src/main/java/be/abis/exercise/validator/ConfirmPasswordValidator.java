package be.abis.exercise.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.abis.exercise.annotation.GoodPassword;
import be.abis.exercise.model.Password;

public class ConfirmPasswordValidator  implements ConstraintValidator<GoodPassword, Password>{

	@Override
	public boolean isValid(Password pwd, ConstraintValidatorContext context) {
		
		return pwd.getPassword1().equals(pwd.getConfirmPassword());
	}
}
