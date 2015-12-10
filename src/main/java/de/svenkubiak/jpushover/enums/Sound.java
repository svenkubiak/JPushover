package de.svenkubiak.jpushover.enums;

/**
 * 
 * @author svenkubiak
 *
 */
public enum Sound {
    PUSHOVER("pushover"),
    BIKE("bike"),
    BUGLE("bugle"),
    CASHREGISTET("cashregister"),
    CLASSICAL("classical"),
    COSMIC("cosmic"),
    FALLING("falling"),
    GAMELAN("gamelan"),
    INCOMING("incoming"),
    INTERMISSION("intermission"),
    MAGIC("magic"),
    MECHANICAL("mechanical"),
    PIANOBAR("pianobar"),
    SIREN("siren"),
    SPACEALARM("spacealarm"),
    TUGBOAT("tugboat"),
    ALIEN("alien"),
    CLIMB("climb"),
    PERSISTENT("persistent"),
    ECHO("echo"),
    UPDOWN("updown"),
    NONE("none");
    
    private final String value;

    Sound (String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
