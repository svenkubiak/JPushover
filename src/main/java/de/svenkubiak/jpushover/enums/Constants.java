package de.svenkubiak.jpushover.enums;

public enum Constants {
    PUSHOVER_URL("https://api.pushover.net/1/messages.json"),
    MESSAGE("message"),
    TITLE("title"),
    DEVICE("device"),
    USER("user"),
    TOKEN("token"),
    SOUND("sound"),
    PRIORITY("priority"),
    TIMESTAMP("timestamp"),
    URL("url"),
    URLTITLE("urltitle");

    private final String value;

    Constants (String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }
}