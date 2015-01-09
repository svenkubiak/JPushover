package de.svenkubiak.jpushover.enums;

/**
 * 
 * @author svenkubiak
 *
 */
public enum Constants {
    MESSAGES_URL("https://api.pushover.net/1/messages.json"),
    VALIDATION_URL("https://api.pushover.net/1/users/validate.json"),
    MESSAGE("message"),
    TITLE("title"),
    DEVICE("device"),
    USER("user"),
    TOKEN("token"),
    SOUND("sound"),
    PRIORITY("priority"),
    TIMESTAMP("timestamp"),
    URL("url"),
    URLTITLE("urltitle"),
    CALLBACK("callback"),
    EXPIRE("expire"),
    RETRY("retry");
    
    private final String value;

    Constants (String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }
}