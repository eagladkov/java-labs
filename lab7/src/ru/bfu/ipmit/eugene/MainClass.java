package ru.bfu.ipmit.eugene;

import ru.bfu.ipmit.eugene.model.DrivingLicense;
import ru.bfu.ipmit.eugene.model.ValidationResult;
import ru.bfu.ipmit.eugene.validator.DrivingLicenseValidator;

import java.text.ParseException;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws ParseException, NoSuchFieldException, IllegalAccessException {
        DrivingLicense license = new DrivingLicense(
                "Гладков",
                "Евгений Александрович",
                "30.04.1999",
                "г. Владивосток",
                "01.01.2020",
                "01.01.2019",
                "ГИБДД 0000",
                "0000 0000 00",
                "г. Калининград",
                List.of("B", "D")
        );

        DrivingLicenseValidator validator = new DrivingLicenseValidatorImpl();
        ValidationResult result = validator.validate(license);

        if (!result.isValid()) {
            result.getValidationErrors().forEach(System.out::println);
        } else {
            System.out.println("Водительское удостоверение заполнено верно");
        }
    }
}
