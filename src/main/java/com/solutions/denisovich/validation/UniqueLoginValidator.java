package com.solutions.denisovich.validation;

import com.solutions.denisovich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    private final UserService userService;

    @Autowired
    public UniqueLoginValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(UniqueLogin constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isLoginAlreadyExists(value);
    }
}