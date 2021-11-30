package ru.bfu.ipmit.eugene;
interface UrlFragments {
    String getProtocol();
    String[] getHostFragments();
    String[] getPathFragments();
    String[] getQueryParamValues();
}
