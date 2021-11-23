package ru.bfu.ipmit.eugene;

public class StandardSub implements BrowserConfig {
    @Override
    public boolean check(ExtUrlFragments url) {
        return url.getHTTPMethod().equals("POST") &&
                (url.getBody() == null || url.getBody().length == 0) &&
                (url.getHeaders() == null || url.getHeaders().length == 0);
    }
}
