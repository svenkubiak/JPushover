package de.svenkubiak.jpushover.enums;

/**
 *
 * @author svenkubiak
 *
 */
public enum Param {
    ATTACHMENT("attachment"),
    CALLBACK("callback"),
    DEVICE("device"),
    EXPIRE("expire"),
    HTML("html"),
    MESSAGE("message"),
    PRIORITY("priority"),
    RETRY("retry"),
    SOUND("sound"),
    TIMESTAMP("timestamp"),
    TITLE("title"),
    TOKEN("token"),
    URL("url"),
    URLTITLE("urltitle"),
    USER("user"),
    TEXT("text"),
    SUBTEXT("subtext"),
    COUNT("count"),
    PERCENT("percent");

    private final String value;

    Param (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}