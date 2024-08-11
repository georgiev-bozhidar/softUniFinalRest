package org.georgievbozhidar.softunifinal2rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.georgievbozhidar.softunifinal2rest.repository.LocationRepository;
import org.georgievbozhidar.softunifinal2rest.validation.annotation.UniqueAddress;

public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, String> {
    private final LocationRepository locationRepository;

    public UniqueAddressValidator(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.locationRepository.findByAddress(value).isEmpty();
    }

    @Override
    public void initialize(UniqueAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
