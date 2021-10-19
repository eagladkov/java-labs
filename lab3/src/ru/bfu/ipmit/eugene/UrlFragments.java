package ru.bfu.ipmit.eugene;

public interface  UrlFragments {
    String getProtocol();
    String[] getHostFragments();
    String[] getPathFragments();
    String[] getQueryParamValues();
}