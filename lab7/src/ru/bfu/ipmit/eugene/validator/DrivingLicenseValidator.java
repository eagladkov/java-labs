package ru.bfu.ipmit.eugene.validator;

import ru.bfu.ipmit.eugene.model.DrivingLicense;
import ru.bfu.ipmit.eugene.model.ValidationResult;

import java.text.ParseException;

/**
 * Интерфейс для валидации водительского удостоверения, который необходимо реализовать
 */
public interface DrivingLicenseValidator {
    ValidationResult validate(DrivingLicense drivingLicense) throws IllegalAccessException, ParseException, NoSuchFieldException;
}
