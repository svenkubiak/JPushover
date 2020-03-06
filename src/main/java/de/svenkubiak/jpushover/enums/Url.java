package de.svenkubiak.jpushover.enums;

/**
 *
 * @author svenkubiak
 *
 */
public enum Url {
    GLANCES("https://api.pushover.net/1/glances.json"),
    MESSAGES("https://api.pushover.net/1/messages.json"),
    VALIDATE("https://api.pushover.net/1/users/validate.json");

    private final String value;

    Url (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}