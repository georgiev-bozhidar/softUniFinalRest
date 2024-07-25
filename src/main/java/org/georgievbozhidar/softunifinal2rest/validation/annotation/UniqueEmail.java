package org.georgievbozhidar.softunifinal2rest.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.georgievbozhidar.softunifinal2rest.validation.UniqueEmailValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email must be unique.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
