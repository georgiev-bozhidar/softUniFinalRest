package org.georgievbozhidar.softunifinal2rest.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.georgievbozhidar.softunifinal2rest.validation.UniqueAddressValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueAddressValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAddress {
    String message() default "Address must be unique.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
