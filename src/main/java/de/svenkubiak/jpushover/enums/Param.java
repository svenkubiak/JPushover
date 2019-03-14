package de.svenkubiak.jpushover.enums;

/**
 *
 * @author svenkubiak
 *
 */
public enum Param {
    ATTACHMENT("attachment"),
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
    URLTITLE("urltitle"),
    USER("user");

    private final String value;

    Param (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}