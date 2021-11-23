package ru.bfu.ipmit.eugene;
interface UrlConverter {
    UrlFragments parseUrl(String url);
    String toString(UrlFragments fragments);
    void setConfig(UrlConfig config);
    void setBrowserConfig(BrowserConfig config);
}
