package org.georgievbozhidar.softunifinal2rest.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.georgievbozhidar.softunifinal2rest.validation.UniqueUsernameValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Username must be unique.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
