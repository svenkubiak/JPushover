package de.svenkubiak.jpushover.enums;

public enum Sound {
    PUSHOVER("pushover"),
    BIKE("bike"),
    BUGLE("bugke"),
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
    
    public String value() {
        return this.value;
    }
}
