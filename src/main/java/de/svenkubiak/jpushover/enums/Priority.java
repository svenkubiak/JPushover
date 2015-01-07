package de.svenkubiak.jpushover.enums;

public enum Priority {
    LOWEST("-2"),
    LOW("-1"),
    NORMAL("0"),
    HIGH("1"),
    EMERGENCY("2");
    
    private final String value;

    Priority (String value) {
        this.value = value;
    }
    
    public String value() {
        return this.value;
    }
}