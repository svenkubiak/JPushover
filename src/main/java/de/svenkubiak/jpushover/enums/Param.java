package de.svenkubiak.jpushover.enums;

public enum Param {
    CALLBACK("callback"),
    COUNT("count"),
    DEVICE("device"),
    EXPIRE("expire"),
    HTML("html"),
    MESSAGE("message"),
    MONOSPACE("monospace"),
    PERCENT("percent"),
    PRIORITY("priority"),
    RETRY("retry"),
    SOUND("sound"),
    SUBTEXT("subtext"),
    TEXT("text"),
    TIMESTAMP("timestamp"),
    TITLE("title"),
    TOKEN("token"),
    URL("url"),
    URL_TITLE("url_title"),
    USER("user"),
    TTL("ttl");

    private final String value;

    Param (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}