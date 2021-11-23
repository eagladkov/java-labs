package ru.bfu.ipmit.eugene;

public class UrlFragmentsImpl implements UrlFragments{
    private String protocol;
    private String[] hostFragments;
    private String[] pathFragments;
    private String[] queryParamValues;

    public UrlFragmentsImpl(String protocol, String hostFragments, String pathFragments, String queryParamValues, UrlConfig config) {
        this.protocol = protocol;
        this.hostFragments = hostFragments != null ? hostFragments.split(config.getPoint()) : new String[0];
        this.pathFragments = pathFragments != null ?pathFragments.split(config.getSlash()) : new String[0];
        this.queryParamValues = queryParamValues != null ? queryParamValues.split(config.getAnd()) : new String[0];
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String[] getHostFragments() {
        return this.hostFragments;
    }

    @Override
    public String[] getPathFragments() {
        return this.pathFragments;
    }

    @Override
    public String[] getQueryParamValues() {
        return this.queryParamValues;
    }
}
