package ru.bfu.ipmit.eugene;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UrlConverterImpl implements UrlConverter{

    private UrlConfig config;

    @Override
    public UrlFragments parseUrl(String url) {
        String[] paramSplit = url.split(config.getQuestion());
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
            return new ExtUrlFragmentsImpl(method, protocol, host, path, params, config);
        } else {
            return new URLs(protocol, host, path, params, config);
        }
    }

    @Override
    public String toString(UrlFragments fragments) {
        String result = "";
        if (fragments instanceof UrlFragmentsExt) {
            result += "(" + ((UrlFragmentsExt) fragments).getHTTPMethod() + ")";
        }
        result += fragments.getProtocol() + config.getSlash() + config.getSlash();
        result += String.join(config.getPoint(),fragments.getHostFragments());
        result += String.join(config.getSlash(), fragments.getPathFragments());
        result += config.getQuestion() + String.join(config.getAnd(), fragments.getQueryParamValues());
        return result;
    }

    @Override
    public void setConfig(UrlConfig config) {
        this.config = config;
    }
}
