package de.svenkubiak.jpushover.enums;

/**
 *
 * @author svenkubiak
 *
 */
public enum Constants {
    ATTACHMENT("attachment"),
    CALLBACK("callback"),
    DEVICE("device"),
    EXPIRE("expire"),
    HTML("html"),
    MESSAGE("message"),
    MESSAGES_URL("https://api.pushover.net/1/messages.json"),
    PRIORITY("priority"),
    RETRY("retry"),
    SOUND("sound"),
    TIMESTAMP("timestamp"),
    TITLE("title"),
    TOKEN("token"),
    URL("url"),
    URLTITLE("urltitle"),
    USER("user"),
    VALIDATION_URL("https://api.pushover.net/1/users/validate.json");

    private final String value;

    Constants (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}