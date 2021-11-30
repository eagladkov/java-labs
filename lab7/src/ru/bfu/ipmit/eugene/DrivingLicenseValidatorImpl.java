package ru.bfu.ipmit.eugene;

import ru.bfu.ipmit.eugene.model.DrivingLicense;
import ru.bfu.ipmit.eugene.model.ValidationResult;
import ru.bfu.ipmit.eugene.validation.crossfield.ValidAge;
import ru.bfu.ipmit.eugene.validation.crossfield.ValidDatesRelation;
import ru.bfu.ipmit.eugene.validation.field.NotBlank;
import ru.bfu.ipmit.eugene.validation.field.ValidCategories;
import ru.bfu.ipmit.eugene.validation.field.ValidDate;
import ru.bfu.ipmit.eugene.validation.field.ValidLicenseNumber;
import ru.bfu.ipmit.eugene.validator.DrivingLicenseValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DrivingLicenseValidatorImpl implements DrivingLicenseValidator {

    private final Map<String, Integer> CATEGORIES = new HashMap<>();

    public DrivingLicenseValidatorImpl() {
        Map<String, Integer> mainCategories = Map.of
                (
                        "M", 16,
                        "A", 18,
                        "B", 18,
                        "C", 18,
                        "D", 21,
                        "Tm", 21,
                        "Tb", 21
                );
        CATEGORIES.putAll(mainCategories);

        Map<String, Integer> additionalCategories = Map.of
                (
                        "A1", 16,
                        "B1", 18,
                        "BE", 18,
                        "C1", 18,
                        "CE", 18,
                        "C1E", 18,
                        "D1", 21,
                        "DE", 21,
                        "D1E", 21
                );
        CATEGORIES.putAll(additionalCategories);
    }

    @Override
    public ValidationResult validate(DrivingLicense drivingLicense) throws IllegalAccessException, ParseException, NoSuchFieldException {
        List<String> errors = new ArrayList<>();

        Class<? extends DrivingLicense> clazz = drivingLicense.getClass();
        validateFields(clazz, drivingLicense, errors);
        validateCrossFields(clazz, drivingLicense, errors);
        return new ValidationResult(errors.isEmpty(), errors);
    }

    private void validateCrossFields(
            Class<? extends DrivingLicense> clazz,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException, ParseException, NoSuchFieldException {
        validateAge(clazz, drivingLicense, errors);
        validateDatesRelation(clazz, drivingLicense, errors);
    }

    private void validateDatesRelation(
            Class<? extends DrivingLicense> clazz,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws NoSuchFieldException, ParseException, IllegalAccessException {
        Annotation annotation = clazz.getAnnotation(ValidDatesRelation.class);
        if (annotation != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            Field dateOfBirthField = clazz.getDeclaredField("dateOfBirth");
            dateOfBirthField.setAccessible(true);
            Field validFromField = clazz.getDeclaredField("validFrom");
            validFromField.setAccessible(true);
            Field validToField = clazz.getDeclaredField("validTo");
            validToField.setAccessible(true);

            Date dateOfBirth = format.parse(String.valueOf(
                    dateOfBirthField.get(drivingLicense)
            ));
            Date validFrom = format.parse(String.valueOf(
                    validFromField.get(drivingLicense)
            ));
            Date validTo = format.parse(String.valueOf(
                    validToField.get(drivingLicense)
            ));

            if (!dateOfBirth.before(validFrom)) {
                errors.add("Дата рождения (" + dateOfBirth + ") больше даты " +
                        "выдачи удостоверения (" + validFrom + ")");
            }
            if (!validFrom.before(validTo)) {
                errors.add("Дата выдачи удостоверения (" + validFrom + ") больше даты " +
                        "окончания срока действия удостоверения (" + validTo + ")");
            }

            dateOfBirthField.setAccessible(false);
            validFromField.setAccessible(false);
            validToField.setAccessible(false);
        }
    }

    private void validateAge(
            Class<? extends DrivingLicense> clazz,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws NoSuchFieldException, ParseException, IllegalAccessException {
        Annotation annotation = clazz.getAnnotation(ValidAge.class);
        if (annotation != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            Field dateOfBirthField = clazz.getDeclaredField("dateOfBirth");
            dateOfBirthField.setAccessible(true);
            Field validFromField = clazz.getDeclaredField("validFrom");
            validFromField.setAccessible(true);
            Field categoriesField = clazz.getDeclaredField("categories");
            categoriesField.setAccessible(true);

            Date dateOfBirth = format.parse(String.valueOf(
                    dateOfBirthField.get(drivingLicense)
            ));
            Date validFrom = format.parse(String.valueOf(
                    validFromField.get(drivingLicense)
            ));
            long millis = validFrom.getTime() - dateOfBirth.getTime();
            Integer gettingAge = Math.toIntExact(millis / (365L * 24 * 60 * 60 * 1000));

            List<String> categories = (List<String>) categoriesField.get(drivingLicense);
            categories.stream()
                    .filter(category -> CATEGORIES.get(category) > gettingAge)
                    .forEach(category -> errors.add("Неверный возраст получения прав категории: " + category
                            + ". Минимальный возраст - " + CATEGORIES.get(category) + ", текущий - " + gettingAge));

            dateOfBirthField.setAccessible(false);
            validFromField.setAccessible(false);
        }
    }

    private void validateFields(
            Class<? extends DrivingLicense> clazz,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            validateNotBlank(field, drivingLicense, errors);
            validateCategories(field, drivingLicense, errors);
            validateDate(field, drivingLicense, errors);
            validateLicenseNumber(field, drivingLicense, errors);
            field.setAccessible(false);
        }
    }

    private void validateLicenseNumber(
            Field field,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException {
        Annotation annotation = field.getAnnotation(ValidLicenseNumber.class);
        if (annotation != null) {
            String value = String.valueOf(field.get(drivingLicense));
            String licensePattern = "\\d{2}.\\d{2}.\\d{6}";
            if (!value.matches(licensePattern)) {
                errors.add("Неверно указан номер водительского удостоверения: " + value);
            }
        }
    }

    private void validateDate(
            Field field,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException {
        Annotation annotation = field.getAnnotation(ValidDate.class);
        if (annotation != null) {
            String value = String.valueOf(field.get(drivingLicense));
            String datePattern = "\\d{2}.\\d{2}.\\d{4}";
            if (!value.matches(datePattern)) {
                errors.add("Неверно указана дата: " + value);
            }
        }
    }

    private void validateCategories(
            Field field,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException {
        Annotation annotation = field.getAnnotation(ValidCategories.class);
        if (annotation != null) {
            List<String> value = (List<String>) field.get(drivingLicense);
            value.stream()
                    .filter(category -> !CATEGORIES.containsKey(category))
                    .forEach(category -> errors.add("Неверно указана категория: " + category));
        }
    }

    private void validateNotBlank(
            Field field,
            DrivingLicense drivingLicense,
            List<String> errors
    ) throws IllegalAccessException {
        Annotation annotation = field.getAnnotation(NotBlank.class);
        if (annotation != null) {
            String value = String.valueOf(field.get(drivingLicense));
            if (isBlank(value)) {
                errors.add("Поле " + field.getName() + " пустое");
            }
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

}
