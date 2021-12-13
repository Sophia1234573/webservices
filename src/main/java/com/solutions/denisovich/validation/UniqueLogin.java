package com.solutions.denisovich.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueLogin {

    public String message() default "There is already user with this login!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}