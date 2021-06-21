package de.svenkubiak.jpushover.enums;

/**
 *
 * @author svenkubiak
 *
 */
public enum Url {
    DELETE("https://api.pushover.net/1/devices/###DEVICE_ID###/update_highest_message.json"),
    DEVICE("https://api.pushover.net/1/devices.json"),
    GLANCES("https://api.pushover.net/1/glances.json"),
    LOGIN("https://api.pushover.net/1/users/login.json"),
    MESSAGES("https://api.pushover.net/1/messages.json"),
    VALIDATE("https://api.pushover.net/1/users/validate.json"),
    WEBSOCKET("wss://client.pushover.net/push");    
    
    private final String value;

    Url (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}