package ru.bfu.ipmit.eugene;

public class PremiumSub implements BrowserConfig {
    @Override
    public boolean check(ExtUrlFragments url) {
        return (url.getHTTPMethod().equals("POST") || url.getHTTPMethod().equals("PUT")) &&
                (url.getHeaders() == null || url.getHeaders().length == 0);
    }
}
