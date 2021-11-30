package ru.bfu.ipmit.eugene;

public class ExtUrlFragmentsImpl extends  UrlFragmentsImpl implements ExtUrlFragments {

    private String httpMethod;
    private String[] headers;
    private String[] body;

    public ExtUrlFragmentsImpl(String httpMethod, String protocol, String hostFragments, String pathFragments,
                               String queryParamValues, String body, String headers, UrlConfig config) {
        super(protocol, hostFragments, pathFragments, queryParamValues, config);
        this.httpMethod = httpMethod;
        this.body = body != null ? body.split(config.getAnd()) : new String[0];
        this.headers = headers != null ? headers.split(config.getAnd()) : new String[0];
    }

    @Override
    public String getHTTPMethod() {
        return this.httpMethod;
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public String[] getBody() {
        return body;
    }
}
