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
    CASH_REGISTER("cashregister"),
    CLASSICAL("classical"),
    CLIMB("climb"),
    COSMIC("cosmic"),
    ECHO("echo"),
    FALLING("falling"),
    GAME_LAN("gamelan"),
    INCOMING("incoming"),
    INTERMISSION("intermission"),
    MAGIC("magic"),
    MECHANICAL("mechanical"),
    NONE("none"),
    PERSISTENT("persistent"),
    PIANO_BAR("pianobar"),
    PUSHOVER("pushover"),
    SIREN("siren"),
    SPACE_ALARM("spacealarm"),
    TUGBOAT("tugboat"),
    UP_DOWN("updown");
    
    private final String value;

    Sound (String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}