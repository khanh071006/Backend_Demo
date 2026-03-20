package com.example.Project2.service.validator;

import com.example.Project2.domain.dto.RegisterDTO;
import com.example.Project2.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {
    private UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords must match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        if (this.userService.checkExistEmail(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email has exist")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
