package ru.bfu.ipmit.eugene;

public class UrlConfig {
    private String slash;
    private String point;
    private String colon;
    private String question;
    private String and;
    private String equally;

    public UrlConfig(String slash, String point, String colon, String question, String and, String equally) {
        this.slash = slash;
        this.point = point;
        this.colon = colon;
        this.question = question;
        this.and = and;
        this.equally = equally;
    }

    public String getSlash() {
        return slash;
    }

    public void setSlash(String slash) {
        this.slash = slash;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getColon() {
        return colon;
    }

    public void setColon(String colon) {
        this.colon = colon;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnd() {
        return and;
    }

    public void setAnd(String and) {
        this.and = and;
    }

    public String getEqually() {
        return equally;
    }

    public void setEqually(String equally) {
        this.equally = equally;
    }
}
