package ru.bfu.ipmit.eugene;


public class MainClass {

    public static void main(String[] args) {

        UrlConfig config = new UrlConfig("/", "\\.", ":", "\\?", "&", "=");
        UrlConverter urlConverter = new UrlConverterImpl();
        urlConverter.setConfig(config);

        String url = "https://github.com/search?q=java&type=code";
        UrlFragments fragments = urlConverter.parseUrl(url);
        System.out.println(urlConverter.toString(fragments));

        url = "(POST)https://maps.google.com";
        fragments = urlConverter.parseUrl(url);
        System.out.println(urlConverter.toString(fragments));

        config = new UrlConfig("-", ",", ";", "!", "%", "+");
        urlConverter.setConfig(config);
        url = "https;-github,com-search!q+java$type+code";
        fragments = urlConverter.parseUrl(url);
        System.out.println(urlConverter.toString(fragments));


    }

}
