package ua.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.validation.validator.PasswordEqualValidator;

@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualValidator.class)
public @interface PasswordsEqual {
	
	String message() default "Password not equal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
