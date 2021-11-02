package ru.bfu.ipmit.eugene;

public class ExtUrlFragmentsImpl extends  URLs implements UrlFragmentsExt {

    private String httpMethod;

    public ExtUrlFragmentsImpl(String httpMethod, String protocol, String hostFragments, String pathFragments, String queryParamValues, UrlConfig config) {
        super(protocol, hostFragments, pathFragments, queryParamValues, config);
        this.httpMethod = httpMethod;
    }

    @Override
    public String getHTTPMethod() {
        return this.httpMethod;
    }
}
