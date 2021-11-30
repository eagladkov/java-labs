package ru.bfu.ipmit.eugene;
interface ExtUrlFragments extends UrlFragments {
    String getHTTPMethod();
    String[] getHeaders();
    String[] getBody();
}
