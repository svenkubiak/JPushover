package de.svenkubiak.jpushover.enums;

/**
 * 
 * @author svenkubiak
 *
 */
public enum Sound {
    ALIEN("alien"),
    BIKE("bike"),
    BUGLE("bugle"),
    CASHREGISTER("cashregister"),
    CLASSICAL("classical"),
    CLIMB("climb"),
    COSMIC("cosmic"),
    ECHO("echo"),
    FALLING("falling"),
    GAMELAN("gamelan"),
    INCOMING("incoming"),
    INTERMISSION("intermission"),
    MAGIC("magic"),
    MECHANICAL("mechanical"),
    NONE("none"),
    PERSISTENT("persistent"),
    PIANOBAR("pianobar"),
    PUSHOVER("pushover"),
    SIREN("siren"),
    SPACEALARM("spacealarm"),
    TUGBOAT("tugboat"),
    UPDOWN("updown");
    
    private final String value;

    Sound (String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}