package ru.bfu.ipmit.eugene;

public interface UrlConverter {
    UrlFragments parseUrl(String url);
    String toString(UrlFragments fragments);
    void setConfig(UrlConfig config);
}
