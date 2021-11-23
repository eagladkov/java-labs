package ru.bfu.ipmit.eugene;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UrlConverterImpl implements UrlConverter {

    private static final UrlConfig DEFAULT_CONFIG = new UrlConfig
            (
                    "/",
                    "\\.",
                    ":",
                    "\\?",
                    "&",
                    "="
            );


    private UrlConfig config;
    private BrowserConfig browserConfig;

    @Override
    public UrlFragments parseUrl(String url) {
        ExtUrlFragments result;
        String[] headersSplit = url.split(config.getQuestion() + config.getQuestion() + config.getQuestion());
        String headers = headersSplit.length != 1 ? headersSplit[1] : null;
        String[] bodySplit = headersSplit[0].split(config.getQuestion() + config.getQuestion());
        String body = bodySplit.length != 1 ? bodySplit[1] : null;
        String[] paramSplit = bodySplit[0].split(config.getQuestion());
        String params = paramSplit.length != 1 ? paramSplit[1] : null;
        String[] hostSplit = paramSplit[0].split(config.getSlash() + config.getSlash());
        String[] pathSplit = hostSplit.length != 1 ? hostSplit[1].split(config.getSlash()) : null;
        String path = pathSplit != null ? Arrays.stream(pathSplit)
                .skip(1).collect(Collectors.joining(config.getSlash())) : null;
        String host = pathSplit != null ? pathSplit[0] : null;
        String[] protocolSplit = hostSplit[0].split("\\)");
        String protocol = protocolSplit.length != 1 ? protocolSplit[1] : protocolSplit[0];
        String method = protocolSplit.length != 1 ? protocolSplit[0].replaceAll("\\(", "") : null;
        if (method != null) {
            result = new ExtUrlFragmentsImpl(method, protocol, host, path, params, body, headers, config);
        } else {
            return new UrlFragmentsImpl(protocol, host, path, params, config);
        }
        if (browserConfig.check(result)) {
            return result;
        } else {
            System.out.println("Тип подписки у пользователя не совпадает");
            return null;
        }
    }

    @Override
    public String toString(UrlFragments fragments) {
        if (fragments == null) {
            return "";
        }
        String result = "";
        if (fragments instanceof ExtUrlFragments) {
            result += "(" + ((ExtUrlFragments) fragments).getHTTPMethod() + ")";
        }
        result += fragments.getProtocol() + config.getSlash() + config.getSlash();
        result += String.join(config.getPoint(),fragments.getHostFragments());
        result += String.join(config.getSlash(), fragments.getPathFragments());
        result += config.getQuestion() + String.join(config.getAnd(), fragments.getQueryParamValues());
        if (fragments instanceof ExtUrlFragments) {
            ExtUrlFragments extFragments = (ExtUrlFragments) fragments;
            result += config.getQuestion() + config.getQuestion() + String.join(config.getAnd(), extFragments.getBody());
            result += config.getQuestion() + config.getQuestion() + config.getQuestion() +String.join(config.getAnd(), extFragments.getHeaders());
        }
        return result;
    }

    public void setConfig(UrlConfig config) {
        try {
            UrlValidator.validate(config);
        } catch (InvalidUrlConfigExt e) {
            System.out.println(e.getMessage());
            System.out.println("Конфиг заменен на дефолтный");
            config = DEFAULT_CONFIG;
        }
        this.config = config;
    }

    @Override
    public void setBrowserConfig(BrowserConfig config) {
        this.browserConfig = config;
    }
}
