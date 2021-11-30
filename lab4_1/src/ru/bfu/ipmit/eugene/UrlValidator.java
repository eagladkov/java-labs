package ru.bfu.ipmit.eugene;

import java.util.ArrayList;
import java.util.List;

public class UrlValidator {

    public static void validate(UrlConfig urlConfig) {
        List<String> config = List.of(urlConfig.getSlash(), urlConfig.getColon(), urlConfig.getAnd(), urlConfig.getPoint(),
                urlConfig.getQuestion(), urlConfig.getEqually());

        List<String> uniqElements = new ArrayList<>();

        config.forEach(configElement -> {
            if (configElement.replaceAll("[a-zA-Z0-9]", "").equals("")) {
                throw new InvalidUrlConfigExt("Неверный разделитель " + configElement);
            }

            if (!uniqElements.contains(configElement)) {
                uniqElements.add(configElement);
            } else {
                throw new InvalidUrlConfigExt("Повторяющийся разделитель " + configElement);
            }
        });

    }

}
