package be.abis.exercise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import be.abis.exercise.validator.ConfirmPasswordValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface GoodPassword {
	
	String message() default "The new passwords have to be the same";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
