package ru.bfu.ipmit.eugene;

public class MainClass {

    public static void main(String[] args) {

        UrlConfig config = new UrlConfig("/", "\\.", ":", "\\?", "&", "=");
        BrowserConfig browserConfig = new PremiumSub();
        UrlConverter urlConverter = new UrlConverterImpl();
        urlConverter.setConfig(config);
        urlConverter.setBrowserConfig(browserConfig);

//        String url = "https://github.com/search?q=java&type=code";
//        UrlFragments fragments = urlConverter.parseUrl(url);
//        System.out.println(urlConverter.toString(fragments));

        String url = "(PUT)https://maps.google.com/map?q1=qwe??b1=ewq";
        UrlFragments fragments = urlConverter.parseUrl(url);
        System.out.println(urlConverter.toString(fragments));

//        String url = "(POST)https://maps.google.com/map?q1=qwe??b1=ewq";
//        UrlFragments fragments = urlConverter.parseUrl(url);
//        System.out.println(urlConverter.toString(fragments));

//        String url = "(DELETE)https://maps.google.com/map?q1=qwe??b1=ewq???h1=tre";
//        UrlFragments fragments = urlConverter.parseUrl(url);
//        System.out.println(urlConverter.toString(fragments));


//        UrlConfig config = new UrlConfig("-", ",", ";", "!", "%", "+");
//        UrlConverter urlConverter = new UrlConverterImpl();
//        urlConverter.setConfig(config);
//
//        String url = "https;-github,com-search!q+java%type+code";
//        UrlFragments fragments = urlConverter.parseUrl(url);
//        System.out.println(urlConverter.toString(fragments));


//        UrlConfig config = new UrlConfig("a", ",", ";", "!", "%", "+");
//        BrowserConfig browserConfig = new PremiumSub();
//        UrlConverter urlConverter = new UrlConverterImpl();
//        urlConverter.setConfig(config);
//        urlConverter.setBrowserConfig(browserConfig);
//
//        String url = "https://github.com/search?q=java&type=code";
//        UrlFragments fragments = urlConverter.parseUrl(url);
//        System.out.println(urlConverter.toString(fragments));
    }
}
